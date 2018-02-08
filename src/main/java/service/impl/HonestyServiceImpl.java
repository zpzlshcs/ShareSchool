package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.HonestyMapper;
import po.Honesty;
import po.HonestyCustom;
import service.HonestyService;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: HonestyServiceImpl.java
* @Description:诚信值的类
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月5日 下午11:37:05 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月5日     GzpzG           v1.0.0               创建
 */
public class HonestyServiceImpl implements HonestyService{
	
	@Autowired
	HonestyMapper honestyMapper;
	
	@Override
	public ResultBean<HonestyCustom> insert(Integer userId) throws CustomException {
		ResultBean<HonestyCustom> resultBean = new ResultBean<HonestyCustom>();
		Honesty honesty = new Honesty();
		honesty.setUserId(userId);
		honesty.setHonestyCreatetime(new Date());
		honestyMapper.insertSelective(honesty);
		resultBean.setResultData(honestyMapper.selectHonestySelective(honesty).get(0));
		resultBean.setResultCode(999);
		return resultBean;
	}
	
	@Override
	public ResultBean<HonestyCustom> changeScore(HonestyCustom honestyCustom) throws CustomException {
		//自动生成
		ResultBean<HonestyCustom> resultBean = new ResultBean<HonestyCustom>();
		Honesty honesty = new Honesty();
		honesty.setHonestyId(honestyCustom.getHonestyId());		
		honesty.setHonestyChangetime(new Date());
		honesty.setHonestyScore(honestyCustom.getHonestyScore());
		honestyMapper.updateByPrimaryKeySelective(honesty);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("修改成功");
		return resultBean;
	}

	@Override
	public ResultBean<HonestyCustom> getHonestySelective(HonestyCustom honestyCustom) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<HonestyCustom> resultBean = new ResultBean<HonestyCustom>();
		BeanUtils.copyProperties(honestyMapper.selectHonestySelective(honestyCustom).get(0), honestyCustom);
		resultBean.setResultCode(999);
		resultBean.setResultData(honestyCustom);
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<HonestyCustom>> getHonestyListSelective(HonestyCustom honestyCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<HonestyCustom>> resultBean = new ResultBean<ResultList<HonestyCustom>>();
		List<HonestyCustom> customList = new ArrayList<HonestyCustom>();
		List<HonestyCustom> list = honestyMapper.selectHonestyListSelective(honestyCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				HonestyCustom honestyCustom2 = new HonestyCustom();
				BeanUtils.copyProperties(list.get(i), honestyCustom2);
				customList.add(honestyCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<HonestyCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<HonestyCustom>> getHonestyListSelectiveByTime(HonestyCustom honestyCustom,
			Date startTime, Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<HonestyCustom>> resultBean = new ResultBean<ResultList<HonestyCustom>>();
		List<HonestyCustom> customList = new ArrayList<HonestyCustom>();
		List<HonestyCustom> list = honestyMapper.selectHonestyListSelectiveByDate(endTime, startTime, honestyCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				HonestyCustom honestyCustom2 = new HonestyCustom();
				BeanUtils.copyProperties(list.get(i), honestyCustom2);
				customList.add(honestyCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<HonestyCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	

}
