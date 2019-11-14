package com.test.mapper.holidayMapper;

import com.test.pojo.ClassLeader;
import com.test.pojo.Holiday;

import java.util.List;

public interface HolidayMapper {
    public int addHoliday(Holiday holiday);

    public int deleteHolidayByHid(int hid);

    public int updateHoliday(Holiday holiday);

    public Holiday getHolidayByHid(int hid);

    public List<Holiday> getHolidayList();

    public int changeStateByHid(int hid);






    public ClassLeader getClassLeaderByHid(int hid);
}
