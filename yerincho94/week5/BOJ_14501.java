package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 퇴사 (실버3)
public class BOJ_14501 {
    static int N;
    static int[] T;  // 소요 기간
    static int[] P;  // 상담 금액

    public static int solve(int day) {
        if (day >= N) { // 퇴사일 넘겼을 경우
            return 0;
        }

        // 상담을 하는 경우
        int doConsult = 0;
        if (day + T[day] <= N) { // 상담 종료일이 퇴사일 이내인지 확인
            doConsult = P[day] + solve(day + T[day]);
        }

        // 상담을 안 하는 경우
        int skip = solve(day + 1);

        return Math.max(doConsult, skip);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        T = new int[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(0));
    }
}
