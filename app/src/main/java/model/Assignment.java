package model;

/**
 * Created by Hermann on 25.06.2017.
 */

public class Assignment {

    int Course_Id;
    String Delivery_Type;
    Boolean Completed;
    String Weekday;
    String Delivery_Time;
    String Next_Delivery;

    //Constructors
    public Assignment(){
    }
    public Assignment(int Course_Id, String Delivery_Type, Boolean Completed, String Weekday,
                      String Delivery_Time, String Next_Delivery){

        this.Course_Id = Course_Id;
        this.Delivery_Type = Delivery_Type;
        this.Completed = Completed;
        this.Weekday = Weekday;
        this.Delivery_Time = Delivery_Time;
        this.Next_Delivery = Next_Delivery;
    }
    //Setters
    public void setCourse_Id(int course_Id) {
        Course_Id = course_Id;
    }
    public void setDelivery_Type(String delivery_Type) {
        Delivery_Type = delivery_Type;
    }
    public void setCompleted(Boolean completed) {
        Completed = completed;
    }
    public void setWeekday(String weekday) {
        Weekday = weekday;
    }
    public void setDelivery_Time(String delivery_Time) {
        Delivery_Time = delivery_Time;
    }
    public void setNext_Delivery(String next_Delivery) {
        Next_Delivery = next_Delivery;
    }

    //Getters
    public int getCourse_Id() {
        return Course_Id;
    }
    public String getDelivery_Type() {
        return Delivery_Type;
    }
    public Boolean getCompleted() {
        return Completed;
    }
    public String getWeekday() {
        return Weekday;
    }
    public String getDelivery_Time() {
        return Delivery_Time;
    }
    public String getNext_Delivery() {
        return Next_Delivery;
    }
}
