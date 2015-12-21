import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a file that acts as a data repository for our small application
 * @author Rahul
 *
 */

public class BookCatalog {
	
	public static Map<String, Integer> booksById;
	
	public static Map<String ,List<Book>> booksByTopic;
	
	public static List<Book> availableBooks;
	
	public static void initialize(){
		List<Book> books = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setBookId();
		book1.setTitle("How to be good at S5523");
		book1.setCost(445);
		book1.setTopic(BookTopic.DS);
		Book book2 = new Book();
		book2.setBookId();
		book2.setTitle("RPCs and RMI in distributed systems");
		book2.setCost(500);
		book2.setTopic(BookTopic.DS);
		books.add(book1);
		books.add(book2);
		availableBooks = new ArrayList<Book>();
		availableBooks.addAll(books);
		booksByTopic = new HashMap<String,List<Book>>();
		booksByTopic.put(BookTopic.DS.getValue(), books);
		booksById = new HashMap<String,Integer>();
		booksById.put(book1.getBookId(), Integer.valueOf(10));
		booksById.put(book2.getBookId(), Integer.valueOf(15));
		books = new ArrayList<Book>();
		book1 = new Book();
		book1.setBookId();
		book1.setTitle("Why go to the graduate school");
		book1.setCost(200);
		book1.setTopic(BookTopic.GS);
		book2 = new Book();
		book2.setBookId();
		book2.setTitle("How to survive the graduate school");
		book2.setCost(150);
		book2.setTopic(BookTopic.GS);
		books.add(book1);
		books.add(book2);
		availableBooks.addAll(books);
		booksByTopic.put(BookTopic.GS.getValue(), books);
		booksById.put(book1.getBookId(), Integer.valueOf(20));
		booksById.put(book2.getBookId(), Integer.valueOf(15));
		System.out.println("Books Catalog Initialized. The bookstore is ready for bussiness");
	}
}
