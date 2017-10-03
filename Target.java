/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	public void run() {
		
		//* create three ovals of specified dimensions, centered on canvas
		GOval outer = new GOval((getWidth() - (DIM_OUTER * INCH_TO_PIX)) / 2, (getHeight() - (DIM_OUTER * INCH_TO_PIX)) / 2, DIM_OUTER * INCH_TO_PIX, DIM_OUTER * INCH_TO_PIX);
		GOval mid = new GOval ((getWidth() - (DIM_MID * INCH_TO_PIX)) / 2, (getHeight() - (DIM_MID * INCH_TO_PIX)) / 2, DIM_MID * INCH_TO_PIX, DIM_MID * INCH_TO_PIX);
		GOval inner = new GOval ((getWidth() - (DIM_INNER * INCH_TO_PIX)) / 2, (getHeight() - (DIM_INNER * INCH_TO_PIX)) / 2, DIM_INNER * INCH_TO_PIX, DIM_INNER * INCH_TO_PIX);
		
		//* set fill and colors
		outer.setFilled(true);
		outer.setFillColor(Color.red);
		mid.setFilled(true);
		mid.setFillColor(Color.white);
		inner.setFilled(true);
		inner.setFillColor(Color.red);
		
		//*add circles
		add(outer);
		add(mid);
		add(inner);
	}
	
	//* given dims of circles in inches
	private static final double DIM_OUTER = 1;
	private static final double DIM_MID = .65;
	private static final double DIM_INNER = .3;
	
	//* given conversion of inches to pixels
	private static final double INCH_TO_PIX = 72/1;
	
}
