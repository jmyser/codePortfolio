/* BankCard.java
 * base class for CreditCard and DebitCard.
 * @author rkasper
 */
package lab5;
import java.util.Scanner;
import java.util.ArrayList;

public class BankCard {
    // instance variables for all types of cards
    private String cardNumber;
    private String userName;
    protected double balance;
    
    // static array to store all cards that have been created
    private static ArrayList<BankCard> cardList = new ArrayList<>();
    private static int cardCount = 0;
    
    // methods for all types of cards
    // constructor
    public BankCard( String number, String name ) {
        // initialize instance variables
        cardNumber = number;
        userName = name;
        balance = 0;
        // add card to cardList
        //if (cardCount < cardList.size() - 1) {
            cardList.add(this);
            cardCount++;
        //}
        //else
        //    System.out.println("Sorry, we cannot issue more cards now.");
    }
    
    // print balance of account
    public void showBalance() {
        System.out.printf( userName + "'s account balance is $ %1.2f", balance);
    }
    
    // find a card by number
    public static BankCard findCard(String number) {
        BankCard result = null;
        // linear search
        for (int c = 0; c < cardList.size(); c++) {
            if ( number.equals(cardList.get(c).cardNumber) )
                return cardList.get(c);
        }
        return result;
    }
    
    // makeCharge: empty method to be overridden by subclasses
    public boolean makeCharge(double amount) {
        return false;
    }
}
