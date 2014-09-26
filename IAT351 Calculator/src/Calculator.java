// Calculator GUI 	

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator {

	// class constants
	private static final int WINDOW_WIDTH = 275; // pixels
	private static final int WINDOW_HEIGHT = 275; // pixels
	private static final int TEXT_WIDTH = 20; // characters

	private static final FlowLayout LAYOUT_STYLE = new FlowLayout();

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

		// humidexText.setEditable(false);
		// humidexText.setBackground(Color.WHITE);

		// register event listener
		// runButton.addActionListener(this);
		// inputField.addActionListener(this);
		// humText.addActionListener(this);

		// set up button arrays
		numberButtons = new JButton[10];
		operatorButtons = new JButton[7];

		// define buttons
		numberButtons[0] = new JButton("0");
		numberButtons[1] = new JButton("1");
		numberButtons[2] = new JButton("2");
		numberButtons[3] = new JButton("3");
		numberButtons[4] = new JButton("4");
		numberButtons[5] = new JButton("5");
		numberButtons[6] = new JButton("6");
		numberButtons[7] = new JButton("7");
		numberButtons[8] = new JButton("8");
		numberButtons[9] = new JButton("9");

		operatorButtons[0] = new JButton("+");
		operatorButtons[1] = new JButton("-");
		operatorButtons[2] = new JButton("*");
		operatorButtons[3] = new JButton("/");
		operatorButtons[4] = new JButton("%");
		operatorButtons[5] = new JButton("=");
		operatorButtons[6] = new JButton("C");

		numberButtonColor = new Color(255, 0, 0); // red
		operatorButtonColor = new Color(0, 0, 255); // blue

		// arrange components in GUI
		// Container c = window.getContentPane();
		panel.setLayout(LAYOUT_STYLE);
		panel.setLayout(LAYOUT_STYLE);
		
		// add input field
		panel.add(inputField);
		inputField.setEditable(false);

		// c.add(runButton);
		final NumberListener numListener = new NumberListener(inputField);

		final OperatorListener opListener = new OperatorListener(inputField,
				numListener);

		for (int i = 0; i < (numberButtons.length); i++) {
			// Add an event listener to each number key.
			numberButtons[i].addActionListener(numListener);
			numberButtons[i].setForeground(numberButtonColor);

			// so the focus doesn't get removed from the frame
			numberButtons[i].setFocusable(false);

			panel.add(numberButtons[i]);
		}

		for (int i = 0; i < (operatorButtons.length); i++) {
			// Add an event listener to each operator key.
			operatorButtons[i].addActionListener(opListener);
			
			// change the color
			operatorButtons[i].setForeground(operatorButtonColor);
			
			// Add each operator key to the GUI.
			panel.add(operatorButtons[i]);
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
				
				// call the number handling function when numbers or decimal is pressed
				for (char i = '0'; i < '9'; i++) {
					if (e.getKeyChar() == i) {
						numListener.doNumber(Character.toString(i));
					}
				}
				
				// handle operator keys
				if (e.getKeyChar() == '=' || e.getKeyChar() == '\n') {
					opListener.doOperator("=");
				}
				else if (e.getKeyChar() == '+') {
					opListener.doOperator("+");
				}
				else if (e.getKeyChar() == '-') {
					opListener.doOperator("-");
				}
				else if (e.getKeyChar() == '/') {
					opListener.doOperator("/");
				}
				else if (e.getKeyChar() == '*') {
					opListener.doOperator("*");
				}
				else if (e.getKeyChar() == '%') {
					opListener.doOperator("%");
				}
				else return;
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
}