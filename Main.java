/*
 * Wordle Game
 * Li Ying 
 * Version 1.0.0 
 */

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

    public ArrayList<String> words = new ArrayList<String>();
    public String answer;

    public static void main(String[] args) {
        // initializes the class
        Main main = new Main();

        // 0 = yes
        // 1 = no
        int continueGame = 0;

        // scans every guess into words
        main.scanFile("words");

        while (continueGame == 0) {
            JOptionPane.showMessageDialog(null,
                    "Welcome to Wordle! \nThis game will test your vocabulary! \nG is used to indicate that the letter in the guess is in the correct place. \nY is used to indicate that the letter in the guess is in the word, but not in the right place. \n* is used to indicate that the letter in the guess is not in the word.",
                    "Tutorial", -1, null);

            // it will enter the if statement if you win
            // otherwise, it will enter the else statement
            if (main.wordleGame()) {
                JOptionPane.showMessageDialog(null,
                        "Congratulations! \nYou guessed the word!\n The word is " + main.answer, "End", -1, null);
            } else {
                JOptionPane.showMessageDialog(null,
                        "You did not guess the word. \nThe word is " + main.answer, "End", -1, null);
            }

            // yes = 0
            // no = 1
            continueGame = JOptionPane.showConfirmDialog(null, "Do you want to play again?", null,
                    JOptionPane.YES_NO_OPTION, -1, null);
        }
    }

    // return true if you win, returns false if you lose
    public boolean wordleGame() {
        // answer: the answer for the wordle
        // input: the guess the user enters in
        // result: A string of G, Y, and * to indicate what letters are correct.
        // allGuesses: A collection of guesses
        String input, result, allGuesses = "";

        answer = words.get(randomInt(0, words.size())).toString();
        System.out.println(answer);

        input = getGuess("Guess #1", "Wordle");
        if (input.equalsIgnoreCase(answer))
            return true;
        else {
            result = checkGuess(input);
            allGuesses = result;
        }
        // i: guess number
        for (int i = 2; i <= 6; i++) {
            input = getGuess("Guess #" + i + "\nPrevious Guesses:\n" + allGuesses, "Wordle");
            if (input.equalsIgnoreCase(answer))
                return true;
            else {
                result = checkGuess(input);
                allGuesses += "\n" + result;
            }

        }

        return false;
    }

    // input a file name and it will scan the file into an ArrayList
    public void scanFile(String file) {
        // added here so we don't have to readLine twice
        String input;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // this shouldn't throw an error because there is more than one value in the
            // list
            input = bufferedReader.readLine();
            while (input != null) {
                // adds the word to the list
                words.add(input);
                // reads the next line and writes it to input
                input = bufferedReader.readLine().toLowerCase();
            }

            // closes stream
            fileReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            // No exception, no need to handle
        }
    }

    public String checkGuess(String guess) {
        // start working here
        return "*****";
    }

    // returns a random int including the lowerBound and upperbound
    public int randomInt(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }

    public String getGuess(String message, String title) {
        String input;

        // first gets the input, and then we can go into the loop
        input = (String) JOptionPane.showInputDialog(null, message, title,
                -1, null, null, null);

        // if word not found, continue looping until you get a valid guess
        // the for(;;) makes an infinite loop until 'I' break it
        for (;;) {
            // if the guess is empty or not in list, indicate to the user that it is not a
            // valid guess
            if (input == null || input.isEmpty() || !words.contains(input.toLowerCase())) {
                JOptionPane.showMessageDialog(null, "Please enter a 5 letter word", "Invalid Guess", -1, null);
                input = JOptionPane.showInputDialog(null, message, title);
            } else
                break;

        }

        return input;
    }

}
