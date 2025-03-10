package fr.ensai.library;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Author tolkien = new Author("J.R.R. Tolkien", 81, "UK");

        Book fellowshipOfTheRing = new Book(
                "978-0-618-26025-6",
                "The Fellowship of the Ring",
                tolkien,
                1954,
                423);

        System.out.println(fellowshipOfTheRing.toString());

        Library myLibrary = new Library("myLibrary", new ArrayList<>());
        myLibrary.loadBooksFromCSV("books.csv");
        Magazine testMagazine = new Magazine("45908", "Voici", 834, 2024, 48);
        Magazine testMagazine2 = new Magazine("93708", "Sciences & Vie", 234, 2021, 96);
        myLibrary.addItem(testMagazine);
        myLibrary.addItem(testMagazine2);
        myLibrary.displayItems();
    }
}