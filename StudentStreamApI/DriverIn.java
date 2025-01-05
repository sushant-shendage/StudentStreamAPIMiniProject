// package StudentStreamAPIMiniProject.StudentStreamApI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Student {

    String name;
    int age;
    int rollNumber;
    long phoneNumber;
    double percentage;

    public Student(String name, int age, int rollNumber, long phoneNumber, double percentage) {
        this.name = name;
        this.age = age;
        this.rollNumber = rollNumber;
        this.phoneNumber = phoneNumber;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", rollNumber=" + rollNumber + ", phoneNumber=" + phoneNumber
                + ", percentage=" + percentage + "]";
    }

}

interface StudentInterface {
    /**
     * Adds a new student to the system.
     * This function gathers the necessary details and stores the student
     * information in the database or list.
     */
    void addStudents();

    /**
     * Displays all students currently in the system.
     * Outputs the details of each student, typically including name, roll number,
     * age, and marks.
     */
    void displayStudents();

    /**
     * Removes a student from the system based on their roll number.
     * Searches for the student with the specified roll number and deletes their
     * record.
     */
    void removeStudentByRollNumber();

    /**
     * Sorts the list of students based on the specified attribute.
     * 
     * @param attributeName The name of the attribute to sort by (e.g., name, age,
     *                      or marks).
     */
    void sortStudents(String attributeName);

    /**
     * Returns the total count of students in the system.
     * Calculates and outputs the number of student records present.
     */
    void totalStudentCount();

    /**
     * Updates the name of an existing student.
     * Typically requires identifying the student by roll number before updating
     * their name.
     */
    void updateStudentName();

    /**
     * Retrieves the maximum age of students in the system.
     * Scans the student records to find and return the highest age.
     */
    void getStudentMaxAge();

    /**
     * Retrieves the highest marks obtained by any student.
     * Scans the student records to find and return the highest marks.
     */
    void getStudentMaxMarks();

}

/**
 * Implementation of the StudentInterface, providing functionality for managing
 * student information.
 */
class StudentInterfaceImp1 implements StudentInterface {

    // List to store information about students.
    List<Student> studentInfo = null;

    // Temporary variable to hold a single student record for operations like update
    // and remove.
    Student singleStudent = null;

    // Static Scanner instance for reading user input.
    static Scanner sc = new Scanner(System.in);

    /**
     * Adds multiple students to the system.
     * Prompts the user to enter the number of students and their details, then
     * stores them in a list.
     */
    @Override
    public void addStudents() {
        studentInfo = new ArrayList<Student>();
        System.out.println("Enter number of students you want to add:");
        int numberofStudent = sc.nextInt();

        for (int i = 1; i <= numberofStudent; i++) {
            // Reading student details and adding to the list.
            studentInfo.add(new Student(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextLong(), sc.nextDouble()));
        }
    }

    /**
     * Displays all student records in the system.
     * Uses a lambda expression to print each student's details.
     */
    @Override
    public void displayStudents() {
        studentInfo.forEach(System.out::println);
    }

    /**
     * Removes a student from the system by roll number.
     * Prompts the user for the roll number, searches for the student, and removes
     * them if found.
     */
    @Override
    public void removeStudentByRollNumber() {
        System.out.println("Enter student roll number to remove student:");
        int removeByRollNumber = sc.nextInt();

        // Filtering the list to find the student with the specified roll number.
        if (studentInfo.stream().filter(n -> n.rollNumber == removeByRollNumber).collect(Collectors.toList())
                .size() != 0) {
            singleStudent = studentInfo.stream().filter(n -> n.rollNumber == removeByRollNumber)
                    .collect(Collectors.toList()).get(0);
        }

        if (singleStudent == null) {
            System.out.println("Student does not exist!");
        } else {
            System.out.println("Student exists!");
            System.out.println(singleStudent);
            studentInfo.remove(singleStudent);
            System.out.println("Student removed successfully!");
        }
    }

    /**
     * Sorts the students by a specified attribute (name or age).
     * Prompts the user for the attribute and sorts the list accordingly.
     * 
     * @param attributeName The attribute to sort by, either "name" or "age".
     */
    @Override
    public void sortStudents(String attributeName) {
        System.out.println("Enter attribute to sort students by [name/age]:");
        System.out.println(attributeName);

        if (attributeName.equals("name")) {
            studentInfo.sort((o1, o2) -> o1.name.compareTo(o2.name));
            System.out.println("Students sorted by name:");
            displayStudents();
        } else if (attributeName.equals("age")) {
            studentInfo.sort((o1, o2) -> o1.age - o2.age);
            System.out.println("Students sorted by age:");
            displayStudents();
        }
    }

