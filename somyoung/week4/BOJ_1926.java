// [BOJ] 1926: 그림
package somyoung.week4;

import java.io.*;
import java.util.*;

public class BOJ_1926 {
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    // 상우하좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0; // 그림 개수
        int maxValue = 0; // 그림 최대 넓이

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(arr[i][j] == 1 && !visited[i][j]){
                    int area = bfs(i, j);
                    cnt++;
                    maxValue = Math.max(maxValue, area);
                }
            }
        }

        System.out.println(cnt);
        System.out.println(maxValue);
    }

    static int bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int area = 1; // x,y 좌표 처음 넓이 1

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int i=0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] == 1 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    area++;
                }
            }
        }

        return area;
    }
}