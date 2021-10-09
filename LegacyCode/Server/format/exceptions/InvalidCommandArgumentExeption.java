package format.exceptions;

import java.io.Serializable;

/**
 * Thrown to indicate that invalid arguments are given
 */
public class InvalidCommandArgumentExeption extends Exception implements Serializable {

    /**
     * Exceptions' default constructor
     *
     * @param message - this message will pop out in System.out stream if an exception
     */
    public InvalidCommandArgumentExeption(String message) {
        super(message);
    }
}
