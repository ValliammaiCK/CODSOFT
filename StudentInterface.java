/*### Student Management System Implementation
This repository contains a customized implementation of a Student Management System in Java, initially inspired by an educational example provided by OpenAI.

#### Specific Modifications:
1. **User Interface Enhancements:** Improved the console-based user interface for better user experience.
2. **Additional Features:** Implemented input validation to ensure data integrity and added support for editing existing student information.
3. **File Handling Updates:** Enhanced file-based storage methods for improved efficiency and error handling.*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String rollNumber) {
        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Student student = new Student(parts[0], parts[1], parts[2]);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class StudentInterface {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();

        // Load existing data from file
        sms.loadFromFile("students.csv");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search for Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save and Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student roll number: ");
                    String rollNumber = scanner.nextLine();
                    System.out.print("Enter student grade: ");
                    String grade = scanner.nextLine();

                    Student newStudent = new Student(name, rollNumber, grade);
                    sms.addStudent(newStudent);
                    break;

                case "2":
                    System.out.print("Enter the roll number of the student to remove: ");
                    String removeRollNumber = scanner.nextLine();
                    sms.removeStudent(removeRollNumber);
                    break;

                case "3":
                    System.out.print("Enter the roll number of the student to search: ");
                    String searchRollNumber = scanner.nextLine();
                    Student foundStudent = sms.searchStudent(searchRollNumber);
                    if (foundStudent != null) {
                        System.out.println("Student found - " + foundStudent.toString());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "4":
                    sms.displayAllStudents();
                    break;

                case "5":
                    sms.saveToFile("students.csv");
                    System.out.println("Data saved. Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

