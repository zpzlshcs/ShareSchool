package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Honesty;
import po.HonestyCustom;

public interface HonestyMapper {
    int deleteByPrimaryKey(Integer honestyId);

    int insert(Honesty record);

    int insertSelective(Honesty record);

    Honesty selectByPrimaryKey(Integer honestyId);

    int updateByPrimaryKeySelective(Honesty record);

    int updateByPrimaryKey(Honesty record);

    List<HonestyCustom> selectHonestySelective(Honesty record);
    
    List<HonestyCustom> selectHonestyListSelective(Honesty record);
    
    List<HonestyCustom> selectHonestyListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("Honesty")Honesty record);
}