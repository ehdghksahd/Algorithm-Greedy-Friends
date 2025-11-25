import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static long totalMoves = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());

		adj = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		// BFS로 각 노드의 깊이 계산 루트 1번
		int[] depth = new int[n + 1];
		boolean[] visited = new boolean[n + 1];

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		visited[1] = true;
		depth[1] = 0;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			boolean isLeaf = true;

			for (int next : adj.get(cur)) {
				if (!visited[next]) {
					isLeaf = false;
					visited[next] = true;
					depth[next] = depth[cur] + 1;
					queue.offer(next);
				}
			}

			if (isLeaf && cur != 1) {
				totalMoves += depth[cur];
			}
		}

		// n=2인 노드 1이 루트이면서 리프가 아님
		// 이미 위에서 처리됨
		System.out.println(totalMoves % 2 == 1 ? "Yes" : "No");
	}
}