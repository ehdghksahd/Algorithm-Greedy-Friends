package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
  규칙: i번째 포도주를 기준으로 3가지 경우:
    1. i번째를 안 마심 → dp[i-1]
    2. i번째만 마심 (i-1번째 안 마심) → dp[i-2] + wine[i]
    3. i-1번째, i번째 둘 다 마심 (i-2번째 안 마심) → dp[i-3] + wine[i-1] + wine[i]

    dp[i] = max(위 3가지)

 */
// 포도주 시식(실버1)
public class BOJ_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] wine = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n + 1];

        dp[0] = 0;  // 포도주 0개
        dp[1] = wine[1];  // 포도주 1개
        if (n >= 2) {
            dp[2] = wine[1] + wine[2];  // 포도주 2개
        }

        // 3번째부터 계산
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(
                    dp[i-1], // i번째 안 마심
                    Math.max(
                            dp[i-2] + wine[i],               // i번째만 마심
                            dp[i-3] + wine[i-1] + wine[i]    // i-1, i번째 마심
                    )
            );
        }
        System.out.println(dp[n]);
    }
}
