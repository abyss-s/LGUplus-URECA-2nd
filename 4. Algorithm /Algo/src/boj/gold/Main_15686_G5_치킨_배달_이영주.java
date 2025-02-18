package boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15686_G5_ġŲ_���_�̿��� {
	static int n, m; // ���� ũ��, ���ܾ� �ϴ� ġŲ��
	static int[][] chickens; // ġŲ�� ��ġ ����
	static int[][] houses; // �� ��ġ ����
	static int chickenCount = 0; // ġŲ�� ����
	static int houseCount = 0; // �� ����
	static int INF = 100000000; // �ּ� �Ÿ� ��꿡 ����� ����� ����
	static int result = INF; // �ּ� �Ÿ� (��)

	// �Ÿ� ��� �Լ�
	public static int calcDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	// �������� ��ȸ�ϸ鼭 �Ÿ� ����ؼ� �ּҰ� ���ϱ�
	public static void combi(int start, int count, boolean[] visited) {
		// m�� ���õǾ��� ��
		if (count == m) {
			int totalDist = 0;       // ��ü �Ÿ�
			for (int i = 0; i < houseCount; i++) {
				int minDist = INF;
				for (int j = 0; j < chickenCount; j++) {
					if (visited[j]) { // ���õ� ġŲ���� ���
						int dist = calcDist(houses[i][0], houses[i][1], chickens[j][0], chickens[j][1]);
						minDist = Math.min(minDist, dist); // �ּ� �Ÿ� ����
					}
				}
				totalDist += minDist;
			}
			result = Math.min(result, totalDist); // �ּ� �Ÿ� ����
			return;
		}

		// ġŲ���� ���� ��� ��ȸ
		for (int i = start; i < chickenCount; i++) {
			visited[i] = true; // ���� ġŲ�� ����
			combi(i + 1, count + 1, visited); // ���� �ܰ�
			visited[i] = false; // �湮��
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // ���� ũ��
		m = Integer.parseInt(st.nextToken()); // ���� ġŲ�� ����

		chickens = new int[n * n][2]; // ġŲ�� �迭(x, y ��ǥ ����)
		houses = new int[n * n][2]; // �� �迭(x, y ��ǥ ����)

		// ���� ���� �Է� ó��
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int token = Integer.parseInt(st.nextToken());
				// ���̸� �� �迭��
				if (token == 1) {
					houses[houseCount++] = new int[]{i, j};
				}
				// ġŲ�̸� ġŲ�� �迭��
				else if (token == 2) {
					chickens[chickenCount++] = new int[]{i, j};
				}
			}
		}

		boolean[] visited = new boolean[chickenCount]; // boolean �迭�� �ش� ġŲ�� �湮 ���� ó��
		combi(0, 0, visited); // ���
		System.out.println(result); // �ּ� �Ÿ� ���
	}
}