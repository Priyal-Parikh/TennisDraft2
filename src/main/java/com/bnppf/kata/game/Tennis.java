package com.bnppf.kata.game;

import com.bnppf.kata.constants.TennisConstants;

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

    public String getScore() {
        return TennisConstants.SCORE_LOVE+TennisConstants.TXT_SPACE+TennisConstants.TXT_ALL;
    }
}
