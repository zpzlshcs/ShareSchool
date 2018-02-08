package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Power;
import po.PowerCustom;

public interface PowerMapper {
    int deleteByPrimaryKey(Integer powerId);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(Integer powerId);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);

    List<PowerCustom> selectPowerSelective(Power record);
    
    List<PowerCustom> selectPowerListSelective(Power record);
    
    List<PowerCustom> selectPowerListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("Power")Power record);
}