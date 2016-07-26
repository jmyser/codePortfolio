package lab2;


public class Student implements Comparable<Student> {
    private String name;
    private int studentNumber;
    
    // Use the orderMethod variable to select the desired ordering to be used
    // by the compareTo method
    public static enum Ordering {BYNUMBER, BYNAME};
    public static Ordering orderMethod = Ordering.BYNAME;

    public Student( ) {
        name = "No name yet.";
        studentNumber = 0;  // indicates no number assigned
    }

    public Student(String initialName, int initialStudentNumber) {
        name = initialName;
        studentNumber = initialStudentNumber;
    }

    public void setName(String newName) {
        name = newName;
    }
    
    public void setStudentNumber(int newStudentNumber) {
        studentNumber = newStudentNumber;
    }

    public String getName() {
        return name;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public boolean equals(Object otherObject) {
        boolean result = false;
        if ((otherObject != null) && (otherObject instanceof Student))
        {
            Student otherStudent = (Student) otherObject;
            result = this.name.equalsIgnoreCase(otherStudent.name)
                     && (this.studentNumber == otherStudent.studentNumber);
        }
        return result;
    }

    public String toString( ) {
        return "Name: " + name +
	       "\nStudent number: "  + studentNumber;
    }
    
    @Override // Implement the compareTo method defined in Comparable interface
    public int compareTo(Student o) {
        int returnValue = -1;
        if (orderMethod == Ordering.BYNAME){
            returnValue = compareByName(o);
        }
        else if (orderMethod == Ordering.BYNUMBER){
            returnValue = compareByNumber(o);
        }
        else
            return returnValue;
        return returnValue;
    }
    
    public int compareByName(Student o) {
        if ((o != null) && (o instanceof Student)){
            return this.name.compareTo(o.getName());
        }
        else
            return -1;
    }
    
    public int compareByNumber(Student o) {
        if ((o != null) && (o instanceof Student)){
            if (getStudentNumber() > o.getStudentNumber())
                return 1;
            else if (getStudentNumber() < o.getStudentNumber())
                return -1;
            else
                return 0;
        }
        else
            return -1;
    }
}
