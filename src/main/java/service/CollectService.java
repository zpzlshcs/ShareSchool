package service;
import java.util.Date;

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
import po.CollectCustom;
import utils.ResultBean;
import utils.ResultList;

public interface CollectService {
	 //添加收藏
	ResultBean<CollectCustom> insert(CollectCustom collectCustom);
	//修改收藏（取消收藏、修改备注）
	ResultBean<CollectCustom> update(CollectCustom collectCustom);
	//根据条件查找收藏
	ResultBean<CollectCustom> getBySelective(CollectCustom collectCustom);
	//根据条件获取收藏列表
	ResultBean<ResultList<CollectCustom>> getCollectListSelective
	(CollectCustom collectCustom,int pageSize,int pageNum);
	//根据时间+条件获取收藏列表
	ResultBean<ResultList<CollectCustom>> getCollectListSelectiveByTime
	(CollectCustom collectCustom,Date startTime,Date endTime,int pageSize,int pageNum);
	

}
