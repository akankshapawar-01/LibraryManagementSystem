package Library;

import java.sql.*;
import java.time.LocalDate;

public class LibraryManagementSystem {

    // Add a new book
    public void addBook(Book book) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getQuantity());
            pstmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new member
    public void addMember(Member member) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO members (name, email) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.executeUpdate();
            System.out.println("Member added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Issue a book
    public void issueBook(int bookId, int memberId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkBook = "SELECT quantity FROM books WHERE book_id = ?";
            PreparedStatement pstmt1 = conn.prepareStatement(checkBook);
            pstmt1.setInt(1, bookId);
            ResultSet rs = pstmt1.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                if (quantity > 0) {
                    String insertIssue = "INSERT INTO issued_books (book_id, member_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt2 = conn.prepareStatement(insertIssue);
                    pstmt2.setInt(1, bookId);
                    pstmt2.setInt(2, memberId);
                    Date issueDate = Date.valueOf(LocalDate.now());
                    Date returnDate = Date.valueOf(LocalDate.now().plusDays(14));
                    pstmt2.setDate(3, issueDate);
                    pstmt2.setDate(4, returnDate);
                    pstmt2.executeUpdate();

                    String updateBook = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";
                    PreparedStatement pstmt3 = conn.prepareStatement(updateBook);
                    pstmt3.setInt(1, bookId);
                    pstmt3.executeUpdate();

                    System.out.println("Book issued successfully. Return by " + returnDate);
                } else {
                    System.out.println("Book is not available.");
                }
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Return a book
    public void returnBook(int issueId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String findIssue = "SELECT book_id FROM issued_books WHERE issue_id = ?";
            PreparedStatement pstmt1 = conn.prepareStatement(findIssue);
            pstmt1.setInt(1, issueId);
            ResultSet rs = pstmt1.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("book_id");

                String deleteIssue = "DELETE FROM issued_books WHERE issue_id = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(deleteIssue);
                pstmt2.setInt(1, issueId);
                pstmt2.executeUpdate();

                String updateBook = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";
                PreparedStatement pstmt3 = conn.prepareStatement(updateBook);
                pstmt3.setInt(1, bookId);
                pstmt3.executeUpdate();

                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Issue record not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View available books
    public void viewAvailableBooks() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM books WHERE quantity > 0";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Available Books:");
            while (rs.next()) {
                System.out.println("Book ID: " + rs.getInt("book_id") +
                                   ", Title: " + rs.getString("title") +
                                   ", Author: " + rs.getString("author") +
                                   ", Quantity: " + rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check overdue books
    public void checkOverdueBooks() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT ib.issue_id, b.title, m.name, ib.return_date FROM issued_books ib " +
                           "JOIN books b ON ib.book_id = b.book_id " +
                           "JOIN members m ON ib.member_id = m.member_id " +
                           "WHERE ib.return_date < ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Overdue Books:");
            while (rs.next()) {
                System.out.println("Issue ID: " + rs.getInt("issue_id") +
                                   ", Title: " + rs.getString("title") +
                                   ", Member: " + rs.getString("name") +
                                   ", Return Date: " + rs.getDate("return_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
