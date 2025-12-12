// [BOJ] 9663: N-Queen
package somyoung.week5;

import java.io.*;
import java.util.*;

public class BOJ_9663 {
    // 왼쪽 위부터 시계 방향, 나이트 이동 방향
    static int N;
    static int cnt;

    static boolean[] col; // 열
    static boolean[] leftDown; // 대각선 왼쪽 아래로 -> row + col 이면 같은 대각선
    static boolean[] rightDown; // 대각선 오른쪽 아래로 -> row - col + N(음수 방지) 이면 같은 대각선

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cnt = 0;

        col = new boolean[N];
        leftDown = new boolean[N*2]; // 대각선의 개수 2n - 1이므로, 2n
        rightDown = new boolean[N*2];

        dfs(0); // row 0부터 탐색

        System.out.print(cnt);
    }

    static void dfs(int row){
        if(row == N){ // 모든 행에 퀸 배치 완료
            cnt++;
            return;
        }

        for(int i=0; i<N; i++){ // 각 열에 퀸 배치
            // 해당 칸을 기준으로, 같은 열/같은 왼쪽 대각선, 같은 오른쪽 대각선 중에 true가 하나라도 존재하면 continue
            if(col[i] || leftDown[row + i] || rightDown[row - i + N]){
                continue;
            }

            // 해당 칸에 퀸 배치 -> 열, 왼쪽 대각선, 오른쪽 대각선 true 마킹
            col[i] = true;
            leftDown[row + i] = true;
            rightDown[row - i + N] = true;

            // 다음 행
            dfs(row + 1);

            // dfs를 빠져나온 후 백트래킹 처리
            col[i] = false;
            leftDown[row + i] = false;
            rightDown[row - i + N] = false;
        }
    }
}