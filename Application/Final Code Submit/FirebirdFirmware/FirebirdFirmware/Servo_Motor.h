
/*
* Function Name:	servo_pin_config
* Input:			None
* Output:			Configure PORTB pin for servo motor operation
* Logic:			Calls the function servo1_pin_config() and servo2_pin_config() to enable/disable the servo motors.
* Example Call:		servo_pin_config();
*
*/
void servo_pin_config(void)
{
	servo1_pin_config(); //Configure PORTB 5 pin for servo motor 1 operation
	servo2_pin_config(); //Configure PORTB 6 pin for servo motor 2 operation
	servo3_pin_config(); //Configure PORTB 7 pin for servo motor 3 operation
}


/*
* Function Name:	servo1_pin_config
* Input:			None
* Output:			Configure PORTB 5 pin for servo motor 1 operation
* Logic:			PB5 is servo motor 1 enable/disable pin. When the logic is '1' it enables the motor, and when the logic is '0' the motors are disabled.
* Example Call:		servo1_pin_config();
*
*/
void servo1_pin_config (void)	//Configure PORTB 5 pin for servo motor 1 operation
{
 DDRB  = DDRB | 0x20;  //making PORTB 5 pin output
 PORTB = PORTB | 0x20; //setting PORTB 5 pin to logic 1
}

/*
* Function Name:	servo2_pin_config
* Input:			None
* Output:			Configure PORTB 6 pin for servo motor 2 operation
* Logic:			PB6 is servo motor 2 enable/disable pin. When the logic is '1' it enables the motor, and when the logic is '0' the motors are disabled.
* Example Call:		servo2_pin_config();
*
*/
void servo2_pin_config (void)
{
 DDRB  = DDRB | 0x40;  //making PORTB 6 pin output
 PORTB = PORTB | 0x40; //setting PORTB 6 pin to logic 1
}

/*
* Function Name:	servo3_pin_config
* Input:			None
* Output:			Configure PORTB 7 pin for servo motor 3 operation
* Logic:			PB7 is servo motor 3 enable/disable pin. When the logic is '1' it enables the motor, and when the logic is '0' the motors are disabled.
* Example Call:		servo3_pin_config();
*
*/
void servo3_pin_config (void)
{
 DDRB  = DDRB | 0x80;  //making PORTB 7 pin output
 PORTB = PORTB | 0x80; //setting PORTB 7 pin to logic 1
}


/*
* Function Name:	servo_1
* Input:			degrees -> character which stores at which angle the servo motor has to be brought
* Output:			brings the servo motor 1 at specified angle
* Logic:			rotate Servo 1 by a specified angle in the multiples of 1.86 degrees
*					PositionTiltServo = ((float)degrees / 1.86) + 35.0;
* Example Call:		servo_1(45);
*
*/
void servo_1(unsigned char degrees)  
{
 float PositionPanServo = 0;
  PositionPanServo = ((float)degrees / 1.86) + 35.0;
 OCR1AH = 0x00;
 OCR1AL = (unsigned char) PositionPanServo;
}


/*
* Function Name:	servo_2
* Input:			degrees -> character which stores at which angle the servo motor has to be brought
* Output:			brings the servo motor 2 at specified angle
* Logic:			rotate Servo 2 by a specified angle in the multiples of 1.86 degrees
*					PositionTiltServo = ((float)degrees / 1.86) + 35.0;
* Example Call:		servo_2(45);
*
*/
void servo_2(unsigned char degrees)
{
 float PositionTiltServo = 0;
 PositionTiltServo = ((float)degrees / 1.86) + 35.0;
 OCR1BH = 0x00;
 OCR1BL = (unsigned char) PositionTiltServo;
}

/*
* Function Name:	servo_3
* Input:			degrees -> character which stores at which angle the servo motor has to be brought
* Output:			brings the servo motor 3 at specified angle
* Logic:			rotate Servo 3 by a specified angle in the multiples of 1.86 degrees
*					PositionTiltServo = ((float)degrees / 1.86) + 35.0;
* Example Call:		servo_3(45);
*
*/
void servo_3(unsigned char degrees)
{
 float PositionServo = 0;
 PositionServo = ((float)degrees / 1.86) + 35.0;
 OCR1CH = 0x00;
 OCR1CL = (unsigned char) PositionServo;
}

/*
* Function Name:	servo_1_free
* Input:			None
* Output:			makes servo 1 free rotating
* Logic:			servo_1_free function unlocks the servo motor 1 from the any angle
*					and make it free by giving 100% duty cycle at the PWM. This function can be used to
*					reduce the power consumption of the motor if it is holding load against the gravity.
* Example Call:		servo_1_free();
*
*/
void servo_1_free (void) //makes servo 1 free rotating
{
 OCR1AH = 0x03; 
 OCR1AL = 0xFF; //Servo 1 off
}

