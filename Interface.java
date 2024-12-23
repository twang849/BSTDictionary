import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// The user interface to use the BST Dictionary
public class Interface {

	public static void main(String[] args) throws FileNotFoundException, DictionaryException, IOException {
		String obj = args[0];
		Scanner scanner = new Scanner(obj);
		
		BSTDictionary dict = new BSTDictionary();

		BufferedReader reader = new BufferedReader(new FileReader(obj));
		// Reads the file and inserts records into the BSTDictionary
		while (reader.ready()) {
			String data = "";
			String label = reader.readLine();
			if (reader.ready()) data = reader.readLine();

		// while(scanner.hasNextLine()) {
		// 	String data = "";
		// 	String label = scanner.nextLine();
		// 	if (scanner.hasNextLine()) data = scanner.nextLine();
			
			int type = 0;
			if (data.startsWith("-")) type = 3;
			else if (data.startsWith("+")) type = 4;
			else if (data.startsWith("*")) type = 5;
			else if (data.startsWith("/")) type = 2;
			else if (data.endsWith("gif")) type = 7;
			else if (data.endsWith("jpg")) type = 6;
			else if (data.endsWith("html")) type = 8;
			else type = 1;

			Key k = new Key(label, type);
			Record rec = new Record(k, data);
			dict.put(rec);
		}

		scanner.close();
		String option, word = "", type = "0";
		StringReader keyboard = new StringReader();
		String line = keyboard.read("Enter next command: ");
		
		// Selects command based on user input
		while (!line.equals("exit")) {
			StringTokenizer token = new StringTokenizer(line);
			option = token.nextToken();
			if (token.hasMoreTokens()) word = token.nextToken();
			if (token.hasMoreTokens()) type = token.nextToken();

			// Provide the definition of this word
			if (option.equals("define")) {
				Record rec = dict.get(new Key(word, 1));
				if (rec == null) System.out.println("The word " + word + " is not in the dictionary");
				else System.out.println(rec.getDataItem());

			// Provide the translation of this word
			} else if (option.equals("translate")) {
				Record rec = dict.get(new Key(word, 2));
				if (rec == null) System.out.println("There is no definition for the word " + word);
				else System.out.println(rec.getDataItem().substring(1));
			
			// Plays the sound file for this key
			} else if (option.equals("sound")) {
				Record rec = dict.get(new Key(word, 3));
				if (rec == null) System.out.println("There is no sound file for " + word);
				else {
					try {
						SoundPlayer player = new SoundPlayer();
						player.play(rec.getDataItem().substring(1));
					} catch (MultimediaException m) {
						System.out.println("Error: " + m);
					}
				}
			
			// Plays the music file for this key
			} else if (option.equals("play")) {
				Record rec = dict.get(new Key(word, 4));
				if (rec == null) System.out.println("There is no music file for " + word);
				else {
					try {
						SoundPlayer player = new SoundPlayer();
						player.play(rec.getDataItem().substring(1));
					} catch (MultimediaException m) {
						System.out.println("Error :" + m);
					}
				}

			// Plays the voice file for this key
			} else if (option.equals("say")) {
				Record rec = dict.get(new Key(word, 5));
				if (rec == null) System.out.println("There is no voice file for " + word);
				else {
					try {
						SoundPlayer player = new SoundPlayer();
						player.play(rec.getDataItem().substring(1));
					} catch (MultimediaException m) {
						System.out.println("Error :" + m);
					}
				}
			
			// Shows the image associated with this key
			} else if (option.equals("show")) {
				Record rec = dict.get(new Key(word, 6));
				if (rec == null) System.out.println("There is no image file for " + word);
				else {
					try {
						PictureViewer viewer = new PictureViewer();
						viewer.show(rec.getDataItem());
					} catch (MultimediaException m) {
						System.out.println("Error :" + m);
					}
				}

			// Shows the animated image file for this key
			} else if (option.equals("animate")) {
				Record rec = dict.get(new Key(word, 7));
				if (rec == null) System.out.println("There is no animated image file for " + word);
				else {
					try {
						PictureViewer viewer = new PictureViewer();
						viewer.show(rec.getDataItem());
					} catch (MultimediaException m) {
						System.out.println("Error :" + m);
					}
				}

			// Shows the webpage for this key
			} else if (option.equals("browse")) {
				Record rec = dict.get(new Key(word, 8));
				if (rec == null) System.out.println("There is no webpage called " + word);
				else {
					try {
						ShowHTML page = new ShowHTML();
						page.show(rec.getDataItem());
					}catch (Exception m) {
							System.out.println("Error :" + m);
						}
				}	

			// Deletes a record from the BSTDictionary
			} else if (option.equals("delete")) {
				try {
					dict.remove(new Key(word, Integer.parseInt(type)));
				} catch (DictionaryException e) {
					System.out.println("No record in the ordered dictionary has key (" + word + "," + type + ")");
				} 

			// Adds a record from the BSTDictionary
			} else if (option.equals("add")) {
				try {
					String definition = "";
					while (token.hasMoreTokens()) {
						definition += token.nextToken() + " ";
					}
					definition = definition.substring(0, definition.length() - 1);
					dict.put(new Record(new Key(word, Integer.parseInt(type)), definition));
				} catch (DictionaryException e) {
					System.out.println("A record with the given key (" + word + ", " + type + ") is already in the ordered dictionary");
				}

			// Shows all records from the BSTDictionary with given prefix
			} else if (option.equals("list")) {
				Record prev = dict.smallest();
				Record curr = prev;
				String strng = "";
				try {
					while (curr != null) {
						String label = curr.getKey().getLabel();
						if (label.startsWith(word)) strng += label + ", ";
						prev = curr;
						curr = dict.successor(curr.getKey());
					}
					System.out.println(strng.substring(0, strng.length() - 2));
				} catch (Exception e) {
					System.out.println("No label attributes in the ordered dictionary start with prefix " + word);
				}

			// Provides the record in the BSTDictionary with the smallest key
			} else if (option.equals("first")) {
				Record small = dict.smallest();
				String definition = small.getDataItem();
				if (definition.startsWith("*") || definition.startsWith("/") || definition.startsWith("-") || definition.startsWith("+"))
					definition = definition.substring(1);
				System.out.println(small.getKey().getLabel() + "," +small.getKey().getType() + "," + definition);
			
			// Provides the record in the BSTDictionary with the largest key
			} else if (option.equals("last")) {
				Record large = dict.largest();
				String definition = large.getDataItem();
				if (definition.startsWith("*") || definition.startsWith("/") || definition.startsWith("-") || definition.startsWith("+"))
					definition = definition.substring(1);
				System.out.println(large.getKey().getLabel() + "," + large.getKey().getType() + "," + definition);
			} else System.out.println("Invalid command.");

			line = keyboard.read("Enter next command: ");
		}

	}

}
