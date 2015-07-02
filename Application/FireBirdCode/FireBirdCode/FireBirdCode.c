


#define F_CPU 14745600
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>

#include "lcd.c"
#include "Position_Encoder.h"
#include "Servo_Motor.h"

unsigned char left_motor_velocity = 0x00;
unsigned char right_motor_velocity = 0x00;
unsigned char horz_servo_pos = 0x00;
unsigned char vert_servo_pos = 0x00;
unsigned char BATT_VALUE, WL_CENTER, WL_LEFT, WL_RIGHT, SHARP_3, SHARP_2, SHARP_4, SHARP_1, SHARP_5, IR1, IR2, IR3, IR4, IR5, IR6, IR7, IR8; 
unsigned char robot_id = 0x05;
unsigned char ADC_flag;
unsigned char sharp, distance, adc_reading;
unsigned int value, distance_position_encoder,angle_rotation,distance_q,distance_r,rotation_q, rotation_r, a;
float BATT_Voltage, BATT_V;
unsigned char ADC_Conversion(unsigned char);
unsigned char ADC_Value;

volatile static int flag =0, flag_position_encoder_forward =0, flag_position_encoder_forward_1=0, flag_position_encoder_backward=0, flag_position_encoder_backward_1=0,flag_rotation_right=0,flag_rotation_right_1=0,flag_rotation_left=0,flag_rotation_left_1=0;
volatile static int flag_servo1=0, flag_servo2=0, flag_servo3=0, flag_lcd=0, flag_lcd_main=0;
char temp1, temp2, temp3, v1, v2;
static unsigned int temp_distance, temp_rotation;
volatile static int count = 1,q=0, count_distance=1, count_rotation=1, flag_servo1_main=1, flag_servo2_main=1, flag_servo3_main=1, s1=0, lcd_row=0, lcd_column=0, count_lcd=1;
//Shaft encoder related variables
unsigned int left_motor_pulse_count = 0x00; 		//incremented at each isr
unsigned int left_motor_pulse_count_locked = 0x00;  // locked at serial comm isr

unsigned int right_motor_pulse_count = 0x00;        //incremented at each isr
unsigned int right_motor_pulse_count_locked = 0x00; // locked at serial comm isr
//-------------------------------------------------------------------------------

void motion_pin_config (void)
{
 DDRA = DDRA | 0x0F;   //set direction of the PORTA 3 to PORTA 0 pins as output
 PORTA = PORTA & 0xF0; // set initial value of the PORTA 3 to PORTA 0 pins to logic 0
 DDRL = DDRL | 0x18;   //Setting PL3 and PL4 pins as output for PWM generation
 PORTL = PORTL | 0x18; //PL3 and PL4 pins are for velocity control using PWM
}

//Configure PORTB 5 pin for servo motor 1 operation
/*void servo1_pin_config (void)
{
 DDRB  = DDRB | 0x20;  //making PORTB 5 pin output
 PORTB = PORTB | 0x20; //setting PORTB 5 pin to logic 1
}

//Configure PORTB 6 pin for servo motor 2 operation
void servo2_pin_config (void)
{
 DDRB  = DDRB | 0x40;  //making PORTB 6 pin output
 PORTB = PORTB | 0x40; //setting PORTB 6 pin to logic 1
}

//Configure PORTB 7 pin for servo motor 3 operation
void servo3_pin_config (void)
{
 DDRB  = DDRB | 0x80;  //making PORTB 7 pin output
 PORTB = PORTB | 0x80; //setting PORTB 7 pin to logic 1
}*/

void spi_pin_config (void)
{
 DDRB = DDRB | 0x07;
 PORTB = PORTB | 0x07;
}

void lcd_port_config (void)
{
 DDRC = DDRC | 0xF7;    //all the LCD pin's direction set as output
 PORTC = PORTC & 0x80;  // all the LCD pins are set to logic 0 except PORTC 7
}

//Function to initialize Buzzer 
void buzzer_pin_config (void)
{
 DDRC = DDRC | 0x08;		//Setting PORTC 3 as output
 PORTC = PORTC & 0xF7;		//Setting PORTC 3 logic low to turnoff buzzer
}

