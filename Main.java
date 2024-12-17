package libb2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Management System Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Record Book Borrowing");
            System.out.println("4. Get Book by ID");
            System.out.println("5. Get Member by ID");
            System.out.println("6. Get Borrow Record by ID");
            System.out.println("7. Update Book");
            System.out.println("8. Update Member");
            System.out.println("9. Delete Book");
            System.out.println("10. Delete Member");
            System.out.println("11. Delete Borrow Record");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Book
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter book author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter number of copies available: ");
                    int copies = sc.nextInt();
                    Book book = new Book(title, author, copies);
                    DB.addBook(book);
                    System.out.println("Book added successfully.");
                    break;

                case 2: // Add Member
                    System.out.print("Enter member name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter member email: ");
                    String email = sc.nextLine();
                    Member member = new Member(name, email);
                    DB.addMember(member);
                    System.out.println("Member added successfully.");
                    break;

                case 3: // Record Book Borrowing
                    System.out.print("Enter member ID: ");
                    int memberId = sc.nextInt();
                    System.out.print("Enter book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter borrow date (YYYY-MM-DD): ");
                    String borrowDate = sc.nextLine();
                    System.out.print("Enter return date (YYYY-MM-DD): ");
                    String returnDate = sc.nextLine();

                    Member m = DB.getMemberById(memberId);
                    Book b = DB.getBookById(bookId);

                    if (m != null && b != null && b.getCopiesAvailable() > 0) {
                        Library borrowRecord = new Library(memberId, bookId, borrowDate, returnDate);
                        DB.addBorrowRecord(borrowRecord);

                        // Update book availability
                        DB.updateBook(bookId, b.getTitle(), b.getAuthor(), b.getCopiesAvailable() - 1);
                        System.out.println("Borrowing record added successfully.");
                    } else {
                        System.out.println("Invalid member, book not available, or book not found.");
                    }
                    break;

                case 4: // Get Book by ID
                    System.out.print("Enter book ID: ");
                    int bookIdToFetch = sc.nextInt();
                    Book fetchedBook = DB.getBookById(bookIdToFetch);
                    if (fetchedBook != null) {
                        System.out.println("Book ID: " + fetchedBook.getId());
                        System.out.println("Title: " + fetchedBook.getTitle());
                        System.out.println("Author: " + fetchedBook.getAuthor());
                        System.out.println("Copies Available: " + fetchedBook.getCopiesAvailable());
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 5: // Get Member by ID
                    System.out.print("Enter member ID: ");
                    int memberIdToFetch = sc.nextInt();
                    Member fetchedMember = DB.getMemberById(memberIdToFetch);
                    if (fetchedMember != null) {
                        System.out.println("Member ID: " + fetchedMember.getId());
                        System.out.println("Name: " + fetchedMember.getName());
                        System.out.println("Email: " + fetchedMember.getEmail());
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;

                case 6: // Get Borrow Record by ID
                    System.out.print("Enter borrowing record ID: ");
                    int borrowIdToFetch = sc.nextInt();
                    Library fetchedRecord = DB.getLibraryById(borrowIdToFetch);
                    if (fetchedRecord != null) {
                        System.out.println("Borrowing Record ID: " + fetchedRecord.getId());
                        System.out.println("Member ID: " + fetchedRecord.getMemberId());
                        System.out.println("Book ID: " + fetchedRecord.getBookId());
                        System.out.println("Borrow Date: " + fetchedRecord.getBorrowDate());
                        System.out.println("Return Date: " + fetchedRecord.getReturnDate());
                    } else {
                        System.out.println("Borrowing record not found.");
                    }
                    break;

                case 7: // Update Book
                    System.out.print("Enter book ID to update: ");
                    int bookIdToUpdate = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter new title: ");
                    String newTitle = sc.nextLine();
                    System.out.print("Enter new author: ");
                    String newAuthor = sc.nextLine();
                    System.out.print("Enter new number of copies: ");
                    int newCopies = sc.nextInt();
                    DB.updateBook(bookIdToUpdate, newTitle, newAuthor, newCopies);
                    System.out.println("Book updated successfully.");
                    break;

                case 8: // Update Member
                    System.out.print("Enter member ID to update: ");
                    int memberIdToUpdate = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();
                    DB.updateMember(memberIdToUpdate, newName, newEmail);
                    System.out.println("Member updated successfully.");
                    break;

                case 9: // Delete Book
                    System.out.print("Enter book ID to delete: ");
                    int bookIdToDelete = sc.nextInt();
                    DB.deleteBook(bookIdToDelete);
                    System.out.println("Book deleted successfully.");
                    break;

                case 10: // Delete Member
                    System.out.print("Enter member ID to delete: ");
                    int memberIdToDelete = sc.nextInt();
                    DB.deleteMember(memberIdToDelete);
                    System.out.println("Member deleted successfully.");
                    break;

                case 11: // Delete Borrow Record
                    System.out.print("Enter borrowing record ID to delete: ");
                    int borrowIdToDelete = sc.nextInt();
                    DB.deleteBorrowRecord(borrowIdToDelete);
                    System.out.println("Borrowing record deleted successfully.");
                    break;

                case 0: // Exit
                    System.out.println("Exiting program. Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}