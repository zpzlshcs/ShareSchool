package mapper;
/**
 * 
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: UserMapper.java
* @Description: UserDao
*
* @version: v1.0.0
* @author: GzpzG
* @date: 2018年2月2日 下午7:50:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月2日     GzpzG           v1.0.0               创建
 */

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.User;


public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUserSelective(User record);
    
    List<User> selectUserListSelective(User record);
    
    List<User> selectUserListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("User")User record);
    
    
}