//Function to configure INT4 (PORTE 4) pin as input for the left position encoder
/*void left_encoder_pin_config (void)
{
 DDRE  = DDRE & 0xEF;  //Set the direction of the PORTE 4 pin as input
 PORTE = PORTE | 0x10; //Enable internal pull-up for PORTE 4 pin
}

//Function to configure INT5 (PORTE 5) pin as input for the right position encoder
void right_encoder_pin_config (void)
{
 DDRE  = DDRE & 0xDF;  //Set the direction of the PORTE 4 pin as input
 PORTE = PORTE | 0x20; //Enable internal pull-up for PORTE 4 pin
}*/

//ADC pin configuration
void adc_pin_config (void)
{
 DDRF = 0x00;  //set PORTF direction as input
 PORTF = 0x00; //set PORTF pins floating
 DDRK = 0x00;  //set PORTK direction as input
 PORTK = 0x00; //set PORTK pins floating
}

void pwm_port_config (void)
{
	DDRL = DDRL | 0x18;   //Setting PL3 and PL4 pins as output for PWM generation
	PORTL = PORTL | 0x18; //PL3 and PL4 pins are for velocity control using PWM.
}

//Port init for different modules
void port_init(void)
{
 motion_pin_config();
 servo1_pin_config();
 servo2_pin_config();
 servo3_pin_config();
 spi_pin_config();
 lcd_port_config();
 buzzer_pin_config();           
 left_encoder_pin_config();
 right_encoder_pin_config();
 adc_pin_config();
 pwm_port_config();
 encoder_pin_config(); //position encoder pin config 	
}

// Timer 5 initialized in PWM mode for velocity control
// Prescale:256
// PWM 8bit fast, TOP=0x00FF
// Timer Frequency:225.000Hz
void timer5_init()
{
	TCCR5B = 0x00;	//Stop
	TCNT5H = 0xFF;	//Counter higher 8-bit value to which OCR5xH value is compared with
	TCNT5L = 0x01;	//Counter lower 8-bit value to which OCR5xH value is compared with
	OCR5AH = 0x00;	//Output compare register high value for Left Motor
	OCR5AL = 0xFF;	//Output compare register low value for Left Motor
	OCR5BH = 0x00;	//Output compare register high value for Right Motor
	OCR5BL = 0xFF;	//Output compare register low value for Right Motor
	OCR5CH = 0x00;	//Output compare register high value for Motor C1
	OCR5CL = 0xFF;	//Output compare register low value for Motor C1
	TCCR5A = 0xA9;	/*{COM5A1=1, COM5A0=0; COM5B1=1, COM5B0=0; COM5C1=1 COM5C0=0}
 					  For Overriding normal port functionality to OCRnA outputs.
				  	  {WGM51=0, WGM50=1} Along With WGM52 in TCCR5B for Selecting FAST PWM 8-bit Mode*/
	
	TCCR5B = 0x0B;	//WGM12=1; CS12=0, CS11=1, CS10=1 (Prescaler=64)
}

//TIMER1 initialize - prescale:256
// WGM: 7) PWM 10bit fast, TOP=0x03FF
// desired value: 40Hz
// actual value: 42.187Hz (5.2%)
void timer1_init(void)
{
 TCCR1B = 0x00; //stop
 TCNT1H = 0xFC; //setup
 TCNT1L = 0x01;
 OCR1AH = 0x03;
 OCR1AL = 0xFF;
 OCR1BH = 0x03;
 OCR1BL = 0xFF;
 OCR1CH = 0x00;
 OCR1CL = 0x00;
 //ICR1H  = 0x03;
 //ICR1L  = 0xFF;
 TCCR1A = 0xA3;
 TCCR1C = 0x00;
 TCCR1B = 0x0C; //start Timer
}

//UART2 initialize
// desired baud rate:115200
// char size: 8 bit
// parity: Disabled
void uart2_init(void)
{
 UCSR2B = 0x00; //disable while setting baud rate
 UCSR2A = 0x00;
 UCSR2C = 0x06;
 UBRR2H = 0x00; //set baud rate hi
 UBRR2L = 0x07; //set baud rate lo
 UCSR2B = 0x98;
}

//SPI initialize
// clock rate: 921600hz
void spi_init(void)
{
 SPCR = 0x53; //setup SPI
 SPSR = 0x00; //setup SPI
 SPDR = 0x00;
}

