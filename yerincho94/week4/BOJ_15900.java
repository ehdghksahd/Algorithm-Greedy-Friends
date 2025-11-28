package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
    목표 : 성원이가 선(Yes), 형석이가 후(No)

    1. root(1번)에서 시작
    2. 더 이상 갈 곳 없으면(리프에 도착하면) 짐. == 더 이상 자식이 없으면
 */

// 나무탈출 (실버1)
public class BOJ_15900 {
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int leafDepthSum = 0;  // 리프 깊이 합

    // DFS로 깊이 계산
    static void dfs(int node, int depth) {
        visited[node] = true;
        boolean isLeafNode = true;  // 리프 노드인지 확인

        // 인접 노드 탐색
        for (int next : tree[node]) {
            if (!visited[next]) {
                isLeafNode = false;  // 자식이 있으면 리프 아님
                dfs(next, depth + 1);  // 깊이 +1
            }
        }

        // 리프 노드면 깊이 더하기
        if (isLeafNode) {
            leafDepthSum += depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 정점 개수

        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        // 간선 정보 입력
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);  // a → b 연결
            tree[b].add(a);  // b → a 연결 (양방향)
        }

        visited = new boolean[N + 1];
        // 루트(1번)부터 DFS
        dfs(1, 0);

        // 홀수면 Yes, 짝수면 No
        if (leafDepthSum % 2 == 1) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
