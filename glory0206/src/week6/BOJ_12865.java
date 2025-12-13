package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int K = Integer.parseInt(st.nextToken()); // 최대로 들 수 있는 무게

        int[] dp = new int[K + 1];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken()); // 무게
            int V = Integer.parseInt(st.nextToken()); // 가치

            for(int j=K; j>=W; j--){ // W 이상의 여유가 있어야 넣을 수 있음
                // 특정 무게의 여유 공간이 남았을 때, 어떤 물건을 넣어야 최대의 효율을 뽑아낼 수 있는지 확인한다.
                dp[j] = Math.max(dp[j], dp[j - W] + V); // dp[j]: 물건을 넣지 않음, dp[j-W] + V: 물건을 넣어서 W 무게의 여유가 줄고 V의 가치가 증가
            }
        }

        System.out.println(dp[K]);
    }
}
