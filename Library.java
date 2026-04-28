import java.util.*;

public class Library{
    private String libraryName;
    private List<Book> bookInventory;
    private List<Member> registeredMembers;
    private FinePolicy finePolicy;
    
    public Library(String libraryName, FinePolicy finePolicy){
        this.libraryName = libraryName;
        this.bookInventory = new ArrayList<>();
        this.registeredMembers = new ArrayList<>();
        this.finePolicy = finePolicy;
    }
    
    public boolean addBook(Book book){
        if(book == null){
            System.out.println("Cannot add null book.");
            return false;
        }
        
        for(Book existingBook : bookInventory){
            if(existingBook.getIsbn().equals(book.getIsbn())){
                
                existingBook.updateQuantity(existingBook.getQuantity() + book.getQuantity());
                System.out.println("Book already exists. Updated quantity to: " + existingBook.getQuantity());
                return true;
            }
        }
        
        bookInventory.add(book);
        System.out.println("Book added successfully: " + book.getTitle());
        return true;
    }
    
    public boolean removeBook(String isbn, int quantity){
        for(Book book : bookInventory){
            if(book.getIsbn().equals(isbn)){
                if(quantity <= 0){
                    System.out.println("Quantity must be greater than zero.");
                    return false;
                }
                
                if(book.getQuantity() < quantity){
                    System.out.println("Cannot remove " + quantity + " copies. Only " + book.getQuantity() + " available.");
                    return false;
                }
                
                if(book.getQuantity() == quantity){
                    bookInventory.remove(book);
                    System.out.println("Book removed completely from inventory: " + book.getTitle());
                } else {
                    book.updateQuantity(book.getQuantity() - quantity);
                    System.out.println("Removed " + quantity + " copies. Remaining: " + book.getQuantity());
                }
                return true;
            }
        }
        
        System.out.println("Book not found with ISBN: " + isbn);
        return false;
    }
    
    public Book getBook(String isbn){
        for(Book book : bookInventory){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return null;
    }
    
    public List<Book> getAllBooks(){
        return new ArrayList<>(bookInventory);
    }
    
    public boolean registerMember(Member member){
        if(member == null){
            System.out.println("Cannot register null member.");
            return false;
        }
        
        for(Member existingMember : registeredMembers){
            if(existingMember.getMemberId().equals(member.getMemberId())){
                System.out.println("Member already registered with ID: " + member.getMemberId());
                return false;
            }
        }
        
        registeredMembers.add(member);
        System.out.println("Member registered successfully: " + member.getName());
        return true;
    }
    
    public Member getMember(String memberId){
        for(Member member : registeredMembers){
            if(member.getMemberId().equals(memberId)){
                return member;
            }
        }
        return null;
    }
    
    public List<Member> getAllMembers(){
        return new ArrayList<>(registeredMembers);
    }
    
    public String getLibraryName(){
        return libraryName;
    }
    
    public FinePolicy getFinePolicy(){
        return finePolicy;
    }
    
    public void setFinePolicy(FinePolicy finePolicy){
        this.finePolicy = finePolicy;
        // Update all existing members with new policy
        for(Member member : registeredMembers){
            member.setFinePolicy(finePolicy);
        }
        System.out.println("Fine policy updated for library and all members.");
    }
}
