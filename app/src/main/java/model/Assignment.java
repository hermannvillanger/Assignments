package model;

/**
 * Created by Hermann on 25.06.2017.
 */

public class Assignment {

    private Integer Course_Id;
    private Integer Assignment_Id;
    private String Delivery_Type;
    //0 for false, not completed, 1 for true
    private Integer Completed;
    private String Weekday;
    //Time of day for delivery, format HH:MM
    private String Delivery_Time;
    //Days until next delivery, ie 7 for 1 week
    private String Next_Delivery;

    //Constructors
    public Assignment(){
    }
    public Assignment(Integer Course_Id,Integer Assignment_Id, String Delivery_Type, int Completed, String Weekday,
                      String Delivery_Time, String Next_Delivery){

        this.Course_Id = Course_Id;
        this.Assignment_Id = Assignment_Id;
        this.Delivery_Type = Delivery_Type;
        this.Completed = Completed;
        this.Weekday = Weekday;
        this.Delivery_Time = Delivery_Time;
        this.Next_Delivery = Next_Delivery;
    }
    //Setters
    public void setCourse_Id(Integer Course_Id) {
        this.Course_Id = Course_Id;
    }
    public void setAssignment_Id(Integer Assignment_Id) {
        this.Assignment_Id = Assignment_Id;
    }
    public void setDelivery_Type(String Delivery_Type) {
        this.Delivery_Type = Delivery_Type;
    }
    public void setCompleted(Integer Completed) {
        this.Completed = Completed;
    }
    public void setWeekday(String Weekday) {
        this.Weekday = Weekday;
    }
    public void setDelivery_Time(String Delivery_Time) {
        this.Delivery_Time = Delivery_Time;
    }
    public void setNext_Delivery(String Next_Delivery) {
        this.Next_Delivery = Next_Delivery;
    }

    //Getters
    public Integer getCourse_Id() {
        return Course_Id;
    }
    public Integer getAssignment_Id() {
        return Assignment_Id;
    }
    public String getDelivery_Type() {
        return Delivery_Type;
    }
    public Integer getCompleted() {
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
