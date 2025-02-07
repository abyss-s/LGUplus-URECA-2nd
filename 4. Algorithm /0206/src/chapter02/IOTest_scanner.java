package chapter02;

import java.util.Scanner;

public class IOTest_scanner {

	public static void main(String[] args) throws Exception {
		// keyboard로부터 읽기
		// Scanner sc = new Scanner(System.in);

		// 파일로부터 읽기
		// Scanner sc = new Scanner(new File("input.txt"));

		Scanner sc = new Scanner(input);

		int n = 5;
		for (int i = 0; i < n; i++) {
			System.out.println(sc.next());
		}
	}

	// method 밖에서 선언하므로 static으로 불변 선언하기!
	static String input = "10 9 8 7 6";

}
