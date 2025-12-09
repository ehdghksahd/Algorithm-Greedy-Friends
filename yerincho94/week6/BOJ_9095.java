package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * n=1: 1
 * n=2: 2
 * n=3: 4
 * n=4: 7 = 4 + 2 + 1 = n=3 + n=2 + n=1
 * 점화식: dp[n] = dp[n-1] + dp[n-2] + dp[n-3]
 *
 * n을 만드는 방법:
 * 1. (n-1)을 만들고 + 1을 더함 → dp[n-1]가지
 * 2. (n-2)를 만들고 + 2를 더함 → dp[n-2]가지
 * 3. (n-3)을 만들고 + 3을 더함 → dp[n-3]가지
 * 합: dp[n] = dp[n-1] + dp[n-2] + dp[n-3]
 */
// 1,2,3 더하기(실버3)
public class BOJ_9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        int[] dp = new int[12]; // 최대 11까지

        // 초기값
        dp[1] = 1;  // 1을 만드는 방법: [1]
        dp[2] = 2;  // 2를 만드는 방법: [1+1], [2]
        dp[3] = 4;  // 3을 만드는 방법: [1+1+1], [1+2], [2+1], [3]

        // 4부터 11까지 계산
        for (int i = 4; i <= 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        // 테스트 케이스 처리
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}