//ADC initialize
// Conversion time: 56uS
void adc_init(void)
{
	ADCSRA = 0x00;
	ADCSRB = 0x00;		//MUX5 = 0
	ADMUX = 0x20;		//Vref=5V external --- ADLAR=1 --- MUX4:0 = 0000
	ACSR = 0x80;
	ADCSRA = 0x86;		//ADEN=1 --- ADIE=1 --- ADPS2:0 = 1 1 0
}

//-------------------------------------------------------------------------------
/*void left_position_encoder_interrupt_init (void) //Interrupt 4 enable
{
 cli(); //Clears the global interrupt
 EICRB = EICRB | 0x02; // INT4 is set to trigger with falling edge
 EIMSK = EIMSK | 0x10; // Enable Interrupt INT4 for left position encoder
 sei();   // Enables the global interrupt 
}

void right_position_encoder_interrupt_init (void) //Interrupt 5 enable
{
 cli(); //Clears the global interrupt
 EICRB = EICRB | 0x08; // INT5 is set to trigger with falling edge
 EIMSK = EIMSK | 0x20; // Enable Interrupt INT5 for right position encoder
 sei();   // Enables the global interrupt 
}

//-------------------------------------------------------------------------------
//ISR for right position encoder
//-------------------------------------------------------------------------------

ISR(INT5_vect)  
{
 right_motor_pulse_count++;
 //external interupt on INT5
}


//ISR for left position encoder

ISR(INT4_vect)   
{
 left_motor_pulse_count++;
 //external interupt on INT4
}*/

//-------------------------------------------------------------------------------
//-- ADC Conversion Function --------------
//-------------------------------------------------------------------------------
unsigned char ADC_Conversion(unsigned char ch)
{
unsigned char a;
 if(ch>7)
	{
		ADCSRB = 0x08;
	}
 ch = ch & 0x07;			  //Store only 3 LSB bits
 ADMUX= 0x20 | ch;			  //Select the ADC channel with left adjust select
 ADC_flag = 0x00; 			  //Clear the user defined flag
 ADCSRA = ADCSRA | 0x40;	  //Set start conversion bit
 while((ADCSRA&0x10)==0);	  //Wait for ADC conversion to complete
 a=ADCH;
 ADCSRA = ADCSRA|0x10;        //clear ADIF (ADC Interrupt Flag) by writing 1 to it
 ADCSRB = 0x00;
 return a;
}

//-------------------------------------------------------------------------------
//-- Function To Transmit/Receive through SPI interface ------
//-------------------------------------------------------------------------------
unsigned char spi_master_tx_and_rx (unsigned char data)
{
 unsigned char rx_data = 0;

 PORTB = PORTB & 0xFE;        // make SS pin low
 SPDR = data;
 while(!(SPSR & (1<<SPIF)));  //wait for data transmission to complete

 _delay_ms(1);                //time for ADC conversion in the slave microcontroller
 
 SPDR = 0x50;                 // send dummy byte to read back data from the slave microcontroller
 while(!(SPSR & (1<<SPIF)));  //wait for data reception to complete
 rx_data = SPDR;
 PORTB = PORTB | 0x01;        // make SS high
 return rx_data;
}

//-------------------------------------------------------------------------------

void sensor_data_interpretation(void) //ld, fd, rd, light int
{
 
 WL_LEFT = ADC_Conversion(3);
  
 WL_CENTER = ADC_Conversion(2);
  
 WL_RIGHT = ADC_Conversion(1);
 
 
 IR1 = ADC_Conversion(4); 
 
 IR2 = ADC_Conversion(5);
 
 IR3 = ADC_Conversion(6);
  
 IR4 = ADC_Conversion(7);

 IR5 = ADC_Conversion(8);
 
 IR6 = spi_master_tx_and_rx(5);  
 
 IR7 = spi_master_tx_and_rx (6); 
 
 IR8 = spi_master_tx_and_rx (7);
 
 
 SHARP_1 = ADC_Conversion(9);
 //lcd_print(2,1,SHARP_1,3);
 
 SHARP_2 = ADC_Conversion(10);
 //lcd_print(2,5,SHARP_2,3);
 
 SHARP_3 = ADC_Conversion(11);
 //lcd_print(1,13,SHARP_3,3);
 
 SHARP_4 = ADC_Conversion(12);
 //lcd_print(2,9,SHARP_4,3);
 
 SHARP_5 = ADC_Conversion(13);
 
 BATT_VALUE = ADC_Conversion(0);
  
}

