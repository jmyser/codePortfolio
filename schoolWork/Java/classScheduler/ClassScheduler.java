import java.sql.Connection;                                                                       
import java.sql.DriverManager;                                                                    
import java.sql.PreparedStatement;                                                                    
import java.sql.ResultSet;                                                                            
import java.sql.SQLException;
import java.sql.ResultSetMetaData;                                                                            
import java.sql.Statement;                                                                                  
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;                                                                                             

public class ClassScheduler {                                                                                         
    private Connection connect = null;                                                                                 
    private Statement statement = null;                                                                                    
    private PreparedStatement preparedStatement = null; 
    private ResultSet resultSet = null; 
 
    public void readDataBase() throws Exception { 
      try { 
          // This will load the MySQL driver, each DB has its own driver 
          Class.forName("com.mysql.jdbc.Driver"); 
          // Setup the connection with the DB 
      	  connect = DriverManager.getConnection("jdbc:mysql://localhost/jermyser?" + "user=jermyser&password=1054417"); 
 			
 			// main menu: 
 			// The program should first ask for the student's ID and 
      // verify that the student has a valid entry. If the ID is 
      // not currently in the system, it should ask the questions 
      // to add the student. The program should show the student 
      // name for the student being scheduled if the student was found. 

      Scanner input = new Scanner(System.in);
      
      System.out.println("||=====================================================================||");
      System.out.println("||                                                                     ||");
      System.out.println("||  Welcome to the super-awesome console based registration program!   ||");
      System.out.println("||                                                                     ||");
      System.out.println("||=====================================================================||");
      System.out.println();

			System.out.println("Please Enter Student ID:");
      String studentID = input.nextLine();



      statement = connect.createStatement(); 
      resultSet = statement.executeQuery("SELECT name, dept_name FROM jermyser.student WHERE ID = '" + studentID + "'");
      if (resultSet.next()) {
          String name = resultSet.getString("name"); 
          String dept_name = resultSet.getString("dept_name"); 
          System.out.println("Welcome " + name + " from department " + dept_name);
      }
      else {
        System.out.println("That student ID doesn't exist in the database, please enter student information: ");
        System.out.print("Enter Name: ");
        String inputName = input.nextLine();
        System.out.print("Enter Email: ");
        String inputDept = input.nextLine();
        System.out.print("Enter Total Credits: ");
        int inputTotCred = input.nextInt();
        // PreparedStatements can use variables and are more efficient 
        preparedStatement = connect.prepareStatement("insert into  jermyser.student values (?, ?, ?, ?)"); 
        // Parameters start with 0
        preparedStatement.setString(1, studentID); 
        preparedStatement.setString(2, inputName); 
        preparedStatement.setString(3, inputDept); 
        preparedStatement.setInt(4, inputTotCred); 
        preparedStatement.executeUpdate(); 
        preparedStatement = connect.prepareStatement("SELECT ID, name, dept_name, tot_cred, from jermyser.student"); 
        resultSet = preparedStatement.executeQuery(); 
        System.out.println("Student Added:");
        writeResultSet(resultSet);
      }

      // The program should then ask for the semester and year the student wants to enroll for.
      System.out.println("Please enter year you want to enroll: ");
      int inputEnrollYear = input.nextInt();
      input.nextLine();
      System.out.println("And the semester: ");
      String inputEnrollSem = input.nextLine();  
      System.out.println("");
      // The program should show the list of classes that student is signed up for for that term.  
      preparedStatement = connect.prepareStatement("SELECT jermyser.course.title FROM jermyser.course WHERE jermyser.course.course_id = ( SELECT course_id FROM jermyser.takes WHERE jermyser.takes.ID = ? AND  jermyser.takes.semester = ? AND  jermyser.takes.year = ?)"); 
      preparedStatement.setString(1, studentID); 
      preparedStatement.setString(2, inputEnrollSem); 
      preparedStatement.setInt(3, inputEnrollYear); 
      resultSet = preparedStatement.executeQuery(); 
      System.out.println("Student Already Enrolled In:");
      writeResultSet(resultSet);

      // The program should then present a list of departments with courses that term, and ask the
      // student to pick the department.
      System.out.println("");
      System.out.println("Departments with courses available:");
      preparedStatement = connect.prepareStatement("SELECT DISTINCT dept_name FROM jermyser.course WHERE jermyser.course.course_id IN (SELECT DISTINCT course_id FROM jermyser.section WHERE semester = ? AND year = ?)"); 
      preparedStatement.setString(1, inputEnrollSem); 
      preparedStatement.setInt(2, inputEnrollYear); 
      resultSet = preparedStatement.executeQuery(); 
      writeResultSet(resultSet);
      System.out.println("");
      System.out.print("Select the department you wish to enroll for: ");
      String inputDept = input.nextLine();  

      // The program should then display a list of courses offered that term in that department , 
      // with an indicator of those that are completed (with grade), those with the prerequisites met, 
      // and those that cannot yet be taken (prerequisites met no met).
      System.out.println("");
      System.out.println("Courses available for " + inputDept + " in term " + inputEnrollSem + ": ");
      preparedStatement = connect.prepareStatement("SELECT course.course_id, course.title AS 'Courses Available' FROM jermyser.section NATURAL JOIN jermyser.course WHERE jermyser.course.dept_name = ? AND jermyser.section.semester = ? AND jermyser.section.year = ?"); 
      preparedStatement.setString(1, inputDept); 
      preparedStatement.setString(2, inputEnrollSem); 
      preparedStatement.setInt(3, inputEnrollYear); 
      resultSet = preparedStatement.executeQuery(); 
      writeResultSet(resultSet);

      System.out.println("");
      System.out.println("Courses completed: ");
      preparedStatement = connect.prepareStatement("SELECT course.course_id, course.title, takes.grade AS 'Completed' FROM jermyser.section NATURAL JOIN jermyser.takes NATURAL JOIN jermyser.course WHERE jermyser.course.dept_name = ? AND jermyser.takes.ID = ?"); 
      preparedStatement.setString(1, inputDept); 
      preparedStatement.setString(2, studentID); 
      resultSet = preparedStatement.executeQuery(); 
      writeResultSet(resultSet);

      System.out.println("");
      System.out.println("Prerequsites completed: ");
      preparedStatement = connect.prepareStatement("SELECT course_id FROM prereq WHERE prereq_id IN (SELECT course_id FROM takes WHERE grade IS NOT NULL AND ID = ?)"); 
      preparedStatement.setString(1, studentID); 
      resultSet = preparedStatement.executeQuery(); 
      writeResultSet(resultSet);

      System.out.println("");
      System.out.println("Prerequsites NOT completed: ");
      preparedStatement = connect.prepareStatement("SELECT course_id FROM prereq WHERE prereq_id IN (SELECT course_id FROM takes WHERE grade IS NULL AND ID = ?)"); 
      preparedStatement.setString(1, studentID); 
      resultSet = preparedStatement.executeQuery(); 
      writeResultSet(resultSet);
       
    
      // The system will allow the user to select one of the classes that are appropriate 
      // (not taken yet, taken with a failing grade, or prerequisite met.)
      
      // Verify that the student does not sign up for more than 18 credit hours.  
      // If the selected course exceeds 18 for the semester. reject the add, and return to c.
      
      System.out.println("");
      System.out.println("Please enter the Course ID you wish to enroll for (ex. BIO-101): ");
      String inputCourseToEnroll = input.nextLine();

      resultSet = statement.executeQuery("SELECT jermyser.course.credits FROM jermyser.course WHERE jermyser.course.course_id = '" + inputCourseToEnroll + "'");
      double courseToEnrollCredits = resultSet.getDouble(1);
      resultSet = statement.executeQuery("SELECT jermyser.course.sec_id FROM jermyser.section WHERE jermyser.course.course_id = '" + inputCourseToEnroll + "'");
      String courseToEnrollSecID = resultSet.getString(1);
      resultSet = statement.executeQuery("SELECT SUM(jermyser.course.credits) FROM jermyser.takes NATURAL JOIN jermyser.course WHERE jermyser.takes.ID = '" + studentID + "'");
      double studentCreditTotal = resultSet.getDouble(1);
      
      if ((courseToEnrollCredits + studentCreditTotal) < 18) {
        //enroll in course
        preparedStatement = connect.prepareStatement("INSERT into  jermyser.takes VALUES (" + studentID + ", " + inputCourseToEnroll + ", " + courseToEnrollSecID + ", " + inputEnrollSem + ", " + inputEnrollYear + ", null)");
        preparedStatement.executeUpdate();
        System.out.println("Registered for " + inputCourseToEnroll);
      }
      else {
        System.out.println("I'm sorry, you are enrolled in too many credits to sign up for that course.");
      }



      // After adding a class, the program will return to c above. 
      // (There show be an option available on every screen to return to the main menu).  
      // The student will had an option of quitting here.

      






      //writeResultSet(resultSet); 
 
      // Remove again the insert comment 
      //preparedStatement = connect.prepareStatement("delete from jermyser.comments where myuser= ? ; "); 
      //preparedStatement.setString(1, "Test"); 
      //preparedStatement.executeUpdate(); 
       
      //resultSet = statement.executeQuery("select * from jermyser.comments"); 
      //writeMetaData(resultSet); 
       
        } catch (Exception e) { 
            throw e; 
        } finally { 
            close(); 
        } 
 
    } 
 
    private void writeMetaData(ResultSet resultSet) throws SQLException { 
        //   Now get some metadata from the database 
        // Result set get the result of the SQL query 
     
        System.out.println("The columns in the table are: "); 
     
        System.out.println("Table: " + resultSet.getMetaData().getTableName(1)); 
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){ 
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i)); 
        } 
    } 
 
    private void writeResultSet(ResultSet resultSet) throws SQLException { 
          ResultSetMetaData rsmd = resultSet.getMetaData();
          int colNum = rsmd.getColumnCount();
          while (resultSet.next()) {
              for (int i = 1; i <= colNum; i++) {
                  if (i > 1) System.out.print(" | ");
                  System.out.print(resultSet.getString(i));
              }
              System.out.println(""); 
        } 
    } 
 
    // You need to close the resultSet 
    private void close() { 
        try { 
            if (resultSet != null) { 
                resultSet.close(); 
            } 
 
            if (statement != null) { 
                statement.close(); 
            } 
 
            if (connect != null) { 
                connect.close(); 
            } 
        } catch (Exception e) { 
 
        } 
    } 
 
} 
