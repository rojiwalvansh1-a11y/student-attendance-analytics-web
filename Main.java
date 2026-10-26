import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AttendanceManager manager = new AttendanceManager();
        Analytics analytics = new Analytics(manager);

        while (true) {

            System.out.println("\n====================================");
            System.out.println(" STUDENT ATTENDANCE ANALYTICS SYSTEM ");
            System.out.println("====================================");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Mark Attendance");
            System.out.println("5. Display All Students");
            System.out.println("6. Show Defaulter List");
            System.out.println("7. Show Top Students");
            System.out.println("8. Sort Students By Attendance");
            System.out.println("9. Show Average Attendance");
            System.out.println("10. Show Highest Attendance");
            System.out.println("11. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Branch: ");
                    String branch = sc.nextLine();

                    manager.addStudent(id, name, branch);

                    break;

                case 2:

                    System.out.print("Enter Student ID: ");
                    int searchId = sc.nextInt();

                    Student student = manager.searchStudent(searchId);

                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Student Not Found.");
                    }

                    break;

                case 3:

                    System.out.print("Enter Student ID: ");
                    int deleteId = sc.nextInt();

                    manager.deleteStudent(deleteId);

                    break;

                case 4:

                    System.out.print("Enter Student ID: ");
                    int attendanceId = sc.nextInt();

                    System.out.print("Present? (true/false): ");
                    boolean present = sc.nextBoolean();

                    manager.markAttendance(attendanceId, present);

                    break;

                case 5:

                    manager.displayAllStudents();

                    break;

                case 6:

                    analytics.showDefaulters();

                    break;

                case 7:

                    analytics.showTopStudents();

                    break;

                case 8:

                    analytics.sortStudentsByAttendance();

                    break;

                case 9:

                    analytics.showAverageAttendance();

                    break;

                case 10:

                    analytics.highestAttendance();

                    break;

                case 11:

                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice.");

            }

        }

    }

}