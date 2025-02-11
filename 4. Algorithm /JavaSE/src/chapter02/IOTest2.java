package chapter02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class IOTest2 {
	public static void main(String[] args) throws Exception {
		
		// System.in 키보드로부터 입력 받는 것이 기본적임
		// 다른 대상으로부터 입력받을 경우 setIn입력 받을 대상) 함수로 설정 
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		System.out.println(line);
		
	}
}
