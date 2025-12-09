import java.util.*;
import java.io.*;

// 연속 3번 X

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] glass = new int[N + 1]; // 포도주
        int[] dp = new int[N + 1]; // i번째 까지 마셨을 때 최대 값

        for(int i = 1; i <= N; i++) {
            glass[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = glass[1];

        if(N == 1){
            System.out.println(dp[1]);
            return;
        }

        dp[2] = glass[1] + glass[2];

        if(N == 2) {
            System.out.println(dp[2]);
            return;
        }
        // 현재 잔 건너뛰는 경우 : 그 전까지 마신 최대값과 동일하게 됨
        // 전에 마시지 않은 경우 : 전전에 마신 최대값과 현재 값 의 합이 됨
        // 연속 2잔 마시는 경우 : -2 번째 잔은 뛰어 넘었어야 하므로 3번전 최대값 + 전 잔의 값 + 현재 잔의 값이 됨
        // 이 세가지 중 최대인 경우 찾기


        for(int i = 3; i <= N; i++) {
            dp[i] = Math.max(
                    Math.max(dp[i - 1], dp[i -2] + glass[i]),
                    dp[i - 3] + glass[i - 1] + glass[i]);
        }
        System.out.println(dp[N]);
    }
}
