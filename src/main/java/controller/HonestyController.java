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
import po.HonestyCustom;
import service.HonestyService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: HonestyController.java
* @Description: 诚信值控制器
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月7日 下午3:16:27 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月7日     GzpzG           v1.0.0               创建
 */
@Controller
@RequestMapping("/honesty")
public class HonestyController {
	@Autowired
	HonestyService honestyService;
	
	@RequestMapping(value="/getHonesty",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<HonestyCustom> getHonesty
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		HonestyCustom honesty = new HonestyCustom();
		if(map.containsKey("honestyId"))
			honesty.setHonestyId(Integer.valueOf(map.get("honestyId")));
		if(map.containsKey("userId"))
			honesty.setUserId(Integer.valueOf(map.get("userId")));
		return honestyService.getHonestySelective(honesty);
	}
	
	
	@RequestMapping(value="/Honesty/getHonesty",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<HonestyCustom> HonestygetHonesty
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		HonestyCustom honesty = new HonestyCustom();
		if(map.containsKey("honestyId"))
			honesty.setHonestyId(Integer.valueOf(map.get("honestyId")));
		if(map.containsKey("userId"))
			honesty.setUserId(Integer.valueOf(map.get("userId")));
		return honestyService.getHonestySelective(honesty);
	}
	
	@RequestMapping(value="/Honesty/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<HonestyCustom>> HonestygetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		HonestyCustom honesty = new HonestyCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return honestyService.getHonestyListSelective(honesty,pageSize, pageNum);
	}
	
	@RequestMapping(value="/Honesty/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<HonestyCustom>> HonestygetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		HonestyCustom honesty = new HonestyCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return honestyService.getHonestyListSelectiveByTime
				(honesty, startTime, endTime, pageSize, pageNum);
	}
}
