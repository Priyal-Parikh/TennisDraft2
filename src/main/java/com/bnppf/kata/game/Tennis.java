package com.bnppf.kata.game;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.entities.TennisPlayer;
import com.bnppf.kata.enums.TennisScoreEnum;
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

        if (isDeuce()) {
            score = TennisConstants.SCORE_DEUCE;
        } else if (secondPlayer.getPoints() - firstPlayer.getPoints() == TennisConstants.POINT_ONE && (secondPlayer.getPoints() > TennisConstants.POINT_THREE || firstPlayer.getPoints() > TennisConstants.POINT_THREE)) {
            return TennisConstants.SCORE_ADVANTAGE + TennisConstants.TXT_COLON + secondPlayer.getName();
        } else {
            score = formatScore();
        }

        return score;
    }

    private String formatScore() {
        TennisScoreEnum firstPlayerTennisScore = getTennisFormatScore(firstPlayer.getPoints());
        TennisScoreEnum secondPlayerTennisScore = getTennisFormatScore(secondPlayer.getPoints());

        return isBothPlayersScoredSame() ?
                firstPlayerTennisScore.getScore() + TennisConstants.TXT_SPACE + TennisConstants.TXT_ALL :
                firstPlayerTennisScore.getScore() + TennisConstants.TXT_COLON + secondPlayerTennisScore.getScore();
    }

    private boolean isDeuce() {
        return isBothPlayersScoredSame() && isAnyPlayerBeyondThirty();
    }

    private boolean isAnyPlayerBeyondThirty() {
        return firstPlayer.getPoints() > TennisConstants.POINT_TWO || secondPlayer.getPoints() > TennisConstants.POINT_TWO;
    }

    private boolean isBothPlayersScoredSame() {
        return firstPlayer.getPoints() == secondPlayer.getPoints();
    }

    private boolean isValidPlayerName(String playerName) {
        return null != playerName && !"".equals(playerName) && (playerName.equalsIgnoreCase(firstPlayer.getName()) || playerName.equalsIgnoreCase(secondPlayer.getName()));
    }

    private TennisScoreEnum getTennisFormatScore(int points) {
        return TennisScoreEnum.fromScore(points);
    }
}
