import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1182

public class Main{
    private static int[] arr;
    private static int S;
    private static int N;
    private static int answer = 0;

    private static void combination(int cur, int sum) {

        if(cur == N) {
            if(sum == S) answer++;

            return;
        }

        combination(cur + 1, sum + arr[cur]); // 현재원소 포함

        combination(cur + 1, sum); // 미포함
    }

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        combination(0, 0);

        if(S == 0 && answer > 0) System.out.println(answer - 1); // S가 0일때 원소가 없어도 0체크가 되니까 -1
        else System.out.println(answer);
    }
}
