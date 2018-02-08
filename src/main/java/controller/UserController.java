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
import po.User;
import po.UserCustom;
import service.UserService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;
import utils.getListRequestBean;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<UserCustom> register
	(@RequestBody UserCustom user) throws Exception{
		if(user.getUserPhone()==null)
			throw new CustomException(101, "手机号不能为空");
		if(user.getUserPassword()==null)
			throw new CustomException(101, "密码不能为空");
		return userService.register(user);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<UserCustom> login
	(@RequestBody UserCustom user) throws Exception{
		if(user.getUserPhone()==null)
			throw new CustomException(101, "手机号不能为空");
		if(user.getUserPassword()==null)
			throw new CustomException(101, "密码不能为空");
		return userService.login(user.getUserPhone(), user.getUserPassword());
	}
	
	@RequestMapping(value="/getUser",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<UserCustom> getUser
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		UserCustom user = new UserCustom();
		if(map.containsKey("userPhone"))
			user.setUserPhone(map.get("userPhone"));
		if(map.containsKey("userId"))
			user.setUserId(Integer.valueOf(map.get("userId")));
		return userService.getUserSelective(user);
	}
	
	@RequestMapping(value="/user/getUser",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<UserCustom> userGetUser
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		UserCustom user = new UserCustom();
		if(map.containsKey("userPhone"))
			user.setUserPhone(map.get("userPhone"));
		if(map.containsKey("userId"))
			user.setUserId(Integer.valueOf(map.get("userId")));
		return userService.getUserSelective(user);
	}
	
	@RequestMapping(value="/user/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<UserCustom>> userGetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		UserCustom user = new UserCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userLevel"))
			user.setUserLevel(Integer.valueOf(map.get("userLevel")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return userService.getUserListSelective(user,pageSize, pageNum);
	}
	
	@RequestMapping(value="/user/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<UserCustom>> userGetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		UserCustom user = new UserCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userLevel"))
			user.setUserLevel(Integer.valueOf(map.get("userLevel")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return userService.getUserListSelectiveByTime
				(user, startTime, endTime, pageSize, pageNum);
	}
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String test
	(HttpServletRequest request) throws Exception{
		return request.getHeader("token");
	}
}
