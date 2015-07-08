/*
 * Project Name: GUI Development for Fire Bird V using Java
 * Author List: Apoorva Bhargava
 * Filename: GUIApp1.java
 * Functions: initComponents(), jButtonBuzzerActionPerformed(), jButtonForwardMotionActionPerformed(),jButtonLeftMotionActionPerformed(),
 *            jButtonRightMotionActionPerformed(), jButtonBackwardMotionActionPerformed(), jTextFieldServo1ActionPerformed(),
 *            jButtonStopMotionActionPerformed(), jButtonCOMConnectActionPerformed(), jButtonCOMDisconnectActionPerformed(),
 *            jComboBoxCOMPortsActionPerformed(), getSliderValueLeftMotor(), jButtonServo2ActionPerformed(),
 *            jTextFieldLeftMotorActionPerformed(), getSliderValueRightMotor(), jButtonBarGraphLedActionPerformed(),
 *            jTextFieldRightMotorActionPerformed(), jButtonSetVelocityActionPerformed(), jButtonLCDPrintActionPerformed(),
 *            jButtonResetVelocityActionPerformed(), getSliderValueServo1(), jButtonServo1ActionPerformed(),
 *            getSliderValueServo1(), getSliderValueServo2(javax.swing.event.ChangeEvent), jButtonRightRotationActionPerformed(),
 *            getSliderValueServo3(), jButtonForwardMovementActionPerformed(), jButtonLeftRotationActionPerformed(),
 *            jButtonBackwardMovementActionPerformed(), Sharp_Distance_Sensor_estimation (int), setSerialEventHandler(),
 *            listSerialPorts(), removeGUIComponents(), connectGUIComponents(), serialEvent(SerialPortEvent), connect(String),
 *            setIcon()
 * Global Variables: NEW_LINE_ASCII, statusBuzzer, flag, value, value1, sliderValue,tempvalue, value2, read, spc, count1
 *
 */

package GUIApp1;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import gnu.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amit Bhargava
 */
public class GUIApp1 extends javax.swing.JFrame
{ 
   
  
    final static int NEW_LINE_ASCII = 10;
    // used to set values of labels giving readings of sensors
    int statusBuzzer=0;
    int flag=0;
    int count1=0;
   
    int value, value1, sliderValue,tempvalue;
    float value2;
    
    boolean read = true;
    //boolean suspended = false;
    SerialPortConnection spc;

    /**
     * Creates new form GUIApp1
     */
    public GUIApp1() {
        initComponents();
        spc = new SerialPortConnection();
        jComboBoxCOMPorts.removeAllItems();
        listSerialPorts();
        jTextFieldLeftMotor.setEnabled(false);
        jTextFieldRightMotor.setEnabled(false);
        jTextFieldServo1.setEnabled(false);
        jTextFieldServo2.setEnabled(false);
        jTextFieldServo3.setEnabled(false);
        jTextFieldDistance.setEnabled(false);
        jButtonCOMDisconnect.setEnabled(false);
        jTextFieldRotation.setEnabled(false);
        jButtonResetVelocity.setEnabled(false);
        jButtonSetVelocity.setEnabled(false);
        jTextFieldLCDRow.setEnabled(false);
        jTextFieldLCDColumn.setEnabled(false);
        jTextFieldLCDText.setEnabled(false);
        jTextFieldBarGraphLed.setEnabled(false);
        setIcon();
    }

