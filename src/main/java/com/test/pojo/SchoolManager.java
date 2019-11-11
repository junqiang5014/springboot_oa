package com.test.pojo;

public class SchoolManager {
    private int smid;
    private String smname;
    private User user;

    @Override
    public String toString() {
        return "SchoolManager{" +
                "smid=" + smid +
                ", smname='" + smname + '\'' +
                ", user=" + user +
                '}';
    }

    public int getSmid() {
        return smid;
    }

    public void setSmid(int smid) {
        this.smid = smid;
    }

    public String getSmname() {
        return smname;
    }

    public void setSmname(String smname) {
        this.smname = smname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
