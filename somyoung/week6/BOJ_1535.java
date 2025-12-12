// BOJ / 1535 / 안녕 / 실버2
package somyoung.week6;

import java.io.*;
import java.util.*;

public class BOJ_1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // dp[j]: 체력 j만큼 소모했을 때, 얻을 수 있는 최대 기쁨
        int[] dp = new int[100]; // 소모한 체력이 100이면 죽음. 99까지 유효

        int[] hp = new int[N]; // 체력
        int[] joy = new int[N]; // 기쁨

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            hp[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            joy[i] = Integer.parseInt(st.nextToken());
        }

        // 초기식
        // dp[0] = 0

        // 점화식
        // max(현재 사람 만나지 않음, 현재 사람 만남)
        // dp[j] = Math.max(dp[j], dp[j - hp[i]] + joy[i])
        for(int i=0; i<N; i++){ // 각 사람 순회
            for(int j=99; j>=hp[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - hp[i]] + joy[i]);
            }
        }

        System.out.print(dp[99]);
    }
}

