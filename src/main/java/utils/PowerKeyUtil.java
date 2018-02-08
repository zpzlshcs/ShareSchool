package utils;

import java.util.UUID;

public class PowerKeyUtil {
	public static String getPowerKey() {
		String key = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32)
				+UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32)
				+UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
		return key;
	}
	public static void main(String[] args) {
		System.out.println(getPowerKey());
	}
}
