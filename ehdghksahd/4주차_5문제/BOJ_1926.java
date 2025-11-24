import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1926

public class Main{
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int paper[][] = new int[n][m]; // 도화지
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken()); // 세팅
            }
        }
        boolean[][] isVisited = new boolean[n][m]; // 방문 여부
        int cnt = 0; // 그림 개수
        int max = -1; // 제일 넓은 그림
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(paper[i][j] == 1 && isVisited[i][j] == false) {
                    cnt++;
                    Stack<int[]> stack = new Stack<>();
                    stack.add(new int[]{j, i});
                    isVisited[i][j] = true;
                    int area = 0;

                    while(!stack.isEmpty()) {
                        // dfs 시작... 재귀로 하는게 좋을까...? 최대 크기가 250000 너무 큰거 같기도?
                        // bfs가 더 효율적? 비슷한거 같기도?
                        int[] cur = stack.pop();
                        int x = cur[0];
                        int y = cur[1];

                        area++;

                        for(int k = 0; k < 4; k++) { // 상하좌우.. 이어진거 체크...
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if(nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                if(!isVisited[ny][nx] && paper[ny][nx] == 1) {
                                    isVisited[ny][nx] = true;
                                    stack.push(new int[] {nx, ny});
                                }
                            }
                        }
                    }
                    max = Math.max(max, area); // 최대 넓이 비교
                }
            }
        }
        if(cnt == 0) max = 0;
        System.out.println(cnt+ "\n"+ max);
    }
}
