package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Follow;
import po.FollowCustom;

public interface FollowMapper {
    int deleteByPrimaryKey(Integer followId);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(Integer followId);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);

    List<FollowCustom> selectFollowSelective(Follow record);
    
    List<FollowCustom> selectFollowListSelective(Follow record);
    
    List<FollowCustom> selectFollowListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("Follow")Follow record);
}