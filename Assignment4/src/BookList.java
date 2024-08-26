// ------------------------------------------------
// Assignment 4
// Question: Part II and III
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Rami Al Najem (40242034) and Fouad Meida (40249310)
 * COMP249
 * Assignment #4
 * Due date: April 17th, 2023
 */
public class BookList {
	/**
	This is a private inner class named Node that is used in the implementation of a linked list of books.
	Each node holds a Book object and a reference to the next Node in the list.
	The first Node of the list is referred to as the head Node.
	*/
	private class Node
	{
		
		private Book b;
		private Node next;
		/**
		 * Constructs an empty node with a null book and null next node.
		 */
		public Node() 
		{
			b= null;
			next = null;
		}
		/**
		Constructs a new Node object with the specified Book and next Node.
		@param b the Book to be stored in the node
		@param next the next Node in the linked list
		*/
		public Node(Book b, Node next) 
		{
			
			this.b = b;
			this.next = next;
		}
			
	}
	
	private Node head;
	
	/**
	Constructs an empty BookList with null head.
	*/
	public BookList() {
		head = null;
	}
	
	/**
	Adds a book to the start of the list.
	@param b the book to be added
	*/
	public void addToStart(Book b)
	{
		Node t = new Node(b, null);
		if(head == null)
		{
			head = t;
			t.next = t;
			t = null;
		}
		else
		{
			Node current = head; // start at the head of the list
	        while (current.next != head)
	            current = current.next; // move to the next node
	        current.next = t; // link the last node to the new node
	        t.next = head;
	        current = null;
	        t = null;
	    }
	}
	
	/**
	Adds a new book to the end of the circular linked list.
	@param b the Book to be added to the end of the list
	*/
	public void addToEnd(Book b) {
		if(head == null) {
			Node current = new Node(b, head);
			head = current;
			current.next = head;
		}
		else {
			Node current = head;
			while(current.next != head) {
				current = current.next;
			}
			current.next = new Node(b,head);
		}
	}
	
	/**
	Returns the size of the book list.
	@return The number of books in the list.
	*/
	private int size()
	{
		if (head == null)
			return 0;
		int counter = 0;
		Node t = head;
		do
		{
			counter++;
			t = t.next;
		} while (t != head);
			return counter;
	}
	
