/*
 * Wordle-inspired Game
 * Version 1.3.5
 */

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JOptionPane;

public class Main {

    public ArrayList<String> words = new ArrayList<String>();
    public HashSet<String> wordsHashSet = new HashSet<String>();
    private String answer;

    // return true if you win, returns false if you lose
    public boolean wordleGame() {
        // answer: the answer for the wordle
        // input: the guess the user enters in
        // result: A string of G, Y, and * to indicate what letters are correct.
        // allGuesses: A collection of guesses
        String input, result, allGuesses = "";

        answer = words.get(randomInt(0, words.size())).toString();
        // this is for testing and creating a video
        System.out.println(answer);

        input = getGuess("Guess #1", "Wordle");
        if (input.equalsIgnoreCase(answer))
            return true;
        else {
            result = checkGuess(input);
            allGuesses = input + ": " + result;
        }
        // i: guess number
        for (int i = 2; i <= 6; i++) {
            input = getGuess("Guess #" + i + "\nPrevious Guesses:\n" + allGuesses, "Wordle");
            if (input.equalsIgnoreCase(answer))
                return true;
            else {
                result = checkGuess(input);
                allGuesses += "\n" + input + ": " + result;

            }

        }

        return false;
    }

    // input a file name and it will scan the file into an ArrayList
    public void scanFile(String file) throws Exception {
        // added here so we don't have to readLine twice
        try {
            String input;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // this shouldn't throw an error because there is more than one value in the
            // list
            input = bufferedReader.readLine();
            while (input != null) {
                // adds the word to the list
                words.add(input);
                // adds the word to the HashSet
                wordsHashSet.add(input);
                // reads the next line and writes it to input
                input = bufferedReader.readLine();
            }

            // closes stream
            fileReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            // nothing here
        }
    }

    private String checkGuess(String guess) {
        // answer in char array
        char[] answerCA = answer.toCharArray();
        // guess in char array
        char[] guessCA = guess.toCharArray();
        // output char array
        char[] result = new char[5];
        // fills array with "*"
        // I don't have to go later in the program to add it
        Arrays.fill(result, '*');

        // loops through the arrays and checks if the chars are at the same spot
        // if at the same spot, set "G" at the same index
        for (int i = 0; i < result.length; i++) {
            if (answerCA[i] == guessCA[i]) {
                result[i] = 'G';
                answerCA[i] = ' ';
                guessCA[i] = ' ';
            }
        }

        // checks for "Y" by comparing all the possible char indexes for both arrays
        // against each other
        for (int i = 0; i < result.length; i++) {
            if (guessCA[i] == ' ')
                continue;
            for (int j = 0; j < result.length; j++) {
                if (answerCA[j] == ' ')
                    continue;
                else if (guessCA[i] == answerCA[j]) {
                    result[i] = 'Y';
                    answerCA[j] = ' ';
                    break;
                }
            }
        }

        return String.valueOf(result);
    }

    /*
     * Stuff about "String.compareTo()""
     * An int value: 0 if the string is equal to the other string.
     * < 0 if first string is alphabetically first
     * > 0 if the first string is alphabetically later
     */

    /*
     * public int binarySearch(ArrayList<String> input, String search, int
     * lowerBound, int upperBound) {
     * int mid = (int) ((lowerBound + upperBound) / 2);
     * 
     * if (input.get(mid).equals(search)) {
     * return mid;
     * }
     * 
     * if (lowerBound == upperBound - 1) {
     * if (input.get(mid).equals(search)) {
     * return lowerBound;
     * } else if (input.get(upperBound).equals(search)) {
     * return upperBound;
     * } else
     * return -1;
     * }
     * 
     * // if the search is alphabetically before the mid of the list
     * if (input.get(mid).compareTo(search) > 0)
     * return binarySearch(input, search, lowerBound, mid);
     * else
     * return binarySearch(input, search, mid, upperBound);
     * 
     * }
     */

    public String getGuess(String message, String title) {
        String input;

        // first gets the input, and then we can go into the loop
        input = (String) JOptionPane.showInputDialog(null, message, title,
                -1, null, null, null);

        // if word not found, continue looping until you get a valid guess
        // the for(;;) makes an infinite loop until broken
        // doing this because I'm too lazy to make a boolean to check
        for (;;) {
            // if the guess is empty or not in the HashSet
            if (input == null || input.isEmpty() || !wordsHashSet.contains(input.toLowerCase())) {
                JOptionPane.showMessageDialog(null, "Please enter a 5 letter word",
                        "Invalid Guess", -1, null);
                input = (String) JOptionPane.showInputDialog(null, message, title,
                        -1, null, null, null);
            } else
                break;

        }

        return input.toLowerCase();
    }

    // returns a random int including the lowerBound and upperbound
    public int randomInt(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }

    public static void main(String[] args) throws Exception {
        // initializes the class
        Main main = new Main();

        // 0 = yes
        // 1 = no
        int continueGame = 0;

        // scans every guess into words
        // the words file is sorted file
        // the unsorted file is found here:
        // https://github.com/tabatkins/wordle-list/blob/main/words
        main.scanFile("words");

        while (continueGame == 0) {
            JOptionPane.showMessageDialog(null,
                    "Welcome to my game! \nThis game will test your vocabulary! \nEach word is 5 letters long. \nG is used to indicate that the letter in the guess is in the correct place. \nY is used to indicate that the letter in the guess is in the word, but not in the right place. \n* is used to indicate that the letter in the guess is not in the word.",
                    "Tutorial", -1, null);

            // it will enter the if statement if you win
            // otherwise, it will enter the else statement
            if (main.wordleGame()) {
                JOptionPane.showMessageDialog(null,
                        "Congratulations! \nYou guessed the word!\nThe word is " + main.answer, "End", -1, null);
            } else {
                JOptionPane.showMessageDialog(null,
                        "You did not guess the word. \nThe word is " + main.answer + ".", "End", -1, null);
            }

            // yes = 0
            // no = 1
            continueGame = JOptionPane.showConfirmDialog(null, "Do you want to play again?", null,
                    JOptionPane.YES_NO_OPTION, -1, null);
        }
    }
}
