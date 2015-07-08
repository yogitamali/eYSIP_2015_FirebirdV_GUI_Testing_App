/*
 *
 * Project Name: GUI Development for Firebird V using- JAVA
 * Author List: Apoorva Bhargava, ERTS Lab, IIT Bombay
 * Filename: FireBirdCode
 * Functions: motion_pin_config(), spi_pin_config(), lcd_port_config(), buzzer_pin_config(), adc_pin_config(), pwm_port_config(), port_init(), timer5_init(), 
 *            timer1_init(), uart2_init(), spi_init(), adc_init(), ADC_Conversion(unsigned char), spi_master_tx_and_rx(unsigned char), sensor_data_interpretation(), 
 *            motion_set(unsigned char), forward(), back(),left(), right(), stop(), buzzer_on(), buzzer_off(), velocity(unsigned char, unsigned char), 
 *            forward_mm(unsigned char), backward_mm(unsigned char), left_degrees(unsigned int), right_degrees(unsigned int), init_devices()    
 * Global Variables: BATT_VALUE, WL_CENTER, WL_LEFT, WL_RIGHT, SHARP_3, SHARP_2, SHARP_4, SHARP_1, SHARP_5, IR1, IR2, IR3, IR4, IR5, IR6, IR7, IR8, ADC_flag, 
 *                   sharp, distance, adc_reading, value, distance_position_encoder,angle_rotation,distance_q,distance_r,rotation_q, rotation_r, a, ADC_Value, 
 *                   flag_velocity, flag_position_encoder_forward, flag_position_encoder_forward_1, flag_position_encoder_backward, flag_position_encoder_backward_1, 
 *                   flag_rotation_right, flag_rotation_right_1, flag_rotation_left, flag_rotation_left_1, flag_servo1=0, flag_servo2=0, flag_servo3=0, flag_lcd=0, 
 *                   flag_lcd_main=0, flag_bar_graph_led=0, flag_bar_graph_led_main, temp_data, vleft, vright, temp_distance, temp_rotation, count = 1,q=0, 
 *                   count_distance, count_rotation, flag_servo1_main, flag_servo2_main, flag_servo3_main, s1, lcd_row
 *
 */

#define F_CPU 14745600
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>

#include "lcd.c"
#include "Position_Encoder.h"
#include "Servo_Motor.h"

unsigned char BATT_VALUE, WL_CENTER, WL_LEFT, WL_RIGHT, SHARP_3, SHARP_2, SHARP_4, SHARP_1, SHARP_5, IR1, IR2, IR3, IR4, IR5, IR6, IR7, IR8; 
unsigned char robot_id = 0x05;
unsigned char ADC_flag;
unsigned char sharp, distance, adc_reading;
unsigned int value, distance_position_encoder,angle_rotation,distance_q,distance_r,rotation_q, rotation_r, a;
unsigned char ADC_Value;
volatile static int flag_velocity=0, flag_position_encoder_forward=0, flag_position_encoder_forward_1=0, flag_position_encoder_backward=0, flag_position_encoder_backward_1=0,flag_rotation_right=0,flag_rotation_right_1=0,flag_rotation_left=0,flag_rotation_left_1=0;
volatile static int flag_servo1=0, flag_servo2=0, flag_servo3=0, flag_lcd=0, flag_lcd_main=0, flag_bar_graph_led=0,flag_bar_graph_led_main=0;
char temp_data, vleft, vright;
static unsigned int temp_distance, temp_rotation;
volatile static int count = 1,q=0, count_distance=1, count_rotation=1, flag_servo1_main=1, flag_servo2_main=1, flag_servo3_main=1, s1=0, lcd_row=0, lcd_column=0, count_lcd=1;
unsigned char ADC_Conversion(unsigned char);
//-------------------------------------------------------------------------------

