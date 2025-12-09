package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
    문제: 배낭의 무게 제한 K가 있을 때, N개의 물건(각각 무게 W, 가치 V)을 넣어서 최대 가치를 구하기.(어떻게 넣어야 최대 가치일까?)
    제약:
    - 각 물건은 1개씩만 있음 (넣거나 안 넣거나)
    - 배낭 무게를 초과하면 안 됨

    dp[i][w] = i번째 물건까지 고려했을 때, 무게 w 이하로 담을 수 있는 최대 가치
    점화식:
     if (weight[i] > w):
        dp[i][w] = dp[i-1][w]  // 못 넣음 (무게 초과)
     else:
         dp[i][w] = max(
         dp[i-1][w],                      // 안 넣음
         dp[i-1][w-weight[i]] + value[i]  // 넣음
         )
 */
// 평범한 배낭(골드5)
public class BOJ_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 물건 개수
        int K = Integer.parseInt(st.nextToken());  // 배낭 무게 제한

        int[] weight = new int[N + 1];
        int[] value = new int[N + 1]; // 가치

        // 물건 정보 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        // DP 테이블
        int[][] dp = new int[N + 1][K + 1];

        // DP 계산
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= K; w++) {
                // 현재 물건을 넣을 수 없는 경우 (무게 초과)
                if (weight[i] > w) {
                    dp[i][w] = dp[i-1][w]; // 못넣음
                }
                // 현재 물건을 넣을 수 있는 경우
                else {
                    dp[i][w] = Math.max(
                            dp[i-1][w],                      // 안 넣음
                            dp[i-1][w-weight[i]] + value[i]  // 넣음
                    );
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
