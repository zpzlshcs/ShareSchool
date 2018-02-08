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
import po.FollowCustom;
import service.FollowService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: FollowController.java
* @Description: 关注的控制器
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月7日 下午2:57:22 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月7日     GzpzG           v1.0.0               创建
 */
@Controller
@RequestMapping("/follow")
public class FollowController {
	@Autowired
	FollowService followService;
	
	@RequestMapping(value="/addFollow",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<FollowCustom> addFollow
	(@RequestBody FollowCustom followCustom,HttpServletRequest request) throws Exception{
		if(followCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request,followCustom.getUserId());
		if(followCustom.getFollowUserId()==null)
			throw new CustomException(101, "被关注用户id不能为空");
		return followService.insert(followCustom);
	}
	
	@RequestMapping(value="/cancaelFollow",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<FollowCustom> cancaelFollow
	(@RequestBody FollowCustom followCustom,HttpServletRequest request) throws Exception{
		if(followCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request,followCustom.getUserId());
		if(followCustom.getFollowId()==null)
			throw new CustomException(101, "关注id不能为空");
		return followService.insert(followCustom);
	}
	
	@RequestMapping(value="/getFollow",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<FollowCustom> getFollow
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		FollowCustom follow = new FollowCustom();
		if(map.containsKey("followId"))
			follow.setFollowId(Integer.valueOf(map.get("followId")));
		return followService.getFollowSelective(follow);
	}
	
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<FollowCustom>> getList
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		FollowCustom follow = new FollowCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			follow.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("followUserId"))
			follow.setFollowUserId(Integer.valueOf(map.get("followUserId")));
		follow.setFollowState(0);
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return followService.getFollowListSelective(follow,pageSize, pageNum);
	}
	
	@RequestMapping(value="/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<FollowCustom>> getListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		FollowCustom follow = new FollowCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			follow.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("followUserId"))
			follow.setFollowUserId(Integer.valueOf(map.get("followUserId")));
		follow.setFollowState(0);
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return followService.getFollowListSelectiveByTime
				(follow, startTime, endTime, pageSize, pageNum);
	}
	
	
	@RequestMapping(value="/Follow/getFollow",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<FollowCustom> FollowgetFollow
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		FollowCustom follow = new FollowCustom();
		if(map.containsKey("followId"))
			follow.setFollowId(Integer.valueOf(map.get("followId")));
		return followService.getFollowSelective(follow);
	}
	
	@RequestMapping(value="/Follow/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<FollowCustom>> FollowgetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		FollowCustom follow = new FollowCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			follow.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("followUserId"))
			follow.setFollowUserId(Integer.valueOf(map.get("followUserId")));
		if(map.containsKey("followState"))
			follow.setFollowState(Integer.valueOf(map.get("followState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return followService.getFollowListSelective(follow,pageSize, pageNum);
	}
	
	@RequestMapping(value="/Follow/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<FollowCustom>> FollowgetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		FollowCustom follow = new FollowCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			follow.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("followUserId"))
			follow.setFollowUserId(Integer.valueOf(map.get("followUserId")));
		if(map.containsKey("followState"))
			follow.setFollowState(Integer.valueOf(map.get("followState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return followService.getFollowListSelectiveByTime
				(follow, startTime, endTime, pageSize, pageNum);
	}

}
