package boj.bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_4344_B1_평균은_넘겠지_김동근 {

    public static void main(String[] args) throws Exception {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()); // 학생 수
            int[] scores = new int[n]; // 학생 성적 배열

            int total = 0;
            for (int i = 0; i < n; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
                total += scores[i];
            }

            // 평균 계산
            double avg = (double) total / n;

            // 평균을 넘는 학생 수 계산
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (scores[i] > avg) {
                    count++;
                }
            }

            // 비율 구하기 (반올림하여 소수 셋째 자리까지 출력)
            double percentage = (double) count / n * 100;
            System.out.printf("%.3f%%\n", percentage);
        }
    }
}
