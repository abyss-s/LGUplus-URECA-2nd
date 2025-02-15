package boj.gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15961_G4_회전_초밥_이영주 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 값 읽기
		int n = Integer.parseInt(st.nextToken()); // 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 종류 수
		int k = Integer.parseInt(st.nextToken()); // 선택할 연속 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] plates = new int[n]; // 음식점의 총 접시 배열

		for (int i = 0; i < n; i++) {
			plates[i] = Integer.parseInt(br.readLine()); // 각 접시에 있는 초밥 종류 입력 처리
		}

		int[] sushiCount = new int[d + 1]; // 인덱스로 초밥 가짓수 셀 배열
		int max = 0; // 서로 다른 초밥 종류 최댓값(최종)
		int curMax = 0; // 해당 윈도우 단계까지 max 저장할 temp


		/*
		 *  sliding window 초기화(첫번째 윈도우)
		 */
		for (int i = 0; i < k; i++) {
			if (sushiCount[plates[i]] == 0) {
				curMax++;
			}
			sushiCount[plates[i]]++;
		}
		// 쿠폰 적용
		if (sushiCount[c] == 0) {
			max = curMax + 1;
		} else {
			max = curMax;
		}

		/*
		 *  sliding window 이동시키기 (두번째 윈도우부터 끝까지 )
		 */
		for (int i = 1; i < n; i++) {
			// 투포인터로 사용할 맨앞, 맨뒤 인덱스 각각 선언
			int start = i - 1; // window start - 곧 나갈 예정
			int end = (i + k - 1) % n; // window end - 들어올 예정
			// end는 연속 접시수만큼 더해주고 원형이기 때문에 n으로 나눠주어야 함

			// start 처리 (삭제)
			sushiCount[plates[start]]--; // 윈도우에서 나가기
			if (sushiCount[plates[start]] == 0) { // 남은 종류 없으면 --
				curMax--;
			}

			// end 처리 (추가)
			if (sushiCount[plates[end]] == 0) { // 새로 들어온거면 ++
				curMax++;
			}
			sushiCount[plates[end]]++; // 윈도우에 들어옴


			// 쿠폰을 적용할 수 있는 경우
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