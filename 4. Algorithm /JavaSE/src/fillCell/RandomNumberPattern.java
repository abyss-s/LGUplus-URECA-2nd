package fillCell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 가로, 세로의 길이가 N인 N x N 정사각 행렬이 있다.
 * 이 행렬의 각각의 값으로서 0에서 9 사이의 수가 무작위로 넣어진다고 하자.
 * 이 때 그 행렬 내에서 주위를 둘러싸고 있는 다른 모든 수들 보다
 * 큰 수와 작은 수가 각각 몇 개인지를 구하는 프로그램을 작성하여라
 * <p>
 * [제한 조건]
 * 1. N은 3 이상 50 이하의 정수이다.
 * 2. 행렬의 가장자리에 있는 수는 8개의 수로 둘러싸여 있지 않으므로
 * "주위를 둘러싸고 있는 다른 모든 수들보다 큰 수, 혹은 작은 수"가 될 수 없다.
 * [입력]
 * 입력은 다음과 같이 구성되어 있다.
 * 첫 번째 줄에는 테스트 케이스의 개수 T가 주어진다.
 * 그 다음 T 개의 테스트 케이스가 차례로 주어진다.
 * 각 테스트 케이스는 다음과 같이 구성 되어 있다.
 * 첫 줄에 정 사각 행렬의 크기 N이 주어진다.
 * 그 다음 N 줄에 걸쳐 정 사각 행렬의 각 행의 값이 순서대로 주어진다.
 * [출력]
 * 각 줄은 #x로 시작하고 (x는 테스트 케이스 번호) 공백을 하나 둔 다음,
 * 문제에서 요구한 큰 수의 개수,
 * 그리고 작은 수의 개수를 공백을 두어 차례로 출력한다.
 * <p>
 * [결과]
 * #1 6 4
 * #2 1 1
 */
public class RandomNumberPattern {
	static int N, Answer1, Answer2;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("4. Algorithm /Algo/src/fillCell/RandomNumberPattern.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			int matrix[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			Answer1 = 0;  // 큰 수
			Answer2 = 0; // 작은 수

			for (int r = 1; r < N - 1; r++) {
				for (int c = 1; c < N - 1; c++) {
					int cur = matrix[r][c];

					// 주변 8방향 값 배열
					int[] arr = {
							matrix[r - 1][c - 1], matrix[r - 1][c], matrix[r - 1][c + 1],
							matrix[r][c - 1], matrix[r][c + 1],
							matrix[r + 1][c - 1], matrix[r + 1][c], matrix[r + 1][c + 1]
					};

					// 확인용 플래그 선언
					boolean isLargest = true;
					boolean isSmallest = true;

					// 배열을 돌면서 조건을 만족하지 않으면 넘어가기
					for (int k = 0; k < 8; k++) {
						if (cur <= arr[k]) {
							isLargest = false;
						}
						if (cur >= arr[k]) {
							isSmallest = false;
						}
					}

					if (isLargest) Answer1++;
					if (isSmallest) Answer2++;
				}
			}

			System.out.println("#" + test_case + " " + Answer1 + " " + Answer2);
		}

	}
}

// solution
//
//		public class RandomNumberPattern {
//			public static void main(String[] args) throws FileNotFoundException {
//				System.setIn(new FileInputStream("src/fillCell/RandomNumberPattern.txt"));
//
//				int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
//				int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
//
//				Scanner sc = new Scanner(System.in);
//				int T = sc.nextInt();  // 2
//				for (int test_case = 1; test_case <= T; test_case++) {
//					//입력 처리
//					int N = sc.nextInt();    // 9
//					int matrix[][] = new int[N][N];
//					for (int i = 0; i < N; i++) {
//						for (int j = 0; j < N; j++) {
//							matrix[i][j] = sc.nextInt();
//						}
//					}
//					//구현
//					// 1~N-1 범위의 모든 배열은 탐색하면서 현재 수가 큰수 또는 작은 수인지 검사하기
//
//
//					int big = 0;            //큰수 카운트 셀 변수
//					int small = 0;            //작은 수 카운트 셀 변수
//					int d = 8, nr = 0, nc = 0;
//					// 1~N-2 범위의 모든 배열은 탐색 ==> 0,N-1은 8방이 없으므로 제외
//					for (int r = 1; r < N - 1; r++) {
//						for (int c = 1; c < N - 1; c++) {
//							//현재 위치의 수가 큰수인지 작은 수 인지를 체크하기 위한 변수를 초기화 한다.
//							//모든 수들을 검사해야 하므로 여기서 선언한다.
//							int big_count = 0;
//							int small_count = 0;
//							for (int i = 0; i < d; i++) { //8방 검사
//								nr = r + dr[i];
//								nc = c + dc[i];
//								//경계검사를 하지 않는다
//								//==> why? 0이나 N-1 위치의 사방은 경계 밖이므로 검사해야 되지만
//								//		   1~N-2까지만 탐색하므로 경계를 벗어날 일이 없다.
//								// 큰수 라면   big_count 증가
//								if (matrix[nr][nc] < matrix[r][c])
//									big_count++;
//
//								// 작은수 라면 small_count 증가
//								if (matrix[nr][nc] > matrix[r][c])
//									small_count++;
//							}
//							//큰수라면
//							if (big_count == 8)
//								big++;
//							//작은수라면
//							if (small_count == 8)
//								small++;
//
//						}
//					}
//
//					System.out.println("#" + test_case + " " + big + " " + small);
//				}
//			}
//		}
//	}
//}







