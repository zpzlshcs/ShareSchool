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
import po.OrderCustom;
import service.OrderService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: OrderController.java
* @Description: 订单控制器
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月7日 下午3:45:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月7日     GzpzG           v1.0.0               创建
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/addOrder",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<OrderCustom> addOrder
	(@RequestBody OrderCustom orderCustom,HttpServletRequest request) throws Exception{
		if(orderCustom.getOrderLaunchUserId()==null)
			throw new CustomException(101, "支付方id不能为空");
		if(orderCustom.getOrderReceiveUserId()==null)
			throw new CustomException(101, "接受方id不能为空");
		CheckToken.check(request, orderCustom.getOrderReceiveUserId());
		if(orderCustom.getOrderTaskId()==null)
			throw new CustomException(101, "任务id不能为空");
		return orderService.getOrderSelective(orderCustom);
	}
	
	@RequestMapping(value="/launch/confirmOrder",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<OrderCustom> LaunchConfirmOrder
	(@RequestBody OrderCustom orderCustom,HttpServletRequest request) throws Exception{
		if(orderCustom.getOrderLaunchUserId()==null)
			throw new CustomException(101, "支付方id不能为空");
		CheckToken.check(request, orderCustom.getOrderLaunchUserId());
		if(orderCustom.getOrderId()==null)
			throw new CustomException(101, "订单id不能为空");
		return orderService.launchConfirm(orderCustom);
	}
	
	@RequestMapping(value="/launch/cutOrder",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<OrderCustom> LaunchCutOrder
	(@RequestBody OrderCustom orderCustom,HttpServletRequest request) throws Exception{
		if(orderCustom.getOrderLaunchUserId()==null)
			throw new CustomException(101, "支付方id不能为空");
		CheckToken.check(request, orderCustom.getOrderLaunchUserId());
		if(orderCustom.getOrderId()==null)
			throw new CustomException(101, "订单id不能为空");
		return orderService.launchCut(orderCustom);
	}
	
	@RequestMapping(value="/launch/deleteOrder",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<OrderCustom> LaunchDeleteOrder
	(@RequestBody OrderCustom orderCustom,HttpServletRequest request) throws Exception{
		if(orderCustom.getOrderReceiveUserId()==null)
			throw new CustomException(101, "支付方id不能为空");
		CheckToken.check(request, orderCustom.getOrderLaunchUserId());
		if(orderCustom.getOrderId()==null)
			throw new CustomException(101, "订单id不能为空");
		return orderService.launchDelete(orderCustom);
	}
	
	@RequestMapping(value="/receive/confirmOrder",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<OrderCustom> ReceiveConfirmOrder
	(@RequestBody OrderCustom orderCustom,HttpServletRequest request) throws Exception{
		if(orderCustom.getOrderReceiveUserId()==null)
			throw new CustomException(101, "接受方id不能为空");
		CheckToken.check(request, orderCustom.getOrderReceiveUserId());
		if(orderCustom.getOrderId()==null)
			throw new CustomException(101, "订单id不能为空");
		return orderService.receiveConfirm(orderCustom);
	}
	
	@RequestMapping(value="/receive/cutOrder",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<OrderCustom> ReceiveCutOrder
	(@RequestBody OrderCustom orderCustom,HttpServletRequest request) throws Exception{
		if(orderCustom.getOrderReceiveUserId()==null)
			throw new CustomException(101, "接受方id不能为空");
		CheckToken.check(request, orderCustom.getOrderReceiveUserId());
		if(orderCustom.getOrderId()==null)
			throw new CustomException(101, "订单id不能为空");
		return orderService.receiveCut(orderCustom);
	}
	
	@RequestMapping(value="/receive/deleteOrder",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<OrderCustom> ReceiveDeleteOrder
	(@RequestBody OrderCustom orderCustom,HttpServletRequest request) throws Exception{
		if(orderCustom.getOrderReceiveUserId()==null)
			throw new CustomException(101, "接受方id不能为空");
		CheckToken.check(request, orderCustom.getOrderReceiveUserId());
		if(orderCustom.getOrderId()==null)
			throw new CustomException(101, "订单id不能为空");
		return orderService.receiveDelete(orderCustom);
	}
	
	@RequestMapping(value="/order/cutOrder",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<OrderCustom> OrderCutOrder
	(@RequestBody OrderCustom orderCustom,HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		if(orderCustom.getOrderTaskId()==null)
			throw new CustomException(101, "任务id不能为空");
		return orderService.getOrderSelective(orderCustom);
	}
	
	@RequestMapping(value="/getOrder",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<OrderCustom> getOrder
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		OrderCustom order = new OrderCustom();
		if(map.containsKey("orderId"))
			order.setOrderId(Integer.valueOf(map.get("orderId")));
		return orderService.getOrderSelective(order);
	}
	
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<OrderCustom>> getList
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		OrderCustom order = new OrderCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("orderLaunchUserId"))
			order.setOrderLaunchUserId(Integer.valueOf(map.get("orderLaunchUserId")));
		if(map.containsKey("orderLaunchEvaluateState"))
			order.setOrderLaunchEvaluateState(Integer.valueOf(map.get("orderLaunchEvaluateState")));
		if(map.containsKey("orderLaunchSchedule"))
			order.setOrderLaunchSchedule(Integer.valueOf(map.get("orderLaunchSchedule")));
		if(map.containsKey("orderLaunchState"))
			order.setOrderLaunchState(Integer.valueOf(map.get("orderLaunchState")));
		if(map.containsKey("orderReceiveEvaluateState"))
			order.setOrderReceiveEvaluateState(Integer.valueOf(map.get("orderReceiveEvaluateState")));
		if(map.containsKey("orderReceiveUserId"))
			order.setOrderReceiveUserId(Integer.valueOf(map.get("orderReceiveUserId")));
		if(map.containsKey("orderReceiveSchedule"))
			order.setOrderReceiveSchedule(Integer.valueOf(map.get("orderReceiveSchedule")));
		if(map.containsKey("orderReceiveState"))
			order.setOrderReceiveState(Integer.valueOf(map.get("orderReceiveState")));
		if(map.containsKey("orderTaskId"))
			order.setOrderTaskId(Integer.valueOf(map.get("orderTaskId")));
		if(map.containsKey("orderState"))
			order.setOrderState(Integer.valueOf(map.get("orderState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return orderService.getOrderListSelective(order,pageSize, pageNum);
	}
	
	@RequestMapping(value="/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<OrderCustom>> getListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		OrderCustom order = new OrderCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("orderLaunchUserId"))
			order.setOrderLaunchUserId(Integer.valueOf(map.get("orderLaunchUserId")));
		if(map.containsKey("orderLaunchEvaluateState"))
			order.setOrderLaunchEvaluateState(Integer.valueOf(map.get("orderLaunchEvaluateState")));
		if(map.containsKey("orderLaunchSchedule"))
			order.setOrderLaunchSchedule(Integer.valueOf(map.get("orderLaunchSchedule")));
		if(map.containsKey("orderLaunchState"))
			order.setOrderLaunchState(Integer.valueOf(map.get("orderLaunchState")));
		if(map.containsKey("orderReceiveEvaluateState"))
			order.setOrderReceiveEvaluateState(Integer.valueOf(map.get("orderReceiveEvaluateState")));
		if(map.containsKey("orderReceiveUserId"))
			order.setOrderReceiveUserId(Integer.valueOf(map.get("orderReceiveUserId")));
		if(map.containsKey("orderReceiveSchedule"))
			order.setOrderReceiveSchedule(Integer.valueOf(map.get("orderReceiveSchedule")));
		if(map.containsKey("orderReceiveState"))
			order.setOrderReceiveState(Integer.valueOf(map.get("orderReceiveState")));
		if(map.containsKey("orderTaskId"))
			order.setOrderTaskId(Integer.valueOf(map.get("orderTaskId")));
		if(map.containsKey("orderState"))
			order.setOrderState(Integer.valueOf(map.get("orderState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return orderService.getOrderListSelectiveByTime
				(order, startTime, endTime, pageSize, pageNum);
	}
	
	@RequestMapping(value="/Order/cutOrder",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<OrderCustom> OrdercutOrder
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		OrderCustom order = new OrderCustom();
		if(map.containsKey("orderId"))
			order.setOrderId(Integer.valueOf(map.get("orderId")));
		else{
			throw new CustomException(101, "缺少要中止的订单orderId");
		}
		return orderService.cutOrder(Integer.valueOf(map.get("orderId")));
	}
	
	@RequestMapping(value="/Order/getOrder",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<OrderCustom> OrdergetOrder
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		OrderCustom order = new OrderCustom();
		if(map.containsKey("orderId"))
			order.setOrderId(Integer.valueOf(map.get("orderId")));
		return orderService.getOrderSelective(order);
	}
	
	@RequestMapping(value="/Order/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<OrderCustom>> OrdergetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		OrderCustom order = new OrderCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("orderLaunchUserId"))
			order.setOrderLaunchUserId(Integer.valueOf(map.get("orderLaunchUserId")));
		if(map.containsKey("orderLaunchEvaluateState"))
			order.setOrderLaunchEvaluateState(Integer.valueOf(map.get("orderLaunchEvaluateState")));
		if(map.containsKey("orderLaunchSchedule"))
			order.setOrderLaunchSchedule(Integer.valueOf(map.get("orderLaunchSchedule")));
		if(map.containsKey("orderLaunchState"))
			order.setOrderLaunchState(Integer.valueOf(map.get("orderLaunchState")));
		if(map.containsKey("orderReceiveEvaluateState"))
			order.setOrderReceiveEvaluateState(Integer.valueOf(map.get("orderReceiveEvaluateState")));
		if(map.containsKey("orderReceiveUserId"))
			order.setOrderReceiveUserId(Integer.valueOf(map.get("orderReceiveUserId")));
		if(map.containsKey("orderReceiveSchedule"))
			order.setOrderReceiveSchedule(Integer.valueOf(map.get("orderReceiveSchedule")));
		if(map.containsKey("orderReceiveState"))
			order.setOrderReceiveState(Integer.valueOf(map.get("orderReceiveState")));
		if(map.containsKey("orderTaskId"))
			order.setOrderTaskId(Integer.valueOf(map.get("orderTaskId")));
		if(map.containsKey("orderState"))
			order.setOrderState(Integer.valueOf(map.get("orderState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return orderService.getOrderListSelective(order,pageSize, pageNum);
	}
	
	@RequestMapping(value="/Order/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<OrderCustom>> OrdergetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		OrderCustom order = new OrderCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("orderLaunchUserId"))
			order.setOrderLaunchUserId(Integer.valueOf(map.get("orderLaunchUserId")));
		if(map.containsKey("orderLaunchEvaluateState"))
			order.setOrderLaunchEvaluateState(Integer.valueOf(map.get("orderLaunchEvaluateState")));
		if(map.containsKey("orderLaunchSchedule"))
			order.setOrderLaunchSchedule(Integer.valueOf(map.get("orderLaunchSchedule")));
		if(map.containsKey("orderLaunchState"))
			order.setOrderLaunchState(Integer.valueOf(map.get("orderLaunchState")));
		if(map.containsKey("orderReceiveEvaluateState"))
			order.setOrderReceiveEvaluateState(Integer.valueOf(map.get("orderReceiveEvaluateState")));
		if(map.containsKey("orderReceiveUserId"))
			order.setOrderReceiveUserId(Integer.valueOf(map.get("orderReceiveUserId")));
		if(map.containsKey("orderReceiveSchedule"))
			order.setOrderReceiveSchedule(Integer.valueOf(map.get("orderReceiveSchedule")));
		if(map.containsKey("orderReceiveState"))
			order.setOrderReceiveState(Integer.valueOf(map.get("orderReceiveState")));
		if(map.containsKey("orderTaskId"))
			order.setOrderTaskId(Integer.valueOf(map.get("orderTaskId")));
		if(map.containsKey("orderState"))
			order.setOrderState(Integer.valueOf(map.get("orderState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return orderService.getOrderListSelectiveByTime
				(order, startTime, endTime, pageSize, pageNum);
	}
}
