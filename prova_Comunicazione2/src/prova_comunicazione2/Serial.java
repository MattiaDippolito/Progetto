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
public class Serial implements SerialPortEventListener {

    private SerialPort porta;
    private int BAUDRATE = 9600;
    private int DATABITS = 8;
    private int STOPBITS = 1;
    private int PARITYNONE = 0;

    public Serial() throws SerialPortException {
        serialInitilize();
    }

    /*public void ApriPorta()
    {
        porta.openPort();
    }*/
    
    @Override
    public void serialEvent(SerialPortEvent spe) {
        //dichiaro un buffer che può contenere solo un carattere alla volta
        byte[] buffer = new byte[1];
        while (true) {
            try {
                
                //Se la porta è aperta
                if (porta.openPort()) {
                    
                    //Assegno il carattere letto al buffer
                    buffer = porta.readBytes(1);
                    
                    //Se riceve w muove il giocatore in alto
                    if (buffer[0] == 'w') {
                        System.out.println("VAI SU");
                    }
                    
                    //Se riceve s muove il giocatore in alto
                    if (buffer[0] == 's') {
                        System.out.println("Vai GIU");
                    }
                }
            } catch (jssc.SerialPortException ex) {
                Logger.getLogger(Serial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void serialInitilize()
    {
        try {
            porta = new SerialPort("COM3");
            
            //APRO LA SERIALE
            System.out.println("Port opened: " + porta.openPort());
            
            //IMPOSTO I PARAMETRI
            System.out.println("Params setted: " + porta.setParams(BAUDRATE, DATABITS, STOPBITS, PARITYNONE));
            
            //DAI TEMPO AD ARDUINO DI RESETTARSI
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Serial.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /*
            //INIZIALIZZA A TRUE
            int mask = SerialPort.MASK_RXCHAR;
            
            //IMPOSTA LA MASCHERA
            porta.setEventsMask(mask);*/
            
            //AGGIUNGI UN INTERFACCIA ATTRAVERSO LA QUALE RICEVEREMO LE INFO
            porta.addEventListener(new SerialPortReader(porta));
            
            System.out.println("Siamo Pronti!!!");
            
        } catch (SerialPortException ex) {
            Logger.getLogger(Serial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
