package greedy;

import java.util.Scanner;

/**
 * Greedy 알고리즘
 * - 현재 단계에서 선택할 수 있는 것 중 가장 좋은것을 선택하는 알고리즘
 * - 현재 단계에서 가장 좋은 것을 선택했지만 모든 선택을 끝낸 최종의 값은 가장 좋은 선택이 아닐 수도 있다.
 * - 방법]
 * 1. 최선의  선택을 위해 문제 해결을 위한 기준(가치)으로 정렬을 하여 가장 좋은 것을 선택한다.
 * 2. 정렬할 수 없는 경우 현재 선택할 수 있는 데이타 중 가장 좋을 것을 탐색하여 선택한다.
 * <p>
 * <p>
 * 동전 바꾸기 - 거스름 돈을 가장 적은 동전 수로 바꾸자
 */
public class GreedyEx1 {
	static int[] coin = {500, 100, 50, 10};                    //잘나오지만
	//	int[] coin={500,400,100,50,10};				//500원을 먼저 선택하기 때문에 안나옴
	static int[] result = new int[coin.length];

	public static void coinChange(int money) {
		for (int i = 0, size = coin.length; i < size; i++) {
			result[i] = money / coin[i]; // 정수 / 정수 = 몫
			money %= coin[i];
		}
	}

	public static void print() {
		System.out.println("동전 교환 결과");
		for (int i = 0; i < coin.length; i++) {
			if (result[i] == 0) continue;
			System.out.println(coin[i] + "원 : " + result[i]);
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("거스름돈을 입력하세요:");
		int money = s.nextInt();
		System.out.println();

		coinChange(money);
		print();
	}
}












