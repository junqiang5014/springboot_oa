package com.test.pojo;

public class Report {
    private int rid;
    private String date;
    private String content;
    private int score;
    private Student student;

    @Override
    public String toString() {
        return "Report{" +
                "rid=" + rid +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", student=" + student +
                '}';
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
