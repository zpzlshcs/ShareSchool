package mapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import po.Information;
import po.User;



public class mappertest {

	public static void main(String[] args) throws JsonProcessingException {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-mapper.xml");
		UserMapper userMapper = ctx.getBean(UserMapper.class);
		User user = new User();
		//System.out.println(userMapper.selectUserListSelectiveByDate(null, new Date(118, 0, 11), user).size());
		Date date = new Date(118, 0, 11);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(sdf.format(date));
		//user.setUserPhone("10000");
		//System.out.println(userMapper.selectUserListSelective(user).get(0).getUserId());
		InformationMapper informationMapper = ctx.getBean(InformationMapper.class);
		Information info = new Information();
		info.setInfoSex(0);
		//System.out.println(informationMapper.selectInformationListSelective(info).size());
		CommentMapper commentMapper = ctx.getBean(CommentMapper.class);
		ObjectMapper objectMapper = new ObjectMapper();  
        //String json = objectMapper.writeValueAsString(commentMapper.selectCommentListSelective(null)); 
		//System.out.println(json);
		OrderMapper orderMapper = ctx.getBean(OrderMapper.class);
		String order = objectMapper.writeValueAsString(orderMapper.selectOrderListSelective(null));
		System.out.println(order);
	}

}
