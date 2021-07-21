package com.tictactoe.exceptions;

/**
 * User: indrajit.s
 * Date: 17/07/21 4:50 PM
 */
public class WrongChoiceException extends Exception {
    public WrongChoiceException() {
    }

    public WrongChoiceException(String message) {
        super(message);
    }
}
