package com.test.pojo;

public class Classes {
    private int cid;
    private String cname;
    private Teacher teacher;
    private ClassLeader classLeader;

    @Override
    public String toString() {
        return "Classes{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", teacher=" + teacher +
                ", classLeader=" + classLeader +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ClassLeader getClassLeader() {
        return classLeader;
    }

    public void setClassLeader(ClassLeader classLeader) {
        this.classLeader = classLeader;
    }
}
