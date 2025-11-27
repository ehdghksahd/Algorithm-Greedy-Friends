// [BOJ] 15900: 나무 탈출
package somyoung.week4;

import java.io.*;
import java.util.*;

public class BOJ_15900 {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); // 인접 리스트
    static int depthSum = 0; // 깊이 합

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n+1]; // 1-index-base

        for(int i=0; i<n+1; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향 연결(간선)
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        dfs(1, 0); // 루트 노드, 깊이 0

        // 리프 노드들의 깊이 합으로 판별
        // 성원이가 선이기 때문에, 홀수면 성원 승리, 짝수면 패배
        if(depthSum % 2 == 1){
            System.out.print("Yes");
        } else {
            System.out.print("No");
        }

    }

    public static void dfs(int node, int depth){
        visited[node] = true;
        boolean isLeaf = true;

        for(int next : adj.get(node)){
            if(!visited[next]){
                isLeaf = false;
                dfs(next, depth+1);
            }
        }

        if(isLeaf) depthSum += depth;
    }
}