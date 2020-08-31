package com.bnppf.kata.game;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.interfaces.TennisInterface;
import org.apache.log4j.Logger;

public class Tennis implements TennisInterface {
    private final Logger logger = Logger.getLogger(Tennis.class);

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
            logger.error(TennisConstants.TXT_INVALID_PLAYER);
            throw new TennisException(TennisConstants.TXT_INVALID_PLAYER);
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
        String score;
        String firstPlayerTennisScore = getTennisFormatScore(firstPlayerScore);
        String secondPlayerTennisScore = getTennisFormatScore(secondPlayerScore);

        if (firstPlayerTennisScore.equalsIgnoreCase(secondPlayerTennisScore)) {
            score = firstPlayerTennisScore + TennisConstants.TXT_SPACE + TennisConstants.TXT_ALL;
        } else {
            score = firstPlayerTennisScore + TennisConstants.TXT_COLON + secondPlayerTennisScore;
        }

        return score;
    }

    private boolean isValidPlayerName(String playerName) {
        return null != playerName && !"".equals(playerName) && (playerName.equalsIgnoreCase(firstPlayerName) || playerName.equalsIgnoreCase(secondPlayerName));
    }

    private String getTennisFormatScore(int points) {
        String tennisFormatScore = "";

        if (points == TennisConstants.POINT_ZERO) {
            tennisFormatScore = TennisConstants.SCORE_LOVE;
        } else if (points == TennisConstants.POINT_ONE) {
            tennisFormatScore = TennisConstants.SCORE_FIFTEEN;
        }

        return tennisFormatScore;
    }
}
