PROBLEM STATEMENT

A console-based system to manage library operations — including book inventory management, member registration, borrowing/returning books, overdue tracking, and fine collection — without requiring a database or external services.

Libraries need a lightweight system to:
- Track book inventory (add/remove copies by ISBN)
- Register and manage members
- Handle borrow/return workflows with due dates
- Automatically calculate overdue fines
- Prevent borrowing when unpaid fines exist

This project solves all of the above via a role-based terminal interface.



Approach & Logic

The system is built around six core classes:

 Class
`Library` - Central registry for books and members 
`Librarian` - Delegates inventory/member operations to Library 
`Member` - Manages borrow records, fine balance 
`Book` - Stores ISBN, title, author, genre, quantity 
`BorrowRecord` - Tracks issue date, due date, overdue status 
`FinePolicy` - Configurable fine rate (decoupled from Member) 

Key rules enforced:
- A member can hold at most 3 active borrows
- Borrowing is blocked if fines are unpaid
- Fine calculation uses the `FinePolicy` object injected into each `Member`, so rates can be changed in one place
- All state is initialised in `initializeSystem()` (pre-seeded books and members for testing)


Execution steps :
1) cd LibraryManagementSystem
2) javac Main.java
3) java Main
