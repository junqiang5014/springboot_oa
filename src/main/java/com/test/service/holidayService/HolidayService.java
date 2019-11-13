package com.test.service.holidayService;

import com.test.pojo.Holiday;

import java.util.List;

public interface HolidayService {
    public int addHoliday(Holiday holiday);

    public int deleteHolidayByHid(int hid);

    public int updateHoliday(Holiday holiday);

    public Holiday getHolidayByHid(int hid);

    public List<Holiday> getHolidayList();

    public int changeStateByHid(int hid);

    public int addteacherHoliday(Holiday holiday, int id);
}
