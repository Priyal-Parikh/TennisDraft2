package com.bnppf.kata;

import com.bnppf.kata.entities.TennisPlayer;
import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.game.Tennis;
import com.bnppf.kata.interfaces.TennisInterface;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TennisTest {
    public static final String TXT_LOVE = "Love";
    public static final String TXT_FIFTEEN = "Fifteen";
    public static final String TXT_THIRTY = "Thirty";
    private static final String TXT_DEUCE = "Deuce";
    public static final String TXT_ALL = " All";
    private static final String TXT_ADVANTAGE = "Advantage";
    public static final String COLON = ":";
    public static final String FIRST_PLAYER_NAME = "Serena Williams";
    public static final String SECOND_PLAYER_NAME = "Maria Sharapova";
    public static final int ONE_POINT = 1;
    public static final int TWO_POINT = 2;
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

        Assert.assertEquals(TXT_LOVE + COLON + TXT_FIFTEEN , tennis.getScore());
    }

    @Test
    public void scoreShouldBeFifteenAllIfBothPlayerScoresFirstPoint() {
        prepareScore(ONE_POINT , ONE_POINT);

        Assert.assertEquals(TXT_FIFTEEN + TXT_ALL , tennis.getScore());
    }

    @Test
    @Parameters({
            "0, 0, Love All" ,
            "0, 1, Love:Fifteen" ,
            "0, 2, Love:Thirty" ,
            "0, 3, Love:Forty" ,
            "1, 0, Fifteen:Love" ,
            "1, 1, Fifteen All" ,
            "1, 2, Fifteen:Thirty" ,
            "1, 3, Fifteen:Forty" ,
            "2, 0, Thirty:Love" ,
            "2, 1, Thirty:Fifteen" ,
            "2, 2, Thirty All" ,
            "2, 3, Thirty:Forty" ,
            "3, 0, Forty:Love" ,
            "3, 1, Forty:Fifteen" ,
            "3, 2, Forty:Thirty"
    })
    public void scoreShouldBeAsPerParameters(int firstPlayerPoints , int secondPlayerPoints , String score) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(score , tennis.getScore());
    }

    @Test
    @Parameters({
            "4, 4" ,
            "5, 5" ,
            "15, 15" ,
            "26, 26"
    })
    public void checkForDeuceSituationInGame(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TXT_DEUCE , tennis.getScore());
    }

    @Test
    @Parameters({
            "3, 4" ,
            "4, 5" ,
            "5, 6" ,
            "6, 7" ,
            "14, 15" ,
            "27, 28"
    })
    public void checkForAdvantageSituationForPlayerTwo(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TXT_ADVANTAGE + COLON + SECOND_PLAYER_NAME , tennis.getScore());
    }

    @Test
    @Parameters({
            "4, 3" ,
            "5, 4" ,
            "6, 5" ,
            "7, 6" ,
            "15, 14" ,
            "22, 21"
    })
    public void checkForAdvantageSituationForFirstPlayer(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(TXT_ADVANTAGE + COLON + FIRST_PLAYER_NAME , tennis.getScore());
    }

    private void prepareScore(int firstPlayerPoints , int secondPlayerPoints) {
        for (int counter = 0; counter < firstPlayerPoints; counter++)
            tennis.increasePlayerScore(FIRST_PLAYER_NAME);
        for (int counter = 0; counter < secondPlayerPoints; counter++)
            tennis.increasePlayerScore(SECOND_PLAYER_NAME);
    }
}
