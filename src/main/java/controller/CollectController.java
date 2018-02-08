package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import exception.CustomException;
import filter.CheckToken;
import po.CollectCustom;
import po.UserCustom;
import service.CollectService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: CollectController.java
* @Description: 收藏的控制器
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月4日 下午10:32:37 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月4日     GzpzG           v1.0.0               创建
 */

@Controller
@RequestMapping(value="/collect",method=RequestMethod.POST)
public class CollectController {
	
	@Autowired
	CollectService collectService;
	
	@RequestMapping(value="/addCollect")
	@ResponseBody
	public ResultBean<CollectCustom> addCollect
	(@RequestBody CollectCustom collectCustom,HttpServletRequest request) throws Exception{	
		if(collectCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request, collectCustom.getUserId());
		if(collectCustom.getTaskId()==null)
			throw new CustomException(101, "任务id不能为空");
		return collectService.insert(collectCustom);
	}
	
	@RequestMapping(value="/cancelCollect")
	@ResponseBody
	public ResultBean<CollectCustom> cancelCollect
	(@RequestBody CollectCustom collectCustom,HttpServletRequest request) throws Exception{
		if(collectCustom.getCollectId()==null)
			throw new CustomException(101, "收藏collectId不能为空");
		if(collectCustom.getUserId()==null)
			throw new CustomException(101, "用户userId不能为空");
		CheckToken.check(request, collectCustom.getUserId());
		return collectService.cancel(collectCustom);
	}
	
	@RequestMapping(value="/remarkCollect")
	@ResponseBody
	public ResultBean<CollectCustom> remarkCollect
	(@RequestBody CollectCustom collectCustom,HttpServletRequest request) throws Exception{
		if(collectCustom.getCollectId()==null)
			throw new CustomException(101, "收藏collectId不能为空");
		if(collectCustom.getUserId()==null)
			throw new CustomException(101, "用户userId不能为空");
		if(collectCustom.getCollectRemarks()==null||collectCustom.getCollectRemarks().length()==0)
			throw new CustomException(101, "不能修改收藏备注为空");
		CheckToken.check(request, collectCustom.getUserId());
		return collectService.changeRemark(collectCustom);
	}
	
	@RequestMapping(value="/getCollect",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<CollectCustom> getCollect
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CollectCustom collect = new CollectCustom();
		if(map.containsKey("collectId"))
			collect.setCollectId(Integer.valueOf(map.get("collectId")));
		return collectService.getCollectSelective(collect);
	}
	
	
	
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<CollectCustom>> getList
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CollectCustom collect = new CollectCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			collect.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskId"))
			collect.setTaskId(Integer.valueOf(map.get("taskId")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		collect.setCollectState(0);
		return collectService.getCollectListSelective(collect,pageSize, pageNum);
	}
	
	@RequestMapping(value="/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<CollectCustom>> getListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CollectCustom collect = new CollectCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			collect.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskId"))
			collect.setTaskId(Integer.valueOf(map.get("taskId")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		collect.setCollectState(0);
		return collectService.getCollectListSelectiveByTime
				(collect, startTime, endTime, pageSize, pageNum);
	}
	
	
	@RequestMapping(value="/Collect/getCollect",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<CollectCustom> CollectgetCollect
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CollectCustom collect = new CollectCustom();
		if(map.containsKey("collectId"))
			collect.setCollectId(Integer.valueOf(map.get("collectId")));
		return collectService.getCollectSelective(collect);
	}
	
	@RequestMapping(value="/Collect/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<CollectCustom>> CollectgetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CollectCustom collect = new CollectCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			collect.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskId"))
			collect.setTaskId(Integer.valueOf(map.get("taskId")));
		if(map.containsKey("collectState"))
			collect.setCollectState(Integer.valueOf(map.get("collectState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return collectService.getCollectListSelective(collect,pageSize, pageNum);
	}
	
}
