package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 삼각형 모양으로 숫자가 배치되어 있을 때,
        꼭대기에서 바닥까지 내려가면서 거쳐간 숫자의 합이 최대가 되도록 하기.
 * 규칙 :
   아래층으로 내려갈 때, 대각선 왼쪽 또는 대각선 오른쪽으로만 이동 가능.

 * 아이디어:
   dp[i][j] = (i, j) 위치까지 도달할 수 있는 최대 합

 * 점화식:
   dp[i][j] = triangle[i][j] + max(
      dp[i-1][j-1],  (왼쪽 위에서 오는 경우)
      dp[i-1][j]     (바로 위에서 오는 경우)
  )
 ex) 0층:
    dp[0][0] = 7

    1층:
    dp[1][0] = 7 + 3 = 10
    dp[1][1] = 7 + 8 = 15

    2층:
    dp[2][0] = 10 + 8 = 18
    dp[2][1] = max(10, 15) + 1 = 15 + 1 = 16
    dp[2][2] = 15 + 0 = 15

    3층:
    dp[3][0] = 18 + 2 = 20
    dp[3][1] = max(18, 16) + 7 = 18 + 7 = 25
    dp[3][2] = max(16, 15) + 4 = 16 + 4 = 20
    dp[3][3] = 15 + 4 = 19
    ........
 */
// 정수 삼각형(실버1)
public class BOJ_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 삼각형 배열
        int[][] triangle = new int[n][n];

        // 입력 받기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 배열
        int[][] dp = new int[n][n];

        // 초기값: 꼭대기
        dp[0][0] = triangle[0][0];

        // 위에서 아래로 내려가면서 계산
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                // 왼쪽 끝
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                }
                // 오른쪽 끝
                else if (j == i) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }
                // 중간
                else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
            }
        }

        // 마지막 층에서 최댓값 찾기
        int answer = 0;
        for (int j = 0; j < n; j++) {
            answer = Math.max(answer, dp[n-1][j]);
        }

        System.out.println(answer);
    }
}
