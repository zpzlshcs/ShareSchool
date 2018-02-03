package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: HonestyService.java
* @Description: 用户诚信值
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月2日 下午10:05:09 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月2日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import po.HonestyCustom;
import utils.ResultBean;
import utils.ResultList;

public interface HonestyService {
	//新增（用户注册时创建）
	void insert(Integer userId);
	//修改分数
	void changeScore(double honestyScore);
	//根据条件查找诚信
	ResultBean<HonestyCustom> getBySelective(HonestyCustom honestyCustom);
	//根据条件获取诚信列表
	ResultBean<ResultList<HonestyCustom>> getHonestyListSelective
	(HonestyCustom honestyCustom,int pageSize,int pageNum);
	//根据时间+条件获取诚信列表
	ResultBean<ResultList<HonestyCustom>> getHonestyListSelectiveByTime
	(HonestyCustom honestyCustom,Date startTime,Date endTime,int pageSize,int pageNum);
}
