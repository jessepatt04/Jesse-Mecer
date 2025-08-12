//Jesse Araujo Pattison
package studentmanagement;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagement {

    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();

        System.out.println("Welcome\nEnter nothing to continue the program (\"\")");

        do {//Loop adding students name,ID,age
            try {
                boolean hasFound = false;
                Scanner scLine = new Scanner(System.in);//Scanner to take user input
                System.out.println("Enter Student " + (studentList.size() + 1) + "'s Name");
                String name = scLine.nextLine();

                if (name.equals("")) {//If nothing is entered end loop
                    break;
                }

                System.out.println("Enter Student " + (studentList.size() + 1) + "'s ID");
                int ID = scLine.nextInt();

                System.out.println("Enter Student " + (studentList.size() + 1) + "'s Age");
                int age = scLine.nextInt();

                for (Student student : studentList) {//Checks if student name has been taken
                    if (student.getName().equals(name)) {
                        hasFound = true;
                    }
                }

                if (!hasFound) {//If name is not used add
                    studentList.add(new Student(name, ID, age));
                    System.out.println("Added");
                } else {
                    System.err.println("Student name taken");
                }
            } catch (InputMismatchException e) {
                System.err.println("Bad Input");
            }
        } while (true);

        System.out.println("Enter grades for your Students\nEnter nothing to continue the program (\"\")");

        do {//Loop for editing Students grades
            
            boolean hasFound = false;

            Scanner scLine = new Scanner(System.in);
            System.out.println("Which student would you like to edit");
            String choice = scLine.nextLine();

            if (choice.equals("")) {
                break;
            }

            for (Student student : studentList) {
                if (student.getName().equalsIgnoreCase(choice)) {
                    hasFound = true;

                    do {
                        try{
                        boolean isLoop = true;
                        Scanner scEdit = new Scanner(System.in);
                        System.out.println("1 Add | 2 Remove | 3 Display | 4 Edit | 5 Exit");
                        int num = scEdit.nextInt();

                        switch (num) {//Switch for add , remove , display , edit , exit
                            case 1 -> {//Adding
                                Scanner inGrades = new Scanner(System.in);
                                System.out.println("How many grades");
                                int many = inGrades.nextInt();

                                if (many < 4) {
                                    for (int i = 0; i < many; i++) {
                                        System.out.println("Enter a grade");
                                        double grade = inGrades.nextDouble();
                                        student.getGrades().add(grade);
                                    }
                                } else {
                                    System.err.println("Can't add more than 3 tests in at once");
                                }
                            }
                            case 2 -> {//Removing
                                Scanner removeGrades = new Scanner(System.in);
                                System.out.println("Which grade would you like to remove");
                                int option = removeGrades.nextInt();

                                if (option <= student.getGrades().size()) {//Checks valid input
                                    student.getGrades().remove(option - 1);
                                    System.out.println("Removed");
                                } else {
                                    System.err.println("Dosen't Exist");
                                }
                            }
                            case 3 -> {//Display
                                int i = 1;
                                for (Double grades : student.getGrades()) {
                                    System.out.println(i + ": " + grades + " ");
                                    i++;
                                }
                                System.out.println();
                            }
                            case 4 -> {//Editing
                                Scanner changeGrades = new Scanner(System.in);
                                System.out.println("Which grade would you like to edit");
                                int option = changeGrades.nextInt();

                                if (option <= student.getGrades().size()) {//Checks valid input
                                    System.out.println(option + ": " + student.getGrades().get(option - 1));
                                    System.out.println("New grade");
                                    double newGrade = changeGrades.nextDouble();
                                    student.getGrades().set(option - 1, newGrade);//Set changes in the arraylist
                                } else {
                                    System.err.println("Dosen't Exist");
                                }
                            }
                            case 5 -> {//Exit
                                isLoop = false;
                            }
                            default -> {
                                System.err.println("Not a valid option");
                            }
                        }

                        if (!isLoop) {//Escapes loop
                            break;
                        }
                        }catch(InputMismatchException e){
                            System.err.println("Bad Input");
                        }
                    } while (true);
                }
            }

            String text = (!hasFound) ? "Never Found\n" : "Edited\n";
            System.out.println(text);

        } while (true);

        DecimalFormat df = new DecimalFormat("0.00");//Display average of each student and class
        double total = 0;
        for (Student student : studentList) {
            total += student.calcAverage();
            System.out.println(student);
            System.out.println(student.getName() + " has the average of " + df.format(student.calcAverage()));
        }
        System.out.println("\nThe class average is " + df.format(total / studentList.size()));
    }
}
