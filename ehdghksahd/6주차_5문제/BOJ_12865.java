import java.util.*;
import java.io.*;


public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1]; // 최대 무게 K에서 얻을 수 있는 최대 가치 저장
        List<int[]> items = new ArrayList<>();

        items.add(new int[]{0, 0});

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            items.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        for(int i = 1; i <= N; i++) {
            int curW = items.get(i)[0]; // 선택한 물건 무게
            int curV = items.get(i)[1]; // 선택한 물건 가치

            for(int w = K; w >= curW; w--){

                dp[w] = Math.max(dp[w], dp[w - curW] + curV);
            }
        }
        System.out.println(dp[K]);
    }
}
