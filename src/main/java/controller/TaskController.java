package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import exception.CustomException;
import filter.CheckToken;
import po.TaskCustom;
import service.TaskService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: TaskController.java
* @Description: 任务控制器
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月7日 下午3:58:58 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月7日     GzpzG           v1.0.0               创建
 */
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@RequestMapping(value="/insertTask",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<TaskCustom> insertTask
	(@RequestBody TaskCustom taskCustom,HttpServletRequest request) throws Exception{
		if(taskCustom.getUserId()==null)
			throw new CustomException(101, "用户userId不能为空");
		CheckToken.check(request, taskCustom.getUserId());
		if(taskCustom.getTaskContent()==null)
			throw new CustomException(101, "任务taskContent内容不能为空");
		if(taskCustom.getTaskEndtime()==null)
			throw new CustomException(101, "任务结束时间endTime不能为空");
		if(taskCustom.getTaskUserName()==null)
			throw new CustomException(101, "用户名字userName不能为空");
		if(taskCustom.getTaskTitle()==null||taskCustom.getTaskTitle()=="")
			throw new CustomException(101, "任务标题taskTitle不能为空");
		return taskService.insert(taskCustom);
	}
	
	@RequestMapping(value="/changeTask",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<TaskCustom> changeTask
	(@RequestBody TaskCustom taskCustom,HttpServletRequest request) throws Exception{
		if(taskCustom.getUserId()==null)
			throw new CustomException(101, "用户userId不能为空");
		CheckToken.check(request, taskCustom.getUserId());
		if(taskCustom.getTaskId()==null)
			throw new CustomException(101, "任务taskId不能为空");
		return taskService.insert(taskCustom);
	}
	
	@RequestMapping(value="/deleteTask",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<TaskCustom> deleteTask
	(@RequestBody TaskCustom taskCustom,HttpServletRequest request) throws Exception{
		if(taskCustom.getUserId()==null)
			throw new CustomException(101, "用户userId不能为空");
		CheckToken.check(request, taskCustom.getUserId());
		if(taskCustom.getTaskId()==null)
			throw new CustomException(101, "任务taskId不能为空");
		return taskService.delete(taskCustom);
	}
	
	@RequestMapping(value="/getTask",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<TaskCustom> getTask
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		TaskCustom task = new TaskCustom();
		if(map.containsKey("taskId"))
			task.setTaskId(Integer.valueOf(map.get("taskId")));
		return taskService.getTaskSelective(task);
	}
	
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<TaskCustom>> getList
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		TaskCustom task = new TaskCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			task.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskSchedule"))
			task.setTaskSchedule(Integer.valueOf(map.get("taskSchedule")));
		if(map.containsKey("taskState"))
			task.setTaskState(Integer.valueOf(map.get("taskState")));
		if(map.containsKey("taskType"))
			task.setTaskType(Integer.valueOf(map.get("taskType")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return taskService.getTaskListSelective(task,pageSize, pageNum);
	}
	
	@RequestMapping(value="/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<TaskCustom>> getListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		TaskCustom task = new TaskCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			task.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskSchedule"))
			task.setTaskSchedule(Integer.valueOf(map.get("taskSchedule")));
		if(map.containsKey("taskState"))
			task.setTaskState(Integer.valueOf(map.get("taskState")));
		if(map.containsKey("taskType"))
			task.setTaskType(Integer.valueOf(map.get("taskType")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return taskService.getTaskListSelectiveByTime
				(task, startTime, endTime, pageSize, pageNum);
	}
	
	
	@RequestMapping(value="/Task/getTask",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<TaskCustom> TaskgetTask
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		TaskCustom task = new TaskCustom();
		if(map.containsKey("taskId"))
			task.setTaskId(Integer.valueOf(map.get("taskId")));
		return taskService.getTaskSelective(task);
	}
	
	@RequestMapping(value="/Task/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<TaskCustom>> TaskgetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		TaskCustom task = new TaskCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			task.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskSchedule"))
			task.setTaskSchedule(Integer.valueOf(map.get("taskSchedule")));
		if(map.containsKey("taskState"))
			task.setTaskState(Integer.valueOf(map.get("taskState")));
		if(map.containsKey("taskType"))
			task.setTaskType(Integer.valueOf(map.get("taskType")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return taskService.getTaskListSelective(task,pageSize, pageNum);
	}
	
	@RequestMapping(value="/Task/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<TaskCustom>> TaskgetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		TaskCustom task = new TaskCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			task.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskSchedule"))
			task.setTaskSchedule(Integer.valueOf(map.get("taskSchedule")));
		if(map.containsKey("taskState"))
			task.setTaskState(Integer.valueOf(map.get("taskState")));
		if(map.containsKey("taskType"))
			task.setTaskType(Integer.valueOf(map.get("taskType")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return taskService.getTaskListSelectiveByTime
				(task, startTime, endTime, pageSize, pageNum);
	}
}