/*
* Function Name:	motion_pin_config
* Input:			None
* Output:			Sets PA0-PA3 as output pins
* Logic:			Output of the left motor is connected to PA0 and PA1 and output of right motor is connected to PA2 and PA3.
*					Thus by giving logic '1' to DDRA0-DDRA3 these pins are set as output pins.
* Example Call:		motion_pin_config();
*
*/
void motion_pin_config (void)
{
 DDRA = DDRA | 0x0F;   //set direction of the PORTA 3 to PORTA 0 pins as output
 PORTA = PORTA & 0xF0; // set initial value of the PORTA 3 to PORTA 0 pins to logic 0
 DDRL = DDRL | 0x18;   //Setting PL3 and PL4 pins as output for PWM generation
 PORTL = PORTL | 0x18; //PL3 and PL4 pins are for velocity control using PWM
}

/*
* Function Name:	spi_pin_config
* Input:			None
* Output:			Initializes pins for serial communication
* Logic:
* Example Call:		spi_pin_config();
*
*/
void spi_pin_config (void)
{
 DDRB = DDRB | 0x07;
 PORTB = PORTB | 0x07;
}

/*
* Function Name:	lcd_port_config
* Input:			None
* Output:			all the LCD pins are set to logic 0 except PORTC 7
* Logic:			All the LCD pin's direction set as output and All the LCD pins are set to logic 0 except PORTC 7.
* Example Call:		lcd_port_config();
*
*/
void lcd_port_config (void)
{
 DDRC = DDRC | 0xF7;    //all the LCD pin's direction set as output
 PORTC = PORTC & 0x80;  // all the LCD pins are set to logic 0 except PORTC 7
}

/*
* Function Name:	buzzer_pin_config
* Input:			None
* Output:			Initializes the buzzer
* Logic:			Buzzer is connected to pin 3 of PORTC. Giving logic '1' to DDRC 3 sets the pin as output pin.
* Example Call:		buzzer_pin_config();
*
*/ 
void buzzer_pin_config (void)
{
 DDRC = DDRC | 0x08;		//Setting PORTC 3 as output
 PORTC = PORTC & 0xF7;		//Setting PORTC 3 logic low to turnoff buzzer
}


/*
* Function Name:	adc_pin_config
* Input:			None
* Output:			Initializes the ADC ports.
* Logic:			PORTF and PORTK are set as input ports  by giving logic '0' to DDRF and DDRK
* Example Call:		adc_pin_config();
*
*/
void adc_pin_config (void)
{
 DDRF = 0x00;  //set PORTF direction as input
 PORTF = 0x00; //set PORTF pins floating
 DDRK = 0x00;  //set PORTK direction as input
 PORTK = 0x00; //set PORTK pins floating
}

/*
* Function Name:	pwm_port_config
* Input:			None
* Output:			Initializes PORTL for velocity control
* Logic:			PORTL is defined as output port by giving logic '1' to DDRL.
* Example Call:		pwm_port_config();
*
*/
void pwm_port_config (void)
{
	DDRL = DDRL | 0x18;   //Setting PL3 and PL4 pins as output for PWM generation
	PORTL = PORTL | 0x18; //PL3 and PL4 pins are for velocity control using PWM.
}


/*
* Function Name:	port_init
* Input:			None
* Output:			Initializes various ports as input/output ports
* Logic:			Calls other functions to initialize the various ports
* Example Call:		port_init();
*
*/
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

/*
* Function Name:	timer5_init
* Input:			None
* Output:			Initializes the timer 5 for controlling speed of DC motors using PWM
* Logic:			Timer is used in fast PWM mode to vary the speed of motors
*					TCCR5A is a timer counter control register and is used to configure the timer 5 for PWM generation
*					TCCR5B register is used along with TCCR5A to control the timer
*					TCNT register counts upwards or downwards according to the clock frequency
*					OCR5n compares itself from TCNT counter and set flags when match occurs
*					OCR5AL is connected to left motor and OCR5BL is connected to right motor
* Example Call:		timer5_init();
*
*/
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


/*
* Function Name:	timer1_init
* Input:			None
* Output:			Initializes timer1 in 10 bit fast pwm mode
* Logic:			prescale:256
*					WGM: 7) PWM 10bit fast, TOP=0x03FF
*					actual value: 52.25Hz
* Example Call:		timer1_init();
*
*/
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


/*
* Function Name:	spi__init
* Input:			None
* Output:
* Logic:
* Example Call:		spi_init();
*
*/
//SPI initialize
// clock rate: 921600hz
void spi_init(void)
{
 SPCR = 0x53; //setup SPI
 SPSR = 0x00; //setup SPI
 SPDR = 0x00;
}

