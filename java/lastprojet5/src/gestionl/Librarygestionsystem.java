package gestionl;
import java.util.Date;
import java.util.Scanner;

public class Librarygestionsystem {
	
	   
	
	    public static void main(String[] args) {
	        Library library = new Library();

	       
	        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", true);
	        library.addbook(book1, 0);
	        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", true);
	        library.addbook(book2, 1);
	        Book book3 = new Book("1984", "George Orwell", true);
	        library.addbook(book3, 2);
	        Book book4 = new Book("Moby Dick", "Herman Melville", true);
	        library.addbook(book4, 3);

	        
	        Member member1 = new Member("John", 12345);
	        library.addmember(member1, 0);
	        Member member2 = new Member("Jane", 67890);
	        library.addmember(member2, 1);

	     
	        Loan loan1 = new Loan(member1, book1, new Date(), new Date(System.currentTimeMillis() + (7L * 24 * 60 * 60 * 1000))); // 7 days loan
	        library.getLoans().add(loan1);
	        book1.setAvailablity(false);

	        
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\nWelcome to the Library Management System");
	            System.out.println("1. List all books");
	            System.out.println("2. Borrow a book");
	            System.out.println("3. Return a book");
	            System.out.println("4. View borrowed books");
	            System.out.println("5. Register as a new member");
	            System.out.println("6. Register a new book");
	            System.out.println("7. View active loans");
	            System.out.println("8. Check overdue loans");
	            System.out.println("9. Search book by title");
	            System.out.println("10. Search book by author");
	            System.out.println("11. Update member information");
	            System.out.println("12. View available books");
	            System.out.println("13. List all members");
	            System.out.println("14. Delete a book");
	            System.out.println("15. Delete a member");
	            System.out.println("16. Exit");
	            System.out.print("Enter your choice: ");

	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {
	                case 1:
	                    listAllBooks(library);
	                    break;

	                case 2:
	                    borrowBook(scanner, library);
	                    break;

	                case 3:
	                    returnBook(scanner, library);
	                    break;

	                case 4:
	                    viewBorrowedBooks(scanner, library);
	                    break;

	                case 5:
	                    registerNewMember(scanner, library);
	                    break;

	                case 6:
	                    registerNewBook(scanner, library);
	                    break;

	                case 7:
	                    library.listActiveLoans();
	                    break;

	                case 8:
	                    library.checkOverdueLoans();
	                    break;

	                case 9:
	                    searchBookByTitle(scanner, library);
	                    break;

	                case 10:
	                    searchBookByAuthor(scanner, library);
	                    break;

	                case 11:
	                    updateMemberInformation(scanner, library);
	                    break;

	                case 12:
	                    listAvailableBooks(library);
	                    break;

	                case 13:
	                    listAllMembers(library);
	                    break;

	                case 14:
	                    deleteBook(scanner, library);
	                    break;

	                case 15:
	                    deleteMember(scanner, library);
	                    break;

	                case 16:
	                    System.out.println("Exiting the system. Goodbye!");
	                    scanner.close();
	                    return;

	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    break;
	            }
	        }
	    }

	    private static void listAllBooks(Library library) {
	        System.out.println("Listing all books:");
	        for (Book book : library.getBooks()) {
	            System.out.println(book.getTitle() + " by " + book.getAuthor() +
	                    (book.isAvailablity() ? " (Available)" : " (Not Available)"));
	        }
	    }

	    private static void listAvailableBooks(Library library) {
	        System.out.println("Listing available books:");
	        for (Book book : library.getBooks()) {
	            if (book.isAvailablity()) {
	                System.out.println(book.getTitle() + " by " + book.getAuthor());
	            }
	        }
	    }

	    private static void listAllMembers(Library library) {
	        System.out.println("Listing all members:");
	        for (Member member : library.getMembers()) {
	            System.out.println("Name: " + member.getName() + ", CIN: " + member.getCin() );
	        }
	    }

	    private static Member registerNewMember(Scanner scanner, Library library) {
	        System.out.print("Enter your first name: ");
	        String Name = scanner.nextLine();

	       

	        System.out.print("Enter your CIN: ");
	        int cin = scanner.nextInt();

	        
	        scanner.nextLine();

	        Member newMember = new Member(Name,cin);
	        library.addmember(newMember, library.getMembers().size());
	        System.out.println("New member registered successfully!");
	        return newMember;
	    }

	    private static void registerNewBook(Scanner scanner, Library library) {
	        System.out.print("Enter the book title: ");
	        String title = scanner.nextLine();

	        System.out.print("Enter the author name: ");
	        String author = scanner.nextLine();

	        Book newBook = new Book(title, author, true);
	        library.addbook(newBook, library.getBooks().size());
	        System.out.println("New book added successfully!");
	    }

	    private static void borrowBook(Scanner scanner, Library library) {
	        System.out.print("Enter your name: ");
	        String borrowerName = scanner.nextLine();
	        Member borrower = library.searchMemberByname(borrowerName);

	        if (borrower == null) {
	            borrower = registerNewMember(scanner, library);
	        }

	        System.out.print("Enter the title of the book to borrow: ");
	        String bookToBorrow = scanner.nextLine();
	        Book bookBorrow = library.searchBookByTitle(bookToBorrow);

	        if (bookBorrow != null && bookBorrow.isAvailablity()) {
	            Date loanDate = new Date();
	            Date returnDate = new Date(loanDate.getTime() + (7L * 24 * 60 * 60 * 1000)); 
	            Loan newLoan = new Loan(borrower, bookBorrow, loanDate, returnDate);

	            library.getLoans().add(newLoan);
	            borrower.borrowBook(bookBorrow);
	            bookBorrow.setAvailablity(false); 

	            System.out.println("Book borrowed successfully. Due date: " + returnDate);
	        } else {
	            System.out.println("Book is not available.");
	        }
	    }

	    private static void returnBook(Scanner scanner, Library library) {
	        System.out.print("Enter your name: ");
	        String returnerName = scanner.nextLine();
	        Member returner = library.searchMemberByname(returnerName);

	        if (returner == null) {
	            System.out.println("Member not found. Cannot return a book.");
	            return;
	        }

	        System.out.print("Enter the title of the book to return: ");
	        String bookToReturn = scanner.nextLine();
	        Book bookReturn = library.searchBookByTitle(bookToReturn);

	        if (bookReturn != null) {
	            for (Loan loan : library.getLoans()) {
	                if (loan.getBook().equals(bookReturn) && loan.getMember().equals(returner) && !loan.isReturned()) {
	                    loan.returnBook();
	                    returner.returnBook(bookReturn);
	                    bookReturn.returnBook();
	                    bookReturn.setAvailablity(true); 
	                    return;
	                }
	            }
	            System.out.println("No active loan found for this book.");
	        } else {
	            System.out.println("Book not found.");
	        }
	    }

	    private static void viewBorrowedBooks(Scanner scanner, Library library) {
	        System.out.print("Enter your name: ");
	        String name = scanner.nextLine();
	        Member member = library.searchMemberByname(name);

	        if (member == null) {
	            System.out.println("Member not found.");
	            return;
	        }

	        System.out.println("Books borrowed by " + member.getName() + ":");
	        for (Book book : member.getBorrowedBooks()) {
	            System.out.println(book.getTitle() + " by " + book.getAuthor());
	        }
	    }

	    private static void searchBookByTitle(Scanner scanner, Library library) {
	        System.out.print("Enter the title of the book to search: ");
	        String title = scanner.nextLine();
	        Book book = library.searchBookByTitle(title);

	        if (book != null) {
	            System.out.println("Found book: " + book.getTitle() + " by " + book.getAuthor());
	        } else {
	            System.out.println("Book not found.");
	        }
	    }

	    private static void searchBookByAuthor(Scanner scanner, Library library) {
	        System.out.print("Enter the author of the book to search: ");
	        String author = scanner.nextLine();
	        Book book = library.searchBookByAuthor(author);

	        if (book != null) {
	            System.out.println("Found book: " + book.getTitle() + " by " + book.getAuthor());
	        } else {
	            System.out.println("Book not found.");
	        }
	    }

	    private static void updateMemberInformation(Scanner scanner, Library library) {
	        System.out.print("Enter your name: ");
	        String name = scanner.nextLine();
	        Member member = library.searchMemberByname(name);

	        if (member != null) {
	            System.out.println("Enter new information:");
	            System.out.print("New age: ");
	            
	            scanner.nextLine();
	            System.out.println("Information updated.");
	        } else {
	            System.out.println("Member not found.");
	        }
	    }

	    private static void deleteBook(Scanner scanner, Library library) {
	        System.out.print("Enter the title of the book to delete: ");
	        String title = scanner.nextLine();
	        Book bookToDelete = library.searchBookByTitle(title);

	        if (bookToDelete != null) {
	            library.getBooks().remove(bookToDelete);
	            System.out.println("Book deleted successfully.");
	        } else {
	            System.out.println("Book not found.");
	        }
	    }

	    private static void deleteMember(Scanner scanner, Library library) {
	        System.out.print("Enter the name of the member to delete: ");
	        String name = scanner.nextLine();
	        Member memberToDelete = library.searchMemberByname(name);

	        if (memberToDelete != null) {
	            library.getMembers().remove(memberToDelete);
	            System.out.println("Member deleted successfully.");
	        } else {
	            System.out.println("Member not found.");
	        }
	    }
	}

