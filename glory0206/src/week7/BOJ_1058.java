package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1058 {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dist = new int[N][N];
        int INF = 1_000_000;

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                if(i == j){ // 자기 자신
                    dist[i][j] = 0;
                } else if(line.charAt(j) == 'Y'){
                    dist[i][j] = 1;
                } else{
                    dist[i][j] = INF;
                }
            }
        }

        // i -> j로의 이동에 있어 k를 거치는 것이 더 빠른지 확인
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            int count = 0;
            for(int j = 0; j < N; j++) {
                if(i != j && dist[i][j] <= 2) {
                    count++; // 자기 자신이 아니면서, 친구의 거리가 2를 넘어서지 않아야 한다.
                }
            }
            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }
}
