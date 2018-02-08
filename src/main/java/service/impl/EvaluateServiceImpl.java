package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.EvaluateMapper;
import mapper.OrderMapper;
import po.Evaluate;
import po.EvaluateCustom;
import po.Order;
import service.EvaluateService;
import utils.ResultBean;
import utils.ResultList;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: EvaluateServiceImpl.java
* @Description: 评价的service
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月5日 下午7:51:54 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月5日     GzpzG           v1.0.0               创建
 */
public class EvaluateServiceImpl implements EvaluateService{
	

	@Autowired
	EvaluateMapper evaluateMapper;
	
	@Autowired
	OrderMapper orderMapper;

	
	@Override
	public ResultBean<EvaluateCustom> insert1(EvaluateCustom evaluateCustom) throws CustomException {
		//添加评价
		ResultBean<EvaluateCustom> resultBean = new ResultBean<EvaluateCustom>();
		Evaluate evaluate = new Evaluate();
		Order order = orderMapper.selectByPrimaryKey(evaluateCustom.getOrderId());
		if(!order.getOrderState().equals(1))
			throw new CustomException(105, "该订单尚未完成");
		else if(order.getOrderLaunchUserId().equals(evaluateCustom.getOrderLaunchUserId())){
			if(!order.getOrderLaunchState().equals(0))
				throw new CustomException(105, "该订单已评价不可再次评价");
			evaluate.setEvaluateCreatetime(new Date());
			evaluate.setEvaluateContent(evaluateCustom.getEvaluateContent());
			evaluate.setEvaluateLevel(1);
			evaluate.setEvaluateLevel(evaluateCustom.getEvaluateLevel());
			evaluate.setUserId(evaluateCustom.getUserId());
			evaluate.setEvaluateUserId(evaluateCustom.getEvaluateUserId());
			evaluateMapper.insertSelective(evaluate);
			order = new Order();
			order.setOrderId(evaluateCustom.getOrderId());
			order.setOrderLaunchEvaluateState(1);
			orderMapper.updateByPrimaryKeySelective(order);
		}
		else if(order.getOrderReceiveUserId().equals(evaluateCustom.getOrderReceiveUserId())){
			if(!order.getOrderLaunchState().equals(0))
				throw new CustomException(105, "该订单已评价不可再次评价");
			evaluate.setEvaluateCreatetime(new Date());
			evaluate.setEvaluateContent(evaluateCustom.getEvaluateContent());
			evaluate.setEvaluateLevel(1);
			evaluate.setEvaluateLevel(evaluateCustom.getEvaluateLevel());
			evaluate.setUserId(evaluateCustom.getUserId());
			evaluate.setEvaluateUserId(evaluateCustom.getEvaluateUserId());
			evaluateMapper.insertSelective(evaluate);
			order = new Order();
			order.setOrderId(evaluateCustom.getOrderId());
			order.setOrderReceiveEvaluateState(1);
			orderMapper.updateByPrimaryKeySelective(order);
		}else{
			throw new CustomException(105, "该用户无权评价这条订单");
		}
		evaluateCustom = evaluateMapper.selectEvaluateListSelective(evaluate).get(0);
		afterEvaluate(evaluateCustom);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("创建评价成功");
		resultBean.setResultData(evaluateCustom);
		return resultBean;
	}
	
