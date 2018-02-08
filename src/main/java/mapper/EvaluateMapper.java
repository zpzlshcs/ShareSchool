package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Evaluate;
import po.EvaluateCustom;

public interface EvaluateMapper {
    int deleteByPrimaryKey(Integer evaluateId);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Integer evaluateId);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);

    List<EvaluateCustom> selectEvaluateSelective(Evaluate record);
    
    List<EvaluateCustom> selectEvaluateListSelective(Evaluate record);
    
    List<EvaluateCustom> selectEvaluateListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("Evaluate")Evaluate record);
}