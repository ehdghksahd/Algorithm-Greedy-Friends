// [BOJ] 14501: 퇴사
package somyoung.week5;

import java.io.*;
import java.util.*;

public class BOJ_14501 {
    static int N;
    static int maxMoney = 0;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // N (1 ≤ N ≤ 15) 이므로 모든 경우를 다 시도해도 2^15 -> 완전탐색 가능
        dfs(0, 0); // day 0, money 0

        System.out.print(maxMoney);
    }

    static void dfs(int day, int money){
        if(day >= N){ // day >= N일 경우 최대 수익 갱신
            maxMoney = Math.max(maxMoney, money);
            return;
        }
        int t = arr[day][0];
        int p = arr[day][1];

        // 오늘 상담 O, 기간안에 끝날 경우만 탐색
        if(day + t <= N){
            dfs(day + t, money + p);
        }

        // 오늘 상담 X, 다음날로 넘김
        dfs(day + 1, money);
    }
}

