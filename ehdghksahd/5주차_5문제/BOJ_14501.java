import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/14501

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] plans = new int[N + 1][2];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());

            plans[i][0] = day;
            plans[i][1] = pay;
        }

        int[] cost = new int[N + 2]; // cost[i] i 일부터 퇴사일까지 벌 수 있는 최대 수익

        for(int i = N; i >= 1; i--) {
            int time = plans[i][0];
            int cash = plans[i][1];

            int endtime = i + time;
            if(endtime <= N + 1) { // 일이 마무리 할 수 있으면

                int tmp1 = cash + cost[endtime]; // 상담할때

                int tmp2 = cost[i + 1]; // 안할 때

                cost[i] = Math.max(tmp1, tmp2); // 둘 중 큰 값

            }
            else cost[i] = cost[i + 1]; // 상담 못할 때 이므로 다음날 최대수익과 동일
        }
        System.out.println(cost[1]);
        // 흠... DP 문제라니.... 너무 어렵다
    }
}
