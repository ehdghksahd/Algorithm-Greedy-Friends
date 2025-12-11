package livecoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1535 {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 사람의 수

        int[] dp = new int[101];

        int[] health = new int[N+1];
        int[] happy = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            health[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            happy[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=N; i++){
            for(int j=100; j>health[i]; j--){ // health가 더 낮으면 말할 수 없기 때문에
                dp[j] = Math.max(dp[j], dp[j-health[i]]+happy[i]); // 사람과 말하기, 말하지 않고 넘어가기
            }
        }
        System.out.println(dp[100]);
    }
}
