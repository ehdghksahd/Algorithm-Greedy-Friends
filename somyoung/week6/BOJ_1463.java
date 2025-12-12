// BOJ / 1463 / 1로 만들기 / 실버3
package somyoung.week6;

import java.io.*;

public class BOJ_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        //  dp[n] -> n를 1로 만들기 위해 필요한 연산을 사용하는 횟수의 최솟값
        int[] dp = new int[n+1];

        // 초기값 dp[1] = 0
        dp[1] = 0;

        // 점화식
        // dp[n] = dp[n/3] + 1
        // dp[n] = dp[n/2] + 1
        // dp[n] = dp[n-1] + 1
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + 1;
            if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1);
            if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1);
        }

        System.out.print(dp[n]);
    }
}

