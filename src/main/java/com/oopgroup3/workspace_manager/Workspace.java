package com.oopgroup3.workspace_manager;

public class Workspace {
    // Variable of all variables on the card
    private String WorkSpace_Name;
    private String Deadline_Time;


    // Parameterized Constructor
    public Workspace(String WorkSpace_Name, String Deadline_Time) {
        this.WorkSpace_Name = WorkSpace_Name;
        this.Deadline_Time = Deadline_Time;
    }

    public String getWorkSpace_Name() {
        return WorkSpace_Name;
    }

    public void setWorkSpace_Name(String workSpace_Name) {
        WorkSpace_Name = workSpace_Name;
    }

    public String getDeadline_Time() {
        return Deadline_Time;
    }

    public void setDeadline_Time(String deadline_Time) {
        Deadline_Time = deadline_Time;
    }

    public String btnOpenPressed(){return WorkSpace_Name;}
}
