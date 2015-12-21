import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerService extends Remote{
	
	public static final String DATA_SERVER_HOST = "localhost";
	
	public static final int DATA_SERVER_PORT = 1099;
	
	public static final String FE_SERVER1_HOST = "localhost";
	
	public static final int FE_SERVER1_PORT = 1120;
	
	public static final String FE_SERVER2_HOST = "localhost";
	
	public static final int FE_SERVER2_PORT = 1122;
	
	public static final String FE_SERVER3_HOST = "localhost";
	
	public static final int FE_SERVER3_PORT = 1124;
	
	public List<Book> search(String topic)throws RemoteException;
	
	public Book lookup(String bookId)throws RemoteException;
	
	public boolean order(String bookId)throws RemoteException;
	
	//Reporting method definitions from here onwards
	
	public int reportRequestNumber(String service)throws RemoteException;
	
	public int reportGoodOrders()throws RemoteException;
	
	public int reportFailedOrders()throws RemoteException;
	
	public double reportServicePerformance(String sevice)throws RemoteException;

	public long berkeleyAlgorithm(long nanoTime)throws RemoteException;
	
	
}
