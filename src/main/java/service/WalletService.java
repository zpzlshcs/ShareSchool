package service;

import java.util.Date;

import exception.CustomException;
import po.WalletCustom;
import utils.ResultBean;
import utils.ResultList;

/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: WalletService.java
* @Description: 钱包
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月3日 下午4:47:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月3日     GzpzG           v1.0.0               创建
 */
public interface WalletService {
	//创建钱包(注册时创建)
	ResultBean<WalletCustom> insert(WalletCustom walletCustom) throws CustomException;
	//删除钱包
	ResultBean<WalletCustom> delete(Integer walletId) throws CustomException;
	//修改钱包
	ResultBean<WalletCustom> update(WalletCustom walletCustom) throws CustomException;
	//根据条件获取钱包
	ResultBean<WalletCustom> getWalletSelective(WalletCustom walletCustom) throws CustomException;
	//根据条件获取钱包列表
	ResultBean<ResultList<WalletCustom>> getWalletListSelective
	(WalletCustom walletCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取钱包列表
	ResultBean<ResultList<WalletCustom>> getWalletListSelectiveByTime
	(WalletCustom walletCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;
}
