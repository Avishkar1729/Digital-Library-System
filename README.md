# Digital-Library-System
Digital Library Book Management System

Overview -

The Digital Library Book Management System is a Spring Boot application integrated with MongoDB Atlas to manage books efficiently. It allows librarians to add, update, search, and delete book records while maintaining their availability status.


Features-

Add a Book: Store book details including Book ID, Title, Author, Genre, and Availability Status.

View All Books: Retrieve and display a list of all books.

Search a Book: Find books by their ID or Title.

Update Book Details: Modify book details like title, author, and availability status.

Delete a Book: Remove a book record from the system.

MongoDB Atlas Integration: Data is stored in a cloud-based MongoDB database.

RESTful API Implementation: Allows interaction via HTTP requests.


Tech Stack-

Backend: Java, Spring Boot

Database: MongoDB Atlas

Build Tool: Maven

Version Control: Git & GitHub


Prerequisites -

Java 17+ installed

Maven installed

MongoDB Atlas account (if running the database remotely)

Git (optional, for version control)


Setup & Installation -

1️⃣ Clone the Repository

 git clone https://github.com/Avishkar1729/Digital-Library-System.git
 cd Digital-Library-System

2️⃣ Configure MongoDB Connection

Create a .env file in the project root with the following content:

MONGO_URI=mongodb+srv://your_username:your_password@cluster.mongodb.net/your_dbname

Or, set the environment variable manually:

export MONGO_URI=mongodb+srv://your_username:your_password@cluster.mongodb.net/your_dbname

For Windows (CMD/PowerShell):

set MONGO_URI=mongodb+srv://your_username:your_password@cluster.mongodb.net/your_dbname

3️⃣ Build the Project

mvn clean package

4️⃣ Run the Application

java -jar target/DigitalLibrarySystem.jar


API Endpoints -

1.Get all books

GET
/api/books

2.Get a book by ID

GET
/api/books/id/{bookId}

3.Get a book by Title

GET
/api/books/title/{bookTitle}

4.Add a new book

POST
/api/books

5.Update a book by ID

PUT
/api/books/{bookId}

6.Delete a book by ID

DELETE
/api/books/{bookId}


License

This project is licensed under the Avishkar Shinde License.
