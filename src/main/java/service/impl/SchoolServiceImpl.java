package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CustomException;
import mapper.SchoolMapper;
import po.School;
import po.SchoolCustom;
import service.SchoolService;
import utils.ResultBean;
import utils.ResultList;

public class SchoolServiceImpl implements SchoolService{
	
	@Autowired
	SchoolMapper schoolMapper;

	
	@Override
	public ResultBean<ResultList<SchoolCustom>> getSchoolListSelective(SchoolCustom SchoolCustom, Integer pageSize,
			Integer pageNum) throws CustomException {
		ResultBean<ResultList<SchoolCustom>> resultBean = new ResultBean<ResultList<SchoolCustom>>();
		List<SchoolCustom> customList = new ArrayList<SchoolCustom>();
		List<School> list = schoolMapper.selectSchoolListSelective(null);
		if(pageSize==null)
			pageSize=10;
		if(pageNum==null)
			pageNum=1;
		for(int i = 0;i<list.size();i++){
			SchoolCustom schoolCustom2 = new SchoolCustom();
			BeanUtils.copyProperties(list.get(i), schoolCustom2);
			customList.add(schoolCustom2);
		}
		resultBean.setResultCode(999);
		resultBean.setResultData(new ResultList<SchoolCustom>(customList, pageSize, pageNum, list.size()));
		resultBean.setResultMessage("请求成功");
		return resultBean;
	}
	
	@Override
	public ResultBean<ResultList<SchoolCustom>> getSchoolListSelectiveByTime(SchoolCustom SchoolCustom, Date startTime,
			Date endTime, Integer pageSize, Integer pageNum) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<SchoolCustom> insert(SchoolCustom schoolCustom) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<SchoolCustom> delete(Integer schoolId) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<SchoolCustom> setHotSchoole(Integer schoolId) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<SchoolCustom> cancelHotSchool(Integer schoolId) throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<SchoolCustom> cancelAllHotSchool() throws CustomException {
		//自动生成
		return null;
	}

	@Override
	public ResultBean<SchoolCustom> getBySelective(SchoolCustom SchoolCustom) throws CustomException {
		//自动生成
		return null;
	}

}
