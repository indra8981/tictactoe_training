package com.tictactoe.exceptions;

public class PositionOccupiedException extends Exception {
    public PositionOccupiedException() {
    }

    public PositionOccupiedException(String message) {
        super(message);
    }
}
