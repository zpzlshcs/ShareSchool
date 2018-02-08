package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.OrderMapper;
import mapper.TaskMapper;
import po.Order;
import po.OrderCustom;
import po.Task;
import service.OrderService;
import utils.DateUtil;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: OrderServiceImpl.java
* @Description: 订单service
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月6日 下午4:24:05 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月6日     GzpzG           v1.0.0               创建
 */
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	TaskMapper taskMapper;
	
	@Override
	public ResultBean<OrderCustom> insert(OrderCustom orderCustom) throws CustomException {
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		Task task = new Task();
		task.setTaskId(orderCustom.getOrderTaskId());
		task = taskMapper.selectByPrimaryKey(orderCustom.getOrderTaskId());
		if(task.getTaskRestcounts()==0)
			throw new CustomException(102, "该任务接取已达到上限");
		Order order = new Order();
		order.setOrderCreatetime(new Date());
		order.setOrderDescribe(orderCustom.getOrderDescribe());
		order.setOrderLaunchUserId(task.getUserId());
		order.setOrderPrice(task.getTaskPrice());
		order.setOrderReceiveUserId(orderCustom.getOrderReceiveUserId());
		order.setOrderTaskId(orderCustom.getOrderTaskId());
		orderMapper.insertSelective(order);
		task.setTaskRestcounts(task.getTaskRestcounts()-1);
		taskMapper.updateByPrimaryKeySelective(task);
		resultBean.setResultData(orderMapper.selectOrderSelective(order).get(0));
		resultBean.setResultCode(999);
		return resultBean;
	}

	
	
	@Override
	public ResultBean<OrderCustom> launchConfirm(OrderCustom orderCustom) throws CustomException {
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		OrderCustom order = new OrderCustom();
		order.setOrderId(orderCustom.getOrderId());
		order = orderMapper.selectOrderSelective(orderCustom).get(0);
		if(order.getOrderState().equals(2))
			throw new CustomException(105, "订单已被终止");
		else if(!order.getOrderLaunchSchedule().equals(0))
			throw new CustomException(105, "支付方订单状态异常");
		else if(order.getOrderReceiveSchedule().equals(2))
			throw new CustomException(105, "接受方申请中止订单");
		else{
			order.setOrderLaunchSchedule(1);
			if(!order.getOrderLaunchUserId().equals(orderCustom.getOrderLaunchUserId()))
				throw new CustomException(103, "用户无权确认该订单");
			if(order.getOrderReceiveSchedule().equals(1)){
				order.setOrderState(1);
				order.setOrderCompleteTime(new Date());
				orderMapper.updateByPrimaryKeySelective(order);
				completeOrderAfter(order);
			}else orderMapper.updateByPrimaryKeySelective(order);
			
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(orderCustom);
		return resultBean;
	}
	
	@Override
	public ResultBean<OrderCustom> receiveConfirm(OrderCustom orderCustom) throws CustomException {
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		OrderCustom order = new OrderCustom();
		order.setOrderId(orderCustom.getOrderId());
		order = orderMapper.selectOrderSelective(orderCustom).get(0);
		if(order.getOrderState().equals(2))
			throw new CustomException(105, "订单已被终止");
		else if(!order.getOrderReceiveSchedule().equals(0))
			throw new CustomException(105, "接受方订单状态异常");
		else if(order.getOrderLaunchSchedule().equals(2))
			throw new CustomException(105, "支付方申请中止订单");
		else if(order.getTaskEndtime().before(new Date()))
			throw new CustomException(105, "任务时间已经结束不可更新订单");
		else{
			if(!order.getOrderReceiveUserId().equals(orderCustom.getOrderReceiveUserId()))
				throw new CustomException(103, "用户无权确认该订单");
			order.setOrderReceiveSchedule(1);
			if(order.getOrderLaunchSchedule().equals(1)){
				order.setOrderState(1);
				order.setOrderCompleteTime(new Date());
				orderMapper.updateByPrimaryKeySelective(order);
				completeOrderAfter(order);
			}else orderMapper.updateByPrimaryKeySelective(order);
		}
		resultBean.setResultCode(999);
		return resultBean;
	}
	
	@Override
	public ResultBean<OrderCustom> launchDelete(OrderCustom orderCustom) throws CustomException {
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		Order order = new Order();
		order.setOrderId(orderCustom.getOrderId());
		order = orderMapper.selectOrderSelective(orderCustom).get(0);
		if(order.getOrderState().equals(0))
			throw new CustomException(105, "订单未完成，不能删除");
		if(!order.getOrderLaunchUserId().equals(orderCustom.getOrderLaunchUserId()))
			throw new CustomException(103, "用户无权删除该订单");
		order.setOrderLaunchState(1);
		orderMapper.updateByPrimaryKeySelective(order);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("删除成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<OrderCustom> receiveDelete(OrderCustom orderCustom) throws CustomException {
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		Order order = new Order();
		order.setOrderId(orderCustom.getOrderId());
		order = orderMapper.selectOrderSelective(orderCustom).get(0);
		if(order.getOrderState().equals(0))
			throw new CustomException(105, "订单未完成，不能删除");
		if(!order.getOrderReceiveUserId().equals(orderCustom.getOrderReceiveUserId()))
			throw new CustomException(103, "用户无权删除该订单");
		order.setOrderReceiveState(1);
		orderMapper.updateByPrimaryKeySelective(order);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("删除成功");
		return resultBean;
	}	
	@Override
	public ResultBean<OrderCustom> launchCut(OrderCustom orderCustom) throws CustomException {
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		Order order = new Order();
		order.setOrderId(orderCustom.getOrderId());
		order = orderMapper.selectOrderSelective(orderCustom).get(0);
		if(order.getOrderState().equals(2))
			throw new CustomException(105, "订单已被终止");
		else if(order.getOrderLaunchSchedule().equals(2))
			throw new CustomException(105, "支付方已申请中止");
		else{
			if(!order.getOrderLaunchUserId().equals(orderCustom.getOrderLaunchUserId()))
				throw new CustomException(103, "用户无权中止订单");
			order.setOrderLaunchSchedule(2);
			if(order.getOrderReceiveSchedule().equals(2)){
				order.setOrderState(2);
				order.setOrderCompleteTime(new Date());
			}
			orderMapper.updateByPrimaryKeySelective(order);
		}
		resultBean.setResultCode(999);
		return resultBean;
	}
	
	@Override
	public ResultBean<OrderCustom> receiveCut(OrderCustom orderCustom) throws CustomException {
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		OrderCustom order = new OrderCustom();
		order.setOrderId(orderCustom.getOrderId());
		order = orderMapper.selectOrderSelective(orderCustom).get(0);
		if(order.getOrderState().equals(2))
			throw new CustomException(105, "订单已被终止");
		else if(order.getOrderReceiveSchedule().equals(2))
			throw new CustomException(105, "接受方已申请中止");
		else if(order.getTaskEndtime().before(new Date()))
			throw new CustomException(105, "任务时间已经结束不可更新订单");
		else{
			if(!order.getOrderReceiveUserId().equals(orderCustom.getOrderReceiveUserId()))
				throw new CustomException(103, "用户无权中止该订单");
			order.setOrderReceiveSchedule(2);
			if(order.getOrderLaunchSchedule().equals(2)){
				order.setOrderState(2);
				order.setOrderCompleteTime(new Date());
			}
			orderMapper.updateByPrimaryKeySelective(order);
		}
		resultBean.setResultCode(999);
		return resultBean;
	}
	
	@Override
	public ResultBean<OrderCustom> cutOrder(Integer orderId) throws CustomException {
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		OrderCustom orderCustom = new OrderCustom();
		orderCustom.setOrderId(orderId);
		orderCustom = orderMapper.selectOrderSelective(orderCustom).get(0);
		if(orderCustom.getOrderState().equals(2))
			throw new CustomException(105, "订单已被终止");
		else{
			orderCustom.setOrderState(2);
			orderMapper.updateByPrimaryKeySelective(orderCustom);
			setTaskRestCounts(orderCustom.getOrderTaskId());
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(orderCustom);
		return resultBean;
	}
	
	
	@Override
	public ResultBean<OrderCustom> cancel(OrderCustom orderCustom) throws CustomException {
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		orderCustom.setOrderId(orderCustom.getOrderId());
		Order order = orderMapper.selectOrderSelective(orderCustom).get(0);
		if(order.getOrderState()==1)
			throw new CustomException(105, "该订单已完成不可取消");
		else if(DateUtil.MinuteNum(order.getOrderCreatetime(), new Date())>120)
			throw new CustomException(105, "订单创建两小时后不可取消");
		else if(order.getOrderReceiveUserId().equals(orderCustom.getOrderReceiveUserId())){
			if(order.getOrderLaunchState().equals(1))
				throw new CustomException(105, "任务支付方方已确认完成，不可取消订单");
			else{
				order.setOrderState(2);
				orderMapper.updateByPrimaryKeySelective(order);
				setTaskRestCounts(order.getOrderTaskId());
				setHonesty(orderCustom.getOrderReceiveUserId());
			}
		}else{
			throw new CustomException(105, "无权取消该订单");
		}
		resultBean.setResultCode(999);
		return resultBean;
	}
	
	@Override
	public ResultBean<OrderCustom> getOrderSelective(OrderCustom orderCustom) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<OrderCustom> resultBean = new ResultBean<OrderCustom>();
		BeanUtils.copyProperties(orderMapper.selectOrderSelective(orderCustom).get(0), orderCustom);
		resultBean.setResultCode(999);
		resultBean.setResultData(orderCustom);
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<OrderCustom>> getOrderListSelective(OrderCustom orderCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<OrderCustom>> resultBean = new ResultBean<ResultList<OrderCustom>>();
		List<OrderCustom> customList = new ArrayList<OrderCustom>();
		List<OrderCustom> list = orderMapper.selectOrderListSelective(orderCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				OrderCustom orderCustom2 = new OrderCustom();
				BeanUtils.copyProperties(list.get(i), orderCustom2);
				customList.add(orderCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<OrderCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<OrderCustom>> getOrderListSelectiveByTime(OrderCustom orderCustom,
			Date startTime, Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<OrderCustom>> resultBean = new ResultBean<ResultList<OrderCustom>>();
		List<OrderCustom> customList = new ArrayList<OrderCustom>();
		List<OrderCustom> list = orderMapper.selectOrderListSelectiveByDate(endTime, startTime, orderCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				OrderCustom orderCustom2 = new OrderCustom();
				BeanUtils.copyProperties(list.get(i), orderCustom2);
				customList.add(orderCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<OrderCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}

	//取消订单降低诚信值
	private void setHonesty(Integer userId){
		
	}
	
	//完成订单增加诚信值并确认任务是否完成
	private void completeOrderAfter(Order order){
		highHonesty(order.getOrderLaunchUserId());
		highHonesty(order.getOrderReceiveUserId());
		
		Task task = new Task();
		task = taskMapper.selectByPrimaryKey(order.getOrderTaskId());
		if(task.getTaskRestcounts()==task.getTaskCounts()){
			task.setTaskSchedule(2);
			taskMapper.updateByPrimaryKeySelective(task);
		}
	}
	//增加诚信值
	private void highHonesty(Integer userId){
		
	}

	//任务剩余人数加1
	private void setTaskRestCounts(Integer taskId) throws CustomException{
		Task task = new Task();
		task = taskMapper.selectByPrimaryKey(taskId);
		if(task.getTaskRestcounts()>=task.getTaskCounts())
			throw new CustomException(105, "订单剩余人数达到上限");
		task.setTaskRestcounts(task.getTaskRestcounts()+1);
		if(task.getTaskRestcounts()==task.getTaskCounts())
			task.setTaskSchedule(0);
		taskMapper.updateByPrimaryKeySelective(task);
	}

	//任务剩余人数减1
	private void setTaskRestCounts2(Integer taskId) throws CustomException{
		Task task = new Task();
		task = taskMapper.selectByPrimaryKey(taskId);
		if(task.getTaskRestcounts()<=0)
			throw new CustomException(105, "订单剩余人数达到下限");
		task.setTaskSchedule(1);
		task.setTaskRestcounts(task.getTaskRestcounts()-1);
		taskMapper.updateByPrimaryKeySelective(task);
	}

	

}
