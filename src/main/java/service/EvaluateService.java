package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: Evaluate.java
* @Description: 订单评价与追评
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月3日 下午1:42:50 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月3日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import po.EvaluateCustom;
import utils.ResultBean;
import utils.ResultList;

public interface EvaluateService {

	//发表评价
	ResultBean<EvaluateCustom> insert1(EvaluateCustom evaluateCustom);
	//发表追评
	ResultBean<EvaluateCustom> insert2(EvaluateCustom evaluateCustom);
	//删除评价
	ResultBean<EvaluateCustom> delete(Integer evaluateId);
	//根据条件查找评价
	ResultBean<EvaluateCustom> getBySelective(EvaluateCustom EvaluateCustom);
	//根据条件获取评价列表
	ResultBean<ResultList<EvaluateCustom>> getEvaluateListSelective
	(EvaluateCustom EvaluateCustom,int pageSize,int pageNum);
	//根据时间+条件获取评价列表
	ResultBean<ResultList<EvaluateCustom>> getEvaluateListSelectiveByTime
	(EvaluateCustom EvaluateCustom,Date startTime,Date endTime,int pageSize,int pageNum);
	
}