    /*
     *
     * Function Name: initComponents 
     * Input: void
     * Output: initializes all the GUI components
     * Logic: initializes all the components used in GUI by calling functions setBackground(Color), setBorder(Border), setFont(Font),
     *        setForeground(Color), setText(String) for setting the background color, border, font, foreground, and text respectively.
     *        It also attaches actionListener to the buttons used by calling the function addActionListener(ActionEvent). Also the layout 
     *        of all the panels used in the GUI is set to GroupLayout.
     * Example Call: initComponents()
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanelFrame = new javax.swing.JPanel();  // Create panel for frame
        jPanelCOMPort = new javax.swing.JPanel(); // Create panel for COM port section
        jLabelCOMPortTitle = new javax.swing.JLabel(); // Create Label for COM Port Title in COM port section
        jComboBoxCOMPorts = new javax.swing.JComboBox(); // Create drop down list of the available COM ports  
        jButtonCOMConnect = new javax.swing.JButton(); // Create the Connect button
        jButtonCOMDisconnect = new javax.swing.JButton(); // Create the Disconnect button
        jLabelHeading = new javax.swing.JLabel();  // Create label for the heating of GUI
        jPanelBuzzer = new javax.swing.JPanel(); // Create panel for buzzer section
        jLabelBuzzerTitle = new javax.swing.JLabel(); // Create label for the title of buzzer section
        jButtonBuzzer = new javax.swing.JButton(); // Create button turn on and off the buzzer
        jPanelMotionControl = new javax.swing.JPanel(); // Create panel for motion control section
        jLabelMotionControlTitle = new javax.swing.JLabel(); // Create label for title of motion control section
        jButtonStopMotion = new javax.swing.JButton(); // Create Stop button
        jButtonBackwardMotion = new javax.swing.JButton(); // Create button for Backward motion
        jButtonRightMotion = new javax.swing.JButton(); // Create button for Right motion
        jButtonLeftMotion = new javax.swing.JButton(); // Create button for Left motion
        jButtonForwardMotion = new javax.swing.JButton(); //Create button for Forward motion
        jPanelWLSensor = new javax.swing.JPanel(); // Create panel for white line sensor readings 
        jLabelWLSensorTitle = new javax.swing.JLabel(); // Create label for white line sensor section
        jLabelRightWLSensor = new javax.swing.JLabel(); // Create label to give Right white line sensor reading
        jLabelCenterWLSensor = new javax.swing.JLabel(); // Create label to give Center white line sensor reading
        jLabelLeftWLSensor = new javax.swing.JLabel(); // Create label to give Left white line sensor reading
        jProgressBarLeftWLSensor = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to give left white line sensor reading
        jProgressBarCenterWLSensor = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to give center white line sensor reading
        jProgressBarRightWLSensor = new javax.swing.JProgressBar(JProgressBar.VERTICAL);  // Create progress bar to give right white line sensor reading
        jLabelLeftWLSensorText = new javax.swing.JLabel(); // Create label to show left white line sensor heading
        jLabelCenterWLSensorText = new javax.swing.JLabel(); // Create label to show center white line sensor heading
        jLabelRightWLSensorText = new javax.swing.JLabel(); // Create label to show right white line sensor heading
        jLabel2 = new javax.swing.JLabel(); // Create label having eyantra logo as icon
        jPanelServoMotor = new javax.swing.JPanel(); // Create panel for servo motor section
        jLabelServoMotorTitle = new javax.swing.JLabel(); // Create label for title of servo motor section 
        jSliderServo2 = new javax.swing.JSlider(); // Create Slider for servo motor 2
        jSliderServo3 = new javax.swing.JSlider(); // Create Slider for servo motor 3
        jLabelServoMotor1 = new javax.swing.JLabel(); // Create label for servo motor 1 heading
        jLabelServoMotor2 = new javax.swing.JLabel(); // Create label for servo motor 2 heading
        jLabelServoMotor3 = new javax.swing.JLabel(); // Create label for servo motor 3 heading
        jSliderServo1 = new javax.swing.JSlider(); // Create Slider for servo motor 1
        jTextFieldServo1 = new javax.swing.JTextField(); // Create text field to enter the angel to be rotated by servo motor 1 
        jTextFieldServo2 = new javax.swing.JTextField(); // Create text field to enter the angel to be rotated by servo motor 2
        jTextFieldServo3 = new javax.swing.JTextField(); // Create text field to enter the angel to be rotated by servo motor 3
        jButtonServo1 = new javax.swing.JButton(); // Create button to rotate servo motor 1
        jButtonServo2 = new javax.swing.JButton(); // Create button to rotate servo motor 2
        jButtonServo3 = new javax.swing.JButton(); // Create button to rotate servo motor 3
        jPanelLCD = new javax.swing.JPanel(); // Create panel for Print on LCD section
        jLabelLCDPrintTitle = new javax.swing.JLabel(); // Create label for title of print on LCD section
        jLabelLCDRow = new javax.swing.JLabel(); // Create label for row 
        jLabelLCDColumn = new javax.swing.JLabel(); // Create label for column
        jLabelLCDText = new javax.swing.JLabel(); // Create label for text 
        jTextFieldLCDRow = new javax.swing.JTextField(); // Create text field to enter row number
        jTextFieldLCDColumn = new javax.swing.JTextField(); // Create text field to enter column number
        jTextFieldLCDText = new javax.swing.JTextField(); // Creates text field to enter the text to be printed on LCD
        jButtonLCDPrint = new javax.swing.JButton(); // Create Print button
        jPanelVelocity = new javax.swing.JPanel(); // Create panel for velocity section
        jLabelVelocityTitle = new javax.swing.JLabel(); // Create title for velocity section
        jSliderRightMotor = new javax.swing.JSlider(JSlider.VERTICAL,0,255,0); // Create slider to set the velocity of right motor
        jTextFieldLeftMotor = new javax.swing.JTextField(); // Create text field to manually enter the velocity of left motor
        jTextFieldRightMotor = new javax.swing.JTextField(); // Create text field to manually enter the velocity of right motor
        jButtonSetVelocity = new javax.swing.JButton(); // Create button to set velocities of both the motors
        jSliderLeftMotor = new javax.swing.JSlider(JSlider.VERTICAL,0,255,0); // Create slider to set the velocity of right motor
        jLabelLeftMotor = new javax.swing.JLabel(); // Create label for heading of left motor
        jLabelRightMotor = new javax.swing.JLabel(); // Create label for heading of right motor
        jButtonResetVelocity = new javax.swing.JButton(); // Create the button to reset the velocities
        jPanelBatterVoltage = new javax.swing.JPanel(); // Create panel for Battery Voltage reading
        jLabelBatteryVoltageTitle = new javax.swing.JLabel(); // Create label for title of battery voltage section
        jLabelVoltage = new javax.swing.JLabel(); // Create label for showing batter voltage reading
        jProgressBarBatteryVoltage = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to show battery voltage reading
        jPanelIRSensor = new javax.swing.JPanel(); // Create panel to show all IR Sensors readings
        jLabelIRSensorTitle = new javax.swing.JLabel(); // Create title of IR Sensors reading section
        jLabelIRSensor1 = new javax.swing.JLabel(); // Create label to show IR Sensor 1 reading
        jLabelIRSensor2 = new javax.swing.JLabel(); // Create label to show IR Sensor 2 reading
        jLabelIRSensor3 = new javax.swing.JLabel(); // Create label to show IR Sensor 3 reading
        jLabelIRSensor4 = new javax.swing.JLabel(); // Create label to show IR Sensor 4 reading
        jLabelIRSensor5 = new javax.swing.JLabel(); // Create label to show IR Sensor 5 reading
        jLabelIRSensor6 = new javax.swing.JLabel(); // Create label to show IR Sensor 6 reading
        jLabelIRSensor7 = new javax.swing.JLabel(); // Create label to show IR Sensor 7 reading
        jLabelIRSensor8 = new javax.swing.JLabel(); // Create label to show IR Sensor 8 reading
        jProgressBarIRSensor1 = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to shw IR Sensor 1 reading
        jProgressBarIRSensor2 = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to shw IR Sensor 2 reading
        jProgressBarIRSensor3 = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to shw IR Sensor 3 reading
        jProgressBarIRSensor4 = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to shw IR Sensor 4 reading
        jProgressBarIRSensor5 = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to shw IR Sensor 5 reading
        jProgressBarIRSensor6 = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to shw IR Sensor 6 reading
        jProgressBarIRSensor7 = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to shw IR Sensor 7 reading
        jProgressBarIRSensor8 = new javax.swing.JProgressBar(JProgressBar.VERTICAL); // Create progress bar to shw IR Sensor 8 reading
        jLabelIRSensorNo1 = new javax.swing.JLabel(); // Create label for IR Sensor 1 heading
        jLabelIRSensorNo2 = new javax.swing.JLabel(); // Create label for IR Sensor 2 heading
        jLabelIRSensorNo3 = new javax.swing.JLabel(); // Create label for IR Sensor 3 heading
        jLabelIRSensorNo4 = new javax.swing.JLabel(); // Create label for IR Sensor 4 heading
        jLabelIRSensorNo5 = new javax.swing.JLabel(); // Create label for IR Sensor 5 heading
        jLabelIRSensorNo6 = new javax.swing.JLabel(); // Create label for IR Sensor 6 heading
        jLabelIRSensorNo7 = new javax.swing.JLabel(); // Create label for IR Sensor 7 heading
        jLabelIRSensorNo8 = new javax.swing.JLabel(); // Create label for IR Sensor 8 heading
        jPanelDistanceSensor = new javax.swing.JPanel(); // Create panel for Distance Sensors reading section
        jLabelDistanceSensorTitle = new javax.swing.JLabel(); // Create label for title of Distance Sensors reading section
        // Create labels to show the reading of Distance Sensors 1,2,3,4 and 5
        jLabelSharpSensor3 = new javax.swing.JLabel();
        jLabelSharpSensor1 = new javax.swing.JLabel();
        jLabelSharpSensor2 = new javax.swing.JLabel();
        jLabelSharpSensor4 = new javax.swing.JLabel();
        jLabelSharpSensor5 = new javax.swing.JLabel();
        // Create progress bars to show the reading of Distance Sensors 1,2,3,4 and 5
        jProgressBarSharpSensor1 = new javax.swing.JProgressBar(JProgressBar.VERTICAL);
        jProgressBarSharpSensor2 = new javax.swing.JProgressBar(JProgressBar.VERTICAL);
        jProgressBarSharpSensor3 = new javax.swing.JProgressBar(JProgressBar.VERTICAL);
        jProgressBarSharpSensor4 = new javax.swing.JProgressBar(JProgressBar.VERTICAL);
        jProgressBarSharpSensor5 = new javax.swing.JProgressBar(JProgressBar.VERTICAL);
        // Create labels for heading of Distance Sensors 1,2,3,4 and 5
        jLabelDistanceSensor1 = new javax.swing.JLabel();
        jLabelDistanceSensor2 = new javax.swing.JLabel();
        jLabelDistanceSensor3 = new javax.swing.JLabel();
        jLabelDistanceSensor4 = new javax.swing.JLabel();
        jLabelDistanceSensor5 = new javax.swing.JLabel();
        jPanelMovementRotation = new javax.swing.JPanel(); // Create panel for Movement an Rotation section
        jLabelMovementRotationTitle = new javax.swing.JLabel(); // Create label for title of Movement and Rotation section
        jLabelRotation = new javax.swing.JLabel(); // Create label to give angel of rotation
        jTextFieldRotation = new javax.swing.JTextField(); // Create text field to give the angel of rotation
        jButtonForwardMovement = new javax.swing.JButton(); // Create button to move bot forward by specified distance 
        jLabelDistance = new javax.swing.JLabel();  // Create label to enter the distace for the forward and backward movement
        jTextFieldDistance = new javax.swing.JTextField();  // Create text field to enter the distance
        jButtonBackwardMovement = new javax.swing.JButton(); // Create button to move bot backward by specified distance
        jButtonRightRotation = new javax.swing.JButton(); // Create button to rotate bot right by specified angel
        jButtonLeftRotation = new javax.swing.JButton(); // Create button to rotate bot left by specified angel 
        jPanelBarLED = new javax.swing.JPanel(); // Create panel for Bar Graph LED section
        jLabelBarGraphLEDTitle = new javax.swing.JLabel(); // Create label for title of Bar Graph LED section
        jLabel37 = new javax.swing.JLabel(); // Create label to enter the LED number
        jTextFieldBarGraphLed = new javax.swing.JTextField(); // Create text field to enter the LED number to glow
        jButtonBarGraphLed = new javax.swing.JButton(); // Create button to glow LED
        
        // Set the exit on clicking the close and set the title of JFrame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FIRE BIRD V");
        
        // Set the background of frame jPanelFrame 
        jPanelFrame.setBackground(new java.awt.Color(204, 204, 255));
        
        // Set the background and border of COM port section
        jPanelCOMPort.setBackground(new java.awt.Color(153, 204, 255));
        jPanelCOMPort.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        // Set font, foreground and text of jLabelCOMPortTitle 
        jLabelCOMPortTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCOMPortTitle.setForeground(new java.awt.Color(0, 0, 102));
        jLabelCOMPortTitle.setText("   COM PORT");
        
        // Set text and assign action listener to the jButtonCOMConnect
        jButtonCOMConnect.setText("Connect");
        jButtonCOMConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCOMConnectActionPerformed();
            }
        });
        
        // Set text and assign action listener to the jButtonCOMDisconnect 
        jButtonCOMDisconnect.setText("Disconnect");
        jButtonCOMDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCOMDisconnectActionPerformed();
            }
        });
        
        javax.swing.GroupLayout jPanelCOMPortLayout = new javax.swing.GroupLayout(jPanelCOMPort);
        jPanelCOMPort.setLayout(jPanelCOMPortLayout);
        jPanelCOMPortLayout.setHorizontalGroup(
            jPanelCOMPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCOMPortLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxCOMPorts, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCOMPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCOMDisconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCOMConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelCOMPortLayout.createSequentialGroup()
                .addComponent(jLabelCOMPortTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelCOMPortLayout.setVerticalGroup(
            jPanelCOMPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCOMPortLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabelCOMPortTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCOMPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCOMPorts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCOMConnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCOMDisconnect)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        
        // Set font, foreground and text of jLabelHeading
        jLabelHeading.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelHeading.setForeground(new java.awt.Color(255, 0, 0));
        jLabelHeading.setText("FIREBIRD V ATMEGA 2560");
        
        // Set background and border of jPanelBuzzer 
        jPanelBuzzer.setBackground(new java.awt.Color(153, 204, 255));
        jPanelBuzzer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        // Set font, foreground and text of jLabelBuzzerTitle
        jLabelBuzzerTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelBuzzerTitle.setForeground(new java.awt.Color(0, 0, 102));
        jLabelBuzzerTitle.setText("  BUZZER");

        jButtonBuzzer.setText("On");
        jButtonBuzzer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuzzerActionPerformed();
            }
        });

        javax.swing.GroupLayout jPanelBuzzerLayout = new javax.swing.GroupLayout(jPanelBuzzer);
        jPanelBuzzer.setLayout(jPanelBuzzerLayout);
        jPanelBuzzerLayout.setHorizontalGroup(
            jPanelBuzzerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuzzerLayout.createSequentialGroup()
                .addComponent(jLabelBuzzerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBuzzerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonBuzzer, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBuzzerLayout.setVerticalGroup(
            jPanelBuzzerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBuzzerLayout.createSequentialGroup()
                .addComponent(jLabelBuzzerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonBuzzer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelMotionControl.setBackground(new java.awt.Color(153, 204, 255));
        jPanelMotionControl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelMotionControlTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelMotionControlTitle.setForeground(new java.awt.Color(0, 0, 102));
        jLabelMotionControlTitle.setText("MOTION CONTROL");

        jButtonStopMotion.setText("Stop");
        jButtonStopMotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopMotionActionPerformed();
            }
        });

        jButtonBackwardMotion.setText("Backward");
        jButtonBackwardMotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackwardMotionActionPerformed();
            }
        });

        jButtonRightMotion.setText("Right");
        jButtonRightMotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRightMotionActionPerformed();
            }
        });

        jButtonLeftMotion.setText("Left");
        jButtonLeftMotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLeftMotionActionPerformed();
            }
        });

        jButtonForwardMotion.setText("Forward");
        jButtonForwardMotion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonForwardMotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonForwardMotionActionPerformed();
            }
        });

        javax.swing.GroupLayout jPanelMotionControlLayout = new javax.swing.GroupLayout(jPanelMotionControl);
        jPanelMotionControl.setLayout(jPanelMotionControlLayout);
        jPanelMotionControlLayout.setHorizontalGroup(
            jPanelMotionControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMotionControlLayout.createSequentialGroup()
                .addGroup(jPanelMotionControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMotionControlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonLeftMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelMotionControlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMotionControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonForwardMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMotionControlLayout.createSequentialGroup()
                        .addGroup(jPanelMotionControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonStopMotion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonBackwardMotion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRightMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );
        jPanelMotionControlLayout.setVerticalGroup(
            jPanelMotionControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMotionControlLayout.createSequentialGroup()
                .addComponent(jLabelMotionControlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jButtonForwardMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanelMotionControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLeftMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonStopMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRightMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonBackwardMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jPanelWLSensor.setBackground(new java.awt.Color(153, 204, 255));
        jPanelWLSensor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelWLSensorTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelWLSensorTitle.setForeground(new java.awt.Color(0, 0, 153));
        jLabelWLSensorTitle.setText("WHITE LINE SENSORS");

        jLabelRightWLSensor.setBackground(new java.awt.Color(255, 255, 255));

        jProgressBarLeftWLSensor.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarLeftWLSensor.setMaximum(300);
        jProgressBarLeftWLSensor.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarCenterWLSensor.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarCenterWLSensor.setMaximum(300);
        jProgressBarCenterWLSensor.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarRightWLSensor.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarRightWLSensor.setMaximum(300);
        jProgressBarRightWLSensor.setPreferredSize(new java.awt.Dimension(26, 200));

        jLabelLeftWLSensorText.setText("Left");

        jLabelCenterWLSensorText.setText("Center");

        jLabelRightWLSensorText.setText("Right");

        javax.swing.GroupLayout jPanelWLSensorLayout = new javax.swing.GroupLayout(jPanelWLSensor);
        jPanelWLSensor.setLayout(jPanelWLSensorLayout);
        jPanelWLSensorLayout.setHorizontalGroup(
            jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWLSensorLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelWLSensorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelWLSensorLayout.createSequentialGroup()
                        .addGroup(jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBarLeftWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLeftWLSensorText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLeftWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCenterWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBarCenterWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCenterWLSensorText, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBarRightWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRightWLSensorText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRightWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanelWLSensorLayout.setVerticalGroup(
            jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWLSensorLayout.createSequentialGroup()
                .addComponent(jLabelWLSensorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRightWLSensorText)
                    .addComponent(jLabelCenterWLSensorText)
                    .addComponent(jLabelLeftWLSensorText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelWLSensorLayout.createSequentialGroup()
                        .addGroup(jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBarRightWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBarCenterWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelWLSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCenterWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRightWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelWLSensorLayout.createSequentialGroup()
                        .addComponent(jProgressBarLeftWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLeftWLSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUIApp1/logo1.png"))); // NOI18N

        jPanelServoMotor.setBackground(new java.awt.Color(153, 204, 255));
        jPanelServoMotor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelServoMotorTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelServoMotorTitle.setForeground(new java.awt.Color(0, 0, 102));
        jLabelServoMotorTitle.setText("SERVO MOTOR");

        jSliderServo2.setMajorTickSpacing(30);
        jSliderServo2.setMaximum(180);
        jSliderServo2.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderServo2.setPaintLabels(true);
        jSliderServo2.setPaintTicks(true);
        jSliderServo2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                getSliderValueServo2();
            }
        });

        jSliderServo3.setMajorTickSpacing(30);
        jSliderServo3.setMaximum(180);
        jSliderServo3.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderServo3.setPaintLabels(true);
        jSliderServo3.setPaintTicks(true);
        jSliderServo3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                getSliderValueServo3();
            }
        });

        jLabelServoMotor1.setText("Motor1");

        jLabelServoMotor2.setText("Motor2");

        jLabelServoMotor3.setText("Motor3");

        jSliderServo1.setMajorTickSpacing(30);
        jSliderServo1.setMaximum(180);
        jSliderServo1.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderServo1.setPaintLabels(true);
        jSliderServo1.setPaintTicks(true);
        jSliderServo1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                getSliderValueServo1();
            }
        });

        jTextFieldServo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldServo1ActionPerformed();
            }
        });
        
        jTextFieldServo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldServo2ActionPerformed();
            }
        });
        
        jTextFieldServo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldServo3ActionPerformed();
            }
        });

        jButtonServo1.setText("Rotate");
        jButtonServo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonServo1ActionPerformed();
            }
        });

        jButtonServo2.setText("Rotate");
        jButtonServo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonServo2ActionPerformed();
            }
        });

        jButtonServo3.setText("Rotate");
        jButtonServo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonServo3ActionPerformed();
            }
        });

        javax.swing.GroupLayout jPanelServoMotorLayout = new javax.swing.GroupLayout(jPanelServoMotor);
        jPanelServoMotor.setLayout(jPanelServoMotorLayout);
        jPanelServoMotorLayout.setHorizontalGroup(
            jPanelServoMotorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServoMotorLayout.createSequentialGroup()
                .addGroup(jPanelServoMotorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelServoMotorLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelServoMotorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelServoMotorLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelServoMotor1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabelServoMotor2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabelServoMotor3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelServoMotorLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jSliderServo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jSliderServo2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jSliderServo3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelServoMotorLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jTextFieldServo1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jTextFieldServo2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jTextFieldServo3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelServoMotorLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jButtonServo1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonServo2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonServo3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelServoMotorLayout.setVerticalGroup(
            jPanelServoMotorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServoMotorLayout.createSequentialGroup()
                .addComponent(jLabelServoMotorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanelServoMotorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelServoMotor1)
                    .addComponent(jLabelServoMotor2)
                    .addComponent(jLabelServoMotor3))
                .addGap(6, 6, 6)
                .addGroup(jPanelServoMotorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderServo1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSliderServo2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSliderServo3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanelServoMotorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldServo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldServo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldServo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanelServoMotorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonServo1)
                    .addComponent(jButtonServo2)
                    .addComponent(jButtonServo3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelLCD.setBackground(new java.awt.Color(153, 204, 255));
        jPanelLCD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelLCDPrintTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelLCDPrintTitle.setForeground(new java.awt.Color(0, 0, 153));
        jLabelLCDPrintTitle.setText("PRINT ON LCD");

        jLabelLCDRow.setText("Row");

        jLabelLCDColumn.setText("Column");

        jLabelLCDText.setText("Text(one character)");

        jButtonLCDPrint.setText("Print");
        jButtonLCDPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLCDPrintActionPerformed();
            }
        });

        javax.swing.GroupLayout jPanelLCDLayout = new javax.swing.GroupLayout(jPanelLCD);
        jPanelLCD.setLayout(jPanelLCDLayout);
        jPanelLCDLayout.setHorizontalGroup(
            jPanelLCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLCDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLCDLayout.createSequentialGroup()
                        .addComponent(jLabelLCDPrintTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelLCDLayout.createSequentialGroup()
                        .addGroup(jPanelLCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelLCDRow, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLCDColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLCDText))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelLCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLCDRow)
                            .addComponent(jTextFieldLCDColumn)
                            .addComponent(jTextFieldLCDText))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLCDLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonLCDPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanelLCDLayout.setVerticalGroup(
            jPanelLCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLCDLayout.createSequentialGroup()
                .addComponent(jLabelLCDPrintTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelLCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLCDRow, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLCDRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLCDColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLCDColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLCDText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLCDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonLCDPrint)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelVelocity.setBackground(new java.awt.Color(153, 204, 255));
        jPanelVelocity.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelVelocityTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelVelocityTitle.setForeground(new java.awt.Color(0, 0, 102));
        jLabelVelocityTitle.setText(" VELOCITY OF MOTORS");

        jSliderRightMotor.setMajorTickSpacing(51);
        jSliderRightMotor.setMaximum(255);
        jSliderRightMotor.setMinorTickSpacing(3);
        jSliderRightMotor.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderRightMotor.setPaintLabels(true);
        jSliderRightMotor.setPaintTicks(true);
        jSliderRightMotor.setValue(0);
        jSliderRightMotor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                getSliderValueRightMotor();
            }
        });

        jTextFieldLeftMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLeftMotorActionPerformed();
            }
        });

        jTextFieldRightMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRightMotorActionPerformed();
            }
        });

        jButtonSetVelocity.setText("SET");
        jButtonSetVelocity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetVelocityActionPerformed();
            }
        });

        jSliderLeftMotor.setMajorTickSpacing(51);
        jSliderLeftMotor.setMaximum(255);
        jSliderLeftMotor.setMinorTickSpacing(3);
        jSliderLeftMotor.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderLeftMotor.setPaintLabels(true);
        jSliderLeftMotor.setPaintTicks(true);
        jSliderLeftMotor.setValue(0);
        jSliderLeftMotor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                getSliderValueLeftMotor();
            }
        });

        jLabelLeftMotor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelLeftMotor.setForeground(new java.awt.Color(0, 0, 153));
        jLabelLeftMotor.setText("Left Motor");

        jLabelRightMotor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelRightMotor.setForeground(new java.awt.Color(0, 0, 153));
        jLabelRightMotor.setText("Right Motor");

        jButtonResetVelocity.setText("RESET");
        jButtonResetVelocity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetVelocityActionPerformed();
            }
        });

        javax.swing.GroupLayout jPanelVelocityLayout = new javax.swing.GroupLayout(jPanelVelocity);
        jPanelVelocity.setLayout(jPanelVelocityLayout);
        jPanelVelocityLayout.setHorizontalGroup(
            jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVelocityLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVelocityLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSliderLeftMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLeftMotor)))
                    .addComponent(jTextFieldLeftMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVelocityTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelVelocityLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSetVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonResetVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVelocityLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSliderRightMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRightMotor)))
                    .addComponent(jTextFieldRightMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanelVelocityLayout.setVerticalGroup(
            jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVelocityLayout.createSequentialGroup()
                .addComponent(jLabelVelocityTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(jButtonSetVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonResetVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelVelocityLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVelocityLayout.createSequentialGroup()
                        .addGroup(jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelVelocityLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jSliderLeftMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelLeftMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldLeftMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVelocityLayout.createSequentialGroup()
                        .addGroup(jPanelVelocityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelVelocityLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jSliderRightMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelRightMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldRightMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanelBatterVoltage.setBackground(new java.awt.Color(153, 204, 255));
        jPanelBatterVoltage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelBatteryVoltageTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelBatteryVoltageTitle.setForeground(new java.awt.Color(0, 0, 153));
        jLabelBatteryVoltageTitle.setText("VOLTAGE");

        jProgressBarBatteryVoltage.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarBatteryVoltage.setMaximum(12);
        jProgressBarBatteryVoltage.setPreferredSize(new java.awt.Dimension(26, 200));

        javax.swing.GroupLayout jPanelBatterVoltageLayout = new javax.swing.GroupLayout(jPanelBatterVoltage);
        jPanelBatterVoltage.setLayout(jPanelBatterVoltageLayout);
        jPanelBatterVoltageLayout.setHorizontalGroup(
            jPanelBatterVoltageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelBatteryVoltageTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(jLabelVoltage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelBatterVoltageLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jProgressBarBatteryVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBatterVoltageLayout.setVerticalGroup(
            jPanelBatterVoltageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBatterVoltageLayout.createSequentialGroup()
                .addComponent(jLabelBatteryVoltageTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jProgressBarBatteryVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelIRSensor.setBackground(new java.awt.Color(153, 204, 255));
        jPanelIRSensor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelIRSensorTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelIRSensorTitle.setForeground(new java.awt.Color(0, 0, 153));
        jLabelIRSensorTitle.setText("   IR SENSORS READINGS");

        jProgressBarIRSensor1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarIRSensor1.setMaximum(256);
        jProgressBarIRSensor1.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarIRSensor2.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarIRSensor2.setMaximum(256);
        jProgressBarIRSensor2.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarIRSensor3.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarIRSensor3.setMaximum(256);
        jProgressBarIRSensor3.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarIRSensor4.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarIRSensor4.setMaximum(256);
        jProgressBarIRSensor4.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarIRSensor5.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarIRSensor5.setMaximum(256);
        jProgressBarIRSensor5.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarIRSensor6.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarIRSensor6.setMaximum(256);
        jProgressBarIRSensor6.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarIRSensor7.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarIRSensor7.setMaximum(256);
        jProgressBarIRSensor7.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarIRSensor8.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarIRSensor8.setMaximum(256);
        jProgressBarIRSensor8.setPreferredSize(new java.awt.Dimension(26, 200));

        jLabelIRSensorNo1.setText("1");

        jLabelIRSensorNo2.setText("2");

        jLabelIRSensorNo3.setText("3");

        jLabelIRSensorNo4.setText("4");

        jLabelIRSensorNo5.setText("5");

        jLabelIRSensorNo6.setText("6");

        jLabelIRSensorNo7.setText("7");

        jLabelIRSensorNo8.setText("8");

        javax.swing.GroupLayout jPanelIRSensorLayout = new javax.swing.GroupLayout(jPanelIRSensor);
        jPanelIRSensor.setLayout(jPanelIRSensorLayout);
        jPanelIRSensorLayout.setHorizontalGroup(
            jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIRSensorLayout.createSequentialGroup()
                .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIRSensorLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelIRSensorLayout.createSequentialGroup()
                                .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelIRSensorNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jProgressBarIRSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(jProgressBarIRSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelIRSensorNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelIRSensorLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabelIRSensorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelIRSensorLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jProgressBarIRSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelIRSensorNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jProgressBarIRSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelIRSensorNo4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelIRSensorLayout.createSequentialGroup()
                                .addGap(152, 152, 152)
                                .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelIRSensorLayout.createSequentialGroup()
                                        .addComponent(jLabelIRSensorNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabelIRSensorNo6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelIRSensorLayout.createSequentialGroup()
                                        .addComponent(jProgressBarIRSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(jProgressBarIRSensor6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelIRSensorNo7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBarIRSensor7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBarIRSensor8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIRSensorNo8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelIRSensorLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabelIRSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabelIRSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabelIRSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabelIRSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelIRSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelIRSensor6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelIRSensor7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelIRSensor8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelIRSensorLayout.setVerticalGroup(
            jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIRSensorLayout.createSequentialGroup()
                .addComponent(jLabelIRSensorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelIRSensorNo4)
                        .addComponent(jLabelIRSensorNo3)
                        .addComponent(jLabelIRSensorNo2)
                        .addComponent(jLabelIRSensorNo1)
                        .addComponent(jLabelIRSensorNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelIRSensorNo6)
                        .addComponent(jLabelIRSensorNo7)
                        .addComponent(jLabelIRSensorNo8)))
                .addGap(18, 18, 18)
                .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jProgressBarIRSensor6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jProgressBarIRSensor7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jProgressBarIRSensor5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jProgressBarIRSensor8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jProgressBarIRSensor4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jProgressBarIRSensor3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jProgressBarIRSensor2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jProgressBarIRSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelIRSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIRSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIRSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIRSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIRSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIRSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIRSensor6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIRSensor7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIRSensor8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelDistanceSensor.setBackground(new java.awt.Color(153, 204, 255));
        jPanelDistanceSensor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelDistanceSensorTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelDistanceSensorTitle.setForeground(new java.awt.Color(0, 0, 153));
        jLabelDistanceSensorTitle.setText("DISTANCE SENSORS");

        jProgressBarSharpSensor1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarSharpSensor1.setMaximum(800);
        jProgressBarSharpSensor1.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarSharpSensor2.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarSharpSensor2.setMaximum(800);
        jProgressBarSharpSensor2.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarSharpSensor3.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarSharpSensor3.setMaximum(800);
        jProgressBarSharpSensor3.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarSharpSensor4.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarSharpSensor4.setMaximum(800);
        jProgressBarSharpSensor4.setPreferredSize(new java.awt.Dimension(26, 200));

        jProgressBarSharpSensor5.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBarSharpSensor5.setMaximum(800);
        jProgressBarSharpSensor5.setPreferredSize(new java.awt.Dimension(26, 200));

        jLabelDistanceSensor1.setText("1");

        jLabelDistanceSensor2.setText("2");

        jLabelDistanceSensor3.setText("3");

        jLabelDistanceSensor4.setText("4");

        jLabelDistanceSensor5.setText("5");

        javax.swing.GroupLayout jPanelDistanceSensorLayout = new javax.swing.GroupLayout(jPanelDistanceSensor);
        jPanelDistanceSensor.setLayout(jPanelDistanceSensorLayout);
        jPanelDistanceSensorLayout.setHorizontalGroup(
            jPanelDistanceSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                .addGroup(jPanelDistanceSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                        .addGroup(jPanelDistanceSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabelDistanceSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(jLabelDistanceSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(70, 70, 70)
                        .addComponent(jLabelDistanceSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabelDistanceSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabelDistanceSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jProgressBarSharpSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jProgressBarSharpSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jProgressBarSharpSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jProgressBarSharpSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jProgressBarSharpSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelSharpSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabelSharpSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabelSharpSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelSharpSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelSharpSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabelDistanceSensorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDistanceSensorLayout.setVerticalGroup(
            jPanelDistanceSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                .addComponent(jLabelDistanceSensorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDistanceSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDistanceSensor1)
                    .addComponent(jLabelDistanceSensor2)
                    .addComponent(jLabelDistanceSensor3)
                    .addComponent(jLabelDistanceSensor4)
                    .addComponent(jLabelDistanceSensor5))
                .addGap(6, 6, 6)
                .addGroup(jPanelDistanceSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBarSharpSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanelDistanceSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBarSharpSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBarSharpSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBarSharpSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBarSharpSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanelDistanceSensorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSharpSensor1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDistanceSensorLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelSharpSensor2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelSharpSensor3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSharpSensor4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSharpSensor5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelMovementRotation.setBackground(new java.awt.Color(153, 204, 255));
        jPanelMovementRotation.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelMovementRotationTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelMovementRotationTitle.setForeground(new java.awt.Color(0, 0, 153));
        jLabelMovementRotationTitle.setText("MOVEMENT AND ROTATION");

        jLabelRotation.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelRotation.setText("Angle Of Rotation");

        jButtonForwardMovement.setText("Forward");
        jButtonForwardMovement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonForwardMovementActionPerformed();
            }
        });

        jLabelDistance.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelDistance.setText("Distance(in mm)");

        jButtonBackwardMovement.setText("Backward");
        jButtonBackwardMovement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackwardMovementActionPerformed();
            }
        });

        jButtonRightRotation.setText("Rotate Right");
        jButtonRightRotation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRightRotationActionPerformed();
            }
        });

        jButtonLeftRotation.setText("Rotate Left");
        jButtonLeftRotation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLeftRotationActionPerformed();
            }
        });

        javax.swing.GroupLayout jPanelMovementRotationLayout = new javax.swing.GroupLayout(jPanelMovementRotation);
        jPanelMovementRotation.setLayout(jPanelMovementRotationLayout);
        jPanelMovementRotationLayout.setHorizontalGroup(
            jPanelMovementRotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMovementRotationLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabelMovementRotationTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelMovementRotationLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabelRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTextFieldRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelMovementRotationLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jButtonRightRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonLeftRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelMovementRotationLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabelDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jTextFieldDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelMovementRotationLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jButtonForwardMovement, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelMovementRotationLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jButtonBackwardMovement, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelMovementRotationLayout.setVerticalGroup(
            jPanelMovementRotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMovementRotationLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabelMovementRotationTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanelMovementRotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanelMovementRotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRightRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLeftRotation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelMovementRotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jButtonForwardMovement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButtonBackwardMovement, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelBarLED.setBackground(new java.awt.Color(153, 204, 255));
        jPanelBarLED.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelBarGraphLEDTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelBarGraphLEDTitle.setForeground(new java.awt.Color(0, 0, 153));
        jLabelBarGraphLEDTitle.setText("BAR GRAPH LED");

        jLabel37.setText("LED Number(1-8)");

        jButtonBarGraphLed.setText("Glow");
        jButtonBarGraphLed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarGraphLedActionPerformed();
            }
        });

        javax.swing.GroupLayout jPanelBarLEDLayout = new javax.swing.GroupLayout(jPanelBarLED);
        jPanelBarLED.setLayout(jPanelBarLEDLayout);
        jPanelBarLEDLayout.setHorizontalGroup(
            jPanelBarLEDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBarLEDLayout.createSequentialGroup()
                .addGroup(jPanelBarLEDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBarLEDLayout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldBarGraphLed))
                    .addGroup(jPanelBarLEDLayout.createSequentialGroup()
                        .addGroup(jPanelBarLEDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBarLEDLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jButtonBarGraphLed, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelBarLEDLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabelBarGraphLEDTitle)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelBarLEDLayout.setVerticalGroup(
            jPanelBarLEDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBarLEDLayout.createSequentialGroup()
                .addComponent(jLabelBarGraphLEDTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBarLEDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jTextFieldBarGraphLed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonBarGraphLed)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelFrameLayout = new javax.swing.GroupLayout(jPanelFrame);
        jPanelFrame.setLayout(jPanelFrameLayout);
        jPanelFrameLayout.setHorizontalGroup(
            jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFrameLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(420, 420, 420))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFrameLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFrameLayout.createSequentialGroup()
                        .addGroup(jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFrameLayout.createSequentialGroup()
                                .addComponent(jPanelCOMPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanelBuzzer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanelMotionControl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelServoMotor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelFrameLayout.createSequentialGroup()
                        .addComponent(jPanelVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelMovementRotation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelWLSensor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFrameLayout.createSequentialGroup()
                        .addGroup(jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelLCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelBarLED, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelBatterVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelDistanceSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelIRSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanelFrameLayout.setVerticalGroup(
            jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFrameLayout.createSequentialGroup()
                .addGroup(jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelFrameLayout.createSequentialGroup()
                        .addGroup(jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelCOMPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelBuzzer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelMotionControl, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelFrameLayout.createSequentialGroup()
                        .addComponent(jPanelLCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelBarLED, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelServoMotor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBatterVoltage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelIRSensor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanelFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelMovementRotation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelVelocity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDistanceSensor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanelWLSensor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /*
     *
     * Function Name: jButtonBuzzerActionPerformed
     * Input: None
     * Output: If the buzzer is OFf then it turns On the buzzer and vice versa.
     * Logic: Checks the current status of  the buzzer stored in the variable statusBuzzer. If statusBuzzer is 0 then it writes 
     *        7 on the terminal to turn ON the buzzer and if statusBuzzer is 1 then it writes 9 on the terminal to turn OFF the
     *        buzzer and correspondingly updates the statusBuzzer.
     * Example Call: jButtonBuzzerActionPerformed()
     *
     */
    private void jButtonBuzzerActionPerformed() {
        //Checks if the buzzer is OFF and correspondingly turns it ON
        if(statusBuzzer==0){
            spc.writeOnTerminal("7");
            statusBuzzer=1;
            jButtonBuzzer.setText("Off");
        }
        //Checks if the buzzer is ON and correspondingly turns it OFF
        else if(statusBuzzer==1){
            spc.writeOnTerminal("9");
            statusBuzzer=0;
            jButtonBuzzer.setText("On");
        }
    }

