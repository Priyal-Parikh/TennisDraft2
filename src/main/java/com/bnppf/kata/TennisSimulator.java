package com.bnppf.kata;

import com.bnppf.kata.entities.TennisPlayer;
import com.bnppf.kata.game.Tennis;
import com.bnppf.kata.interfaces.TennisInterface;

import java.util.Scanner;

public class TennisSimulator {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TennisInterface tennis = startGameWithTwoPlayers();
        System.out.println("Starting Game Score :" + tennis.getScore());
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