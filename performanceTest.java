import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

public class performanceTest {

    public ArrayList<String> words = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        performanceTest test = new performanceTest();

        test.scanFile("words");

        test.test();
    }

    public void test() throws Exception{
        int randomIndex = randomInt(0,words.size() -1);

        long startTime = System.nanoTime();
        binarySearch(words, words.get(randomIndex), 0, words.size() - 1);
        System.out.println((System.nanoTime() - startTime) / 1000000);

        startTime = System.nanoTime()
        words.indexOf(words.get(randomIndex));
        System.out.println((System.nanoTime() - startTime) / 1000000);
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

    public int binarySearch(ArrayList<String> input, String search, int lowerBound, int upperBound) {
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
        String input;
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
    }

}
