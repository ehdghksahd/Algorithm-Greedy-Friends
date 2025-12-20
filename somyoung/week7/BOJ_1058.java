// BOJ / 1058 / 친구 / 실버2
package somyoung.week7;

import java.io.*;
import java.util.*;

public class BOJ_1058 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[][] adj = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if(str.charAt(j) == 'Y') adj[i][j] = true;
                else adj[i][j] = false;
            }
        }

        int maxValue = 0;

        // 어떤 사람 A가 또다른 사람 B의 2-친구가 되기 위해서는
        // 두 사람이 친구이거나, A와 친구이고, B와 친구인 C가 존재
        for (int a = 0; a < N; a++) {
            boolean[] temp = new boolean[N]; // 사람마다 방문여부 초기화

            // 두 사람이 친구
            for (int b = 0; b < N; b++) {
                if (adj[a][b]){
                    temp[b] = true;
                }
            }

            // A와 친구이고, B와 친구인 C가 존재
            for (int c = 0; c < N; c++) {
                if (!adj[a][c]) continue;
                for (int b = 0; b < N; b++) {
                    if (adj[c][b]) temp[b] = true;
                }
            }

            // A와 B가 친구면, B와 A도 친구이고, A와 A는 친구가 아니다.
            int cnt = 0;
            for (int b = 0; b < N; b++) {
                if (b != a && temp[b]) cnt++;
            }

            maxValue = Math.max(maxValue, cnt);
        }

        System.out.print(maxValue);
    }
}

