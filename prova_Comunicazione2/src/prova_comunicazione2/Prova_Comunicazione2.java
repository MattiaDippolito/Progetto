/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_comunicazione2;

import jssc.SerialPortEvent;
import jssc.SerialPortException;

/**
 *
 * @author thoma
 */
public class Prova_Comunicazione2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SerialPortException {
        Serial porta = new Serial();
        SerialPortEvent spe = new SerialPortEvent("COM3", 0, 0);
        porta.serialEvent(spe);
    }
    
}
