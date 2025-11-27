import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
	int node, weight;
	public Edge(int node, int weight) {
		this.node = node;
		this.weight = weight;
	}
}

class Main {
	static int N;
	static ArrayList<Edge>[] tree;
	static int[] dist;
	static int farthestNode, maxDist;

	public static void bfs(int start) {
		dist = new int[N + 1];
		Arrays.fill(dist, -1);

		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		dist[start] = 0;

		farthestNode = start;
		maxDist = 0;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Edge next : tree[cur]) {
				if (dist[next.node] == -1) {
					dist[next.node] = dist[cur] + next.weight;
					q.offer(next.node);

					if (dist[next.node] > maxDist) {
						maxDist = dist[next.node];
						farthestNode = next.node;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		tree = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			tree[parent].add(new Edge(child, weight));
			tree[child].add(new Edge(parent, weight));
		}

		// 1에서 가장 먼 노드 찾기
		bfs(1);

		// 그 노드에서 다시 가장 먼 거리 = 지름
		bfs(farthestNode);

		System.out.println(maxDist);
	}
}