package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: TaskService.java
* @Description: 任务
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月3日 下午3:56:23 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月3日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import po.TaskCustom;
import utils.ResultBean;
import utils.ResultList;

public interface TaskService {

	//创建任务
	ResultBean<TaskCustom> insert(TaskCustom taskCustom);
	//删除任务
	ResultBean<TaskCustom> insert(Integer taskId);
	//修改任务
	ResultBean<TaskCustom> update(TaskCustom taskCustom);
	//根据条件获取任务
	ResultBean<TaskCustom> getTaskSelective(TaskCustom taskCustom);
	//根据条件获取任务列表
	ResultBean<ResultList<TaskCustom>> getTaskListSelective
	(TaskCustom taskCustom,int pageSize,int pageNum);
	//根据时间+条件获取任务列表
	ResultBean<ResultList<TaskCustom>> getTaskListSelectiveByTime
	(TaskCustom taskCustom,Date startTime,Date endTime,int pageSize,int pageNum);

}
