package service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import filter.JWTtutil;
import mapper.HonestyMapper;
import mapper.InformationMapper;
import mapper.PowerMapper;
import mapper.UserMapper;
import mapper.WalletMapper;
import po.Honesty;
import po.Information;
import po.PowerCustom;
import po.User;
import po.UserCustom;
import po.Wallet;
import service.UserService;
import utils.DateUtil;
import utils.PasswordUtil;
import utils.PowerKeyUtil;
import utils.ResultBean;
import utils.ResultList;
import utils.TokenBean;

public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	@Autowired
	HonestyMapper honestyMapper;
	@Autowired
	InformationMapper informationMapper;
	@Autowired
	WalletMapper walletMapper;
	@Autowired
	PowerMapper powerMapper;
	
	@Override
	public ResultBean<UserCustom> register(UserCustom userCustom) throws CustomException{
		ResultBean<UserCustom> resultBean = new ResultBean<UserCustom>();
		if(!(PasswordUtil.checkPassword(userCustom.getUserPassword())=="")){
			throw new CustomException(101, PasswordUtil.checkPassword(userCustom.getUserPassword()));
		}else if(!userMapper.selectUserSelective(userCustom).isEmpty()){
			throw new CustomException(102, "手机号已被注册");
		}else{
			UserCustom user = new UserCustom();
			user.setUserPassword(userCustom.getUserPassword());
			user.setUserPhone(userCustom.getUserPhone());
			Date date = DateUtil.getDate();
			user.setUserCreatetime(date);
			user.setUserName("手机用户"+userCustom.getUserPhone());
			userMapper.insertSelective(user);
			User getUser = userMapper.selectUserSelective(user).get(0);
			BeanUtils.copyProperties(getUser, user);
			Integer userId = getUser.getUserId();
			
			Honesty honesty = new Honesty();
			honesty.setHonestyCreatetime(date);
			honesty.setUserId(userId);
			honestyMapper.insertSelective(honesty);
			
		    Information info = new Information();
		    info.setInfoCreatetime(date);
		    info.setUserId(userId);
		    info.setInfoNickname("手机用户"+userCustom.getUserPhone());
		    informationMapper.insertSelective(info);
		    
		    Wallet wallet = new Wallet();
		    wallet.setUserId(userId);
		    wallet.setWalletCreatetime(date);
		    walletMapper.insertSelective(wallet);
			
			resultBean.setResultCode(999);
			resultBean.setResultMessage("注册成功");
			user.setUserPassword(null);
			user.setUserLevel(null);
			resultBean.setResultData(user);
		}
		return resultBean;
	}
	
	@Override
	public ResultBean<UserCustom> delete(Integer user_id) throws CustomException {
		UserCustom user = new UserCustom();
		ResultBean<UserCustom> resultBean = new ResultBean<UserCustom>();
		user.setUserId(user_id);
		if(!userMapper.selectUserSelective(user).isEmpty()){
			userMapper.deleteByPrimaryKey(user_id);
			resultBean.setResultCode(999);
		}else{
			throw new CustomException(104, "没有该用户");
		}
		return resultBean;
	}
	
	@Override
	public ResultBean<UserCustom> login(String phone, String password) throws CustomException {
		// TODO Auto-generated method stub
		UserCustom user = new UserCustom();
		ResultBean<UserCustom> resultBean = new ResultBean<UserCustom>();
		user.setUserPhone(phone);
		List<User> list = userMapper.selectUserSelective(user);
		if(!list.isEmpty()){
			BeanUtils.copyProperties(list.get(0), user);
			if(user.getUserPassword().equals(password)){
				
				user.setUserLastlogintime(new Date());
				userMapper.updateByPrimaryKeySelective(user);
				TokenBean tokenBean = new TokenBean();
				tokenBean.setUserId(user.getUserId());
				tokenBean.setUserLevel(user.getUserLevel());
				String token = JWTtutil.createJWT(tokenBean);
				user.setUserPassword(null);
				user.setUserLevel(null);
				user.setToken(token);
				resultBean.setResultCode(999);
				resultBean.setResultMessage("登录成功");
				resultBean.setResultData(user);
			}else{
				resultBean.setResultCode(104);
				resultBean.setResultMessage("密码错误");
				resultBean.setResultData(user);
			}
		}else{
			resultBean.setResultCode(104);
			resultBean.setResultMessage("手机号错误");
			resultBean.setResultData(user);
		}
		return resultBean;
	}
	
	@Override
	public ResultBean<UserCustom> changePassword(String phone, String newpassword) throws CustomException {
		// TODO Auto-generated method stub
		UserCustom user = new UserCustom();
		ResultBean<UserCustom> resultBean = new ResultBean<UserCustom>();
		user.setUserPhone(phone);
		List<User> list = userMapper.selectUserSelective(user);
		if(list.isEmpty()){
			throw new CustomException(104, "该手机号尚未注册");
		}else if(PasswordUtil.checkPassword(newpassword).equals("")){
			throw new CustomException(101, PasswordUtil.checkPassword(newpassword));
		}else{
			user.setUserId(list.get(0).getUserId());
			user.setUserPassword(newpassword);
			userMapper.updateByPrimaryKeySelective(user);
			resultBean.setResultCode(999);
			resultBean.setResultMessage("修改成功");
		}
		return resultBean;
	}
	
	@Override
	public ResultBean<UserCustom> changeLevel(UserCustom user) throws CustomException {
		ResultBean<UserCustom> resultBean = new ResultBean<UserCustom>();
		User user2 = new User();
		user2.setUserId(user.getUserId());
		user2 = userMapper.selectByPrimaryKey(user.getUserId());
		if(user2.getUserLevel().equals(2))
			throw new CustomException(102, "该用户已经成为大咖");
		if(user2.getUserLevel().equals(3))
			throw new CustomException(102, "管理员用户不能成为大咖");
		user2.setUserLevel(2);
		userMapper.updateByPrimaryKeySelective(user2);
		resultBean.setResultCode(999);
		resultBean.setResultMessage("该用户被提升为大咖");
		return resultBean;
	}
	
	@Override
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
	
	@Override
	public ResultBean<ResultList<UserCustom>> getUserListSelective
	(UserCustom userCustom, Integer pageSize, Integer pageNum) {
		ResultBean<ResultList<UserCustom>> resultBean = new ResultBean<ResultList<UserCustom>>();
		List<UserCustom> customList = new ArrayList<UserCustom>();
		List<User> list = userMapper.selectUserListSelective(userCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		int start = pageSize*(pageNum-1);
		int end = pageSize*(pageNum);
		for(int i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				UserCustom userCustom2 = new UserCustom();
				BeanUtils.copyProperties(list.get(i), userCustom2);
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
	(UserCustom userCustom, Date startTime,Date endTime, Integer pageSize, Integer pageNum) {
		// TODO Auto-generated method stub
		ResultBean<ResultList<UserCustom>> resultBean = new ResultBean<ResultList<UserCustom>>();
		List<UserCustom> customList = new ArrayList<UserCustom>();
		List<User> list = userMapper.selectUserListSelectiveByDate(endTime, startTime, userCustom);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		int start = pageSize*(pageNum-1);
		int end = pageSize*(pageNum);
		for(int i = 0;i<list.size();i++){
			if(start<=i&&end>i){
				UserCustom userCustom2 = new UserCustom();
				BeanUtils.copyProperties(list.get(i), userCustom2);
				
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
}
