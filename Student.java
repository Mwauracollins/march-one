import java.io.*;
import java.util.*;

public class Student {
    private String regNo;
    private String name;
    private String program;
    private String dateOfBirth;

    //constructor for Student
    public Student(String regNo, String name, String program, String dateOfBirth) {
        this.regNo = regNo;
        this.name = name;
        this.program = program;
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getName() {
        return name;
    }

    public String getProgram() {
        return program;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String toString() {
        return regNo + ", " + name + ", " + program + ", " + dateOfBirth;
    }//overriding of the getObject() and toString() methods

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter student's registration no:");
        String regNo = input.nextLine();

        System.out.println("Enter student's name:");
        String name = input.nextLine();

        System.out.println("Enter student's program:");
        String program = input.nextLine();

        System.out.println("Enter student's date of birth (dd.mm.yyyy):");
        String dateOfBirth = input.nextLine();

        //creating an object Student with the inputs as the arguments and save to file
        Student student = new Student(regNo, name, program, dateOfBirth);
        writeToFile("student.txt", student);

        System.out.println("Student's details saved");

        //search for students taking a particular program
        System.out.print("Enter program to search: ");
        String searchProgram = input.nextLine();

        List<Student> students = readFromFile("student.txt");
        ArrayList<Student> matchingStudents = new ArrayList<Student>();
        for (Student s : students) {
            if (s.getProgram().equalsIgnoreCase(searchProgram)) {
                matchingStudents.add(s);
            }
        }

        System.out.println("Students taking " + searchProgram + ":");
        for (Student s : matchingStudents) {
            System.out.println(s);
        }
    }

    //this method takes the arguments and writes to file using the filewriter
    //catches an exception if error occurs
    public static void writeToFile(String fileName, Student student) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(student.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing.");
            e.printStackTrace();
        }
    }

    //the readFromFile() method takes file name as parameter and reads the details of student
    public static List<Student> readFromFile(String fileName) {
        List<Student> students = new ArrayList<Student>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String regNo = parts[0].trim();
                String name = parts[1].trim();
                String program = parts[2].trim();
                String dateOfBirth = parts[3].trim();
                Student student = new Student(regNo, name, program, dateOfBirth);
                students.add(student);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file.");
            e.printStackTrace();
        }
        return students;
    }
}
