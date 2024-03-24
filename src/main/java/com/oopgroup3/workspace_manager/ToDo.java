package com.oopgroup3.workspace_manager;

public class ToDo {
    // all variables on the card
    private String AppName;
    private String Path;
    public ToDo(String AppName, String Path){
        this.AppName = AppName;
        this.Path = Path;
    }
        public void setAppName(String appName) {AppName = appName;}

    public void setPath(String path) {Path = path;}

    public String getAppName() {return AppName;}

    public String getPath() {return Path;}
}
