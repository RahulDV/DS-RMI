
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;


public class FrontEndServer3 {
	
	public static void main(String[] args){
		try {
			String name = "askServer3";
			AbstractServerTimerTask compute = new Server3TimerTask();
			ServerService stub = (ServerService) UnicastRemoteObject.exportObject(compute, ServerService.FE_SERVER3_PORT);
			Registry registry = LocateRegistry.createRegistry(ServerService.FE_SERVER3_PORT);
			registry.rebind(name, stub);
			
			Timer timer = new Timer();
			timer.schedule(compute, 0, 2000);

			System.out.println("FE Server3 Running");
		}catch (Exception e) {
            System.err.println("FE Server3 Exception:");
            e.printStackTrace();
        }
	}

}
