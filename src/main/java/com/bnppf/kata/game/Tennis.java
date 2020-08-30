package com.bnppf.kata.game;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.interfaces.TennisInterface;

public class Tennis implements TennisInterface {
    private String firstPlayerName;
    private String secondPlayerName;
    private int firstPlayerScore;

    public Tennis(String firstPlayerName , String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    @Override
    public void increaseAPointForFirstPlayer() {
        firstPlayerScore++;
    }

    @Override
    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public String getScore() {
        return TennisConstants.SCORE_LOVE + TennisConstants.TXT_SPACE + TennisConstants.TXT_ALL;
    }
}
