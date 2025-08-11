//Jesse A P
package studentmanagement;

import java.util.ArrayList;

public class Student {
    private String name;
    private int ID, age;
    private ArrayList<Double> grades;
    
    public Student(){}//Default

    public Student(String name, int ID, int age) {//Main
        this.name = name;
        this.ID = ID;
        this.age = age;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }
    
    public double calcAverage(){
        double total = 0;
        for (int i = 0; i < grades.size(); i++) {
        total+=grades.get(i);
        }
        return total/grades.size();
    }
    
    public String toString(){
    return ID+" "+name + " is "+age+" years old";}
    
}
