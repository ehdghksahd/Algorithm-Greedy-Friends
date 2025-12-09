import java.util.*;
import java.io.*;

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[100];
        int[] hp = new int[N + 1];
        int[] exp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            hp[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            exp[i] = Integer.parseInt(st.nextToken());
        }

        // 체력이 0 이하가 되면 끝
        for(int i = 1; i <= N; i++) {
            int curH = hp[i];
            int curExp = exp[i];

            for(int h = 99; h >= curH; h--) {  // hp 초과 되었을 때,

                // 선택 안했을 때와, 선택 했을 때 두개중 큰걸..
                dp[h] = Math.max(dp[h], dp[h - curH] + curExp);
            }
        }
        System.out.println(dp[99]); // 100은 포함 안함
    }
}
