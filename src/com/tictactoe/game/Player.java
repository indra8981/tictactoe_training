package com.tictactoe.game;


import com.tictactoe.exceptions.GameIdNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;

        this.score=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public synchronized void  incrementScore(int delta) {
        this.score+=delta;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
