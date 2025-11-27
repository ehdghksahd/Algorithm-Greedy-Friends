package week4.BOJ_2178_미로_탐색;

import java.util.*;
import java.io.*;

public class Main{
    static int[][] miro;
    static boolean[][] visited;
    static int N, M;

    // 동서남북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 미로 초기화
        miro = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                miro[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(bfs(0,0));
    }

    static int bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true; // 해당 위치 방문
        q.add(new int[]{x, y});

        while(!q.isEmpty()){
            int[] spot = q.poll();
            int cx = spot[0];
            int cy = spot[1];

            if(cx==N-1 && cy==M-1){ // 도착지에 도달했을 경우
                return miro[cx][cy];
            }

            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=M){ // 미로 밖으로 나갔을 경우
                    continue;
                }
                if(miro[nx][ny] == 0){ // 해당 위치가 벽인 경우
                    continue;
                }
                if(visited[nx][ny] == true){ // 이미 다녀간 곳인 경우
                    continue;
                }

                visited[nx][ny] = true;
                miro[nx][ny] = miro[cx][cy] + 1;
                q.add(new int[]{nx, ny});
            }
        }
        return -1;
    }
}
