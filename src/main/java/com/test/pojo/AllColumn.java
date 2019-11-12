package com.test.pojo;

public class AllColumn {
private String cname; //班级名
private int stage;   //阶段
private double AVG; //平均分
private String stuname;
private int score;

    public AllColumn(String cname, int stage, double AVG) {
        this.cname = cname;
        this.stage = stage;
        this.AVG = AVG;
    }

    public AllColumn() {
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public double getAVG() {
        return AVG;
    }

    public void setAVG(double AVG) {
        this.AVG = AVG;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "AllColumn{" +
                "cname='" + cname + '\'' +
                ", stage=" + stage +
                ", AVG=" + AVG +
                ", stuname='" + stuname + '\'' +
                ", score=" + score +
                '}';
    }
}
