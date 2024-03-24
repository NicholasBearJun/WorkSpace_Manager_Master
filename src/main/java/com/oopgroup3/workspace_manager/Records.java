package com.oopgroup3.workspace_manager;

public class Records {
    // all variables on the card
    private String AppName;
    private String Path;
    private String workspaceNameField;

    public Records(String AppName, String Path){
        this.AppName = AppName;
        this.Path = Path;
    }

    public void setWorkspaceName(String workspaceName) {
        workspaceNameField = workspaceName;
    }
    public String getWorkspaceName() {
        return workspaceNameField;
    }

    public void setAppName(String appName) {AppName = appName;}

    public void setPath(String path) {Path = path;}

    public String getAppName() {return AppName;}

    public String getPath() {return Path;}
}
