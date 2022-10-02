package app.routes.books;

public class Book {
    public final String title;
    public final String author;
    public final String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getMediumCover() {
        return "http://covers.openlibrary.org/b/isbn/" + this.isbn + "-M.jpg";
    }

    public String getLargeCover() {
        return "http://covers.openlibrary.org/b/isbn/" + this.isbn + "-L.jpg";
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean match(String pattern) {
        String value = title.toLowerCase() + "\t" + this.author.toLowerCase() + "\t" + this.isbn;
        return value.contains(pattern.toLowerCase());
    }
}
