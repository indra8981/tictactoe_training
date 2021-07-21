package com.tictactoe.game;

import com.tictactoe.exceptions.OutOfBoardException;
import com.tictactoe.exceptions.PositionOccupiedException;

import java.util.Arrays;

public class Board {
    private int DIMENSION;
    private Symbol[][] board;

    public Board(int DIMENSION) {
        this.DIMENSION = DIMENSION;
        board = new Symbol[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            Arrays.fill(board[i], Symbol.BLANK);
        }
    }



    public void printCurrentBoardState() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void move(Position position, Symbol symbol) throws OutOfBoardException, PositionOccupiedException {
        if (position.getX() > (DIMENSION) || position.getY() > (DIMENSION)) {
            throw new OutOfBoardException(position + " is out of board. Current board size is " + (DIMENSION) + " x " + (DIMENSION));
        }

        if (board[position.getX() - 1][position.getY() - 1] != Symbol.BLANK) {
            throw new PositionOccupiedException(position + " is already occupied!");
        }
        board[position.getX() - 1][position.getY() - 1] = symbol;
    }

    public boolean isCurrentMoveWinning(Position position) throws OutOfBoardException {
        if (position.getX() > (DIMENSION) || position.getY() > (DIMENSION)) {
            throw new OutOfBoardException(position + " is out of board. Current board size is " + (DIMENSION) + " x " + (DIMENSION));
        }
        int rowCount = 0, columnCount = 0, diagonalCount = 0, reverseDiagonalCount = 0;
        Symbol symbol = board[position.getX() - 1][position.getY() - 1];
        if (symbol == Symbol.BLANK)
            return false;
        for (int i = 0; i < DIMENSION; i++) {
            if (board[position.getX() - 1][i] == symbol)
                rowCount++;
            if (board[i][position.getY() - 1] == symbol)
                columnCount++;
            if (board[i][i] == symbol)
                diagonalCount++;
            if (board[i][DIMENSION - i - 1] == symbol)
                reverseDiagonalCount++;
        }
        return (rowCount == DIMENSION || columnCount == DIMENSION || diagonalCount == DIMENSION || reverseDiagonalCount == DIMENSION);
    }

    public boolean isDraw(){
        for(int i=0;i<DIMENSION;i++){
            for(int j=0;j<DIMENSION;j++){
                if(board[i][j]==Symbol.BLANK)
                    return false;
            }
        }
        return true;
    }

}
