package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import exception.CustomException;
import filter.CheckToken;
import po.MessageCustom;
import service.MessageService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: MessageController.java
* @Description: 消息控制器
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月7日 下午3:35:45 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月7日     GzpzG           v1.0.0               创建
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<MessageCustom>> getList
	(HttpServletRequest request) throws Exception{
		Map<String, String> map = RequestUtil.RequestToMap(request);
		MessageCustom message = new MessageCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request,Integer.valueOf(map.get("usdrId")));
		if(map.containsKey("messageReceiveUserId"))
			message.setMessageReceiveUserId(Integer.valueOf(map.get("messageReceiveUserId")));
		if(map.containsKey("messageSendUserId"))
			message.setMessageSendUserId(Integer.valueOf(map.get("messageSendUserId")));
		if(!map.containsKey("messageReceiveUserId")&&!map.containsKey("messageSendUserId"))
			throw new CustomException(103, "不能获取与自己无关的消息");
		if(!(map.containsKey("messageReceiveUserId")&&map.get("userId")==map.get("messageReceiveUserId"))
				&&!(map.containsKey("messageSendUserId")&&map.get("userId")==map.get("messageSendUserId")))
			throw new CustomException(103, "不能获取与自己无关的消息");
		if(map.containsKey("messageReceiveState"))
			message.setMessageReceiveState(Integer.valueOf(map.get("messageReceiveState")));
		if(map.containsKey("messageType"))
			message.setMessageType(Integer.valueOf(map.get("messageType")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return messageService.getMessageListSelective(message,pageSize, pageNum);
	}
	
	@RequestMapping(value="/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<MessageCustom>> getListByTime
	(HttpServletRequest request) throws Exception{
		Map<String, String> map = RequestUtil.RequestToMap(request);
		MessageCustom message = new MessageCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request,Integer.valueOf(map.get("usdrId")));
		if(map.containsKey("messageReceiveUserId"))
			message.setMessageReceiveUserId(Integer.valueOf(map.get("messageReceiveUserId")));
		if(map.containsKey("messageSendUserId"))
			message.setMessageSendUserId(Integer.valueOf(map.get("messageSendUserId")));
		if(!map.containsKey("messageReceiveUserId")&&!map.containsKey("messageSendUserId"))
			throw new CustomException(103, "不能获取与自己无关的消息");
		if(!(map.containsKey("messageReceiveUserId")&&map.get("userId")==map.get("messageReceiveUserId"))
				&&!(map.containsKey("messageSendUserId")&&map.get("userId")==map.get("messageSendUserId")))
			throw new CustomException(103, "不能获取与自己无关的消息");
		if(map.containsKey("messageReceiveState"))
			message.setMessageReceiveState(Integer.valueOf(map.get("messageReceiveState")));
		if(map.containsKey("messageType"))
			message.setMessageType(Integer.valueOf(map.get("messageType")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return messageService.getMessageListSelectiveByTime
				(message, startTime, endTime, pageSize, pageNum);
	}
	
	
	@RequestMapping(value="/Message/getMessage",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<MessageCustom> MessagegetMessage
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		MessageCustom message = new MessageCustom();
		if(map.containsKey("messageId"))
			message.setMessageId(Integer.valueOf(map.get("messageId")));
		return messageService.getMessageSelective(message);
	}
	
	@RequestMapping(value="/Message/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<MessageCustom>> MessagegetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		MessageCustom message = new MessageCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("messageReceiveUserId"))
			message.setMessageReceiveUserId(Integer.valueOf(map.get("messageReceiveUserId")));
		if(map.containsKey("messageSendUserId"))
			message.setMessageSendUserId(Integer.valueOf(map.get("messageSendUserId")));
		if(map.containsKey("messageReceiveState"))
			message.setMessageReceiveState(Integer.valueOf(map.get("messageReceiveState")));
		if(map.containsKey("messageType"))
			message.setMessageType(Integer.valueOf(map.get("messageType")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return messageService.getMessageListSelective(message,pageSize, pageNum);
	}
	
	@RequestMapping(value="/Message/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<MessageCustom>> MessagegetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		MessageCustom message = new MessageCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("messageReceiveUserId"))
			message.setMessageReceiveUserId(Integer.valueOf(map.get("messageReceiveUserId")));
		if(map.containsKey("messageSendUserId"))
			message.setMessageSendUserId(Integer.valueOf(map.get("messageSendUserId")));
		if(map.containsKey("messageReceiveState"))
			message.setMessageReceiveState(Integer.valueOf(map.get("messageReceiveState")));
		if(map.containsKey("messageType"))
			message.setMessageType(Integer.valueOf(map.get("messageType")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return messageService.getMessageListSelectiveByTime
				(message, startTime, endTime, pageSize, pageNum);
	}
}
