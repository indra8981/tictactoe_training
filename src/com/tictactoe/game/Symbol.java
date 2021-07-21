package com.tictactoe.game;

public enum Symbol {
    BLANK("-"),
    CROSS("X"),
    CIRCLE("O");


    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
