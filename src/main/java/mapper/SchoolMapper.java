package mapper;

import java.util.List;


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
    
}