package chapter02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Tokenizer {
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new StringReader(input));
		String line = br.readLine();
		
		/*
		 * split:  구분자를 기준으로 데이터를 분리 
		 * 
		 * */
		String[] tokens = line.split("");
		System.out.println(Arrays.toString(tokens));
		// result: [1, 0,  , 9,  , 8,  , 7,  , 6]
		
		String[] tokens2 = line.split(" ");
		System.out.println(Arrays.toString(tokens2));
		// result: [10, 9, 8, 7, 6]
		
		System.out.println(line);
		
		/*
		 * StringTokenizer(data, 구분자) :  구분자를 기준으로 데이터를 분리 
		 * 
		 * */
		
		StringTokenizer st = new StringTokenizer(line, " ");
		int n = 5;
		for(int i = 0 ; i < 5; i++) {
			System.out.println(st.nextToken());
		}
		
	}

	static String input = "10 9 8 7 6";
}
