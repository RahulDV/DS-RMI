

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;




public class Client {
	
	public static void main(String[] args){
		try{
			String name = "askServer1";
			String host=ServerService.FE_SERVER1_HOST;
			int port = ServerService.FE_SERVER1_PORT;
			System.out.println("Which Front Server you want to connect. 1, 2 or 3?");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch(choice){
			case 1: host=ServerService.FE_SERVER1_HOST;
					port=ServerService.FE_SERVER1_PORT;
					name="askServer1";
					break;
			case 2:	host=ServerService.FE_SERVER2_HOST;
					port=ServerService.FE_SERVER2_PORT;
					name="askServer2";
					break;
			case 3:	host=ServerService.FE_SERVER3_HOST;
					port=ServerService.FE_SERVER3_PORT;
					name="askServer3";
					break;
			}
			System.out.println("Connecting to FE Server on "+host+":"+port);
			Registry registry = LocateRegistry.getRegistry(host,port);
			ServerService compute = (ServerService)registry.lookup(name);
			System.out.println("Welcome to BookStore.\nPlease enter your service option");
			System.out.println("choice\tservice");
			System.out.println("1\tsearch by topic\n2\tlookup by bookId\n3\torder by bookId\n4\tReport number of requests by service\n5\thow many orders are good\n6\thow many orders failed\n7\tAverage performance time per service\n8\tQuit.");
			String topic,bookId,service;
			while(true){
				System.out.println("Enter your service choice");
				
			    choice = sc.nextInt();
			    switch(choice){
			    case 1: System.out.println("Pick the Topic: "+BookTopic.DS.getValue()+" or "+BookTopic.GS.getValue());
			    		topic = sc.next();
			    		printBooks(compute.search(topic));
			    		continue;
			    case 2: System.out.println("Enter the book id you want to look up. You may want to search before if you do not know the book id");
			    		bookId = sc.next();
			    		printBook(compute.lookup(bookId));
			    		continue;
			    case 3: System.out.println("Enter the book id you want to ORDER. You may want to search before if you do not know the book id");		
			    		bookId = sc.next();
			    		if(compute.order(bookId)){
			    			System.out.println("Book Ordered successfully");
			    		}else {
			    			System.out.println("Book Ordered Failed. Please check Items in stock before ordering");
			    		}
			    		continue;
			    case 4: System.out.println("Pick the service : "+AvailableServices.SEARCH.getValue()+", "+AvailableServices.LOOKUP.getValue()+", "+AvailableServices.ORDER.getValue());
			    		service = sc.next();
			    		int result = compute.reportRequestNumber(service);
			    		if (result<0){
			    			System.out.println("Incorrect service entered");
			    		} else {
			    			System.out.println("Number of requests for "+service+" service ="+result);
			    		}
			    		continue;
			    case 5: System.out.println("The number of Good orders = "+compute.reportGoodOrders());
			    		continue;
			    case 6: System.out.println("The number of Failure orders = "+compute.reportFailedOrders());
	    				continue;
			    case 7: System.out.println("Pick the service : "+AvailableServices.SEARCH.getValue()+", "+AvailableServices.LOOKUP.getValue()+", "+AvailableServices.ORDER.getValue());
	    				service = sc.next();
	    				double perf = compute.reportServicePerformance(service);
	    				if (perf<0){
			    			System.out.println("Incorrect service entered");
			    		} else {
			    			System.out.println("Average Running time, in nanoseconds, of "+service+" service ="+perf);
			    		}
			    		continue;
			    case 8: System.out.println("Bye Bye!");
			    		break;
			    
			    }
			    break;
			}
		}catch (Exception e) {
            System.err.println("Client Exception:");
            e.printStackTrace();
        }
	}
	
	private static void printBooks(List<Book> books){
		if(null==books){
			System.out.println("Selected Topic Not available in BookStore");
			return;
		}
		for(Book book:books){
			System.out.println("Book Title : "+book.getTitle());
			System.out.println("Book Topic : "+book.getTopic());
			System.out.println("Book ID : "+book.getBookId());
			System.out.println("Book Cost : "+book.getCost());
			System.out.println("Items in Stock : "+book.getItemsInStock());
		}
	}
	
	private static void printBook(Book book){
		if(null==book){
			System.out.println("There is no Book with given book ID");
			return;
		}
		System.out.println("Book Title : "+book.getTitle());
		System.out.println("Book Topic : "+book.getTopic());
		System.out.println("Book ID : "+book.getBookId());
		System.out.println("Book Cost : "+book.getCost());
		System.out.println("Items in Stock : "+book.getItemsInStock());
	}
}
