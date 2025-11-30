// [BOJ] 1182: 부분수열의 합
package somyoung.week5;

import java.io.*;
import java.util.*;

public class BOJ_1182 {
    static int N, S;
    static int[] arr;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0); // index 0, sum 0

        // S가 0일 때, 모든 원소를 선택하지 않았을 경우 cnt - 1
        // 공집합은 부분 수열에 포함되지 않음
        if(S == 0) cnt--;

        System.out.print(cnt);
    }

    static void dfs(int index, int sum){
        if(index == N){ // 탐색 종료, 부분 수열의 합과 S의 일치 여부 확인
            if(sum == S) cnt++;
            return;
        }

        // 현재 원소를 더함
        dfs(index + 1, sum + arr[index]);

        // 현재 원소를 더하지 않음
        dfs(index + 1, sum);
    }
}

