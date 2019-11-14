package com.test.pojo;

public class Score {
    private int scoreid;
    private int score;
    private Student student;
    private int stage;
    private Course course;

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreid=" + scoreid +
                ", score='" + score + '\'' +
                ", student=" + student +
                ", stage=" + stage +
                ", course=" + course +
                '}';
    }

    public int getScoreid() {
        return scoreid;
    }

    public void setScoreid(int scoreid) {
        this.scoreid = scoreid;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
