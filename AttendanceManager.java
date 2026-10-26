import java.util.HashMap;

public class AttendanceManager {

    // HashMap to store students
    private HashMap<Integer, Student> students;

    // Constructor
    public AttendanceManager() {
        students = new HashMap<>();
    }

    // -----------------------------
    // Add Student
    // -----------------------------
    public void addStudent(int id, String name, String branch) {

        // Check if student already exists
        if (students.containsKey(id)) {
            System.out.println("Student with this ID already exists.");
            return;
        }

        Student student = new Student(id, name, branch);

        students.put(id, student);

        System.out.println("Student added successfully.");
    }

    // -----------------------------
    // Search Student
    // -----------------------------
    public Student searchStudent(int id) {

        return students.get(id);

    }

    // -----------------------------
    // Delete Student
    // -----------------------------
    public void deleteStudent(int id) {

        if (students.containsKey(id)) {

            students.remove(id);

            System.out.println("Student deleted successfully.");

        } else {

            System.out.println("Student not found.");

        }

    }

    // -----------------------------
    // Mark Attendance
    // -----------------------------
    public void markAttendance(int id, boolean present) {

        Student student = students.get(id);

        if (student == null) {

            System.out.println("Student not found.");

            return;

        }

        if (present) {

            student.markPresent();

        } else {

            student.markAbsent();

        }

        System.out.println("Attendance updated successfully.");

    }

    // -----------------------------
    // Display One Student
    // -----------------------------
    public void displayStudent(int id) {

        Student student = students.get(id);

        if (student == null) {

            System.out.println("Student not found.");

        } else {

            System.out.println(student);

        }

    }

    // -----------------------------
    // Display All Students
    // -----------------------------
    public void displayAllStudents() {

        if (students.isEmpty()) {

            System.out.println("No students available.");

            return;

        }

        for (Student student : students.values()) {

            System.out.println(student);

            System.out.println("---------------------------");

        }

    }

    // -----------------------------
    // Getter for Analytics Module
    // -----------------------------
    public HashMap<Integer, Student> getStudents() {

        return students;

    }

}