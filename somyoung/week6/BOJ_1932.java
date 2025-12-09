// BOJ / 1932 / 정수 삼각형 / 실버1
package somyoung.week6;

import java.io.*;
import java.util.*;

public class BOJ_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 빈값은 자동으로 0으로 초기화되기 때문에 이차 배열로 선언
        // 0-index-base, arr[층수-1][인덱스]
        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<=i; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //      7  // 초기식 dp[0][0] = arr[0][0]
        //     3 8 // dp[0][0] + arr[1][0] / dp[0][0] + arr[1][1]
        //    8 1 0 // dp[1][0] + arr[2][0]
        //          // max(dp[1][0], dp[1][1]) + arr[2][1]
        //          // dp[1][1] + arr[2][2]
        //   2 7 4 4 // dp[2][0] + arr[3][0]
        //           // max(dp[2][0], dp[2][1]) + arr[3][1]
        //           // max(dp[2][1], dp[2][2]) + arr[3][2]
        //           // dp[2][2] + arr[3][3]
        //  4 5 2 6 5

        // 초기식: dp[0][0] = arr[0][0]

        // 점화식:
        // 맨 왼쪽: dp[i-1][0] + arr[i][0] // j == 0
        // 맨 오른쪽: dp[i-1][i-1] + arr[i][i] // j == i
        // 가운데: Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j] // 1 <= j <= i-1
        dp[0][0] = arr[0][0];

        for(int i=1; i<n; i++){
            for(int j=0; j<=i; j++){
                // 맨 왼쪽
                if(j == 0) dp[i][0] = dp[i-1][0] + arr[i][0];

                // 맨 오른쪽
                if(j == i) dp[i][i] = dp[i-1][i-1] + arr[i][i];

                // 가운데
                if(1 <= j && j <= i-1) dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
            }
        }

        // 마지막 줄에 있는 합들 중 가장 큰 값을 추출
        int result = 0;
        for(int i=0; i<n; i++){
            result = Math.max(dp[n-1][i], result);
        }
        System.out.print(result);
    }
}

