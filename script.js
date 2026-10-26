// ===============================
// Student Attendance Analytics
// Part 1
// ===============================

// Student Array
let students = JSON.parse(localStorage.getItem("students")) || [];

// Save Data
function saveData() {
    localStorage.setItem("students", JSON.stringify(students));
}

// Display Student Table
function displayStudents() {

    let table = document.getElementById("studentTable");

    table.innerHTML = "";

    students.forEach((student) => {

        let percentage = 0;

        if (student.total > 0) {
            percentage = ((student.present / student.total) * 100).toFixed(2);
        }

        table.innerHTML += `
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.branch}</td>
            <td>${student.present}</td>
            <td>${student.total}</td>
            <td>${percentage}%</td>
        </tr>
        `;

    });

}

// Add Student
function addStudent() {

    let id = document.getElementById("studentId").value;

    let name = document.getElementById("studentName").value;

    let branch = document.getElementById("studentBranch").value;

    // Validation
    if (id === "" || name === "" || branch === "") {

        alert("Please fill all fields.");

        return;

    }

    // Duplicate ID Check
    let exists = students.find(student => student.id == id);

    if (exists) {

        alert("Student ID already exists.");

        return;

    }

    // Create Student Object
    let student = {

        id: id,
        name: name,
        branch: branch,
        present: 0,
        total: 0

    };

    students.push(student);

    saveData();

    displayStudents();

    alert("Student Added Successfully!");

    // Clear Input Fields
    document.getElementById("studentId").value = "";
    document.getElementById("studentName").value = "";
    document.getElementById("studentBranch").value = "";

}

// Show Data on Page Load
displayStudents();

// ======================================
// Part 2
// Mark Attendance + Search Student
// ======================================

// Mark Attendance
function markAttendance() {

    let id = document.getElementById("attendanceId").value;
    let status = document.getElementById("attendanceStatus").value;

    let student = students.find(s => s.id == id);

    if (!student) {

        alert("Student Not Found!");

        return;
    }

    student.total++;

    if (status === "Present") {

        student.present++;

    }

    saveData();

    displayStudents();

    updateDashboard();

    alert("Attendance Marked Successfully!");

    document.getElementById("attendanceId").value = "";

}



// Search Student
function searchStudent() {

    let id = document.getElementById("searchId").value;

    let result = document.getElementById("searchResult");

    let student = students.find(s => s.id == id);

    if (!student) {

        result.innerHTML = `
        <h3 style="color:red;">Student Not Found</h3>
        `;

        return;
    }

    let percentage = 0;

    if (student.total > 0) {

        percentage = ((student.present / student.total) * 100).toFixed(2);

    }

    result.innerHTML = `

    <h3>Student Details</h3>

    <p><b>ID :</b> ${student.id}</p>

    <p><b>Name :</b> ${student.name}</p>

    <p><b>Branch :</b> ${student.branch}</p>

    <p><b>Present :</b> ${student.present}</p>

    <p><b>Total :</b> ${student.total}</p>

    <p><b>Attendance :</b> ${percentage}%</p>

    `;

}

// =====================================
// Part 3
// Dashboard Analytics
// =====================================

// Dashboard Update

function updateDashboard() {

    // Total Students
    document.getElementById("totalStudents").innerHTML = students.length;

    if (students.length == 0) {

        document.getElementById("averageAttendance").innerHTML = "0%";
        document.getElementById("topStudent").innerHTML = "-";
        document.getElementById("defaulters").innerHTML = "0";

        return;
    }

    let totalPercentage = 0;

    let topStudent = null;

    let highest = -1;

    let defaulterCount = 0;

    students.forEach(student => {

        let percentage = 0;

        if (student.total > 0) {

            percentage = (student.present / student.total) * 100;

        }

        totalPercentage += percentage;

        if (percentage > highest) {

            highest = percentage;

            topStudent = student.name;

        }

        if (percentage < 75) {

            defaulterCount++;

        }

    });

    let average = (totalPercentage / students.length).toFixed(2);

    document.getElementById("averageAttendance").innerHTML =
        average + "%";

    document.getElementById("topStudent").innerHTML =
        topStudent;

    document.getElementById("defaulters").innerHTML =
        defaulterCount;

}


