package com.test.pojo;

public class ClassLeader {
    private int clid;
    private String clname;
    private User user;

    @Override
    public String toString() {
        return "ClassLeader{" +
                "clid=" + clid +
                ", clname='" + clname + '\'' +
                ", user=" + user +
                '}';
    }

    public int getClid() {
        return clid;
    }

    public void setClid(int clid) {
        this.clid = clid;
    }

    public String getClname() {
        return clname;
    }

    public void setClname(String clname) {
        this.clname = clname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
