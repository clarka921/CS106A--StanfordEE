
import acm.program.*;
import acm.graphics.*;
import javax.swing.*;

public class FontSampler extends GraphicsProgram {

	// Constants
	private static final int MAX_FONT_NAME = 30;
	private static final int LEFT_MARGIN = 3;
	private static final String TEST_STRING = "The quick fox jumped over the lazy dog";

	// Instance variables
	private JTextField fontField;
	private GLabel lastLabel;
	private double lastY;

	@Override
	public void init() {

		fontField = new JTextField(MAX_FONT_NAME);
		fontField.addActionListener(this);
		add(new JLabel("Font"), SOUTH);
		add(fontField, SOUTH);
		lastY = 0;
		lastLabel = new GLabel(TEST_STRING);
		add(lastLabel);

	}

	private void addGLabel(GLabel label) {
		lastY += label.getHeight();
		lastY += lastLabel.getDescent() - label.getDescent();
		add(label, LEFT_MARGIN, lastY);
	}

}
