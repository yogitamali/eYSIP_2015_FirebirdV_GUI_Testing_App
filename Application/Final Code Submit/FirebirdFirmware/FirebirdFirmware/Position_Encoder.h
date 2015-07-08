volatile unsigned long int ShaftCountLeft = 0; //to keep track of left position encoder
volatile unsigned long int ShaftCountRight = 0; //to keep track of right position encoder
volatile unsigned int Degrees; //to accept angle in degrees for turning

/*
*
* Function Name: 	left_encoder_pin_config
* Input: 			None
* Output: 			Data Direction Register of Port E is initialized with value 0xEF.
					PORTx data register of Port L is assigned value 0x10.(Please note that the code only involves changes to the Pin E4 of
					Port E with the help of bitwise AND and OR operators.)
* Logic: 			Giving value 0xEF to Data Direction Registers of Port E initializes the pin PE4 as input and assigning PORTx data register as
					0x10 despite giving the Data Direction Registers as input is known as pull-up.
* Example Call:		left_encoder_pin_config()
*
*/
void left_encoder_pin_config (void)
{
	DDRE  = DDRE & 0xEF;  //Set the direction of the PORTE 4 pin as input
	PORTE = PORTE | 0x10; //Enable internal pull-up for PORTE 4 pin
}

/*
*
* Function Name: 	right_encoder_pin_config
* Input: 			None
* Output: 			Data Direction Register of Port E is initialized with value 0xDF.
					PORTx data register of Port L is assigned value 0x20.(Please note that the code only involves changes to the Pin E5 of
					Port E with the help of bitwise AND and OR operators.)
* Logic: 			Giving value 0xDF to Data Direction Registers of Port E initializes the pin PE5 as input and assigning PORTx data register as
					0x20 despite giving the Data Direction Registers as input is known as pull-up.
* Example Call:		right_encoder_pin_config()
*
*/
void right_encoder_pin_config (void)
{
	DDRE  = DDRE & 0xDF;  //Set the direction of the PORTE 4 pin as input
	PORTE = PORTE | 0x20; //Enable internal pull-up for PORTE 4 pin
}

/*
*
* Function Name: 	encoder_pin_config
* Input: 			None
* Output: 			None
* Logic: 			This function is used to configure left_position_encoder and right_position_encoder together.
* Example Call:		encoder_pin_config()
*
*/
void encoder_pin_config(void)
{
	left_encoder_pin_config(); //left encoder pin config
	right_encoder_pin_config(); //right encoder pin config
}

/*
*
* Function Name: 	left_position_encoder_interrupt_init
* Input: 			None
* Output: 			External Interrupt Control Register B is initialized with value 0x02.
					External Interrupt Mask Register is initialized with value 0x10.
* Logic: 			This function triggers INT4 with falling edge and enable interrupt INT4 for left position encoder.
* Example Call:		left_position_encoder_interrupt_init()
*
*/
void left_position_encoder_interrupt_init (void) //Interrupt 4 enable
{
	cli(); //Clears the global interrupt
	EICRB = EICRB | 0x02; // INT4 is set to trigger with falling edge
	EIMSK = EIMSK | 0x10; // Enable Interrupt INT4 for left position encoder
	sei();   // Enables the global interrupt
}

/*
*
* Function Name: 	right_position_encoder_interrupt_init
* Input: 			None
* Output: 			External Interrupt Control Register B is initialized with value 0x08.
					External Interrupt Mask Register is initialized with value 0x20.
* Logic: 			This function triggers INT5 with falling edge and enable interrupt INT5 for right position encoder.
* Example Call:		right_position_encoder_interrupt_init()
*
*/
void right_position_encoder_interrupt_init (void) //Interrupt 5 enable
{
	cli(); //Clears the global interrupt
	EICRB = EICRB | 0x08; // INT5 is set to trigger with falling edge
	EIMSK = EIMSK | 0x20; // Enable Interrupt INT5 for right position encoder
	sei();   // Enables the global interrupt
}

//ISR for right position encoder
ISR(INT5_vect)
{
	ShaftCountRight++;  //increment right shaft position count
}


//ISR for left position encoder
ISR(INT4_vect)
{
	ShaftCountLeft++;  //increment left shaft position count
}

/*
*
* Function Name: 	angle_rotate
* Input: 			degrees->unsigned int variable that takes the degrees the robot has to turn
* Output: 			None
* Logic: 			This function calculates the shaft count by dividing the degrees by resolution and then rotates the robot that much degrees
					by comparing the right/left_shaft to the req_shaft.
* Example Call:		angle_rotate(45)
*
*/
void angle_rotate(unsigned int Degrees)
{
	float ReqdShaftCount = 0;
	unsigned long int ReqdShaftCountInt = 0;

	ReqdShaftCount = (float) Degrees/ 4.090; // division by resolution to get shaft count
	ReqdShaftCountInt = (unsigned int) ReqdShaftCount;
	ShaftCountRight = 0;
	ShaftCountLeft = 0;

	while (1)
	{
		if((ShaftCountRight >= ReqdShaftCountInt) | (ShaftCountLeft >= ReqdShaftCountInt))
		break;
	}
	stop(); //Stop robot
}



/*
*
* Function Name: 	linear_distance_mm
* Input: 			distance->unsigned int variable that takes the distance to be moved by the robot in mm.
* Output: 			None
* Logic: 			This function calculates the shaft count by dividing the distance by resolution and then moves the robot that much distance
					by comparing the right/left_shaft to the req_shaft. 
* Example Call:		linear_distance_mm(50)
*
*/
void linear_distance_mm(unsigned int DistanceInMM)
{
	float ReqdShaftCount = 0;
	unsigned long int ReqdShaftCountInt = 0;

	ReqdShaftCount = DistanceInMM / 5.338; // division by resolution to get shaft count
	ReqdShaftCountInt = (unsigned long int) ReqdShaftCount;
	
	ShaftCountRight = 0;
	while(1)
	{
		if(ShaftCountRight > ReqdShaftCountInt)
		{
			break;
		}
	}
	stop(); //Stop robot
}







