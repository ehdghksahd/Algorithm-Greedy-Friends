package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 미로 탐색 (실버1)
public class BOJ_2178 {
    static int N, M;
    static boolean[][] visited;
    static int[][] map;

    // 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 좌표& 거리를 저장하는 클래스
    static class Point {
        int x, y, dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();

        queue.offer(new Point(0,0,1)); // 시작점 (0, 0)을 큐에 넣기 (거리 1부터 시작!)
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 도착점에 도달했으면 거리 반환
            if (current.x == N - 1 && current.y == M - 1) {
                return current.dist;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 범위 체크
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                // 벽(0)이면 못 감
                if (map[nx][ny] == 0) {
                    continue;
                }

                // 이미 방문했으면 스킵
                if (visited[nx][ny]) {
                    continue;
                }

                // 큐에 추가 (거리 +1)
                queue.offer(new Point(nx, ny, current.dist + 1));
                visited[nx][ny] = true;  // 방문 표시
            }
        }

        return -1;  // 도착 불가능

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0'; // 문자 -> 숫자
            }
        }

        int result = bfs();
        System.out.println(result);
    }
}
