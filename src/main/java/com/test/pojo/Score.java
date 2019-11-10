package com.test.pojo;

public class Score {
    private int scoreid;
    private String score;
    private Student student;
    private Course course;

    @Override
    public String toString() {
        return "Score{" +
                "scoreid=" + scoreid +
                ", score='" + score + '\'' +
                ", student=" + student +
                ", course=" + course +
                '}';
    }

    public int getScoreid() {
        return scoreid;
    }

    public void setScoreid(int scoreid) {
        this.scoreid = scoreid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
