package frametools;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A window that accepts strings from user input and 
 * displays the boolean result of a strings palindromicity.
 * Results are displayed in a non-editable text area.
 *
 */
public class PalindromeDemo extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6523249222414784352L;
	private static final String newline = "\n";
	private static FrameUtilities utils;
	protected JTextField textField;
	protected JTextArea textArea;
	protected JLabel label;

	public PalindromeDemo() {
		super(new GridBagLayout());
		utils = new FrameUtilities();
		
		// setup Swing components
		label = new JLabel("Enter input string:");

		textField = new JTextField(20); // 20 columns
		textField.addActionListener(this);

		textArea = new JTextArea(8, 20); // 8 rows, 20 columns
		// this container is meant to log output and shouldn't be editable
		textArea.setEditable(false);

		// Setup the scroll pane for text area container
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Initialize grid constraints to define sizing/position for
		// components in the layout manager
		GridBagConstraints c = new GridBagConstraints();

		// handle layout and setup constraints 
		// for how components are resized in the window
		c.gridwidth = GridBagConstraints.REMAINDER;

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(label);

		// define grid position for textField
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(textField);

		// define grid position for the scroll pane
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 3;
		// 40px of vertical padding
		c.ipady = 40;
		c.gridx = 0;
		c.gridy = 1;

		// Add scroll pane with its layout constraints
		add(scrollPane, c);
	}

	public void actionPerformed(ActionEvent evt) {
		String text = textField.getText();

		// append result to textarea
		textArea.append(Boolean.toString(utils.isPalindrome(text)) + newline);
		
		// select all user input
		textField.selectAll();

		// set "cursor" position for insertions
		// to textArea after appending user input
		int docLength = textArea.getDocument().getLength();
		textArea.setCaretPosition(docLength);
	}

	/**
	 * Initialize a JFrame which holds the current FrameBuilder window. Not
	 * thread-safe. Must be ran on the "event-dispatching" thread.
	 * 
	 */
	private static void initGUI() {
		JFrame frame = new JFrame("Palindrome Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add new FrameBuilder instance to the current window
		frame.add(new PalindromeDemo());

		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		// Schedule a new job/event using the "event dispatching" thread
		// Swing data-structures are not thread-safe
		// so we schedule jobs on the AWT event-dispatching thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// render GUI
				initGUI();
				System.out.println("Running on thread: " + Thread.currentThread());
			}
		});
	}
}
