package lab5;

/*
 * @author jeremy myser 
 */
public class DebitCard extends BankCard {
    // instance variables
    private int chargeCount;
    
    // constructor
    public DebitCard(String number, String name) {
        // call constructor of superclass
        super(number, name);
        // initialize instance variables of this class
        chargeCount = 0; // default limit
    }
    
    public boolean makeCharge(double price) {
        if (price > balance)
            return false;   // over limit
        balance -= price;
        chargeCount++;
        return true;
    }
    
    public void makeDeposit(double amount) {
        balance += amount;
    }
    
    public void showBalance() {
        super.showBalance();
        System.out.println(" and number of charges is: " + chargeCount);
    }
}
