package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main_6808_규영이와_인영이의_카드게임_이영주 {
	static int winCount = 0;
	static int loseCount = 0;

	// swap permutation을 위한 스왑 함수
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// backtracking: swap permutation 이용한 스왑 재귀
	// https://www.jiwon.me/permutations/
	public static void permute(int[] iny, int start, int end, int[] gyu) {
		if (start == end) {
			calculateSum(gyu, iny);
		} else {
			for (int i = start; i <= end; i++) {
				swap(iny, start, i);
				permute(iny, start + 1, end, gyu);
				swap(iny, start, i);
			}
		}
	}

	// 규영이가 이기고 지는 횟수를 계산
	public static void calculateSum(int[] gyu, int[] iny) {
		int qyuSum = 0;
		int inySum = 0;
		for (int j = 0; j < 9; j++) {
			if (gyu[j] > iny[j]) {
				qyuSum += gyu[j] + iny[j];
			} else {
				inySum += gyu[j] + iny[j];
			}
		}

		if (qyuSum > inySum) {
			winCount++;
		} else {
			loseCount++;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int[] gyu = new int[9]; // 규영이 카드
			boolean[] cards = new boolean[19]; // 1~18 카드 사용 나타낼 불리언 배열

			// 규영이가 낸 카드 입력
			for (int i = 0; i < 9; i++) {
				gyu[i] = sc.nextInt();
				cards[gyu[i]] = true; // 카드 사용 처리
			}

			int[] iny = new int[9]; // 인영이 카드
			int idx = 0;

			// 인영이 카드 찾기
			for (int i = 1; i <= 18; i++) {
				if (!cards[i]) {
					iny[idx++] = i;
				}
			}

			winCount = 0;
			loseCount = 0;

			permute(iny, 0, 8, gyu);

			System.out.println("#" + test_case + " " + winCount + " " + loseCount);
		}
	}
}