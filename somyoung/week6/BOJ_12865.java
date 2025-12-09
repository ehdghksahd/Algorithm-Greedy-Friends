// BOJ / 12865 / 평범한 배낭 / 골드5
package somyoung.week6;

import java.io.*;
import java.util.*;

public class BOJ_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 1-index-base
        int[] W = new int[N+1]; // 무게
        int[] V = new int[N+1]; // 가치

        // dp[i][k]:
        // i번째 물건까지 고려했을 때,
        // 허용 무게 K 이하에서 얻을 수 있는 최대 가치
        int[][] dp = new int[N+1][K+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        // 초기식
        // dp[0][k] = 0 // 물건이 0개면 최대 가치는 0
        // dp[i][0] = 0 // 허용 무게가 0이면 물건을 담을 수 없으므로 0
        // 이차 배열은 0으로 초기화되므로, 따로 지정해 줄 필요는 없음

        // 점화식
        // 담지 못하면, 이전 탐색의 가치와 똑같이 마킹
        // W[i] > K:  dp[i][j] = dp[i-1][j]

        // 담을 수 있는 경우, max(현재 물건을 담지 않은 경우, 현재 물건을 담은 경우)
        // W[i] <= K: dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - W[i]] + V[i])

        for(int i=1; i<=N; i++){
            for(int j=1; j<=K; j++){
                if(W[i] > j){ // 현재 물건 담을 수 없음
                    dp[i][j] = dp[i-1][j];
                } else { // 담을 수 있음
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - W[i]] + V[i]);
                }
            }
        }

        System.out.print(dp[N][K]);
    }
}

