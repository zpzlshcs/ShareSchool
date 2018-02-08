package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Information;
import po.InformationCustom;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer infoId);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer infoId);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);

    List<InformationCustom> selectInformationSelective(Information record);
    
    List<InformationCustom> selectInformationListSelective(Information record);
    
    List<InformationCustom> selectInformationListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("Information")Information record);
}