//-------------------------------------------------------------------------------
//------Function used for setting motor's direction----------
//-------------------------------------------------------------------------------
void motion_set(unsigned char ucDirection)
{
 unsigned char ucPortARestore = 0;

 ucDirection &= 0x0F;            // removing upper nibbel for the protection
 ucPortARestore = PORTA;         // reading the PORTA original status
 ucPortARestore &= 0xF0;         // making lower direction nibbel to 0
 ucPortARestore |= ucDirection;  // adding lower nibbel for forward command and restoring the PORTA status
 PORTA = ucPortARestore;         // executing the command
}

void forward(void) 
{ 
 motion_set(0x06);
}

void back(void)  
{
  motion_set(0x09);  
}

void left(void)  
{
  motion_set(0x05);
}

void right(void)
{
  motion_set(0x0A);
}

void stop(void)
{
  motion_set(0x00);
}

//-------------------------------------------------------------------------------
//------- Function For Buzzer ON ----------
//-------------------------------------------------------------------------------
void buzzer_on(void)
{
 unsigned char portc_restore = 0;
 portc_restore = PORTC; // reading the PORTC original status
 portc_restore |= 0x08; // setting the bit to turn on the buzzer
 PORTC = portc_restore; // executing the command
}

//-------------------------------------------------------------------------------
//------ Function For Buzzer OFF -------------
//-------------------------------------------------------------------------------
void buzzer_off(void)
{
 unsigned char portc_restore = 0;
 portc_restore = PORTC; // reading the PORTC original status
 portc_restore &= 0xF7; // resetting the bit to turn off the buzzer
 PORTC = portc_restore; // executing the command
}

//-------------------------------------------------------------------------------
//------ Velocity Control -------------
//-------------------------------------------------------------------------------
void velocity (unsigned char left_motor, unsigned char right_motor)
{
	OCR5AL = (unsigned char)left_motor;
	OCR5BL = (unsigned char)right_motor;
}

//-------------------------------------------------------------------------------
//------ Position Encoders -------------
//-------------------------------------------------------------------------------
void forward_mm(unsigned int DistanceInMM)
{
	forward();
	linear_distance_mm(DistanceInMM);
}
void backward_mm(unsigned int DistanceInMM)
{
	back();
	linear_distance_mm(DistanceInMM);
}
void left_degrees(unsigned int Degrees)
{
	// 88 pulses for 360 degrees rotation 4.090 degrees per count
	left(); //Turn left
	angle_rotate(Degrees);
}
void right_degrees(unsigned int Degrees)
{
	// 88 pulses for 360 degrees rotation 4.090 degrees per count
	right(); //Turn right
	angle_rotate(Degrees);
}


