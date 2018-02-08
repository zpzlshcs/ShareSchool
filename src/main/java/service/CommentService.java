package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: CommentService.java
* @Description: 任务评论
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月2日 下午9:30:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月2日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import exception.CustomException;
import po.CommentCustom;
import utils.ResultBean;
import utils.ResultList;

public interface CommentService {
	// 新增评论
	ResultBean<CommentCustom> insert(CommentCustom commentCustom) throws CustomException;
	// 删除评论
	ResultBean<CommentCustom> delete(CommentCustom commentCustom) throws CustomException;
	// 修改评论
	ResultBean<CommentCustom> update(CommentCustom commentCustom) throws CustomException;
	//根据条件查找评论
	ResultBean<CommentCustom> getCommentSelective(CommentCustom commentCustom) throws CustomException;
	//根据条件获取评论列表
	ResultBean<ResultList<CommentCustom>> getCommentListSelective
	(CommentCustom commentCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取评论列表
	ResultBean<ResultList<CommentCustom>> getCommentListSelectiveByTime
	(CommentCustom commentCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;
}