/*
* Function Name:	adc_init
* Input:			None
* Output:			Initializes the four ADC registers
* Logic:			ADCSRA -->

					Bit Symbol	Description					Bit Value
					7	ADEN	ADC Enable					 1
					6	ADSC	ADC Start Conversion		 0
					5	ADATE	ADC Auto Trigger Enable		 0
					4	ADIF	ADC Interrupt Flag			 0
					3	ADIE	ADC Interrupt Enable		 0
					2	ADPS2	ADC Prescaler Select Bits    1
					1	ADPS1	ADC Prescaler Select Bits    1
					0	ADPS0	ADC Prescaler Select Bits	 0
														ADCSRA=0x86
					
					ADCSRB -->
					Bit Symbol Description								Bit Value
					7	-		Reserved Bit							-
					6	ACME	Analog Comparator Multiplexer Enable	0
					5	-		Reserved Bit							-
					4	-		Reserved Bit							-
					3	MUX5	ADC Channel selection bit-5				0
					2	ADTS2	ADC Auto Trigger Source Bits			0
					1	ADTS1	ADC Auto Trigger Source Bits			0
					0	ADTS0	ADC Auto Trigger Source Bits			0
												
																	ADCSRB=0x00
																	
					ADMUX -->
					Bit Symbol	Description						Bit Value
					7	REFS1	Reference Selection Bit			0
					6	REFS2	Reference Selection Bit			0
					5	ADLAR	ADC Left Adjust Result			1
					4	MUX4	ADC Channel selection bit-4		0
					3	MUX3	ADC Channel selection bit-3		0
					2	MUX2	ADC Channel selection bit-2		0
					1	MUX1	ADC Channel selection bit-1		0
					0	MUX0	ADC Channel selection bit-0		0
											
																ADMUX=0x20
																
					ACSR -->
					Bit Symbol	Description									Bit Value
					7	ACD		Analog Comparator Disable					1
					6	ACBG	Analog Comparator Bandgap Select			0
					5	ACO		Analog Comparator Output					0
					4	ACI		Analog Comparator Interrupt Flag			0
					3	ACIE	Analog Comparator Interrupt Enable			0
					2	ACIC	Analog Comparator Input Capture Enable		0
					1	ACIS1	Analog Comparator Interrupt Mode Select		0
					0	ACIS0	Analog Comparator Interrupt Mode Select		0
					
																		ACSR=0x80
		
* Example Call:		adc_init();
*
*/
void adc_init(void)
{
	ADCSRA = 0x00;
	ADCSRB = 0x00;		//MUX5 = 0
	ADMUX = 0x20;		//Vref=5V external --- ADLAR=1 --- MUX4:0 = 0000
	ACSR = 0x80;
	ADCSRA = 0x86;		//ADEN=1 --- ADIE=1 --- ADPS2:0 = 1 1 0
}

/*
* Function Name:	ADC_Conversion
* Input:			Ch-> character which stores from which channel the analog value has to be read.
* Output:			Returns the digital value of the analog value read from the specified channel
* Logic:			Starts the ADC conversion by setting ADCSRA to 1. When ADCSRA becomes 0 it signifies that the conversion has finished.
*					Result of the conversion is stored in ADCH
* Example Call:		ADC_Conversion(9);
*
*/
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



/*
* Function Name:	sensor_data_interpretation
* Input:			Null
* Output:			Stores the analog value of all the sensors and battery voltage in the variables
* Logic:			ADC_Conversion() function is called
* Example Call:		sensor_data_interpretation()
*
*/
void sensor_data_interpretation(void) 
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
 
 SHARP_2 = ADC_Conversion(10);
 
 SHARP_3 = ADC_Conversion(11);
 
 SHARP_4 = ADC_Conversion(12);
 
 SHARP_5 = ADC_Conversion(13);
 
 BATT_VALUE = ADC_Conversion(0);
  
}

