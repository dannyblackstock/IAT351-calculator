import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class OperatorListener implements ActionListener {

	private CalculatorModel model;
	private JTextField inputField;

	private double previousNumber;
	private double currentNumber = 0.0;
	private String previousOperator = null;
	private double result;

	private NumberListener numListener;

	// pass in the inputField object so the content can be accessed
	public OperatorListener(JTextField inputField, CalculatorModel model) {
		this.inputField = inputField;
		this.model = model;
		previousNumber = 0.0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// get the button clicked as a JButton object
		JButton clickedButton = (JButton) e.getSource();

		// get the button's text
		doOperator(clickedButton.getText());
	}

	// function to handle operations
	public void doOperator(String operator) {

		// if the clear button was pressed
		if (operator == "C") {
			// reset all variables
			model.setPreviousOperator(null);
			model.setCurrentValue("");
			model.setPreviousNumber(0.0);
			// model.setResult = 0.0;

			// clear the display
			// inputField.setText("");

			// a new number will need to be entered, so turn on the flag for the
			// NumberListener
			model.setStartNewNumber(true);
			return;
		}

		String previousOperator = model.getPreviousOperator();

		// if there is a previous operator stored
		if (previousOperator != null) {

			if (previousOperator == "+") {
				model.add();
			} else if (previousOperator == "-") {
				model.subtract();
			} else if (previousOperator == "*") {
				model.multiply();
			} else if (previousOperator == "/") {
				model.divide();
			} else if (previousOperator == "%") {
				model.modulus();
			}
		}

		model.setPreviousNumber(Double.parseDouble(model.getCurrentValue()));

		// else {
		// model.setCurrentValue(model.getCurrentValue());
		// }

		model.setPreviousOperator(operator);

		// a new number will need to be entered, so turn on the flag for the
		// NumberListener
		model.setStartNewNumber(true);

		// if the current value is not empty
		if (!model.getCurrentValue().isEmpty() && operator != "=") {
			// store it as the previous number in preparation for next operation
			model.setPreviousNumber(Double.parseDouble(model.getCurrentValue()));
		} else {
			// just store 0.0 as the previous number
			model.setPreviousNumber(0.0);
		}
	}

	public double getCurrentDisplayNumber() {

		// check if the display is not empty
		if (!inputField.getText().isEmpty()) {
			// get current number
			currentNumber = Double.parseDouble(inputField.getText());
			return currentNumber;
		}
		// if the display is empty, the current number is just 0
		else {
			return 0.0;
		}
	}

}