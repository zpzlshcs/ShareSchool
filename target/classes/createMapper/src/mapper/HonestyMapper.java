package mapper;

import po.Honesty;

public interface HonestyMapper {
    int deleteByPrimaryKey(Integer honestyId);

    int insert(Honesty record);

    int insertSelective(Honesty record);

    Honesty selectByPrimaryKey(Integer honestyId);

    int updateByPrimaryKeySelective(Honesty record);

    int updateByPrimaryKey(Honesty record);
}