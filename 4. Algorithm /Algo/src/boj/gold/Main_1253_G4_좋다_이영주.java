package boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1253_G4_����_�̿��� {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		// ������ ���� �Է� ó��
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int count = 0;

		// � ���� �ٸ� �� �� ���� ������ ��Ÿ�� �� �ִ��� Ȯ��
		for (int i = 0; i < n; i++) {
			int temp = arr[i]; // � ��
			int sum = 0;

			// �������� ����
			int start = 0;
			int end = n - 1;

			while (start < end) {
				// ���̻� ã�� �� ������ ���� �� ã���� ������ �̵�
				if (start == i) {
					start++;
					continue;
				}
				if (end == i) {
					end--;
					continue;
				}

				sum = arr[start] + arr[end];

				if (sum < temp) {
					start++;
				} else if (sum > temp) {
					end--;
				} else {
					count++;
					break;
				}
			}
		}

		// ��� ���
		System.out.println(count);
	}
}