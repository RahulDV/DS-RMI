
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class Server2TimerTask extends AbstractServerTimerTask{
	
	private Registry registry;
	private ServerService server1TimerTask=null;
	
	public Server2TimerTask(){
		super.feServer="FE Server2";
		try {
			registry = LocateRegistry.getRegistry(FE_SERVER1_HOST,FE_SERVER1_PORT);
			server1TimerTask = (ServerService)registry.lookup("askServer1");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		super.run();
		try {
			if(null!=server1TimerTask){
				
					super.correction =  server1TimerTask.berkeleyAlgorithm(System.nanoTime());
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Correction in system time calculated is: "+super.correction);
	}

	@Override
	public long berkeleyAlgorithm(long nanoTime) {
		// TODO Auto-generated method stub
		return 0;
	}

}
