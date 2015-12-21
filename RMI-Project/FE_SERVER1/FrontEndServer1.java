

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;



public class FrontEndServer1 {
	
	public static void main(String[] args){
		try {
			String name = "askServer1";
			AbstractServerTimerTask compute = new Server1TimerTask();
			ServerService stub = (ServerService) UnicastRemoteObject.exportObject(compute, ServerService.FE_SERVER1_PORT);
			Registry registry = LocateRegistry.createRegistry(ServerService.FE_SERVER1_PORT);
			registry.rebind(name, stub);
			
			Timer timer = new Timer();
			timer.schedule(compute, 0, 2000);

			System.out.println("FE Server1 Running");
		}catch (Exception e) {
            System.err.println("FE Server1 Exception:");
            e.printStackTrace();
        }
	}

}
