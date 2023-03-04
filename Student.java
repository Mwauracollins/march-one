import java.io.*;
import java.util.*;

public class Student {
    private String regNo;
    private String name;
    private String program;
    private String dateOfBirth;

    public Student(String regNo, String name, String program, String dateOfBirth) {
        this.regNo = regNo;
        this.name = name;
        this.program = program;
        this.dateOfBirth = dateOfBirth;
    }

    public String toString() {
        return regNo + ", " + name + ", " + program + ", " + dateOfBirth;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter student's registration number:");
        String regNo = input.nextLine();

        System.out.println("Enter student's name:");
        String name = input.nextLine();

        System.out.println("Enter student's program:");
        String program = input.nextLine();

        System.out.println("Enter student's date of birth (dd.mm.yyyy):");
        String dateOfBirth = input.nextLine();

        Student student = new Student(regNo, name, program, dateOfBirth);
        writeToFile("student.txt", student);

        System.out.println("Student details saved to file.");

        System.out.println("Enter program to search for:");
        String searchProgram = input.nextLine();
        List<Student> students = readFromFile("student.txt");
        List<Student> results = searchByProgram(students, searchProgram);

        if (results.size() == 0) {
            System.out.println("No students found for program " + searchProgram);
        } else {
            System.out.println("Students in program " + searchProgram + ":");
            for (Student s : results) {
                System.out.println(s.toString());
            }
        }
    }

    public static void writeToFile(String fileName, Student student) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(student.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

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

    public static List<Student> searchByProgram(List<Student> students, String program) {
        List<Student> results = new ArrayList<Student>();
        for (Student s : students) {
            if (s.program.equalsIgnoreCase(program)) {
                results.add(s);
            }
        }
        return results;
    }
}