	/**
	Creates a new file that contains all records of the specified year.
	@param yr the year to search for records
	*/
	public void storeRecordsByYear(int yr) {
	    boolean recordsExist = false;
	    Node t = head;
	    int count = 0;

	    // check if there are any records for the specified year
	    while (t != null && count < size()) {
	        if (t.b.getYear() == yr) {
	            recordsExist = true;
	            break;
	        }
	        t = t.next;
	        count++;
	    }

	    // create file only if records exist for the specified year
	    if (recordsExist) {
	        String filename = yr + ".txt";
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            t = head;
	            count = 0;

	            while (t != null && count < size()) {
	                if (t.b.getYear() == yr) {
	                    writer.write(t.b.toString());
	                    writer.newLine();
	                }
	                t = t.next;
	                count++;
	            }

	            writer.close();
            	System.out.println("The file " + filename + " has been successfully created, which contains all records of year "+ yr + ".");


	        } catch (IOException e) {
	            System.out.println("An error occurred while writing to the file: " + e.getMessage());
	        }
	    } else {
	        System.out.println("No records found for year " + yr);
	    }
	}
	
	/**
	Inserts a book before the specified ISBN in the list.
	@param isbn the ISBN before which the book will be inserted
	@param b1 the book to be inserted
	@return true if the book was successfully inserted, false otherwise
	*/
	public boolean insertBefore(long isbn, Book b1) {
	    if (head == null) {
	        return false; // list is empty
	    } else if (head.b.getIsbn() == isbn) {
	        head = new Node(b1, head);
	        return true; // inserted at head
	    } else {
	        Node current = head;
	        while (current.next != head && current.next.b.getIsbn() != isbn) {
	            current = current.next;
	        }
	        if (current.next == head) {
	            return false; // ISBN not found
	        } else {
	            current.next = new Node(b1, current.next);
	            return true; // inserted in middle
	        }
	    }
	}

	/**
	Inserts a new book object before the node with ISBN number isbn1 or isbn2, if found.
	If neither ISBN number is found, the method returns false.
	@param isbn1 the ISBN number of the node before which the new book object is to be inserted
	@param isbn2 the ISBN number of the node before which the new book object is to be inserted
	@param b1 the book object to be inserted
	@return true if the new book object is successfully inserted, false otherwise
	*/
	public boolean insertBetween(long isbn1, long isbn2, Book b1) 
	{
	    Node t = head;
	    while (t.next != null && (t.b.getIsbn() != isbn1 && t.b.getIsbn() != isbn2)) {
	        t = t.next;
	    }
	    if (t.next != null && (t.b.getIsbn() == isbn1 || t.b.getIsbn() == isbn2)) {
	        t.next = new Node(b1, t.next);
	        return true;
	    }
	    return false;
	}

	/**
	Displays the content of the book list by printing the string representation
	of each book in the list in order, followed by an arrow indicating the next book,
	and finally the word "head" to indicate the end of the list.
	If the list is empty, prints a message indicating that the list is empty.
	*/
   public void displayContent() {
	    if (head == null) {
	        System.out.println("List is empty.");
	        return;
	    }
	    
	    Node current = head;
	    
	    do {
	        System.out.println(current.b.toString() + " ==> ");
	        current = current.next;
	    } while (current != head);
	    
	    System.out.println("===> head");
	}

   /**
   Deletes consecutive repeated records from the list.
   @return true if any records were deleted, false otherwise
   */
   public boolean delConsecutiveRepeatedRecords() {
	    if (head == null) {
	        // List is empty, nothing to delete
	        return false;
	    }
	    
	    Node current = head;
	    Node prev = null;
	    boolean deleted = false;
	    
	    do {
	        if (current.next.b.equals(current.b)) {
	            if (current == head) {
	                // Update head to skip the repeated node
	                head = current.next;
	            }
	            // Skip the repeated node
	            prev.next = current.next;
	            current = current.next;
	            deleted = true;
	        } else {
	            prev = current;
	            current = current.next;
	        }
	    } while (current != head);
	    
	    return deleted;
	}
   
   /**
   Creates a new BookList containing all the books in this list with the specified author.
   @param aut the author to search for
   @return a new BookList containing all the books in this list with the specified author
   */
   public BookList extractAuthList(String aut) {
	    BookList newList = new BookList();
	    if (head != null) {
	        Node t = head;
	        do {
	            if (t.b.getAuthor().equals(aut)) {
	                Book newBook = new Book(t.b.getTitle(),
	                        t.b.getAuthor(),
	                        t.b.getPrice(),
	                        t.b.getIsbn(),
	                        t.b.getGenre(),
	                        t.b.getYear());
	               newList.addToStart(newBook);
	            }
	            t = t.next;
	            
	        } while (t != head);
	    }
	    return newList;
	}
   
   /**
   Swaps the positions of two books in the circular linked list with the given ISBNs.
   @param isbn1 the ISBN of the first book to be swapped
   @param isbn2 the ISBN of the second book to be swapped
   @return true if the books were successfully swapped, false otherwise
   */
   public boolean swap(long isbn1, long isbn2)
   {
	   boolean swap = false;
	   String temp_title = "";
	   String temp_author = "";
	   double temp_price = 0;
	   long temp_isbn = 0;
	   String temp_genre = "";
	   int temp_year = 0;
	   Node t1 = head;
	   Node t2 = head;
	   do
	   {
		   if (t1.b.getIsbn() != isbn1)
			   t1 = t1.next;
		   else
		   {
			   if (t2.b.getIsbn() != isbn2)
				   t2 = t2.next;
			   else
			   {
				   swap = true;
				   
				   temp_title = t1.b.getTitle();
				   temp_author = t1.b.getAuthor();
				   temp_price = t1.b.getPrice();
				   temp_isbn = t1.b.getIsbn();
				   temp_genre = t1.b.getGenre();
				   temp_year = t1.b.getYear();
				   
				   t1.b.setTitle(t2.b.getTitle());
				   t1.b.setAuthor(t2.b.getAuthor());
				   t1.b.setPrice(t2.b.getPrice());
				   t1.b.setIsbn(t2.b.getIsbn());
				   t1.b.setGenre(t2.b.getGenre());
				   t1.b.setYear(t2.b.getYear());
				   
				   t2.b.setTitle(temp_title);
				   t2.b.setAuthor(temp_author);
				   t2.b.setPrice(temp_price);
				   t2.b.setIsbn(temp_isbn);
				   t2.b.setGenre(temp_genre);
				   t2.b.setYear(temp_year);
				   
				   break;
			   }
		   }
	   } while (t1 != head || t2 != head);
	   return swap;
   }
   
   /**
   Writes the contents of the BookList to a text file named "Update_Books.txt".
   This method appends the contents to the existing file, if any.
   If the file does not exist, a new file is created.
   If an I/O error occurs while writing to the file, an error message is printed to the console.
   */
   public void commit()
   {
	   try
	   {
		   BufferedWriter writer = new BufferedWriter(new FileWriter("Update_Books.txt", true));
		   Node t = head;
		   do
		   {
			   if (t != null)
			   {
				   writer.write(t.b.toString());
				   writer.newLine();
				   t = t.next;
			   }
		   } while (t != head);
		   
		   writer.write("________________________________________________________________");
		   writer.newLine();
		   writer.close();
	   }
	   catch (IOException e)
	   {
	        System.out.println("An error occurred while writing to the file: " + e.getMessage());
	   }
   }

}