// Page Load pe Dashboard Show karo

updateDashboard();



// =====================================
// Part 4
// Top Students + Defaulters + Sorting
// =====================================


// Show Top Students
function showTopStudents() {

    if (students.length == 0) {

        alert("No Students Available!");

        return;
    }

    let sorted = [...students];

    sorted.sort((a, b) => {

        let perA = a.total == 0 ? 0 : (a.present / a.total) * 100;

        let perB = b.total == 0 ? 0 : (b.present / b.total) * 100;

        return perB - perA;

    });

    let message = "🏆 TOP STUDENTS\n\n";

    sorted.forEach((student, index) => {

        let percentage = student.total == 0
                ? 0
                : ((student.present / student.total) * 100).toFixed(2);

        message +=
                (index + 1) + ". "
                        + student.name
                        + " (" + percentage + "%)\n";

    });

    alert(message);

}



// Show Defaulters
function showDefaulters() {

    let message = "⚠️ DEFAULTER STUDENTS\n\n";

    let found = false;

    students.forEach(student => {

        let percentage = student.total == 0
                ? 0
                : ((student.present / student.total) * 100);

        if (percentage < 75) {

            message += student.name
                    + " (" + percentage.toFixed(2) + "%)\n";

            found = true;

        }

    });

    if (!found) {

        alert("🎉 No Defaulters!");

    } else {

        alert(message);

    }

}



// Sort Student Table
function sortStudents() {

    students.sort((a, b) => {

        let perA = a.total == 0
                ? 0
                : (a.present / a.total) * 100;

        let perB = b.total == 0
                ? 0
                : (b.present / b.total) * 100;

        return perB - perA;

    });

    displayStudents();

    saveData();

    alert("Students Sorted Successfully!");

}


// =====================================
// Part 5
// Delete + Export + Auto Refresh
// =====================================


// Delete Student
function deleteStudent(id) {

    let confirmDelete = confirm("Do you want to delete this student?");

    if (!confirmDelete)
        return;

    students = students.filter(student => student.id != id);

    saveData();

    displayStudents();

    updateDashboard();

}



// Export Report
function exportReport() {

    if (students.length == 0) {

        alert("No Student Data Available!");

        return;

    }

    let report = "STUDENT ATTENDANCE REPORT\n\n";

    students.forEach(student => {

        let percentage = 0;

        if (student.total > 0) {

            percentage = ((student.present / student.total) * 100).toFixed(2);

        }

        report +=
            "ID : " + student.id + "\n" +
            "Name : " + student.name + "\n" +
            "Branch : " + student.branch + "\n" +
            "Present : " + student.present + "\n" +
            "Total : " + student.total + "\n" +
            "Attendance : " + percentage + "%\n";

        report += "-------------------------\n";

    });

    let blob = new Blob([report], { type: "text/plain" });

    let link = document.createElement("a");

    link.href = URL.createObjectURL(blob);

    link.download = "AttendanceReport.txt";

    link.click();

}



// Update Table with Delete Button

function displayStudents() {

    let table = document.getElementById("studentTable");

    table.innerHTML = "";

    students.forEach(student => {

        let percentage = 0;

        if (student.total > 0) {

            percentage =
                ((student.present / student.total) * 100).toFixed(2);

        }

        table.innerHTML +=

            `<tr>

            <td>${student.id}</td>

            <td>${student.name}</td>

            <td>${student.branch}</td>

            <td>${student.present}</td>

            <td>${student.total}</td>

            <td>${percentage}%</td>

            <td>

                <button onclick="deleteStudent(${student.id})">

                    Delete

                </button>

            </td>

            </tr>`;

    });

}



// Initial Load

displayStudents();

updateDashboard();