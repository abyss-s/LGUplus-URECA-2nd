package boj.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_17478_S5_재귀함수가_뭔가요_이영주 {

	public static void main(String[] args) throws Exception {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recursice_func(N, "");
	}

	public static void recursice_func(int n, String prefix) {
		System.out.println(prefix + "\"재귀함수가 뭔가요?\"");

		// 깊이가 없는 경우
		if (n == 0) {
			System.out.println(prefix + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(prefix + "라고 답변하였지.");
			return;
		}

		System.out.println(prefix + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(prefix + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(prefix + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");

		recursice_func(n - 1, "____" + prefix);

		System.out.println(prefix + "라고 답변하였지.");
	}

	//	첫 번째 질문: 함수가 시작되면 학생은 교수님에게 "재귀함수가 뭔가요?"라고 질문합니다.
	//
	//	대답 준비: 깊이에 따라 두가지 답변이 가능합니다!
	//
	//	재귀 호출이 계속될 경우: 교수님은 이야기를 하며 다시 질문에 대답하기 위해 자기 자신을 호출합니다.
	//	기본 경우 (N이 0일 때): "재귀함수는 자기 자신을 호출하는 함수라네."라는 대답을 하고 대화의 마무리를 짓습니다.
	// recursice_func(0)이 끝난 후 recursice_func(1)으로 돌아와서
	// "라고 답변하였지." 출력
	// 다시 recursice_func(2)로 돌아와서
	// "라고 답변하였지." 출력

}