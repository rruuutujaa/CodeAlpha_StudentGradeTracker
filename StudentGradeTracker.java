import java.util.*;

public class StudentGradeTracker {
    private ArrayList<Student> students;
    private Scanner scanner;
    
    public StudentGradeTracker() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Student class to store student information
    static class Student {
        private String name;
        private int id;
        private ArrayList<Double> grades;
        
        public Student(String name, int id) {
            this.name = name;
            this.id = id;
            this.grades = new ArrayList<>();
        }
        
        public void addGrade(double grade) {
            grades.add(grade);
        }
        
        public double calculateAverage() {
            if (grades.isEmpty()) return 0.0;
            double sum = 0;
            for (double grade : grades) {
                sum += grade;
            }
            return sum / grades.size();
        }
        
        public double getHighestGrade() {
            if (grades.isEmpty()) return 0.0;
            return Collections.max(grades);
        }
        
        public double getLowestGrade() {
            if (grades.isEmpty()) return 0.0;
            return Collections.min(grades);
        }
        
        // Getters
        public String getName() { return name; }
        public int getId() { return id; }
        public ArrayList<Double> getGrades() { return grades; }
    }
    
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        students.add(new Student(name, id));
        System.out.println("Student added successfully!");
    }
    
    public void addGradeToStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found. Please add students first.");
            return;
        }
        
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        
        Student student = findStudentById(id);
        if (student != null) {
            System.out.print("Enter grade: ");
            double grade = scanner.nextDouble();
            
            if (grade >= 0 && grade <= 100) {
                student.addGrade(grade);
                System.out.println("Grade added successfully!");
            } else {
                System.out.println("Invalid grade. Please enter a grade between 0 and 100.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }
    
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    public void displayStudentReport() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        System.out.println("\n=== STUDENT GRADE REPORT ===");
        System.out.println("ID\tName\t\tAverage\tHighest\tLowest\tGrades");
        System.out.println("================================================================");
        
        for (Student student : students) {
            System.out.printf("%d\t%-12s\t%.2f\t%.2f\t%.2f\t%s%n",
                student.getId(),
                student.getName(),
                student.calculateAverage(),
                student.getHighestGrade(),
                student.getLowestGrade(),
                student.getGrades().toString()
            );
        }
    }
    
    public void displayOverallStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        double totalAverage = 0;
        double overallHighest = Double.MIN_VALUE;
        double overallLowest = Double.MAX_VALUE;
        int totalStudents = students.size();
        
        for (Student student : students) {
            double avg = student.calculateAverage();
            totalAverage += avg;
            
            if (student.getHighestGrade() > overallHighest) {
                overallHighest = student.getHighestGrade();
            }
            if (student.getLowestGrade() < overallLowest) {
                overallLowest = student.getLowestGrade();
            }
        }
        
        System.out.println("\n=== OVERALL STATISTICS ===");
        System.out.printf("Total Students: %d%n", totalStudents);
        System.out.printf("Class Average: %.2f%n", totalAverage / totalStudents);
        System.out.printf("Highest Grade: %.2f%n", overallHighest);
        System.out.printf("Lowest Grade: %.2f%n", overallLowest);
    }
    
    public void showMenu() {
        System.out.println("\n=== STUDENT GRADE TRACKER ===");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. Display Student Report");
        System.out.println("4. Display Overall Statistics");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }
    
    public void run() {
        int choice;
        
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGradeToStudent();
                    break;
                case 3:
                    displayStudentReport();
                    break;
                case 4:
                    displayOverallStatistics();
                    break;
                case 5:
                    System.out.println("Thank you for using Student Grade Tracker!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        StudentGradeTracker tracker = new StudentGradeTracker();
        tracker.run();
    }
}
