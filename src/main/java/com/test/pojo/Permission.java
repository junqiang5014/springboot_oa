package com.test.pojo;

public class Permission {
    private int pid;
    private String rolename;
    private String permission;

    @Override
    public String toString() {
        return "Permission{" +
                "pid=" + pid +
                ", rolename='" + rolename + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
