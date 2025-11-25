package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 목표:
    1.모든 토마토가 익는 최소 일수 구하기
    2.처음부터 모두 익어있으면 0
    3.모두 익을 수 없으면 -1

    규칙:
    - 하루가 지나면 익은 토마토 상하좌우에 있는 안 익은 토마토가 익음
    - 대각선은 영향 없음!
    - M × N 크기의 상자에 토마토가 들어있음
    - 1 = 익은 토마토
    - 0 = 안 익은 토마토
    - -1 = 토마토가 없는 칸
 */
// 토마토(골드5)
public class BOJ_7576 {
    static int M, N; // 가로, 세로 칸수
    static int[][] box; // 토마토 상자

    // 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Tomato {
        int x, y, day;

        Tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    static int bfs() {
        Queue<Tomato> queue = new LinkedList<>();

        // 익은 토마토 큐에 넣기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(box[i][j] == 1) queue.offer(new Tomato(i, j, 0));
            }
        }

        int maxDay = 0; // 가장 오래 걸린 일수 누적용

        while (!queue.isEmpty()) {
            Tomato current = queue.poll();
            maxDay = Math.max(maxDay, current.day);

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if(box[nx][ny] == 0) {
                    box[nx][ny] = 1; // 익히기
                    queue.offer(new Tomato(nx, ny, current.day + 1)); // 하루증가
                }
            }
        }

        // 안익은 토마토 남아있는지 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(box[i][j] == 0) {
                    return -1;
                }
            }
        }
        return maxDay;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로칸수
        N = Integer.parseInt(st.nextToken()); // 세로칸수

        box = new int[N][M];

        // 상자 상태 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }
}
