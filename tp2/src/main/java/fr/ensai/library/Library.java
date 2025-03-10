package fr.ensai.library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ensai.Loan;

public class Library {
    // Attributes
    private String name;
    private List<Item> items;
    private List<Loan> activeLoans;
    private List<Loan> completedLoans;

    // Constructor
    public Library(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    // Methods
    public void addItem(Item item) {
        this.items.add(item);
    }

    public void displayItems() {
        int n = this.items.size();
        if (n == 0) {
            System.out.print("Empty library, try to add some books");
        } else {
            for (Item item : this.items) {
                System.out.println(item.title);
            }
        }
    }

    public void loadBooksFromCSV(String filePath) {

        URL url = getClass().getClassLoader().getResource(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(url.getFile()))) {
            Map<String, Author> authors = new HashMap<>();
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5) {
                    String isbn = data[0].trim();
                    String title = data[1].trim();
                    String authorName = data[2].trim();
                    int year = Integer.parseInt(data[3].trim());
                    int pageCount = Integer.parseInt(data[4].trim());

                    // Check if author already exists in the map
                    Author author = authors.get(authorName);
                    if (author == null) {
                        author = new Author(authorName, 0, "US");
                        authors.put(authorName, author);
                        System.out.println(author.toString());
                    }
                    Book book = new Book(isbn, title, author, year, pageCount);

                    this.addItem(book);
                }
            }
        } catch (

        IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public Loan findActiveLoanForItem(Item item) {
        for (Loan loan : this.activeLoans) {
            if (loan.item.equals(item)) {
                return loan;
            }
        }
        return null;
    }

    public ArrayList<Book> getBooksbyAuthor(Author author) {
        ArrayList<Book> tempList = new ArrayList<>();
        for (Item item : this.items) {
            if (item instanceof Book) {
                Book tempBook = (Book) item;
                if (tempBook.getAuthor().equals(author)) {
                    tempList.add(tempBook);
                }
            }
        }
        return tempList;

    }

    public Boolean loanItem(Item item, Student student) {
        Loan isAvailable = this.findActiveLoanForItem(item);
        if (isAvailable.equals(null)) {
            Loan newLoan = new Loan(student, item, "today");
            this.activeLoans.add(newLoan);
            return true;
        }
        return false;
    }

    public Boolean renderItem(Item item) {
        for (Loan loan : this.activeLoans) {
            if (loan.item.equals(item)) {
                loan.setReturnDate("today");
                completedLoans.add(loan);
                activeLoans.remove(loan);
                return true;
            }
        }
        return false;
    }

    public void displayActiveLoan() {
        for (Loan loan : this.activeLoans) {
            System.out.println(loan.toString());
        }
    }
}
