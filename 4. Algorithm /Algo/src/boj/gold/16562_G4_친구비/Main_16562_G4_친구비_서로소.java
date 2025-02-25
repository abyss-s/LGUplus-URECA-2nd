package p.boj.graph;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 모든 학생들이 친구가 되는 비용을 구하기 
 * 단, 친구의 친구는 내 친구다! 
 * ==> 결국 모든 원소를 union한 후 
 *     서로 친구가 되지 못하는 집합들의 root의 비용이 친구 사귀는 비용이다!!! 
 * 
 *     이때 union을 아무 원소나 하지 않고 친구되는 비용이 적은 원소를 root로 한다. 
 */
public class Main_16562_G4_친구비_서로소 {
	static int[] parents;
	static int[] pay;
	static int N, M, K;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		
		parents = new int[N+1];
		pay = new int[N+1];
		for(int i=1; i<=N; i++)
			pay[i] = sc.nextInt();
		
		make();
		
		for(int i=0; i<M; i++) {
			union(sc.nextInt(), sc.nextInt());
		}
		
		int sum = 0;
		for(int i=1; i<=N; i++) {
			if(parents[i] == i)
				sum += pay[i];
		}
		
		System.out.println(sum<=K? sum : "Oh no");
	}
	
	static void make() {
		for(int i=1; i<=N; i++)
			parents[i] = i;
	}
	
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(pay[parentA]<pay[parentB])
			parents[parentB] = parentA;
		else
			parents[parentA] = parentB;
	}
	
	static int find(int n) {
		if(parents[n]==n) return n;
		
		return parents[n] = find(parents[n]);
	}
}
