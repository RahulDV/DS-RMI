
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;


public class FrontEndServer2 {
	
	public static void main(String[] args){
		try {
			String name = "askServer2";
			AbstractServerTimerTask compute = new Server2TimerTask();
			ServerService stub = (ServerService) UnicastRemoteObject.exportObject(compute, ServerService.FE_SERVER2_PORT);
			Registry registry = LocateRegistry.createRegistry(ServerService.FE_SERVER2_PORT);
			registry.rebind(name, stub);
			
			Timer timer = new Timer();
			timer.schedule(compute, 0, 2000);

			System.out.println("FE Server2 Running");
		}catch (Exception e) {
            System.err.println("FE Server2 Exception:");
            e.printStackTrace();
        }
	}

}
