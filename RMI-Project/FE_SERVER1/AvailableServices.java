
public enum AvailableServices {
	
	SEARCH("search"), LOOKUP("lookup"), ORDER("order");
	
	private String value;
	
	private AvailableServices(String value){
		this.value=value;
	}

	public String getValue(){
		return this.value;
	}
}
