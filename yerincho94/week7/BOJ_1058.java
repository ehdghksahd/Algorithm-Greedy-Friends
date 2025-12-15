package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * N명의 사람이 있고, 친구 관계가 주어짐
 *
 * 2-친구란?
 * - 직접 친구 (거리 1)
 * - 친구의 친구 (거리 2)
 *
 * 가장 많은 2-친구를 가진 사람의 2-친구 수는?
 *
 *     0  1  2
 * 0: [N][Y][Y]  → 0번은 1번, 2번과 친구
 * 1: [Y][N][Y]  → 1번은 0번, 2번과 친구
 * 2: [Y][Y][N]  → 2번은 0번, 1번과 친구
 *
 * 각 사람 i에 대해:
 *   각 사람 j에 대해 (i != j):
 *     if (i와 j가 직접 친구) → 카운트
 *     else if (i와 j가 공통 친구 있음) → 카운트
 */
// 친구(실버2) - 그래프
public class BOJ_1058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[][] friends = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                friends[i][j] = line.charAt(j);
            }
        }

        int maxFriends = 0;

        // 각 사람의 2-친구 수 세기
        for (int i = 0; i < N; i++) {
            int count = getCount(N, i, friends);
            maxFriends = Math.max(maxFriends, count);
        }

        System.out.println(maxFriends);
    }

    private static int getCount(int N, int i, char[][] friends) {
        int count = 0;

        for (int j = 0; j < N; j++) {
            if (i == j) continue;

            // 직접 친구
            if (friends[i][j] == 'Y') {
                count++;
            } else { // 친구의 친구
                for (int k = 0; k < N; k++) {
                    if (k == i || k == j) continue;

                    if (friends[i][k] == 'Y' && friends[k][j] == 'Y') {
                        count++;
                        break;  // 한 명이라도 공통 친구 있으면 OK
                    }
                }
            }
        }
        return count;
    }
}
