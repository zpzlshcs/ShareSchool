package mapper;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import po.CommentCustom;
import po.TaskCustom;

public class taskMapperTest {
	static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext-mapper.xml");
	static ObjectMapper objectMapper = new ObjectMapper();
	public static void main(String[] args) throws JsonProcessingException {
		//自动生成
		Comment();
		
	}
	public static void Task(){
		TaskMapper taskMapper = ctx.getBean(TaskMapper.class);
		TaskCustom task = new TaskCustom();
		task.setUserId(1);
		task.setTaskContent("111");
		task.setTaskTitle("1111");
		task.setTaskCreatetime(new Date());
		task.setTaskEndtime(new Date());
		taskMapper.insertSelective(task);
	}
	
	public static void Comment() throws JsonProcessingException{
		CommentMapper commentMapper = ctx.getBean(CommentMapper.class);
		CommentCustom commentCustom = new CommentCustom();
		commentCustom.setUserId(3);
		//System.out.println(objectMapper.writeValueAsString(commentMapper.selectCommentListSelective(null)));
		System.out.println(objectMapper.writeValueAsString
				(commentMapper.selectCommentListSelectiveByDate(null, null, commentCustom)));
	}

}
