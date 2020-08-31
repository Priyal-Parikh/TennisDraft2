package com.bnppf.kata;

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
        playGame(tennis);
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

    private static void playGame(TennisInterface tennis) {
        System.out.println("\n Start New Game!");

        try {
            while (!tennis.getScore().contains("Winner")) {
                System.out.print("Enter point winning player:");
                String pointScoredByPlayer = scanner.nextLine();
                try {
                    tennis.increasePlayerScore(pointScoredByPlayer);
                } catch (TennisException e) {
                    logger.error(e.getMessage());
                }
                printScoreBoard(tennis);
            }
        } catch (TennisException e) {
            logger.error(e.getMessage());
        }

        System.out.println("Game over!");
    }

    private static void printScoreBoard(TennisInterface tennis) {
        System.out.println("#####################################################");
        System.out.println(" " + tennis.getScore());
        System.out.println("#####################################################");
    }
}