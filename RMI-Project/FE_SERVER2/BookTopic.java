import java.io.Serializable;

public enum BookTopic implements Serializable{
	
	DS ("Distributed_Systems"),
	GS ("Graduate_School");
	
	private final String value;
	
	private BookTopic(String value){
		this.value=value;
	}
	
	public String getValue(){
		return this.value;
	}
}
