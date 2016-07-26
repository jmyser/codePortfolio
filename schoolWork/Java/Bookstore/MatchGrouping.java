/* MatchGrouping.java
 * main class for CSCI3113 Lab 4b
 * program to check whether a Java source file has correct pairs of grouping symbols.
 */

package lab4;

import java.util.*;
import java.io.*;

public class MatchGrouping {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Enter input file name: ");
    String fname = keyboard.nextLine();
    
    // TO DO: declare variables for stack of characters, and a line counter
    Stack<Character> delimiterStack = new Stack<>();
    int lineCounter = 0;
    char c = 0;
    
    try {
      Scanner input = new Scanner(new File(fname));
      while (input.hasNextLine()) {
            String line = input.nextLine();
            lineCounter++;
            boolean breakTest = false;
            
            for (int i = 0; i < line.length(); i++) {
                c = line.charAt(i); // c is next character from input file
                
                // a left delimiter character should be pushed on the stack,
                if (c == '(' || c == '{' || c == '[')
                    delimiterStack.push(c);
                // a right delimiter character should match the most 
                // recent left delimiter
                // by popping the stack.
                if (c == ')' || c == '}' || c == ']'){
                    switch (delimiterStack.pop()) {
                        case '(': 
                            if (c != ')') {
                                System.out.println("unbalanced: " + c + " found on line " + lineCounter + " where ) expected.");
                                input.close();
                                breakTest = true;
                            }
                            break;
                        case '{': 
                            if (c != '}') {
                                System.out.println("unbalanced: " + c + " found on line " + lineCounter + " where } expected.");
                                input.close();
                                breakTest = true;
                            }
                            break;
                        case '[': 
                            if (c != ']') {
                                System.out.println("unbalanced: " + c + " found on line " + lineCounter + " where ] expected.");
                                input.close();
                                breakTest = true;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            if (breakTest){
                break;
            }
      }

      // end of input file has been reached
      // TO DO: Check if any extra delimiters remain on the stack.

      if (delimiterStack.empty()){
          System.out.println("Processed " + lineCounter + " lines with no errors");
      }
      else {
          switch (delimiterStack.pop()) {
              case '(':
                  System.out.println("unbalanced: missing ) at end of file.");
                  break;
              case '{':
                  System.out.println("unbalanced: missing } at end of file.");
                  break;
              case '[':
                  System.out.println("unbalanced: missing ] at end of file.");
                  break;    
              default:
                  break;
          }
          
      }

    }
    
    
    catch (FileNotFoundException ex) {
        System.out.println("Error: could not open file: " + fname);
    }
    catch (EmptyStackException ex) {
        System.out.println("unbalanced: extra " + c + " found on line " + lineCounter);
    }
  }
}
