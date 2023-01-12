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

    public static void main(String[] args) {
        // initializes the class
        Main main = new Main();

        // scans every guess into allPossibleGuesses
        main.scanFile("words");

        String answer = main.words.get(main.randomInt(0, main.words.size())).toString();
        System.out.println(answer);

        JOptionPane.showMessageDialog(null,
                "Welcome to Wordle! \nThis game will test your vocabulary! \nG is used to indicate that the letter in the guess is in the correct place. \nY is used to indicate that the letter in the guess is in the word, but not in the right place. \n* is used to indicate that the letter in the guess is not in the word. ",
                "Tutorial", -1, null);
    }

    // input a file name and it will scan the file into an ArrayList
    public void scanFile(String file) {
        // arrayList to store all the possible wordle guesses
        ArrayList<String> allPossibleGuesses = new ArrayList<>();
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
                input = bufferedReader.readLine();
            }

            // closes stream
            fileReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            // No exception, no need to handle
        }
    }

    public void checkGuess(String guess) {
    }

    // returns a random int including the lowerBound and upperbound
    public int randomInt(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }
}
