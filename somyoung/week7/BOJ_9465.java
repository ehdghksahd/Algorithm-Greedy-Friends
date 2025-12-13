// BOJ / 9465 / 스티커 / 실버1
package somyoung.week7;

import java.io.*;
import java.util.*;

public class BOJ_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            int col = Integer.parseInt(br.readLine());

            // dp[i][j] = j번째 열까지 고려했을 때, i번째 행의 스티커를 선택한 경우의 최댓값
            int[][] dp = new int[2][col];
            int[][] sticker = new int[2][col];

            for(int i=0; i<2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<col; j++){
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 예외 처리, 2일 때는 점화식 진입안하기 때문에 따로 처리안해도 됨
            if(col == 1){
                System.out.println(Math.max(sticker[0][0], sticker[1][0]));
                continue; // 다음 루프 실행
            }

            // 초기식, 최댓값 고려(두번째 열에서 스티커 2개 선택)
            // 첫번째 열
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];
            // 두번째 열
            dp[0][1] = sticker[0][1] + sticker[1][0];
            dp[1][1] = sticker[1][1] + sticker[0][0];

            // 점화식
            // dp[0][j] = max(dp[1][j-1], dp[0][j-2], dp[1][j-2]) + sticker[0][j]
            // dp[1][j] = max(dp[0][j-1], dp[0][j-2], dp[1][j-2]) + sticker[1][j]
            for(int j=2; j<col; j++){
                dp[0][j] = Math.max(
                        dp[1][j-1],
                        Math.max(dp[0][j-2], dp[1][j-2])
                ) + sticker[0][j];

                dp[1][j] = Math.max(
                        dp[0][j-1],
                        Math.max(dp[0][j-2], dp[1][j-2])
                ) + sticker[1][j];
            }

            System.out.println(Math.max(dp[0][col-1], dp[1][col-1]));
        }
    }
}

