# Library Management System 📚
A simple Library Management System built using Java, JDBC, and MySQL .
- This project demonstrates how to manage books, members, and book issues/returns in a library using a console-based Java application.
---

## ⭐ Features
- Add new books to the library
- Add new members
- Issue books to members
- Return books
- View available books
- Check overdue books
---

## 🛠 Technologies Used
- **Java** – Object-Oriented Programming for core logic
- **JDBC** – Connects Java application to MySQL database
- **MySQL** – Stores books, members, and issued books
---

## Project Structure
```
LibraryManagementSystem/
│
├── README.md
└── src/
     └── Library/
            ├── Book.java
            ├── Member.java
            ├── Issue.java
            ├── DatabaseConnection.java
            ├── LibraryManagementSystem.java
            └── Main.java
```
---

## Setup Instructions
**Clone the repository**  
```bash
   git clone https://github.com/akankshapawar-01/LibraryManagementSystem.git
   cd LibraryManagementSystem
```

Create MySQL database
```
CREATE DATABASE library_db;
USE library_db;

CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    quantity INT
);

CREATE TABLE members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE issued_books (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    member_id INT,
    issue_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);
```

- Update DatabaseConnection.java
  
- Replace placeholder values with your MySQL username/password:
    - private static final String USER = "your_username";
    - private static final String PASSWORD = "your_password_here";
      
- Run the project
    - Open Main.java in your IDE and run it.
    - Follow the console menu to add books/members, issue/return books, and view available/overdue books.
---

## 📌 Concepts Covered 
- Object-Oriented Programming (classes, objects, encapsulation)
- Java basics (loops, switch-case, user input with Scanner)
- JDBC (database connectivity, CRUD operations)
- MySQL (tables, relationships, joins, queries)
- Separation of concerns (modular classes & DB connection handling)
---

## 👩‍💻 Author
Akanksha Pawar | Computer Engineering Graduate | Aspiring Java Developer
