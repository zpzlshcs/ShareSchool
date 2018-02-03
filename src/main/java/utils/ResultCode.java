package utils;

public class ResultCode {
	public static String getResultMessage(int resultCode){
		switch (resultCode) {
		case 101:
			return "缺少必要数据";
		case 102:
			return "创建对象冲突";
		case 103:
			return "权限等级不够";
		case 999:
			return "请求成功";
		default:
			return "未定义的未知错误";
		}
	}
}
