package gestionl;

import java.util.ArrayList;


public class Library {
	private ArrayList<Member> members;
    private ArrayList<Book> books;
    private ArrayList<Loan> loans;
	
	
	
	public Library () {
		 this.members = new ArrayList<Member>();
	     this.books = new ArrayList<Book>();
	     this.loans = new ArrayList<Loan>();
		 	
	}


	public ArrayList<Loan> getLoans() {
		return loans;
	}


	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}


	public ArrayList<Member> getMembers() {
		return members;
	}


	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}


	public ArrayList<Book> getBooks() {
		return books;
	}


	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}


	
	


	public void addbook(Book book,int index) {
		books.add(index,book);
		
		
		 

		
	}
	public void addmember(Member member,int index) {
		members.add(index,member);
		
		
		 

		
	}
	public void deletebook (Book book) {
		books.remove(book);
	
				
				
			}
	public void deletemember(Member member)
	{
		members.remove(member);
	}

	
	
	
	public boolean  searchbook(Book book)
	{
		return books.contains(book);
		
		
	
	}
 public boolean searchmember(Member member) {
	 return members.contains(member);
 }
 public Book searchBookByTitle(String title) {
	    for (Book book : books) {
	        if(book.getTitle().equalsIgnoreCase(title)) {
	        	return book;
	        }
	       
	    }
	    return null; 
	}
 public Member searchMemberByname(String name) {
	    for (Member member : members) {
	        if(member.getName().equalsIgnoreCase(name)) {
	        	return member;
	        }
	       
	    }
	    return null; 
	}
public Book searchBookByAuthor(String author) {
	for(Book book:books) {
		if (book.getAuthor().equalsIgnoreCase(author)) {
			return book;
		}
	
	}
	return null;
}
public void borrowBook(Member member, Book book) {
    if (book.isAvailablity()) {
        member.borrowBook(book);
        System.out.println("Book borrowed successfully.");
    } else {
        System.out.println("Book is not available.");
    }
}
public void listActiveLoans() {
    System.out.println("Active Loans:");
    for (Loan loan : loans) {
        if (!loan.isReturned()) {
            System.out.println(loan.toString());
        }
    }
}
public void checkOverdueLoans() {
    for (Loan loan : loans) {
        if (loan.isOverdue()) {
            System.out.println("Overdue loan: " + loan.toString());
        }
    }
}


@Override
public String toString() {
	return "Library [members=" + members + ", books=" + books + ", loans=" + loans + ", getLoans()=" + getLoans()
			+ ", getMembers()=" + getMembers() + ", getBooks()=" + getBooks() + "]";
}


	

	
	
	
	
	
	

}
