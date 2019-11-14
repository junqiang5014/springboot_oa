package com.test.mapper.holidayMapper;

import com.test.pojo.Holiday;
import com.test.pojo.User;

import java.util.List;

public interface HolidayMapper {
    public int addHoliday(Holiday holiday);

    public int deleteHolidayByHid(int hid);

    public int updateHoliday(Holiday holiday);

    public Holiday getHolidayByHid(int hid);

    public List<Holiday> getHolidayList();

    public int changeStateByHid(int hid);

    /**
     * 根据hid查询所对应的角色
     * @param hid
     * @return
     */
    public User getUserByHid(int hid);
}
