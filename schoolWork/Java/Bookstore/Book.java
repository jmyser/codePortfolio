/* Book.java
 * Book class represents objects with information about books.
 * @author Bob Kasper
 * for CSCI3113 Foundations of Computer Science 2
 */

package lab4;

public class Book
{
     private String title;
     private String author;
     private double price;

     /** default constructor
     */
     public Book( ) {
          title = "";
          author = "";
          price  = 0.0;
     }

     /** constructor with initial values
     *  @param newTitle   the value to assign to title
     *  @param newAuthor  the value to assign to author
     *  @param newPrice   the value to assign to price
     */
     public Book( String newTitle, String newAuthor, double newPrice ) {
          title = newTitle;
          author = newAuthor;
          price  = newPrice;
     }

     /* ACCESSOR METHODS */

     public String getTitle( ) {
          return title;
     }

     public String getAuthor( ) {
          return author;
     }

     public double getPrice( ) {
          return price;
     }

     /** toString
     * @return title, author, and price
     */
     public String toString( ) {
          String priceString = String.format("%1.2f", price);
          return ( "title: " + title + "\t"
                 + "author: " + author + "\t"
                 + "price: " + priceString );
     }
}
