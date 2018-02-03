package controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import po.UserCustom;
import service.UserService;
import utils.ResultBean;
import utils.ResultList;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<UserCustom> register(@RequestBody UserCustom user,HttpServletRequest request) throws Exception{
		request.getSession();
		
		return userService.register(user);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<UserCustom> delete(@Param("user_id")Integer id) throws Exception{
		return userService.delete(id);
	}
	
	@RequestMapping(value="/selectSelective",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<UserCustom> selectSelective(@RequestBody UserCustom user) throws Exception{
		return userService.getUserSelective(user);
	}
	
	@RequestMapping(value="/getUserListSelective",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<ResultList<UserCustom>> getUserListSelective(@RequestBody UserCustom user,int pageSize,int pageNum) throws Exception{
		return userService.getUserListSelective(user, pageSize, pageNum);
	}
}
