package service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.PowerMapper;
import mapper.UserMapper;
import po.PowerCustom;
import po.UserCustom;
import service.PowerService;
import utils.DateUtil;
import utils.PowerKeyUtil;
import utils.ResultBean;
import utils.ResultList;

public class PowerServiceImpl implements PowerService{
	
	@Autowired
	PowerMapper powerMapper;
	@Autowired
	UserMapper userMapper;
	
	@Override
	public ResultBean<PowerCustom> insert(PowerCustom powerCustom) throws CustomException {
		// TODO Auto-generated method stub
		ResultBean<PowerCustom> resultBean = new ResultBean<PowerCustom>();
		if(powerCustom.getUserId()==null){
			throw new CustomException(101, "缺少userId");
		}else if(userMapper.selectUserSelective(new UserCustom(powerCustom.getUserId())).isEmpty()){
			throw new CustomException(104, "无相关用户，请重新申请");
		}else if(powerMapper.selectPowerSelective(powerCustom).size()!=0){
			throw new CustomException(102, "已有证书，请设置延期");
		}else{
			powerCustom.setPowerCreatetime(DateUtil.getDate());
			powerCustom.setPowerEndtime(DateUtil.get30AfterDate());
			powerCustom.setPowerKey(PowerKeyUtil.getPowerKey());
			powerMapper.insertSelective(powerCustom);
			BeanUtils.copyProperties(powerMapper.selectPowerSelective(powerCustom), powerCustom);
			resultBean.setResultData(powerCustom);
			resultBean.setResultCode(999);
		}
		return resultBean;
	}

	@Override
	public ResultBean<PowerCustom> changeLevel(PowerCustom powerCustom) {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<PowerCustom> cutPower(PowerCustom powerCustom) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<PowerCustom> remakePower(String key, Date date) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<PowerCustom> checkPower(Integer userId, String key) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<PowerCustom> getBySelective(PowerCustom PowerCustom) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<ResultList<PowerCustom>> getPowerListSelective(PowerCustom PowerCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<ResultList<PowerCustom>> getPowerListSelectiveByTime(PowerCustom PowerCustom, Date startTime,
			Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		//自动生成
		return null;
	}




}
