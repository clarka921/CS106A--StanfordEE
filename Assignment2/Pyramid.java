
//*Pyramid.java*/


import acm.graphics.*;
import acm.program.*;
import java.awt.*;

/*
 * this program draws a pyramid, with the number of bricks in the base defined as anamed constant, and each successive layer of bricks containing one fewer
 * bricks than the layer below. the pyramid ascends until a row is created with only a single brick. the pyramid is centered horizontally and vertically in
 * the window.
 */


public class Pyramid extends GraphicsProgram {
	
	public void run() {
		
		//*we should build as many rows as there are bricks in base
		for( int i = 0; i < BRICKS_IN_BASE; i++) {
			
			//* starting with the number of BRICKS_IN_BASE, bricks per row go down by 1 as we go up
			int bricksrow = BRICKS_IN_BASE - i;
			for (int j = 0; j < bricksrow; j++) {
	
				//*for both x and y, set starting position and add/subtract the appropriate distance to get the next brick/row position 
				double x = ((getWidth() - (bricksrow * BRICK_WIDTH)) / 2) + (j * BRICK_WIDTH); 
				double y = ((getHeight() + (BRICKS_IN_BASE * BRICK_HEIGHT)) / 2) - (i * BRICK_HEIGHT);
				GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			}
		}
	}
	
/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 10;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 4;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 80;
	
}

