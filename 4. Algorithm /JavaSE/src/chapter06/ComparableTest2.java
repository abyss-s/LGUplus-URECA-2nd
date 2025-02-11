package chapter06;

import java.util.Arrays;

/*
 * 람다
 * - 자바에서도 람다 표현식을 제공한다.
 *   () ->{ }
 *
 *   () 	: 인자가 없거나 두개 이상일때 반드시 소괄호로 인자를 표시한다.
 *   { }	: 문장이 2문장 이상일 때 사용한다.
 *
 */
public class ComparableTest2 {
	public static void main(String[] args) {
		Employee[] emps = {
				new Employee("2", "1", 5),
				new Employee("1", "1", 1),
				new Employee("3", "1", 2),
		};


		Integer[] array2 = {5, 1, 7, 2};
		Arrays.sort(array2, (a, b) -> {
			return b - a;
		});
		System.out.println(Arrays.toString(array2));

		Arrays.sort(emps, (a, b) -> {
			return b.getEmpno().compareTo(a.getEmpno());
		});
		System.out.println(Arrays.toString(emps));


	}

}