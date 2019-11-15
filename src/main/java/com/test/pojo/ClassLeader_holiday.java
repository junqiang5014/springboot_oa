package com.test.pojo;

public class ClassLeader_holiday {
    private ClassLeader classLeader;
    private Holiday holiday;

    public ClassLeader_holiday(ClassLeader classLeader, Holiday holiday) {
        this.classLeader = classLeader;
        this.holiday = holiday;
    }

    public ClassLeader_holiday() {
    }

    @Override
    public String toString() {
        return "ClassLeader_holiday{" +
                "classLeader=" + classLeader +
                ", holiday=" + holiday +
                '}';
    }

    public ClassLeader getClassLeader() {
        return classLeader;
    }

    public void setClassLeader(ClassLeader classLeader) {
        this.classLeader = classLeader;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }
}
