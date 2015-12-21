import java.rmi.RemoteException;
import java.util.List;

/**
 * This is the main Service class that provides all the necessary services that a server should provide
 * @author Rahul
 *
 */
public class ServerServiceImpl implements DataServerService {
	

	private static int searchCounter=0;
	
	private static int lookupCounter=0;
	
	private static int orderCounter=0;
	
	private static long seachServiceTimer=0;
	
	private static long lookupServiceTimer=0;
	
	private static long orderServiceTimer=0;
	
	private static int goodOrders=0;
	
	private static int badOrders=0;
	
	public ServerServiceImpl() {
		BookCatalog.initialize();
	}
	
	/**
	 * This method returns a list of books that have the same topic.
	 */
	@Override
	public  List<Book> search(String topic)throws RemoteException {
		System.out.println("Searching for topic : "+topic);
		long startTime = System.nanoTime();
		searchCounter = searchCounter+1;
		List<Book> booksOfSameTopic = BookCatalog.booksByTopic.get(topic);
		for(Book book:booksOfSameTopic){
			book.setItemsInStock(BookCatalog.booksById.get(book.getBookId()).intValue());
		}
		long endTime = System.nanoTime();
		seachServiceTimer = seachServiceTimer + endTime - startTime;
		return null;
	}

	/**
	 * This method returns a book if the bookId specified is available otherwise returns NULL.
	 */
	@Override
	public synchronized Book lookup(String bookId) throws RemoteException{
		System.out.println("Trying to look up book with bookId : "+bookId);
		long startTime = System.nanoTime();
		lookupCounter = lookupCounter+1;
		Book validBook = null;
		Integer stock = BookCatalog.booksById.get(bookId);
		if(null!=stock){
			List<Book> books = BookCatalog.availableBooks;
			for(Book book:books){
				if (bookId.equalsIgnoreCase(book.getBookId())){
					book.setItemsInStock(stock.intValue());
					validBook = book;
					break;
				}
			}
		}
		long endTime = System.nanoTime();
		lookupServiceTimer = lookupServiceTimer + endTime - startTime;
		return validBook;
	}

	@Override
	public synchronized boolean order(String bookId) throws RemoteException{
		System.out.println("Ordering book with bookId : "+bookId);
		long startTime = System.nanoTime();
		orderCounter = orderCounter+1;
		boolean ordered = false;
		Integer stock = BookCatalog.booksById.remove(bookId);
		if(null!=stock && stock.intValue()>0){
			stock = Integer.valueOf(stock.intValue()-1);
			BookCatalog.booksById.put(bookId, stock);
			ordered=true;
		}
		if(ordered){
			goodOrders=goodOrders+1;
		}else {
			badOrders=badOrders+1;
		}
		long endTime = System.nanoTime();
		orderServiceTimer = orderServiceTimer + endTime - startTime;
		return ordered;
	}

	@Override
	public int reportRequestNumber(String service) throws RemoteException{
		System.out.println("Reporting the number of requests for service "+service);
		switch(service){
		case "search": return (searchCounter<=0)?0:searchCounter;
		case "lookup": return (lookupCounter<=0)?0:lookupCounter;
		case "order": return (orderCounter<=0)?0:orderCounter;
		default : return -1;
		}
	}

	@Override
	public int reportGoodOrders() throws RemoteException{
		System.out.println("Reporting number of Good Orders");
		return goodOrders;
	}

	@Override
	public int reportFailedOrders() throws RemoteException{
		System.out.println("Reporting number of Failed Orders");
		return badOrders;
	}

	@Override
	public double reportServicePerformance(String service) throws RemoteException{
		System.out.println("Reporting Average running time of service :"+service);
		switch(service){
		case "search": return 1.0*seachServiceTimer/searchCounter;
		case "lookup": return 1.0*lookupServiceTimer/lookupCounter;
		case "order": return 1.0*orderServiceTimer/orderCounter;
		default : return -1;
		}
	}

	@Override
	public List<Book> sendAllBooks() throws RemoteException {
		List<Book> books = BookCatalog.availableBooks;
		for(Book book:books){
			book.setItemsInStock(BookCatalog.booksById.get(book.getBookId()).intValue());
		}
		return books;
	}

	@Override
	public void updateSearchCounter() throws RemoteException {
		System.out.println("Updating search counter");
		searchCounter = searchCounter+1;
		
	}

	@Override
	public long berkeleyAlgorithm(long nanoTime) {
		// TODO Auto-generated method stub
		return 0;
	}

}
