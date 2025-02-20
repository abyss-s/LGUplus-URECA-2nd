package divideconquer;

import java.util.Arrays;

/*
 * 탐색하려는 데이타가 배열 내에 있는지 확인하기 위해
 * 1. 배열의 중간 값과 비교해서 작으면 왼쪽, 크면 오른쪽으로 이동한다.
 * 2. 찾을 때까지 1번을 반복 후 찾은 위치의 index를 반환한다. 못 찾은 경우 -1을 반환한다.
 */
public class BinarySearchTest {
	private static int[] values = {20, 3, 45, 11, 15, 21, 29, 72, 59, 65};

	public static void main(String[] args) {
		//step0. 정렬하기: 이진 탐색은 정렬된 데이터만 탐색할 수 있다.
		Arrays.sort(values);
		System.out.println(Arrays.toString(values));
		System.out.println(binarySearch1(65));
		System.out.println(binarySearch1(2));
		System.out.println("===================================");
		System.out.println(binarySearch2(65, 0, values.length - 1));
		System.out.println(binarySearch2(2, 0, values.length - 1));
		System.out.println("===================================");

		System.out.println(Arrays.binarySearch(values, 2));
		// 못 찾으면 -insertion point-1 값 리턴(0인덱스와 구분하기 위해 음수화시키고 -1)
		//Math.abs(idx)-1 위치에 데이터를  추가하면 된다.
		
	}

	private static int binarySearch1(int key) {
		// 찾으려는 범위의 시작 위치, 끝 위치, 중간 위치를 위한 변수를 선언한다.
		int start = 0;
		int end = values.length - 1;
		int mid;

		// 데이터를 찾을 때까지 반복 하면서
		while (start <= end) {
			// 주어진 범위를 이용해서 mid값을 구한다.
			mid = (start + end) >> 1;

			// mid 위치의 데이터와 탐색하려는 데이터가 같으면 mid 위치를 반환한다.
			if (key == values[mid]) {
				return mid;
			}
			// mid 위치의 데이터 보다 탐색하려는 데이터가 작으면 end 위치를 mid - 1로 바꾼다.
			if (key < values[mid]) {
				end = mid - 1;
			}
			// mid 위치의 데이터 보다 탐색하려는 데이터가 크면 start 위치를 mid + 1로 바꾼다.
			if (key > values[mid]) {
				start = mid + 1;
			}
		}
		// 데이터가 없는 경우 -1을 리턴
		return -1;
	}

	// 데이터를 못 찾은 경우 -1을 리턴
	private static int binarySearch2(int key, int start, int end) {
		// base case: start가 end보다 큰 경우
		if (start > end) {
			return -1;
		}

		// mid 값을 구한다.
		int mid = (start + end) >> 1;

		// mid 위치의 데이터와 탐색하려는 데이터가 같으면 mid를 반환한다.
		if (key == values[mid]) {
			return mid;
		}
		// mid 위치의 데이터 보다 탐색하려는 데이터가 작으면 end 위치를 mid - 1로 바꾼다.
		if (key < values[mid]) {
			return binarySearch2(key, start, mid - 1);
		}
		// mid 위치의 데이터 보다 탐색하려는 데이터가 크면 start 위치를 mid + 1로 바꾼다.
		return binarySearch2(key, mid + 1, end);

	}
}