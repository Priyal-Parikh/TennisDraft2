package com.bnppf.kata.game;

public class Tennis {
    private String firstPlayerName;
    private String secondPlayerName;

    public Tennis(String firstPlayerName , String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }
}
