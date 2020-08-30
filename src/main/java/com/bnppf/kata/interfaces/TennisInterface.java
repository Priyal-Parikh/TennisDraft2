package com.bnppf.kata.interfaces;

public interface TennisInterface {
    String getScore();

    String getSecondPlayerName();

    String getFirstPlayerName();

    void increasePlayerScore(String pointWinnerPlayer);

    int getFirstPlayerScore();

    int getSecondPlayerScore();
}
