package assign;


import acm.graphics.*;


//this class contains the graphical component of the Hangman game
public class HangmanCanvas extends GCanvas {
	
	
	//instance variables
	char [] wrongLettersGuessed = new char[20];

	//create space for objects
	private GLabel displayWordLabel;
	private GLabel wrongLettersDisplay;


	// public method used to reset GCanvas by removing all objects, then adding scaffold and (blank) GLabels
	public void reset() { 
		removeAll();
		addScaffolding();
		addDisplayWordLabel();
		addWrongLettersDisplay();
	}
	
		
	//method used by reset() that adds in three GLines to make up scaffolding
	private void addScaffolding() {
		double topLine = ((getHeight() - SCAFFOLD_HEIGHT)  /  2);
		double bottomLine = (getHeight()  -  ((getHeight() - SCAFFOLD_HEIGHT) / 2)); 
		double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
		
		GLine horizontalBeam = new GLine(leftLine, topLine, leftLine + BEAM_LENGTH, topLine);
		GLine uprightBeam = new GLine(leftLine, topLine, leftLine, bottomLine);
		GLine noose = new GLine(leftLine + BEAM_LENGTH, topLine, leftLine+BEAM_LENGTH, topLine + ROPE_LENGTH);
		
		add(horizontalBeam);
		add(uprightBeam);
		add(noose);	
		
	}
	
	
	//method adding blank (no string) GLabel that will show the correct characters already guessed and any unguessed characters as '-'
	private void addDisplayWordLabel() { 
		double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
		double bottomLine = (getHeight()  -  ((getHeight() - SCAFFOLD_HEIGHT) / 2));
		double y = bottomLine + 34;
				
		displayWordLabel = new GLabel("", leftLine, y);
		displayWordLabel.setFont("Helvetica-24");
		add(displayWordLabel);
	
	}
	
	
	//method adding blank (no string) GLabel that will show the incorrect letters already guessed.
	private void addWrongLettersDisplay() {
		double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
		double bottomLine = (getHeight()  -  ((getHeight() - SCAFFOLD_HEIGHT) / 2));
		double y = bottomLine + 60;
		
		wrongLettersDisplay = new GLabel("", leftLine, y);
		wrongLettersDisplay.setFont("Helvetica-18");
		add(wrongLettersDisplay);
		
	}
	

