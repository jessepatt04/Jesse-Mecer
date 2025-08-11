//Jesse A P
package todolist;

import java.util.ArrayList;
import java.util.Collections;
/**
 * 
 * @author Studio20-10
 */
public class Present extends ToDo implements Comparable<Present> {

    private String code;
    private static ArrayList<Present> alPre = new ArrayList<>();

    public Present(String task, String code, boolean daily, boolean weekly) {//Scanner 
        this.setTask(task);
        this.setCode(code);
        this.setDaily(daily);
        this.setWeekly(weekly);
        alPre.add(this);
    }
    
    public Present(String task, boolean daily, boolean weekly){//Change down with recurence
        this.setTask(task);
        this.setCode(randomCode());
        this.setDaily(daily);
        this.setWeekly(weekly);
        alPre.add(this);
    }

    public Present(String task) {//New
        this.setTask(task);
        this.setCode(randomCode());
        alPre.add(this);
    }

    @Override
    public int compareTo(Present other) {
        return other.getPriority() - this.getPriority();
    }

    public static void displayPresent() {
        /*Implements the comparable<> interface where we override compareTo() 
        to compare objects between priority and sorts*/
        Collections.sort(alPre);
        for (Present pre : alPre) {
            pre.setPriority(0);
            double tab = pre.getTask().length() / 8;
            System.out.println(YELLOW+pre.getCode() + "\t" + pre.getTask() + tabber((int)Math.floor(tab)) + "Doing Now"+RESET);
        }
    }

    public static ArrayList<Present> getAlPre() {
        return alPre;
    }

    @Override
    public String randomCode() {
        setID();
        while (checkID(getID()) == false) {
            setID();
        }
        code = "N" + Integer.toString(getID());
        return code;
    }

    public boolean checkID(int id) {
        for (int i = 0; i < alPre.size(); i++) {
            if (alPre.get(i).getID() == id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return getCode() + ":" + getTask()+ ":" + isDaily() + ":" + isWeekly();
    }
}
