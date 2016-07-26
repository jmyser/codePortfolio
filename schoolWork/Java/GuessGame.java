package guessGame;

/***********************************************************************
Program Name: GuessGame.java
Programmer's Name: Jeremy Myser
Program Description: Guess the random  number
***********************************************************************/

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GuessGame extends JFrame {

    private JFrame mainFrame;
    private JButton btnGuess;
    private JButton btnNewGame;
    private JButton btnExit;
    private JLabel lblRules;
    private JTextField txtGuessEntry;
    private JLabel lblFeedBack;

    public GuessGame(){
        mainFrame = new JFrame("Guess The Number");
        btnNewGame = new JButton("New Game");
        btnExit = new JButton("Exit");
        lblRules = new JLabel("I have a number between 1 and 1000 -- Can you guess my number?");
        txtGuessEntry = new JTextField(20);
        btnGuess = new JButton("Test Guess");
        lblFeedBack = new JLabel("");

        Random CorrectAnswer = new Random();
        int numCorrect = CorrectAnswer.nextInt(1001);
        int numGuesses = 0;
        
        
        Container c = mainFrame.getContentPane();
        c.setLayout(new FlowLayout());
        
        c.add(btnNewGame);
        c.add(btnExit);
        c.add(lblRules);
        c.add(txtGuessEntry);
        c.add(btnGuess);
        c.add(lblFeedBack);

        btnGuess.setMnemonic('G');

        mainFrame.setSize(400, 300);
        
        mainFrame.addWindowListener(new WindowAdapter(){
            public void WindowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        ButtonHandler bhandler = new ButtonHandler();
        btnGuess.addActionListener(bhandler);
        
        bhandler.setNumGuesses(numGuesses);
        bhandler.setNumCorrect(numCorrect);

        btnNewGame.addActionListener(new BtnNewGameListener());
        btnExit.addActionListener(new BtnExitListener());
        
        mainFrame.setVisible(true);
    }
    
    private class BtnNewGameListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		txtGuessEntry.setEditable(true);
    		txtGuessEntry.setText("");
    	    lblFeedBack.setOpaque(false);
    	    lblFeedBack.setText("");
    	    Random CorrectAnswer = new Random();
            int numCorrect = CorrectAnswer.nextInt(1001);
            int numGuesses = 0;
            
            // Resetting the random number is the only thing I couldn't figure out how to do.
            
    	}
    }


    private class BtnExitListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    	    System.exit(0);
    	}
    }

    
    class ButtonHandler implements ActionListener {
    	
    	private int numCorrect;
    	private int numGuesses;
    	
    	public int getNumCorrect() {
    		return numCorrect;
    	}

    	public void setNumCorrect(int numCorrect) {
    		this.numCorrect = numCorrect;
    	}
    	
    	public int getNumGuesses() {
    		return numGuesses;
    	}

    	public void setNumGuesses(int numGuesses) {
    		this.numGuesses = numGuesses;
    	}
    	
        public void actionPerformed(ActionEvent e) {

            int Guess;
            Guess = Integer.parseInt(txtGuessEntry.getText());

            numGuesses++;

            if (Guess < numCorrect) {
                    lblFeedBack.setText("Too Low!");
                    lblFeedBack.setBackground(Color.BLUE);
                    lblFeedBack.setOpaque(true);
            } else if (Guess > numCorrect) {
                    lblFeedBack.setText("Too High!");
                    lblFeedBack.setBackground(Color.RED);
                    lblFeedBack.setOpaque(true);
            } else {
                    lblFeedBack.setText("Congratulations, you guessed it!  --  Number of guesses: " + numGuesses);
                    lblFeedBack.setOpaque(false);
                    txtGuessEntry.setEditable(false);
            }
        }
    }
    
    public static void main(String[] args) {
        GuessGame app;
        app = new GuessGame();
    }
}

