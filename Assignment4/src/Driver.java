// ------------------------------------------------
// Assignment 4
// Question: Part IV
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Rami Al Najem (40242034) and Fouad Meida (40249310)
 * COMP249
 * Assignment #4
 * Due date: April 17th, 2023
 */
public class Driver {
	/**
	Splits a record string by comma delimiter and returns an array of strings.
	@param record the record string to be split.
	@return an array of strings obtained by splitting the record string by comma delimiter.
	*/
	public static String[] splitter(String record)
	{
		String[] Array;
		Array = record.split(",");
		return Array;
	}
	
	/**
	Checks if a given year is less than 2024.
	@param year the year to be checked
	@return true if the year is less than 2024, false otherwise
	*/
	public static boolean yearChecker(int year)
	{
		if (year < 2024)
			return true;
		else
			return false;
	}
	
	/**
	This is the main method of our program which reads a file, parses its records, and checks their syntax.
	It initializes variables for book attributes and iterates over each line of the file. For each line, it
	calls the splitter method to split the record into an array of strings. It then parses the array into the
	appropriate data types for each book attribute. Finally, it checks the year of the book to ensure it is not
	in the future, and adds the book to the BookList if all checks pass.
	@param args the command line arguments
	*/
	public static void main(String[] args) {
		System.out.println("Welcome to our program!");
		System.out.println("First step: reading the file and find the correcy records based on their syntax (especially the year):");
		BufferedReader inputFileReader = null;
		String title = "";
		String authors = "";
		double price = 0;
		long isbn = 0;
		String genre = "";
		int year = 0;
		
		ArrayList<String> wrong_records = new ArrayList<String>();
		BookList records = new BookList();
		
		// Step 1: Reading all the records of the file and separate the correct records from the non correct ones based on the year.
		try
		{
        	inputFileReader = new BufferedReader(new FileReader("file_input_name.txt"));
        	String line;
            try
            {
				while ((line = inputFileReader.readLine()) != null) 
				{
					// Split the record using splitter static method and store it in an array of Strings called fields. 
                    String[] fields = splitter(line);
                    
                    title = fields[0];
                    authors = fields[1];
                    price = Double.parseDouble(fields[2]);
                    isbn = Long.parseLong(fields[3]);
                    genre = fields[4];
                    year = Integer.parseInt(fields[5]);
                    
                    if (yearChecker(year))
                    {
                    	Book book = new Book(title, authors, price, isbn, genre, year);
                    	records.addToEnd(book);
                    }
                    
                    else
                    	wrong_records.add(line);
				}
				
				System.out.println("YearError File Created");
				
				// Close the file
				inputFileReader.close();
			}
            
            catch (IOException e)
            {
				e.getMessage();
			}
        	
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Error in opening the file_input_name.txt file");
		}
		
		// Step 2: Write all the wrong records to a BufferedWrityer file called YearErr.txt.
		try
		{
            BufferedWriter writer = new BufferedWriter(new FileWriter("YearErr.txt"));
            for (String record : wrong_records)
            {
                writer.write(record);
                // add a new line after each record
                writer.newLine(); 
            }
            writer.close(); // close the writer to flush the buffer and release resources
        }
		catch (FileNotFoundException f)
		{
			System.out.println("Error while creating or opening the file.");
		}
		catch (IOException e)
		{
            e.getMessage();
		}
		
		// Step 3: 
		// Display all the existed records then create an animated main menu with the instructions to be done based on the user's input.
		System.out.println("Here are the contents of the list");
		System.out.println("=================================");
		records.displayContent();
		System.out.println("------------------------------------------------------------------");
		
		// Use while loop to display an animated main menu with the offered options based on the user's input.
		   while(true)
		   {
			   System.out.println("Tell me what you want to do? Let's Chat since this is trending now! Here are the options:");
			   System.out.println("\t1) Give me a year # and I would extract all records of that year and store them in a file for that year;");
			   System.out.println("\t2) Ask me to delete all consecutive repeated records;");
			   System.out.println("\t3) Give me an author name and and I will create a new list with the records of this author and display them;");
			   System.out.println("\t4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;");
			   System.out.println("\t5) Give me 2 ISBN numbers and a Book object, and I will insert Node between them, if I find them!");
			   System.out.println("\t6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!");
			   System.out.println("\t7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;");
			   System.out.println("\t8) Tell me to STOP TALKING! Remember, if you do not commit, I will not!");
			   System.out.print("Enter your Selection: ");
			   Scanner keyboard = new Scanner(System.in);
			   // Prompt the user to input an integer which indicates the choice that should be executed.
			   int choice = keyboard.nextInt();
			   
			   switch(choice)
			   {
			   case 1: // extracting all records of that year and store them in a file for that year based on user's input
				   System.out.print("Please enter the year in order to extract all records of that year and store them in a file for that year: ");
				   int input_year = keyboard.nextInt();
               	   records.storeRecordsByYear(input_year);
				   System.out.println("==========================================================================");
				   break;
				   
			   case 2: // deleting all consecutive repeated records
				   System.out.println("Here are the contents of the list after removing consecutive duplicates");
				   System.out.println("=======================================================================");
				   boolean deleted = records.delConsecutiveRepeatedRecords();
				   if (deleted) {
					    System.out.println("\nAfter deleting consecutive repeated records:");
					    System.out.println();
					    records.displayContent();
					} else {
					    System.out.println("\nNo consecutive repeated records found.");
					}		
				 
				   break;
				   
			   case 3: // creating a new list with the records of the author entered by the user and display them.
				   System.out.print("Please enter the name of the author to create an extracted list :");
				   keyboard.nextLine();
				   String s = keyboard.nextLine();
				   System.out.println("Here are the contents of the author list for " + s);
				   BookList newList= records.extractAuthList(s);
				   newList.displayContent();
				   break;
				   
			   case 4: // creating a node object which contains a new book object. This will be inserted before the record based on its ISBN.
				   System.out.println("Please enter the information of the bool record that you wish to insert :");
				   keyboard.nextLine();
				   System.out.print("Title :" );
				   String title1 = keyboard.nextLine();
				   System.out.print("Author : ");
				   String author1 = keyboard.nextLine();
				   System.out.print("Price : ");
				   double price1 = keyboard.nextDouble();
				   System.out.print("ISBN : ");
				   long isbn3 = keyboard.nextLong();
				   keyboard.nextLine();
				   System.out.print("Genre : ");
				   String genre1 = keyboard.nextLine();
				   System.out.print("Year : ");
				   int year1 = keyboard.nextInt();
				   Book b1 = new Book(title1, author1, price1, isbn3, genre1, year1);
				   System.out.print("\nPlease enter the ISBN that you wish to insert the record before it : ");
				   long is = keyboard.nextLong();
				   boolean inserted = records.insertBefore(is, b1);
				   if (inserted) {
					    System.out.println("\nAfter inserting the record:");
					    System.out.println();
					    records.displayContent();
					} else {
					    System.out.println("\nIt didn't insert there must be a problem");
					}		
				   break;
				   
			   case 5: // creating a node object which contains a new book object. This will be inserted between two records based on their ISBN.
				   System.out.println("Please enter the information of the book record that you wish to insert :");
				   keyboard.nextLine();
				   System.out.print("Title :" );
				   String newTitle = keyboard.nextLine();
				   System.out.print("Author: ");
				   String newAuthor = keyboard.nextLine();
				   System.out.print("Price: ");
				   double newPrice = keyboard.nextDouble();
				   System.out.print("ISBN: ");
				   long newISBN = keyboard.nextLong();
				   System.out.print("Genre: ");
				   keyboard.nextLine();
				   String newGenre = keyboard.nextLine();
				   System.out.print("Year: ");
				   int newYear = keyboard.nextInt();
				   System.out.print("Now, please enter the ISBN of the previous book that you wish insert the new Book before it: ");
				   long previousISBN = keyboard.nextLong();
				   System.out.print("Finally, please enter the ISBN of the next book that you wish insert the new Book after it: ");
				   long nextISBN = keyboard.nextLong();
				   
				   Book newBook = new Book(newTitle, newAuthor, newPrice, newISBN, newGenre, newYear);
				   boolean addBetween = records.insertBetween(previousISBN, nextISBN, newBook);
				   
				   if (addBetween)
				   {
					   System.out.println("The new Book object has been successfully added between the previous book that has the following ISBN " + previousISBN + " and the " +
				   "next book that has the following ISBN " + nextISBN + ".");
					   System.out.println("Here are the contents of the new list after adding the new Book object between two Book objects based on their ISBN: ");
					   records.displayContent();
				   }
				   else
				   {
					   System.out.println("Adding a new Book operation between two Book objects is failed because one or both given ISBN does not exist.");
					   System.out.println("==========================================================================");
				   }
				   break;
				   
			   case 6: // swapping two records based on their ISBN, which will be chosen by the user's input.
				   System.out.print("Enter two ISBN to swap their locations: ");
				   long isbn1 = keyboard.nextLong();
				   long isbn2 = keyboard.nextLong();
				   boolean swap = records.swap(isbn1, isbn2);
				   if (swap)
				   {
					   System.out.println("\nRecords with ISBN "+ isbn1 + " and " + isbn2 + " were found and swapped. Here are the contents of the list after rearranging the record.");
					   System.out.println("==========================================================================");
					   records.displayContent();
				   }
				   else
				   {
					   System.out.println("\nSwap operation failed because one or both given ISBN does not exist.");
					   System.out.println("==========================================================================");
				   }
				   break;
				   
			   case 7: // commiting the given list to to Updated_Books file.
				   records.commit();
				   System.out.println("Commiting the given list to to Updated_Books file has been successfully completed.");
				   System.out.println("==========================================================================");
				   break;
				   
			   case 8: // terminating the program.
				   System.out.println("Program will be terminated.");
				   System.out.println("Thank you for using our program!");
				   keyboard.close();
				   System.exit(0);
				   
			   default: // if the input of the user does not match any of the integers from 1 to 8, display a message in order to let the user to try again.
				   System.out.println("Invalid input. Please try again!");
			   }

		   }

	}
}