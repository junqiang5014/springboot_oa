package com.test.pojo;

public class Student {
    private int stuid;
    private String stuname;
    private int stuage;
    private String stusex;
    private Classes Classes;
    private User user;

    @Override
    public String toString() {
        return "Student{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", stuage=" + stuage +
                ", stusex='" + stusex + '\'' +
                ", Classes=" + Classes +
                ", user=" + user +
                '}';
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public int getStuage() {
        return stuage;
    }

    public void setStuage(int stuage) {
        this.stuage = stuage;
    }

    public String getStusex() {
        return stusex;
    }

    public void setStusex(String stusex) {
        this.stusex = stusex;
    }

    public com.test.pojo.Classes getClasses() {
        return Classes;
    }

    public void setClasses(com.test.pojo.Classes classes) {
        Classes = classes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
