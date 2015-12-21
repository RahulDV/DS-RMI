import java.io.Serializable;
import java.util.Random;

public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Random random = new Random(20);
	
	private String bookId;

	private float cost;
	
	private String topic;

	private String title;
	
	private int itemsInStock;
	
	
	
	public int getItemsInStock() {
		return itemsInStock;
	}

	public void setItemsInStock(int itemsInStock) {
		this.itemsInStock = itemsInStock;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(BookTopic topic) {
		this.topic = topic.getValue();
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId() {
		if(null==this.bookId || this.bookId.isEmpty()){
			this.bookId =  "IBN"+random.nextInt();
		}
	}
	
	

}


