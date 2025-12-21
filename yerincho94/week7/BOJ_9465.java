package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 스티커 배치 : 2행 N열
 * 각 스티커에는 점수가 있고, 스티커를 떼면 상하좌우 인접한 스티커는 못 뗀다.
 * 최대 점수는?
 * 2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유 하지 않는 스티커 집합을 구해야 한다.
 *
 * i열의 0행을 선택하면:
 * - (i-1)열의 0행은 못 뗌 (좌우 인접)
 * - (i-1)열의 1행은 떼도 됨 (대각선)
 *
 * i열의 1행을 선택하면:
 * - (i-1)열의 1행은 못 뗌 (좌우 인접)
 * - (i-1)열의 0행은 떼도 됨 (대각선)
 */
// 스티커(실버1) - DP
public class BOJ_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            // 스티커 점수 입력
            int[][] sticker = new int[2][N];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // DP 테이블
            int[][] dp = new int[2][N];

            // 초기값
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];

            // N이 1이면 초기값이 답
            if (N == 1) {
                System.out.println(Math.max(dp[0][0], dp[1][0]));
                continue;
            }

            // 1번째 열 (i-2가 없으므로 따로 처리)
            dp[0][1] = dp[1][0] + sticker[0][1];
            dp[1][1] = dp[0][0] + sticker[1][1];

            // 2번째 열부터
            for (int i = 2; i < N; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + sticker[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + sticker[1][i];
            }

            // 마지막 열의 최댓값
            System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
        }
    }
}
