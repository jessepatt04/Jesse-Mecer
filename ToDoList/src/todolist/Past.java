//Jesse A P
package todolist;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * 
 * @author Studio20-10
 */
public class Past extends ToDo {

    private String code;
    private static ArrayList<Past> alPas = new ArrayList<>();
    private LocalDate dueDate, now = LocalDate.now();

    public Past(String task, String code,LocalDate due) {//Scanner
        this.setTask(task);
        this.setCode(code);
        this.dueDate = due;
        alPas.add(this);
    }

    public Past(String task) {//New
        this.setTask(task);
        this.setCode(randomCode());
        this.dueDate = now;
        alPas.add(this);
    }

    public static void displayPast() {
        for (Past pas : alPas) {
            
            double tab = pas.getTask().length()/8;
            System.out.println(GREEN+pas.getCode()+"\t"+pas.getTask()+tabber((int)Math.floor(tab))+pas.dueDate+RESET);
        }
    }

    public static ArrayList<Past> getAlPas() {
        return alPas;
    }

    @Override
    public String randomCode() {
        setID();
        while (checkID(getID()) == false) {
            setID();
        }
        code = "P" + Integer.toString(getID());
        return code;
    }

    public boolean checkID(int id) {
        for (int i = 0; i < alPas.size(); i++) {
            if (alPas.get(i).getID() == id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return getCode() +":"+ getTask() +":"+ dueDate;
    }
}
