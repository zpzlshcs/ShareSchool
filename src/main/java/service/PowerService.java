package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: PowerService.java
* @Description: 权限证书
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月3日 下午2:16:30 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月3日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import exception.CustomException;
import po.PowerCustom;
import utils.ResultBean;
import utils.ResultList;

public interface PowerService {

	//创建证书（登录创建证书）
	ResultBean<PowerCustom> insert(PowerCustom powerCustom) throws CustomException;
	//修改证书权限
	ResultBean<PowerCustom> changeLevel(PowerCustom powerCustom);
	//取消证书(让证书过期)
	ResultBean<PowerCustom> cutPower(PowerCustom powerCustom) throws CustomException;
	//延长证书使用期限
	ResultBean<PowerCustom> remakePower(String key,Date date) throws CustomException;
	//验证证书权限
	ResultBean<PowerCustom> checkPower(Integer userId,String key) throws CustomException;
	
	//根据条件查找证书
	ResultBean<PowerCustom> getBySelective(PowerCustom PowerCustom) throws CustomException;
	//根据条件获取证书列表
	ResultBean<ResultList<PowerCustom>> getPowerListSelective
	(PowerCustom PowerCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取证书列表
	ResultBean<ResultList<PowerCustom>> getPowerListSelectiveByTime
	(PowerCustom PowerCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;
	
}
