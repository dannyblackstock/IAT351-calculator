import java.util.Observable;

public class CalculatorModel extends Observable {
	private String previousOperator;
	private String currentValue = "";
	private boolean startNewNumber = true;
	private boolean equalsPressed = false;
	private double previousNumber = 0.0;
	private double result;

	// constructor
	CalculatorModel() {
	}

	public boolean getStartNewNumber() {
		return startNewNumber;
	}

	// variable used to flag whether a new number will be entered
	public void setStartNewNumber(boolean value) {
		startNewNumber = value;
	}

	public boolean getEqualsPressed() {
		return equalsPressed;
	}

	// function used to flag whether a the equals button was the last operator
	// pressed
	public void setEqualsPressed(boolean value) {
		equalsPressed = value;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String value) {
		currentValue = value;
		setChanged();
		notifyObservers(currentValue);
	}

	public void setPreviousNumber(double number) {
		previousNumber = number;
	}

	public double getPreviousNumber() {
		return previousNumber;
	}

	public String getPreviousOperator() {
		return previousOperator;
	}

	public void setPreviousOperator(String operator) {
		previousOperator = operator;
	}

	// math operations for manipulating model data
	public void add() {
		if (!currentValue.isEmpty()) {
			result = previousNumber + Double.parseDouble(currentValue);
		} else {
			result = previousNumber + 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	public void multiply() {
		if (!currentValue.isEmpty()) {
			result = previousNumber * Double.parseDouble(currentValue);
		} else {
			result = previousNumber * 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	public void subtract() {
		if (!currentValue.isEmpty()) {
			result = previousNumber - Double.parseDouble(currentValue);
		} else {
			result = previousNumber - 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	public void divide() {
		if (!currentValue.isEmpty()) {
			result = previousNumber / Double.parseDouble(currentValue);
		} else {
			result = previousNumber / 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	public void modulus() {
		if (!currentValue.isEmpty()) {
			result = previousNumber % Double.parseDouble(currentValue);
		} else {
			result = previousNumber % 0.0;
		}
		setCurrentValue(Double.toString(result));
	}

	// for the clear button
	public void reset() {
		setPreviousOperator(null);
		setCurrentValue("");
		setPreviousNumber(0.0);
		setEqualsPressed(false);

		// a new number will need to be entered, so turn on the flag
		setStartNewNumber(true);
	}
}
