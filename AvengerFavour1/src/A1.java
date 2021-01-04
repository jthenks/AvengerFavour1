import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * COMP 2503 Winter 2020 Assignment 1
 * 
 * This program must read a input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias.
 *
 * @author Maryam Elahi
 * @author John Valiente
 * @author Shargeel Hayat
 * 
 * @date Fall 2020
 */

public class A1 
{

	public String[][] avengerRoster = 
		{
		{"captainamerica", "rogers"},
		{ "ironman", "stark" },
		{ "blackwidow", "romanoff"}, 
		{ "hulk", "banner" }, 
		{ "blackpanther", "tchalla" }, 
		{ "thor", "odinson" },
		{ "hawkeye", "barton" }, 
		{ "warmachine", "rhodes" },
		{ "spiderman", "parker" },
		{ "wintersoldier", "barnes" }
		};
	
	
	// Hash map to store occurrences of avenger from input stream. Also used to determine first occurrence of avenger name
	public HashMap<String, Integer> roster = new HashMap<String, Integer>();
	
	private ArrayList <Avenger> avengersArrayList = new ArrayList<>();
	
	private Scanner input;
	private Scanner kb;
	private final int FOUR = 4;
	private int totalwordcount = 0;

	/**
	 * Creates A1 class object calls run() method
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		A1 a1 = new A1();
		a1.run();
	}

	/**
	 * loads the method required for program to begin
	 * @throws FileNotFoundException
	 */
	public void run() throws FileNotFoundException
	{
		loadHashMap();
		readInput();
		printResults();
	}

	/**
	 * In a loop, while the scanner object has not reached end of stream, - read a
	 * word. - clean up the word - if the word is not empty, add the word count. -
	 * Check if the word is either an avenger alias or last name then - Create a new
	 * avenger object with the corresponding alias and last name. - if this avenger
	 * has already been mentioned, increase the frequency count for the object
	 * already in the list. - if this avenger has not been mentioned before, add the
	 * newly created avenger to the list, remember to set the frequency.
	 * @throws FileNotFoundException 
	 * 
	 */
	private void readInput() throws FileNotFoundException
	{	
		
		String words;
		String filename;
		
		kb = new Scanner(System.in);
		System.out.println("Placeholder - Enter file name without extensions:");
		filename = kb.next() + ".txt";
		
		File inFile = new File(filename);
		input = new Scanner(inFile);
		
		while (input.hasNext()) 
		{			
			// Trims and cleans the input word		
			words = input.next().trim().toLowerCase().replaceAll("[^a-z's]","");	
			
			// this variable removes words with apostrophe s
			String words2 = words.replaceAll("'s", ""); 
			
			// this ensures that numbers are NOT included; needed this for cleaning inputstream; 
			String words3 = words2.replaceAll("[0-9]" , ""); 
			
			//this ensures that words with an apostrophe after a word is removed (ex. Rhodes' turns to rhodes)
			String words4 = words3.replaceAll("[']", "");

			//Total word counter
			if (!words4.equals(""))
			{
				totalwordcount = totalwordcount + 1;
			}
			
			
			for (int row = 0; row < avengerRoster.length; row++) 
			{

				// If condition statement to detect input stream match
				if (avengerRoster[row][0].equals(words4) || avengerRoster[row][1].equals(words4)) 
				{

					// Iterates through the hashmap string keys
					for (String key : roster.keySet()) 
					{
						// If key and input word ARE the same
						if (key.equals(avengerRoster[row][0]) || key.equals(avengerRoster[row][1])) 
						{
							
							//Creates object if the avenger has never been mentioned before
							if (roster.get(key) < 1) 
							{
								roster.put(key, roster.get(key) + 1);
								createAvenger(key, avengerRoster[row][1]);
							}
							
							//Otherwise, simply updates frequency
							else
							{			
								roster.put(key, roster.get(key) + 1);
								updateAvengers(key);
				
							}
						}
					}
				}
			}
		}			
	}

	/**
	 * Prints the results from the input stream
	 */
	private void printResults() 
	{
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + avengersArrayList.size());
		System.out.println();

		
		System.out.println("All avengers in the order they appeared in the input stream:");
		printList(avengersArrayList);
		
		System.out.println();
		
		CompMostPopAvengers popular = new CompMostPopAvengers();
		Collections.sort(avengersArrayList, popular );
		System.out.println("Top " + FOUR + " most popular avengers:");
		printFour(avengersArrayList);
				
		System.out.println();

		CompLeastPopAvengers least = new CompLeastPopAvengers();
		Collections.sort(avengersArrayList, least);
		System.out.println("Top " + FOUR + " least popular avengers:");
		printFour(avengersArrayList);

		System.out.println();

		
		System.out.println("All mentioned avengers in alphabetical order:");
		Collections.sort(avengersArrayList);
		printList(avengersArrayList);
		
		
		System.out.println();
	}
	
	/**
	 * This method consist of a for each loop that iterates through avenger arraylist
	 * 
	 * @param list - the array list of Avengers
	 */
	public void printList(ArrayList<Avenger> list) 
	{
		for(Avenger a : list) 
		{
			System.out.println(a.toString());
		}
		System.out.println();
	}
	
	/**
	 * Loads 2d array roster into a HashMap
	 */
	public void loadHashMap() 
	{
		for (int i = 0; i < avengerRoster.length; i++) 
		{
			roster.put(avengerRoster[i][0], 0);
		}
	}

	/**
	 * Creates a new avenger and adds it to array list
	 * 
	 * @param alias - alias of the Avenger
	 * @param name - name of Avenger
	 * @param freq - initial amount an Avenger is mentioned
	 */
	public void createAvenger(String alias, String name) 
	{
		Avenger a = new Avenger();		
		a.setHeroAlias(alias);
		a.setHeroName(name);
	
		avengersArrayList.add(a);
	}
	
	/**
	 * Updates the frequency of a specific avenger 
	 * 
	 * @param key - key for specific avenger
	 */
	public void updateAvengers(String key)
	{
		for (int i = 0; i < avengersArrayList.size(); i++) 
		{
			avengersArrayList.get(i).increaseFrequency(key);
		}
	}

	/**
	 * prints 4 of the Avengers in the array list
	 * 
	 * @param list - Avengers array list 
	 */
  	public void printFour(ArrayList<Avenger> list) 
    {
		for(int i = 0; i<avengersArrayList.size();i++) 
		{
			if(i<FOUR) 
			{
				System.out.println(list.get(i));
			}
		}
	}
}
