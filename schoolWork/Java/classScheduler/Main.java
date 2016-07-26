public class Main {
    public static void main(String[] args) throws Exception {
        //MySQLAccess dao = new MySQLAccess();
        ClassScheduler dao = new ClassScheduler();
        dao.readDataBase();
    }
}