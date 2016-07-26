package lab5;
import java.util.Scanner;

public class CardTransactions {
    static Scanner input;
    
    public static BankCard enterCardNumber () {
        System.out.println("Enter card number: ");
        String number = input.nextLine();
        BankCard card = BankCard.findCard(number);
        while (card == null) {
            System.out.println("Invalid card number: " + number);
            System.out.println("Enter another card number: ");
            number = input.nextLine();
            card = BankCard.findCard(number);
        }
        return card;
    }
    
    public static void main(String [] args) {
        input = new Scanner(System.in);
        CreditCard exampleCard = new CreditCard("1001", "Big Spender");
        BankCard card;
        String request, number, name;
        // transaction loop
        do {
            // prompt
            System.out.println("\nSelect a type of transaction.\n"
                + "   Press N to issue a new card.\n"
                + "   Press C to make a charge.\n"
                + "   Press P to make a payment or deposit.\n"
                + "   Press B to show your account balance.\n"
                + "   Press Q if done.");
            System.out.println("Type your request and then ENTER: ");
            
            request = input.nextLine();
            if (request.equalsIgnoreCase("N")) {
                // create a new card
                System.out.println("Enter new card number: ");
                number = input.nextLine();
                System.out.println("Enter name of card owner: ");
                name = input.nextLine();
                String cardType = "C";
                do {
                    System.out.println(
                            "Enter C for credit card, or D for debit card: ");
                    cardType = input.nextLine();
                } while (!(cardType.equalsIgnoreCase("C")
                            || cardType.equalsIgnoreCase("D")));
                if (cardType.equalsIgnoreCase("C"))
                    card = new CreditCard(number, name);
                else
                    card = new DebitCard(number, name);
                card.showBalance();
            }
            else if (request.equalsIgnoreCase("C")) {
                // make a charge
                System.out.println("Enter amount to charge: ");
                double charge = input.nextDouble();
                input.nextLine(); // discard newline after number
                card = enterCardNumber();
                if (card.makeCharge(charge)) {
                    System.out.println("Your purchase was approved.");
                }
                else {
                    if (card instanceof CreditCard)
                        System.out.println("Sorry, that would exceed your limit.");
                    else
                        // message for DebitCard
                        System.out.println(
                                "Sorry, you do not have enough in your account.");
                }
            }
            else if (request.equalsIgnoreCase("B")) {
                card = enterCardNumber();
                card.showBalance();
            }
            else if (request.equalsIgnoreCase("P")) {
                // make a payment for CreditCard, or deposit for DebitCard
                
                System.out.print("Enter payment amount: ");
                double payment = input.nextDouble();
                input.nextLine(); // discard newline after number
                card = enterCardNumber();
                
                if (card instanceof CreditCard) {
                    CreditCard c = (CreditCard) card;
                    c.makePayment(payment);
                }
                else {
                    DebitCard c = (DebitCard) card;
                    c.makeDeposit(payment);
                }
                    
            }
        } while (!request.equalsIgnoreCase("Q"));
    }
}
