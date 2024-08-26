// ------------------------------------------------
// Assignment 4
// Question: Part I
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

/**
 * @author Rami Al Najem (40242034) and Fouad Meida (40249310)
 * COMP249
 * Assignment #4
 * Due date: April 17th, 2023
 */
public class Book {
	private String title;
	private String author;
	private double price;
	private long isbn;
	private String genre;
	private int year;
	
	/**
	Constructs a new Book object with the given attributes.
	@param title The title of the book.
	@param author The author of the book.
	@param price The price of the book.
	@param isbn The ISBN of the book.
	@param genre The genre of the book.
	@param year The year the book was published.
	*/
	public Book(String title, String author, double price, long isbn, String genre, int year)
	{
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}

	/**
	Returns the title of the book.
	@return The title of the book.
	*/
	public String getTitle() {
		return title;
	}

	/**
	Sets the title of the book.
	@param title The new title of the book.
	*/
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	Returns the author of the book.
	@return The author of the book.
	*/
	public String getAuthor() {
		return author;
	}

	/**
	Sets the author of the book.
	@param author The new author of the book.
	*/
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	Returns the price of the book.
	@return The price of the book.
	*/
	
	public double getPrice() {
		return price;
	}

	/**
	Sets the price of the book.
	@param price The new price of the book.
	*/
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	Returns the ISBN of the book.
	@return The ISBN of the book.
	*/
	public long getIsbn() {
		return isbn;
	}

	/**
	Sets the ISBN of the book.
	@param isbn The new ISBN of the book.
	*/
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	/**
	Returns the genre of the book.
	@return The genre of the book.
	*/
	public String getGenre() {
		return genre;
	}

	/**
	Sets the genre of the book.
	@param genre The new genre of the book.
	*/
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	Returns the publication year of the book.
	@return The publication year of the book.
	*/
	public int getYear() {
		return year;
	}

	/**
	Sets the publication year of the book.
	@param year The new publication year of the book.
	*/
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	Returns a string representation of the book.
	@return A string representation of the book in the format "title, author, price, isbn, genre, year."
	*/
	public String toString() {
		return title +"," + author +", "+ price + ", " + isbn + ", " + genre + ", " + year;
	}
	
	/**
	Compares the current Book instance with another Book instance for equality.
	Two books are considered equal if they have the same title, author, price, ISBN, genre, and year.
	@param b the other Book instance to be compared with
	@return true if the two books are equal, false otherwise
	*/
	public boolean equals(Book b) {
		return b.title.equals(this.title) && b.author.equals(this.author) && b.price == this.price && b.isbn == this.isbn && b.genre.equals(this.genre) && b.year == this.year;
	}

}