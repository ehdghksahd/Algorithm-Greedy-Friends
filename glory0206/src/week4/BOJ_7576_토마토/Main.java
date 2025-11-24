package week4.BOJ_7576_토마토;

import java.util.*;
import java.io.*;

public class Main{
    static int[][] box;
    static int[][] count;
    static int N, M;
    static Queue<int[]> q = new LinkedList<>();

    // 동서남북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        count = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1){ // 익은 토마토의 위치 add
                    q.add(new int[]{i, j});
                }
            }
        }
        // bfs 실행
        bfs();

        int result = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(box[i][j] == 0){ // 익지 않은 토마토가 하나라도 있다면 토마토는 모두 익지 못함
                    System.out.println(-1);
                    return;
                }
                result = Math.max(result, count[i][j]); // 토마토가 익었다면 새로히 날짜 갱신
            }
        }
        System.out.println(result);
    }

    static void bfs(){
        while(!q.isEmpty()){
            int[] spot = q.poll();
            int cx = spot[0];
            int cy = spot[1];

            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=M){// box 바깥
                    continue;
                }
                if(box[nx][ny] != 0){// 토마토가 없거나 이미 익어있는 곳
                    continue;
                }
                box[nx][ny] = 1;// 토마토 익음
                count[nx][ny] = count[cx][cy] + 1;// 하루 증가
                q.add(new int[] {nx, ny});// 방금 익은 토마토
            }
        }
    }
}
