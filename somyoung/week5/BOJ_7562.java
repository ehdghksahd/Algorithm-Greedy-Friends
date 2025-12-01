// [BOJ] 7562: 나이트의 이동
package somyoung.week5;

import java.io.*;
import java.util.*;

public class BOJ_7562 {
    // 왼쪽 위부터 시계 방향, 나이트 이동 방향
    static int[] moveDx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] moveDy = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            int l = Integer.parseInt(br.readLine()); // 체스판 한 변의 길이

            int[][] arr = new int[l][l];

            // 출발 좌표
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] start = new int[2];
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());

            // 도착 좌표
            st = new StringTokenizer(br.readLine());
            int[] end = new int[2];
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());

            // 출발점과 도착점이 같을 때
            if(start[0] == end[0] && start[1] == end[1]){
                System.out.println(0); // 이동 횟수 0
                continue;
            }

            bfs(arr, start, end); // 배열, 출발 좌표, 도착 좌표

            System.out.println(arr[end[0]][end[1]]); // 도착 좌표에 마킹된 이동 횟수
        }
    }

    static void bfs(int[][] arr, int[] start, int[] end){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1]}); // 출발 좌표 삽입
        int l = arr.length; // 체스판 한 변의 길이

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i=0; i<8; i++){ // 나이트 이동 방향 탐색
                int nx = x + moveDx[i];
                int ny = y + moveDy[i];

                if(nx >= 0 && nx < l && ny >= 0 && ny < l && arr[nx][ny] == 0){
                    arr[nx][ny] = arr[x][y] + 1;
                    if(nx == end[0] && ny == end[1]) return; // 도착 좌표 도달 시 탈출
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}