    /*
     *
     * Function Name: jButtonForwardMotionActionPerformed
     * Input: None
     * Output: Makes the bot move forward
     * Logic: writes "8" to the outputstream which makes the bot move forward.
     * Example Call: jButtonForwardMotionActionPerformed()
     *
     */    
    private void jButtonForwardMotionActionPerformed() {
        spc.writeOnTerminal("8");
    }

    /*
     *
     * Function Name: jButtonRightMotionActionPerformed
     * Input: None
     * Output: Makes the bot move right
     * Logic: writes "4" to the outputstream which makes the bot move right
     * Example Call: jButtonRightMotionActionPerformed()
     *
     */    
    private void jButtonRightMotionActionPerformed() {
        spc.writeOnTerminal("4");
    }

    /*
     *
     * Function Name: jButtonBackwardMotionActionPerformed
     * Input: None
     * Output: Makes the bot move backward
     * Logic: writes "2" to the outputstream which makes the bot move backward 
     * Example Call: jButtonBackwardMotionActionPerformed()
     *
     */
    private void jButtonBackwardMotionActionPerformed() {
       spc.writeOnTerminal("2");
    }

    /*
     *
     * Function Name: jButtonLeftMotionActionPerformed
     * Input: None
     * Output: Makes the bot move left
     * Logic: writes "6" to the outputstream which makes the bot move left  
     * Example Call: jButtonLeftMotionActionPerformed()
     *
     */
    private void jButtonLeftMotionActionPerformed(){
       spc.writeOnTerminal("6");
     }    

