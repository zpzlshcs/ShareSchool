package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.MessageMapper;
import po.Message;
import po.MessageCustom;
import service.MessageService;
import utils.ResultBean;
import utils.ResultList;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: MessageServiceImpl.java
* @Description: 消息的获取
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月6日 下午4:17:05 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月6日     GzpzG           v1.0.0               创建
 */
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	MessageMapper messageMapper;
	
	
	@Override
	public ResultBean<MessageCustom> send(MessageCustom messageCustom) throws CustomException {
		//自动生成
		ResultBean<MessageCustom> resultBean = new ResultBean<MessageCustom>();
	    Message message = new Message();
	    message.setMessageContent(messageCustom.getMessageContent());
	    message.setMessageCreatetime(new Date());
	    message.setMessageImg(messageCustom.getMessageImg());
	    message.setMessageReceiveUserId(messageCustom.getMessageReceiveUserId());
	    message.setMessageSendUserId(messageCustom.getMessageSendUserId());
	    messageMapper.insertSelective(message);
	    resultBean.setResultCode(999);
	    resultBean.setResultMessage("发送成功");
	    return resultBean;
	}

	@Override
	public ResultBean<MessageCustom> getMessageSelective(MessageCustom messageCustom) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<MessageCustom> resultBean = new ResultBean<MessageCustom>();
		BeanUtils.copyProperties(messageMapper.selectMessageSelective(messageCustom).get(0), messageCustom);
		resultBean.setResultCode(999);
		resultBean.setResultData(messageCustom);
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<MessageCustom>> getMessageListSelective(MessageCustom messageCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<MessageCustom>> resultBean = new ResultBean<ResultList<MessageCustom>>();
		List<MessageCustom> customList = new ArrayList<MessageCustom>();
		List<MessageCustom> list = messageMapper.selectMessageListSelective(messageCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				MessageCustom messageCustom2 = new MessageCustom();
				BeanUtils.copyProperties(list.get(i), messageCustom2);
				customList.add(messageCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<MessageCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<MessageCustom>> getMessageListSelectiveByTime(MessageCustom messageCustom,
			Date startTime, Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<ResultList<MessageCustom>> resultBean = new ResultBean<ResultList<MessageCustom>>();
		List<MessageCustom> customList = new ArrayList<MessageCustom>();
		List<MessageCustom> list = messageMapper.selectMessageListSelectiveByDate(endTime, startTime, messageCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		Integer start = pageSize*(pageNum-1);
		Integer end = pageSize*(pageNum);
		for(Integer i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				MessageCustom messageCustom2 = new MessageCustom();
				BeanUtils.copyProperties(list.get(i), messageCustom2);
				customList.add(messageCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<MessageCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}

	

}
