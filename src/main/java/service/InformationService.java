package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: InformationService.java
* @Description: 个人信息
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月3日 下午12:46:58 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月3日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import po.InformationCustom;
import utils.ResultBean;
import utils.ResultList;

public interface InformationService {
	//创建（用户注册时）
	void insert(Integer userId);
	//更新个人信息
	ResultBean<InformationCustom> update(InformationCustom informationCustom);
	//根据条件查找个人信息
	ResultBean<InformationCustom> getBySelective(InformationCustom informationCustom);
	//根据条件获取个人信息列表
	ResultBean<ResultList<InformationCustom>> getInformationListSelective
	(InformationCustom informationCustom,int pageSize,int pageNum);
	//根据时间+条件获取个人信息列表
	ResultBean<ResultList<InformationCustom>> getInformationListSelectiveByTime
	(InformationCustom informationCustom,Date startTime,Date endTime,int pageSize,int pageNum);
	
}
