package libb2;

import javax.persistence.*;
import java.util.List;

public class DB {

    private static final String DB_URL = "objectdb/db/library.odb";

    // Add a Book
    public static void addBook(Book b) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    // Add a Member
    public static void addMember(Member m) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    // Add Borrow Record
    public static void addBorrowRecord(Library record) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(record);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    // Retrieve a Book by ID
    public static Book getBookById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        Book b = em.find(Book.class, id);
        em.close();
        emf.close();
        return b;
    }

    // Retrieve a Member by ID
    public static Member getMemberById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        Member m = em.find(Member.class, id);
        em.close();
        emf.close();
        return m;
    }

    // Retrieve Borrow Record by ID
    public static Library getLibraryById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        Library record = em.find(Library.class, id);
        em.close();
        emf.close();
        return record;
    }

    // Update Book Details
    public static void updateBook(int id, String newTitle, String newAuthor, int newCopies) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        Book b = em.find(Book.class, id);
        if (b != null) {
            em.getTransaction().begin();
            b.setTitle(newTitle);
            b.setAuthor(newAuthor);
            b.setCopiesAvailable(newCopies);
            em.getTransaction().commit();
        }
        em.close();
        emf.close();
    }

    // Update Member Details
    public static void updateMember(int id, String newName, String newEmail) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        Member m = em.find(Member.class, id);
        if (m != null) {
            em.getTransaction().begin();
            m.setName(newName);
            m.setEmail(newEmail);
            em.getTransaction().commit();
        }
        em.close();
        emf.close();
    }

    // Delete Book
    public static void deleteBook(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        Book b = em.find(Book.class, id);
        if (b != null) {
            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
        }
        em.close();
        emf.close();
    }

    // Delete Member
    public static void deleteMember(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        Member m = em.find(Member.class, id);
        if (m != null) {
            em.getTransaction().begin();
            em.remove(m);
            em.getTransaction().commit();
        }
        em.close();
        emf.close();
    }

    // Delete Borrow Record
    public static void deleteBorrowRecord(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        Library record = em.find(Library.class, id);
        if (record != null) {
            em.getTransaction().begin();
            em.remove(record);
            em.getTransaction().commit();
        }
        em.close();
        emf.close();
    }

    // List all Books
    public static List<Book> getAllBooks() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        em.close();
        emf.close();
        return books;
    }

    // List all Members
    public static List<Member> getAllMembers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        List<Member> members = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
        em.close();
        emf.close();
        return members;
    }

    // List all Borrow Records
    public static List<Library> getAllBorrowRecords() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DB_URL);
        EntityManager em = emf.createEntityManager();
        List<Library> records = em.createQuery("SELECT l FROM Library l", Library.class).getResultList();
        em.close();
        emf.close();
        return records;
    }
}