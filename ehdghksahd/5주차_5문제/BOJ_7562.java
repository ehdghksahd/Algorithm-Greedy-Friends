import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/7562

// 최소...? BFS
public class Main{
    private static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
    private static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2}; // 시계 방향으로
    private static int N;

    private static boolean[][] visited;
    private static int[][] board;
    private static Queue<int[]> q;

    private static void BFS(int[] start, int[] des){
        q.offer(start);
        visited[start[0]][start[1]] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            if(r == des[0] && c == des[1]) {
                System.out.println(board[r][c]);
                break;
            }

            for(int i = 0; i < 8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if(!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        board[nr][nc] = board[r][c] + 1; // 이동한 칸 횟수 중첩
                    }

                }
            }
        }
    }

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            board = new int[N][N];
            visited = new boolean[N][N];
            q = new LinkedList<>();

            int[] cur = new int[2];
            cur[0] = Integer.parseInt(st.nextToken());
            cur[1] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int[] des = new int[2];
            des[0] = Integer.parseInt(st.nextToken());
            des[1] = Integer.parseInt(st.nextToken());

            BFS(cur, des);
        }

    }
}
