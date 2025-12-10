package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
    i번 사람에게 인사하면, L[i] 체력 ↓, J[i] 기쁨 ↑
    목표:
        주어진 체력 내에서 최대한의 기쁨(J[i])
    if 체력(L[i]) < 0 (0or음수) -> 기쁨(J[i]) = 0
    시작 : 체력 100, 기쁨 0 start
    * 순서대로 인사하는게 아니라, 각 사람에게 인사할지 말지 선택할 수 있음!!! 모든 조합 생각해보기

    dp[i][strength] = i번째까지 고려, 체력 strength 소모 시 최대 기쁨
    각 사람마다:
        1. 인사 안함 -> dp[i-1][strength]
        2. 인사 함 -> dp[i-1][strength-L[i]] + J[i]
    둘 중 최대값 선택.
 */
// 안녕(실버2)
public class BOJ_1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 사람의 수

        int[] L = new int[N + 1]; // 체력
        int[] J = new int[N + 1]; // 기쁨

        // 체력 감소
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        // 기쁨 증가
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            J[i] = Integer.parseInt(st.nextToken());
        }

        // dp테이블
        int[][] dp = new int[N + 1][100]; // dp[i][strength] = i번째까지, 체력(strength)소모했을 때 최대기쁨

        for (int i = 1; i <= N; i++) {
            for (int strength = 0; strength < 100; strength++) { // 99까지만
                if(L[i] > strength) dp[i][strength] = dp[i-1][strength]; // 체력부족 사망
                else {
                    dp[i][strength] = Math.max(
                            dp[i-1][strength], // 인사안함
                            dp[i-1][strength -L[i]] + J[i] // 인사함
                    );
                }
            }
        }
        System.out.println(dp[N][99]); // 체력(strength) 99까지 소모했을때 최대기쁨
    }
}
