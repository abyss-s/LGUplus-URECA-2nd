package boj.bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_4344_B1_�����_�Ѱ���_�赿�� {

    public static void main(String[] args) throws Exception {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // �׽�Ʈ ���̽� ����

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()); // �л� ��
            int[] scores = new int[n]; // �л� ���� �迭

            int total = 0;
            for (int i = 0; i < n; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
                total += scores[i];
            }

            // ��� ���
            double avg = (double) total / n;

            // ����� �Ѵ� �л� �� ���
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (scores[i] > avg) {
                    count++;
                }
            }

            // ���� ���ϱ� (�ݿø��Ͽ� �Ҽ� ��° �ڸ����� ���)
            double percentage = (double) count / n * 100;
            System.out.printf("%.3f%%\n", percentage);
        }
    }
}
