package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: CollectService.java
* @Description: 收藏的service
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月2日 下午9:09:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月2日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import exception.CustomException;
import po.CollectCustom;
import utils.ResultBean;
import utils.ResultList;

public interface CollectService {
	 //添加收藏
	ResultBean<CollectCustom> insert(CollectCustom collectCustom) throws CustomException;
	//取消收藏
	ResultBean<CollectCustom> cancel(CollectCustom collectCustom) throws CustomException;
	//修改收藏备注
    ResultBean<CollectCustom> changeRemark(CollectCustom collectCustom) throws CustomException;
	//根据条件查找收藏
	ResultBean<CollectCustom> getCollectSelective(CollectCustom collectCustom) throws CustomException;
	//根据条件获取收藏列表
	ResultBean<ResultList<CollectCustom>> getCollectListSelective
	(CollectCustom collectCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取收藏列表
	ResultBean<ResultList<CollectCustom>> getCollectListSelectiveByTime
	(CollectCustom collectCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;
	

}
