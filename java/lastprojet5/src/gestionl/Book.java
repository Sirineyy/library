package gestionl;

public class Book {
	private String title;
	private String author ;
	
	private boolean availability;
	public Book(String title,String author,boolean availability) {
		this.title=title;
		this.author=author;
		
		this.availability=availability;
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public boolean isAvailablity() {
		return availability;
	}
	public void setAvailablity(boolean availablity) {
		this.availability = availablity;
	}
	public void borrow() {
	    if (availability) {
	        availability = false;
	        System.out.println("Book borrowed successfully.");
	    } else {
	        System.out.println("Book is currently unavailable.");
	    }
	}
	public void returnBook() {
	    availability = true;
	    System.out.println("Book returned successfully.");
	}
	
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", availability=" + availability + ", getTitle()="
				+ getTitle() + ", getAuthor()=" + getAuthor() + ", isAvailablity()=" + isAvailablity() + "]";
	}

	
	
}
