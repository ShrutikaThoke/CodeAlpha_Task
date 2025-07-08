import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class Studentgradetracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = sc.nextLine();

            System.out.print("Enter marks of " + name + ": ");
            int marks = sc.nextInt();
            sc.nextLine(); // consume newline

            students.add(new Student(name, marks));
        }

        // Summary Report
        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        String topStudent = "", bottomStudent = "";

        for (Student s : students) {
            total += s.marks;

            if (s.marks > highest) {
                highest = s.marks;
                topStudent = s.name;
            }
            if (s.marks < lowest) {
                lowest = s.marks;
                bottomStudent = s.name;
            }
        }

        double average = (double) total / n;

        System.out.println("\n====== Student Summary Report ======");
        for (Student s : students) {
            System.out.println("Name: " + s.name + ", Marks: " + s.marks);
        }

        System.out.println("\nAverage Marks: " + average);
        System.out.println("Highest Marks: " + highest + " (by " + topStudent + ")");
        System.out.println("Lowest Marks: " + lowest + " (by " + bottomStudent + ")");

        sc.close();
    }
}