package subset;

/**
 * subset의 개수는 2의 n승 개이므로
 * 0 : 원소를 선택 안함    1:원소를 선택함
 * <p>
 * 0      0000:  원소 하나도 선택안한 서브셋
 * 1      0001:  맨 끝의 원소 하나를 선택한 서브셋
 * 2      0010:  맨 끝에서 두번째 원소 하나를 선택한 서브셋
 * 3      0011:  맨 끝의 두개의 원소를 선택한 서브셋
 * ...
 * size-1 1111 : 모든 원소를 선택한 서브셋
 * <p>
 * 시간 복잡도 2^n
 */
public class SubsetTest1 {
	static String[] input = "abcd".split("");

	public static void main(String[] args) {
		int[] subset = new int[input.length];
		for (int i = 0; i < 2; i++) {
			subset[0] = i;
			for (int j = 0; j < 2; j++) {
				subset[1] = j;
				for (int k = 0; k < 2; k++) {
					subset[2] = k;
					for (int l = 0; l < 2; l++) {
						subset[3] = l;
						print(subset);
//						System.out.println(Arrays.toString(subset));
					}
				}
			}
		}
	}

	private static void print(int[] subset) {
		int k = 0;
		System.out.print("[");
		for (int s : subset) {
			if (s != 0) {
				System.out.print(input[k] + " ");
			}
			k++;
		}
		System.out.println("]");
	}
}












