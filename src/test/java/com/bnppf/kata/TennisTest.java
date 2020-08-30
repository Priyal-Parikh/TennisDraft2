package com.bnppf.kata;

import com.bnppf.kata.game.Tennis;
import com.bnppf.kata.interfaces.TennisInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TennisTest {
    public static final String TXT_LOVE = "Love";
    public static final String TXT_ALL = " All";
    public static final String FIRST_PLAYER_NAME = "Serena Williams";
    public static final String SECOND_PLAYER_NAME = "Maria Sharapova";
    public static final int ONE_POINT = 1;
    TennisInterface tennis;

    @Before
    public void initialSetup() {
        tennis = new Tennis(FIRST_PLAYER_NAME , SECOND_PLAYER_NAME);
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennis);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(FIRST_PLAYER_NAME , tennis.getFirstPlayerName());
        Assert.assertEquals(SECOND_PLAYER_NAME , tennis.getSecondPlayerName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(TXT_LOVE + TXT_ALL , tennis.getScore());
    }

    @Test
    public void firstPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennis.increaseAPointForFirstPlayer();

        Assert.assertEquals(ONE_POINT , tennis.getFirstPlayerScore());
    }

    @Test
    public void secondPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennis.increaseAPointForSecondPlayer();

        Assert.assertEquals(ONE_POINT , tennis.getSecondPlayerScore());
    }
}
