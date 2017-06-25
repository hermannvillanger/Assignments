package model;

/**
 * Created by Hermann on 25.06.2017.
 */

public class Course {

    int Course_Id;
    String Name;
    String Course_Code;

    //Constructors
    public Course(){
    }
    public Course(int Course_Id, String Name, String Course_Code){
        this.Course_Id = Course_Id;
        this.Name = Name;
        this.Course_Code = Course_Code;
    }

    //Setters
    public void setCId(int Course_Id){
        this.Course_Id = Course_Id;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public void setCCode(String Course_Code){
        this.Course_Code = Course_Code;
    }
    //Getters
    public int getCId(){
        return this.Course_Id;
    }
    public String getName(){
        return this.Name;
    }
    public String getCCode(){
        return this.Course_Code;
    }

}
