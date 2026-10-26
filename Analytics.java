import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Analytics {

    private AttendanceManager manager;

    // Constructor
    public Analytics(AttendanceManager manager) {
        this.manager = manager;
    }

    // -----------------------------
    // Show Defaulters (<75%)
    // -----------------------------
    public void showDefaulters() {

        boolean found = false;

        System.out.println("\n===== Defaulter List =====");

        for (Student student : manager.getStudents().values()) {

            if (student.getAttendancePercentage() < 75) {

                System.out.println(student);
                System.out.println("------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("No Defaulters Found.");
        }
    }

    // -----------------------------
    // Show Top Students
    // -----------------------------
    public void showTopStudents() {

        PriorityQueue<Student> pq =
                new PriorityQueue<>(
                        (a, b) -> Double.compare(
                                b.getAttendancePercentage(),
                                a.getAttendancePercentage()
                        ));

        pq.addAll(manager.getStudents().values());

        System.out.println("\n===== Top Attendance Students =====");

        int rank = 1;

        while (!pq.isEmpty()) {

            Student student = pq.poll();

            System.out.println(rank + ". " +
                    student.getName() +
                    " - " +
                    String.format("%.2f",
                            student.getAttendancePercentage()) + "%");

            rank++;
        }
    }

    // -----------------------------
    // Sort Students
    // -----------------------------
    public void sortStudentsByAttendance() {

        ArrayList<Student> list =
                new ArrayList<>(manager.getStudents().values());

        Collections.sort(list,
                (a, b) -> Double.compare(
                        b.getAttendancePercentage(),
                        a.getAttendancePercentage()));

        System.out.println("\n===== Students Sorted by Attendance =====");

        for (Student student : list) {

            System.out.println(student);

            System.out.println("------------------------");
        }

    }

    // -----------------------------
    // Average Attendance
    // -----------------------------
    public void showAverageAttendance() {

        if (manager.getStudents().isEmpty()) {

            System.out.println("No Students Available.");

            return;
        }

        double sum = 0;

        for (Student student : manager.getStudents().values()) {

            sum += student.getAttendancePercentage();

        }

        double average =
                sum / manager.getStudents().size();

        System.out.printf("Average Attendance = %.2f%%\n",
                average);

    }

    // -----------------------------
    // Highest Attendance Student
    // -----------------------------
    public void highestAttendance() {

        Student top = null;

        for (Student student : manager.getStudents().values()) {

            if (top == null ||
                    student.getAttendancePercentage()
                            > top.getAttendancePercentage()) {

                top = student;
            }
        }

        if (top != null) {

            System.out.println("\n===== Highest Attendance =====");

            System.out.println(top);

        }

    }

}