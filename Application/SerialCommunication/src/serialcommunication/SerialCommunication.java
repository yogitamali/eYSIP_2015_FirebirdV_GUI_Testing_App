/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialcommunication;
import gnu.io.*;
import java.io.*;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Amit Bhargava
 */
public class SerialCommunication {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Hello");
       
        CommPortIdentifier portId;
        CommPort port;
        SerialPort serialport;
        OutputStream outputstream;
        InputStream inputstream;
        //byte[] readbuffer = new byte[500];
        //byte b[] = {100};
        try {
            portId = CommPortIdentifier.getPortIdentifier("COM4");
            System.out.println(portId.getName());
            try {
                port = portId.open("Demo Application", 5000);
                System.out.println("Get the port's ownership");
                //port.close();
                serialport = (SerialPort)port;
                int baudRate = 9600;
                try {
                    serialport.setSerialPortParams(
                            baudRate,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                    serialport.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                    System.out.println("Parameters set");
                    try {
                        outputstream = serialport.getOutputStream();
                        inputstream = serialport.getInputStream();
                        System.out.println("Set input and output stream");
                        String serialmessage = "abc";
                        outputstream.write(serialmessage.getBytes());
                        System.out.println("written on serialport");
                        serialport.close();
                        
                    } catch (IOException ex) {
                        //Logger.getLogger(SerialCommunication.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("IOException");
                    }
                    
                    
                } catch (UnsupportedCommOperationException ex) {
                    //Logger.getLogger(SerialCommunication.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("UnsupportedCommOperationException");
                }
            } catch (PortInUseException ex) {
                //Logger.getLogger(SerialCommunication.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("PortInUseException");
            }
            
            
 
        } catch (NoSuchPortException ex) {
            //Logger.getLogger(SerialCommunication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NoSuchPortException");
        }
        
        
        //System.out.println(portId);
        
    }
   
    
}   
    
    
    
    

