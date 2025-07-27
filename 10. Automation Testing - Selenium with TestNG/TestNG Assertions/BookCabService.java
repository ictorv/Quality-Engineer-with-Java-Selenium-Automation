import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookCabService {

	List<Booking> BookingList = new ArrayList<>();

	public List<Booking> getBookingList() {
		return BookingList;
	}

	public void setBookingList(List<Booking> BookingList) {
		this.BookingList = BookingList;
	}

	public Booking viewBookingById(String bookingId) {
		Booking book=null;
		if(BookingList.size()>0) {
			for(Booking cab : BookingList){
				if(cab.getBookingId().equalsIgnoreCase(bookingId))
					book= cab;
			}
			
		}
		return book;
		
	}

	public List<Booking> viewBookingByCabType(String cabType) {

		List<Booking> result = new ArrayList<>();
		if(BookingList.size()>0) {
			for(Booking cab : BookingList){
				if(cab.getCabType().equalsIgnoreCase(cabType))
					result.add(cab);
			}
			
		}
		return result;
	}

}
