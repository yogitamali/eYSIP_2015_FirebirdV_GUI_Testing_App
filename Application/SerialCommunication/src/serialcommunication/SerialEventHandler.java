/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialcommunication;
import gnu.io.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialEventHandler extends SerialCommunication implements SerialPortEventListener {

    @Override
    public void serialEvent(SerialPortEvent spe) {
        byte[] readBuffer = new byte[400];
        try {
            System.out.println("Serial Event");
            switch(spe.getEventType()){
                case SerialPortEvent.DATA_AVAILABLE:
                    int availableBytes = SerialCommunication.inputstream.available();
                    System.out.println(availableBytes);
                    if(availableBytes>0){
                        SerialCommunication.inputstream.read(readBuffer, 0, availableBytes);
                        System.out.println(new String(readBuffer, 0, availableBytes));
                    }
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(SerialEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
