import java.util.*;
import java.io.*;


public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][K + 1]; // 최대 무게 K에서 얻을 수 있는 최대 가치 저장
        List<int[]> items = new ArrayList<>();

        items.add(new int[]{0, 0});

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            items.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        for(int i = 1; i <= N; i++) {
            int curW = items.get(i)[0]; // 선택한 물건 무게
            int curV = items.get(i)[1]; // 선택한 물건 가치

            for(int w = 1; w <= K; w++){
                if(w < curW) dp[i][w] = dp[i - 1][w]; // 무게가 초과 될 경우

                    // 물건을 넣을 수 있는 경우
                    // 1. 안넣고 넘길때 -> 이전 배낭 가치와 동등
                    // 2. 넣을 때
                    // 1. 2. 두개 중 큰 값
                else dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - curW] + curV);
            }
        }
        System.out.println(dp[N][K]);
    }
}
