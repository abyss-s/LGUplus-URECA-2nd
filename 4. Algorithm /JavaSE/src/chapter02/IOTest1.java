package chapter02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;

public class IOTest1 {
	public static void main(String[] args) throws Exception {
		/*
		 *  System.in : keyboard input. 
		 */
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		System.out.println(line);
		
		/*
		 *  file로부터 입력받
		 */
		BufferedReader br2=new BufferedReader(new FileReader("input.txt"));
		
		
		BufferedWriter wr;
		
		
	}
}
