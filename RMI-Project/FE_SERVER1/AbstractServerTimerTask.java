
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;



public abstract class AbstractServerTimerTask extends TimerTask implements ServerService{
	
	static List<Book> books=null;
	private Registry registry;
	private DataServerService dataServerService;
	
	protected String feServer;
	
	protected long correction;

	public AbstractServerTimerTask(){
		try {
			registry = LocateRegistry.getRegistry(DATA_SERVER_HOST,DATA_SERVER_PORT);
			dataServerService = (DataServerService)registry.lookup("computeRegName");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
			
			try {
				books = dataServerService.sendAllBooks();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		
	}

	@Override
	public List<Book> search(String topic) throws RemoteException {
		System.out.println(feServer+" computing the List of Books of given topic: "+topic);
		List<Book> booksOfSameTopic = new ArrayList<Book>();
		for(Book book:books){
			if(book.getTopic().equalsIgnoreCase(topic)){
				booksOfSameTopic.add(book);
			}
		}
		dataServerService.updateSearchCounter();
		return booksOfSameTopic;
	}

	@Override
	public Book lookup(String bookId) throws RemoteException {
		System.out.println(feServer+" querying DataServer for lookup of book with ID "+bookId);
		return dataServerService.lookup(bookId);
	}

	@Override
	public boolean order(String bookId) throws RemoteException {
		System.out.println(feServer+" querying DataServer for ordering book with ID "+bookId);
		return dataServerService.order(bookId);
	}

	@Override
	public int reportRequestNumber(String service) throws RemoteException {
		System.out.println(feServer+" querying DataServer for reporting Request number of service "+service);
		return dataServerService.reportRequestNumber(service);
	}

	@Override
	public int reportGoodOrders() throws RemoteException {
		System.out.println(feServer+" querying DataServer for reporting number of Good Orders");
		return dataServerService.reportGoodOrders();
	}

	@Override
	public int reportFailedOrders() throws RemoteException {
		System.out.println(feServer+" querying DataServer for reporting number of Failed Orders");
		return dataServerService.reportFailedOrders();
	}

	@Override
	public double reportServicePerformance(String sevice) throws RemoteException {
		System.out.println(feServer+" querying DataServer for reporting performance of "+sevice+" service");
		return dataServerService.reportServicePerformance(sevice);
	}

	

}
