
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class UserInterface {

    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter in=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        DateTimeFormatter out=DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        
        System.out.println("Enter check in date and time");
        String checkInp=sc.nextLine();
        
        try{
            LocalDateTime checkIn= LocalDateTime.parse(checkInp,in);
            System.out.println("Enter number of hours of stay");
            
            int stay=sc.nextInt();
            LocalDateTime checkOut=checkIn.plusHours(stay);
            
            System.out.println("Check in Date and Time is "+checkIn.format(out));
            System.out.println("Check out Date and Time is "+checkOut.format(out));
        }catch(DateTimeParseException e){
            System.out.println(checkInp+" is an invalid check in date or time");
        }
        // FILL THE CODE HERE
    }
}
