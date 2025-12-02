package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 나이트의 이동 (실버1)
public class BOJ_7562 {
    static int l;          // 체스판 한 변의 길이 (l x l)
    static int[][] dist;   // dist[x][y] : (x, y)에 도달하는 데 필요한 최소 이동 수

    // 나이트가 한 번에 이동할 수 있는 8가지 방향 (dx, dy)
    // (2, 1), (2, -1), (1, 2), (1, -2), (-1, 2), (-1, -2), (-2, 1), (-2, -1)
    static int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};

    // BFS로 (startX, startY) → (endX, endY) 최소 이동 횟수를 구함
    public static int bfs(int startX, int startY, int endX, int endY) {
        // 시작과 끝 위치가 같은 경우 → 이동할 필요 없음
        if (startX == endX && startY == endY) {
            return 0;
        }

        // 거리 배열을 -1로 초기화 (아직 방문하지 않은 상태를 의미)
        dist = new int[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                dist[i][j] = -1;
            }
        }

        // BFS를 위한 큐
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY}); // 시작 지점을 큐에 넣고
        dist[startX][startY] = 0; // 거리 0으로 설정

        // BFS 시작
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 현재 위치에서 나이트가 이동할 수 있는 8가지 방향을 모두 탐색
            for (int dir = 0; dir < 8; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 1. 체스판 범위 안인지 확인 -> 벗어나면 skip
                if (nx < 0 || ny < 0 || nx >= l || ny >= l) continue;

                // 2. 아직 방문하지 않은 칸인지 확인 -> 방문하지 않은 칸이면 이동
                if (dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1; // 현재까지의 이동 수 + 1
                    queue.offer(new int[]{nx, ny});

                    // 3. 목표 위치에 도달한 경우 → 그 즉시 최소 이동 수 반환 가능
                    if (nx == endX && ny == endY) return dist[nx][ny];
                }
            }
        }

        return -1; // 도달할 수 없는 경우 (실제로는 발생하지 않음)
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            l = Integer.parseInt(br.readLine()); // 체스판 한 변의 길이(l*l)

            // 시작 위치 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            // 목표 위치 입력
            st = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());

            // BFS로 최소 이동 횟수 구하기
            int result = bfs(startX, startY, targetX, targetY);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
