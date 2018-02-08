package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: OrderService.java
* @Description: 订单
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月3日 下午1:10:35 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月3日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import exception.CustomException;
import po.OrderCustom;
import utils.ResultBean;
import utils.ResultList;

public interface OrderService {
	//创建订单
	ResultBean<OrderCustom> insert(OrderCustom orderCustom) throws CustomException;
	//支付方确认订单完成
	ResultBean<OrderCustom> launchConfirm(OrderCustom orderCustom) throws CustomException;
	//接受方确认完成
	ResultBean<OrderCustom> receiveConfirm(OrderCustom orderCustom) throws CustomException;
	//支付方删除订单
	ResultBean<OrderCustom> launchDelete(OrderCustom orderCustom) throws CustomException;
	//接受方删除订单
	ResultBean<OrderCustom> receiveDelete(OrderCustom orderCustom) throws CustomException;
	//支付方申请中止订单
	ResultBean<OrderCustom> launchCut(OrderCustom orderCustom) throws CustomException;
	//接受方申请中止订单
	ResultBean<OrderCustom> receiveCut(OrderCustom orderCustom) throws CustomException;
	
	//中止订单
	ResultBean<OrderCustom> cutOrder(Integer orderId) throws CustomException;
	
	//取消订单（下订单两个小时内，对诚信值有影响）
	ResultBean<OrderCustom> cancel(OrderCustom orderCustom) throws CustomException;
	
	//根据条件查找订单
	ResultBean<OrderCustom> getOrderSelective(OrderCustom OrderCustom) throws CustomException;
	//根据条件获取订单列表
	ResultBean<ResultList<OrderCustom>> getOrderListSelective
	(OrderCustom OrderCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取订单列表
	ResultBean<ResultList<OrderCustom>> getOrderListSelectiveByTime
	(OrderCustom OrderCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;
}
