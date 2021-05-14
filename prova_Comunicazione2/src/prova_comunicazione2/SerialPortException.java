/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_comunicazione2;

import java.io.IOException;

/**
 *
 * @author thoma
 */
@SuppressWarnings("serial")
public class SerialPortException extends IOException {

    public SerialPortException() {
        super();
    }

    public SerialPortException(String message) {
        super(message);
    }

    public SerialPortException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerialPortException(Throwable cause) {
        super(cause);
    }
}
