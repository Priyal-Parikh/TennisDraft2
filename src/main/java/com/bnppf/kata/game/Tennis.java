package com.bnppf.kata.game;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.interfaces.TennisInterface;

public class Tennis implements TennisInterface {
    private String firstPlayerName;
    private String secondPlayerName;
    private int firstPlayerScore;
    private int secondPlayerScore;

    public Tennis(String firstPlayerName , String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    @Override
    public void increasePlayerScore(String pointWinnerPlayer) {
        if (!isValidPlayerName(pointWinnerPlayer)) {
            throw new TennisException("Invalid Player Name");
        }

        if (pointWinnerPlayer.equalsIgnoreCase(firstPlayerName)) {
            firstPlayerScore++;
        } else {
            secondPlayerScore++;
        }
    }

    @Override
    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    @Override
    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public String getScore() {
        return TennisConstants.SCORE_LOVE + TennisConstants.TXT_SPACE + TennisConstants.TXT_ALL;
    }

    private boolean isValidPlayerName(String playerName) {
        return null != playerName && !"".equals(playerName) && (playerName.equalsIgnoreCase(firstPlayerName) || playerName.equalsIgnoreCase(secondPlayerName));
    }
}
