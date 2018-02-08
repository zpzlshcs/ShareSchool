package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.CollectMapper;
import po.Collect;
import po.CollectCustom;
import service.CollectService;
import utils.DateUtil;
import utils.ResultBean;
import utils.ResultList;

public class CollectServiceImpl implements CollectService{
	
	@Autowired
	CollectMapper collectMapper;
	
	@Override
	public ResultBean<CollectCustom> insert(CollectCustom collectCustom) throws CustomException {
		ResultBean<CollectCustom> resultBean = new ResultBean<CollectCustom>();
		Collect collect = new Collect();
		collect.setCollectRemarks(collectCustom.getCollectRemarks());
		collect.setTaskId(collectCustom.getTaskId());
		collect.setUserId(collectCustom.getUserId());
		List<CollectCustom> list = collectMapper.selectCollectSelective(collectCustom);
		if(!list.isEmpty()){
			collectCustom = list.get(0);
			collectCustom.setCollectState(0);
			collectCustom.setCollectChangetime(new Date());
			collectMapper.updateByPrimaryKeySelective(collectCustom);
		}else{
			Collect record = new Collect();
			record.setCollectCreatetime(DateUtil.getDate());
			record.setTaskId(collectCustom.getTaskId());
			record.setUserId(collectCustom.getUserId());
			collectMapper.insertSelective(record);
			list = collectMapper.selectCollectSelective(record);
			collectCustom = list.get(0);
		}
		resultBean.setResultData(collectCustom);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("收藏成功");
		return resultBean;
	}


	@Override
	public ResultBean<CollectCustom> cancel(CollectCustom collectCustom) throws CustomException {
		ResultBean<CollectCustom> resultBean = new ResultBean<CollectCustom>();
		CollectCustom collect = new CollectCustom();
		collect.setCollectState(1);
		collect.setCollectId(collectCustom.getCollectId());
		collectMapper.updateByPrimaryKeySelective(collect);
		resultBean.setResultCode(999);
		resultBean.setResultData(collect);
		return resultBean;
	}
	

	@Override
	public ResultBean<CollectCustom> changeRemark(CollectCustom collectCustom) throws CustomException {
		ResultBean<CollectCustom> resultBean = new ResultBean<CollectCustom>();
		CollectCustom collect = new CollectCustom();
		collect.setCollectRemarks(collectCustom.getCollectRemarks());
		collect.setCollectId(collectCustom.getCollectId());
		collectMapper.updateByPrimaryKeySelective(collect);
		resultBean.setResultCode(999);
		resultBean.setResultData(collect);
		return resultBean;
	}
	
	@Override
	public ResultBean<CollectCustom> getCollectSelective(CollectCustom collectCustom) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<CollectCustom> resultBean = new ResultBean<CollectCustom>();
		BeanUtils.copyProperties(collectMapper.selectCollectSelective(collectCustom).get(0), collectCustom);
		resultBean.setResultCode(999);
		resultBean.setResultData(collectCustom);
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<CollectCustom>> getCollectListSelective(CollectCustom collectCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<CollectCustom>> resultBean = new ResultBean<ResultList<CollectCustom>>();
		List<CollectCustom> customList = new ArrayList<CollectCustom>();
		List<CollectCustom> list = collectMapper.selectCollectListSelective(collectCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				CollectCustom collectCustom2 = new CollectCustom();
				BeanUtils.copyProperties(list.get(i), collectCustom2);
				customList.add(collectCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<CollectCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<CollectCustom>> getCollectListSelectiveByTime(CollectCustom collectCustom,
			Date startTime, Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<CollectCustom>> resultBean = new ResultBean<ResultList<CollectCustom>>();
		List<CollectCustom> customList = new ArrayList<CollectCustom>();
		List<CollectCustom> list = collectMapper.selectCollectListSelectiveByDate(endTime, startTime, collectCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				CollectCustom collectCustom2 = new CollectCustom();
				BeanUtils.copyProperties(list.get(i), collectCustom2);
				customList.add(collectCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<CollectCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}


}
