//Jesse A P
package todolist;

import java.util.Random;

/**
 *
 * @author Studio20-10
 */
abstract class ToDo {

    private int ID, priority = 0;
    private String task, code;
    private boolean daily = false, weekly = false;
    final static String RESET = "\u001B[0m";
    final static String RED = "\u001B[31m";
    final static String GREEN = "\u001B[32m";
    final static String YELLOW = "\u001B[33m";

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getID() {
        return ID;
    }

    public void setID() {
        Random rngID = new Random();
        ID = rngID.nextInt(100, 1000);
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDaily() {
        return daily;
    }

    public void setDaily(boolean daily) {
        this.daily = daily;
    }

    public boolean isWeekly() {
        return weekly;
    }

    public void setWeekly(boolean weekly) {
        this.weekly = weekly;
    }

    public String randomCode() {
        return null;
    }

    public static String tabber(int num) {
        String whiteSpace = "";
        if (num >= 7) {
            num++;
        }
        for (int i = 0; i <= 18 - num; i++) {
            whiteSpace += "\t";
        }
        return whiteSpace;
    }

    public String toString() {

        return "To Do:" + task;
    }
}
