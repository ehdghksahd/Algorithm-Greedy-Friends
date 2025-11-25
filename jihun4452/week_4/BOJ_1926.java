import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] grid;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		grid = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0; // 그림 개수
		int maxArea = 0; // 최대 넓이

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) {
					int area = bfs(i, j);
					count++;
					maxArea = Math.max(maxArea, area);
				}
			}
		}

		System.out.println(count);
		System.out.println(maxArea);
	}

	static int bfs(int startX, int startY) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{startX, startY});
		visited[startX][startY] = true;
		int area = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx >= 0 && nx < n && ny >= 0 && ny < m
					&& grid[nx][ny] == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.offer(new int[]{nx, ny});
					area++;
				}
			}
		}

		return area;
	}
}