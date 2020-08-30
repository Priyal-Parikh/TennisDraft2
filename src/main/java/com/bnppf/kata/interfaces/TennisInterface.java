package com.bnppf.kata.interfaces;

public interface TennisInterface {
    String getScore();

    String getSecondPlayerName();

    String getFirstPlayerName();

    void increaseAPointForFirstPlayer();

    int getFirstPlayerScore();

    void increaseAPointForSecondPlayer();

    int getSecondPlayerScore();
}
