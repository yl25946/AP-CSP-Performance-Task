import java.util.ArrayList;
import java.util.HashSet;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class performanceTest {

    public final String answer = "forte";

    public ArrayList<String> words = new ArrayList<String>();
    public HashSet<String> wordsHashSet = new HashSet<String>();

    public static void main(String[] args) throws Exception {
        performanceTest test = new performanceTest();

        test.scanFile("words");

        test.test(1000000);

        System.out.println(test.checkGuess("forts"));
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

    public void test(int times) throws Exception {
        long hashSetTime = 0, binarySearchTime = 0;

        for (int i = 0; i < times; i++) {
            int randomIndex = randomInt(0, words.size() - 1);

            long startTime = System.nanoTime();
            binarySearch(words, words.get(randomIndex), 0, words.size() - 1);
            binarySearchTime += System.nanoTime() - startTime;

            startTime = System.nanoTime();
            wordsHashSet.contains(words.get(randomIndex));
            hashSetTime += System.nanoTime() - startTime;
        }

        System.out.println("Average time for Hashset is: " + (hashSetTime / times));
        System.out.println("Average time for binarySearch is: " + (binarySearchTime / times));
    }

    // returns a random int including the lowerBound and upperbound
    public int randomInt(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }

    /*
     * Stuff about "String.compareTo()""
     * An int value: 0 if the string is equal to the other string.
     * < 0 if first string is alphabetically first
     * > 0 if the first string is alphabetically later
     */

    public int binarySearch(ArrayList<String> input, String search, int lowerBound, int upperBound) throws Exception {
        int mid = (int) ((lowerBound + upperBound) / 2);

        if (input.get(mid).equals(search)) {
            return mid;
        }

        if (lowerBound == upperBound - 1) {
            if (input.get(mid).equals(search)) {
                return lowerBound;
            } else if (input.get(upperBound).equals(search)) {
                return upperBound;
            } else
                return -1;
        }

        // if the search is alphabetically before the mid of the list
        if (input.get(mid).compareTo(search) > 0)
            return binarySearch(input, search, lowerBound, mid);
        else
            return binarySearch(input, search, mid, upperBound);

    }

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
                wordsHashSet.add(input);
                // reads the next line and writes it to input
                input = bufferedReader.readLine().toLowerCase();
            }

            // closes stream
            fileReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            // nothing here
        }
    }

}