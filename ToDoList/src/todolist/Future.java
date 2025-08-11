//Jesse A P
package todolist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Studio20-10
 */
public class Future extends ToDo implements Comparable<Future> {

    private String code;
    private static ArrayList<Future> alFut = new ArrayList<>();
    private LocalDate dueDate, now = LocalDate.now();
    

    public Future(String task, String code, LocalDate due, boolean daily, boolean weekly) {//Scanner
        this.setTask(task);
        this.setCode(code);
        this.setDaily(daily);
        this.setWeekly(weekly);
        this.dueDate = due;
        alFut.add(this);
    }

    public Future(String task, boolean daily, boolean weekly) {//Change up or down with recur
        this.setTask(task);
        this.setCode(randomCode());
        if (daily == true) {
            this.dueDate = now.plusDays(1);
        } else if (weekly == true) {
            this.dueDate = now.plusWeeks(1);
        } else {
            this.dueDate = now;
        }
        this.setDaily(daily);
        this.setWeekly(weekly);
        alFut.add(this);
    }

    public Future(String task) {//New
        this.setTask(task);
        this.setCode(randomCode());
        this.dueDate = now;
        alFut.add(this);
    }

    @Override
    public int compareTo(Future other) {
        return other.getPriority() - this.getPriority();
    }

    public static void displayFuture() {
        /*Implements the comparable<> interface where we override compareTo() 
        to compare objects between priority and sorts*/
        Collections.sort(alFut);
        for (Future fut : alFut) {
            fut.setPriority(0);
            double tab = fut.getTask().length() / 8;
            System.out.println(RED+fut.getCode() + "\t" + fut.getTask() + tabber((int) Math.floor(tab)) + fut.dueDate+RESET);
        }
    }

    public static ArrayList<Future> getAlFut() {
        return alFut;
    }

    @Override
    public String randomCode() {
        setID();
        while (checkID(getID()) == false) {
            setID();
        }
        code = "F" + Integer.toString(getID());
        return code;
    }

    public boolean checkID(int id) {
        for (int i = 0; i < alFut.size(); i++) {
            if (alFut.get(i).getID() == id) {
                return false;
            }
        }
        return true;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public static boolean isValidPattern(String due) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {

            LocalDate.parse(due, format);
            return true;

        } catch (DateTimeParseException e) {

            return false;

        }
    }

    @Override
    public String toString() {
        return getCode() + ":" + getTask() + ":" + dueDate + ":" + isDaily() + ":" + isWeekly();
    }
}
