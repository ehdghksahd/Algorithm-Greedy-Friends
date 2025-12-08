// BOJ / 9095 / 1, 2, 3 더하기 / 실버3
package somyoung.week6;

import java.io.*;

public class BOJ_9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        // dp[n] -> n을 1, 2, 3의 합으로 나타내는 방법의 수
        // n은 양수이며, 11보다 작다
        int[] dp = new int[11]; // 10 + 1 (1-index-base)

        // 초기값
        // dp[1] = 1 // 1
        // dp[2] = 2 // 1+1, 2
        // dp[3] = 4 // 3, 1+2, 2+1, 1+1+1
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        // 점화식
        // dp[n]
        // 3+1, 1+2+1, 2+1+1, 1+1+1+1 // dp[n-1]개의 경우 각각에 마지막에 +1을 붙이는 경우
        // -> dp[n-1] + 1
        // 1+1+2, 2+2
        // -> dp[n-2] + 2
        // 1+3
        // -> dp[n-3] + 3
        // 그러므로, dp[n] = dp[n-1] + dp[n-2] + dp[n-3]
        for(int i=4; i<11; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}

