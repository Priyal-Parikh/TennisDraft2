package com.bnppf.kata;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.entities.TennisPlayer;
import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.game.Tennis;
import com.bnppf.kata.interfaces.TennisInterface;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class TennisSimulator {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(TennisSimulator.class);

    public static void main(String[] args) {
        TennisInterface tennis = startGameWithTwoPlayers();
        System.out.println("Start New Game!");

        while (!tennis.getScore().contains("Winner")) {
            System.out.print("Enter point winning player:");
            String pointScoredByPlayer = scanner.nextLine();
            try {
                tennis.increasePlayerScore(pointScoredByPlayer);
            } catch (TennisException e) {
                logger.error(TennisConstants.TXT_INVALID_PLAYER + e.getMessage());
                System.out.println("Exception occurred: " + e.getMessage());
            }
            System.out.println("*****************************************************");
            System.out.println(" " + tennis.getScore());
            System.out.println("*****************************************************");
        }

        System.out.println("Game over!");
        scanner.close();
    }

    private static TennisInterface startGameWithTwoPlayers() {
        String firstPlayer;
        String secondPlayer;

        do {
            System.out.println("Kindly enter two valid player names to start the game.");

            System.out.print("Player 1 name : ");
            firstPlayer = scanner.nextLine().trim();

            System.out.print("Player 2 name : ");
            secondPlayer = scanner.nextLine().trim();

        } while ("".equals(firstPlayer) || "".equals(secondPlayer) || firstPlayer.equalsIgnoreCase(secondPlayer));

        return new Tennis(new TennisPlayer(firstPlayer) , new TennisPlayer(secondPlayer));
    }
}