//-------------------------------------------------------------------------------
//call this routine to initialize all peripherals
void init_devices(void)
{
 //stop errant interrupts until set up
 cli();              //disable all interrupts
 port_init();
 uart2_init();
 adc_init();
 timer1_init();
 timer5_init();
 lcd_set_4bit();
 lcd_init();
 spi_init();
 
 // below for lines are important for Encoder init
 left_position_encoder_interrupt_init();
 right_position_encoder_interrupt_init();
 EICRB  = 0x0A;     //pin change int edge 4:7
 EIMSK  = 0x30;

 sei();             //re-enable interrupts
 //all peripherals are now initialized
}
/*SIGNAL(SIG_USART2_TRANS) 		// ISR for receive complete interrupt
{
	UDR2=q;
	if(q==17)
	UCSR2B=0xD8;
	
	q++;
	
}*/
//-------------------------------------------------------------------------------
SIGNAL(SIG_USART2_RECV) 		// ISR for receive complete interrupt
{
 unsigned char ser_data = 0x00;
 unsigned char ser_data_upper_nibbel = 0x00;
 unsigned char ser_data_lower_nibbel = 0x00;
 //uart has received a character in UDR2
 ser_data = UDR2;
 ser_data_upper_nibbel = ser_data & 0xF0;
 ser_data_lower_nibbel = ser_data & 0x0F;

//__________________________Locomotoion setting commands_____________
if(flag == 1)
  {
	temp1 = ser_data;
	if(count == 1)
	{
		v1 = temp1;
		count++;
		ser_data = 0x00;
	}
	else if(count == 2)
	{
		v2 = temp1;
		flag = 0;
		count=1;
		ser_data = 0x00;
		velocity(v1,v2);
	}
  }
  
  //__________________________ Forward and Backward Movement by Some Distance Command ________________
  if (flag_position_encoder_forward==1)
  {
	  temp2 = ser_data;
	  if (count_distance==1)
	  {
		  distance_q = (unsigned int)temp2;
		  count_distance++;
		  ser_data=0x00;
	  }
	  else if (count_distance==2)
	  {
		  distance_r = (unsigned int)temp2;
		  temp_distance = (distance_q)*255 + (distance_r);
		  count_distance=1;
		  flag_position_encoder_forward_1 = 1;
		  flag_position_encoder_forward = 0;
		  ser_data = 0x00;
	  }
	  
  }
  
  if (flag_position_encoder_backward==1)
  {
	  temp2 = ser_data;
	  if (count_distance==1)
	  {
		  distance_q = (unsigned int)temp2;
		  count_distance++;
		  ser_data=0x00;
	  }
	  else if (count_distance==2)
	  {
		  distance_r = (unsigned int)temp2;
		  temp_distance = (distance_q)*255 + (distance_r);
		  count_distance=1;
		  flag_position_encoder_backward_1 = 1;
		  flag_position_encoder_backward = 0;
		  ser_data = 0x00;
	  }
  }
  
  if (flag_rotation_right==1)
  {
	  temp2=ser_data;
	  if (count_rotation == 1)
	  {
		  rotation_q = (unsigned int)temp2;
		  count_rotation++;
		  ser_data = 0x00;
	  }
	  else if(count_rotation == 2)
	  {
		  rotation_r = (unsigned int)temp2;
		  temp_rotation = (rotation_q)*255 + (rotation_r);
		  count_rotation = 1;
		  flag_rotation_right_1 = 1;
		  flag_rotation_right = 0;	  
	  }
  }
  
  if (flag_rotation_left==1)
  {
	  temp2=ser_data;
	  if (count_rotation == 1)
	  {
		  rotation_q = (unsigned int)temp2;
		  count_rotation++;
		  ser_data = 0x00;
	  }
	  else if(count_rotation == 2)
	  {
		  rotation_r = (unsigned int)temp2;
		  temp_rotation = (rotation_q)*255 + (rotation_r);
		  count_rotation = 1;
		  flag_rotation_left_1 = 1;
		  flag_rotation_left = 0;	  
	  }
  }
  
    //__________________________ Servo Motor Commands ________________
	if (flag_servo1 == 1)
  {
	  temp2 = ser_data;
	  flag_servo1_main=1;
	  flag_servo1 = 0;
	  ser_data = 0x00;
  }
  
  if (flag_servo2 == 1)
  {
	  temp2 = ser_data;
	  flag_servo2_main=1;
	  flag_servo2 = 0;
	  ser_data = 0x00;
  }
  
  if (flag_servo3 == 1)
  {
	  temp2 = ser_data;
	  flag_servo3_main=1;
	  flag_servo3 = 0;
	  ser_data = 0x00;
  }
  
  //__________________________ LCD Command ________________
  if (flag_lcd == 1)
  {
	  temp2 = ser_data;
	  if (count_lcd == 1)
	  {
		  lcd_row = temp2;
		  count_lcd++;
		  ser_data = 0x00;
	  }
	  else if (count_lcd == 2)
	  {
		  lcd_column = temp2;
		  count_lcd++;
		  ser_data == 0x00;
	  }
	  else if (count_lcd == 3)
	  {
		  flag_lcd_main=1;
		  count_lcd = 1;
		  flag_lcd = 0;
		  ser_data = 0x00;
	  }
  }
  
   
 if (ser_data == 0x38) // ASCII value of 8
 {
  forward(); 
 }
 
 if (ser_data == 0x32) // ASCII value of 2
 {
  back();
 }
 
 if (ser_data == 0x34) // ASCII value of 4
 {
  right();
 }
 
 if (ser_data == 0x36) // ASCII value of 6
 {
  left();
 }
 
 if (ser_data == 0x35) // ASCII value of 5
 {
  stop();
 }

//_______________________Shaft encoder data__________________________

 if(ser_data == 0x72) //Lower_byte number of pulse counted
 {
  left_motor_pulse_count_locked = left_motor_pulse_count;
  UDR2 = left_motor_pulse_count_locked % 256;
 }
 
 if(ser_data == 0x73) //Upper_byte number of pulse counted
 {
  UDR2 = left_motor_pulse_count_locked / 256;
 }

 if(ser_data == 0x79) //Lower_byte number of pulse counted
 {
  right_motor_pulse_count_locked = right_motor_pulse_count;
  UDR2 = right_motor_pulse_count_locked % 256;
 }
 
 if(ser_data == 0x7A) //Upper_byte number of pulse counted
 {
  UDR2 = right_motor_pulse_count_locked / 256;
 }
   
//_______________________Servo motors position commands________________
 /*if (ser_data_upper_nibbel == 0x80)
 {
  horz_servo_pos = ser_data_lower_nibbel;
 }
 
 if (ser_data_upper_nibbel == 0x90)
 {
  horz_servo_pos = ((ser_data_lower_nibbel * 16) + horz_servo_pos);
  
  if(horz_servo_pos > 0x65)
  {
  
  horz_servo_pos = 0x65;
  }
 
  if(horz_servo_pos < 0x15)
  {
   horz_servo_pos = 0x15;
  }
 
  OCR1AH = 0x00;
  OCR1AL = horz_servo_pos;
 }
 
 if (ser_data_upper_nibbel == 0xA0)
  {
   vert_servo_pos = ser_data_lower_nibbel;
  }
 
  if (ser_data_upper_nibbel == 0xB0)
  {
   vert_servo_pos = ((ser_data_lower_nibbel * 16) + vert_servo_pos);
 
   if(vert_servo_pos > 0x65)
   {
    vert_servo_pos = 0x65;
   }
 
   if(vert_servo_pos < 0x15)
   {
    vert_servo_pos = 0x15;
   }
   OCR1BH = 0x00;
   OCR1BL = vert_servo_pos;
  }*/
 

 
 //________________ IR PROXIMITY STATUS COMMANDS + BATT VALUE + WHITE LINE SENSORS + SHARP SENSORS _____________________
 
 if (ser_data == 0x54)
{
	UCSR2B=0xD8;
	UDR2 = 0x59;
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = IR1;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = IR2;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = IR3;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = IR4;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = IR5;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = IR6;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = IR7;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = IR8;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = SHARP_1;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = SHARP_2;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = SHARP_3;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = SHARP_4;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = SHARP_5;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = WL_LEFT;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = WL_CENTER;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = WL_RIGHT;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = BATT_VALUE;
		UCSR2A|=0x40;
	}
	while((UCSR2A&0x40) ==0x00);
	{UDR2 = 0x5A;
		UCSR2A|=0x40;
	}
	UCSR2B = 0x98;
}


	
 
 
  
