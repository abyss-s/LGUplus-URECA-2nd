package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main_6808_�Կ��̿�_�ο�����_ī�����_�̿��� {
	static int winCount = 0;
	static int loseCount = 0;

	// swap permutation�� ���� ���� �Լ�
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// backtracking: swap permutation �̿��� ���� ���
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

	// �Կ��̰� �̱�� ���� Ƚ���� ���
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
			int[] gyu = new int[9]; // �Կ��� ī��
			boolean[] cards = new boolean[19]; // 1~18 ī�� ��� ��Ÿ�� �Ҹ��� �迭

			// �Կ��̰� �� ī�� �Է�
			for (int i = 0; i < 9; i++) {
				gyu[i] = sc.nextInt();
				cards[gyu[i]] = true; // ī�� ��� ó��
			}

			int[] iny = new int[9]; // �ο��� ī��
			int idx = 0;

			// �ο��� ī�� ã��
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