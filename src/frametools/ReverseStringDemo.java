package frametools;

import java.awt.event.ActionListener;

/**
 * A lightweight container that accepts strings from user input and 
 * displays the reversed strings in a non-editable text area.
 * @author TannerDolby
 *
 */
public class ReverseStringDemo extends FrameBuilder implements ActionListener {
	private static final long serialVersionUID = 6741401887784258365L;
	private static FrameBuilder frameBuilder;
	
	public ReverseStringDemo() {
		super(frameTitle, frameLabel, toolToRun);
	}

	public static void main(String[] args) {
		// Schedule a new job/event using the "event dispatching" thread
		// Swing data-structures are not thread-safe 
		// so we schedule jobs on the AWT event-dispatching thread 
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// render GUI
				frameBuilder = new FrameBuilder("Reverse String Demo", "Enter input string:", "reverseStr");
				frameBuilder.setup();
				frameBuilder.initGUI();
				System.out.println("Running on thread: " + Thread.currentThread());
			}
		});
	}
}
