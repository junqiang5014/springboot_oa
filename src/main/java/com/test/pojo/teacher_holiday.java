package com.test.pojo;

public class teacher_holiday {
    private int tid;
    private int hid;

    @Override
    public String toString() {
        return "teacher_holiday{" +
                "tid=" + tid +
                ", hid=" + hid +
                '}';
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }
}
