package frametools;

import java.awt.event.ActionListener;

/**
 * A window that accepts strings from user input and 
 * displays the boolean result of a strings palindromicity.
 * Results are displayed in a non-editable text area.
 * @author TannerDolby
 *
 */
public class PalindromeDemo extends FrameBuilder implements ActionListener {
	private static final long serialVersionUID = -6523249222414784352L;
	private static FrameBuilder frameBuilder;
	
	public PalindromeDemo() {
		super(frameTitle, frameLabel, toolToRun);	
	}

	public static void main(String[] args) {
		// schedule job on event-dispatching thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// render and start GUI
				frameBuilder = new FrameBuilder("Palindrome Demo", "Enter input string:", "isPalindrome");
				frameBuilder.setup();
				frameBuilder.initGUI();
				System.out.println("Running on thread: " + Thread.currentThread());
			}
		});
	}
}
