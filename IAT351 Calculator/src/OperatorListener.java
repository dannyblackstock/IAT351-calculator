import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OperatorListener implements ActionListener {

	private CalculatorModel model;

	// pass in the inputField object so the content can be accessed
	public OperatorListener(CalculatorModel model) {
		this.model = model;
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
			model.reset();
			return;
		}

		// if equals was pressed before the current operator, but this operator
		// isn't equals
		if (model.getEqualsPressed() && operator != "=") {
			// set the previous operator to the new (current) one, and prepare
			// model for a new number to be entered
			model.setPreviousOperator(operator);

			// try setting the previous number to the currentValue
			if (!model.getCurrentValue().isEmpty()) {
				model.setPreviousNumber(Double.parseDouble(model
						.getCurrentValue()));
			} else {
				model.setPreviousNumber(0.0);
			}

			model.setStartNewNumber(true);
			model.setEqualsPressed(false);
			return;
		}

		// save temporary variables with the model's values
		String previousOperator = model.getPreviousOperator();
		String currentValue = model.getCurrentValue();
		double previousNumber = model.getPreviousNumber();

		// if equals was the last pressed operator, and the user pressed equals
		// again
		if (model.getEqualsPressed() && operator == "=") {
			// switch the current number with the previous number so that they
			// are calculated by the model's functions properly
			// since the model always does previousNumber then (operator) then
			// currentNumber
			model.setCurrentValue(Double.toString(previousNumber));
			model.setPreviousNumber(Double.parseDouble(currentValue));
		}

		// if there is a previous operator stored
		if (previousOperator != null) {

			// complete the operation with the pattern
			// previousNumber then (operator) then currentNumber
			// see CalculatorModel
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

		// if the last operator was not equals and the current one isn't either
		if (!model.getEqualsPressed() && operator != "=") {

			// try to set the previous number to the current value on the screen
			if (!model.getCurrentValue().isEmpty()) {
				model.setPreviousNumber(Double.parseDouble(model
						.getCurrentValue()));
			} else {
				model.setPreviousNumber(0.0);
			}

			// save this operator as the previous operator
			model.setPreviousOperator(operator);
			// equals was not the last operator in this case
			model.setEqualsPressed(false);
		}

		// if this is the first time in a row equals has been pressed
		else if (!model.getEqualsPressed() && operator == "=") {
			// save the locally saved variable that was on the screen before as
			// the previous number so that it can be repeated if the user
			// presses equals repeatedly
			model.setPreviousNumber(Double.parseDouble(currentValue));

			// equals was pressed now
			model.setEqualsPressed(true);
		}

		// if equals was clicked twice in a row
		else if (model.getEqualsPressed() && operator == "=") {
			// change the previousNumber to the temporary saved version of it,
			// so it can be used again
			model.setPreviousNumber(previousNumber);
		}

		// a new number will need to be entered, so turn on the flag
		model.setStartNewNumber(true);
	}
}