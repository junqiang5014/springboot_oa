package com.test.pojo;

public class Report {
    private int rid;
    private Student student;
    private int yue;
    private int week;
    private String title;
    private String content;
    private int score;

    @Override
    public String toString() {
        return "Report{" +
                "rid=" + rid +
                ", student=" + student +
                ", yue=" + yue +
                ", week=" + week +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                '}';
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getYue() {
        return yue;
    }

    public void setYue(int yue) {
        this.yue = yue;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
