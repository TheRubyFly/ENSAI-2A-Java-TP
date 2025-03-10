package fr.ensai;

import fr.ensai.library.Item;
import fr.ensai.library.Student;

public class Loan {

    // Attributes

    private Student student;
    public Item item;
    private String startDate;
    private String returnDate;

    // Constructor

    public Loan(Student student, Item item, String startDate) {
        this.student = student;
        this.item = item;
        this.startDate = startDate;
        this.returnDate = null;
    }

    // Methods

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String toString() {
        return "Item" + item + "borrowed by" + student;
    }
}
