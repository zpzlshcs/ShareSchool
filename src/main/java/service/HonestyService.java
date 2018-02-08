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

import exception.CustomException;
import po.HonestyCustom;
import utils.ResultBean;
import utils.ResultList;

public interface HonestyService {
	//新增（用户注册时创建）
	ResultBean<HonestyCustom> insert(Integer userId) throws CustomException;
	//修改分数
	ResultBean<HonestyCustom> changeScore(HonestyCustom honestyCustom) throws CustomException;
	//根据条件查找诚信
	ResultBean<HonestyCustom> getHonestySelective(HonestyCustom honestyCustom) throws CustomException;
	//根据条件获取诚信列表
	ResultBean<ResultList<HonestyCustom>> getHonestyListSelective
	(HonestyCustom honestyCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取诚信列表
	ResultBean<ResultList<HonestyCustom>> getHonestyListSelectiveByTime
	(HonestyCustom honestyCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;
}
