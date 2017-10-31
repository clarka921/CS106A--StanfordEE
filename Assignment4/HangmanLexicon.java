package assign;


import java.io.*;
import java.util.*;


//this class contains the part of the game opens, reads, and closes the .txt file form where the random words come.
public class HangmanLexicon {

	
	//create space for objects
	private ArrayList <String> HngLexicon = new ArrayList <String>(); 
	private BufferedReader reader = null;
	
	
	//loads file into buffered reader
	public HangmanLexicon() {
			try {
				reader = new BufferedReader(new FileReader("C:\\Users\\Owner\\Desktop\\CS106A\\Assingment4\\HangmanLexicon.txt"));
			}catch (IOException ex) {
				System.out.println("File not found");
			}	
	}

	public String getElement(int index) {
		return HngLexicon.get(index);
	}
	
	
	//add elements from file into arraylist
	public void addToArrayList() {
		while(true) {
			try {
				String line = reader.readLine();
				HngLexicon.add(line);
				if(line == null) break;
			}catch (IOException ex){
				System.out.println("Couldn't load elements");
			}
		}
	}
	
	
	//close buffered reader
	public void closeReader() {
		try {
		reader.close();
		}catch (IOException ex) {
			System.out.println("Unable to close file");
		}	
	}
	
	
	//Returns the number of words in the lexicon.
	public int getWordCount() {
		int integer = HngLexicon.size();
		return integer;
	}
	
	
}
