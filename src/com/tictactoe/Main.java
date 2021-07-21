package com.tictactoe;

import com.tictactoe.game.Game;
import com.tictactoe.game.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
//        Player player1=getPlayer(1);
//        Player player2=getPlayer(2);
//        Game game1=new Game(1,player1,player2);
//        Game game2=new Game(2,player1,player2);


        executor.submit(() -> {
text jm=new text();
jm.start();
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            game1.start();

//            game1.simulateGameTillNow();
        });
        executor.submit(() -> {
            text jm=new text();
            jm.start();
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            game2.start();
//            game2.simulateGameTillNow();
        });

        executor.shutdown();

    }

    private static Player getPlayer(int index) {
        Scanner in=new Scanner(System.in);
        System.out.print("Enter name for player"+index+": ");
        String name=in.nextLine();
        return new Player(name);
    }
}
