
package lab5;

public class CreditCard extends BankCard {
    // instance variables
    private int creditLimit;
    
    // constructor
    public CreditCard( String number, String name ) {
        // call constructor of superclass
        super(number, name);
        // initialize instance variables of this class
        creditLimit = 1000; // default limit
    }
    
    // update methods for transactions
    public boolean makeCharge(double price) {
        if (price + balance > creditLimit)
            return false;   // over limit
        balance += price;
        return true;        // the charge goes through
    }
    
    public void makePayment(double payment) {
        balance -= payment;
    }
}
