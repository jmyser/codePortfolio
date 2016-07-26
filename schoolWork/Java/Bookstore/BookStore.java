/* BookStore.java
 * main class for CSCI3113 Lab 4
 * represents the inventory of books in a store using an ArrayList
 * provides search methods for to find books with different criteria.
 */

package lab4;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class BookStore
{
     private ArrayList<Book> inventory;

     /** default constructor
     *   instantiates ArrayList of Books
     */
     public BookStore( ) {
          inventory = new ArrayList<>( );
     }

     /** toString
     *  @return  information for all books in inventory, one per line
     */
     public String toString( ) {
          String result = "";
          for(Book tempBook : inventory ) {
              result += tempBook.toString( ) + "\n";
          }
          return result;
     }

    /* readBookData
     * initializes the book store inventory by reading a CSV file.
     */
    public void readBookData() {
        String fileName = "BookstoreData.csv";
        try
        {
            Scanner inputStream = new Scanner(new File(fileName));
            // Read the header line
            String line = inputStream.nextLine();
            // Read the rest of the file line by line
            while (inputStream.hasNextLine())
            {
                // format of each line: Title, Author, Price
                line = inputStream.nextLine();
                // Turn the string into an array of strings
                String[] ary = line.split(",");
                // Extract each item
                String title = ary[0];
                String author = ary[1];
                double price = Double.parseDouble(ary[2]);
                // TO DO (Lab): create a Book object, and add it to inventory list
                inventory.add(new Book(title, author, price));
            }
            inventory.trimToSize();
            inputStream.close( );
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Cannot find file " + fileName);
        }
        catch(IOException e)
        {
            System.out.println("Problem with input from file " + fileName);
        }
    }

     public static void main( String [] args ) {
         // create book store object and read the inventory file
         BookStore store = new BookStore( );
         store.readBookData();
         // print a list showing the entire inventory
         // You may comment this out when testing your search methods.
         //System.out.println( "The entire inventory has " + store.inventory.size() + " books:");
         //System.out.println( store.toString( ) );

         Scanner keyboard = new Scanner(System.in);
         System.out.print("What kind of search would you like?\n"
                  + "\tsearch by title (enter t)\n"
                  + "\tsearch by author (enter a)\n"
                  + "\tlowest price (enter l)\n"
                  + "\tsearch below a price (enter b)\n"
                  + "Enter search type: ");
         String searchType = keyboard.nextLine();
         
         // determine search type and invoke appropriate method
         if (searchType.startsWith("t")) {
              System.out.print("Enter search string: ");
              String search = keyboard.nextLine();
              // invoke the search method and report results
              System.out.println("Found " + store.searchForTitle(search).size() + " books containing " + search);
              for(Book tempBook : store.searchForTitle(search) ) {
                System.out.println(tempBook.toString());
              }    
         }
         else if (searchType.startsWith("a")) {
              System.out.print("Enter search string: ");
              String search = keyboard.nextLine();
              // invoke the search method and report results
              System.out.println("Found " + store.searchForAuthor(search).size() + " books containing " + search);
              for(Book tempBook : store.searchForAuthor(search) ) {
                System.out.println(tempBook.toString());
              }    
         }
         else if (searchType.startsWith("l")) {
            System.out.println(store.lowestPrice().toString());
         }
         else if (searchType.startsWith("b")) {
              System.out.print("Search for books below price: ");
              double search = keyboard.nextDouble();
              // invoke the search method and report results
              System.out.println("Found " + store.searchForPriceBelow(search).size() + " books below " + search);
              for(Book tempBook : store.searchForPriceBelow(search) ) {
                System.out.println(tempBook.toString());
              }    
         }
         else
             System.out.println("Unknown search method: " + searchType);
     }
     
     // TO DO (Lab): define searchForTitle method
     public ArrayList<Book> searchForTitle(String s) {
         ArrayList<Book> returnList = new ArrayList<>();
         for(Book tempBook : inventory ) {
            if (tempBook.getTitle().indexOf(s) > -1)
                returnList.add(tempBook);
         }
         return returnList;
     }
     // searchForAuthor
     public ArrayList<Book> searchForAuthor(String s) {
         ArrayList<Book> returnList = new ArrayList<>();
         for(Book tempBook : inventory ) {
            if (tempBook.getAuthor().indexOf(s) > -1)
                returnList.add(tempBook);
         }
         return returnList;
     }
     // lowestPrice
     public Book lowestPrice() {
        int i = 0;
        int lowPriceIndex = 0;
        while (i < inventory.size()) {
            if (inventory.get(i).getPrice() < inventory.get(lowPriceIndex).getPrice())
                    lowPriceIndex = i;
            i++;
        }
         return inventory.get(lowPriceIndex);
     }
     // searchForPriceBelow
     public ArrayList<Book> searchForPriceBelow(double p) {
         ArrayList<Book> returnList = new ArrayList<>();
         for(Book tempBook : inventory ) {
            if (tempBook.getPrice() < p)
                returnList.add(tempBook);
         }
         return returnList;
     }
}