//______________________Identity tag ____________________________
 if (ser_data == 0x6B)
 {
  UDR2 = robot_id;
 }

//________________  Buzzer _______________________________________
 if (ser_data == 0x37) // ASCII value of 7
 {
  buzzer_on();
 }
 
 if (ser_data == 0x39) // ASCII value of 9
 {
  buzzer_off();
 }



//_______________________ Velocity _______________________________
if(ser_data == 0x52)
{
	flag = 1;
}

if (ser_data == 0x53)
{
	velocity(255,255);	
}

//________________________ Position Encoders _______________________________
if (ser_data == 0x55 )
{
	flag_position_encoder_forward = 1;
}

if (ser_data == 0x56)
{
	flag_position_encoder_backward = 1;
}

if (ser_data == 0x57)
{
	flag_rotation_right=1;
}

if (ser_data == 0x58)
{
	flag_rotation_left=1;
}

//_______________________________ Servo Motor ______________________________________________
if (ser_data == 0x80)
{
	flag_servo1 = 1;
}
if (ser_data == 0x81)
{
	flag_servo2 = 1;
}
if (ser_data == 0x82)
{
	flag_servo3 = 1;
}

//________________________________________ LCD ______________________________________________
if (ser_data == 0x83)
{
	flag_lcd = 1;
}

}



