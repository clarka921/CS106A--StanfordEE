/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

/*
 * program to create a diagram of class hierarchy with GLabel, GRect, and GLine- straight forward except for positioning of objects, which is
 * relative except for X_SPACING and Y_SPACING, which are named constants.
 */


public class ProgramHierarchy extends GraphicsProgram {	
	public void run() {
		
		/*
		 * create and add GRects, figure centered in horizontal dimension and beginning 1/4 between top and bottom in vertical dimension.
		 * Spacing between boxes set as constants (totally arbitrary).
		 */
		
		GRect pb = new GRect((getWidth()-X_DIM)/2, (getHeight()-Y_DIM)/4, X_DIM, Y_DIM);
		GRect gpb = new GRect(pb.getX() - X_DIM - X_SPACING, pb.getY() + Y_DIM + Y_SPACING, X_DIM, Y_DIM);
		GRect cpb = new GRect((getWidth()-X_DIM) / 2, pb.getY() + Y_DIM + Y_SPACING, X_DIM, Y_DIM);
		GRect dpb = new GRect(pb.getX() + X_DIM + X_SPACING, pb.getY() + Y_DIM + Y_SPACING, X_DIM, Y_DIM);
		
		add(pb);
		add(gpb);
		add(cpb);
		add(dpb);
		
		/*
		 * create and add GLabels, centered in GRects. Placed code below GLabel and defined position in method add() because,
		 * I believe, Java requires objects to be already defined before they can be used as instance variables.
		 */
		
		GLabel p = new GLabel("Program");
		GLabel gp = new GLabel("GraphicsProgram", getWidth()/2, getHeight()/2);
		GLabel cp = new GLabel("ConsoleProgram", getWidth()/4, getHeight()/2);
		GLabel dp = new GLabel("DialogProgram", getWidth()/8, getHeight()/2);
		
		add(p, pb.getX() + .5 * (pb.getWidth() - p.getWidth()), pb.getY() + .5 * (pb.getHeight() + p.getAscent()));
		add(gp, gpb.getX() + .5 * (gpb.getWidth() - gp.getWidth()), gpb.getY() + .5 * (gpb.getHeight() + gp.getAscent()));
		add(cp, cpb.getX() + .5 * (cpb.getWidth() - cp.getWidth()), cpb.getY() + .5 * (cpb.getHeight() + cp.getAscent()));
		add(dp, dpb.getX() + .5 * (dpb.getWidth() - dp.getWidth()), dpb.getY() + .5 * (dpb.getHeight() + dp.getAscent()));
	
		/*
		 * create and add GLines, all start positions at bottom center of GRect pb, end positions at top center of relevant GRect.
		 */
		
		GLine gpl = new GLine(pb.getX() + .5 * pb.getWidth(), pb.getY() + pb.getHeight(), gpb.getX() + .5 * gpb.getWidth(), gpb.getY());
		GLine cpl = new GLine(pb.getX() + .5 * pb.getWidth(), pb.getY() + pb.getHeight(), cpb.getX() + .5 * cpb.getWidth(), cpb.getY());
		GLine dpl = new GLine(pb.getX() + .5 * pb.getWidth(), pb.getY() + pb.getHeight(), dpb.getX() + .5 * dpb.getWidth(), dpb.getY());
	
		add(gpl);
		add(cpl);
		add(dpl);
	}
	
	/*
	 * named constants for GRect dims and spacing between Grects.
	 */
	
	private static final double X_DIM = 150;
	private static final double Y_DIM = 75;
	private static final double X_SPACING = 20;
	private static final double Y_SPACING = 60;
	
}


