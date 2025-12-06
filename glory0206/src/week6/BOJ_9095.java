package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9095 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 횟수

        StringBuilder sb = new StringBuilder();
        int[] dp = new int[15]; // n은 양수이며 11보다 작다
        dp[1] = 1; // 1을 만드는 경우의 수: 1개(1)
        dp[2] = 2; // 2를 만드는 경우의 수: 2개(1+1, 2)
        dp[3] = 4; // 3을 만드는 경우의 수: 4개(1+1+1, 1+2, 2+1, 3)
        // dp[4] -> dp[1]+3, dp[2]+2, dp[3]+1으로 4를 만들 수 있음
        for(int i=0; i<T; i++){
            int n = Integer.parseInt(br.readLine());
            for(int j=4; j<=n; j++){
                dp[j] = dp[j-3] + dp[j-2] + dp[j-1];
            }
            sb.append(dp[n] + "\n");
        }

        System.out.println(sb);
    }
}
