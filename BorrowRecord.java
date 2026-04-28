import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowRecord{
    private String isbn;
    private String bookTitle;
    private String bookAuthor;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String status;
    
    public BorrowRecord(String isbn, String bookTitle, String bookAuthor, LocalDate issueDate, LocalDate dueDate, String status){
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
    }
    
    public String getIsbn(){
        return isbn;
    }
    
    public String getBookTitle(){
        return bookTitle;
    }
    
    public String getBookAuthor(){
        return bookAuthor;
    }
    
    public LocalDate getIssueDate(){
        return issueDate;
    }
    
    public LocalDate getDueDate(){
        return dueDate;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public boolean isOverdue(){
        return status.equals("issued") && LocalDate.now().isAfter(dueDate);
    }
    
    public long getDaysOverdue(){
        if(!isOverdue()){
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, LocalDate.now());
    }
    
    public double calculateFine(FinePolicy policy){
        if(policy == null){
            System.out.println("ERROR: FinePolicy cannot be null");
            return 0.0;
        }
        
        long daysOverdue = getDaysOverdue();
        return policy.computeFine(daysOverdue);
    }
    
    @Override
    public String toString(){
        return "Book: " + bookTitle + " by " + bookAuthor + 
               ", ISBN: " + isbn + 
               ", Issue Date: " + issueDate + 
               ", Due Date: " + dueDate + 
               ", Status: " + status;
    }
}
