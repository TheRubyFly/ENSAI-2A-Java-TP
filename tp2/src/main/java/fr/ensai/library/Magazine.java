package fr.ensai.library;

public class Magazine extends Item {

    // Attributes
    private String issn;
    private int IssueNumber;

    public Magazine(String issn, String title, int IssueNumber, int year, int pageCount) {
        this.issn = issn;
        this.title = title;
        this.IssueNumber = IssueNumber;
        this.year = year;
        this.pageCount = pageCount;
    }
}
