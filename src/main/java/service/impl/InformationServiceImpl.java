package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.InformationMapper;
import mapper.UserMapper;
import po.Information;
import po.InformationCustom;
import po.User;
import service.InformationService;
import utils.ResultBean;
import utils.ResultList;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: InformationServiceImpl.java
* @Description: 个人信息service
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月6日 下午4:07:54 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月6日     GzpzG           v1.0.0               创建
 */
public class InformationServiceImpl implements InformationService{
	
	@Autowired
	InformationMapper informationMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public ResultBean<InformationCustom> insert(Integer userId) throws CustomException {
		//自动生成
		ResultBean<InformationCustom> resultBean = new ResultBean<InformationCustom>();
		Information info = new Information();
		info.setUserId(userId);
		info.setInfoCreatetime(new Date());
		informationMapper.insertSelective(info);
		resultBean.setResultCode(999);
		return resultBean;
	}
	
	@Override
	public ResultBean<InformationCustom> update(InformationCustom informationCustom) throws CustomException {
		//自动生成
		ResultBean<InformationCustom> resultBean = new ResultBean<InformationCustom>();
		Information info = new Information();
		info = informationMapper.selectByPrimaryKey(informationCustom.getInfoId());
		if(info.getUserId()!=informationCustom.getUserId())
			throw new CustomException(103, "无权修改其他用户身份信息");
		info.setInfoId(informationCustom.getInfoId());
		info.setInfoBirthday(informationCustom.getInfoBirthday());
		info.setInfoChangetime(new Date());
		info.setInfoIconimg(informationCustom.getInfoIconimg());
		info.setInfoIntroduce(informationCustom.getInfoIntroduce());
		info.setInfoNickname(informationCustom.getInfoNickname());
		info.setInfoRealname(informationCustom.getInfoRealname());
		info.setInfoSchoolId(informationCustom.getInfoSchoolId());
		info.setInfoSex(informationCustom.getInfoSex());
		info.setInfoPlace(informationCustom.getInfoPlace());
		informationMapper.updateByPrimaryKeySelective(info);
		if(info.getInfoNickname()!=null){
			User user = new User();
			user.setUserId(informationCustom.getUserId());
			user.setUserName(info.getInfoNickname());
			userMapper.updateByPrimaryKeySelective(user);
		}
		resultBean.setResultCode(999);
		return resultBean;
	}

	@Override
	public ResultBean<InformationCustom> getInformationSelective(InformationCustom informationCustom) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<InformationCustom> resultBean = new ResultBean<InformationCustom>();
		BeanUtils.copyProperties(informationMapper.selectInformationSelective(informationCustom).get(0), informationCustom);
		resultBean.setResultCode(999);
		resultBean.setResultData(informationCustom);
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<InformationCustom>> getInformationListSelective(InformationCustom informationCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<InformationCustom>> resultBean = new ResultBean<ResultList<InformationCustom>>();
		List<InformationCustom> customList = new ArrayList<InformationCustom>();
		List<InformationCustom> list = informationMapper.selectInformationListSelective(informationCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				InformationCustom informationCustom2 = new InformationCustom();
				BeanUtils.copyProperties(list.get(i), informationCustom2);
				customList.add(informationCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<InformationCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<InformationCustom>> getInformationListSelectiveByTime(InformationCustom informationCustom,
			Date startTime, Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<InformationCustom>> resultBean = new ResultBean<ResultList<InformationCustom>>();
		List<InformationCustom> customList = new ArrayList<InformationCustom>();
		List<InformationCustom> list = informationMapper.selectInformationListSelectiveByDate(endTime, startTime, informationCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				InformationCustom informationCustom2 = new InformationCustom();
				BeanUtils.copyProperties(list.get(i), informationCustom2);
				customList.add(informationCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<InformationCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}




}
