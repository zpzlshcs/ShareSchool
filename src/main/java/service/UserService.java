package service;
/**
* 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: UserService.java
* @Description: UserService
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月2日 下午7:58:01 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月2日     GzpzG           v1.0.0               创建
*/

import java.util.Date;

import exception.CustomException;
import po.UserCustom;
import utils.ResultBean;
import utils.ResultList;

public interface UserService {
	//注册
	ResultBean<UserCustom> register(UserCustom user) throws CustomException;
	//销户
	ResultBean<UserCustom> delete(Integer user_id)  throws CustomException;
	//登陆
	ResultBean<UserCustom> login(String phone,String password) throws CustomException;
	//修改密码
	ResultBean<UserCustom> changePassword(String phone,String newpassword) throws CustomException;
	//提升为大咖
	ResultBean<UserCustom> changeLevel(UserCustom user) throws CustomException;
	
	
	//根据条件获取用户
	ResultBean<UserCustom> getUserSelective(UserCustom userCustom) throws CustomException;
	//根据条件获取用户列表
	ResultBean<ResultList<UserCustom>> getUserListSelective
	(UserCustom userCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取用户列表
	ResultBean<ResultList<UserCustom>> getUserListSelectiveByTime
	(UserCustom userCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;
	
	
	
}
