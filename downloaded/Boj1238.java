import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1238 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static ArrayList<Node>[] list;
	static ArrayList<Node>[] list_reverse;
	static int[] dist;
	static int[] dist_reverse;
	static boolean[] visited;
	static int N, M, X;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		X = Integer.parseInt(st.nextToken()); // 모이는 곳.. 출력

		list = new ArrayList[N + 1];
		list_reverse = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Node>();
			list_reverse[i] = new ArrayList<Node>();
		}

		int now, next, cost;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			now = Integer.parseInt(st.nextToken());
			next = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			list[now].add(new Node(next, cost));
			list_reverse[next].add(new Node(now,cost));
		} // Graph
		
		int max = Integer.MIN_VALUE;
		int departCost = 0;
		int arriveCost = 0;
		
		dist = new int[N + 1];
		dist_reverse = new int[N + 1];
		
		
		Arrays.fill(dist, INF); // 거리 배열 초기화.
		Arrays.fill(dist_reverse, INF); // 거리 배열 초기화.
		
		dijstra(X,dist_reverse,list_reverse);
		dijstra(X,dist,list);
		
		for (int i = 1; i <= N; i++) {
			System.out.println(i);
			System.out.println(departCost = dist[i]);
			System.out.println(arriveCost = dist_reverse[i]);
			System.out.println("============================");
			cost = departCost + arriveCost;
			if(max<cost)max=cost;
		}
		System.out.println(max);
	}

	private static void dijstra(int start, int[] di, ArrayList<Node>[] li) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		di[start] = 0;
		pq.offer(new Node(start, di[start]));
		Node node;
		while (!pq.isEmpty()) {
			node = pq.poll();
			
			for (int i = 0; i < li[node.index].size(); i++) {
				Node temp = li[node.index].get(i);

				if (di[temp.index] > di[node.index] + temp.cost) {
					int cost = di[temp.index] = di[node.index] + temp.cost;
					pq.offer(new Node(temp.index, cost));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int index;
		int cost;

		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return cost - o.cost;
		}

		@Override
		public String toString() {
			return "Node [index=" + index + ", cost=" + cost + "]";
		}

	}
}
