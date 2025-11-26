import java.util.*;
import java.io.*;

// '최소' 들어가면 어지간하면 BFS
//https://www.acmicpc.net/problem/2178

public class Main{

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0}; // 상하좌우 대각 없음

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0'; // 아스키 코드를 활용한 char to int
            }
        }

        q.offer(new int[] {0, 0});
        while(!q.isEmpty()) {
            int[] dir = q.poll();
            int x = dir[0];
            int y = dir[1];

            if(x == M - 1 && y == N - 1) {
                System.out.println(map[y][x]);
                break;
            }

            for(int i = 0; i < 4; i++) { // 상하좌우
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if(map[ny][nx] == 1) { // 지나 갈때마다 map의 값을 ++ 해주면서 누적 -> 마지막 자리 최솟값
                        // 1이 아닌값이면 0이거나 이미 지나온것
                        q.offer(new int[] {nx, ny});
                        map[ny][nx] = map[y][x] + 1;
                    }
                }
            }
        }
    }
}