// This Function prints the Analog Value Of Corresponding Channel No. at required Row
// and Coloumn Location.
void print_sensor(char row, char coloumn,unsigned char channel)
{
	ADC_Value = ADC_Conversion(channel);
	lcd_print(row, coloumn, ADC_Value, 3);
}

// This Function calculates the actual distance in millimeters(mm) from the input
// analog value of Sharp Sensor.
unsigned int Sharp_GP2D12_estimation(unsigned char adc_reading)
{
	float distance;
	unsigned int distanceInt;
	distance = (int)(10.00*(2799.6*(1.00/(pow(adc_reading,1.1546)))));
	distanceInt = (int)distance;
	if(distanceInt>800)
	{
		distanceInt=800;
	}
	return distanceInt;
}

//-------------------------------------------------------------------------------
//Main Function
//-------------------------------------------------------------------------------
int main(void)
{
	init_devices();
	
  

	while(1)
	{
		if (flag_position_encoder_forward_1 == 1)
		{
			//distance_position_encoder = (unsigned int)temp4;
			forward_mm(temp_distance);
			flag_position_encoder_forward_1=0;
		}
		if (flag_position_encoder_backward_1 == 1)
		{
			//distance_position_encoder = (unsigned int)temp4;
			backward_mm(temp_distance);
			flag_position_encoder_backward_1=0;
		}
		if (flag_rotation_right_1==1)
		{
			//angle_rotation = (unsigned int)temp2;
			right_degrees(temp_rotation);
			flag_rotation_right_1=0;
		}
		if (flag_rotation_left_1==1)
		{
			//angle_rotation = (unsigned int)temp2;
			left_degrees(temp_rotation);
			flag_rotation_left_1=0;
		}
		
		//Rotate Servo Motor 1
		if (flag_servo1_main==1)
		{
			s1 = (int)temp2;
			for(a=0;a<=s1;a++){
				servo_1(a);
				_delay_ms(30);
			}
			_delay_ms(1000);
			servo_1_free();
			flag_servo1_main=0;
		}
		
		//Rotate Servo Motor 2
		if (flag_servo2_main==1)
		{
			s1 = (int)temp2;
			for(a=0;a<=s1;a++){
				servo_2(a);
				_delay_ms(30);
			}
			_delay_ms(1000);
			servo_2_free();
			flag_servo2_main=0;
		}
		
		//Rotate Servo Motor 3
		if (flag_servo3_main==1)
		{
			s1 = (int)temp2;
			for(a=0;a<=s1;a++){
				servo_3(a);
				_delay_ms(30);
			}
			_delay_ms(1000);
			servo_3_free();
			flag_servo3_main=0;
		}
		
		//Print On LCD
		if (flag_lcd_main == 1)
		{
			lcd_cursor(lcd_row, lcd_column);
			lcd_wr_char(temp2);
			flag_lcd_main = 0;
		}
		
		sensor_data_interpretation();
		//print_sensor(1,1,1);				//Prints IR Proximity Sensor 1
		//BATT_V = ADC_Conversion(0);
		//BATT_Voltage = ((ADC_Conversion(0)*100)*0.07902) + 0.7;	//Prints Battery Voltage Status
		//lcd_print(1,1,v1,4);

		//print_sensor(1,1,0);							//Prints Battery voltage binary value

		print_sensor(1,6,5);							//Prints IR Proximity Sensor 1
		print_sensor(1,10,6);							//Prints vlaue of Analog IR Proximity Sensor 2
		print_sensor(1,14,7);							//Prints value of Analog IR Proximity Sensor 3
		//lcd_print(2,1,v2,4);

		//print_sensor(2,9,11); 						//Analog Value Of Front Sharp Sensor

		sharp = ADC_Conversion(11);						//Stores the Analog value of front sharp connected to ADC channel 11 into variable "sharp"
		value = Sharp_GP2D12_estimation(sharp);				//Stores Distance calsulated in a variable "value".
		lcd_print(2,14,value,3);
	}

}
//-------------------------------------------------------------------------------


