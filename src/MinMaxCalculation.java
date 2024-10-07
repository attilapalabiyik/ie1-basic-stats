import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A simple class to compute min and max statistics.
 */
public class MinMaxCalculation {

  // Store all collected numbers
  private static ArrayList<Double> data = new ArrayList<Double>();

  public static void main(String ... args) {

    //Initialize controllers
    AddNumCtrl numCtrl = new AddNumCtrl();
    ResetCtrl resetCtrl = new ResetCtrl();

    //Initialize model
    Numbers numbers = new Numbers();

    //Initialize views
    MinView minView = new MinView();
    MaxView maxView = new MaxView();
    NumbersView numbersView = new NumbersView();
    AddNumView addNumView = new AddNumView();
    ResetView resetView = new ResetView();

    //Create statsView
    ArrayList<View> statsViews = new ArrayList<View>();
    statsViews.add(minView);
    statsViews.add(maxView);

    /***** REGISTER MVC BLOCK *****/

    //Register models to controls
    numCtrl.addModel(numbers);
    resetCtrl.addModel(numbers);

    //Register views to models
    for (View view: statsViews) {
      numbers.addObserver(view);
    }
    numbers.addObserver(numbersView);
    numbers.addObserver(addNumView);
    numbers.addObserver(resetView);

    //Register controls to views
    addNumView.addController(numCtrl);
    resetView.addController(resetCtrl);

    /***** END OF REGISTER MVC BLOCK *****/

    // Create the main frame of the application, and set size and position
    JFrame jfMain = new JFrame("Simple stats");
    jfMain.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    jfMain.setSize(1000,900);
    jfMain.setLocationRelativeTo(null);

    // Panel that shows stats about the numbers
    JPanel jpStats = new JPanel(new FlowLayout(FlowLayout.CENTER));

    for (View view: statsViews) {
      jpStats.add(new JLabel(view.getName() + ":"));
      jpStats.add(view.show());
    }

    //Set stats view
    jfMain.getContentPane().add(jpStats, BorderLayout.CENTER);

    // TextArea that shows all the numbers
    jfMain.getContentPane().add(numbersView.show(), BorderLayout.SOUTH);

    //Create panel for input
    JPanel jpInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
    jpInput.add(addNumView.show());
    jpInput.add(addNumView.getButton());
    jpInput.add(resetView.getButton());

    //Set input view
    jfMain.getContentPane().add(jpInput, BorderLayout.NORTH);

    // Show the frame
    jfMain.setVisible(true);

  }

    /**
     * Compute the minimum of an array of numbers.
     */
    public static double min(double ... numbers) {
        double min = numbers[0];
        for (double num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    /**
     * Compute the maximum of an array of numbers.
     */
    public static double max(double ... numbers) {
        double max = numbers[0];
        for (double num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    //Helper method
    public static double[] getArrayDouble(ArrayList<Double> doubles) {
          double[] result = new double[doubles.size()];

          //O(n) iteration to convert result
          for (int i = 0 ; i < doubles.size(); i++) {
              result[i] = doubles.get(i);
          }

          return result;
      }
}
