package com.bnppf.kata;

import com.bnppf.kata.game.Tennis;
import org.junit.Assert;
import org.junit.Test;

public class TennisTest {
    @Test
    public void initializeNewTennisGame() {
        Tennis tennis = new Tennis();

        Assert.assertNotNull(tennis);
    }
}
