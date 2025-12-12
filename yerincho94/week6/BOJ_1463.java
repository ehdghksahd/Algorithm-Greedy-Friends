package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  10은 2로 나누어 떨어지니까 , 10 -> 5 근데 오는 2 or 3으로 안나누어 떨어지니까 -1
 *  - 10 -> 5 -> 4 -> 2 -> 1 (총 연산 횟수 : 4)
 *  다른 계산으로는,
 *  - 10 -> 9 -> 3 -> 1 (총 연산 횟수 : 3)
 */
// 1로 만들기(실버3)
public class BOJ_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        dp[1] = 0; // 1은 이미 1이므로 연산 0번

        // 2부터 n까지 차례대로 계산
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1; // 일단 1빼기
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1); // 2로 나누어떨어질 때
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1); // 3로 나누어떨어질 때
        }

        System.out.println(dp[n]);
    }
}
