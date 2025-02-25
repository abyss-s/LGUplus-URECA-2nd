package p.boj.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


//592ms
public class Main_1922_G4_네트워크연결_크루스칼 {

	static int[] parents;
	static Edge[] list;
	static int n;
	static int m;
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e) {
			return weight - e.weight;
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 최소 스패닝 트리/네트워크 연결
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		list = new Edge[m];

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[i]=new Edge(from, to, weight);

		}
		Arrays.sort(list);
		make();
		
		long result = 0, cnt=0;
		for (Edge e : list) {
			if (union(e.from, e.to)) {
				result += e.weight;
				if(++cnt == n-1) break;
			}
		}
		System.out.println(result);

	}
	static void make() {
		parents = new int[n + 1];
		for (int i = 0; i <= n; i++)
			parents[i] = i;
	}
	static int find(int v) {
		if (parents[v] == v)
			return v;
		return parents[v] = find(parents[v]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot==bRoot) return false;
			
		parents[aRoot] = bRoot;
		return true;
	}


}