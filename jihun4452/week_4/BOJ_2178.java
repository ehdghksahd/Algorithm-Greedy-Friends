import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x, y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Main {

	//1은 이동할 수 있는 칸 0은 이동할 수 없는 칸
	//값 두개를 입력받고 지나야하는 최소의 칸 수를 구해야한다.
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static int[][] board, dis;
	static int N, M,answer =0;

	public static void bfs(int x, int y) {
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(x, y));
		board[x][y] = 0;
		while(!Q.isEmpty()){
			Point tmp= Q.poll();
			for(int i= 0; i<4; i++){
				int nx= tmp.x +dx[i];
				int ny= tmp.y +dy[i]; //범위 내에있고 0이어야함
				if (nx < 1 || nx > N || ny < 1 || ny > M) continue;
				if (board[nx][ny] == 1) {
					board[nx][ny] = 0; //방문
					dis[nx][ny] = dis[tmp.x][tmp.y] + 1; //tmp가 현재 위치임 거기에 1을 더한게 본진이됨
					Q.offer(new Point(nx, ny));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1=new StringTokenizer(br.readLine());
		N= Integer.parseInt(st1.nextToken());
		M= Integer.parseInt(st1.nextToken());
		board = new int[N+1][M+1];
		dis = new int[N+1][M+1];

		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				board[i][j] = line.charAt(j-1) - '0';
			}
		}
		dis[1][1] = 1;
		bfs(1,1);
		System.out.println(dis[N][M]);
	}
}
