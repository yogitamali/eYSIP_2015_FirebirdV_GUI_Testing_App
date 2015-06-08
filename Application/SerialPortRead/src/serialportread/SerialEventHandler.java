/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialportread;
import gnu.io.*;
import java.io.*;
import java.util.TooManyListenersException;

/**
 *
 * @author Amit Bhargava
 */
public class SerialEventHandler implements SerialPortEventListener {
    public void serialEvent(SerialPortEvent evt){
        System.out.println("Serial Event");
        switch (evt.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                System.out.println("Reading Start");
                readSerial();
                break; 
        }
    }
    
    byte[] readBuffer = new byte[400];
private void readSerial() {
    
    try {
        int availableBytes = SerialPortRead.inputstream.available();
        if (availableBytes > 0) {
            // Read the serial port
            SerialPortRead.inputstream.read(readBuffer, 0, availableBytes);
            //System.out.println("Read");
            // Print it out
            System.out.println(
                    new String(readBuffer, 0, availableBytes));
        }
    } catch (IOException e) {
    }
}

}   
