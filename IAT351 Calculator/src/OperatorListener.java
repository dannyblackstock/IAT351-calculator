import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTextField;

public class OperatorListener implements ActionListener {

	private JTextField inputField;

	private double previousNumber;
	private double currentNumber = 0.0;
	private String previousOperator = null;
	private double result;

	private NumberListener numListener;

	// pass in the inputField object so the content can be accessed
	public OperatorListener(JTextField inputField, NumberListener numListener) {
		this.inputField = inputField;
		this.numListener = numListener;
		previousNumber = 0.0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// get current number
		currentNumber = Double.parseDouble(inputField.getText());

		// get the button clicked as a JButton object
		JButton clickedButton = (JButton) e.getSource();

		// get the button's text
		doOperator(clickedButton.getText());
	}

	public void doOperator(String operator) {

		// if the clear button was pressed
		if (operator == "C") {
			// reset all variables
			previousOperator = null;
			currentNumber = 0.0;
			previousNumber = 0.0;
			result = 0.0;

			// clear the display
			inputField.setText("");

			// a new number will need to be entered, so turn on the flag for the
			// NumberListener
			numListener.resetNumber();
			return;
		}

		// if there is a previous operator stored
		if (previousOperator != null) {

			if (previousOperator == "+") {
				result = previousNumber + currentNumber;
			} else if (previousOperator == "-") {
				result = previousNumber - currentNumber;
			} else if (previousOperator == "*") {
				result = previousNumber * currentNumber;
			} else if (previousOperator == "/") {
				result = previousNumber / currentNumber;
			} else if (previousOperator == "%") {
				result = previousNumber % currentNumber;
			}

			inputField.setText(Double.toString(result));
		}

		else {
			result = currentNumber;
		}
		
		/* so that you can repeatedly complete the last operator by clicking the "=" button,
		 * check if the operator button pressed was "=" 
		 * 
		 */
		
		if (operator != "=") {
			// store as the previous operator in preparation for next operation
			previousOperator = operator;
		}
		
		// a new number will need to be entered, so turn on the flag for the
		// NumberListener
		numListener.resetNumber();

		// store as the previous number in preparation for next operation
		previousNumber = result;
	}
}
