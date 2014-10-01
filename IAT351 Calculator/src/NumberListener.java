import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NumberListener implements ActionListener {

	private CalculatorModel model;

	// private JTextField inputField;

	// pass in the inputField object so the content can be accessed
	public NumberListener(CalculatorModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// get the button clicked as a JButton object
		JButton clickedButton = (JButton) e.getSource();

		doNumber(clickedButton.getText());
	}

	public void doNumber(String buttonClickedValue) {
		// if a new number is being entered (ie. after an operator was hit)
		// first clear the input field
		if (model.getStartNewNumber()) {
			model.setCurrentValue("");
		}

		// after the first number was clicked, turn off the flag so that the
		// numbers clicked afterwards will just be appended
		model.setStartNewNumber(false);

		// get current string from the input
		String currentValue = model.getCurrentValue();

		// if the number entered was a decimal point
		if (buttonClickedValue == ".") {
			// if the user entered a decimal point first, add a zero in front
			if (currentValue.isEmpty()) {
				currentValue = "0.";
			}
			// otherwise, if the current number doesn't already have a decimal,
			// it's OK to add one
			else if (!currentValue.contains(".")) {
				// append the clicked button's text to the input field's text
				currentValue = currentValue + buttonClickedValue;
			}
		} else {
			// append the clicked button's text to the input field's text
			currentValue = currentValue + buttonClickedValue;
		}

		// change the text
		model.setCurrentValue(currentValue);

	}

}