import java.util.*;
import java.io.*;


// https://www.acmicpc.net/problem/7576

// 1 : 익은거, 0 : 익지 않은거, -1 : 없는거
// '최소' 일수 -> 최소 들어감 BFS
// 익은 토마토를 먼저 찾고 -> 상하좌우 안익은 토마토 찾고 -> 익은 토마토가 되었으므로 반복....?

public class Main{
    private static int[] dr = {-1, 1, 0, 0}; // 상하좌우 대각 없음
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] storage = new int [N][M];
        int[][] visited = new int[N][M]; // 각 칸마다 영향받았다면 가중치 더해감
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                storage[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1;
                if(storage[i][j] == 1) {
                    visited[i][j] = 0;
                    q.offer(new int[] {i, j});  // 시작전에 미리 q에 익은토마토 넣기
                }
            }
        }

        while(!q.isEmpty()) {
            int[] dir = q.poll();
            int r = dir[0];
            int c = dir[1];

            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if(storage[nr][nc] == 0 && visited[nr][nc] == -1) {
                        visited[nr][nc] = visited[r][c] + 1;
                        q.offer(new int[] {nr, nc});
                    }
                }
            }
        }

        int answer = 0;
        boolean chk = true;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                answer = Math.max(answer, visited[i][j]);
                if(storage[i][j] == 0 && visited[i][j] == -1) {
                    answer = -1;
                    chk = false;
                    break;
                }
            }
            if(!chk) break;
        }
        System.out.println(answer);
    }
}
