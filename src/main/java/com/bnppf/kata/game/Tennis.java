package com.bnppf.kata.game;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.entities.TennisPlayer;
import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.interfaces.TennisInterface;
import org.apache.log4j.Logger;

public class Tennis implements TennisInterface {
    private final Logger logger = Logger.getLogger(Tennis.class);

    private TennisPlayer firstPlayer;
    private TennisPlayer secondPlayer;

    public Tennis(TennisPlayer firstPlayer , TennisPlayer secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    @Override
    public void increasePlayerScore(String pointWinnerPlayer) {
        if (!isValidPlayerName(pointWinnerPlayer)) {
            logger.error(TennisConstants.TXT_INVALID_PLAYER);
            throw new TennisException(TennisConstants.TXT_INVALID_PLAYER);
        }

        if (pointWinnerPlayer.equalsIgnoreCase(firstPlayer.getName())) {
            firstPlayer.setPoints(firstPlayer.getPoints() + TennisConstants.POINT_ONE);
        } else {
            secondPlayer.setPoints(secondPlayer.getPoints() + TennisConstants.POINT_ONE);
        }
    }

    @Override
    public TennisPlayer getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public TennisPlayer getSecondPlayer() {
        return secondPlayer;
    }

    @Override
    public String getScore() {
        String score;
        String firstPlayerTennisScore = getTennisFormatScore(firstPlayer.getPoints());
        String secondPlayerTennisScore = getTennisFormatScore(secondPlayer.getPoints());

        if (firstPlayerTennisScore.equalsIgnoreCase(secondPlayerTennisScore)) {
            score = firstPlayerTennisScore + TennisConstants.TXT_SPACE + TennisConstants.TXT_ALL;
        } else {
            score = firstPlayerTennisScore + TennisConstants.TXT_COLON + secondPlayerTennisScore;
        }

        return score;
    }

    private boolean isValidPlayerName(String playerName) {
        return null != playerName && !"".equals(playerName) && (playerName.equalsIgnoreCase(firstPlayer.getName()) || playerName.equalsIgnoreCase(secondPlayer.getName()));
    }

    private String getTennisFormatScore(int points) {
        String tennisFormatScore = "";

        if (points == TennisConstants.POINT_ZERO) {
            tennisFormatScore = TennisConstants.SCORE_LOVE;
        } else if (points == TennisConstants.POINT_ONE) {
            tennisFormatScore = TennisConstants.SCORE_FIFTEEN;
        } else if (points == TennisConstants.POINT_TWO) {
            tennisFormatScore = TennisConstants.SCORE_THIRTY;
        }


        return tennisFormatScore;
    }
}
