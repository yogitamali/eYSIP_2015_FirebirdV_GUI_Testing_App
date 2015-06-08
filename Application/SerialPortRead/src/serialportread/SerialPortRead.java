/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialportread;
import gnu.io.*;
import java.io.*;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Amit Bhargava
 */
public class SerialPortRead  {
    CommPortIdentifier portId;
    CommPort port;
    SerialPort serialport;
    public static InputStream inputstream;
    OutputStream outputstream;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        System.out.println("Hello");
        SerialPortRead serialportread = new SerialPortRead();
        System.out.println("End");
        }

    public SerialPortRead() {
        try {
            portId = CommPortIdentifier.getPortIdentifier("COM4");
            try {
                port = portId.open("Demo Application", 5000);
                serialport = (SerialPort)port;
                int baudRate = 9600;
                try {
                    serialport.setSerialPortParams(
                            baudRate,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                    serialport.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                    try {
                        outputstream = serialport.getOutputStream();
                        inputstream = serialport.getInputStream();
                        try {
                            System.out.println("Start Event");      
                            SerialEventHandler seh = new SerialEventHandler();
                            serialport.addEventListener(seh);
                            serialport.notifyOnDataAvailable(true);
                            System.out.println("Action performed");
                            //serialport.close();
                        } catch (TooManyListenersException ex) {
                            Logger.getLogger(SerialPortRead.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(SerialPortRead.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } catch (UnsupportedCommOperationException ex) {
                    Logger.getLogger(SerialPortRead.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
            } catch (PortInUseException ex) {
                Logger.getLogger(SerialPortRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchPortException ex) {
            Logger.getLogger(SerialPortRead.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("abc");
        }
        
    }
    
/*private class ReadThread implements Runnable {
    public void run() {
        while(true) {
            readSerial();
        }
    }
}
/*public void setSerialListener() {
    new Thread(new ReadThread()).start();
}*/
/*private byte[] readBuffer = new byte[400];
 
private void readSerial() {
    try {
        int availableBytes = inputstream.available();
        if (availableBytes > 0) {
            // Read the serial port
            inputstream.read(readBuffer, 0, availableBytes);
 
            // Print it out
            System.out.println(
                    new String(readBuffer, 0, availableBytes));
        }
    } catch (IOException e) {
    }
}

private void setSerialEventHandler(SerialPort serialport) {
    try {
        // Add the serial port event listener
        serialport.addEventListener(new SerialEventHandler1());
        serialport.notifyOnDataAvailable(true);
    } catch (TooManyListenersException ex) {
        System.err.println(ex.getMessage());
    }
}

private class SerialEventHandler1 implements SerialPortEventListener {
    public void serialEvent(SerialPortEvent event) {
        System.out.println("serial event");
        switch (event.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                readSerial();
                break;
        }
    }
}*/
    
}    
