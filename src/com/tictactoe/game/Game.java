package com.tictactoe.game;

import com.tictactoe.exceptions.WrongChoiceException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static final Object lock = new Object();
    private Player player1, player2, winningPlayer;
    private final Symbol symbol1;
    private final Symbol symbol2;
    private ArrayList<Position> moves;
    private Board board;
    private int turnCount;
    private final int gameId;
    private int boardDimension;
    private boolean isDraw;
    private final Scanner in;

    // To move the moves and symbol inside the game so that its a property of game

    public Game(int gameId, Player player1, Player player2) {
        this.gameId = gameId;
        this.player1 = player1;
        this.player2 = player2;
        turnCount = 0;
        isDraw = false;
        moves = new ArrayList<>();
        symbol1 = Symbol.CROSS;
        symbol2 = Symbol.CIRCLE;
        in = new Scanner(System.in);
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }


    private void makeTossAndSetSymbol() {
        System.out.println("\nToss is going to take place for game " + gameId + ". \nRules of toss:\n\t1) Any of the player voluntarily will either choose 1 for " + player1 + " or 2 for " + player2 + ".\n\t2) Now a dice having value only 1 and 2 will roll and if the dice falls 1 then the player chosen previously will play as X and the other player will play as O else if dice falls 2 then vice versa will happen.");
        while (true) {
            try {
                System.out.print("Any one of the player please choose a number. Either 1/2: ");
                int choice = in.nextInt();
                if (choice < 1 || choice > 2) {
                    throw new WrongChoiceException("Wrong choice! Please try again.");
                }
                choice--;
                int random = getRandomNumber(0, 100) % 2;
                if ((choice == 0 && random == 1) || (choice == 1 && random == 0)) {
                    Player temp = player1;
                    player1 = player2;
                    player2 = temp;
                }
                System.out.println("\n" + player1 + " won the toss! and will play 1st with symbol X and " + player2 + " will play as O");
                break;
            } catch (WrongChoiceException e) {
                System.out.println(e);
            }
        }
    }

    private void getAndSetBoardDimensions() {
        while (true) {
            try {
                System.out.print("Enter Dimension of board (Minimum 3) for game with game " + gameId + ": ");
                boardDimension = in.nextInt();
                if (boardDimension < 3)
                    throw new WrongChoiceException("Dimension must be atleast 3. Please try again!");
                board = new Board(boardDimension);
                break;
            } catch (WrongChoiceException e) {
                System.out.println(e);
            }
        }
    }

    public Player getCurrentPlayer() {
        return turnCount % 2 == 0 ? player1 : player2;
    }

    public Player getPlayerForTurn(int turnCount) {
        return turnCount % 2 == 0 ? player1 : player2;
    }

    public Symbol getCurrentSymbol() {
        return turnCount % 2 == 0 ? symbol1 : symbol2;
    }

    public Symbol getSymbolForTurn(int turnCount) {
        return turnCount % 2 == 0 ? symbol1 : symbol2;
    }


    public void printAllMoves() {
        try {
            System.out.println("All Moves for game " + gameId + " :-");
            for (int i = 0; i < turnCount; i++) {
                Player currentPlayer = getPlayerForTurn(i);
                Symbol currentSymbol = getSymbolForTurn(i);
                Position currentMove = moves.get(i);
                printIthMove(currentPlayer, currentSymbol, i, currentMove);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void printIthMove(Player currentPlayer, Symbol currentSymbol, int turnCount, Position currentMove) {
        System.out.println("Player " + currentPlayer + " ,Symbol " + currentSymbol + ", Move : " + (turnCount + 1) + " " + currentMove);
    }

    public void simulateGameTillNow() {
        try {
            System.out.println("Simulating Game " + gameId + " :-");
            Board boardForSimulation = new Board(boardDimension);
            Position position;
            Player currentPlayer;
            Symbol currentSymbol;
            for (int i = 0; i < turnCount; i++) {
                position = moves.get(i);
                currentPlayer = getPlayerForTurn(i);
                currentSymbol = getSymbolForTurn(i);
                System.out.println();
                printIthMove(currentPlayer, currentSymbol, i, position);
                System.out.println();
                boardForSimulation.move(position, currentSymbol);
                boardForSimulation.printCurrentBoardState();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addMove(Position movePosition) {
        moves.add(movePosition);
    }

    void printScoreOfPlayers() {
        System.out.println(player1 + " score=" + player1.getScore());
        System.out.println(player2 + " score=" + player2.getScore());
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public ArrayList<Position> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Position> moves) {
        this.moves = moves;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }


    public void start() {
        synchronized (lock) {
            getAndSetBoardDimensions();
        }
        synchronized (lock) {
            makeTossAndSetSymbol();
            System.out.println("\nGame Rules: ");
            System.out.println("1) The Board is represented as " + boardDimension + " x " + boardDimension + " cells where the top left cell is 1, 1 and bottom right cell is " + boardDimension + ", " + boardDimension);
            System.out.println("2) Each player should enter the position as row and column of their move in their turn.\n");
            System.out.println("Starting the game!");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                try {
                    System.out.println("Game Id:- " + gameId);
                    Player currentPlayer = getCurrentPlayer();
                    Symbol currentSymbol = getCurrentSymbol();
                    System.out.println(currentPlayer + " this is your move. Your symbol is " + currentSymbol);
                    System.out.println("Current Board:-");
                    board.printCurrentBoardState();
                    System.out.println("Enter space separated row and column");
                    int xPos = in.nextInt();
                    int yPos = in.nextInt();
                    Position movePosition = new Position(xPos, yPos);
                    board.move(movePosition, currentSymbol);
                    addMove(movePosition);
                    turnCount++;
                    if (board.isCurrentMoveWinning(movePosition)) {
                        System.out.println(currentPlayer + " won the match! Congratulations !!\n");
                        printAllMoves();
                        System.out.println("Current Board");
                        board.printCurrentBoardState();
                        winningPlayer = currentPlayer;
                        currentPlayer.incrementScore(1);
                        printScoreOfPlayers();
                        break;
                    }
                    if (board.isDraw()) {
                        System.out.println("The match is Draw!");
                        printAllMoves();
                        System.out.println("Current Board");
                        board.printCurrentBoardState();
                        isDraw = true;
                        printScoreOfPlayers();
                        break;
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }


}
