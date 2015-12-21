import java.rmi.RemoteException;
import java.util.List;

public interface DataServerService extends ServerService {
	
	public List<Book> sendAllBooks() throws RemoteException;
	
	public void updateSearchCounter()throws RemoteException;

}
