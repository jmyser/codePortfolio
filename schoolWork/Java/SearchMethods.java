/* SearchMethods.java
 */

import java.util.*;

public class SearchMethods {
    // TO DO: declare static variables to count repetitions of the loops
    // for each search
    static int reps = 0;

  /* linear search method for finding a key in an integer array
   * from Listing 7.6
   */
  public static int linearSearch(int[] list, int key) {
    for (int i = 0; i < list.length; i++) {
        reps++;
        if (key == list[i])
        return i;
    }
    return -1;
  }
  
  /* binary search to find a key in an integer array
   * from Listing 7.7
   */
  public static int binarySearch(int[] list, int key) {
    int low = 0;
    int high = list.length - 1;

    while (high >= low) {
      reps++;
      int mid = (low + high) / 2;
      if (key < list[mid])
        high = mid - 1;
      else if (key == list[mid])
        return mid;
      else
        low = mid + 1;
    }
    return -low - 1; // return negative number for failure
  }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter array size: ");
        int size = keyboard.nextInt();
        System.out.println("Enter number of searches: ");
        int trials = keyboard.nextInt();
        
        int[] anArray = new int[size];
        
        // set each element to a random value between 0 and size
        
        for (int i = 0; i < size; i++) {
            anArray[i] = (int)(Math.random() * (size + 1));
        }
        
        // TO DO: sort the array using library method
        
        
        Arrays.sort(anArray);
        
        
        // TO DO: perform the number of searches given trials
        
        int linearRepsTotal = 0;
        int binaryRepsTotal = 0;
        
        for (int t = 0; t < trials; t++){
            // TO DO for each trial:
            // generate random search key
            int randKey = (int)(Math.random() * (size + 1));
            // invoke linear search
            reps = 0;
            linearSearch(anArray, randKey);
            linearRepsTotal = linearRepsTotal + reps;
            
            // invoke binary search
            reps = 0;
            binarySearch(anArray, randKey);
            binaryRepsTotal = binaryRepsTotal + reps;
        }
            
        
        // TO DO: report average number of repetitions per trial
        // for both search algorithm
        System.out.println("Average number of reps, linearSearch: " + linearRepsTotal/trials);
        System.out.println("Average number of reps, binarySearch: " + binaryRepsTotal/trials);
        
    }
}
