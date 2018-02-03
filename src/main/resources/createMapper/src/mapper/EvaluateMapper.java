package mapper;

import po.Evaluate;

public interface EvaluateMapper {
    int deleteByPrimaryKey(Integer evaluateId);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Integer evaluateId);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
}