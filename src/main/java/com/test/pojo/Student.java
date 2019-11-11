package com.test.pojo;

public class Student {
    private int stuid;
    private String stuname;
    private int stuage;
    private String stusex;
    private Classes Classes;

    @Override
    public String toString() {
        return "Student{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", stuage=" + stuage +
                ", stusex='" + stusex + '\'' +
                ", Classes=" + Classes +
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

    public Classes getaClasses() {
        return Classes;
    }

    public void setaClasses(Classes aClasses) {
        this.Classes = aClasses;
    }
}
