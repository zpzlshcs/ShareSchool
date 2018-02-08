package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.CommentMapper;
import po.Comment;
import po.CommentCustom;
import service.CommentService;
import utils.DateUtil;
import utils.ResultBean;
import utils.ResultList;

public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public ResultBean<CommentCustom> insert(CommentCustom commentCustom) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<CommentCustom> resultBean = new ResultBean<CommentCustom>();
		CommentCustom comment = new CommentCustom();
		comment.setCommentContent(commentCustom.getCommentContent());
		comment.setCommentToUserId(commentCustom.getCommentToUserId());
		comment.setTaskId(commentCustom.getTaskId());
		comment.setUserId(commentCustom.getUserId());
		comment.setCommentCreatetime(DateUtil.getDate());
		commentMapper.insertSelective(comment);
		BeanUtils.copyProperties(commentMapper.selectCommentListSelective(comment).get(0), comment);
		resultBean.setResultCode(999);
		resultBean.setResultData(comment);
		return resultBean;
	}
	
	@Override
	public ResultBean<CommentCustom> delete(CommentCustom commentCustom) throws CustomException {
		ResultBean<CommentCustom> resultBean = new ResultBean<CommentCustom>();
		Comment comment = new Comment();
		comment = commentMapper.selectByPrimaryKey(commentCustom.getCommentId());
		if(!commentCustom.getUserId().equals(comment.getUserId()))
			throw new CustomException(103, "您不是该条评论的拥有者");
		comment.setCommentState(1);
		comment.setCommentChangetime(new Date());
		commentMapper.updateByPrimaryKeySelective(comment);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("删除评论成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<CommentCustom> update(CommentCustom commentCustom) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<CommentCustom> resultBean = new ResultBean<CommentCustom>();
		Comment comment = new Comment();
		comment = commentMapper.selectByPrimaryKey(commentCustom.getCommentId());
		if(!commentCustom.getUserId().equals(comment.getUserId()))
			throw new CustomException(103, "您不是该条评论的拥有者");
		comment.setCommentId(commentCustom.getCommentId());
		comment.setCommentContent(commentCustom.getCommentContent());
		comment.setCommentChangetime(new Date());
		commentMapper.updateByPrimaryKeySelective(comment);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("修改评论成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<CommentCustom> getCommentSelective(CommentCustom commentCustom) throws CustomException {
		ResultBean<CommentCustom> resultBean = new ResultBean<CommentCustom>();
		resultBean.setResultData(commentMapper.selectCommentListSelective(commentCustom).get(0));	
		resultBean.setResultCode(999);
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<CommentCustom>> getCommentListSelective(CommentCustom commentCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<CommentCustom>> resultBean = new ResultBean<ResultList<CommentCustom>>();
		List<CommentCustom> customList = new ArrayList<CommentCustom>();
		List<CommentCustom> list = commentMapper.selectCommentListSelective(commentCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				CommentCustom userCustom2 = new CommentCustom();
				BeanUtils.copyProperties(list.get(i), userCustom2);
				
				customList.add(userCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<CommentCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}

	public ResultBean<ResultList<CommentCustom>> getCommentListSelectiveByTime(CommentCustom commentCustom,
			Date startTime, Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<CommentCustom>> resultBean = new ResultBean<ResultList<CommentCustom>>();
		List<CommentCustom> customList = new ArrayList<CommentCustom>();
		List<CommentCustom> list = commentMapper.selectCommentListSelectiveByDate(endTime, startTime, commentCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				CommentCustom userCustom2 = new CommentCustom();
				BeanUtils.copyProperties(list.get(i), userCustom2);
				
				customList.add(userCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<CommentCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}

	
}
