// [BOJ] 7576: 토마토
package somyoung.week4;

import java.io.*;
import java.util.*;

public class BOJ_7576 {
    static int N, M;
    static int[][] arr;
    static int[][] days;
    static Queue<int[]> queue = new LinkedList<>();
    // 상우하좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // col
        N = Integer.parseInt(st.nextToken()); // row

        arr = new int[N][M];
        days = new int[N][M];

        // days -1로 초기화
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                days[i][j] = -1;
            }
        }

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1){
                    queue.offer(new int[]{i, j}); // 익은 토마토 queue에 add
                    days[i][j] = 0; // 0일차
                }
            }
        }

        bfs();

        int result = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){

                if(arr[i][j] == 0){
                    System.out.print(-1);
                    return;
                }

                result = Math.max(result, days[i][j]); // 최종 day 갱신
            }
        }

        System.out.print(result);
    }

    static void bfs(){
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 익지 않은 토마토, 날짜 계산 안된 칸 마킹
                if(nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] == 0 && days[nx][ny] == -1){
                    arr[nx][ny] = 1;
                    days[nx][ny] = days[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}