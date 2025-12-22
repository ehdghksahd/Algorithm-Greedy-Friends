package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            int n = Integer.parseInt(br.readLine()); // 행의 수
            int[][] sticker = new int[2][n+1];

            // 스티커 점수 부여
            for(int i=0; i<2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=1; j<=n; j++){
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 2개 중 하나도 고르지 않는다. 위의 스티커를 고른다. 아래의 스티커를 고른다.
            int[][] dp = new int[3][n+1]; // [0][]: 고르지 않음, [1][]: 위를 고름, [2][]: 아래를 고름

            dp[1][1] = sticker[0][1]; // 첫 번째로 위를 고름
            dp[2][1] = sticker[1][1]; // 첫 번째로 아래를 고름

            for(int i=2; i<=n; i++){
                dp[0][i] = Math.max(Math.max(dp[0][i-1], dp[1][i-1]), dp[2][i-1]);
                dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + sticker[0][i];
                dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]) + sticker[1][i];
            }

            int answer = Math.max(Math.max(dp[0][n], dp[1][n]), dp[2][n]);

            System.out.println(answer);
        }
    }
}
