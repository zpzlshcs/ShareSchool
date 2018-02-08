package service;

import java.util.Date;

import exception.CustomException;
import po.SchoolCustom;
import utils.ResultBean;
import utils.ResultList;

public interface SchoolService {
	//添加学校
	ResultBean<SchoolCustom> insert(SchoolCustom schoolCustom) throws CustomException;
	//删除学校
	ResultBean<SchoolCustom> delete(Integer schoolId) throws CustomException;
	//设置学校为热门
	ResultBean<SchoolCustom> setHotSchoole(Integer schoolId) throws CustomException;
	//取消学校热门
	ResultBean<SchoolCustom> cancelHotSchool(Integer schoolId) throws CustomException;
	//取消全部热门
	ResultBean<SchoolCustom> cancelAllHotSchool() throws CustomException;

	//根据条件查找学校
	ResultBean<SchoolCustom> getBySelective(SchoolCustom SchoolCustom) throws CustomException;
	//根据条件获取学校列表
	ResultBean<ResultList<SchoolCustom>> getSchoolListSelective
	(SchoolCustom SchoolCustom,Integer pageSize,Integer pageNum) throws CustomException;
	//根据时间+条件获取学校列表
	ResultBean<ResultList<SchoolCustom>> getSchoolListSelectiveByTime
	(SchoolCustom SchoolCustom,Date startTime,Date endTime,Integer pageSize,Integer pageNum) throws CustomException;
}
