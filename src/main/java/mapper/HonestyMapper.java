package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Honesty;

public interface HonestyMapper {
    int deleteByPrimaryKey(Integer honestyId);

    int insert(Honesty record);

    int insertSelective(Honesty record);

    Honesty selectByPrimaryKey(Integer honestyId);

    int updateByPrimaryKeySelective(Honesty record);

    int updateByPrimaryKey(Honesty record);

    List<Honesty> selectHonestySelective(Honesty record);
    
    List<Honesty> selectHonestyListSelective(Honesty record);
    
    List<Honesty> selectHonestyListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("Honesty")Honesty record);
}