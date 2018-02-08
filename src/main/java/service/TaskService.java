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

import exception.CustomException;
import po.TaskCustom;
import utils.ResultBean;
import utils.ResultList;

public interface TaskService {

	//创建任务
	ResultBean<TaskCustom> insert(TaskCustom taskCustom) throws CustomException;
	//删除任务
	ResultBean<TaskCustom> delete(TaskCustom taskCustom) throws CustomException;
	//修改任务
	ResultBean<TaskCustom> update(TaskCustom taskCustom) throws CustomException;
	//根据条件获取任务
	ResultBean<TaskCustom> getTaskSelective(TaskCustom taskCustom) throws CustomException;
	//根据条件获取任务列表
	ResultBean<ResultList<TaskCustom>> getTaskListSelective
	(TaskCustom taskCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取任务列表
	ResultBean<ResultList<TaskCustom>> getTaskListSelectiveByTime
	(TaskCustom taskCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;

}
