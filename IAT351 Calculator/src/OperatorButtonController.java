import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OperatorButtonController implements ActionListener {

	private CalculatorModel model;

	// pass in the inputField object so the content can be accessed
	public OperatorButtonController(CalculatorModel model) {
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
		model.doOperation(operator);
	}
}