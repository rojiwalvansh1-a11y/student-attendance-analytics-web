public class Student {

    private int id;
    private String name;
    private String branch;

    private int totalClasses;
    private int presentClasses;

    public Student(int id, String name, String branch) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.totalClasses = 0;
        this.presentClasses = 0;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public int getPresentClasses() {
        return presentClasses;
    }

    // Mark attendance
    public void markPresent() {
        presentClasses++;
        totalClasses++;
    }

    public void markAbsent() {
        totalClasses++;
    }

    // Attendance Percentage
    public double getAttendancePercentage() {

        if (totalClasses == 0)
            return 0;

        return (presentClasses * 100.0) / totalClasses;
    }

    @Override
    public String toString() {

        return "ID : " + id +
                "\nName : " + name +
                "\nBranch : " + branch +
                "\nPresent : " + presentClasses +
                "\nTotal Classes : " + totalClasses +
                "\nAttendance : " + String.format("%.2f", getAttendancePercentage()) + "%";
    }

}