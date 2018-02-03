package service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import mapper.UserMapper;
import po.User;
import po.UserCustom;
import service.UserService;
import utils.DateUtil;
import utils.ResultBean;
import utils.ResultList;

public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	

	
	public ResultBean<UserCustom> register(UserCustom user){
		ResultBean<UserCustom> resultBean = new ResultBean<UserCustom>();
		if(user.getUserPhone() == null){
			resultBean.setResultCode(101);
			resultBean.setResultMessage("电话不能为空");
		}else if(user.getUserPassword() == null){
			resultBean.setResultCode(101);
			resultBean.setResultMessage("密码不能为空");
		}else{
			user.setUserCreatetime(DateUtil.getDate());
			userMapper.insertSelective(user);
			resultBean.setResultCode(999);
			resultBean.setResultMessage("注册成功");
			//resultBean.setResultData(user);
		}
		return resultBean;
	}

	public ResultBean<UserCustom> delete(Integer user_id) {
		ResultBean<UserCustom> resultBean = new ResultBean<UserCustom>();
		userMapper.deleteByPrimaryKey(user_id);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("注册成功");
		return resultBean;
	}

	public ResultBean<UserCustom> login(String phone, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultBean<UserCustom> changePassword(String phone, String newpassword) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultBean<UserCustom> changeLevel(Integer user) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultBean<UserCustom> getUserSelective(UserCustom userCustom) {
		// TODO Auto-generated method stub
		ResultBean<UserCustom> resultBean = new ResultBean<UserCustom>();
		List<User> list = userMapper.selectUserSelective(userCustom);
		if(list.size()==0){
			resultBean.setResultMessage("没有相应的用户");
			resultBean.setResultData(userCustom);
			resultBean.setResultCode(999);
		}else{
			BeanUtils.copyProperties(list.get(0), userCustom);
			resultBean.setResultData(userCustom);
			resultBean.setResultCode(999);
			resultBean.setResultMessage("查找成功");
		}
		return resultBean;
	}

	public ResultBean<ResultList<UserCustom>> getUserListSelective
	(UserCustom userCustom, int pageSize, int pageNum) {
		ResultBean<ResultList<UserCustom>> resultBean = new ResultBean<ResultList<UserCustom>>();
		List<UserCustom> customList = new ArrayList<UserCustom>();
		List<User> list = userMapper.selectUserListSelective(userCustom);
		int start = pageSize*(pageNum-1);
		int end = pageSize*(pageNum);
		for(int i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				UserCustom userCustom2 = new UserCustom();
				BeanUtils.copyProperties(list.get(i), userCustom);
				customList.add(userCustom2);
			}else if(end<=i){
				break;
			}
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<UserCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}

	public ResultBean<ResultList<UserCustom>> getUserListSelectiveByTime
	(UserCustom userCustom, Date startTime,Date endTime, int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