    /**
     * Displays the total number of students currently in the system.
     */
    @Override
    public void totalStudentCount() {
        System.out.println("Total students: " + studentInfo.size());
    }

    /**
     * Finds and displays the student with the maximum age.
     * Uses a stream to find the student with the highest age.
     */
    @Override
    public void getStudentMaxAge() {
        System.out.println("Max age: " + (studentInfo.stream().max((o1, o2) -> o1.age - o2.age).get().age));
    }

    /**
     * Finds and displays the student with the highest marks.
     * Uses a stream to find the student with the highest percentage.
     */
    @Override
    public void getStudentMaxMarks() {
        System.out.println("Max marks: " + studentInfo.stream()
                .max((o1, o2) -> (int) o1.percentage - (int) o2.percentage)
                .get().percentage);
    }

    /**
     * Updates the name of a student by their roll number.
     * Prompts the user for the roll number and the new name, then updates the
     * student's record.
     */
    @Override
    public void updateStudentName() {
        System.out.println("Enter student roll number to update student:");
        int removeByRollNumber = sc.nextInt();

        if (studentInfo.stream().filter(n -> n.rollNumber == removeByRollNumber).collect(Collectors.toList())
                .size() != 0) {
            singleStudent = studentInfo.stream().filter(n -> n.rollNumber == removeByRollNumber)
                    .collect(Collectors.toList()).get(0);
        }

        if (singleStudent == null) {
            System.out.println("Student does not exist!");
        } else {
            System.out.println("Student exists!");
            System.out.println(singleStudent);

            System.out.println("Enter new name for the student:");
            String newName = sc.next();
            singleStudent.name = newName;
            System.out.println("Updated student list:");
            studentInfo.forEach(System.out::println);
            System.out.println("Student updated successfully!");
        }
    }
}

/**
 * DriverIn class serves as the entry point for interacting with the student
 * management system.
 * It provides a menu-driven interface for performing various operations such as
 * adding, displaying,
 * removing students, and more.
 */
public class DriverIn {

    public static void main(String[] args) {
        // Create an instance of StudentInterfaceImp1 to interact with the student
        // system
        StudentInterfaceImp1 ob1 = new StudentInterfaceImp1();

        int inputCode = 0;

        // Loop to continuously show the menu until the user chooses to exit
        do {
            // Display the menu options
            menue();
            System.out.println("Enter the code to perform operation:");

            // Read user input for selecting an operation
            inputCode = StudentInterfaceImp1.sc.nextInt();

            // Switch case to handle the user's choice based on input code
            switch (inputCode) {
                case 1: {
                    // Add students to the system
                    ob1.addStudents();
                    break;
                }
                case 2: {
                    // Display the total number of students
                    ob1.totalStudentCount();
                    break;
                }
                case 3: {
                    // Display all student details
                    ob1.displayStudents();
                    break;
                }
                case 4: {
                    // Remove a student based on roll number
                    ob1.removeStudentByRollNumber();
                    break;
                }
                case 5: {
                    // Sort students by name
                    ob1.sortStudents("name");
                    break;
                }
                case 6: {
                    // Sort students by age
                    ob1.sortStudents("age");
                    break;
                }
                case 7: {
                    // Display the maximum age among students
                    ob1.getStudentMaxAge();
                    break;
                }
                case 8: {
                    // Display the maximum marks obtained by a student
                    ob1.getStudentMaxMarks();
                    break;
                }
                case 9: {
                    // Update the name of a student
                    ob1.updateStudentName();
                    break;
                }
                case 10: {
                    // Exit the program
                    break;
                }

                default: {
                    // Handle invalid input code
                    System.out.println("Please enter a valid code!");
                    break;
                }
            }

        } while (inputCode != 10); // Exit loop when input code is 10

        // Notify the user that the program execution is completed
        System.out.println("Program execution completed!");
    }

    /**
     * Displays the menu options to the user.
     * This method shows the available actions that the user can choose from.
     */
    public static void menue() {
        System.out.println("-------------------Menu-------------------");
        System.out.println("[ 1  ] : [ Add students                  ]");
        System.out.println("[ 2  ] : [ Count total students          ]");
        System.out.println("[ 3  ] : [ Display students              ]");
        System.out.println("[ 4  ] : [ Remove student by roll number ]");
        System.out.println("[ 5  ] : [ Sort students by name         ]");
        System.out.println("[ 6  ] : [ Sort students by age          ]");
        System.out.println("[ 7  ] : [ Get student max age           ]");
        System.out.println("[ 8  ] : [ Get student max marks         ]");
        System.out.println("[ 9  ] : [ Update student name           ]");
        System.out.println("[ 10 ] : [ Exit                          ]");
    }
}
