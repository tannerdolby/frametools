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
 * A minimal text input GUI class that will perform a specific
 * utility function on user input from one JTextField
 * based on the `action` string defined in the constructor.
 * @author TannerDolby
 *
 */
public class FrameBuilder extends JPanel implements ActionListener {
	private static final long serialVersionUID = 227042720459260261L;
	public FrameUtilities utils;
	public static JTextField textField;
	public static JTextArea textArea;
	public static String title;
	public static JLabel label;
	public static String frameTitle;
	public static String frameLabel;
	public static final String newline = "\n";
	public static String toolToRun;

	public FrameBuilder(String title, String label, String action) {
		super(new GridBagLayout());
		utils = new FrameUtilities();
		frameTitle = title;
		frameLabel = label;
		toolToRun = action;
	}

	public void actionPerformed(ActionEvent evt) {
		// todo: utilize actionPerformed call from extending inheriting classes
		String text = textField.getText();
		String res = "";

		// Conditional checks for determining correct utility function to run on user
		// input text
		if (toolToRun == "reverseStr") {
			res = utils.reverse(text, 0, text.length() - 1);
		}

		if (toolToRun == "reverseCharArr") {
			char[] chars = text.toCharArray();
			utils.reverse(chars, 0, chars.length);
			res = new String(chars);
		}
		
		if (toolToRun == "isPalindrome") {
			boolean isAnagram = utils.isPalindrome(text);
			res = Boolean.toString(isAnagram);
		}

		// append result to textarea
		textArea.append(res + newline);

		// reset the text field
		// textField.setText("");

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
	public void initGUI() {
		JFrame frame = new JFrame(frameTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add new FrameBuilder instance to the current window
		frame.add(this);

		frame.setVisible(true);
		frame.pack();
	}

	/**
	 * Define Swing data structures (JTextField, JTextArea) and style
	 * the GridBagLayout with a few simple grid definitions.
	 */
	public void setup() {

		// This code is constant across these basic "text input demo" frame tools
		textField = new JTextField(20); // 20 columns
		textField.addActionListener(this);

		textArea = new JTextArea(8, 20); // 8 rows, 20 columns
		// this container is meant to log output and shouldn't be editable
		textArea.setEditable(false);

		label = new JLabel(frameLabel);

		// Setup the scroll pane for text area container
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Initialize grid constraints to define sizing/position for 
		// components in the layout manager
		GridBagConstraints c = new GridBagConstraints();

		// setup constraints for how components are resized in the window
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
}