    /*
     *
     * Function Name: jButtonStopMotionActionPerformed
     * Input: None
     * Output: Stops the bot
     * Logic: writes "5" to the outputstream which makes the bot stop  
     * Example Call: jButtonStopMotionActionPerformed()
     *
     */
    private void jButtonStopMotionActionPerformed() {        
        spc.writeOnTerminal("5");
    }

    /*
     *
     * Function Name: jButtonCOMConnectActionPerformed
     * Input: None
     * Output: connects the GUI with the selected COM port
     * Logic: gets the selected COM port from the jComboBoxCOMPorts by using getSelectedItem() function and calls the connect 
     *        function and passes the selected port to make connection.
     * Example Call: jButtonCOMConnectActionPerformed()
     *
     */
    private void jButtonCOMConnectActionPerformed() {
        String selectedPort = (String)jComboBoxCOMPorts.getSelectedItem();
        connect(selectedPort);
    }

    /*
     *
     * Function Name: jButtonCOMDisconnectActionPerformed
     * Input: None
     * Output: disconnects the GUI from the selected COM port, disables all the GUI components and enables the connect button 
     * Logic: calls removeGUIComponenets method to disable all the enabled GUI components, calls removeSerialPorts method to close the
     *        output and input stream and closes the serial port. It also enables the connect button by calling setEnabled function
     * Example Call: jButtonCOMDisconnectActionPerformed()
     *
     */
    private void jButtonCOMDisconnectActionPerformed() {

        removeGUIComponents();
        spc.removeSerialPorts();
        jButtonCOMDisconnect.setEnabled(false);
        jButtonCOMConnect.setEnabled(true);
    }

