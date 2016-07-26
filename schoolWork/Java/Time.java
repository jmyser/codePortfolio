package lab3;
import java.util.Scanner;

/*
 * @author       jeremy myser
 * @institution  Mount Vernon Nazarene University - GPS Computer Science
 */
public class Time {

    private int hour;
    private int minute;
    private boolean getTime12;
    
    public Time() {
        hour = 0;
        minute =0;
    }
    
    private boolean isValid(int hour, int minute){
        return 0 <= hour && hour <= 23 && 0 <= minute && minute <= 59;
    }
    
    public void setTime(int h, int m){
        if (isValid(h, m)){
            hour = h;
            minute = m;
        }
    }
    
    public String getTime24(){
        return String.format("%02d%02d", hour, minute);
    } 
    
    public String getTime12(){
        String suffix = "am";
        int tempHour;
        if (hour > 11) {
            suffix = "pm";
            tempHour = hour - 12;
        }
        else {
            tempHour = hour;
        }
        if (tempHour == 0)
            tempHour = 12;
        return String.format("%d:%02d %s", tempHour, minute, suffix);
    } 
    
    public boolean isBefore(Time another) {
        if (this.hour == another.hour) {
            if (this.minute < another.minute)
                return true;
            else
                return false;
        }
        else if (this.hour < another.hour){
            return true;
        }
        else {
            return false;
        }
    }
    
    public int difference(Time another) {
        int startTimeInMinutes = (this.hour * 60) + this.minute;
        int endTimeInMinutes  = (another.hour * 60) + another.minute;
        int diff = endTimeInMinutes - startTimeInMinutes;
        return diff;
    }

    public static void main(String[] args) {
        // New Scanner Object
        Scanner input = new Scanner(System.in);
        // Create Time objects
        Time startTime = new Time();
        Time endTime = new Time();
        
        // temp variables
        int inputHour;
        int inputMinute;
        String inputString;
        
        // Enter values for time objects
        System.out.print("Enter the starting time (HH:MM): ");
        inputString = input.next();
        inputHour = Integer.parseInt(inputString.substring(0,2));
        inputMinute = Integer.parseInt(inputString.substring(3,5));
        startTime.setTime(inputHour, inputMinute);
        
        System.out.print("Enter the ending time (HH:MM): ");
        inputString = input.next();
        inputHour = Integer.parseInt(inputString.substring(0,2));
        inputMinute = Integer.parseInt(inputString.substring(3,5));
        endTime.setTime(inputHour, inputMinute);
        
        // Compare and print results
        if (startTime.isBefore(endTime)) {
            System.out.println("The appointment will last " + startTime.difference(endTime) + " minutes.");
        }
        else {
            System.out.println("The ending time may not come before the start time.");
        }
        
        

        /*     Lab 3a output
        // Create new Time objects
        Time time1 = new Time();
        Time time2 = new Time();
        Time time3 = new Time();
        Time time4 = new Time();
        
        // Enter values
        time1.setTime(12, 19);
        time2.setTime(00, 19);
        time3.setTime(03, 18);
        time4.setTime(15, 19);
        
        // Output results
        System.out.println("Print results with good data:");
        System.out.println("time1 is " + time1.getTime24() + " or " + time1.getTime12() + ".");
        System.out.println("time2 is " + time2.getTime24() + " or " + time2.getTime12() + ".");
        System.out.println("time3 is " + time3.getTime24() + " or " + time3.getTime12() + ".");
        System.out.println("time4 is " + time4.getTime24() + " or " + time4.getTime12() + ".");
        
        // Enter invalid times to test the isValid method
        time1.setTime(12, -30);
        time2.setTime(2, 100);
        time3.setTime(-3, 30);
        time4.setTime(30, 20);
        
        // Output results
        System.out.println();
        System.out.println("Print results again to make sure bad data wasn't allowed:");
        System.out.println("time1 is " + time1.getTime24() + " or " + time1.getTime12() + ".");
        System.out.println("time2 is " + time2.getTime24() + " or " + time2.getTime12() + ".");
        System.out.println("time3 is " + time3.getTime24() + " or " + time3.getTime12() + ".");
        System.out.println("time4 is " + time4.getTime24() + " or " + time4.getTime12() + ".");
        */
    }

}
