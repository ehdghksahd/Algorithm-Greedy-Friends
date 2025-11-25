import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Point{
	int x, y;
	public Point(int x, int y){
		this.x= x;
		this.y=y;
	}
}
class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int N, M;
	static int[][] board, dis;
	static Queue<Point> q = new LinkedList<>();

	public static void bfs() {
		while (!q.isEmpty()) {
			Point current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				if (nx >= 1 && nx <= N && ny >= 1 && ny <= M && board[nx][ny] == 0) {
					board[nx][ny] = 1;
					q.offer(new Point(nx, ny));
					dis[nx][ny] = dis[current.x][current.y] + 1;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st1.nextToken()); //열
		N = Integer.parseInt(st1.nextToken()); //행

		board = new int[N+1][M+1];
		dis   = new int[N+1][M+1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1)
					q.offer(new Point(i, j)); //1이면 익은 토마토라 그때 좌표를 큐에 넣는다.
			}
		}
		bfs();
		boolean flag = true;
		int answer = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (board[i][j] == 0)
					flag = false;
			}
		}
		if (flag) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					answer = Math.max(answer, dis[i][j]);
				}
			}
			System.out.println(answer);
		}
		else System.out.println(-1);
	}
}