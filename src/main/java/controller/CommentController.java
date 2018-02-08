package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import exception.CustomException;
import filter.CheckToken;
import po.CommentCustom;
import service.CommentService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: CommentController.java
* @Description:评论的控制器 
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月7日 下午2:52:38 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月7日     GzpzG           v1.0.0               创建
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<CommentCustom> getComment
	(@RequestBody CommentCustom commentCustom,HttpServletRequest request) throws Exception{
		if(commentCustom.getCommentContent()==null||commentCustom.getCommentContent().length()==0)
			throw new CustomException(101, "聊天内容不能为空");
		if(commentCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request, commentCustom.getUserId());
		if(commentCustom.getTaskId()==null)
			throw new CustomException(101, "任务id不能为空");
		return commentService.insert(commentCustom);
	}
	
	@RequestMapping(value="/changeComment",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<CommentCustom> changeComment
	(@RequestBody CommentCustom commentCustom,HttpServletRequest request) throws Exception{
		if(commentCustom.getCommentContent()==null||commentCustom.getCommentContent().length()==0)
			throw new CustomException(101, "聊天内容不能为空");
		if(commentCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request, commentCustom.getUserId());
		if(commentCustom.getTaskId()==null)
			throw new CustomException(101, "评论id不能为空");
		return commentService.update(commentCustom);
	}
	
	@RequestMapping(value="/deleteComment",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean<CommentCustom> deleteComment
	(@RequestBody CommentCustom commentCustom,HttpServletRequest request) throws Exception{
		if(commentCustom.getUserId()==null)
			throw new CustomException(101, "用户id不能为空");
		CheckToken.check(request, commentCustom.getUserId());
		if(commentCustom.getTaskId()==null)
			throw new CustomException(101, "评论id不能为空");
		return commentService.delete(commentCustom);
	}
	
	@RequestMapping(value="/getComment",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<CommentCustom> getComment
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CommentCustom comment = new CommentCustom();
		if(map.containsKey("commentId"))
			comment.setCommentId(Integer.valueOf(map.get("commentId")));
		return commentService.getCommentSelective(comment);
	}
	
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<CommentCustom>> getList
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CommentCustom comment = new CommentCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			comment.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskId"))
			comment.setTaskId(Integer.valueOf(map.get("taskId")));
		comment.setCommentState(0);
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return commentService.getCommentListSelective(comment,pageSize, pageNum);
	}
	
	@RequestMapping(value="/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<CommentCustom>> getListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.check(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CommentCustom comment = new CommentCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			comment.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskId"))
			comment.setTaskId(Integer.valueOf(map.get("taskId")));
		comment.setCommentState(1);
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return commentService.getCommentListSelectiveByTime
				(comment, startTime, endTime, pageSize, pageNum);
	}
	
	
	@RequestMapping(value="/Comment/getComment",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<CommentCustom> CommentgetComment
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CommentCustom comment = new CommentCustom();
		if(map.containsKey("commentId"))
			comment.setCommentId(Integer.valueOf(map.get("commentId")));
		return commentService.getCommentSelective(comment);
	}
	
	@RequestMapping(value="/Comment/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<CommentCustom>> CommentgetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CommentCustom comment = new CommentCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("userId"))
			comment.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskId"))
			comment.setTaskId(Integer.valueOf(map.get("taskId")));
		if(map.containsKey("commentState"))
			comment.setCommentState(Integer.valueOf(map.get("commentState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return commentService.getCommentListSelective(comment,pageSize, pageNum);
	}
	
	@RequestMapping(value="/Comment/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<CommentCustom>> CommentgetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		CommentCustom comment = new CommentCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("userId"))
			comment.setUserId(Integer.valueOf(map.get("userId")));
		if(map.containsKey("taskId"))
			comment.setTaskId(Integer.valueOf(map.get("taskId")));
		if(map.containsKey("commentState"))
			comment.setCommentState(Integer.valueOf(map.get("commentState")));
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return commentService.getCommentListSelectiveByTime
				(comment, startTime, endTime, pageSize, pageNum);
	}
}