/*
* Function Name:	motion_set
* Input:			Character
* Output:			Rotates the motors in the desired direction
* Logic:			Controls the motion of the motor by setting the value of PORTA
* Example Call:		motion_set(0x06)
*
*/
void motion_set(unsigned char ucDirection)
{
 unsigned char ucPortARestore = 0;

 ucDirection &= 0x0F;            // removing upper nibbel for the protection
 ucPortARestore = PORTA;         // reading the PORTA original status
 ucPortARestore &= 0xF0;         // making lower direction nibbel to 0
 ucPortARestore |= ucDirection;  // adding lower nibbel for forward command and restoring the PORTA status
 PORTA = ucPortARestore;         // executing the command
}

/*
* Function Name:	forward
* Input:			None
* Output:			The bot moves in forward direction
* Logic:			both wheels forward
* Example Call:		forward();
*
*/
void forward(void) 
{ 
 motion_set(0x06);
}

/*
* Function Name:	back
* Input:			None
* Output:			The bot moves in backward direction
* Logic:			Both wheels backward
* Example Call:		back();
*
*/
void back(void)  
{
  motion_set(0x09);  
}

/*
* Function Name:	left
* Input:			None
* Output:			The bot moves in left direction from its position
* Logic:			Left wheel backward, Right wheel forward
* Example Call:		left();
*
*/
void left(void)  
{
  motion_set(0x05);
}

/*
* Function Name:	right
* Input:			None
* Output:			The bot moves in right direction from its position
* Logic:			Right wheel backward, Left wheel forward
* Example Call:		right();
*
*/
void right(void)
{
  motion_set(0x0A);
}


/*
* Function Name:	stop
* Input:			None
* Output:		    stops the bot
* Logic:			Both wheels stop rotating
* Example Call:		stop();
*
*/
void stop(void)
{
  motion_set(0x00);
}

/*
* Function Name:	buzzer_on
* Input:			None
* Output:			Logic '1' to PC3
* Logic:			PC3 is buzzer enable/disable pin. When the logic is '1' it enables the buzzer, and when the logic is '0' the the buzzer is disabled.
* Example Call:		buzzer_on();
*
*/
void buzzer_on(void)
{
 unsigned char portc_restore = 0;
 portc_restore = PORTC; // reading the PORTC original status
 portc_restore |= 0x08; // setting the bit to turn on the buzzer
 PORTC = portc_restore; // executing the command
}

/*
* Function Name:	buzzer_off
* Input:			None
* Output:			Logic '0' to PC3
* Logic:			PC2 is buzzer enable/disable pin. When the logic is '1' it enables the buzzer, and when the logic is '0' the the buzzer is disabled.
* Example Call:		buzzer_off();
*
*/
void buzzer_off(void)
{
 unsigned char portc_restore = 0;
 portc_restore = PORTC; // reading the PORTC original status
 portc_restore &= 0xF7; // resetting the bit to turn off the buzzer
 PORTC = portc_restore; // executing the command
}

/*
* Function Name:	velocity
* Input:			left_motor, right_motor -> character which stores the speed of left motor and right motor respectively
* Output:			Rotates the left and right motors with specified velocity
* Logic:			Controls the speed of motors by changing the pulse width -
*					OCR5AL = (unsigned char)left_motor
*					OCR5BL = (unsigned char)right_motor
* Example Call:		velocity(250,250);
*
*/
void velocity (unsigned char left_motor, unsigned char right_motor)
{
	OCR5AL = (unsigned char)left_motor;
	OCR5BL = (unsigned char)right_motor;
}


/*
* Function Name:	forward_mm
* Input:			DistanceInMM -> integer which specifies by how much distance the bot has to move forward.
* Output:			Moves the robot in forward direction by specified distance
* Logic:			Calls the function forward() and linear_distance_mm(DistanceInMM) to perform the desired task
* Example Call:		forward_mm(45);
*
*/
void forward_mm(unsigned int DistanceInMM)
{
	forward();
	linear_distance_mm(DistanceInMM);
}

/*
* Function Name:	backward_mm
* Input:			DistanceInMM -> integer which specifies by how much distance the bot has to move backward.
* Output:			Moves the robot in backward direction by specified distance
* Logic:			Calls the function backward() and linear_distance_mm(DistanceInMM) to perform the desired task
* Example Call:		backward_mm(45);
*
*/
void backward_mm(unsigned int DistanceInMM)
{
	back();
	linear_distance_mm(DistanceInMM);
}


