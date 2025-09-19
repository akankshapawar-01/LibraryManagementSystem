package Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManagementSystem library = new LibraryManagementSystem();

        System.out.println("Welcome to the Library Management System");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Available Books");
            System.out.println("6. Check Overdue Books");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    Book book = new Book(0, title, author, quantity);
                    library.addBook(book);
                }

                case 2 -> {
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member email: ");
                    String email = scanner.nextLine();
                    Member member = new Member(0, name, email);
                    library.addMember(member);
                }

                case 3 -> {
                    System.out.print("Enter book ID to issue: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();
                    library.issueBook(bookId, memberId);
                }

                case 4 -> {
                    System.out.print("Enter issue ID to return: ");
                    int issueId = scanner.nextInt();
                    scanner.nextLine();
                    library.returnBook(issueId);
                }

                case 5 -> library.viewAvailableBooks();

                case 6 -> library.checkOverdueBooks();

                case 7 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
