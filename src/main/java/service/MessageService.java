package service;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: MessageService.java
* @Description: 消息通知
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月3日 下午12:52:34 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月3日     GzpzG           v1.0.0               创建
 */

import java.util.Date;

import exception.CustomException;
import po.MessageCustom;
import utils.ResultBean;
import utils.ResultList;

public interface MessageService {
	//发送消息
	ResultBean<MessageCustom> send(MessageCustom messageCustom) throws CustomException;
	//根据条件查找消息
	ResultBean<MessageCustom> getMessageSelective(MessageCustom messageCustom) throws CustomException;
	//根据条件获取消息列表
	ResultBean<ResultList<MessageCustom>> getMessageListSelective
	(MessageCustom messageCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取消息列表
	ResultBean<ResultList<MessageCustom>> getMessageListSelectiveByTime
	(MessageCustom messageCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;
}
