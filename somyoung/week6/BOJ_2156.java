// BOJ / 2156 / 포도주 시식 / 실버1
package somyoung.week6;

import java.io.*;

public class BOJ_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        int[] arr = new int[n+1];

        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 첫번째 시도 -> 런타임 에러 (ArrayIndexOutOfBounds)
        // arr[3]에 접근하기 때문에 n이 1,2일 때를 처리해주어야 함
        if(n == 1){
            System.out.print(arr[1]);
            return;
        }

        if(n == 2){
            System.out.print(arr[1]+arr[2]);
            return;
        }

        // 초기값
        // dp[1] = arr[1]
        // dp[2] = arr[1] + arr[2]
        // dp[3] = Math.max(dp[2], dp[1]+arr[3], arr[2]+arr[3])
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        dp[3] = Math.max(
                Math.max(dp[2], dp[1]+arr[3]),
                arr[2]+arr[3]
        );

        // 점화식
        // n번째 잔을 선택하지 않는 경우: dp[n-1]
        // n번째 잔을 선택하고, 바로 앞의 잔을 선택하지 않는 경우: dp[n-2] + arr[n]
        // n번째 잔을 선택하고, 바로 앞의 잔을 선택하는 경우: dp[n-3] + arr[n-1] + arr[n]
        // dp[n-3]을 식에 쓰려면...1-index-base이므로 for문은 4부터 시작, 초기식 3까지 정의
        for(int i=4; i<=n; i++){
            dp[i] = Math.max(
                    Math.max(dp[i-1], dp[i-2]+arr[i]),
                    dp[i-3]+arr[i-1]+arr[i]
            );
        }

        System.out.print(dp[n]);
    }
}

