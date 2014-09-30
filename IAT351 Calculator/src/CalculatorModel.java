import java.util.Observable;

public class CalculatorModel extends Observable {
	private String previousOperator;
	private double previousNumber = 0.0;
	private String currentValue = "";
	private boolean startNewNumber = true;
	double result;

	CalculatorModel() {

	}

	public boolean getStartNewNumber() {
		return startNewNumber;
	}

	// function used to flag whether a new number will be entered
	public void setStartNewNumber(boolean value) {
		startNewNumber = value;
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

	public String getPreviousOperator() {
		return previousOperator;
	}

	public void setPreviousOperator(String operator) {
		previousOperator = operator;
	}

	public void add() {
		result = previousNumber + Double.parseDouble(currentValue);
		setCurrentValue(Double.toString(result));
	}

	public void multiply() {
		result =  previousNumber * Double.parseDouble(currentValue);
		setCurrentValue(Double.toString(result));
	}

	public void subtract() {
		result =  previousNumber - Double.parseDouble(currentValue);
		setCurrentValue(Double.toString(result));
	}

	public void divide() {
		result = previousNumber / Double.parseDouble(currentValue);
		setCurrentValue(Double.toString(result));
	}

	public void modulus() {
		result = Double.parseDouble(currentValue) % previousNumber;
		setCurrentValue(Double.toString(result));
	}
}