/*
* Function Name:	left_degrees
* Input:			Degrees -> integer which specifies by how much degree the bot has to rotate in left direction.
* Output:			Turns the robot in left direction by specified degree
* Logic:			Calls the function sharp_left() and angle_rotate(Degrees) to perform the desired task
* Example Call:		left_degrees(45);
*
*/
void left_degrees(unsigned int Degrees)
{
	// 88 pulses for 360 degrees rotation 4.090 degrees per count
	left(); //Turn left
	angle_rotate(Degrees);
}

/*
* Function Name:	right_degrees
* Input:			Degrees -> integer which specifies by how much degree the bot has to rotate in right direction.
* Output:			Turns the robot in right direction by specified degree
* Logic:			Calls the function sharp_right() and angle_rotate(Degrees) to perform the desired task
* Example Call:		right_degrees(45);
*
*/
void right_degrees(unsigned int Degrees)
{
	// 88 pulses for 360 degrees rotation 4.090 degrees per count
	right(); //Turn right
	angle_rotate(Degrees);
}


/*
* Function Name:	init_devices
* Input:			None
* Output:			Initializes all the devices
* Logic:			Calls other functions to initialize the various devices
* Example Call:		init_devices();
*
*/
void init_devices(void)
{
 //stop errant interrupts until set up
 cli();  //Clears the global interrupt             
 port_init();  //Initializes all the ports
 uart2_init();  //Initializes UART2
 adc_init();  //Initializes adc
 timer1_init();  //Initializes the timer 1
 timer5_init();   //Initializes timer 5
 lcd_set_4bit();  
 lcd_init();  //Initializes the LCD
 spi_init();  //Initializes  the spi
 left_position_encoder_interrupt_init(); //Initializes left position encoder interrupt
 right_position_encoder_interrupt_init(); //Initializes right position encoder interrupt
 EICRB  = 0x0A;     //pin change int edge 4:7
 EIMSK  = 0x30;

 sei();             //re-enable interrupts
 //all peripherals are now initialized
}

