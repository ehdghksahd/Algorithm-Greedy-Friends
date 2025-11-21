import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/14719

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] block = new int[W];

        for(int i = 0; i < W; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = W - 1;
        int l_max = block[l];
        int r_max = block[r];
        int cnt = 0;    // 현 위치에서 왼쪽 오른쪽 값이 더 작은쪽 - 현위치 값

        while(l < r) {
            if(l_max <= r_max) {
                l++;
                l_max = Math.max(block[l], l_max);
                cnt += l_max - block[l];
            }
            else {
                r--;
                r_max = Math.max(block[r], r_max);
                cnt += r_max - block[r];
            }
        }
        System.out.println(cnt);

    }
}
