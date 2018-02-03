package service;

import java.util.Date;

import po.SchoolCustom;
import utils.ResultBean;
import utils.ResultList;

public interface SchoolService {
	//添加学校
	ResultBean<SchoolCustom> insert(SchoolCustom schoolCustom);
	//删除学校
	ResultBean<SchoolCustom> delete(Integer schoolId);
	//设置学校为热门
	ResultBean<SchoolCustom> setHotSchoole(Integer schoolId);
	//取消学校热门
	ResultBean<SchoolCustom> cancelHotSchool(Integer schoolId);
	//取消全部热门
	ResultBean<SchoolCustom> cancelAllHotSchool();

	//根据条件查找学校
	ResultBean<SchoolCustom> getBySelective(SchoolCustom SchoolCustom);
	//根据条件获取学校列表
	ResultBean<ResultList<SchoolCustom>> getSchoolListSelective
	(SchoolCustom SchoolCustom,int pageSize,int pageNum);
	//根据时间+条件获取学校列表
	ResultBean<ResultList<SchoolCustom>> getSchoolListSelectiveByTime
	(SchoolCustom SchoolCustom,Date startTime,Date endTime,int pageSize,int pageNum);
}
