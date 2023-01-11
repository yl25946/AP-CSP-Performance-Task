/*
 * Program that can numerically integrate using trapezoidal sums. 
 * Additional add-on to add manual trapezoidal sum calculator
 */

import javax.swing.JOptionPane;

public class IntegralCalculator {
    public static void main(String[] args) {
        long lowerBound, upperBound;
        char variable;
        String equation;
        long output;

        // instantiates object
        IntegralCalculator main = new IntegralCalculator();

        // gets input from user
        equation = JOptionPane.showInputDialog(null, "Enter the equation you want to integrate:");
        // parses int for all the int
        lowerBound = main.getLong("Enter the lower bound:");
        upperBound = main.getLong("Enter the upper bound");

    }

    public long getLong(String message) {
        // checks if we have an integer, starts true so the loop will start
        boolean noInt = true;

        // what the function will return
        long output = 0;

        // variable to store input to increase readability
        String input = "";

        while (noInt) {
            try {
                input = JOptionPane.showInputDialog(null, message);
                output = Integer.parseInt(input);
                noInt = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a number");
            }
            // checks if the input is empty
            // if so, continue the loop
            if (input == null)
                noInt = true;
        }

        return output;

    }

}
