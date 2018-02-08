package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Collect;
import po.CollectCustom;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer collectId);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer collectId);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<CollectCustom> selectCollectSelective(Collect record);
    
    List<CollectCustom> selectCollectListSelective(Collect record);
    
    List<CollectCustom> selectCollectListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("Collect")Collect record);
}