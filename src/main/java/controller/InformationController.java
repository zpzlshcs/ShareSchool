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
import po.InformationCustom;
import service.InformationService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: InformationController.java
* @Description: 个人信息控制器
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月7日 下午3:19:37 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月7日     GzpzG           v1.0.0               创建
 */
public class InformationController {
	@Autowired
	InformationService infoService;
	
	@RequestMapping(value="/changeInfo",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<InformationCustom> changeInfo
	(@RequestBody InformationCustom informationCustom,HttpServletRequest request) throws Exception{
		if(informationCustom.getInfoId()==null)
			throw new CustomException(101, "个人信息id不能为空");
		if(informationCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request, informationCustom.getUserId());
		return infoService.update(informationCustom);
	}
	
	@RequestMapping(value="/getInfo",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<InformationCustom> getInformation
	(HttpServletRequest request) throws Exception{
		Map<String, String> map = RequestUtil.RequestToMap(request);
		InformationCustom info = new InformationCustom();
		if(map.containsKey("infoId"))
			info.setInfoId(Integer.valueOf(map.get("infoId")));
		return infoService.getInformationSelective(info);
	}
	
	
	@RequestMapping(value="/Infor/getInfo",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<InformationCustom> InformationgetInformation
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		InformationCustom info = new InformationCustom();
		if(map.containsKey("infoId"))
			info.setInfoId(Integer.valueOf(map.get("infoId")));
		return infoService.getInformationSelective(info);
	}
	
	@RequestMapping(value="/Info/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<InformationCustom>> InformationgetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		InformationCustom info = new InformationCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		if(map.containsKey("infoSchoolId"))
			info.setInfoSchoolId(Integer.valueOf(map.get("infoSchoolId")));
		if(map.containsKey("infoSex"))
			info.setInfoSex(Integer.valueOf(map.get("infoSex")));
		if(map.containsKey("infoNickname"))
			info.setInfoNickname(map.get("infoNickname"));
		if(map.containsKey("infoBirthday"))
			info.setInfoBirthday(format2.parse(map.get("infoBirthday")));
		if(map.containsKey("infoRealname"))
			info.setInfoRealname(map.get("infoRealname"));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return infoService.getInformationListSelective(info,pageSize, pageNum);
	}
	
	@RequestMapping(value="/Info/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<InformationCustom>> InformationgetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		InformationCustom info = new InformationCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		if(map.containsKey("infoSchoolId"))
			info.setInfoSchoolId(Integer.valueOf(map.get("infoSchoolId")));
		if(map.containsKey("infoSex"))
			info.setInfoSex(Integer.valueOf(map.get("infoSex")));
		if(map.containsKey("infoNickname"))
			info.setInfoNickname(map.get("infoNickname"));
		if(map.containsKey("infoBirthday"))
			info.setInfoBirthday(format2.parse(map.get("infoBirthday")));
		if(map.containsKey("infoRealname"))
			info.setInfoRealname(map.get("infoRealname"));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return infoService.getInformationListSelectiveByTime
				(info, startTime, endTime, pageSize, pageNum);
	}
}
