package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: FollowService.java
* @Description: 用户关注
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月2日 下午9:38:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月2日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import po.FollowCustom;
import utils.ResultBean;
import utils.ResultList;

public interface FollowService {
	//添加关注
	ResultBean<FollowCustom> insert(FollowCustom followCustom);
	//取消关注
	ResultBean<FollowCustom> delete(Integer followId);
	//根据条件查找关注
	ResultBean<FollowCustom> getBySelective(FollowCustom followCustom);
	//根据条件获取关注列表
	ResultBean<ResultList<FollowCustom>> getFollowListSelective
	(FollowCustom followCustom,int pageSize,int pageNum);
	//根据时间+条件获取关注列表
	ResultBean<ResultList<FollowCustom>> getFollowListSelectiveByTime
	(FollowCustom followCustom,Date startTime,Date endTime,int pageSize,int pageNum);
	
}
