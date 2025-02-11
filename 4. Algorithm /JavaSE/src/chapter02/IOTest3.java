package chapter02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class IOTest3 {
	public static void main(String[] args) throws Exception {
		
		// System.in 키보드로부터 입력 받는 것이 기본적임
		// 다른 대상으로부터 입력받을 경우 setIn(입력 받을 대상) 함수로 설정 
	
		BufferedReader br =new BufferedReader(new StringReader(input));
		String line = br.readLine();
		System.out.println(line);
		
	}
	static String input = "10 9 8 7 6";
}
