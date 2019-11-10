package com.test.pojo;

public class Holiday {
    private int hid;
    private String reason;
    private int days;
    private int state;

    @Override
    public String toString() {
        return "Holiday{" +
                "hid=" + hid +
                ", reason='" + reason + '\'' +
                ", days=" + days +
                ", state=" + state +
                '}';
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
