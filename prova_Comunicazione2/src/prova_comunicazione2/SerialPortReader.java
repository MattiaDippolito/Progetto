/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_comunicazione2;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author thoma
 */
public class SerialPortReader implements SerialPortEventListener{

    private SerialPort porta;
    
    public SerialPortReader(SerialPort porta)
    {
        this.porta = porta;
    }
    
    @Override
    public void serialEvent(SerialPortEvent spe) {
               
        byte[] by = new byte[1];
        
        while(true){
            try {
                by = porta.readBytes(1);
                if(by[0]=='w')
                {
                    System.out.println("Vai Su");
                }
                else if (by[0]=='s')
                {
                    System.out.println("Vai Giu");
                }
            } catch (SerialPortException ex) {
                Logger.getLogger(SerialPortReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
