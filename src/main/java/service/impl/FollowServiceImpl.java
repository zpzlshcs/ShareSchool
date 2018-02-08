package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.FollowMapper;
import po.Follow;
import po.FollowCustom;
import service.FollowService;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: FollowServiceImpl.java
* @Description: 关注
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月5日 下午11:07:41 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月5日     GzpzG           v1.0.0               创建
 */
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	FollowMapper followMapper;
	
	@Override
	public ResultBean<FollowCustom> insert(FollowCustom followCustom) throws CustomException {
		//自动生成
		ResultBean<FollowCustom> resultBean = new ResultBean<FollowCustom>();
		Follow follow = new Follow();
		follow.setUserId(followCustom.getUserId());
		follow.setFollowUserId(followCustom.getFollowUserId());
		List<FollowCustom> list = followMapper.selectFollowSelective(follow);
		if(list.isEmpty()){
			follow.setFollowCreatetime(new Date());
			followMapper.insertSelective(follow);
			followCustom = followMapper.selectFollowListSelective(follow).get(0);
		}else{
			followCustom = list.get(0);
			followCustom.setFollowState(0);
			followCustom.setFollowChangetime(new Date());
			followMapper.updateByPrimaryKeySelective(followCustom);
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(followCustom);
		resultBean.setResultMessage("关注成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<FollowCustom> delete(Integer followId) throws CustomException {
		//自动生成
		ResultBean<FollowCustom> resultBean = new ResultBean<FollowCustom>();
		Follow follow = new Follow();
		follow.setFollowId(followId);
		follow.setFollowChangetime(new Date());
		follow.setFollowState(1);
		followMapper.updateByPrimaryKeySelective(follow);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("取消关注成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<FollowCustom> getFollowSelective(FollowCustom followCustom) throws CustomException {
		//自动生成
		ResultBean<FollowCustom> resultBean = new ResultBean<FollowCustom>();
		resultBean.setResultData(followMapper.selectFollowSelective(followCustom).get(0));
		resultBean.setResultCode(999);
		resultBean.setResultMessage("取消关注成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<FollowCustom>> getFollowListSelective(FollowCustom followCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		//自动生成
		ResultBean<ResultList<FollowCustom>> resultBean = new ResultBean<ResultList<FollowCustom>>();
		List<FollowCustom> customList = new ArrayList<FollowCustom>();
		List<FollowCustom> list = followMapper.selectFollowListSelective(followCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				FollowCustom followCustom2 = new FollowCustom();
				BeanUtils.copyProperties(list.get(i), followCustom2);
				customList.add(followCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<FollowCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<FollowCustom>> getFollowListSelectiveByTime(FollowCustom followCustom, Date startTime,
			Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		//自动生成
		ResultBean<ResultList<FollowCustom>> resultBean = new ResultBean<ResultList<FollowCustom>>();
		List<FollowCustom> customList = new ArrayList<FollowCustom>();
		List<FollowCustom> list = followMapper.selectFollowListSelectiveByDate(endTime, startTime, followCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				FollowCustom followCustom2 = new FollowCustom();
				BeanUtils.copyProperties(list.get(i), followCustom2);
				customList.add(followCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<FollowCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}

}
