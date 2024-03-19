package com.oopgroup3.workspace_manager;

public class Workspace {
    // Variable of all variables on the card
    private String WorkSpace_Name;
    private String Work_Percentage;
    private String Deadline_Time;
    private String WorkSpace_Directory;

    // Parameterized Constructor
    public Workspace(String WorkSpace_Name, String Work_Percentage, String Deadline_Time, String WorkSpace_Directory) {
        this.WorkSpace_Name = WorkSpace_Name;
        this.Work_Percentage = Work_Percentage;
        this.Deadline_Time = Deadline_Time;
        this.WorkSpace_Directory = WorkSpace_Directory;
    }

    public String getWorkSpace_Name() {
        return WorkSpace_Name;
    }

    public void setWorkSpace_Name(String workSpace_Name) {
        WorkSpace_Name = workSpace_Name;
    }

    public String getWork_Percentage() {
        return Work_Percentage;
    }

    public void setWork_Percentage(String work_Percentage) {
        Work_Percentage = work_Percentage;
    }

    public String getDeadline_Time() {
        return Deadline_Time;
    }

    public void setDeadline_Time(String deadline_Time) {
        Deadline_Time = deadline_Time;
    }

    public void setWorkSpace_Directory(String workSpace_Directory) {
        WorkSpace_Directory = workSpace_Directory;
    }

    public String getWorkSpace_Directory() {
        return WorkSpace_Directory;
    }
}
