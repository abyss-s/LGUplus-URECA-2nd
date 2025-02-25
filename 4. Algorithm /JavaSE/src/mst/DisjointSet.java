package mst;

import java.util.Arrays;

/**
 * 서로소 집합(Disjoint Set)
 * - 서로소 관계
 * - 상호 배타 집합들은 서로 중복으로 포함된 원소가 없는 집합들이다. => 두 집합에 교집합이 없는 관계
 * - 집합에 속한 하나의 특정 멤버(대표자 root)를 통해 각 집합들을 구분한다.
 * <p>
 * 방법
 * 1. makeset(x) : x를 원소로 갖는 최소 단위 집합 만들기  => x가 대표자(root)로 만들어 놓자
 * 2. findset(x) : x원소의 root를 찾는 함수
 * 3. union(x, y): x의 집합과 y의 집합을 합친다.
 * => x의 root를 찾고 y의 root를 찾아  두 집합 중
 * 하나의 집합의  root를 한 다른 하나의 집합의 원소로 만든다.
 * <p>
 * - 서로소 집합 응용
 * - MST(최소 신장 트리)의 크루스칼 알고리즘에 적용
 * <p>
 * 시간 복잡도
 * 최악의 경우 2N(초기 make 횟수+pathCompression횟수) + 2(루트까지 find하는 횟수)*유니온횟수(E)
 */
public class DisjointSet {
	static int N;
	// 원소의 부모를 담는 배열
	static int[] parents;

	/*
	 * 모든 원소들에 대한 초기 값을 설정 하는 함수
	 * - 자기 자신을 root로 설정 한다.
	 */
	public static void make() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	/*
	 * 인자로 전달 받은 원소(v)의 root를 찾는 기능
	 */
	public static int find(int v) {
		// root가 v, 나 자신인 경우 => root를 찾았으니 종료
		if (parents[v] == v)
			return v;
//		내가 root가 아닌 부모가 있는 상황 => 부모의 root를 또 찾으러 가기!
//		return find(parents[v]); // 문제점: 편향 트리인 경우 시간복잡도 O(n) => path compression 필요

		/*
		 * path compression: 찾아온 root를 나의 부모로 변경
		 */
//		parents[v] = find(parents[v]);
//		return parents[v];
		return parents[v] = find(parents[v]);
	}

	/*
	 * 인자로 받은 두 원소의 집합이 다른 경우 합치는 기능
	 */
	public static boolean union(int a, int b) {
		// 두 원소의 root를 찾아야 한다.
		int aRoot = find(a);
		int bRoot = find(b);

		// root가 같으면 cycle이 발생할 수 있기 때문에 union하면 안됨!
		if (aRoot == bRoot) return false;

		parents[a] = bRoot; // 서로 합치기
		// parents[b] = aRoot; // 같은 역할
		return true;
	}

	public static void main(String[] args) {
		N = 6;
		parents = new int[N];
		make();
		System.out.println(Arrays.toString(parents));
		union(1, 2);
		System.out.println(Arrays.toString(parents));
		union(3, 4);
		System.out.println(Arrays.toString(parents));
		union(5, 3);
		System.out.println(Arrays.toString(parents));
		union(1, 5);
		System.out.println(Arrays.toString(parents));
		System.out.println(find(1));
		System.out.println(Arrays.toString(parents));
		System.out.println(find(4));
		System.out.println(Arrays.toString(parents));
	}
}
