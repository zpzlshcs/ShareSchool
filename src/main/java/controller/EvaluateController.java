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
import po.EvaluateCustom;
import service.EvaluateService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: EvaluateController.java
* @Description: 评价的控制器
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
@RequestMapping("/evaluate")
public class EvaluateController {
	@Autowired
	EvaluateService evaluateService;
	
	@RequestMapping(value="/addEvaluate",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<EvaluateCustom> addEvaluate
	(@RequestBody EvaluateCustom evaluateCustom,HttpServletRequest request) throws Exception{
		if(evaluateCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request, evaluateCustom.getUserId());
		if(evaluateCustom.getOrderId()==null)
			throw new CustomException(101, "订单id不能为空");
		if(evaluateCustom.getEvaluateContent()==null||evaluateCustom.getEvaluateContent().length()==0)
			throw new CustomException(101, "评价内容不能为空");
		return evaluateService.insert1(evaluateCustom);
	}
	
	@RequestMapping(value="/addExtraEvaluate",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<EvaluateCustom> addExtraEvaluate
	(@RequestBody EvaluateCustom evaluateCustom,HttpServletRequest request) throws Exception{
		if(evaluateCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request, evaluateCustom.getUserId());
		if(evaluateCustom.getOrderId()==null)
			throw new CustomException(101, "订单id不能为空");
		if(evaluateCustom.getEvaluateContent()==null||evaluateCustom.getEvaluateContent().length()==0)
			throw new CustomException(101, "评价内容不能为空");
		return evaluateService.insert2(evaluateCustom);
	}
	
	@RequestMapping(value="/deleteEvaluate",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<EvaluateCustom> deleteEvaluate
	(@RequestBody EvaluateCustom evaluateCustom,HttpServletRequest request) throws Exception{
		if(evaluateCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request, evaluateCustom.getUserId());
		if(evaluateCustom.getEvaluateId()==null)
			throw new CustomException(101, "评价id不能为空");
		return evaluateService.insert2(evaluateCustom);
	}
	
	@RequestMapping(value="/getEvaluate",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<EvaluateCustom> getEvaluate
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		EvaluateCustom evaluate = new EvaluateCustom();
		if(map.containsKey("evaluateId"))
			evaluate.setEvaluateId(Integer.valueOf(map.get("evaluateId")));
		return evaluateService.getEvaluateSelective(evaluate);
	}
	
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<EvaluateCustom>> getList
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		EvaluateCustom evaluate = new EvaluateCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			evaluate.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("evaluateUserId"))
			evaluate.setEvaluateUserId(Integer.valueOf(map.get("evaluateUserId")));
		if(map.containsKey("orderId"))
			evaluate.setOrderId(Integer.valueOf(map.get("orderId")));
		evaluate.setEvaluateState(0);
		if(map.containsKey("evaluateType"))
			evaluate.setEvaluateType(Integer.valueOf(map.get("evaluateType")));
		if(map.containsKey("evaluateLevel"))
			evaluate.setEvaluateLevel(Integer.valueOf(map.get("evaluateLevel")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return evaluateService.getEvaluateListSelective(evaluate,pageSize, pageNum);
	}
	
	@RequestMapping(value="/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<EvaluateCustom>> getListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		EvaluateCustom evaluate = new EvaluateCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			evaluate.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("evaluateUserId"))
			evaluate.setEvaluateUserId(Integer.valueOf(map.get("evaluateUserId")));
		if(map.containsKey("orderId"))
			evaluate.setOrderId(Integer.valueOf(map.get("orderId")));
		evaluate.setEvaluateState(0);
		if(map.containsKey("evaluateType"))
			evaluate.setEvaluateType(Integer.valueOf(map.get("evaluateType")));
		if(map.containsKey("evaluateLevel"))
			evaluate.setEvaluateLevel(Integer.valueOf(map.get("evaluateLevel")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return evaluateService.getEvaluateListSelectiveByTime
				(evaluate, startTime, endTime, pageSize, pageNum);
	}
	
	
	@RequestMapping(value="/Evaluate/getEvaluate",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<EvaluateCustom> EvaluategetEvaluate
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		EvaluateCustom evaluate = new EvaluateCustom();
		if(map.containsKey("evaluateId"))
			evaluate.setEvaluateId(Integer.valueOf(map.get("evaluateId")));
		return evaluateService.getEvaluateSelective(evaluate);
	}
	
	@RequestMapping(value="/Evaluate/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<EvaluateCustom>> EvaluategetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		EvaluateCustom evaluate = new EvaluateCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			evaluate.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("evaluateUserId"))
			evaluate.setEvaluateUserId(Integer.valueOf(map.get("evaluateUserId")));
		if(map.containsKey("orderId"))
			evaluate.setOrderId(Integer.valueOf(map.get("orderId")));
		if(map.containsKey("evaluateState"))
			evaluate.setEvaluateState(Integer.valueOf(map.get("evaluateState")));
		if(map.containsKey("evaluateType"))
			evaluate.setEvaluateType(Integer.valueOf(map.get("evaluateType")));
		if(map.containsKey("evaluateLevel"))
			evaluate.setEvaluateLevel(Integer.valueOf(map.get("evaluateLevel")));
		if(map.containsKey("evaluateState"))
			evaluate.setEvaluateState(Integer.valueOf(map.get("evaluateState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return evaluateService.getEvaluateListSelective(evaluate,pageSize, pageNum);
	}
	
	@RequestMapping(value="/Evaluate/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<EvaluateCustom>> EvaluategetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		EvaluateCustom evaluate = new EvaluateCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			evaluate.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("evaluateUserId"))
			evaluate.setEvaluateUserId(Integer.valueOf(map.get("evaluateUserId")));
		if(map.containsKey("orderId"))
			evaluate.setOrderId(Integer.valueOf(map.get("orderId")));
		if(map.containsKey("evaluateState"))
			evaluate.setEvaluateState(Integer.valueOf(map.get("evaluateState")));
		if(map.containsKey("evaluateType"))
			evaluate.setEvaluateType(Integer.valueOf(map.get("evaluateType")));
		if(map.containsKey("evaluateLevel"))
			evaluate.setEvaluateLevel(Integer.valueOf(map.get("evaluateLevel")));
		if(map.containsKey("evaluateState"))
			evaluate.setEvaluateState(Integer.valueOf(map.get("evaluateState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return evaluateService.getEvaluateListSelectiveByTime
				(evaluate, startTime, endTime, pageSize, pageNum);
	}

}
