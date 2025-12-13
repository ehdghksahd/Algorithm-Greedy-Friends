package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2156 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 포도주 잔의 개수

        int[][] cups = new int[n+1][3]; // n+1: 선택하는 포도주 잔의 index, y배열의 0, 1, 2를 사용 0: 잔을 선택하지 않음, 1: 처음으로 선택, 2: 연속으로 고른 2번째 선택
        int[] juices = new int[n+1];

        for(int i=1; i<=n; i++){
            juices[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) { // 잔이 1개라면 그 잔을 선택하는 것이 최대
            System.out.println(juices[1]);
            return;
        }

        cups[1][0] = 0; // 선택하지 않음
        cups[1][1] = juices[1]; // 제일 첫 잔을 첫 번째로 선택
        cups[1][2] = 0; // 첫 잔을 두 번째로 선택할 수 없다.

        cups[2][0] = Math.max(cups[1][0], Math.max(cups[1][1], cups[1][2])); // 이전의 선택 중 가장 큰 값
        cups[2][1] = juices[2]; // 제일 첫 잔을 첫 번째로 선택
        cups[2][2] = cups[1][1] + juices[2]; // 두 번째 잔을 두 번째로 선택하는 경우, 이전에 첫번 째 잔을 선택했어야 한다.


        for (int i = 3; i <= n; i++) {
            cups[i][0] = Math.max(cups[i-1][0], Math.max(cups[i-1][1], cups[i-1][2])); // 이전에도 선택X 또는 이전의 잔을 선택 중 더 큰 값

            cups[i][1] = Math.max(cups[i-2][0], Math.max(cups[i-2][1], cups[i-2][2])) + juices[i]; // 이번이 첫 선택이기 때문에, 이전의 잔은 선택X. 전전의 잔은 선택하지 않았을 수도, 첫 선택일 수도, 두 번째 선택일 수도 있다.

            cups[i][2] = cups[i-1][1] + juices[i]; // [][2]이기 때문에 이미 이전의 잔을 선택했음을 알 수 있다. 이전의 잔은 첫 번째 선택이어야 하기에 [n-1][1]로 설정.
        }

        System.out.println(Math.max(cups[n][0], Math.max(cups[n][1], cups[n][2]))); // 최종 결과
    }
}
