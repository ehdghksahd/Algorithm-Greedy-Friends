package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리의 지름 (골드4)
public class BOJ_1967 {
    static ArrayList<Edge>[] tree;
    static boolean[] visited;
    static int farthestNode;  // 가장 먼 정점
    static int maxDistance;   // 가장 먼 거리

    static class Edge {
        int to, weight;  // 도착 정점, 가중치

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static void dfs(int node, int distance) {
        visited[node] = true;

        // 가장 먼 노드 갱신
        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        // 인접 노드 탐색
        for (Edge edge : tree[node]) {
            if (!visited[edge.to]) {
                dfs(edge.to, distance + edge.weight);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n+1];

        // tree초기화
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        // 간석 입력(양방향)
        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[parent].add(new Edge(child, weight));
            tree[child].add(new Edge(parent, weight));
        }

        // 1차 DFS: 1번에서 가장 먼 노드 찾기
        visited = new boolean[n + 1];
        maxDistance = 0;
        dfs(1, 0);

        // 2차 DFS: 찾은 노드에서 가장 먼 노드 찾기
        visited = new boolean[n + 1];
        maxDistance = 0;
        dfs(farthestNode, 0);

        // 결과 출력
        System.out.println(maxDistance);
    }
}
