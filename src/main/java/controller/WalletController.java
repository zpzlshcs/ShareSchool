package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import filter.CheckToken;
import po.WalletCustom;
import service.WalletService;
import utils.RequestUtil;
import utils.ResultBean;
import utils.ResultList;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: WalletController.java
* @Description: 钱包的控制器
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月7日 下午4:45:14 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月7日     GzpzG           v1.0.0               创建
 */
@Controller
@RequestMapping("/wallet")
public class WalletController {
	@Autowired
	WalletService walletService;
	
	@RequestMapping(value="/getWallet",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<WalletCustom> getWallet
	(HttpServletRequest request) throws Exception{
		Map<String, String> map = RequestUtil.RequestToMap(request);
		WalletCustom wallet = new WalletCustom();
		if(map.containsKey("walletId"))
			wallet.setWalletId(Integer.valueOf(map.get("walletId")));
		return walletService.getWalletSelective(wallet);
	}
	
	
	@RequestMapping(value="/Wallet/getWallet",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<WalletCustom> WalletgetWallet
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		WalletCustom wallet = new WalletCustom();
		if(map.containsKey("walletId"))
			wallet.setWalletId(Integer.valueOf(map.get("walletId")));
		return walletService.getWalletSelective(wallet);
	}
	
	@RequestMapping(value="/Wallet/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean<ResultList<WalletCustom>> WalletgetList
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		WalletCustom wallet = new WalletCustom();
		Integer pageSize = 10;
		Integer pageNum = 1;
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		return walletService.getWalletListSelective(wallet,pageSize, pageNum);
	}
	
	@RequestMapping(value="/Wallet/getListByTime")
	@ResponseBody
	public ResultBean<ResultList<WalletCustom>> WalletgetListByTime
	(HttpServletRequest request) throws Exception{
		CheckToken.checkManager(request);
		Map<String, String> map = RequestUtil.RequestToMap(request);
		WalletCustom wallet = new WalletCustom();
		Date startTime = null;
		Date endTime = null;
		Integer pageSize = 10;
		Integer pageNum = 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(map.containsKey("pageSize"))
			pageSize = Integer.valueOf(map.get("pageSize"));
		if(map.containsKey("pageNum"))
			pageNum = Integer.valueOf(map.get("pageNum"));
		if(map.containsKey("startTime"))
			startTime = format.parse(map.get("startTime"));
		if(map.containsKey("endTime"))
			endTime = format.parse(map.get("endTime"));
		return walletService.getWalletListSelectiveByTime
				(wallet, startTime, endTime, pageSize, pageNum);
	}
}
