// [BOJ] 2178: 미로 탐색
package somyoung.week4;

import java.io.*;
import java.util.*;

public class BOJ_2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        // 상우하좌
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = str.charAt(j) - '0'; // char -> int 변환
                // arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        // 문제에서는 (1, 1) -> (N, M)이지만 실제 배열은 (0, 0) -> (N-1, M-1)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] == 1){
                    arr[nx][ny] = arr[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        System.out.print(arr[N-1][M-1]);
    }
}