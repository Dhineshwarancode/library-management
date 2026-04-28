import java.util.*;
import java.time.LocalDate;

public class Member{
    private String memberId;
    private String name;
    private String email;
    private List<BorrowRecord> activeBorrows;
    private double fines;
    private FinePolicy finePolicy;

    public Member(String memberId, String name, String email, FinePolicy finePolicy){
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.fines = 0;
        this.activeBorrows = new ArrayList<>();
        this.finePolicy = finePolicy;
    }
    

    public String getMemberId(){
        return memberId;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public List<BorrowRecord> getActiveBorrows(){
        return activeBorrows;
    }

    public double getFines(){
        return fines;
    }
    
    public void setFines(double fines){
        this.fines = fines;
    }
    
    public void addFine(double amount){
        this.fines += amount;
    }
    
    public FinePolicy getFinePolicy(){
        return finePolicy;
    }
    
    public void setFinePolicy(FinePolicy finePolicy){
        this.finePolicy = finePolicy;
    }
    
    public boolean borrowBook(Book book){
        if(this.fines > 0){
            System.out.println("Cannot borrow book. Member has unpaid fines of: $" + this.fines);
            return false;
        }
        
        if(this.activeBorrows.size() >= 3){
            System.out.println("Cannot borrow book. Member has 3 active borrows (maximum limit reached).");
            return false;
        }
        
        if(!book.checkAvailability()){
            System.out.println("Cannot borrow book. Book is not available.");
            return false;
        }
        
        if(book.getQuantity() <= 0){
            System.out.println("Cannot borrow book. No copies available.Waiting List added!!!");
            return false;
        }
        
        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(14);
        
        BorrowRecord borrow = new BorrowRecord(book.getIsbn(), book.getTitle(), book.getAuthor(), issueDate, dueDate, "issued");
        this.activeBorrows.add(borrow);
        
        book.updateQuantity(book.getQuantity() - 1);
        
        System.out.println("Book successfully borrowed!");
        System.out.println("Book: " + book.getTitle() + " by " + book.getAuthor());
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Due Date: " + dueDate);
        
        return true;
    }
    public boolean returnBook(String isbn, Book book){
        BorrowRecord borrowToReturn = null;
        for(BorrowRecord borrow : activeBorrows){
            if(borrow.getIsbn().equals(isbn)){
                borrowToReturn = borrow;
                break;
            }
        }
        
        if(borrowToReturn == null){
            System.out.println("No active borrow record found for ISBN: " + isbn);
            return false;
        }
        
        double fine = borrowToReturn.calculateFine(finePolicy);
        if(fine > 0){
            System.out.println("Book is overdue. Fine of $" + String.format("%.2f", fine) + " has been added to member's account.");
            this.addFine(fine);
        }
        
        activeBorrows.remove(borrowToReturn);
        book.updateQuantity(book.getQuantity() + 1);
        
        System.out.println("Book successfully returned!");
        return true;
    }
    public void payFine(double amount){
        if(amount <= 0){
            System.out.println("Payment amount must be greater than zero.");
            return;
        }
        
        if(amount > this.fines){
            System.out.println("Payment amount exceeds total fines. Paying off all fines.");
            this.fines = 0;
        } else {
            this.fines -= amount;
            System.out.println("Payment of $" + amount + " received. Remaining fines: $" + this.fines);
        }
    }

}