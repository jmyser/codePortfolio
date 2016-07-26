package lab2;
import java.util.Arrays;

/*
 * @author       jeremy myser
 * @institution  Mount Vernon Nazarene University - GPS Computer Science
 */

public class StudentSort {

    // @param args the command line arguments

    public static void main(String[] args) {
        // TODO code application logic here
        Student [] students = {
            new Student("Matthew", 4352),
            new Student("Mark", 3737),
            new Student("Luke", 6543),
            new Undergraduate("John", 1372, 3),
            new Undergraduate("Paul", 2918, 2)
        };
        
        for (Student.Ordering printloop : Student.Ordering.values()){
            
            Student.orderMethod = Student.Ordering.valueOf(printloop.name());
            
            java.util.Arrays.sort(students);
        
            System.out.println("Students sorted " +printloop.toString() + ":");
            for (Student student: students) {
                System.out.print(student + " "); 
                System.out.println();
            }
            System.out.println();
        }

    }
}
