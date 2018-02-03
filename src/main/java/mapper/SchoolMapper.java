package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.School;

public interface SchoolMapper {
    int deleteByPrimaryKey(Integer schoolId);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Integer schoolId);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);

    List<School> selectSelective(School record);
    
    List<School> selectSchoolListSelective(School record);
    
    List<School> selectSchoolListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("School")School record);
}