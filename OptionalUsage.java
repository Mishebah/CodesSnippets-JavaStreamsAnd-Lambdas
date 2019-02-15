// File: OptionalUsage.java
import java.util.Optional;

// A book can have an optional blurb.
class Book {
  private String bookName;
  private Optional<String> optBlurb;

  public String getBookName() { return bookName; }
  public Optional<String> getOptBlurb() { return optBlurb; }

  public Book(String bookName, Optional<String> optBlurb) {
    this.bookName = bookName;
    this.optBlurb = optBlurb;
  }
}

// A course can have an optional book.
class Course {
  private Optional<Book> optBook;
  public Optional<Book> getOptBook() { return optBook; }
  public Course(Optional<Book> optBook) { this.optBook = optBook; }
}

public class OptionalUsage {
  public static void main(String[] args) {

    // Creating an Optional:
    Optional<String> blurb0 = Optional.of("Java Programmers tell all!");
    //Optional<String> xblurb = Optional.of(null);   // NullPointerExecption
    Optional<String> blurb1 = Optional.ofNullable("Program like a Java Pro!");
    Optional<String> noBlurb2 = Optional.ofNullable(null);   // Optional.empty()
    Optional<String> noBlurb3 = Optional.empty();

    // Create some books:
    Book book0 = new Book("Embarrassing Exceptions", blurb0);
    Book book1 = new Book("Cool Streams", blurb1);
    Book book2 = new Book("Dancing Lambdas", noBlurb2);      // No blurb.
    Book book3 = new Book("Untangled Threads", noBlurb3);    // No blurb.

    // Querying an Optional:
    if (book0.getOptBlurb().isPresent()) {
      System.out.println(book0.getOptBlurb().get());//Java Programmers tell all!
    }

    book0.getOptBlurb()
         .ifPresent(System.out::println);           //Java Programmers tell all!

//  System.out.println(book2.getOptBlurb().get());  // NoSuchElementException

    String blurb = book0.getOptBlurb()
                        .orElse("No blurb");     // "Java Programmers tell all!"
    System.out.println(blurb);

    blurb = book2.getOptBlurb().orElse("No blurb");           // "No blurb"
    System.out.println(blurb);

    blurb = book2.getOptBlurb().orElseGet(() -> "No blurb");  // "No blurb"
    System.out.println(blurb);

    //blurb = book2.getOptBlurb()                   // RuntimeException
    //             .orElseThrow(() -> new RuntimeException("No blurb"));

    System.out.println("==========");

    // Operations on an Optional:
    blurb = book0.getOptBlurb()
                 .filter(b -> b.length() >= 10)
                 .orElse("No blurb or blurb shorter than 10 characters.");
    System.out.println(blurb);      // Java Programmers tell all!

    blurb = book0.getOptBlurb()            // Optional<String>
                 .map(String::toUpperCase) // mapper: String -> String
                                           //   returns Optional<String>
                 .orElse("NO BLURB.");     // String
    System.out.println(blurb);             // JAVA PROGRAMMERS TELL ALL!

    blurb = book2.getOptBlurb()            // Optional<String>
                 .map(String::toUpperCase) // mapper: String -> String
                                           //   returns Optional<String>
                 .orElse("NO BLURB.");     // String
    System.out.println(blurb);             // NO BLURB.

    Integer length = book0.getOptBlurb()          // Optional<String>
                          .map(String::length)    // mapper: String -> Integer
                                                  //   returns Optional<Integer>
                          .orElse(0);             // Integer
    System.out.println(length);                   // 26.

    Course course1 = new Course(Optional.of(book0));// outer Optional<Book> with
                                                    //   inner Optional<String>

    blurb = course1.getOptBook()                  // Optional<Book>
                   .flatMap(Book::getOptBlurb)    // Optional<String>
                   .map(String::toUpperCase)      // Optional<String>
                   .orElse("NO BLURB.");          // String
    System.out.println(blurb);                    // JAVA PROGRAMMERS TELL ALL!
  }
}