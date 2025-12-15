import java.util.*;
import java.io.*;

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][N];
            int[][] dp = new int[N][3]; // 0 위, 1 아래, 2 선택X

            for(int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k = 0; k < N; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = sticker[0][0];
            dp[0][1] = sticker[1][0];
            dp[0][2] = 0;

            for(int r = 1; r < N; r++) {
                dp[r][0] = sticker[0][r] + Math.max(dp[r-1][1], dp[r-1][2]); // 위 스티커를 뗀 경우, 이 전에는 아래 or 선택 X;

                dp[r][1] = sticker[1][r] + Math.max(dp[r-1][0], dp[r-1][2]); // 아래 스티커를 뗀 경우 이 전에는 위 or 선택 X;

                dp[r][2] = Math.max(dp[r-1][0], Math.max(dp[r-1][1], dp[r-1][2])); // 뗀 스티커가 없는 경우, 모든 경우 선택
            }

            int answer = Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2]));
            System.out.println(answer);

        }


    }
}
