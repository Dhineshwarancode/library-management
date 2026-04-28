import java.util.*;

public class Librarian{
    private int staffId;
    private String name;
    private Library library;
    
    public Librarian(int staffId, String name, Library library){
        this.staffId = staffId;
        this.name = name;
        this.library = library;
    }
    
    public boolean issueBook(String memberId, String isbn){
        System.out.println("\n--- Book Issuance Process ---");
        System.out.println("Librarian: " + this.name + " processing book issue...");
        
        Member member = library.getMember(memberId);
        if(member == null){
            System.out.println("ERROR: Member not found with ID: " + memberId);
            return false;
        }
        
        Book book = library.getBook(isbn);
        if(book == null){
            System.out.println("ERROR: Book not found with ISBN: " + isbn);
            return false;
        }
        
        boolean issuedSuccessfully = member.borrowBook(book);
        
        if(issuedSuccessfully){
            System.out.println("Status: ISSUED by Librarian " + this.name);
        } else {
            System.out.println("Status: ISSUE FAILED");
        }
        
        return issuedSuccessfully;
    }
    
    // Book Return Management
    public boolean processReturn(String memberId, String isbn){
        System.out.println("\n--- Book Return Process ---");
        System.out.println("Librarian: " + this.name + " processing book return...");
        
        Member member = library.getMember(memberId);
        if(member == null){
            System.out.println("ERROR: Member not found with ID: " + memberId);
            return false;
        }
        
        Book book = library.getBook(isbn);
        if(book == null){
            System.out.println("ERROR: Book not found with ISBN: " + isbn);
            return false;
        }
        
        boolean returnedSuccessfully = member.returnBook(isbn, book);
        
        if(returnedSuccessfully){
            System.out.println("Status: RETURNED by Librarian " + this.name);
            
            double memberFines = member.getFines();
            if(memberFines > 0){
                System.out.println("Member Fine: $" + String.format("%.2f", memberFines));
            }
        } else {
            System.out.println("Status: RETURN FAILED");
        }
        
        return returnedSuccessfully;
    }
    
    
    public boolean addBook(Book book){
        System.out.println("\n--- Add Book to Inventory ---");
        System.out.println("Librarian: " + this.name + " adding book to inventory...");
        
        if(book == null){
            System.out.println("ERROR: Cannot add null book.");
            return false;
        }
        
        boolean added = library.addBook(book);
        if(added){
            System.out.println("Book added by Librarian " + this.name);
        }
        return added;
    }
    
    public boolean removeBook(String isbn, int quantity){
        System.out.println("\n--- Remove Book from Inventory ---");
        System.out.println("Librarian: " + this.name + " removing book from inventory...");
        
        if(quantity <= 0){
            System.out.println("ERROR: Quantity must be greater than zero.");
            return false;
        }
        
        boolean removed = library.removeBook(isbn, quantity);
        if(removed){
            System.out.println("Book removed by Librarian " + this.name);
        }
        return removed;
    }
    
    
    public void viewInventory(){
        System.out.println("\n--- Library Inventory ---");
        List<Book> books = library.getAllBooks();
        if(books.isEmpty()){
            System.out.println("No books in inventory.");
            return;
        }
        for(Book book : books){
            System.out.println(book);
        }
    }
    
    public void viewFinePolicy(){
        System.out.println("\n--- Library Fine Policy ---");
        System.out.println(library.getFinePolicy());
    }
    
   
    public boolean processMemberPayment(String memberId, double amount){
        System.out.println("\n--- Process Member Fine Payment ---");
        System.out.println("Librarian: " + this.name + " processing payment...");
        
        Member member = library.getMember(memberId);
        if(member == null){
            System.out.println("ERROR: Member not found with ID: " + memberId);
            return false;
        }
        
        if(member.getFines() <= 0){
            System.out.println("Member has no outstanding fines.");
            return false;
        }
        
        member.payFine(amount);
        System.out.println("Payment processed by Librarian " + this.name);
        return true;
    }
    
    // Getters
    public int getStaffId(){
        return staffId;
    }
    
    public String getName(){
        return name;
    }
    
    public Library getLibrary(){
        return library;
    }
}
