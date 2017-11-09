package assign3;

/*
 * A program to play Breakout! The only thing I did differently from the assignment directions is to use the mouse rather
 * than the keyboard to move the paddle. Click on the paddle and move the mouse left/right to move the paddle. Game ends
 * when all bricks are knocked out or the player has run through all their balls. I added a scoreboard showing the number 
 * bricks and balls remaining. 
 *  
 */

import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import acm.graphics.*;

public class Breakout extends GraphicsProgram {
	
	//private instance variables.
	private GObject curPadPos;
	private GLabel BrickDis;
	private GLabel BallDis;
	private GPoint lastPadPos;
	private double vx = (0.0);
	private double vy = (0.0);
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private GOval ball;
	private GRect paddle;
	private int numberBricks = NBRICK_ROWS * NBRICKS_PER_ROW;
	private int numberBalls = NTURNS;
	
	//run method... first playing board/pieces are set, then game progresses until all bricks are removed, 
	//or player runs through all their balls
	public void run() {
			setBricks();
			setScoreBoard();
			setPaddle();
			setBall();
			while(numberBalls > 0 && numberBricks > 0) {
				moveBall();
				checkForCollision();
			}
			//if statement checking # of bricks/balls to detemrine when player has won/lost
			if(numberBalls == 0) {
				youLose();
			} else if(numberBricks == 0) {
				youWin();
			}
	}		
	
	//create and instatiate bricks
	public void setBricks() {		
		for( int i = 0; i < NBRICK_ROWS; i++) {
				//for loop to lay down individual bricks
				for (int j = 0; j < NBRICKS_PER_ROW; j++) {
					//*for both x and y, set starting position and add/subtract the appropriate distance to get the next brick/row position 
					int x = (BRICK_SEP/2) + ((BRICK_SEP + BRICK_WIDTH) * j); 
					int y = BRICK_Y_OFFSET + ((BRICK_SEP + BRICK_HEIGHT) * i);
					GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
					if(i == 0 | i == 1) {
						brick.setFilled(true);
						brick.setFillColor(Color.RED);
					} else if (i == 2 | i == 3) {
						brick.setFilled(true);
						brick.setFillColor(Color.ORANGE);
					} else if (i == 4 | i == 5) {
						brick.setFilled(true);
						brick.setFillColor(Color.YELLOW);
					} else if (i == 6 | i == 7) {
						brick.setFilled(true);
						brick.setFillColor(Color.GREEN);
					} else if (i == 8 | i == 9) {
						brick.setFilled(true);
						brick.setFillColor(Color.CYAN);
					}
					add(brick);
				}
			}
	}
	
	//instatiate paddle and add mouselisteners
	public void setPaddle() {
		paddle = new GRect( (getWidth() - PADDLE_WIDTH) / 2, getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.lightGray);
		add(paddle);
		addMouseListeners();
	}	
	
	//two methods enabling the mouse and mouse button to control motion of the paddle. I modified this directly from
	//the 'dragobjects' example on p.359 of the book. only the paddle can be moved by clicking on it.
	public void mousePressed(MouseEvent e) {
		lastPadPos = new GPoint(e.getPoint());
		curPadPos = getElementAt(lastPadPos);
	}
			
	public void mouseDragged(MouseEvent e) {
		if (curPadPos == paddle) {
			curPadPos.move(e.getX() - lastPadPos.getX(), 0);
			lastPadPos = new GPoint (e.getPoint());
		}
	}
	
	//instatiate two Gblabels- collectively the scoreboard
	private void setScoreBoard() {
		BrickDis = new GLabel("Brick(s) Remaining: " + numberBricks, 10, getHeight()/2);
		BallDis= new GLabel("Ball(s) Remaining:" + numberBalls, 10, BrickDis.getY() - 10);
		BrickDis.setFont("Helvetica-11");
		BallDis.setFont("Helvetica-11");
		add(BrickDis);
		add(BallDis);
	}
	
	//instantiate ball object and set vx/vy to a random double 
	private void setBall() {
		ball = new GOval( getWidth()/2 - BALL_RADIUS, getHeight() / 2 - BALL_RADIUS, 2 * BALL_RADIUS, 2 * BALL_RADIUS );
		ball.setFilled(true);
		ball.setFillColor(Color.yellow);
		add(ball);
		vx = rgen.nextDouble(1.0, 4.0);
		vy = rgen.nextDouble(3.0, 5.5);
		if(rgen.nextBoolean(.5)) vx = -vx;
		pause(DELAY * 75);
	}
	
	//move the ball vx and vy
	private void moveBall() {
		ball.move(vx, vy);
		pause(DELAY);
	}
	
	// Determine if collision with walls, paddle, or bricks and define actions
 	private void checkForCollision() {
 		GObject collider = getCollidingObject();
 		// checking conditions to see if the ball has gone past theorugh the South wall (past the paddle). if so, the number of balls is reduced by one and the ball resets.
 		if (ball.getY() - 5 > getHeight()) {
 			numberBalls--;
 			updateScoreboard();
 			remove(ball);
 			setBall();
 		//if ball hits N/E/W 'wall', vy/vx reverse and speed increases	
 		}else if(ball.getY() < 1) {
 			vy = -vy * SPEED_INCR_FACTOR;
 		}else if(ball.getX() > getWidth() - (BALL_RADIUS * 2) | ball.getX() < 1) {
 			vx = -vx * SPEED_INCR_FACTOR;
 		//if ball hits paddle, reverse vy
 		} else	if (collider == paddle) {
 	 		vy = -vy;
 	 	 //if ball its brick, remove the brick and update the scorboard	
 	 	 }else if (collider != null && collider != BrickDis && collider != BallDis) {
 	 	 	remove(collider);
 	 	 	vy = -vy;
 	 	 	numberBricks--;
 	 	 	updateScoreboard();
 	 	 }
 	 }
 	
	//method returning the GObject that the ball collides with, tests each corner of ball succesively running clockwise form top left
 	private GObject getCollidingObject() {
 		GObject gobj;
 		gobj = null;
 		gobj = getElementAt(ball.getX(), ball.getY());
 		if (gobj != null) {
 			return gobj;
	 		}else{
	 			gobj = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
	 			if (gobj != null) {
	 				return gobj;
	 			}else {
	 				gobj = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
	 				if (gobj != null) {
	 					return gobj;
	 				}else {
	 					gobj = getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
	 					if (gobj != null) {
	 						return gobj;
	 					}
	 				}	
	 			}
	 		}
 		return gobj;
 	}
 	
 	//method updating the scoreboard after a brick has been knocked out or a ball lost
 	private void updateScoreboard() {
 		BrickDis.setLabel("Bricks(s) Remaining:" + numberBricks);
 		BallDis.setLabel("Ball(s) Remaining:" + numberBalls);
 	}
 	
 	//method displaying a message to the winner
 	private void youWin() {
 		GLabel win = new GLabel("Congratulations!!! You've won the game by knocking out all the bricks!", 10, BallDis.getY() + 45 );
 		win.setFont("Helvetica-14");
 		add(win);		
 	}
 		
 	//method displaying a message to a loser
 	private void youLose() {
 		GLabel lose = new GLabel("GAME OVER. You've lost all your balls!", 10, BallDis.getY() + 45 );
 		lose.setFont("Helvetica-14");
 		add(lose);	
 	}
	
	//delay slowing down motion of ball
	private static final int DELAY = 15;
	
	//speed increase factor after a collision
	private static final double SPEED_INCR_FACTOR = 1.025;
			
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;
	
	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;
	
	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;
	
	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 6;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
}
