import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int l;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx = {-2,-1,1,2,2,1,-1,-2};
	static int[] dy = {1,2,2,1,-1,-2,-2,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 1. 나이트는 8방향으로 이동 가능.
		 * 2. 최소 이동 횟수는 BFS 사용.
		 * 3. 시작 위치에서 목표 위치까지 BFS로 탐색.
		 */

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			l = Integer.parseInt(br.readLine());
			board = new int[l][l];
			visited = new boolean[l][l];

			StringTokenizer st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());

			// 시작은 끝이어야한다.
			if (sx == ex && sy == ey) {
				System.out.println(0);
				continue;
			}

			System.out.println(bfs(sx, sy, ex, ey));
		}
	}

	private static int bfs(int sx, int sy, int ex, int ey) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{sx, sy, 0});
		visited[sx][sy] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int cnt = cur[2];

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= l || ny >= l) continue;
				if (visited[nx][ny]) continue;

				// 최종 도착해야함
				if (nx == ex && ny == ey) {
					return cnt + 1;
				}

				visited[nx][ny] = true;
				q.add(new int[]{nx, ny, cnt + 1});
			}
		}

		return 0;
	}
}
