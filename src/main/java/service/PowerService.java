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

import po.PowerCustom;
import utils.ResultBean;
import utils.ResultList;

public interface PowerService {

	//创建证书（登录创建证书）
	ResultBean<PowerCustom> insert(PowerCustom powerCustom);
	//修改证书权限
	ResultBean<PowerCustom> changeLevel(Integer level);
	//取消证书(让证书过期)
	ResultBean<PowerCustom> cutPower(PowerCustom powerCustom);
	//延长证书使用期限
	ResultBean<PowerCustom> remakePower(String key,Date date);
	//验证证书权限
	ResultBean<PowerCustom> checkPower(Integer userId,String key);
	
	//根据条件查找证书
	ResultBean<PowerCustom> getBySelective(PowerCustom PowerCustom);
	//根据条件获取证书列表
	ResultBean<ResultList<PowerCustom>> getPowerListSelective
	(PowerCustom PowerCustom,int pageSize,int pageNum);
	//根据时间+条件获取证书列表
	ResultBean<ResultList<PowerCustom>> getPowerListSelectiveByTime
	(PowerCustom PowerCustom,Date startTime,Date endTime,int pageSize,int pageNum);
	
}
