package wns.ForFire.entity;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String author;
    private String name_book;

    @Column(columnDefinition="TEXT")
    private String text_about_books;
    private String url_book;

    public Book(int id, String author, String name_book, String text_about_books, String url_book) {
        this.id = id;
        this.author = author;
        this.name_book = name_book;
        this.text_about_books = text_about_books;
        this.url_book = url_book;
    }

    public Book(String author, String name_book, String text_about_books, String url_book) {
        this.author = author;
        this.name_book = name_book;
        this.text_about_books = text_about_books;
        this.url_book = url_book;
    }

    public Book(int id, String author, String name_book, String text_about_books) {
        this.id = id;
        this.author = author;
        this.name_book = name_book;
        this.text_about_books = text_about_books;
    }

    public Book(String author, String name_book, String text_about_books) {
        this.author = author;
        this.name_book = name_book;
        this.text_about_books = text_about_books;
    }

    public Book(int id, String name_book, String text_about_books) {
        this.id = id;
        this.name_book = name_book;
        this.text_about_books = text_about_books;
    }

    public Book(String author,  String text_about_books) {
        this.author = author;
        this.text_about_books = text_about_books;
    }

    public Book() {
    }

    public String getUrl_book() {
        return url_book;
    }

    public void setUrl_book(String url_book) {
        this.url_book = url_book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getName_book() {
        return name_book;
    }

    public void setName_book(String name_book) {
        this.name_book = name_book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText_about_books() {
        return text_about_books;
    }

    public void setText_about_books(String text_about_books) {
        this.text_about_books = text_about_books;
    }
}
