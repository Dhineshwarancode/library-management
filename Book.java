
public class Book{
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String status;
    private int quantity;

    public Book(String isbn, String title, String author, String genre, String status, int quantity){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.quantity = quantity;
    }

    public boolean checkAvailability(){
        return status.equals("Available");
    }

    public void updateStatus(String status){
        this.status = status;
    }

    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }
    public String getIsbn(){
        return isbn;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getGenre(){
        return genre;
    }
    public int getQuantity(){
        return quantity;
    }


    @Override
    public String toString(){
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Status: " + (status.equals("Available") ? "Available" : "Not Available") + ", Quantity: " + quantity;
    }
}