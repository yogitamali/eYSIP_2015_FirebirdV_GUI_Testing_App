
package GUIApp1;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Project Name: GUI Development for Fire Bird V using Java
 * Author List: Apoorva Bhargava
 * Filename: SerialPortConnection.java
 * Functions: SerialPortConnection(), removePorts(), serialPorts(), writeOnTerminal(String), connectToPort(String), 
 *            setInputOutputStream()
 * Global Variables: serialport, outputstream, inputstream, portId, port
 *
 */
public class SerialPortConnection 
{
    // SerialPort object
    SerialPort serialport;
    // OutputStream object
    OutputStream outputstream;
    // InputStream object
    InputStream inputstream;
    // CommPortIdentifier object
    CommPortIdentifier portId;
    // CommPort object
    CommPort port;
    
    /*
    * Function Name: removeSerialPorts
    * Input: void
    * Output: disconnect the connect serial port
    * Logic: calls removeEventListener() method of SerialPort class to stop the event that occurs while reading from terminal
    *        and also closes the serial port, input and output stream
    * Example Call: removeSerialPorts()
    */
    public void removeSerialPorts(){    
        try {
            writeOnTerminal("5");
            writeOnTerminal("9");
            serialport.removeEventListener();
            serialport.close();
            outputstream.close();
            inputstream.close();
            
        } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    * Function Name: serialPorts
    * Input: void
    * Output: returns a string array consisting of all the available ports
    * Logic: ports are enumerated using getPortIdentifiers() method of CommPortIdentifier class and
    *        stored in an array using ArrayList and then converted into String array and returned to the calling function
    * Example Call: serialPorts()
    */
    public String[] serialPorts() {
        // Obtains an enumeration object that contains a CommPortIdentifier object for each port in the system.
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        ArrayList portList = new ArrayList();
        String portArray[] = null;
        while (ports.hasMoreElements()) {
            CommPortIdentifier portIdentified = (CommPortIdentifier) ports.nextElement();
            if (portIdentified.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portList.add(portIdentified.getName());
            }
        }
        portArray = (String[]) portList.toArray(new String[0]);
        return portArray;
    }
    
    /*
    * Function Name: writeOnTerminal
    * Input: String-> string to write on terminal
    * Output: writes the string to the output stream
    * Logic: called write() and flush() method of OutputStream class to write to the output stream
    * Example Call: writeOnTerminal("A")
    */
    public void writeOnTerminal(String serialmessage){  // Function to write on the serial port
        try {
            outputstream.write(serialmessage.getBytes());
            outputstream.flush();
        } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    * Function Name: connectToPort 
    * Input: portName --> String which contains the name of the port to which connection has to be made
    * Output: make connection between specified port and GUI and sends the status back to the calling function. 
    *         Returns 1 if successful connection has been made
    *         Returns 2 if UnsupportedCommOperationException is thrown
    *         Returns 3 if PortInUseException is thrown
    *         Returns 4 if NoSuchPortException is thrown
    * Logic: calls the getPortIdentifier(portName) method of CommPortIdentifier class to obtain a CommPortIdentifier object
    *        and then open the communication channel of that port by using open(String, int) method of CommPortIdentifier
    *        class. After this it sets the baud rate, databits, stopbits and parity of the selected port. 
    * Example Call: connectToPort("COM 3")
    */
    public int connectToPort(String portName){  // Function to connect to the serial port
        try {
            System.out.println("Connect to Port");
            //Obtains a CommPortIdentifier object by using a port name.
            portId = CommPortIdentifier.getPortIdentifier(portName);
            System.out.println(portId.getName());
            try {
                //Opens the communications port.
                port = portId.open("Demo Application", 5000);
                serialport = (SerialPort)port;
                int baudRate = 115200;
                try {
                    // Sets serial port parameters.
                    serialport.setSerialPortParams(
                            baudRate,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                    // Sets the flow control mode.
                    serialport.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                    setInputOutputStream();
                    return 1;
                } catch (UnsupportedCommOperationException ex) {
                        Logger.getLogger(SerialPortConnection.class.getName()).log(Level.SEVERE, null, ex);
                    return 2;
                }
            } catch (PortInUseException ex) {
                Logger.getLogger(SerialPortConnection.class.getName()).log(Level.SEVERE, null, ex);
                return 3; 
            }
        } catch (NoSuchPortException ex) {
            Logger.getLogger(SerialPortConnection.class.getName()).log(Level.SEVERE, null, ex);
            return 4;
        }
    }
    
    /*
    * Function Name: SetInputOutputStream
    * Input: void
    * Output: returns the object of InputStream and OutputStream
    * Logic: creates the object for inputstream and outputstream to read from port and write on port respectively.
    * Example Call: setInputOutputStream()
    */
    public void setInputOutputStream(){   
        InputStream tempInputStream = null;
        OutputStream tempOutputStream = null;
        try {
            tempInputStream = serialport.getInputStream();
            tempOutputStream = serialport.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(GUIApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
        inputstream = tempInputStream;
        outputstream = tempOutputStream;
    }
}
