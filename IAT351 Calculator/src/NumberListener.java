import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class NumberListener implements ActionListener {
	
	private JTextField inputField;
	private boolean startNumber = true;
	
	// pass in the inputField object so the content can be accessed
	public NumberListener(JTextField inputField) {
		this.inputField = inputField;
	}
	
	// function used to flag whether a new number will be entered
	public void resetNumber() {
		this.startNumber = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// get the button clicked as a JButton object
		JButton clickedButton = (JButton) e.getSource();
		
		doNumber(clickedButton.getText());
	}
	
	public void doNumber(String numString) {
		
		// if a new number is being entered (ie. after an operator was hit) first clear the input field
		if (this.startNumber) {
			inputField.setText("");
		}
		
		// after the first number was clicked, turn off the flag so that the numbers clicked afterwards will just be appended
		this.startNumber = false;
		
		// get current string from the input
		String currentNumber = inputField.getText();
		
		// append the clicked button's text to the input field's text
		currentNumber = currentNumber + numString;
			
		// change the text
		inputField.setText(currentNumber);	
	}

}
