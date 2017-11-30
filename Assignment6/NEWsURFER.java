
import acm.program.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;

public class NEWsURFER extends Program implements NameSurferConstants {

	// Instance variables
	private JLabel nameLabel = new JLabel("Name");
	private JTextField nameTextField = new JTextField(10);
	private JButton graphButton = new JButton("Graph");
	private JButton clearButton = new JButton("Clear");

	private NameSurferGraph canvas;

	
	JPanel mainPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	

	private HashMap<String, NameSurferEntry> hashMap = new HashMap<String, NameSurferEntry>();
	// End instance variables

	public void init() {

		loadNameSurferEntry();
		canvas = new NameSurferGraph(hashMap);
		
		setupGUI();

		addActionListeners();
		nameTextField.addActionListener(this);

	}

	// Sets up GUI with calls to various display components
	public void setupGUI() {

		// A border layout lays out a container, arranging and resizing its components
		// to fit in five regions: north, south, east, west, and center
		// This is the syntax for setting one up... doesn't make sense to me, but it has
		// to be done
		mainPanel.setLayout((new BorderLayout()));

		buttonPanel.add(nameLabel);
		buttonPanel.add(nameTextField);
		buttonPanel.add(graphButton);
		buttonPanel.add(clearButton);

		add(mainPanel);
		mainPanel.add(buttonPanel, SOUTH);
		mainPanel.add(canvas, CENTER);

	}

	// Open buffered reader and read in lines to create HashMap of names and
	// NameSurferEntry objects
	public void loadNameSurferEntry() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader("names-data.txt"));

			while (true) {

				String line = reader.readLine();
				if (line == null)
					break;
				NameSurferEntry newNameSurferEntry = parseLine(line);
				hashMap.put(newNameSurferEntry.getName(), newNameSurferEntry);

			}
			reader.close();
		} catch (IOException ex) {
			System.out.println("Sometins screwy wit' tha' reader, cap'n!");
		}
	}

	// Parse line read from document into String and Array[] and create
	// NameSurferEntry with data
	private NameSurferEntry parseLine(String line) {

		// Extract name to String
		int entryNameStart = 0;
		int entryNameEnd = line.indexOf(" ");
		String nameSurferEntryName = line.substring(entryNameStart, entryNameEnd);

		// Reads through decade rank info and places each found decade/rank in an
		// ArrayList
		ArrayList<Integer> rankByDecade = new ArrayList<Integer>();

		int decadeReadStartIndex = entryNameEnd;

		for (int i = 0; i < NDECADES; i++) {

			int decadeStart = line.indexOf(" ", decadeReadStartIndex) + 1;
			int decadeEnd = line.indexOf(" ", decadeStart);

			// DecadeEnd == -1 at the end of the string
			if (decadeEnd == -1) {
				int currentElement = Integer.parseInt(line.substring(decadeStart));
				rankByDecade.add(i, currentElement);
			} else {
				int currentElement = Integer.parseInt(line.substring(decadeStart, decadeEnd));
				rankByDecade.add(i, currentElement);
				decadeReadStartIndex = decadeEnd;
			}
		}
		return (new NameSurferEntry(nameSurferEntryName, rankByDecade));
	}
	
	
	public int hashMapSize() {
		return hashMap.size();
	}
	
	

	// Dictate what happens when Graph or Clear buttons are pressed
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == graphButton) {

			try {

				String userEntry = nameTextField.getText();
				NameSurferEntry currentEntry = hashMap.get(userEntry);
				
				System.out.println(currentEntry.toString());
				System.out.println("the size of the hashmap is " + hashMapSize());
				
				canvas.addEntry(currentEntry);
				canvas.update();
				
			} catch (NullPointerException g) {
				System.out.println("There was an error adding the user entry to the array list");
				
			}

		} else if (e.getSource() == clearButton) {

			canvas.clearList();
			canvas.update();

		}
	}

} // END OF CLASS. CREATED BY ACLARK 2017
