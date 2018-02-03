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

import po.OrderCustom;
import utils.ResultBean;
import utils.ResultList;

public interface OrderService {
	//创建订单
	ResultBean<OrderCustom> insert(OrderCustom orderCustom);
	//支付方确认订单完成
	ResultBean<OrderCustom> launchConfirm(Integer orderId);
	//接受方确认完成
	ResultBean<OrderCustom> receiveConfirm(Integer orderId);
	//支付方删除订单
	ResultBean<OrderCustom> launchDelete(Integer orderId);
	//接受方删除订单
	ResultBean<OrderCustom> receiveDelete(Integer orderId);
	//中止订单
	ResultBean<OrderCustom> cutOrder(Integer orderId);
	
	
	//根据条件查找订单
	ResultBean<OrderCustom> getBySelective(OrderCustom OrderCustom);
	//根据条件获取订单列表
	ResultBean<ResultList<OrderCustom>> getOrderListSelective
	(OrderCustom OrderCustom,int pageSize,int pageNum);
	//根据时间+条件获取订单列表
	ResultBean<ResultList<OrderCustom>> getOrderListSelectiveByTime
	(OrderCustom OrderCustom,Date startTime,Date endTime,int pageSize,int pageNum);
}
