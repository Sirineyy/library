package gestionl;
import java.util.ArrayList;
public class Member {
	private String name;
	private int cin;
	
	

	private ArrayList<Book> borrowedBooks ;
	public Member( String name ,int cin) {
		this.borrowedBooks = new ArrayList<>();
		
		this.name=name;
		
		this.cin=cin;
		
		
		
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public ArrayList<Book> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	public void borrowBook(Book book) {
	    if (borrowedBooks.size() < 2) {  
	        borrowedBooks.add(book);
	        book.borrow();
	    } else {
	        System.out.println(" Maximum limit reached.");
	    }
	    
	}
	public void returnBook(Book book) {
		borrowedBooks.remove(book);
		
		
	}
	public void returnBook(Member member, Book book) {
	    member.returnBook(book);
	    System.out.println("Book returned successfully.");
	}
	public void listBorrowedBooks(Member member) {
	    System.out.println("Books borrowed by " + this.name + ":");
	    for (Book book : this.borrowedBooks) {
	        System.out.println(book.getTitle());
	    }
	}
	@Override
	public String toString() {
		return "Member [name=" + name + ", borrowedBooks=" + borrowedBooks + ", getName()=" + getName()
				+ ", getBorrowedBooks()=" + getBorrowedBooks() + "]";
	}
	

}
