package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.TaskMapper;
import po.Task;
import po.TaskCustom;
import service.TaskService;
import utils.DateUtil;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: TaskServiceImpl.java
* @Description: 任务的service
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月6日 下午7:47:30 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月6日     GzpzG           v1.0.0               创建
 */
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	TaskMapper taskMapper;
	
	@Override
	public ResultBean<TaskCustom> insert(TaskCustom taskCustom) throws CustomException {
		//自动生成
		ResultBean<TaskCustom> resultBean = new ResultBean<TaskCustom>();
		Task task = new Task();
		task.setTaskContent(taskCustom.getTaskContent());
		task.setUserId(taskCustom.getUserId());
		task.setTaskCounts(taskCustom.getTaskCounts());
		task.setTaskCreatetime(new Date());
		if(taskCustom.getTaskEndtime().before(new Date()))
			throw new CustomException(102, "不能设置结束时间在当前时间之前");
		task.setTaskEndtime(taskCustom.getTaskEndtime());
		task.setTaskImg(taskCustom.getTaskImg());
		task.setTaskPrice(taskCustom.getTaskPrice());
		task.setTaskRestcounts(taskCustom.getTaskCounts());
		task.setTaskTitle(taskCustom.getTaskTitle());
		task.setTaskUserName(taskCustom.getTaskUserName());
		task.setTaskType(task.getTaskType());
		taskMapper.insertSelective(task);
		taskCustom = taskMapper.selectTaskSelective(task).get(0);
		resultBean.setResultData(taskCustom);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("创建任务成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<TaskCustom> delete(TaskCustom taskCustom) throws CustomException {
		ResultBean<TaskCustom> resultBean = new ResultBean<TaskCustom>();
		Task task = new Task();
		task = taskMapper.selectByPrimaryKey(taskCustom.getTaskId());
		if(task.getUserId()!=taskCustom.getUserId())
			throw new CustomException(103, "无权删除不属于你的任务");
		if(task.getTaskSchedule().equals(1))
			throw new CustomException(105, "任务正在进行无法删除");
		task.setTaskState(1);
		taskMapper.updateByPrimaryKeySelective(task);
		resultBean.setResultCode(999);
		return resultBean;
	}
	
	@Override
	public ResultBean<TaskCustom> update(TaskCustom taskCustom) throws CustomException {
		ResultBean<TaskCustom> resultBean = new ResultBean<TaskCustom>();
		Integer counts = null;
		Task task = new Task();
		task = taskMapper.selectByPrimaryKey(taskCustom.getTaskId());
		Integer alreceiveTask = task.getTaskCounts()-task.getTaskRestcounts();
		if(task.getUserId()!=taskCustom.getUserId())
			throw new CustomException(103, "无权修改不属于你的任务");
		if(task.getTaskSchedule()!=0)
			throw new CustomException(105, "任务已接受不能修改");
		if(DateUtil.MinuteNum(task.getTaskCreatetime(), new Date())>120)
			throw new CustomException(105, "任务已创建两小时不可修改");
		if(taskCustom.getTaskCounts()!=null){
			if(taskCustom.getTaskCounts()<alreceiveTask)
				throw new CustomException(105, "不能修改任务人数小于已接受人数");
			task.setTaskCounts(taskCustom.getTaskCounts());
			task.setTaskRestcounts(taskCustom.getTaskCounts()-alreceiveTask);
		}
		if(taskCustom.getTaskContent()!=null)
		    task.setTaskContent(taskCustom.getTaskContent());
		if(taskCustom.getTaskEndtime()!=null&&taskCustom.getTaskEndtime().before(new Date()))
			throw new CustomException(105, "不能设置结束时间在当前时间之前");
		task.setTaskEndtime(taskCustom.getTaskEndtime());
		task.setTaskImg(taskCustom.getTaskImg());
		task.setTaskPrice(taskCustom.getTaskPrice());
		task.setTaskTitle(taskCustom.getTaskTitle());
		task.setTaskUserName(taskCustom.getTaskUserName());
		task.setTaskType(task.getTaskType());
		taskMapper.updateByPrimaryKey(task);
		resultBean.setResultCode(999);
		return resultBean;
	}

	@Override
	public ResultBean<TaskCustom> getTaskSelective(TaskCustom taskCustom) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<TaskCustom> resultBean = new ResultBean<TaskCustom>();
		BeanUtils.copyProperties(taskMapper.selectTaskSelective(taskCustom).get(0), taskCustom);
		resultBean.setResultCode(999);
		resultBean.setResultData(taskCustom);
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<TaskCustom>> getTaskListSelective(TaskCustom taskCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<TaskCustom>> resultBean = new ResultBean<ResultList<TaskCustom>>();
		List<TaskCustom> customList = new ArrayList<TaskCustom>();
		List<TaskCustom> list = taskMapper.selectTaskListSelective(taskCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				TaskCustom taskCustom2 = new TaskCustom();
				BeanUtils.copyProperties(list.get(i), taskCustom2);
				customList.add(taskCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<TaskCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<TaskCustom>> getTaskListSelectiveByTime(TaskCustom taskCustom,
			Date startTime, Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<TaskCustom>> resultBean = new ResultBean<ResultList<TaskCustom>>();
		List<TaskCustom> customList = new ArrayList<TaskCustom>();
		List<TaskCustom> list = taskMapper.selectTaskListSelectiveByDate(endTime, startTime, taskCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				TaskCustom taskCustom2 = new TaskCustom();
				BeanUtils.copyProperties(list.get(i), taskCustom2);
				customList.add(taskCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<TaskCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}



}
