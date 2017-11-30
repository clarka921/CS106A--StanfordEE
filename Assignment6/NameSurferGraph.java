
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	// Instance Variables
	private ArrayList<NameSurferEntry> list = new ArrayList<NameSurferEntry>();
	private ArrayList<Color> colorList = new ArrayList<Color>();
	private ArrayList<Integer> labelNumberList = new ArrayList<Integer>();

	// Random generator- used to pick color for name entry, which is then stored in
	// an ArrayList colorList, in parrallel with ArrayList.colorList
	private RandomGenerator rgen = RandomGenerator.getInstance();

	// Class constructor
	public NameSurferGraph() {
		addComponentListener(this);
	}

	// Clear all objects from ArrayList <NameSurferEntry> list
	public void clearList() {
		list.clear();
		colorList.clear();
		labelNumberList.clear();
	}

	// Adds entry to ArrayList <NameSurferEntry> list
	public void addEntry(NameSurferEntry entry) {
		list.add(entry);
		colorList.add(rgen.nextColor());
		labelNumberList.add(rgen.nextInt(2, NDECADES - 2));
	}

	// Totally clears canvas then adds back in graph grid and labels, and data lines
	// and data labels for NameSurferEntries in list
	public void update() {
		removeAll();
		makeGraphBackground();
		drawNameUserEntries();
	}

	// Adds lines and decade labels to graph
	private void makeGraphBackground() {
		add(new GLine(0, 20, getWidth(), 20));
		add(new GLine(0, getHeight() - 20, getWidth(), getHeight() - 20));
		for (double i = 0.0; i < NDECADES; i++) {

			double x0 = getWidth() * (i / NDECADES);
			double x1 = getWidth() * (i / NDECADES); // (i/NDECADES) * getWidth();

			double y0 = 0;
			double y1 = getHeight();
			add(new GLine(x0, y0, x1, y1));

			double decade = ((i * 10) + 1900);
			String str = String.valueOf((int) decade);

			double x = getWidth() * (i / NDECADES) + 5;
			double y = getHeight() - 5;
			add(new GLabel(str, x, y));
		}
	}

	// Draws data lines for entered names
	private void drawNameUserEntries() {

		// Run through ArrayList list, element-by-element
		for (int i = 0; i < list.size(); i++) {

			NameSurferEntry current = list.get(i);
			String name = current.getName();

			double labelDecadeLocation = labelNumberList.get(i);
			Color lineColor = colorList.get(i);

			// For the current NameSurferEntry, use getRank() to create each data-line,
			// decade by decade
			for (double n = 0; n < NDECADES - 1; n++) {

				double rank = current.getRank((int) n);
				double rankNPlus1 = current.getRank((int) (n + 1));
				if (rank == 0)
					rank = 1000;
				if (rankNPlus1 == 0)
					rankNPlus1 = 1000;

				double x0 = getWidth() * (n / NDECADES);
				double y0 = 20 + ((getHeight() - 40) * (rank / 1000));

				double x1 = getWidth() * ((n + 1) / NDECADES);
				double y1 = 20 + ((getHeight() - 40) * (rankNPlus1 / 1000));

				GLine line = new GLine(x0, y0, x1, y1);

				line.setColor(lineColor);
				add(line);
				
				if (rankNPlus1 != 1000) {
					String strRank = String.valueOf((int) rankNPlus1);
					add(new GLabel(strRank, x1 + 5, y1 + 13));
				}

				if(n == labelDecadeLocation) {
					add(new GLabel(name, x1 + 5, y1 - 13));
				}	
			}
			
			
		}
	}

	/* Implementation of the ComponentListener interface */
	
	public void componentHidden(ComponentEvent e) {
	}

	
	public void componentMoved(ComponentEvent e) {
	}

	
	public void componentResized(ComponentEvent e) {
		update();
	}

	
	public void componentShown(ComponentEvent e) {
	}

} // END OF CLASS. CREATED BY ACLARK 2017
