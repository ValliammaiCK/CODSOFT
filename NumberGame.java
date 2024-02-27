/*
 * Number Guessing Game in Java
 * 
 * This program implements a number guessing game where the user has to guess a randomly generated number within a specified range.
 * The user is given a limited number of attempts to guess the number correctly.
 * The program allows for multiple rounds of play and displays the user's score.
 * 
 * Adapted from a common algorithm for a number guessing game.
 * Original concept inspired by tutorials and resources found online.
 */

import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attemptsLimit = 7;
        boolean playAgain = true;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.print("Enter your name: ");
        String userName = sc.nextLine();


        while (playAgain) {
            int generatedNumber = ran.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("\nHELLO,"+userName+"! I have generated a number between " + minRange + " and " + maxRange + ". Guess what it is!");

            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < attemptsLimit) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations "+userName+"! You've guessed the number correctly in " + attempts + " attempts.");
                    score += attempts;
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("I'm sorry,"+userName+" but you're out of attempts! That number was: " + generatedNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = sc.next().toLowerCase();
            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Game over "+userName+"! Your total score is: " + score);
        sc.close();
    }
}
