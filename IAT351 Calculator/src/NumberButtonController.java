import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NumberButtonController implements ActionListener {

	private CalculatorModel model;

	// private JTextField inputField;

	// pass in the inputField object so the content can be accessed
	public NumberButtonController(CalculatorModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// get the button clicked as a JButton object
		JButton clickedButton = (JButton) e.getSource();

		doNumber(clickedButton.getText());
	}

	public void doNumber(String buttonClickedValue) {
		model.doNumber(buttonClickedValue);
	}

}