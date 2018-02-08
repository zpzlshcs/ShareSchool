package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Task;
import po.TaskCustom;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    List<TaskCustom> selectTaskSelective(Task record);
    
    List<TaskCustom> selectTaskListSelective(Task record);
    
    List<TaskCustom> selectTaskListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("Task")Task record);
}