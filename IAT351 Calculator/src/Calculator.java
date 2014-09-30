// Calculator GUI 	

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator implements Observer {

	// class constants
	private static final int WINDOW_WIDTH = 275; // pixels
	private static final int WINDOW_HEIGHT = 275; // pixels
	private static final int TEXT_WIDTH = 20; // characters

	// private static final FlowLayout LAYOUT_STYLE = new FlowLayout();

	// window for GUI
	private JFrame window = new JFrame("Awesome Calculator");

	// add a panel so we can listen to keyboard events
	JPanel panel = new JPanel();

	// main input field for numbers
	private JTextField inputField = new JTextField(TEXT_WIDTH);

	// create arrays of each type of button
	private JButton[] numberButtons;
	private JButton[] operatorButtons;

	// colors for the button types;
	private Color numberButtonColor;
	private Color operatorButtonColor;

	// Calculator(): constructor
	public Calculator() {
		// configure GUI
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.getContentPane().add(panel);

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// set up button arrays
		numberButtons = new JButton[11];
		operatorButtons = new JButton[7];

		// define buttons
		numberButtons[0] = new JButton("0");
		numberButtons[1] = new JButton("7");
		numberButtons[2] = new JButton("8");
		numberButtons[3] = new JButton("9");
		numberButtons[4] = new JButton("4");
		numberButtons[5] = new JButton("5");
		numberButtons[6] = new JButton("6");
		numberButtons[7] = new JButton("1");
		numberButtons[8] = new JButton("2");
		numberButtons[9] = new JButton("3");
		numberButtons[10] = new JButton(".");

		operatorButtons[0] = new JButton("+");
		operatorButtons[1] = new JButton("*");
		operatorButtons[2] = new JButton("-");
		operatorButtons[3] = new JButton("/");
		operatorButtons[4] = new JButton("%");
		operatorButtons[5] = new JButton("C");
		operatorButtons[6] = new JButton("=");

		numberButtonColor = new Color(255, 0, 0); // red
		operatorButtonColor = new Color(0, 0, 255); // blue

		// arrange components in GUI
		// Container c = window.getContentPane();
		// panel.setLayout(LAYOUT_STYLE);
		// panel.setLayout(LAYOUT_STYLE);
		panel.setLayout(new GridBagLayout());

		// add input field
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
		c.gridwidth = 5;
		c.weightx = 1;
		c.weighty = 1;

		panel.add(inputField, c);
		inputField.setEditable(false);
		inputField.setHorizontalAlignment(SwingConstants.RIGHT);

		CalculatorModel model = new CalculatorModel();
		// add this view as an observer
		model.addObserver(this);

		// c.add(runButton);
		final NumberListener numListener = new NumberListener(inputField, model);

		final OperatorListener opListener = new OperatorListener(inputField,
				model);

		for (int i = 0; i < (numberButtons.length); i++) {
			// Add an event listener to each number key.
			numberButtons[i].addActionListener(numListener);
			numberButtons[i].setBackground(numberButtonColor);
			numberButtons[i].setOpaque(true);
			// numberButtons[i].setBorderPainted(false);

			// so the focus doesn't get removed from the frame
			numberButtons[i].setFocusable(false);

			// panel.add(numberButtons[i], c);

		}

		c.gridwidth = 1;
		c.gridheight = 1;
		int counter = 1;

		// loop through the grid adding numbers 1-9 buttons along the way
		for (int y = 1; y < 4; y++) {
			for (int x = 0; x < 3; x++) {
				c.gridx = x;
				c.gridy = y;

				panel.add(numberButtons[counter], c);
				counter++;
			}
		}

		// add main operators; + - * /
		for (int i = 1; i < 5; i++) {
			c.gridx = 3;
			c.gridy = i;
			panel.add(operatorButtons[i - 1], c);
		}

		// add Clear button
		c.gridx = 4;
		c.gridy = 1;
		panel.add(operatorButtons[4], c);

		// add % button
		c.gridx = 4;
		c.gridy = 2;
		panel.add(operatorButtons[5], c);

		// add = button
		c.gridy = 3;
		c.gridheight = 2;
		panel.add(operatorButtons[6], c);

		// add 0 button
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		panel.add(numberButtons[0], c);

		// add . button
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		panel.add(numberButtons[10], c);

		for (int i = 0; i < (operatorButtons.length); i++) {
			// Add an event listener to each operator key.
			operatorButtons[i].addActionListener(opListener);

			// change the color
			operatorButtons[i].setBackground(operatorButtonColor);
			operatorButtons[i].setOpaque(true);

			// Add each operator key to the GUI.
			// panel.add(operatorButtons[i]);
			operatorButtons[i].setFocusable(false);

		}

		// add key listener to the JPanel so we can handle keyboard input
		panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Pressed " + e.getKeyChar());

				// call the number handling function when numbers or decimal is
				// pressed. less than ':' to include the char '9'
				for (char i = '0'; i < ':'; i++) {
					if (e.getKeyChar() == i) {
						numListener.doNumber(Character.toString(i));
					}
				}

				if (e.getKeyChar() == '.') {
					numListener.doNumber(".");
				}

				// handle operator keys
				if (e.getKeyChar() == '=' || e.getKeyChar() == '\n') {
					opListener.doOperator("=");
				} else if (e.getKeyChar() == '+') {
					opListener.doOperator("+");
				} else if (e.getKeyChar() == '-') {
					opListener.doOperator("-");
				} else if (e.getKeyChar() == '/') {
					opListener.doOperator("/");
				} else if (e.getKeyChar() == '*') {
					opListener.doOperator("*");
				} else if (e.getKeyChar() == '%') {
					opListener.doOperator("%");
				} else
					return;
			}
		});

		panel.setFocusable(true);
		panel.requestFocusInWindow();

		// display GUI
		window.setVisible(true);
	}

	// main(): application entry point
	public static void main(String[] args) {
		Calculator gui1 = new Calculator();
		// Humidex gui2 = new Humidex();
	}

	@Override
	public void update(Observable model, Object updateNum) {
		String numberString;
		
		if (updateNum instanceof String) { 
			numberString = (String) updateNum;
		}
		else {
			Double number = (Double) updateNum;
			numberString = Double.toString(number);
		}

		// put num on the screen
		inputField.setText(numberString);
	}
}