	@Override
	public ResultBean<EvaluateCustom> insert2(EvaluateCustom evaluateCustom) throws CustomException {
		//添加追评
		ResultBean<EvaluateCustom> resultBean = new ResultBean<EvaluateCustom>();
		Evaluate evaluate = new Evaluate();
		Order order = orderMapper.selectByPrimaryKey(evaluateCustom.getOrderId());
		if(!order.getOrderState().equals(1))
			throw new CustomException(105, "该订单尚未完成");
		else if(order.getOrderLaunchUserId().equals(evaluateCustom.getOrderLaunchUserId())){
			if(!order.getOrderLaunchState().equals(1))
				throw new CustomException(103, "该订单暂不可追评");
			evaluate.setEvaluateCreatetime(new Date());
			evaluate.setEvaluateContent(evaluateCustom.getEvaluateContent());
			evaluate.setEvaluateLevel(1);
			evaluate.setEvaluateLevel(evaluateCustom.getEvaluateLevel());
			evaluate.setUserId(evaluateCustom.getUserId());
			evaluate.setEvaluateUserId(evaluateCustom.getEvaluateUserId());
			evaluateMapper.insertSelective(evaluate);
			order = new Order();
			order.setOrderId(evaluateCustom.getOrderId());
			order.setOrderLaunchEvaluateState(2);
			orderMapper.updateByPrimaryKeySelective(order);
		}
		else if(order.getOrderReceiveUserId().equals(evaluateCustom.getOrderReceiveUserId())){
			if(!order.getOrderReceiveState().equals(1))
				throw new CustomException(105, "该订单暂不可追评");
			evaluate.setEvaluateCreatetime(new Date());
			evaluate.setEvaluateContent(evaluateCustom.getEvaluateContent());
			evaluate.setEvaluateLevel(1);
			evaluate.setEvaluateLevel(evaluateCustom.getEvaluateLevel());
			evaluate.setUserId(evaluateCustom.getUserId());
			evaluate.setEvaluateUserId(evaluateCustom.getEvaluateUserId());
			evaluateMapper.insertSelective(evaluate);
			order = new Order();
			order.setOrderId(evaluateCustom.getOrderId());
			order.setOrderReceiveEvaluateState(2);
			orderMapper.updateByPrimaryKeySelective(order);
		}else{
			throw new CustomException(103, "该用户无权追评这条订单");
		}
		evaluateCustom = evaluateMapper.selectEvaluateListSelective(evaluate).get(0);
		afterEvaluate(evaluateCustom);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("创建追评成功");
		resultBean.setResultData(evaluateCustom);
		return resultBean;
	}
	
	@Override
	public ResultBean<EvaluateCustom> delete(EvaluateCustom evaluateCustom) throws CustomException {
		ResultBean<EvaluateCustom> resultBean = new ResultBean<EvaluateCustom>();
		Evaluate evaluate = new Evaluate();
		evaluate = evaluateMapper.selectByPrimaryKey(evaluateCustom.getEvaluateId());
		if(evaluate.getUserId()!=evaluateCustom.getUserId())
			throw new CustomException(103, "该用户无权删除这条评价");
		evaluate.setEvaluateState(1);
		evaluateMapper.updateByPrimaryKeySelective(evaluate);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("删除评价成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<EvaluateCustom> getEvaluateSelective(EvaluateCustom evaluateCustom) throws CustomException {
		// 2018年2月5日 下午10:39:20 
		ResultBean<EvaluateCustom> resultBean = new ResultBean<EvaluateCustom>();
		List<EvaluateCustom> list = evaluateMapper.selectEvaluateSelective(evaluateCustom);
		resultBean.setResultCode(999);
		resultBean.setResultData(list.get(0));
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<EvaluateCustom>> getEvaluateListSelective(EvaluateCustom evaluateCustom,
			Integer pageSize, Integer pageNum) throws CustomException {
		ResultBean<ResultList<EvaluateCustom>> resultBean = new ResultBean<ResultList<EvaluateCustom>>();
		List<EvaluateCustom> customList = new ArrayList<EvaluateCustom>();
		List<EvaluateCustom> list = evaluateMapper.selectEvaluateListSelective(evaluateCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				EvaluateCustom evaluateCustom2 = new EvaluateCustom();
				BeanUtils.copyProperties(list.get(i), evaluateCustom2);
				
				customList.add(evaluateCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<EvaluateCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<EvaluateCustom>> getEvaluateListSelectiveByTime(EvaluateCustom evaluateCustom,
			Date startTime, Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		ResultBean<ResultList<EvaluateCustom>> resultBean = new ResultBean<ResultList<EvaluateCustom>>();
		List<EvaluateCustom> customList = new ArrayList<EvaluateCustom>();
		List<EvaluateCustom> list = evaluateMapper.selectEvaluateListSelectiveByDate(endTime, startTime, evaluateCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				EvaluateCustom evaluateCustom2 = new EvaluateCustom();
				BeanUtils.copyProperties(list.get(i), evaluateCustom2);
				
				customList.add(evaluateCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<EvaluateCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	//好评提高被评人与评人诚信值
	//差评降低被评人诚信值
	private void afterEvaluate(EvaluateCustom evaluateCustom){
		if(evaluateCustom.getEvaluateLevel().equals(1)){
			
		}else if(evaluateCustom.getEvaluateLevel().equals(3)){
			
		}
	}
	
}
