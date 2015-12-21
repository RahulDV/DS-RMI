import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	

	public static void main(String[] args){
		try {
			String name = "computeRegName";
			ServerService compute = new ServerServiceImpl();
			ServerService stub = (ServerService) UnicastRemoteObject.exportObject(compute, ServerService.DATA_SERVER_PORT);
			Registry registry = LocateRegistry.createRegistry(ServerService.DATA_SERVER_PORT);
			registry.rebind(name, stub);

			System.out.println("Server Running");
		}catch (Exception e) {
            System.err.println("Server Exception:");
            e.printStackTrace();
        }
	}
	
}
