package week4.BOJ_1926_그림;

import java.util.*;
import java.io.*;

public class Main{
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    // 동서남북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        // 도화지 채우기
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        int maxSize = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    int size = bfs(i, j);
                    count++;
                    maxSize = Math.max(maxSize, size);
                }
            }
        }
        System.out.println(count);
        System.out.println(maxSize);
    }

    static int bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new int[]{x, y});
        int size = 1; // 그림의 크기

        while(!q.isEmpty()){
            int[] spot = q.poll();
            int cx = spot[0];
            int cy = spot[1];

            for(int i=0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=M){
                    continue;
                }
                if(map[nx][ny] == 0){
                    continue;
                }
                if(visited[nx][ny]){
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                size++; // 조건문에 의해 넘어가지 않는다면, 그림이 그려져있기에 size up
            }
        }
        return size;
    }
}
