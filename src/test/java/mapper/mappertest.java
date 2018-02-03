package mapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.User;



public class mappertest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-mapper.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		User user = new User();
		//System.out.println(userMapper.selectUserListSelectiveByDate(null, new Date(118, 0, 11), user).size());
		Date date = new Date(118, 0, 11);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(sdf.format(date));
		//user.setUserPhone("10000");
		System.out.println(userMapper.selectUserListSelective(user).get(0).getUserId());
	}

}
