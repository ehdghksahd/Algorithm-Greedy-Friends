import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1967

public class Main{
    private static int maxDistance = 0;
    private static int endNode = 0;
    private static List<ArrayList<int[]>> list;
    private static boolean[] visited; // 방문 여부

    private static void dfs(int node, int currentDist) {

        // 현재까지의 거리가 최대 거리보다 크면 업데이트
        if (currentDist > maxDistance) {
            maxDistance = currentDist;
            endNode = node;
        }

        for(int[] edge : list.get(node)) {
            int child = edge[0]; // 자식노드
            int weight = edge[1]; // 가중치

            if (!visited[child]) {
                visited[child] = true; // 방문 처리
                dfs(child, currentDist + weight);
            }
        }
    }

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<int[]>());
        }

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()); // 부모
            int c = Integer.parseInt(st.nextToken()); // 자식
            int w = Integer.parseInt(st.nextToken()); // 가중치

            list.get(p).add(new int[] {c, w});
            list.get(c).add(new int[] {p, w});
        }

        // 가장 먼 노드 찾기
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, 0); // 1번 노드에서 DFS 시작

        // 가장 먼 노드에서 시작
        visited = new boolean[N + 1];
        visited[endNode] = true;
        dfs(endNode, 0);  // dfs든 bfs든 두번 해야되는게 주요 풀이점... 어렵다...

        System.out.println(maxDistance);
    }
}