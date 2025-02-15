package jungol.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// ���� 1141��. ������ ��
// https://jungol.co.kr/problem/1141
public class Main_1141_G5_������_�� {
	public static void main(String[] args) throws Exception {
		//����¹ޱ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] cows = new int[n];  // �� �ҵ��� Ű�� ������ �迭

		long count = 0;
		Stack<Integer> stack = new Stack<>();  // �ڽ��� �� �� �ִ� �ҵ��� Ű�� ������ ����

		for (int i = 0; i < n; ++i) {
			cows[i] = Integer.parseInt(br.readLine());

			// ���ÿ��� �ڽź��� ũ�ų� ���� �ҵ��� ����
			// (�ڽ��� Ű���� ���� �ҵ鸸 �� �� �ִ�.)
			while (!stack.isEmpty() && cows[i] >= stack.peek()) {
				stack.pop();
			}

			// ���� �Ҹ� �� �� �ִ� �Ҹ� ī��Ʈ�� �߰�
			if (!stack.isEmpty()) {
				count += stack.size();
			}

			// ���� �Ҹ� ���ÿ� �߰�
			stack.push(cows[i]);
		}

		System.out.println(count);
	}
}





