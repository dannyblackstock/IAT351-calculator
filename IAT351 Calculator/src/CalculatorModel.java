import java.util.Observable;

public class CalculatorModel extends Observable {
	private String previousOperator;
	private String currentNumberStr = "";
	private boolean startNewNumber = true;
	private boolean equalsPressed = false;
	private double previousNumber = 0.0;
	private double currentNumber = 0.0;
	private double result;

	// constructor
	CalculatorModel() {
	}

	// variable used to flag whether a new number will be entered
	private void setStartNewNumber(boolean value) {
		startNewNumber = value;
	}

	// function used to flag whether a the equals button was the last operator
	// pressed
	private void setEqualsPressed(boolean value) {
		equalsPressed = value;
	}

	private void setCurrentValue(String value) {
		currentNumberStr = value;
		if (value != null && !value.isEmpty()) {
			currentNumber = Double.parseDouble(currentNumberStr);
		}
		setChanged();
		notifyObservers(currentNumberStr);
	}

	// math operations for manipulating model data
	private void add() {
		if (!currentNumberStr.isEmpty()) {
			result = previousNumber + currentNumber;
		} else {
			result = previousNumber + 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	private void multiply() {
		if (!currentNumberStr.isEmpty()) {
			result = previousNumber * currentNumber;
		} else {
			result = previousNumber * 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	private void subtract() {
		if (!currentNumberStr.isEmpty()) {
			result = previousNumber - currentNumber;
		} else {
			result = previousNumber - 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	private void divide() {
		if (!currentNumberStr.isEmpty()) {
			result = previousNumber / currentNumber;
		} else {
			result = previousNumber / 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	private void modulus() {
		if (!currentNumberStr.isEmpty()) {
			result = previousNumber % currentNumber;
		} else {
			result = previousNumber % 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	// for the clear button
	private void reset() {
		previousOperator = null;
		previousNumber = 0.0;
		equalsPressed = false;

		setCurrentValue("");

		// a new number will need to be entered, so turn on the flag
		startNewNumber = true;
	}

	public void doNumber(String numStr) {
		// if a new number is being entered (ie. after an operator was hit)
		// first clear the input field
		if (startNewNumber) {
			setCurrentValue("");
		}

		// after the first number was clicked, turn off the flag so that the
		// numbers clicked afterwards will just be appended
		startNewNumber = false;

		// get current string from the input
		String currentValueTemp = currentNumberStr;

		// if the number entered was a decimal point
		if (numStr == ".") {
			// if the user entered a decimal point first, add a zero in front
			if (currentValueTemp.isEmpty()) {
				currentValueTemp = "0.";
			}
			// otherwise, if the current number doesn't already have a decimal,
			// it's OK to add one
			else if (!currentValueTemp.contains(".")) {
				// append the clicked button's text to the input field's text
				currentValueTemp = currentValueTemp + numStr;
			}
		} else {
			// append the clicked button's text to the input field's text
			currentValueTemp = currentValueTemp + numStr;
		}

		// change the text
		setCurrentValue(currentValueTemp);

	}

	public void doOperation(String operator) {

		if (operator == "C") {
			reset();
			return;
		}

		// if equals was pressed before the current operator, but this operator
		// isn't equals
		if (equalsPressed && operator != "=") {
			// set the previous operator to the new (current) one, and prepare
			// model for a new number to be entered
			previousOperator = operator;

			// try setting the previous number to the currentValue
			if (!currentNumberStr.isEmpty()) {
				previousNumber = Double.parseDouble(currentNumberStr);
			} else {
				previousNumber = 0.0;
			}

			setStartNewNumber(true);
			setEqualsPressed(false);
			return;
		}

		// save temporary variables with the model's values
		String currentValueTemp = currentNumberStr;
		double previousNumberTemp = previousNumber;

		// if equals was the last pressed operator, and the user pressed equals
		// again
		if (equalsPressed && operator == "=") {
			// switch the current number with the previous number so that they
			// are calculated by the model's functions properly
			// since the model always does previousNumber then (operator) then
			// currentNumber
			setCurrentValue(Double.toString(previousNumberTemp));
			previousNumber = Double.parseDouble(currentValueTemp);
		}

		// if there is a previous operator stored
		if (previousOperator != null) {

			// complete the operation with the pattern
			// previousNumber then (operator) then currentNumber
			// see CalculatorModel
			if (previousOperator == "+") {
				add();
			} else if (previousOperator == "-") {
				subtract();
			} else if (previousOperator == "*") {
				multiply();
			} else if (previousOperator == "/") {
				divide();
			} else if (previousOperator == "%") {
				modulus();
			}
		}

		// if the last operator was not equals and the current one isn't either
		if (!equalsPressed && operator != "=") {

			// try to set the previous number to the current value on the screen
			if (!currentNumberStr.isEmpty()) {
				previousNumber = (Double.parseDouble(currentNumberStr));
			} else {
				previousNumber = (0.0);
			}

			// save this operator as the previous operator
			previousOperator = operator;
			// equals was not the last operator in this case
			equalsPressed = false;
		}

		// if this is the first time in a row equals has been pressed
		else if (!equalsPressed && operator == "=") {
			// save the locally saved variable that was on the screen before as
			// the previous number so that it can be repeated if the user
			// presses equals repeatedly
			previousNumber = Double.parseDouble(currentValueTemp);

			// equals was pressed now
			equalsPressed = true;
		}

		// if equals was clicked twice in a row
		else if (equalsPressed && operator == "=") {
			// change the previousNumber to the temporary saved version of it,
			// so it can be used again
			previousNumber = previousNumberTemp;
		}

		// a new number will need to be entered, so turn on the flag
		startNewNumber = true;
	}

}
