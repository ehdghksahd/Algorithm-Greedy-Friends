// [BOJ] 1967: 트리의 지름
package somyoung.week4;

import java.io.*;
import java.util.*;

public class BOJ_1967 {
    static boolean[] visited;
    static ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
    static int maxDistance; // 최대 거리
    static int maxNode; // 가장 먼 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n+1; i++){ // 1-index-base
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향 연결(간선)
            adj.get(parent).add(new int[]{child, weight});
            adj.get(child).add(new int[]{parent, weight});
        }

        // 1. 루트 노드에서 가장 먼 노드(가중치 누적 합이 가장 큰 노드) 찾기
        visited = new boolean[n+1];
        maxDistance = 0;
        dfs(1, 0); // 루트 노드, 거리 0

        // 2. 1번의 노드에서 가장 먼 노드 찾기
        visited = new boolean[n+1];
        maxDistance = 0;
        dfs(maxNode, 0); // 루트 노드에서 가장 먼 노드, 거리 0

        System.out.print(maxDistance);
    }

    public static void dfs(int node, int distance){
        visited[node] = true;

        if(distance > maxDistance){
            maxDistance = distance;
            maxNode = node;
        }

        for(int[] next: adj.get(node)){
            int nextNode = next[0];
            int weight = next[1];

            if(!visited[nextNode]){
                dfs(nextNode, distance + weight);
            }
        }

    }
}