    /*
     *
     * Function Name: getSliderValueLeftMotor
     * Input: None
     * Output: sets the text of the label displaying the left motor velocity equals to the slider value
     * Logic: gets the changed value of slider by calling the getValue function and then sets the value of the label by calling
     *        setText function. 
     * Example Call: getSliderValueLeftMotor()
     *
     */
    private void getSliderValueLeftMotor() {
        //gets the changed slider value and stores it in variable sliderValueLeftMotor
        int sliderValueLeftMotor = jSliderLeftMotor.getValue();
        jTextFieldLeftMotor.setText(String.valueOf(sliderValueLeftMotor));
    }

    /*
     *
     * Function Name: jTextFieldLeftMotorActionPerformed
     * Input: None
     * Output: sets the jSliderLeftMotor value accordind to the value entered in the left motor's velocity text field 
     * Logic: gets the value entered in the jTextFieldLeftMotor by calling getText method and then sets the slider value 
     *        by calling setValue method on jSliderLeftMotor
     * Example Call: jTextFieldLeftMotorActionPerformed()
     *
     */
    private void jTextFieldLeftMotorActionPerformed() {
        // gets the value entered in the jTextFieldLeftmotor and stores it in variable sliderInputLeftMotor
        String SliderInputLeftMotor = jTextFieldLeftMotor.getText();
        jSliderLeftMotor.setValue(Integer.parseInt(SliderInputLeftMotor));
    }

    /*
     *
     * Function Name: getSliderValueRightMotor
     * Input: None
     * Output: sets the text of the label displaying the right motor velocity equals to the slider value
     * Logic: gets the changed value of slider by calling the getValue function and then sets the value of the label by calling
     *        setText function. 
     * Example Call: getSliderValueRightMotor()
     *
     */
    private void getSliderValueRightMotor() {
        //gets the changed slider value and stores it in variable sliderValueRightMotor
        int sliderValueRightMotor = jSliderRightMotor.getValue();
        jTextFieldRightMotor.setText(String.valueOf(sliderValueRightMotor));
    }

    /*
     *
     * Function Name: jTextFieldRightMotorActionPerformed
     * Input: None
     * Output: sets the jSliderRightMotor value accordind to the value entered in the rightt motor's velocity text field 
     * Logic: gets the value entered in the jTextFieldRightMotor by calling getText method and then sets the slider value 
     *        by calling setValue method on jSliderRightMotor
     * Example Call: jTextFieldRightMotorActionPerformed()
     *
     */
    private void jTextFieldRightMotorActionPerformed() {
        // gets the value entered in the jTextFieldRightmotor and stores it in variable sliderInputRightMotor
        String SliderInputRightMotor = jTextFieldRightMotor.getText();
        jSliderRightMotor.setValue(Integer.parseInt(SliderInputRightMotor));
    }