/*
* Function Name:	servo_2_free
* Input:			None
* Output:			makes servo 2 free rotating
* Logic:			servo_2_free function unlocks the servo motor 2 from the any angle
*					and make it free by giving 100% duty cycle at the PWM. This function can be used to
*					reduce the power consumption of the motor if it is holding load against the gravity.
* Example Call:		servo_2_free();
*
*/
void servo_2_free (void) //makes servo 2 free rotating
{
 OCR1BH = 0x03;
 OCR1BL = 0xFF; //Servo 2 off
}

/*
* Function Name:	servo_3_free
* Input:			None
* Output:			makes servo 3 free rotating
* Logic:			servo_3_free function unlocks the servo motor 3 from the any angle
*					and make it free by giving 100% duty cycle at the PWM. This function can be used to
*					reduce the power consumption of the motor if it is holding load against the gravity.
* Example Call:		servo_3_free();
*
*/
void servo_3_free (void) //makes servo 3 free rotating
{
 OCR1CH = 0x03;
 OCR1CL = 0xFF; //Servo 3 off
} 

/*
* Function Name:	rotateServo_1AntiClockWise
* Input:			Character--> initial angle and the degrees by which it is to be rotated
* Output:			Rotates servo 1 in anti-clockwise direction with a slower speed
* Logic:			rotate Servo 1 from the initial angle to a specified angle in the multiples of 1.86 degrees
*					PositionTiltServo = ((float)degrees / 1.86) + 35.0;
* Example Call:		rotateServo_1AntiClockWise(45,45);
*
*/
void rotateServo_1AntiClockWise(unsigned char initialAngle, unsigned char degrees) /*Rotates servo 1 in anti-clockwise direction with a slower speed, 
																				     Therefore, intialAngle>=degrees*/
{
	float PositionPanServo = 0;
	PositionPanServo = ((float)degrees / 1.86) + 35.0;
	char i;
	for (i=(((float)initialAngle / 1.86) + 35.0);i>=PositionPanServo;i--)
	{
		OCR1AH = 0x00;
		OCR1AL = (unsigned char) i;
		_delay_ms(20);
	}
}


/*
* Function Name:	rotateServo_1ClockWise
* Input:			Character--> initial angle and the degrees by which it is to be rotated
* Output:			Rotates servo 1 in clockwise direction with a slower speed
* Logic:			rotate Servo 1 from the initial angle clockwise to a specified angle in the multiples of 1.86 degrees
*					PositionTiltServo = ((float)degrees / 1.86) + 35.0;
* Example Call:		rotateServo_1ClockWise(45,45);
*
*/
void rotateServo_1ClockWise(unsigned char initialAngle, unsigned char degrees) /*Rotates servo 2 in clockwise direction with a slower speed, 
																				 Therefore, intialAngle<=degrees*/
{
	float PositionPanServo = 0;
	PositionPanServo = ((float)degrees / 1.86) + 35.0;
	char i;
	for (i=(((float)initialAngle / 1.86) + 35.0);i<=PositionPanServo;i++)
	{
		OCR1AH = 0x00;
		OCR1AL = (unsigned char) i;
		_delay_ms(20);
	}
}

/*
* Function Name:	rotateServo_2AntiClockWise
* Input:			Character--> initial angle and the degrees by which it is to be rotated
* Output:			Rotates servo 1 in anti-clockwise direction with a slower speed
* Logic:			rotate Servo 1 from the initial angle to a specified angle in the multiples of 1.86 degrees
*					PositionTiltServo = ((float)degrees / 1.86) + 35.0;
* Example Call:		rotateServo_2AntiClockWise(45,45);
*
*/
void rotateServo_2AntiClockWise(unsigned char initialAngle, unsigned char degrees) /*Rotates servo 1 in anti-clockwise direction with a slower speed, 
																				     Therefore, intialAngle>=degrees*/
{
	float PositionPanServo = 0;
	PositionPanServo = ((float)degrees / 1.86) + 35.0;
	char i;
	for (i=(((float)initialAngle / 1.86) + 35.0);i>=PositionPanServo;i--)
	{
		OCR1BH = 0x00;
		OCR1BL = (unsigned char) i;
		_delay_ms(20);
	}
}

/*
* Function Name:	rotateServo_2ClockWise
* Input:			Character--> initial angle and the degrees by which it is to be rotated
* Output:			Rotates servo 2 in clockwise direction with a slower speed
* Logic:			rotate Servo 2 from the initial angle clockwise to a specified angle in the multiples of 1.86 degrees
*					PositionTiltServo = ((float)degrees / 1.86) + 35.0;
* Example Call:		rotateServo_2ClockWise(45,45);
*
*/
void rotateServo_2ClockWise(unsigned char initialAngle, unsigned char degrees) /*Rotates servo 2 in clockwise direction with a slower speed, 
																				 Therefore, intialAngle<=degrees*/
{
	float PositionPanServo = 0;
	PositionPanServo = ((float)degrees / 1.86) + 35.0;
	char i;
	for (i=(((float)initialAngle / 1.86) + 35.0);i<=PositionPanServo;i++)
	{
		OCR1BH = 0x00;
		OCR1BL = (unsigned char) i;
		_delay_ms(20);
	}
}
