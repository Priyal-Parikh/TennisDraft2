package com.bnppf.kata.interfaces;

import com.bnppf.kata.entities.TennisPlayer;

public interface TennisInterface {
    String getScore();

    void increasePlayerScore(String pointWinnerPlayer);

    TennisPlayer getFirstPlayer();

    TennisPlayer getSecondPlayer();
}