    /*
     *
     * Function Name: jButtonSetVelocityActionPerformed
     * Input: None
     * Output: sets the velocity of left and right motor according to the specified value.
     * Logic: gets the left and right motor velocity from their respective TextFields and performs a check whether any of the velocity
     *        is left blank. If so then prompts the user to set a valid velocity. Once a valid velocity has been entered then it writes 
     *        "R" on the outputstream to indicate that the velocity has to changed and then writes both the velocities on the 
     *        outputstream.
     * Example Call: jButtonSetVelocityActionPerformed()
     *
     */
    private void jButtonSetVelocityActionPerformed() {
        //checks whether any of the velocity text field has been left blank
        if(!(jTextFieldLeftMotor.getText().equals(""))&& !(jTextFieldLeftMotor.getText()).isEmpty() && !(jTextFieldRightMotor.getText().equalsIgnoreCase("")) && !(jTextFieldRightMotor.getText().isEmpty()))
        {
            //enables the reset velocity button to reset the velocity
            jButtonResetVelocity.setEnabled(true);
            //gets the left motor velocity and stores it in variable lVelocity
            int lVelocity = Integer.parseInt(jTextFieldLeftMotor.getText());
            //gets the right motor velocity and stores it in variable rVelocity
            int rVelocity = Integer.parseInt(jTextFieldRightMotor.getText());
            spc.writeOnTerminal("R");
            try {
                //writes the left motor velocity on the output stream
                spc.outputstream.write((byte)lVelocity);
                //writes the right motor velocity on the output stream
                spc.outputstream.write((byte)rVelocity);
            } catch (IOException ex) {
                Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Prompts the user that velocity has been set and now the bot will move with the specified velocity
            JOptionPane.showMessageDialog(jPanelFrame, "Bot Will Move With Specified Speeds of Motors");
        }
        else if (jTextFieldLeftMotor.getText().equalsIgnoreCase("") || jTextFieldRightMotor.getText().equalsIgnoreCase("")){
            //Prompts the user to set the valid velocities    
            JOptionPane.showMessageDialog(jPanelFrame, "Please Set the Velocities");
        }
    }

    /*
     *
     * Function Name: jButtonResetVelocityActionPerformed
     * Input: None
     * Output: resets the velocity of both the motors to there initial value
     * Logic: the initial value of the motors is 255
     * Example Call: jButtonResetVelocityActionPerformed()
     *
     */
    private void jButtonResetVelocityActionPerformed() {
        
        spc.writeOnTerminal("S");
        jTextFieldLeftMotor.setText("");
        jTextFieldRightMotor.setText("");
        //sets the left motor velocity equal to 255
        jSliderLeftMotor.setValue(255);
        //sets the right motor velocity equals to 255
        jSliderRightMotor.setValue(255);
    }

    /*
     *
     * Function Name: getSliderValueServo1
     * Input: None
     * Output: rotates the servo motor S1 by specified angle
     * Logic: gets the value of angle by calling the method getValue() of jSlider class and sets the text field to that value
     *        using setText() method of jTextField class
     * Example Call: getSliderValueServo1()
     *
     */
    private void getSliderValueServo1() {
        // gets the value of the jSliderServo1 and stores it in variable SliderValueServo1
        int SliderValueServo1 = jSliderServo1.getValue();
        jTextFieldServo1.setText(String.valueOf(SliderValueServo1));
    }

    /*
     *
     * Function Name: getSliderValueServo2
     * Input: None
     * Output: rotates the servo motor S2 by specified angle
     * Logic: gets the value of angle by calling the method getValue() of jSlider class and sets the text field to that value
     *        using setText() method of jTextField class
     * Example Call: getSliderValueServo2()
     *
     */
    private void getSliderValueServo2() {
       // gets the value of the jSliderServo2 and stores it in variable SliderValueServo2
        int SliderValueServo2 = jSliderServo2.getValue();
        jTextFieldServo2.setText(String.valueOf(SliderValueServo2));
    }

    /*
     *
     * Function Name: getSliderValueServo3
     * Input: None
     * Output: rotates the servo motor S3 by specified angle
     * Logic: gets the value of angle by calling the method getValue() of jSlider class and sets the text field to that value
     *        using setText() method of jTextField class
     * Example Call: getSliderValueServo3()
     *
     */
    private void getSliderValueServo3() {
        // gets the value of the jSliderServo3 and stores it in variable SliderValueServo3
        int SliderValueServo3 = jSliderServo3.getValue();
        jTextFieldServo3.setText(String.valueOf(SliderValueServo3));
    }

    /*
     *
     * Function Name: jButtonForwardMovementActionPerformed
     * Input: None
     * Output: moves the bot forward by specified distance
     * Logic: gets the value of the distance by which bot has to move forward from the jTextFieldDistance by calling getText method 
     *        and then sends the value to the outputstream to move the bot. To get the value of distance greater than 255 the enterd 
     *        value is divided by 255 and modulo by 255 and then sent.
     * Example Call: jButtonForwardMovementActionPerformed()
     *
     */
    private void jButtonForwardMovementActionPerformed() {
     
        // checks whether a valid angle has been entered or not
        if(jTextFieldDistance.getText() != null && !jTextFieldDistance.getText().isEmpty())
        {
        spc.writeOnTerminal("U");
        //converts the distance into integer
        int Distance = Integer.parseInt(jTextFieldDistance.getText());
       
        //stores the value of distance divided by 255 in variable qDistance
        int qDistance = Distance/255;
         
        //stores the value of distance modulo 255 in variable rDistance
        int rDistance = Distance%255;
        
        try {
             
            spc.outputstream.write((byte)qDistance);
            
            spc.outputstream.write((byte)rDistance);
        } catch (IOException ex) {
            
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    /*
     *
     * Function Name: jButtonBackwardMovementActionPerformed
     * Input: None
     * Output: moves the bot backward by specified distance
     * Logic: gets the value of the distance by which bot has to move backward from the jTextFieldDistance by calling getText method 
     *        and then sends the value to the outputstream to move the bot. To get the value of distance greater than 255 the enterd 
     *        value is divided by 255 and modulo by 255 and then sent.
     * Example Call: jButtonBackwardMovementActionPerformed()
     *
     */
    private void jButtonBackwardMovementActionPerformed() {
        // checks whether a valid distance has been entered or not
        if (jTextFieldDistance.getText() != null && !jTextFieldDistance.getText().isEmpty()){
        spc.writeOnTerminal("V");
        //converts the distance into integer
        int Distance = Integer.parseInt(jTextFieldDistance.getText());
        //stores the value of distance divided by 255 in variable qDistance
        int qDistance = Distance/255;
        //stores the value of distance modulo 255 in variable rDistance
        int rDistance = Distance%255;
        try {
            spc.outputstream.write((byte)qDistance);
            spc.outputstream.write((byte)rDistance);
        } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    /*
     *
     * Function Name: jButtonRightRotationActionPerformed
     * Input: None
     * Output: rotates the bot right by specified degrees
     * Logic: gets the value of the angle by which motor has to be rotated right from the jTextFieldRotation by calling getText method 
     *        and then sends the value to the outputstream to rotate the bot. To get the value of angle greater than 255 the enterd 
     *        value is divided by 255 and modulo by 255 and then sent.
     * Example Call: jButtonRightRotationActionPerformed()
     *
     */
    private void jButtonRightRotationActionPerformed() { 
        // checks whether a valid angle has been entered or not
        if(jTextFieldRotation.getText() != null && !jTextFieldRotation.getText().isEmpty()){
        spc.writeOnTerminal("W");
        //converts the angle into integer
        int Angel = Integer.parseInt(jTextFieldRotation.getText());
        //stores the value of angle divided by 255 in variable qRotation
        int qRotation = Angel/255;
        //stores the value of angle modulo 255 in variable rRotation
        int rRotation = Angel%255;
        try {
            spc.outputstream.write((byte)qRotation);
            spc.outputstream.write((byte)rRotation);
        } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    /*
     *
     * Function Name: jButtonLeftRotationActionPerformed
     * Input: None
     * Output: rotates the bot left by specified degrees
     * Logic: gets the value of the angle by which motor has to be rotated left from the jTextFieldRotation by calling getText method 
     *        and then sends the value to the outputstream to rotate the bot. To get the value of angle greater than 255 the enterd 
     *        value is divided by 255 and modulo by 255 and then sent.
     * Example Call: jButtonLeftRotationActionPerformed()
     *
     */
    private void jButtonLeftRotationActionPerformed() {
        // checks whether a valid angle has been entered or not
        if(jTextFieldRotation.getText() != null && !jTextFieldRotation.getText().isEmpty()){
            spc.writeOnTerminal("X");
            //converts the angle into integer
            int Angel = Integer.parseInt(jTextFieldRotation.getText());
            //stores the value of angle divided by 255 in variable qRotation
            int qRotation = Angel/255;
            //stores the value of angle modulo 255 in variable rRotation
            int rRotation = Angel%255;
            try {
                spc.outputstream.write((byte)qRotation);
                spc.outputstream.write((byte)rRotation);
            } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
     *
     * Function Name: jButtonServo1ActionPerformed
     * Input: None
     * Output: rotates the servo motor S1 by specified degrees
     * Logic: gets the value of the angle by which motor has to be rotated from the jTextFieldServo1 by calling getText method 
     *        and then sends the value to the outputstream to rotate the motor
     * Example Call: jButtonServo1ActionPerformed()
     *
     */
    private void jButtonServo1ActionPerformed() {
        jSliderServo1.setValue(Integer.parseInt(jTextFieldServo1.getText()));
        try {
            spc.outputstream.write(0x80);
            spc.outputstream.write((byte)Integer.parseInt(jTextFieldServo1.getText()));
        } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     *
     * Function Name: jButtonServo2ActionPerformed
     * Input: None
     * Output: rotates the servo motor S2 by specified degrees
     * Logic: gets the value of the angle by which motor has to be rotated from the jTextFieldServo2 by calling getText method 
     *        and then sends the value to the outputstream to rotate the motor
     * Example Call: jButtonServo2ActionPerformed()
     *
     */
    private void jButtonServo2ActionPerformed() {
        
        jSliderServo2.setValue(Integer.parseInt(jTextFieldServo2.getText()));
        try {
            spc.outputstream.write(0x81);
            spc.outputstream.write((byte)Integer.parseInt(jTextFieldServo2.getText()));
        } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
     *
     * Function Name: jButtonServo3ActionPerformed
     * Input: None
     * Output: rotates the servo motor S3 by specified degrees
     * Logic: gets the value of the angle by which motor has to be rotated from the jTextFieldServo3 by calling getText method 
     *        and then sends the value to the outputstream to rotate the motor
     * Example Call: jButtonServo3ActionPerformed()
     *
     */
    private void jButtonServo3ActionPerformed() {
        
        jSliderServo3.setValue(Integer.parseInt(jTextFieldServo3.getText()));
        try {
            spc.outputstream.write(0x82);
            spc.outputstream.write((byte)Integer.parseInt(jTextFieldServo3.getText()));
        } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /*
     *
     * Function Name: jButtonLCDPrintActionPerformed
     * Input: None
     * Output: prints the character entered by the user on the LCD screen at entered row and column
     * Logic: gets the row no, column no and the character to be printed on the LCD screen by calling getText method and then
     *        sends these values to the output stream 
     * Example Call: jButtonLCDPrintActionPerformed()
     *
     */
    private void jButtonLCDPrintActionPerformed() {
        try {
            spc.outputstream.write(0x83);
            //gets the row no
            spc.outputstream.write((byte)Integer.parseInt(jTextFieldLCDRow.getText()));
            //gets the column no
            spc.outputstream.write((byte)Integer.parseInt(jTextFieldLCDColumn.getText()));
            //gets the character to be printed
            spc.outputstream.write(jTextFieldLCDText.getText().getBytes());
        } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     *
     * Function Name: jButtonBarGraphLedActionPerformed
     * Input: None
     * Output: glows the respective bar graph led as entered by the user
     * Logic: gets the number of bar graph led to be glown from the jTextFieldBarGraphLed by calling getText method and then 
     *        writes the corresponding hex value on the output stream
     * Example Call: jButtonBarGraphLedActionPerformed()
     *
     */
    private void jButtonBarGraphLedActionPerformed() {
        
        int lednumber = Integer.parseInt(jTextFieldBarGraphLed.getText());
        try{
            spc.outputstream.write(0x84);
        if(lednumber == 1){
            spc.outputstream.write(0x01);
        }
        if(lednumber == 2){
            spc.outputstream.write(0x02);
        }
        if(lednumber == 3){
            spc.outputstream.write(0x04);
        }
        if(lednumber == 4){
            spc.outputstream.write(0x08);
        }
        if(lednumber == 5){
            spc.outputstream.write(0x10);
        }
        if(lednumber == 6){
            spc.outputstream.write(0x20);
        }
        if(lednumber == 7){
            spc.outputstream.write(0x40);
        }
        if(lednumber == 8){
            spc.outputstream.write(0x80);
        }
        }catch(IOException ex){
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
     *
     * Function Name: jTextFieldServo1ActionPerformed
     * Input: None
     * Output: sets the jSliderServo1 slider value according to the text entered in the jTextFieldServo1
     * Logic: gets the value from the jTextFieldServo1 by calling getText method, then converts it into an integer 
     *        value and finally sets the value of slider by calling setValue method.
     * Example Call: jTextFieldServo1ActionPerformed()
     *
     */
    private void jTextFieldServo1ActionPerformed() {
       jSliderServo1.setValue(Integer.parseInt(jTextFieldServo1.getText()));
    }
    
    /*
     *
     * Function Name: jTextFieldServo2ActionPerformed
     * Input: None
     * Output: sets the jSliderServo2 slider value according to the text entered in the jTextFieldServo2
     * Logic: gets the value from the jTextFieldServo2 by calling getText method, then converts it into an integer 
     *        value and finally sets the value of slider by calling setValue method.
     * Example Call: jTextFieldServo1ActionPerformed()
     *
     */
    private void jTextFieldServo2ActionPerformed() {
       jSliderServo2.setValue(Integer.parseInt(jTextFieldServo2.getText()));
    }
    
    /*
     *
     * Function Name: jTextFieldServo3ActionPerformed
     * Input: None
     * Output: sets the jSliderServo3 slider value according to the text entered in the jTextFieldServo3
     * Logic: gets the value from the jTextFieldServo3 by calling getText method, then converts it into an integer 
     *        value and finally sets the value of slider by calling setValue method.
     * Example Call: jTextFieldServo1ActionPerformed()
     *
     */
    private void jTextFieldServo3ActionPerformed() {
       jSliderServo3.setValue(Integer.parseInt(jTextFieldServo3.getText()));
    }

  
    /*
     *
     * Function Name: main
     * Input: String array which stores anything entered from command prompt
     * Output: starts the GUI
     * Logic: called automatically by the operating system
     * Example Call: main(String s[])
     *
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIApp1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new GUIApp1().setVisible(true);
            }
        });
    }
    
    /*
     * Function Name: listSerialPorts
     * Input: void
     * Output: list all the available ports in JComboBox
     * Logic: calls serialPorts() method of SerialPortConnection class to get a String array of all the available ports 
     * Example Call: listSerialPorts()
     */
    private void listSerialPorts() 
    {   
        String portArray[] = spc.serialPorts();
        jComboBoxCOMPorts.setModel(new javax.swing.DefaultComboBoxModel(portArray));
        jComboBoxCOMPorts.setSelectedIndex(-1);
    }
    
    /*
     * Function Name: removeGUIComponents
     * Input: void
     * Output: enable disable various components of GUI and removes text from JTextFiel and jLabel and set the value of all the jProgressBar to 0.
     * Logic: setText() method of jLabel class and jTextField is called to clear the text, setEnabled() method is used to disable the text field on diconnecting and setValue() method of jProgressBar class is called to set the value of progress bar equals to 0  
     * Example Call: removeGUIComponents()
     */
    public void removeGUIComponents(){
            jLabelRightWLSensor.setText(null);
            jLabelCenterWLSensor.setText(null);
            jLabelLeftWLSensor.setText(null);
            jLabelIRSensor1.setText(null);
            jLabelIRSensor2.setText(null);
            jLabelIRSensor3.setText(null);
            jLabelIRSensor4.setText(null);
            jLabelIRSensor5.setText(null);
            jLabelIRSensor6.setText(null);
            jLabelIRSensor7.setText(null);
            jLabelIRSensor8.setText(null);
            jLabelSharpSensor1.setText(null);
            jLabelSharpSensor2.setText(null);
            jLabelSharpSensor3.setText(null);
            jLabelSharpSensor4.setText(null);
            jLabelSharpSensor5.setText(null);
            jLabelVoltage.setText(null);
            jTextFieldLeftMotor.setEnabled(false);
            jTextFieldRightMotor.setEnabled(false);
            jTextFieldServo1.setEnabled(false);
            jTextFieldServo2.setEnabled(false);
            jTextFieldServo3.setEnabled(false);
            jTextFieldDistance.setEnabled(false);
            jTextFieldRotation.setEnabled(false);
            jProgressBarLeftWLSensor.setValue(0);
            jProgressBarRightWLSensor.setValue(0);
            jProgressBarCenterWLSensor.setValue(0);
            jProgressBarSharpSensor1.setValue(0);
            jProgressBarSharpSensor2.setValue(0);
            jProgressBarSharpSensor3.setValue(0);
            jProgressBarSharpSensor4.setValue(0);
            jProgressBarSharpSensor5.setValue(0);
            jProgressBarIRSensor1.setValue(0);
            jProgressBarIRSensor2.setValue(0);
            jProgressBarIRSensor3.setValue(0);
            jProgressBarIRSensor4.setValue(0);
            jProgressBarIRSensor5.setValue(0);
            jProgressBarIRSensor6.setValue(0);
            jProgressBarIRSensor7.setValue(0);
            jProgressBarIRSensor8.setValue(0);
            jProgressBarBatteryVoltage.setValue(0);
            jTextFieldLeftMotor.setText("");
            jTextFieldRightMotor.setText("");
            jTextFieldServo1.setText("");
            jTextFieldServo2.setText("");
            jTextFieldServo3.setText("");
            jTextFieldDistance.setText("");
            jTextFieldRotation.setText("");
            jTextFieldLCDRow.setText("");
            jTextFieldLCDColumn.setText("");
            jTextFieldLCDText.setText("");
            jTextFieldLCDRow.setEnabled(false);
            jTextFieldLCDColumn.setEnabled(false);
            jTextFieldLCDText.setEnabled(false);
            jTextFieldBarGraphLed.setEnabled(false);
    }
    
    /*
     * Function Name: ConnectComponents
     * Input: void
     * Output: Enable all the jTextField and jLabels and starts the ReadThread to start reading the sensors value
     * Logic: setEnabled() function of jTextField and jLabel class is called to enable text field and label
     * Example Call: ConnectGUIComponents()
     */
    public void connectGUIComponents(){
        jTextFieldLeftMotor.setEnabled(true);
        jTextFieldRightMotor.setEnabled(true);
        jTextFieldServo1.setEnabled(true);
        jTextFieldServo2.setEnabled(true);
        jTextFieldServo3.setEnabled(true);
        jTextFieldDistance.setEnabled(true);
        jTextFieldRotation.setEnabled(true);
        jButtonCOMDisconnect.setEnabled(true);
        jButtonCOMConnect.setEnabled(false);
        jButtonSetVelocity.setEnabled(true);
        jTextFieldLCDRow.setEnabled(true);
        jTextFieldLCDColumn.setEnabled(true);
        jTextFieldLCDText.setEnabled(true);
        jTextFieldBarGraphLed.setEnabled(true);
        new Thread(new ReadThread()).start();
        
    }
    
    // Thread to read the value of sensors
    private class ReadThread implements Runnable 
    {  
        /*
         * Function Name: run
         * Input: void
         * Output: sensor values
         * Logic: writes "T" on outputstream to start reading the values of sensors
         * Example Call: called everytime this Thread is started
         */
        public void run() 
        {
            // continously writes "T" on the output stream to read the value of the sensors till the time GUI is connected to the robot
            while(spc.portId.isCurrentlyOwned() && read==true) 
            {
                try 
                {
                    spc.writeOnTerminal("T");
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /*
     * Function Name: connect
     * Input: portName --> String which contains the name of the port with which the connection has to be made 
     * Output: connects GUI with the selected COM port
     * Logic: calls connectToPort(portName) method of SerialPortConnection class in order to connect to the robot. If connection is not 
     *        not possible due to some exception then displays the corresponding exception message in a dialog box.
     * Example Call: connect("COM3")
     */
    public void connect(String portName)
    {
        // stores the result of connection in local variable res
        int res = spc.connectToPort(portName);
        // if successful connection has been made then calls setSerialEventHandler() and connectGUIComponents() method to set the 
        // serial event handler for reading the values of the sensors and to initialize the GUI components with there default values
        if(res == 1)
        {
            connectGUIComponents();
            setSerialEventHandler();
        }
        else if( res == 3)
        {
            JOptionPane.showMessageDialog(jPanelFrame, "Port is in use");
        }
            
        else if(res == 4)
        {
            JOptionPane.showMessageDialog(jPanelFrame, "No such port exists");
        }
        
    }
    
    private class SerialEventHandler implements SerialPortEventListener{

        /*
         * Function Name: serialEvent
         * Input: void
         * Output: reads the value of different sensors and updates there corresponding progress bar
         * Logic: data 0x59 in the inputstream indicates that the next 17 values read will be the 8 IR proximity, 5 sharp IR, 3 whiteline sensors 
         *        and 1 battery voltage. As the values are being read the corresponding progress bar and the jLabels are getting
         *        updated. After reading these 17 values the data in the input stream becomes equal to 0x5A indicating that 
         *        all the sensor values have been read.
         * Example Call: Method called when event to read from serial port occurs
         */
        @Override
        public void serialEvent(SerialPortEvent spe) 
        {   
            switch(spe.getEventType())
            {
                case SerialPortEvent.DATA_AVAILABLE:
                    try {        
                        byte singleData = (byte)spc.inputstream.read();    
                        if (singleData != NEW_LINE_ASCII)
                        {
                            value= singleData & 0xff;
                            String input = String.valueOf(value);
                            if(flag==1){
                                if(singleData == 0x5A && count1==18){
                                    System.out.println("end");
                                    flag=0;
                                    count1=0;
                                }
                                int tempvalue = value;
                                if(count1==1){
                                    System.out.println(input);
                                    jLabelIRSensor1.setText(input);
                                    jProgressBarIRSensor1.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==2){
                                    System.out.println(input);
                                    jLabelIRSensor2.setText(input);
                                    jProgressBarIRSensor2.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==3){
                                    System.out.println(input);
                                    jLabelIRSensor3.setText(input);
                                    jProgressBarIRSensor3.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==4){
                                    System.out.println(input);
                                    jLabelIRSensor4.setText(input);
                                    jProgressBarIRSensor4.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==5){
                                    System.out.println(input);
                                    jLabelIRSensor5.setText(input);
                                    jProgressBarIRSensor5.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==6){
                                    System.out.println(input);
                                    jLabelIRSensor6.setText(input);
                                    jProgressBarIRSensor6.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==7){
                                    System.out.println(input);
                                    jLabelIRSensor7.setText(input);
                                    jProgressBarIRSensor7.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==8){
                                    System.out.println(input);
                                    jLabelIRSensor8.setText(input);
                                    jProgressBarIRSensor8.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==9){
                                    System.out.println(input);
                                    // calls the function Sharp_Distance_Sensor_estimation(tempvalue) to convert analog value into distance
                                    value1= Sharp_Distance_Sensor_estimation(tempvalue);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor1.setText(input+"mm");
                                    jProgressBarSharpSensor1.setValue(value1);
                                    count1++;
                                }
                                else if(count1==10){
                                    System.out.println(input);
                                    // calls the function Sharp_Distance_Sensor_estimation(tempvalue) to convert analog value into distance
                                    value1= Sharp_Distance_Sensor_estimation(value);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor2.setText(input+"mm");
                                    jProgressBarSharpSensor2.setValue(value1);
                                    count1++;
                                    singleData = 0x00;
                                }
                                else if(count1==11){
                                    System.out.println(input);
                                    // calls the function Sharp_Distance_Sensor_estimation(tempvalue) to convert analog value into distance
                                    value1= Sharp_Distance_Sensor_estimation(value);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor3.setText(input+"mm");
                                    jProgressBarSharpSensor3.setValue(value1);
                                    count1++;
                                }
                                else if(count1==12){
                                    System.out.println(input);
                                    // calls the function Sharp_Distance_Sensor_estimation(tempvalue) to convert analog value into distance
                                    value1= Sharp_Distance_Sensor_estimation(tempvalue);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor4.setText(input+"mm");
                                    jProgressBarSharpSensor4.setValue(value1);
                                    count1++;
                                }
                                else if(count1==13){
                                    System.out.println(input);
                                    // calls the function Sharp_Distance_Sensor_estimation(tempvalue) to convert analog value into distance
                                    value1= Sharp_Distance_Sensor_estimation(tempvalue);
                                    input = String.valueOf(value1);
                                    jLabelSharpSensor5.setText(input+"mm");
                                    jProgressBarSharpSensor5.setValue(value1);
                                    count1++;
                                }
                                else if(count1==14){
                                    System.out.println(input);
                                    jLabelLeftWLSensor.setText(input);
                                    jProgressBarLeftWLSensor.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==15){
                                    System.out.println(input);
                                    jLabelCenterWLSensor.setText(input);
                                    jProgressBarCenterWLSensor.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==16){
                                    System.out.println(input);
                                    jLabelRightWLSensor.setText(input);
                                    jProgressBarRightWLSensor.setValue(tempvalue);
                                    count1++;
                                }
                                else if(count1==17){
                                    System.out.println(input);
                                    // converts the read value into battery voltage
                                    value2 = (float)(((((tempvalue)*100)*0.07902) + 0.7)/100);
                                    input = String.valueOf(value2);
                                    jLabelVoltage.setText("   "+input+"V");
                                    jProgressBarBatteryVoltage.setValue((int)value2);
                                    count1++;
                                }
                            }
                            if(singleData == 0x59){
                                System.out.println("start");
                                flag=1;
                                count1=1;
                            }
                        }
                    }catch (IOException ex) {
                        Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                    break;
                default: System.out.println("No data found");
            }
        
        }
    }
        
    /**
     * Function Name: setSerialEventHandler 
     * Input: void
     * Output: set the serial event handler by adding the event listener
     * Logic: addEventListener() and notifyOnDataAvailable() method of serialport class is called to set the serial event handler
     * Example Call: setSerialEventHandler()
     */
    public void setSerialEventHandler()
    {   
        //Creates the object of SeriaEventHandler class
        SerialEventHandler seh = new SerialEventHandler(); 
        try {
            //Registers a SerialPortEventListener object to listen for SerialEvents.
            spc.serialport.addEventListener(seh); 
            //Expresses interest in receiving notification when input data is available.
            spc.serialport.notifyOnDataAvailable(true); 
        } catch (TooManyListenersException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
  
    /**
     * Function Name: Sharp_Distance_Sensor_estimation
     * Input: int-> reading of sensor 
     * Output: int-> reading of sensor in millimeter
     * Logic: 10.00*(2799.6*(1.00/(Math.pow(value,1.1546))))
     * Example Call: Sharp_Distance_Sensor_estimation(value)
     */
    int Sharp_Distance_Sensor_estimation (int value){
        //local variable to store the value of distance sensor in mm
        float distance; 
        //convert local variable distance into interger
	int distanceInt; 
        //converts distance sensor reading into millimeter
	distance = (int)(10.00*(2799.6*(1.00/(Math.pow(value,1.1546))))); 
	distanceInt = (int)distance;
	if(distanceInt>800)
	{
		distanceInt=800;
	}
	return distanceInt;
     
    }
 
    /*
     * Function Name: setIcon
     * Input: void
     * Output: sets the icon
     * Logic: sets the icon by calling setIconImage(Image) method
     * Example Call: setIcon()
     */
    public void setIcon()
    {
           setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.jpg")));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonBackwardMotion;
    public javax.swing.JButton jButtonBackwardMovement;
    public javax.swing.JButton jButtonBarGraphLed;
    private javax.swing.JButton jButtonBuzzer;
    public javax.swing.JButton jButtonCOMConnect;
    public javax.swing.JButton jButtonCOMDisconnect;
    public javax.swing.JButton jButtonForwardMotion;
    public javax.swing.JButton jButtonForwardMovement;
    public javax.swing.JButton jButtonLCDPrint;
    public javax.swing.JButton jButtonLeftMotion;
    public javax.swing.JButton jButtonLeftRotation;
    public javax.swing.JButton jButtonResetVelocity;
    public javax.swing.JButton jButtonRightMotion;
    public javax.swing.JButton jButtonRightRotation;
    public javax.swing.JButton jButtonServo1;
    public javax.swing.JButton jButtonServo2;
    public javax.swing.JButton jButtonServo3;
    private javax.swing.JButton jButtonSetVelocity;
    public javax.swing.JButton jButtonStopMotion;
    public javax.swing.JComboBox jComboBoxCOMPorts;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabelBarGraphLEDTitle;
    private javax.swing.JLabel jLabelBatteryVoltageTitle;
    private javax.swing.JLabel jLabelBuzzerTitle;
    private javax.swing.JLabel jLabelCOMPortTitle;
    public javax.swing.JLabel jLabelCenterWLSensor;
    private javax.swing.JLabel jLabelCenterWLSensorText;
    private javax.swing.JLabel jLabelDistance;
    private javax.swing.JLabel jLabelDistanceSensor1;
    private javax.swing.JLabel jLabelDistanceSensor2;
    private javax.swing.JLabel jLabelDistanceSensor3;
    private javax.swing.JLabel jLabelDistanceSensor4;
    private javax.swing.JLabel jLabelDistanceSensor5;
    private javax.swing.JLabel jLabelDistanceSensorTitle;
    private javax.swing.JLabel jLabelHeading;
    public javax.swing.JLabel jLabelIRSensor1;
    public javax.swing.JLabel jLabelIRSensor2;
    public javax.swing.JLabel jLabelIRSensor3;
    public javax.swing.JLabel jLabelIRSensor4;
    public javax.swing.JLabel jLabelIRSensor5;
    public javax.swing.JLabel jLabelIRSensor6;
    public javax.swing.JLabel jLabelIRSensor7;
    public javax.swing.JLabel jLabelIRSensor8;
    private javax.swing.JLabel jLabelIRSensorNo1;
    private javax.swing.JLabel jLabelIRSensorNo2;
    private javax.swing.JLabel jLabelIRSensorNo3;
    private javax.swing.JLabel jLabelIRSensorNo4;
    private javax.swing.JLabel jLabelIRSensorNo5;
    private javax.swing.JLabel jLabelIRSensorNo6;
    private javax.swing.JLabel jLabelIRSensorNo7;
    private javax.swing.JLabel jLabelIRSensorNo8;
    private javax.swing.JLabel jLabelIRSensorTitle;
    private javax.swing.JLabel jLabelLCDColumn;
    private javax.swing.JLabel jLabelLCDPrintTitle;
    private javax.swing.JLabel jLabelLCDRow;
    private javax.swing.JLabel jLabelLCDText;
    private javax.swing.JLabel jLabelLeftMotor;
    public javax.swing.JLabel jLabelLeftWLSensor;
    private javax.swing.JLabel jLabelLeftWLSensorText;
    private javax.swing.JLabel jLabelMotionControlTitle;
    private javax.swing.JLabel jLabelMovementRotationTitle;
    private javax.swing.JLabel jLabelRightMotor;
    public javax.swing.JLabel jLabelRightWLSensor;
    private javax.swing.JLabel jLabelRightWLSensorText;
    private javax.swing.JLabel jLabelRotation;
    private javax.swing.JLabel jLabelServoMotor1;
    private javax.swing.JLabel jLabelServoMotor2;
    private javax.swing.JLabel jLabelServoMotor3;
    private javax.swing.JLabel jLabelServoMotorTitle;
    public javax.swing.JLabel jLabelSharpSensor1;
    public javax.swing.JLabel jLabelSharpSensor2;
    public javax.swing.JLabel jLabelSharpSensor3;
    public javax.swing.JLabel jLabelSharpSensor4;
    public javax.swing.JLabel jLabelSharpSensor5;
    private javax.swing.JLabel jLabelVelocityTitle;
    public javax.swing.JLabel jLabelVoltage;
    private javax.swing.JLabel jLabelWLSensorTitle;
    private javax.swing.JPanel jPanelBarLED;
    private javax.swing.JPanel jPanelBatterVoltage;
    private javax.swing.JPanel jPanelBuzzer;
    private javax.swing.JPanel jPanelCOMPort;
    private javax.swing.JPanel jPanelDistanceSensor;
    private javax.swing.JPanel jPanelFrame;
    private javax.swing.JPanel jPanelIRSensor;
    private javax.swing.JPanel jPanelLCD;
    private javax.swing.JPanel jPanelMotionControl;
    private javax.swing.JPanel jPanelMovementRotation;
    private javax.swing.JPanel jPanelServoMotor;
    public javax.swing.JPanel jPanelVelocity;
    public javax.swing.JPanel jPanelWLSensor;
    public javax.swing.JProgressBar jProgressBarBatteryVoltage;
    public javax.swing.JProgressBar jProgressBarCenterWLSensor;
    public javax.swing.JProgressBar jProgressBarIRSensor1;
    public javax.swing.JProgressBar jProgressBarIRSensor2;
    public javax.swing.JProgressBar jProgressBarIRSensor3;
    public javax.swing.JProgressBar jProgressBarIRSensor4;
    public javax.swing.JProgressBar jProgressBarIRSensor5;
    public javax.swing.JProgressBar jProgressBarIRSensor6;
    public javax.swing.JProgressBar jProgressBarIRSensor7;
    public javax.swing.JProgressBar jProgressBarIRSensor8;
    public javax.swing.JProgressBar jProgressBarLeftWLSensor;
    public javax.swing.JProgressBar jProgressBarRightWLSensor;
    public javax.swing.JProgressBar jProgressBarSharpSensor1;
    public javax.swing.JProgressBar jProgressBarSharpSensor2;
    public javax.swing.JProgressBar jProgressBarSharpSensor3;
    public javax.swing.JProgressBar jProgressBarSharpSensor4;
    public javax.swing.JProgressBar jProgressBarSharpSensor5;
    public javax.swing.JSlider jSliderLeftMotor;
    public javax.swing.JSlider jSliderRightMotor;
    public javax.swing.JSlider jSliderServo1;
    public javax.swing.JSlider jSliderServo2;
    public javax.swing.JSlider jSliderServo3;
    public javax.swing.JTextField jTextFieldBarGraphLed;
    public javax.swing.JTextField jTextFieldDistance;
    public javax.swing.JTextField jTextFieldLCDColumn;
    public javax.swing.JTextField jTextFieldLCDRow;
    public javax.swing.JTextField jTextFieldLCDText;
    public javax.swing.JTextField jTextFieldLeftMotor;
    public javax.swing.JTextField jTextFieldRightMotor;
    public javax.swing.JTextField jTextFieldRotation;
    public javax.swing.JTextField jTextFieldServo1;
    public javax.swing.JTextField jTextFieldServo2;
    public javax.swing.JTextField jTextFieldServo3;
    // End of variables declaration//GEN-END:variables
}
