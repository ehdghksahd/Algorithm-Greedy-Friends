package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라.

// 그림 (실버1)
public class BOJ_1926 {
    static int N, M;
    static int[][] paper;
    static boolean[][] visited;

    // 방향 배열 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // DFS로 그림 크기 구하기
    static int dfs(int x, int y) {
        visited[x][y] = true;
        int size = 1;  // 현재 칸

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            // 0이면 못 감
            if (paper[nx][ny] == 0) continue;
            // 이미 방문
            if (visited[nx][ny]) continue;

            size += dfs(nx, ny);  // 재귀로 크기 누적
        }
        return size;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int pictureCount = 0;  // 그림 개수
        int maxSize = 0;       // 가장 큰 그림

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1이고 방문 안 했으면
                if (paper[i][j] == 1 && !visited[i][j]) {
                    pictureCount++;  // 그림 개수 증가

                    int size = dfs(i, j);  // 이 그림의 크기
                    maxSize = Math.max(maxSize, size);  // 최댓값 갱신
                }
            }
        }

        System.out.println(pictureCount);
        System.out.println(maxSize);
    }
}
