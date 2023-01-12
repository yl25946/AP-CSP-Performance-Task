import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public ArrayList<String> allPossibleGuesses = new ArrayList<String>();

    public static void main(String[] args) {
        // initializes the class
        Main main = new Main();

        // scans every guess into allPossibleGuesses
        main.scanFile("C:\\Users\\gamin\\OneDrive\\Desktop\\Java Stuff\\AP-CSP-Performance-Task\\words");
    }

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
                allPossibleGuesses.add(input);
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
}
