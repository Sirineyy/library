package gestionl;
import java.util.Date;
public class Loan {
	private Member member;
	private Book book;
	private Date loandate;
	private Date returndate;
	private boolean isReturned;
	public Loan(Member member,Book book,Date loandate,Date returndate) {
		  this.member = member;
	        this.book = book;
	        this.loandate = loandate;
	        this.returndate = returndate;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getLoandate() {
		return loandate;
	}
	public void setLoandate(Date loandate) {
		this.loandate = loandate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	public boolean isReturned() {
		return isReturned;
	}
	public void setIsreturned(boolean isreturned) {
		this.isReturned = isreturned;
	}
	 public void returnBook() {
	        this.isReturned = true;
	        System.out.println("Book returned successfully.");
	    }
	 public boolean isOverdue() {
	        return !isReturned && new Date().after(returndate);
	    }
	
	@Override
	public String toString() {
		return "Loan [member=" + member + ", book=" + book + ", loandate=" + loandate + ", returndate=" + returndate
				+ ", getMember()=" + getMember() + ", getBook()=" + getBook() + ", getLoandate()=" + getLoandate()
				+ ", getReturndate()=" + getReturndate() + "]";
	}
	
	

}