	//Updates the word in GLabel displayWordLabel to correspond to the current state of the game. 
	//The argument string shows what letters have been guessed so far; unguessed letters are indicated by hyphens. 
	public void displayWord(char[] rando) {
		String randoStr = String.valueOf(rando);
		displayWordLabel.setLabel(randoStr);
	} 			
	
	
	//Updates the graphical display after an incorrect guess by the user. this method causes the next body part to appear 
	//on the scaffold and adds the letter to the list of incorrect guesses that appears at the bottom of the window. 
	public void noteIncorrectGuess(char wrongCharacter, int incorrectGuessNum) {
		
		wrongLettersGuessed[incorrectGuessNum] = wrongCharacter;
		String wrongLettersGuessedStr = String.valueOf(wrongLettersGuessed);
		wrongLettersDisplay.setLabel(wrongLettersGuessedStr);
		
		if(incorrectGuessNum == 0) {
			double topLine = ((getHeight() - SCAFFOLD_HEIGHT)  /  2);
			double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
			
			double x = (leftLine + BEAM_LENGTH) - HEAD_RADIUS;
			double y = topLine + ROPE_LENGTH;
			
			GOval Head = new GOval (x, y, HEAD_RADIUS*2, HEAD_RADIUS*2);
			add(Head);
			
			
		} else if (incorrectGuessNum == 1) {
			double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
			double topLine = ((getHeight() - SCAFFOLD_HEIGHT)  /  2);
			
			double x1 = leftLine + BEAM_LENGTH;
			double y1 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2);
			double x2 = leftLine + BEAM_LENGTH;
			double y2 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
			
			GLine Body = new GLine(x1, y1, x2, y2);
			add(Body);
			
			
		} else if (incorrectGuessNum == 2) {
			double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
			double topLine = ((getHeight() - SCAFFOLD_HEIGHT)  /  2);
			
			double x1 = leftLine + BEAM_LENGTH;
			double x2 = leftLine + BEAM_LENGTH + UPPER_ARM_LENGTH;
			double x3 = leftLine + BEAM_LENGTH + UPPER_ARM_LENGTH;
			double y1 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
			double y2 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
			double y3 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;
			
			GLine RightUpperArm = new GLine (x1, y1, x2, y2);
			GLine RightLowerArm = new GLine (x2, y2, x3, y3);
			add(RightUpperArm);
			add(RightLowerArm);
			
			
		} else if (incorrectGuessNum == 3) {
			double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
			double topLine = ((getHeight() - SCAFFOLD_HEIGHT)  /  2);
			
			double x1 = leftLine + BEAM_LENGTH;
			double x2 = leftLine + BEAM_LENGTH - UPPER_ARM_LENGTH;
			double x3 = leftLine + BEAM_LENGTH - UPPER_ARM_LENGTH;
			double y1 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
			double y2 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;
			double y3 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;
			
			GLine LeftUpperArm = new GLine (x1, y1, x2, y2);
			GLine LeftLowerArm = new GLine (x2, y2, x3, y3);
			add(LeftUpperArm);
			add(LeftLowerArm);
			
			
		} else if (incorrectGuessNum == 4) {
			double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
			double topLine = ((getHeight() - SCAFFOLD_HEIGHT)  /  2);
			
			double x1 = leftLine + BEAM_LENGTH;
			double x2 = leftLine + BEAM_LENGTH + HIP_WIDTH;
			double x3 = leftLine + BEAM_LENGTH + HIP_WIDTH;
			double y1 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
			double y2 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
			double y3 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH;
			double x4 = leftLine + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH;
			double y4 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH;
			
			GLine RightUpperLeg = new GLine (x1, y1, x2, y2);
			GLine RightLowerLeg = new GLine (x2, y2, x3, y3);
			GLine RightFoot = new GLine (x3, y3, x4, y4);
			add(RightUpperLeg);
			add(RightLowerLeg);
			add(RightFoot);
			
			
		} else if (incorrectGuessNum == 5) {
			double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
			double topLine = ((getHeight() - SCAFFOLD_HEIGHT)  /  2);
			
			double x1 = leftLine + BEAM_LENGTH;
			double x2 = leftLine + BEAM_LENGTH - HIP_WIDTH;
			double x3 = leftLine + BEAM_LENGTH - HIP_WIDTH;
			double y1 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
			double y2 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
			double y3 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH;
			double x4 = leftLine + BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH;
			double y4 = topLine + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH;
			
			GLine LeftUpperLeg = new GLine (x1, y1, x2, y2);
			GLine LeftLowerLeg = new GLine (x2, y2, x3, y3);
			GLine LeftFoot = new GLine (x3, y3, x4, y4);
			add(LeftUpperLeg);
			add(LeftLowerLeg);
			add(LeftFoot);
			
			
		} else if (incorrectGuessNum == 6) {
		
			double topLine = ((getHeight() - SCAFFOLD_HEIGHT)  /  2);
			double leftLine = ((getWidth() / 2) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2));
			
			double x1 = leftLine + BEAM_LENGTH + 12;
			double x2 = leftLine + BEAM_LENGTH - 18;
			double y1 = topLine + ROPE_LENGTH + 32;
			
			GLabel RightEye = new GLabel ("X", x1, y1);
			GLabel LeftEye = new GLabel ("X",x2, y1);
			RightEye.setFont("Helvetica-14");
			LeftEye.setFont("Helvetica-14");
			add(RightEye);
			add(LeftEye);
		}
	
	}
			

	// Constants for the simple version of the picture (in pixels)
	private static final int SCAFFOLD_HEIGHT = 360; 
	private static final int BEAM_LENGTH = 144; 
	private static final int ROPE_LENGTH = 18; 
	private static final int HEAD_RADIUS = 36; 
	private static final int BODY_LENGTH = 144; 
	private static final int ARM_OFFSET_FROM_HEAD = 28; 
	private static final int UPPER_ARM_LENGTH = 72; 
	private static final int LOWER_ARM_LENGTH = 44; 
	private static final int HIP_WIDTH = 36; 
	private static final int LEG_LENGTH = 108; 
	private static final int FOOT_LENGTH = 28; 

	
	
	
	
	
}


