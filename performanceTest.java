import java.util.ArrayList;
import java.util.HashSet;
import java.io.FileReader;
import java.io.BufferedReader;

public class performanceTest {

    public ArrayList<String> words = new ArrayList<String>();
    public HashSet<String> wordsHashSet = new HashSet<String>();

    public static void main(String[] args) throws Exception {
        performanceTest test = new performanceTest();

        test.scanFile("words");

        test.test(1000000);
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