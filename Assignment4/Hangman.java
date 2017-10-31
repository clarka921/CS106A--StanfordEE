package assign;


import acm.program.*;
import acm.util.*; 


//this class plays the game hangman
public class Hangman extends ConsoleProgram {

	
	//instance variables
	private char[] dashedWord;
	private int totalWrongGuessesAvail = 7;
	private int incorrectGuess = 0;
	
	//create space for objects
	private HangmanCanvas canvas = new HangmanCanvas();
	private HangmanLexicon lexicon = new HangmanLexicon();
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private String randomWord;
	
	
	
	
	
	
	//method adding graphical (canvas) component of game
	public void init() {
		add(canvas);
	}
	
	//run method executing game
	public void run() {
		
		chooseRandomWord();
		createDashedWord();
		println("Welcome to Hangman!");
		
		canvas.reset();
		
		showWelcomeMessage();
		while(incorrectGuess < totalWrongGuessesAvail) {
			canvas.displayWord(dashedWord);
			acceptUserEntry();
			displayGuessesRemain();
			if(randomWord.equals(String.valueOf(dashedWord))) break;
		}
		
		endGame();
	}
	
	
	
	
	
	
	//call HangmanLexicon to find, read, and close word array.
	//select random word from array and initialize instance variable with 
	private void chooseRandomWord() {
		lexicon.addToArrayList();
		lexicon.closeReader();
		int listIndex = rgen.nextInt(lexicon.getWordCount());
		randomWord = lexicon.getElement(listIndex);	
		println("the random word is " + randomWord);
	}
	
	
	//set length of instance variable dashedWord to == length.randomWord and initialized all characters to '-'
	private void createDashedWord() {
		dashedWord = new char[randomWord.length()];
		for(int i = 0; i < randomWord.length(); i++) {
			dashedWord[i] = '-';
		}
	}
	
	public void showWelcomeMessage() {
		println("");
		println("We have randomly selected a " + randomWord.length() + " character word for you.");
		println("");
		println("Keep choosing letters until you've completely filled in the word...");
		println("");
		println("Or until the poor fellow on the right is complete...");
		println("");
		println("Good luck, pardner!");
		println("");
	}
	
	
	//print short message and prompt user to enter letter. If character entered (only first character considered, case not important) matches a character in randomWord, 
	//that character will be replace the appropriate dash in dashedWord. if the character entered doesn't match anything in randomWord, the 
	//GLabel WrongLetters display will be updated with that letter 
	private void acceptUserEntry() {
		
		String str = String.valueOf(dashedWord);
		
		String userEntry = readLine("Your guess: ");
		String userLetter = userEntry.toUpperCase();
		char userLetterCap = userLetter.charAt(0);
		
		for(int i = 0; i < randomWord.length(); i++) {
			if (userLetterCap == randomWord.charAt(i)) {
				dashedWord[i] = userLetterCap; 
			}	
		}
		
		String dashedWordStr = String.valueOf(dashedWord);
		if(!str.equals(dashedWordStr)) {
			println("Yes!");
		}
		if (str.equals(dashedWordStr)) {
			canvas.noteIncorrectGuess(userLetterCap, incorrectGuess);
			println("D'oh!");
			incorrectGuess++;
		}
	}
	
	
	//show user how many guesses they have left
	private void displayGuessesRemain() {
		if( totalWrongGuessesAvail - incorrectGuess == 1 ) {
			println("You have " + (totalWrongGuessesAvail - incorrectGuess) + " guess remaining.");
			println("");
		}else {
			println("You have " + (totalWrongGuessesAvail - incorrectGuess) + " guesses remaining.");
			println("");
		}
	}	
	
		
	//display end game messages
	private void endGame() {
		char [] randomWordChar = randomWord.toCharArray();
		canvas.displayWord(randomWordChar);
		
		if(randomWord.equals(String.valueOf(dashedWord))) {
			
			String dashedWordStr = String.valueOf(dashedWord);
			println("");
			println("And the word was... " + dashedWordStr);
			println("Congratulations! Your superior command of orthography has saved yet another life!");
			
		}else {
			
			println("");
			println("Too bad!");
			println("The word we were looking for was " + randomWord + ".");
			println("");
			println("Better luck next time, Space Cowboy!");
		}
	}
  
	
}




