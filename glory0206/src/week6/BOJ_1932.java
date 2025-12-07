package week6;

import java.util.*;
import java.io.*;

public class BOJ_1932 {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 삼각형의 크기

        // 값 넣어주기
        int[][] triangle = new int[n+1][];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            triangle[i] = new int[i+1];
            for (int j = 1; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(n==1){
            System.out.println(triangle[1][1]);
            return;
        }

        int[][] dp = new int[n+1][n+1];

        dp[1][1] = triangle[1][1]; // root값

        for(int i=2; i<=n; i++){
            for(int j=1; j<=i; j++){
                if(j==1){ // 해당 열의 가장 좌측이라면 이전 층의 우측 값을 가져와야 함
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else if(j==i){ // 해당 열의 가장 우측이라면 이전 층의 좌측 값을 가져와야 함
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                } else{
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j]; // 이전 층의 좌측 위 또는 우측 위의 총합을 비교해서 더 큰 수 + 현 위치.
                }
            }
        }

        int answer = 0;
        for (int j = 1; j <= n; j++) {
            answer = Math.max(answer, dp[n][j]); // 마지막 층의 값들 중 가장 큰 값.
        }

        System.out.println(answer);
    }
}
