package poolCourseProject;

/***********************************************************************
Program Name: PoolCourseProject.java
Programmer's Name: Jeremy Myser
Program Description: GUI for calculating pool volume
***********************************************************************/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class PoolCourseProject extends	JFrame {
	private	JTabbedPane tabbedPane;
	private	JPanel panel1;
	private	JPanel panel2;
	private	JPanel panel3;
	private JLabel lblDiameter;
	private JTextField txtDiameter;
	private JLabel lblLength;
	private JTextField txtLength;
	private JLabel lblWidth;
	private JTextField txtWidth;
	private JLabel lblDepth;
	private JTextField txtDepth;
	private JButton btnCalculateRectangle;
	private JLabel lblRectangleFeedBack;
	private JButton btnCalculateCircle;
	private JLabel lblCircleFeedBack;
	private JTextArea lblInstructions;
	private JButton btnExit;
    

	public PoolCourseProject(){
		
		setTitle("Pool Volume Calculator");
		setSize( 600, 150 );

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add(topPanel);

		// Create the tab pages
		createTabOne();
		createTabTwo();
		createTabThree();

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Instructions", panel1 );
		tabbedPane.addTab( "Rectangle Pool", panel2 );
		tabbedPane.addTab( "Round Pool", panel3 );
		topPanel.add( tabbedPane, BorderLayout.CENTER );
	}

	public void createTabOne(){
		
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		lblInstructions = new JTextArea("Select the tab of the style pool you have. \n Enter the dimensions and click the Calculate button to acquire the number of gallons needed to fill pool.");
		lblInstructions.setVisible(true);
		panel1.add(lblInstructions);
	}

	public void createTabTwo(){

		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

        lblLength = new JLabel("Length:");
        txtLength = new JTextField(20);
        lblWidth = new JLabel("Width:");
        txtWidth = new JTextField(20);
        lblDepth = new JLabel("Depth:");
        txtDepth = new JTextField(20);
        btnCalculateRectangle = new JButton("Calculate Rectangle");
        btnExit = new JButton("Exit");
        
        lblRectangleFeedBack = new JLabel("");

		panel2.add(lblLength);
		panel2.add(txtLength);
		panel2.add(lblWidth);
		panel2.add(txtWidth);
		panel2.add(lblDepth);
		panel2.add(txtDepth);
		panel2.add(btnCalculateRectangle);
		panel2.add(btnExit);
		panel2.add(lblRectangleFeedBack);

		btnCalculateRectangle.addActionListener(new BtnCalculateRectangle());
		btnExit.addActionListener(new BtnExitListener());
	}

	public void createTabThree(){

		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());

        lblDiameter = new JLabel("Diameter:");
        txtDiameter = new JTextField(20);
        lblDepth = new JLabel("Depth:");
        txtDepth = new JTextField(20);
        btnCalculateCircle = new JButton("Calculate Circle");
        btnExit = new JButton("Exit");
        
        lblCircleFeedBack = new JLabel("");

        panel3.add(lblDiameter);
        panel3.add(txtDiameter);
        panel3.add(lblDepth);
        panel3.add(txtDepth);
        panel3.add(btnCalculateCircle);
        panel3.add(btnExit);
        panel3.add(lblCircleFeedBack);

        btnCalculateCircle.setMnemonic('R');

		btnCalculateCircle.addActionListener(new BtnCalculateCircle());
		btnExit.addActionListener(new BtnExitListener());
	}


	private class BtnCalculateRectangle implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	double length;
	        double width;
	        double depth;
	        double gallons;

	        length = Double.parseDouble(txtLength.getText());
	        width = Double.parseDouble(txtWidth.getText());
	        depth = Double.parseDouble(txtDepth.getText());

	        gallons = (length * width * depth * 7.48052);

	        lblRectangleFeedBack.setText("Gallons Needed: " + gallons);
	            
	    }
	}

	private class BtnCalculateCircle implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        double diameter;
	        double depth;
	        double gallons;

	        diameter = Double.parseDouble(txtWidth.getText());
	        depth = Double.parseDouble(txtDepth.getText());

	        gallons = (diameter / 2) * (diameter / 2) * depth * 7.48052 * 3.14159265359;

	        lblCircleFeedBack.setText("Gallons Needed: " + gallons);
	            
	    }

	}
	
	private class BtnExitListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    	    System.exit(0);
    	}
    }


	public static void main( String args[] ){

		// Create an instance of the test application
		PoolCourseProject mainFrame	= new PoolCourseProject();
		mainFrame.setVisible( true );
	}
}