// Stores the value of UDR2 register ser_data variable and performs the action depending on the value stored in ser_data
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
// if the flag_velocity is one then next two values in UDR2 are stored in vleft and vright which are set as the velocities of left motor and right motor respectively.
if(flag_velocity == 1)
  {
	temp_data = ser_data;
	if(count == 1)
	{
		vleft = temp_data;
		count++;
		ser_data = 0x00;
	}
	else if(count == 2)
	{
		vright = temp_data;
		flag_velocity = 0;
		count=1;
		ser_data = 0x00;
		velocity(vleft,vright);
	}
  }
  
  //__________________________ Forward and Backward Movement by Some Distance Command ________________
  if (flag_position_encoder_forward==1)
  {
	  temp_data = ser_data;
	  if (count_distance==1)
	  {
		  distance_q = (unsigned int)temp_data;
		  count_distance++;
		  ser_data=0x00;
	  }
	  else if (count_distance==2)
	  {
		  distance_r = (unsigned int)temp_data;
		  temp_distance = (distance_q)*255 + (distance_r);
		  count_distance=1;
		  flag_position_encoder_forward_1 = 1;
		  flag_position_encoder_forward = 0;
		  ser_data = 0x00;
	  }
	  
  }
  
  if (flag_position_encoder_backward==1)
  {
	  temp_data = ser_data;
	  if (count_distance==1)
	  {
		  distance_q = (unsigned int)temp_data;
		  count_distance++;
		  ser_data=0x00;
	  }
	  else if (count_distance==2)
	  {
		  distance_r = (unsigned int)temp_data;
		  temp_distance = (distance_q)*255 + (distance_r);
		  count_distance=1;
		  flag_position_encoder_backward_1 = 1;
		  flag_position_encoder_backward = 0;
		  ser_data = 0x00;
	  }
  }
  
  if (flag_rotation_right==1)
  {
	  temp_data=ser_data;
	  if (count_rotation == 1)
	  {
		  rotation_q = (unsigned int)temp_data;
		  count_rotation++;
		  ser_data = 0x00;
	  }
	  else if(count_rotation == 2)
	  {
		  rotation_r = (unsigned int)temp_data;
		  temp_rotation = (rotation_q)*255 + (rotation_r);
		  count_rotation = 1;
		  flag_rotation_right_1 = 1;
		  flag_rotation_right = 0;	  
	  }
  }
  
  if (flag_rotation_left==1)
  {
	  temp_data=ser_data;
	  if (count_rotation == 1)
	  {
		  rotation_q = (unsigned int)temp_data;
		  count_rotation++;
		  ser_data = 0x00;
	  }
	  else if(count_rotation == 2)
	  {
		  rotation_r = (unsigned int)temp_data;
		  temp_rotation = (rotation_q)*255 + (rotation_r);
		  count_rotation = 1;
		  flag_rotation_left_1 = 1;
		  flag_rotation_left = 0;	  
	  }
  }
  
    //__________________________ Servo Motor Commands ________________
	if (flag_servo1 == 1)
  {
	  temp_data = ser_data;
	  flag_servo1_main=1;
	  flag_servo1 = 0;
	  ser_data = 0x00;
  }
  
  if (flag_servo2 == 1)
  {
	  temp_data = ser_data;
	  flag_servo2_main=1;
	  flag_servo2 = 0;
	  ser_data = 0x00;
  }
  
  if (flag_servo3 == 1)
  {
	  temp_data = ser_data;
	  flag_servo3_main=1;
	  flag_servo3 = 0;
	  ser_data = 0x00;
  }
  
  //__________________________ LCD Command ________________
  if (flag_lcd == 1)
  {
	  temp_data = ser_data;
	  if (count_lcd == 1)
	  {
		  lcd_row = temp_data;
		  count_lcd++;
		  ser_data = 0x00;
	  }
	  else if (count_lcd == 2)
	  {
		  lcd_column = temp_data;
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
  
  //________________________ Bar Graph LED Command _____________________
  if (flag_bar_graph_led == 1)
  {
	  temp_data = ser_data;
	  flag_bar_graph_led_main = 1;
	  flag_bar_graph_led = 0;
	  ser_data = 0x00;
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
	flag_velocity = 1;
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

//_________________________________________ Bar Graph LED ___________________________________
if (ser_data == 0x84)
{
	flag_bar_graph_led = 1;
}

}




/*
* Function Name:	main
* Input:			None
* Output:		    On every every interrupt action is performed 
* Logic:			Initializes the devices and have conditions to rotate the bot by some angel, move it forward and backward by 
*                   some distance, rotate the servo motor 1, 2 and 3, print on LCD and glow bar graph LED 
* Example Call:	 
*
*/
int main(void)
{
	init_devices();
	DDRJ = 0xFF;
  

	while(1)
	{
		//Move the bot forward by some distance in millimeter
		if (flag_position_encoder_forward_1 == 1)
		{
			forward_mm(temp_distance);
			flag_position_encoder_forward_1=0;
		}
		
		//Move the bot backward by some distance in millimeter
		if (flag_position_encoder_backward_1 == 1)
		{
			backward_mm(temp_distance);
			flag_position_encoder_backward_1=0;
		}
		
		// Rotate the bot right by specified degrees
		if (flag_rotation_right_1==1)
		{
			right_degrees(temp_rotation);
			flag_rotation_right_1=0;
		}
		
		// Rotate the bot left by specified degrees
		if (flag_rotation_left_1==1)
		{
			left_degrees(temp_rotation);
			flag_rotation_left_1=0;
		}
		
		//Rotate Servo Motor 1
		if (flag_servo1_main==1)
		{
			s1 = (int)temp_data;
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
			s1 = (int)temp_data;
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
			s1 = (int)temp_data;
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
			lcd_clear();
			lcd_cursor(lcd_row, lcd_column);
			lcd_wr_char(temp_data);
			flag_lcd_main = 0;
		}
		
		//Bar Graph Led
		if (flag_bar_graph_led_main == 1)
		{
			PORTJ = temp_data;
			_delay_ms(2000);
			PORTJ = 0x00;
			flag_bar_graph_led_main = 0;
		}
		
		sensor_data_interpretation();
	}

}     



