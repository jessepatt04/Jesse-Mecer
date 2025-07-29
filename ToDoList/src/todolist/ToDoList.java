//Jesse Araujo Pattison
package todolist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Studio20-10
 */
public class ToDoList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File mainFile = new File("ToDoList.txt");

        try {//Scanner starts

            Scanner scFile = new Scanner(mainFile);

            while (scFile.hasNextLine()) {

                Scanner scLine = new Scanner(scFile.nextLine()).useDelimiter(":");

                String code = scLine.next();
                String task = scLine.next();
                switch (code.charAt(0)) {//Create a obj that gets stored in a static arraylist
                    case 'F' -> {
                        LocalDate due = LocalDate.parse(scLine.next());
                        ToDo temp = new Future(task, code, due);
                    }
                    case 'N' -> {
                        ToDo temp = new Present(task, code);
                    }
                    case 'P' -> {
                        LocalDate due = LocalDate.parse(scLine.next());
                        ToDo temp = new Past(task, code, due);
                    }
                    default -> {
                    }
                }
            }

            scFile.close();

        } catch (FileNotFoundException ex) {
            System.out.println("No File");
        } finally {
            System.out.println("Finished Scan");
        }
        //Scanner ends

        boolean loop = true;//Loops through code until nothing is entered
        LocalDate now = LocalDate.now();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("\n1 Display | 2 Remove | 3 New | 4 Change Down | 5 Change Up");
            System.out.println("6 Clear | 7 Edit Date | 8 Edit Task | 9 Increase | 10 Decrease");
            System.out.println("11 Add Recurence");
            switch (input.nextLine()) {
                case "" -> {//End Loop
                    loop = false;
                }
                case "1" -> {//Display arrList and static values

                    System.out.println("-------------------------------------");
                    System.out.println("Today's date: " + now + "\t" + time);
                    System.out.println("-------------------------------------");
                    System.out.println("Code:\tTask:" + ToDo.tabber(0) + "Dates:");
                    System.out.println("\nFuture:" + Future.getAlFut().size());
                    Future.displayFuture();
                    System.out.println("\nPresent:" + Present.getAlPre().size());
                    Present.displayPresent();
                    System.out.println("\nPast:" + Past.getAlPas().size());
                    Past.displayPast();
                    System.out.println("-------------------------------------");
                }
                case "2" -> {//Remove from arrList and decrement static value
                    boolean hasFound = false;
                    Scanner remove = new Scanner(System.in);
                    System.out.println("\nWhat would you like to remove");
                    String rmv = remove.nextLine();
                    for (int i = 0; i < Future.getAlFut().size(); i++) {
                        if (Future.getAlFut().get(i).getTask().equalsIgnoreCase(rmv) || Future.getAlFut().get(i).getCode().equalsIgnoreCase(rmv)) {
                            Future.getAlFut().remove(i);
                            hasFound = true;
                        }
                    }
                    for (int i = 0; i < Present.getAlPre().size() && hasFound == false; i++) {
                        if (Present.getAlPre().get(i).getTask().equalsIgnoreCase(rmv) || Present.getAlPre().get(i).getCode().equalsIgnoreCase(rmv)) {
                            Present.getAlPre().remove(i);
                            hasFound = true;
                        }
                    }
                    for (int i = 0; i < Past.getAlPas().size() && hasFound == false; i++) {
                        if (Past.getAlPas().get(i).getTask().equalsIgnoreCase(rmv) || Past.getAlPas().get(i).getCode().equalsIgnoreCase(rmv)) {
                            Past.getAlPas().remove(i);
                            hasFound = true;
                        }
                    }
                    if (hasFound == true) {
                        System.out.println("Removed");
                    } else {
                        System.out.println("Not Found");
                    }
                }
                case "3" -> {//Add to arrList as a Future obj
                    Scanner newFuture = new Scanner(System.in);
                    System.out.println("\nEnter a new Task (Max 104 characters)");
                    String newTask = newFuture.nextLine();
                    if (newTask.length() > 104) {
                        System.out.println("To long of a task");
                    } else {
                        ToDo temp = new Future(newTask);
                    }
                }
                case "4" -> {
                    /*Remove obj and add a new obj of different subclass
                  Future -> Present -> Past*/
                    boolean hasFound = false;
                    Scanner changeDown = new Scanner(System.in);
                    System.out.println("\nWhat would you like to change");
                    String chngDwn = changeDown.nextLine();
                    for (int i = 0; i < Future.getAlFut().size(); i++) {

                        if (Future.getAlFut().get(i).getTask().equalsIgnoreCase(chngDwn) || Future.getAlFut().get(i).getCode().equalsIgnoreCase(chngDwn)) {

                            ToDo temp = new Present(Future.getAlFut().get(i).getTask(),Future.getAlFut().get(i).isDaily(),Future.getAlFut().get(i).isWeekly());
                            Future.getAlFut().remove(i);
                            hasFound = true;
                            break;
                        }
                    }
                    for (int i = 0; i < Present.getAlPre().size() && hasFound == false; i++) {

                        if (Present.getAlPre().get(i).getTask().equalsIgnoreCase(chngDwn) || Present.getAlPre().get(i).getCode().equalsIgnoreCase(chngDwn)) {
                            if(Present.getAlPre().get(i).isDaily()){}
                            ToDo temp = new Past(Present.getAlPre().get(i).getTask());
                            Present.getAlPre().remove(i);
                            hasFound = true;
                            break;
                        }
                    }

                    if (hasFound == true) {
                        System.out.println("Changed Down");
                    } else {
                        System.out.println("Not Found");
                    }
                }
                case "5" -> {
                    /*Remove obj and add a new obj of different subclass
                  Past -> Present -> Future*/
                    boolean hasFound = false;
                    Scanner changeUp = new Scanner(System.in);
                    System.out.println("\nWhat would you like to change");
                    String chngP = changeUp.nextLine();
                    for (int i = 0; i < Past.getAlPas().size(); i++) {

                        if (Past.getAlPas().get(i).getTask().equalsIgnoreCase(chngP) || Past.getAlPas().get(i).getCode().equalsIgnoreCase(chngP)) {

                            String changePastTask = Past.getAlPas().get(i).getTask();
                            Past.getAlPas().remove(i);
                            ToDo temp = new Present(changePastTask);
                            hasFound = true;
                            break;

                        }
                    }
                    for (int i = 0; i < Present.getAlPre().size() && hasFound == false; i++) {

                        if (Present.getAlPre().get(i).getTask().equalsIgnoreCase(chngP) || Present.getAlPre().get(i).getCode().equalsIgnoreCase(chngP)) {

                            String changePresentTask = Present.getAlPre().get(i).getTask();
                            Present.getAlPre().remove(i);
                            ToDo temp = new Future(changePresentTask);
                            hasFound = true;
                            break;

                        }
                    }
                    if (hasFound == true) {
                        System.out.println("Changed Up");
                    } else {
                        System.out.println("Not Found");
                    }
                }
                case "6" -> {//Clear all arrLists
                    Scanner confirm = new Scanner(System.in);
                    System.out.println("\nAre you sure you want to clear (Type Confirm)");
                    String clear = confirm.nextLine();
                    if (clear.equalsIgnoreCase("confirm")) {
                        Future.getAlFut().clear();
                        Present.getAlPre().clear();
                        Past.getAlPas().clear();
                    }
                }
                case "7" -> {//Change localdate
                    boolean hasFound = false, validPattern = false;

                    Scanner edit = new Scanner(System.in);
                    System.out.println("\nWhich Future task would you like to edit");
                    String edited = edit.nextLine();

                    for (int i = 0; i < Future.getAlFut().size(); i++) {

                        if (Future.getAlFut().get(i).getTask().equalsIgnoreCase(edited) || Future.getAlFut().get(i).getCode().equalsIgnoreCase(edited)) {

                            System.out.println("\n" + Future.getAlFut().get(i).toString());
                            hasFound = true;
                            Scanner found = new Scanner(System.in);
                            System.out.println("\nEnter New due date (yyyy-mm-dd)");
                            String newDue = found.nextLine();

                            if (Future.isValidPattern(newDue)) {
                                /*The isValidPattern method takes the date entered and uses LocalDate.parse to check
                              if the date entered is the same as the pattern
                              if it is not it gives a datetime exception
                              I use a try catch to return true or false*/
                                Future.getAlFut().get(i).setDueDate(LocalDate.parse(newDue));
                                validPattern = true;

                            } else {
                                System.out.println("Not a valid date pattern");
                            }

                        }
                    }
                    if (hasFound == true && validPattern == true) {
                        System.out.println("Edited");
                    } else {
                        System.out.println("Something went wrong");
                    }
                }
                case "8" -> {//Edit Task 
                    boolean hasFound = false;
                    Scanner edit = new Scanner(System.in);
                    System.out.println("\nWhich Task would you like to edit");
                    String edited = edit.nextLine();

                    for (int i = 0; i < Future.getAlFut().size(); i++) {
                        if (Future.getAlFut().get(i).getTask().equalsIgnoreCase(edited) || Future.getAlFut().get(i).getCode().equalsIgnoreCase(edited)) {
                            System.out.println("\nTask: " + Future.getAlFut().get(i).getTask());
                            Scanner change = new Scanner(System.in);
                            System.out.println("Enter Change");
                            String changeTask = change.nextLine();
                            if (changeTask.length() > 104) {
                                System.out.println("To long of a task");
                            } else {
                                Future.getAlFut().get(i).setTask(changeTask);
                            }
                            hasFound = true;
                        }
                    }
                    for (int i = 0; i < Present.getAlPre().size() && hasFound == false; i++) {
                        if (Present.getAlPre().get(i).getTask().equalsIgnoreCase(edited) || Present.getAlPre().get(i).getCode().equalsIgnoreCase(edited)) {
                            System.out.println("\nTask: " + Present.getAlPre().get(i).getTask());
                            Scanner change = new Scanner(System.in);
                            System.out.println("Enter Change");
                            String changeTask = change.nextLine();
                            if (changeTask.length() > 104) {
                                System.out.println("To long of a task");
                            } else {
                                Present.getAlPre().get(i).setTask(changeTask);
                            }
                            hasFound = true;
                        }
                    }
                    for (int i = 0; i < Past.getAlPas().size() && hasFound == false; i++) {
                        if (Past.getAlPas().get(i).getTask().equalsIgnoreCase(edited) || Past.getAlPas().get(i).getCode().equalsIgnoreCase(edited)) {
                            System.out.println("\nTask: " + Past.getAlPas().get(i).getTask());
                            Scanner change = new Scanner(System.in);
                            System.out.println("Enter Change");
                            String changeTask = change.nextLine();
                            if (changeTask.length() > 104) {
                                System.out.println("To long of a task");
                            } else {
                                Past.getAlPas().get(i).setTask(changeTask);
                            }
                            hasFound = true;
                        }
                    }
                    if (hasFound == true) {
                        System.out.println("Edited");
                    } else {
                        System.out.println("Not Found");
                    }
                }
                case "9" -> {//Increase the priority by 1
                    boolean hasFound = false;
                    Scanner edit = new Scanner(System.in);
                    System.out.println("\nWhich Task would you like to increase");
                    String edited = edit.nextLine();

                    for (int i = 0; i < Future.getAlFut().size(); i++) {
                        if (Future.getAlFut().get(i).getTask().equalsIgnoreCase(edited) || Future.getAlFut().get(i).getCode().equalsIgnoreCase(edited)) {
                            Future.getAlFut().get(i).setPriority(Future.getAlFut().get(i).getPriority() + 1);
                            hasFound = true;
                        }
                    }
                    for (int i = 0; i < Present.getAlPre().size() && hasFound == false; i++) {
                        if (Present.getAlPre().get(i).getTask().equalsIgnoreCase(edited) || Present.getAlPre().get(i).getCode().equalsIgnoreCase(edited)) {
                            Present.getAlPre().get(i).setPriority(Present.getAlPre().get(i).getPriority() + 1);
                            hasFound = true;
                        }
                    }
                    if (hasFound == true) {
                        System.out.println("Increased");
                    } else {
                        System.out.println("Not Found");
                    }
                }
                case "10" -> {//Decrease the priority by 1
                    boolean hasFound = false;
                    Scanner edit = new Scanner(System.in);
                    System.out.println("\nWhich Task would you like to decrease");
                    String edited = edit.nextLine();

                    for (int i = 0; i < Future.getAlFut().size(); i++) {
                        if (Future.getAlFut().get(i).getTask().equalsIgnoreCase(edited) || Future.getAlFut().get(i).getCode().equalsIgnoreCase(edited)) {
                            Future.getAlFut().get(i).setPriority(Future.getAlFut().get(i).getPriority() - 1);
                            hasFound = true;
                        }
                    }
                    for (int i = 0; i < Present.getAlPre().size() && hasFound == false; i++) {
                        if (Present.getAlPre().get(i).getTask().equalsIgnoreCase(edited) || Present.getAlPre().get(i).getCode().equalsIgnoreCase(edited)) {
                            Present.getAlPre().get(i).setPriority(Present.getAlPre().get(i).getPriority() - 1);
                            hasFound = true;
                        }
                    }
                    if (hasFound == true) {
                        System.out.println("Decreased");
                    } else {
                        System.out.println("Not Found");
                    }
                }
                case "11" ->{
                boolean hasFound = false;
                    Scanner recur = new Scanner(System.in);
                    System.out.println("\n1 Daily | 2 Weekly");
                    String dailyOrWeekly = recur.nextLine();
                    Scanner task = new Scanner(System.in);
                    System.out.println("\nWhich Task");
                    String edited = task.nextLine();

                    for (int i = 0; i < Future.getAlFut().size(); i++) {
                        if (Future.getAlFut().get(i).getTask().equalsIgnoreCase(edited) || Future.getAlFut().get(i).getCode().equalsIgnoreCase(edited)) {
                            Future.getAlFut().get(i).setDaily((dailyOrWeekly.equals("1")));
                            Future.getAlFut().get(i).setWeekly((dailyOrWeekly.equals("2")));
                            hasFound = true;
                        }
                    }
                    for (int i = 0; i < Present.getAlPre().size() && hasFound == false; i++) {
                        if (Present.getAlPre().get(i).getTask().equalsIgnoreCase(edited) || Present.getAlPre().get(i).getCode().equalsIgnoreCase(edited)) {
                            Present.getAlPre().get(i).setDaily((dailyOrWeekly.equals("1")));
                            Present.getAlPre().get(i).setWeekly((dailyOrWeekly.equals("2")));
                            hasFound = true;
                        }
                    }
                    if (hasFound == true) {
                        System.out.println("Done");
                    } else {
                        System.out.println("Not Found");
                    }
                }
                default -> {
                    System.out.println("\nOption not valid\nIf you want to end the loop enter nothing (\"\")");
                }
            }

        } while (loop == true);//Do while condition

        //Writer starts
        try {

            FileWriter fwFile = new FileWriter(mainFile);

            for (int i = 0; i < Future.getAlFut().size(); i++) {
                fwFile.write(Future.getAlFut().get(i).toString());
                fwFile.write("\n");
            }
            for (int i = 0; i < Present.getAlPre().size(); i++) {
                fwFile.write(Present.getAlPre().get(i).toString());
                fwFile.write("\n");
            }
            for (int i = 0; i < Past.getAlPas().size(); i++) {
                fwFile.write(Past.getAlPas().get(i).toString());
                fwFile.write("\n");
            }

            fwFile.close();

        } catch (IOException ex) {
            System.out.println("IO Error");
        } finally {
            System.out.println("Finalized Write");
        }//Writer ends

    }

}
