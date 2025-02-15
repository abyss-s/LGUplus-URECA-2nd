package boj.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3273_S3_��_����_��_�̿��� {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int[] data = new int[n]; // ���� �迭

		// ���� �� ���� �Է� ó��
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		int x = Integer.parseInt(br.readLine()); // target: x

		Arrays.sort(data);

		int start = 0; // ������1
		int end = n - 1; // ������2
		int count = 0; // ������ �����ϴ� ���� ����
		int curSum = 0; // ���� �������� �� �հ�

		// two-pointer ����
		while (start < end) {
			curSum = data[start] + data[end];

			// Ÿ�ٰ����� ������
			if (curSum < x) {
				start++; // ���������� �̵�
			} // Ÿ�ٰ����� ũ��
			else if (curSum > x) {
				end--; // �������� �̵�
			}
			// Ÿ�ϰ��� ��ġ => ������ �����ϴ� ���
			else {
				count++;
				start++;
				end--;
			}
		}

		System.out.println(count); // ��� ���
	}
}