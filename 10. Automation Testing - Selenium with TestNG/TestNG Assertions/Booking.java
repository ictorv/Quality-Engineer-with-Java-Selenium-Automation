
public class Booking {
	
	private String bookingId;
	private String cabType;
	private String pickupPlace;
	private String dropPlace;
	private int distanceTravelled;
	

	public Booking(){}

	public Booking(String bookingId, String cabType, String pickupPlace, String dropPlace, int distanceTravelled) {
		super();
		this.bookingId = bookingId;
		this.cabType = cabType;
		this.pickupPlace = pickupPlace;
		this.dropPlace = dropPlace;
		this.distanceTravelled = distanceTravelled;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getCabType() {
		return cabType;
	}

	public void setCabType(String cabType) {
		this.cabType = cabType;
	}

	public String getPickupPlace() {
		return pickupPlace;
	}

	public void setPickupPlace(String pickupPlace) {
		this.pickupPlace = pickupPlace;
	}

	public String getDropPlace() {
		return dropPlace;
	}

	public void setDropPlace(String dropPlace) {
		this.dropPlace = dropPlace;
	}

	public int getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(int distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

	public double calculateAmountToBePaid() {
		double amount=0;
		double cost=0;
		if(cabType.equalsIgnoreCase("single"))
			cost=45;
		else if(cabType.equalsIgnoreCase("crew"))
			cost=56;
		else if(cabType.equalsIgnoreCase("extended"))
			cost=78;
		else if(cabType.equalsIgnoreCase("regular"))
			cost=85;
		else if(cabType.equalsIgnoreCase("quad"))
			cost=92;
		else 
			cost=-1;
		if(cost==-1 || distanceTravelled<=0)
			amount=-1;
		else
		amount=distanceTravelled*cost;


		return amount;
	}
	
	
	
}
