package com.tictactoe.exceptions;

public class OutOfBoardException extends Exception {
    public OutOfBoardException() {
    }

    public OutOfBoardException(String message) {
        super(message);
    }
}
