import java.util.*;

public class Main{
    private static Library library;
    private static Librarian librarian;
    private static Scanner scanner;

    public static void main(String[] args){
        
        initializeSystem();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("=== Welcome to the Library Management System ===");
        scanner = new Scanner(System.in);
        while(true){
            System.out.println("\nMain Menu:");
            System.out.println("1. Librarian/Library Member");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch(choice){
                    case 1:
                        handleRoleSelection();
                        break;
                    case 2:
                        System.out.println("Exiting the system. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void initializeSystem(){
        // Create FinePolicy with tiered rates
        FinePolicy finePolicy = new FinePolicy(0.0, 0.0, 0);
        library = new Library("City Public Library", finePolicy);
        librarian = new Librarian(101, "admin01", library);
        Book book1 = new Book("ISBN001", "dummy1", "name1", "Classic", "Available", 5);
        Book book2 = new Book("ISBN002", "dummy2", "name2", "Classic", "Available", 3);
        Book book3 = new Book("ISBN003", "dummy3", "name3", "drama", "Available", 4);
        Book book4 = new Book("ISBN004", "dummy4", "name4", "History", "Available", 2);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        Member member1 = new Member("M001", "Dhinesh", "dhinesh@email.com", finePolicy);
        member1.setFines(100.0);
        Member member2 = new Member("M002", "Dhin", "dhin@email.com", finePolicy);
        library.registerMember(member1);
        library.registerMember(member2);
        System.out.println("Library Management System initialized successfully!");
    }

    private static void handleRoleSelection(){
        while(true){
            System.out.println("\nSelect Role:");
            System.out.println("1. Librarian");
            System.out.println("2. Library Member");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int roleChoice = scanner.nextInt();
                scanner.nextLine(); 

                switch(roleChoice){
                    case 1:
                        handleLibrarianMenu();
                        break;
                    case 2:
                        handleMemberMenu();
                        break;
                    case 3:
                        return; 
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void handleLibrarianMenu(){
        while(true){
            System.out.println("\n=== LIBRARIAN MENU ===");
            System.out.println("1. Add Books");
            System.out.println("2. Remove Books");
            System.out.println("3. Add Members");
            System.out.println("4. Back to Role Selection");
            System.out.print("Enter your choice: ");

            try {
                int librarianChoice = scanner.nextInt();
                scanner.nextLine(); 

                switch(librarianChoice){
                    case 1:
                        addBooks();
                        break;
                    case 2:
                        removeBooks();
                        break;
                    case 3:
                        addMembers();
                        break;
                    case 4:
                        return; 
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            }
        }
    }

    private static void handleMemberMenu(){
        System.out.print("Enter your Member ID: ");
        String memberId = scanner.nextLine().trim();
        Member member = library.getMember(memberId);
        if(member == null){
            System.out.println("Member not found with ID: " + memberId);
            return;
        }
        System.out.println("Welcome, " + member.getName() + "!");

        while(true){
            System.out.println("\n=== LIBRARY MEMBER MENU ===");
            System.out.println("Member ID: " + member.getMemberId());
            System.out.println("Outstanding Fines: ₹" + String.format("%.2f", member.getFines()));
            System.out.println();
            System.out.println("1. Borrow Books");
            System.out.println("2. Check Available Books");
            System.out.println("3. Return Books");
            System.out.println("4. Pay Fines");
            System.out.println("5. View My Borrowed Books");
            System.out.println("6. Back to Role Selection");
            System.out.print("Enter your choice: ");
            try {
                int memberChoice = scanner.nextInt();
                scanner.nextLine(); 

                switch(memberChoice){
                    case 1:
                        borrowBooks(member);
                        break;
                    case 2:
                        checkAvailableBooks();
                        break;
                    case 3:
                        returnBooks(member);
                        break;
                    case 4:
                        payFines(member);
                        break;
                    case 5:
                        viewBorrowedBooks(member);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    // Librarian Functions
    private static void addBooks(){
        System.out.println("\n--- Add New Book ---");
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        try {
            int quantity = scanner.nextInt();
            scanner.nextLine();
            if(quantity <= 0){
                System.out.println("Quantity must be greater than 0.");
                return;
            }
            Book newBook = new Book(isbn, title, author, genre, "Available", quantity);
            librarian.addBook(newBook);
        } catch (InputMismatchException e) {
            System.out.println("Invalid quantity. Please enter a number.");
            scanner.nextLine();
        }
    }
    private static void removeBooks(){
        System.out.println("\n--- Remove Books ---");
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();

        System.out.print("Enter quantity to remove: ");
        try {
            int quantity = scanner.nextInt();
            scanner.nextLine();
            librarian.removeBook(isbn, quantity);
        } catch (InputMismatchException e) {
            System.out.println("Invalid quantity. Please enter a number.");
            scanner.nextLine();
        }
    }
    private static void addMembers(){
        System.out.println("\n--- Add New Member ---");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        Member newMember = new Member(memberId, name, email, library.getFinePolicy());
        library.registerMember(newMember);
    }


    private static void borrowBooks(Member member){
        System.out.println("\n--- Borrow Books ---");
        if(member.getFines() > 0){
            System.out.println("You have fines of ₹" + String.format("%.2f", member.getFines()));
            System.out.println("Please pay your fines before borrowing books.");
            return;
        }
        if(member.getActiveBorrows().size() >= 3){
            System.out.println("You have reached the maximum limit of 3 active borrows.");
            return;
        }
        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.nextLine();
        Book book = library.getBook(isbn);
        if(book == null){
            System.out.println("Book not found with ISBN: " + isbn);
            return;
        }
        member.borrowBook(book);
    }
    private static void checkAvailableBooks(){
        System.out.println("\n--- Available Books ---");
        List<Book> books = library.getAllBooks();
        if(books.isEmpty()){
            System.out.println("No books in inventory.");
            return;
        }
        System.out.printf("%-15s %-30s %-20s %-10s %-10s%n", "ISBN", "Title", "Author", "Genre", "Available");
        System.out.println("=".repeat(85));
        for(Book book : books){
            if(book.getQuantity() > 0){
                System.out.printf("%-15s %-30s %-20s %-10s %-10d%n",
                    book.getIsbn(),
                    book.getTitle().length() > 28 ? book.getTitle().substring(0, 25) + "..." : book.getTitle(),
                    book.getAuthor().length() > 18 ? book.getAuthor().substring(0, 15) + "..." : book.getAuthor(),
                    book.getGenre(),
                    book.getQuantity());
            }
        }
    }
    private static void returnBooks(Member member){
        System.out.println("\n--- Return Books ---");
        if(member.getActiveBorrows().isEmpty()){
            System.out.println("You have no active borrows to return.");
            return;
        }
        System.out.println("Your active borrows:");
        for(int i = 0; i < member.getActiveBorrows().size(); i++){
            BorrowRecord borrow = member.getActiveBorrows().get(i);
            System.out.println((i+1) + ". " + borrow.getBookTitle() + " (ISBN: " + borrow.getIsbn() + ")");
        }
        System.out.print("Enter ISBN of book to return: ");
        String isbn = scanner.nextLine().trim();
        Book book = library.getBook(isbn);
        if(book == null){
            System.out.println("Book not found with ISBN: " + isbn);
            return;
        }
        member.returnBook(isbn, book);
    }
    private static void payFines(Member member){
        System.out.println("\n--- Pay Fines ---");
        System.out.println("Your outstanding fines: ₹" + String.format("%.2f", member.getFines()));
        if(member.getFines() <= 0){
            System.out.println("You have no outstanding fines.");
            return;
        }
        System.out.print("Enter payment amount: ₹");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            member.payFine(amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a number.");
            scanner.nextLine();
        }
    }
    private static void viewBorrowedBooks(Member member){
        System.out.println("\n--- Your Borrowed Books ---");
        List<BorrowRecord> borrows = member.getActiveBorrows();
        if(borrows.isEmpty()){
            System.out.println("You have no active borrows.");
            return;
        }
        System.out.printf("%-30s %-15s %-12s %-12s %-10s%n", "Title", "ISBN", "Issue Date", "Due Date", "Status");
        System.out.println("=".repeat(90));

        for(BorrowRecord borrow : borrows){
            System.out.printf("%-30s %-15s %-12s %-12s %-10s%n",
                borrow.getBookTitle().length() > 28 ? borrow.getBookTitle().substring(0, 25) + "..." : borrow.getBookTitle(),
                borrow.getIsbn(),
                borrow.getIssueDate(),
                borrow.getDueDate(),
                borrow.getStatus());
            if(borrow.isOverdue()){
                long daysOverdue = borrow.getDaysOverdue();
                double fine = borrow.calculateFine(member.getFinePolicy());
                System.out.println("    *** OVERDUE by " + daysOverdue + " days. Fine: ₹" + String.format("%.2f", fine) + " ***");
            }
        }
    }
}
