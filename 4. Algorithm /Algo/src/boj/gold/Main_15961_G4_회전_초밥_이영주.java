package boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15961_G4_ȸ��_�ʹ�_�̿��� {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// �Է� �� �б�
		int n = Integer.parseInt(st.nextToken()); // ���� ��
		int d = Integer.parseInt(st.nextToken()); // �ʹ� ���� ��
		int k = Integer.parseInt(st.nextToken()); // ������ ���� ���� ��
		int c = Integer.parseInt(st.nextToken()); // ���� ��ȣ

		int[] plates = new int[n]; // �������� �� ���� �迭

		for (int i = 0; i < n; i++) {
			plates[i] = Integer.parseInt(br.readLine()); // �� ���ÿ� �ִ� �ʹ� ���� �Է� ó��
		}

		int[] sushiCount = new int[d + 1]; // �ε����� �ʹ� ������ �� �迭
		int max = 0; // ���� �ٸ� �ʹ� ���� �ִ�(����)
		int curMax = 0; // �ش� ������ �ܰ���� max ������ temp


		/*
		 *  sliding window �ʱ�ȭ(ù��° ������)
		 */
		for (int i = 0; i < k; i++) {
			if (sushiCount[plates[i]] == 0) {
				curMax++;
			}
			sushiCount[plates[i]]++;
		}
		// ���� ����
		if (sushiCount[c] == 0) {
			max = curMax + 1;
		} else {
			max = curMax;
		}

		/*
		 *  sliding window �̵���Ű�� (�ι�° ��������� ������ )
		 */
		for (int i = 1; i < n; i++) {
			// �������ͷ� ����� �Ǿ�, �ǵ� �ε��� ���� ����
			int start = i - 1; // window start - �� ���� ����
			int end = (i + k - 1) % n; // window end - ���� ����
			// end�� ���� ���ü���ŭ �����ְ� �����̱� ������ n���� �����־�� ��

			// start ó�� (����)
			sushiCount[plates[start]]--; // �����쿡�� ������
			if (sushiCount[plates[start]] == 0) { // ���� ���� ������ --
				curMax--;
			}

			// end ó�� (�߰�)
			if (sushiCount[plates[end]] == 0) { // ���� ���°Ÿ� ++
				curMax++;
			}
			sushiCount[plates[end]]++; // �����쿡 ����


			// ������ ������ �� �ִ� ���
			if (sushiCount[c] == 0) {
				max = Math.max(max, curMax + 1);
			} else {
				max = Math.max(max, curMax);
			}
			System.out.println(i + " " + curMax + "\n");
		}

		System.out.println(max);
	}
}