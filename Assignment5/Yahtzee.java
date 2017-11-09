/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;



public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	
	//Private instance variables
	private int nPlayers;
	private int categorySelected;
	private int calculatedScore;
	
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	private String[] playerNames;
	private int [] currentDieArrang = new int[N_DICE];
	private int [] histogramArray = new int[6];
	private int [][] scoreArray;
	private boolean [][] catAvailArray;
	
	
	public static void main(String[] args) {
		
		new Yahtzee().start(args);
		
	}
	
	//Initializes game board and takes ID info from player
	public void run() {
		
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		initScoreArray();
		initCatAvailArray();
		
		//Nested for-loops setting the number of game rounds to N_SCORING_CATEGORIES * nPlayers
		//Methods within for-loops control dice rolling and scoring
		for(int currentTurn = 0; currentTurn < N_SCORING_CATEGORIES ; currentTurn++) {
			for (int currentPlayer = 1; currentPlayer <= nPlayers; currentPlayer++) {
	
				rollEm(currentPlayer);
				checkCategoryAvail(currentPlayer);
				calculateScore(categorySelected);
				updateScoreArrays(categorySelected, currentPlayer, calculatedScore);
				computeUpperScore(currentPlayer);
				computeLowerScore(currentPlayer);
				computeTotalScore(currentPlayer);
				
			}
		}
		
		computeBonus();
		computeFinalScores();
		endGame();
		
	}
	
	
	//Sets up scoreArray
	private void initScoreArray() {
		
		scoreArray = new int[N_CATEGORIES][nPlayers];
		
		//set scoreArray initial values to 0
		for(int row = 0; row < N_CATEGORIES; row++) {
			for(int col = 0; col < scoreArray[row].length; col++) {
				scoreArray[row][col] = 0;
			}
		}
	}
	
	
	//Sets up categoryAvailArray
		private void initCatAvailArray() {
			
			catAvailArray = new boolean[N_CATEGORIES][nPlayers];
			
			//set catAvailArray initial values to TRUE
			for(int row = 0; row < N_CATEGORIES; row++) {
				
				for(int col = 0; col < catAvailArray[row].length; col++) {
					
					catAvailArray[row][col] = true;
				}
			}
		}
	
	
	
	
	//Calls dice-rolling methods and set display.displayDice to equal current die configuration
	private void rollEm(int player) {
		
		String playerName = playerNames[player - 1];   
		
		rollAllDice(player, playerName);
		for (int roll = 0; roll < 2; roll++) {
			rollSelectedDice(playerName, roll);
		}
	}	
	
	
	//Roll all dice
	private void rollAllDice(int currentPlayer, String playerName) {
		
		display.printMessage(playerName + "'s turn.");
		display.waitForPlayerToClickRoll(currentPlayer);
		
		for(int i = 0; i < N_DICE; i++) {
			int random = rgen.nextInt(1,6);
			currentDieArrang[i] = random;
		}
		
		display.displayDice(currentDieArrang);
	}
	
	
	//Roll selected dice
	private void rollSelectedDice(String playerName, int roll) {
		
		switch(roll) {
		
		case 0:	display.printMessage(playerName + "'s second roll. Click on any dice you'd like to re-roll.");
				break;
				
		case 1:	display.printMessage(playerName + "'s final roll. Click on any dice you'd like to re-roll.");
				break;
		}
		
		display.waitForPlayerToSelectDice();
		
		for (int i = 0; i < N_DICE; i++) {
			
			if(display.isDieSelected(i) == true) {
				
				int random = rgen.nextInt(1,6);
				currentDieArrang[i] = random;
			}
		}
		
		display.displayDice(currentDieArrang);
	}
	

	//Checks if category is available ( to be scored in
	private void checkCategoryAvail(int player) {
		
		while (true) {
			
			display.printMessage("Pick which category you would like to assign your points.");
			categorySelected = display.waitForPlayerToSelectCategory();
			
			if(catAvailArray[categorySelected - 1][player -1] == true) {
				
				catAvailArray[categorySelected - 1][player -1] = false;
				break;
			
			}else {	
				
				display.printMessage("Sorry " + playerNames[player - 1] + ", but you've already used that category. Please choose another.");	
			}
		}	
	}		
		
	
	//Calculates score based on category selected by current player
	private void calculateScore(int categ) {
		calculatedScore = 0;      
		
		switch(categ) {
			//ONES category
			case 1:		onesThroughSixes(categorySelected);
						break;
		
			//TWOS category
			case 2: 	onesThroughSixes(categorySelected);
						break;
		
			//THREES category
			case 3:		onesThroughSixes(categorySelected);
						break;
		
			//FOURS category	
			case 4:		onesThroughSixes(categorySelected);
						break;
		
			//FIVES category	
			case 5:		onesThroughSixes(categorySelected);
						break;
		
			//SIXES category
			case 6:		onesThroughSixes(categorySelected);
						break;
		
			//THREE_OF_A_KIND category
			case 9:		zeroHistoArray();
						setHistoArrayCount();
		
						//Checks through histogramArray[] to see if any dice values were rolled 3+ times. 
						//If so, calculatedScore equals the sum of all dice values
						for(int x = 0; x < 6; x++) {
							int frequency = histogramArray[x];
							if (frequency >= 3) {
								for(int i = 0; i < N_DICE; i++) {
									calculatedScore += currentDieArrang[i];  
								}
							}	
						}
						if(calculatedScore == 0) {
							display.printMessage("Oh, too bad! No points this time.");
						}
						break;
			
			//FOUR_OF_A_KIND category
			case 10:	zeroHistoArray();
						setHistoArrayCount();	
			
						//Checks through histogramArray[] to see if any dice values were rolled 4+ times
						//If so, calculatedScore equals the sum of all dice values
						for(int x = 0; x < 6; x++) {
							int frequency = histogramArray[x];
							if (frequency >= 4) {
								for(int i = 0; i < N_DICE; i++) {
									calculatedScore += currentDieArrang[i]; 
								}	
							}
						}
						if(calculatedScore == 0) {
							display.printMessage("Oh, too bad! No points this time.");
						}
						break;
						
			//FULL_HOUSE category	
			case 11:	zeroHistoArray();
						setHistoArrayCount();
			
						//Checks through histogramArray[] for one dice value that was rolled 3 times
						//and for another dice value that was rolled two times. If both are found, calculatedScore is set to 25
						boolean threeOfAKind = false;
						boolean twoOfAKind = false;
						for(int i = 0; i < 6; i++) {
							int frequency = histogramArray[i];
							if (frequency == 3) {
								threeOfAKind = true;
							}
							if (frequency == 2) {
								twoOfAKind = true;
							}
						}	
						if(twoOfAKind && threeOfAKind == true) {	
							calculatedScore = 25;
						}
						if(calculatedScore == 0) {
							display.printMessage("Oh, too bad! No points this time.");
						}
						break;
						
			//SMALL_STRAIGHT category
			case 12:	zeroHistoArray();
						setHistoArrayOneorZero();
			
						//Checks through histogramArray to see if there are 4 consecutive values in histogramArray
						for(int pass = 0; pass <= 2; pass++) {
							int sum = 0;
							for(int i = pass; i <= (pass + 3); i++) {
								sum += histogramArray[i];  
								if(sum == 4) {
									calculatedScore = 30;
								}
							}
						}	
						if(calculatedScore == 0) {
							display.printMessage("Oh, too bad! No points this time.");	
							}
						break;
		
			//LARGE_STRAIGHT category
			case 13:	zeroHistoArray();
						setHistoArrayOneorZero();
			
						//Checks to see if there are 5 consecutive values in histogramArray
						for(int pass = 0; pass <= 1; pass++) {
							int sum = 0;
							for(int i = pass; i <= (pass + 4); i++) {
								sum += histogramArray[i];  
								if(sum == 5) {
									calculatedScore = 40;
								}
							}
						}	
						if(calculatedScore == 0) {
							display.printMessage("Oh, too bad! No points this time.");
						}
						break;
			
			//YAHTZEE! category			
			case 14:	zeroHistoArray();
						setHistoArrayCount();
			
						//Checks through histogramArray[] to see if a dice value was rolled 5 times
						//If so, YAHTZEE! has been achieved and calculatedScore is set to 50
						for(int i = 0; i < 6; i++) {
							int frequency = histogramArray[i];
							if (frequency == 5) {
								calculatedScore = 50; 
							}
						}
						if(calculatedScore == 0) {
							display.printMessage("Oh, too bad! No points this time.");
						}	
						break;
						
			//CHANCE category... calculatedScore is sum of final currentDieArrang	
			case 15:	for(int i = 0; i < N_DICE; i++) {
							calculatedScore += currentDieArrang[i];
						}
						break;
						
		//End switch block				
		}			
	
	//End of calculateScore()	
	}	
	
	
	//Calculates score based on values of qualifying die showing
	private void onesThroughSixes(int categorySelected) {
		
		for(int i = 0; i < N_DICE; i++) {
			
			if(currentDieArrang[i] == categorySelected) {
				
				calculatedScore += currentDieArrang[i];
				
			}
				if(calculatedScore == 0) {
					
					display.printMessage("Oh, too bad! No points this time.");
				}	
		}
	}
	
	
	//Sets all values in histoGram to zero
	private void zeroHistoArray() {
		for (int i = 0; i < 6; i++) {
			histogramArray[i] = 0;
		}
	}
	
	
	//Sets elements of histogramArray to represent count of die scores (a histogram)
	private void setHistoArrayCount() {
		int score = 0;
		for(int i = 0; i < N_DICE; i++) {
			score = currentDieArrang[i];
			histogramArray[score-1]++;
		}
	}
	
	
	//Sets elements of histogramArray to 1 if the dice value is present in currentDieArrang; otherwise element is left 0 
	private void setHistoArrayOneorZero() {
		for(int i = 0; i < N_DICE; i++) {
			int diceValue = currentDieArrang[i];
			histogramArray[diceValue - 1] = 1;
		}
	}
		
	
	//Sets appropriate element of internal score card (scoreArray[][]) to current score and
	//updates the display scorecard because I thought putting that function in the
	//main method looked messy
	private void updateScoreArrays(int category, int player, int score) {
		
		scoreArray[category -1][player - 1] = score; 
		display.updateScorecard(category, player, score);
		
	}	
	
	
	//Compute upper score for each player, during each turn
	private void computeUpperScore(int Player) {
		
		int sum = 0;
		
		//6 is the index in scoreArray of the upper score; 0-5 are ONES through SIXES
		for (int i = 0; i < 6; i++) {
			
				sum += scoreArray[i][Player - 1];
			}
		
			updateScoreArrays(7, Player, sum);		
	}
		
	
	//Compute lower score for each player, during each turn
	private void computeLowerScore(int Player) {
		
		int sum = 0;
		
		//8 is the index in scoreArray of the lower score row 
		for (int i = 8; i < 15; i++) {
			
			sum += scoreArray[i][Player - 1];
		
		}
		
			updateScoreArrays(16, Player, sum);
				
	}
		
		
	//Compute total score for each player, during each turn
	private void computeTotalScore(int Player) {
		
		int sum = 0;
		sum = scoreArray[6][Player - 1] + scoreArray[15][Player - 1];
		updateScoreArrays(17, Player, sum);
	
		
	}
				

	//compute upper bonus for all players	
	private void computeBonus() {
		
		for(int j = 0; j < nPlayers; j++) {
			
			if(scoreArray [6][j] >= 63) {
				
				updateScoreArrays(7, j+1, 35);
	
			}	
		}
	}	
	
	//compute final score for all players
	private void computeFinalScores() {
		
		for(int i = 0; i < nPlayers; i++) {
			
			int sum = 0;
			sum = scoreArray[6][i] + scoreArray[7][i] + scoreArray[15][i];
			
			updateScoreArrays(16, i+1, sum);
			display.updateScorecard(17, i+1, sum);
		}
	}
	

	//Displays game over and a congrats message to the winner (checked by comparing each value to the next)
	private void endGame() {
		
		int highScore = 0;
		int highIndex = 0;
		
		for (int i = 0; i < nPlayers; i++) {
			
			if(scoreArray[16][i] > highScore) {
				highScore = scoreArray[16][i];
				highIndex = i;
			}			
		}
		
		display.printMessage("Congratulations " + playerNames[highIndex] + "! You've won the game with " + highScore + " points!");
	
	
	}
	
	
	
	

}	//End of class
