package service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.WalletMapper;
import po.Wallet;
import po.WalletCustom;
import service.WalletService;
import utils.ResultBean;
import utils.ResultList;

public class WalletServiceImpl implements WalletService{
	
	@Autowired
	WalletMapper walletMapper;

	@Override
	public ResultBean<WalletCustom> insert(WalletCustom walletCustom) throws CustomException {
		ResultBean<WalletCustom> resultBean = new ResultBean<WalletCustom>();
		Wallet wallet = new Wallet();
		wallet.setUserId(walletCustom.getUserId());
		List<WalletCustom> list = walletMapper.selectWalletSelective(wallet);
		if(list.isEmpty()){
			wallet.setWalletCreatetime(new Date());
			walletMapper.insertSelective(wallet);
			list = walletMapper.selectWalletSelective(wallet);
			resultBean.setResultCode(999);
		}else{
			resultBean.setResultCode(105);
			resultBean.setResultMessage("该用户已创建钱包");
		}
		resultBean.setResultData(list.get(0));
		return resultBean;
	}

	@Override
	public ResultBean<WalletCustom> delete(Integer walletId) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<WalletCustom> update(WalletCustom walletCustom) throws CustomException {
		ResultBean<WalletCustom> resultBean = new ResultBean<WalletCustom>();
		Wallet wallet = new Wallet();
		wallet.setUserId(walletCustom.getUserId());
		wallet.setWalletRest(walletCustom.getWalletRest());
		wallet.setWalletChangetime(new Date());
		walletMapper.updateByPrimaryKeySelective(wallet);
		resultBean.setResultCode(999);
		return resultBean;
	}

	@Override
	public ResultBean<WalletCustom> getWalletSelective(WalletCustom walletCustom) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<ResultList<WalletCustom>> getWalletListSelective(WalletCustom walletCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<ResultList<WalletCustom>> getWalletListSelectiveByTime(WalletCustom walletCustom, Date startTime,
			Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		//自动生成
		return null;
	}



}
