package com.tictactoe.exceptions;

/**
 * User: indrajit.s
 * Date: 17/07/21 4:13 PM
 */
public class GameIdNotFoundException extends Exception {
    public GameIdNotFoundException() {
    }

    public GameIdNotFoundException(String message) {
        super(message);
    }
}
