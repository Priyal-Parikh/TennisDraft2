package com.bnppf.kata;

import com.bnppf.kata.entities.TennisPlayer;
import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.game.Tennis;
import com.bnppf.kata.interfaces.TennisInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TennisTest {
    public static final String TXT_LOVE = "Love";
    public static final String TXT_FIFTEEN = "Fifteen";
    public static final String TXT_ALL = " All";
    public static final String COLON = ":";
    public static final String FIRST_PLAYER_NAME = "Serena Williams";
    public static final String SECOND_PLAYER_NAME = "Maria Sharapova";
    public static final int ONE_POINT = 1;
    public static final String TXT_INVALID_PLAYER_NAME = "Invalid Player Name";
    public static final String TXT_RANDOM_PLAYER = "Random Player";
    TennisInterface tennis;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void initialSetup() {
        tennis = new Tennis(new TennisPlayer(FIRST_PLAYER_NAME) , new TennisPlayer(SECOND_PLAYER_NAME));
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennis);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(FIRST_PLAYER_NAME , tennis.getFirstPlayer().getName());
        Assert.assertEquals(SECOND_PLAYER_NAME , tennis.getSecondPlayer().getName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(TXT_LOVE + TXT_ALL , tennis.getScore());
    }

    @Test
    public void firstPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennis.increasePlayerScore(FIRST_PLAYER_NAME);

        Assert.assertEquals(ONE_POINT , tennis.getFirstPlayer().getPoints());
    }

    @Test
    public void secondPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennis.increasePlayerScore(SECOND_PLAYER_NAME);

        Assert.assertEquals(ONE_POINT , tennis.getSecondPlayer().getPoints());
    }

    @Test
    public void shouldNotAllowInvalidPlayerName() {
        exceptionRule.expect(TennisException.class);
        exceptionRule.expectMessage(TXT_INVALID_PLAYER_NAME);

        tennis.increasePlayerScore(TXT_RANDOM_PLAYER);
    }

    @Test
    public void scoreShouldBeLoveFifteenIfSecondPlayerScoresFirstPoint() {
        tennis.increasePlayerScore(SECOND_PLAYER_NAME);
        tennis.getScore();

        Assert.assertEquals(TXT_LOVE + COLON + TXT_FIFTEEN , tennis.getScore());
    }
}
