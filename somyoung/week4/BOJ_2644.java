// [BOJ] 2644: 촌수계산
package somyoung.week4;

import java.io.*;
import java.util.*;

public class BOJ_2644 {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static int result = -1; // 촌수
    static int b; // 탐색 대상 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 노드 개수
        visited = new boolean[n+1];

        for(int i=0; i<n+1; i++){
            adj.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 촌수를 계산해야 하는 서로 다른 두 사람의 번호
        int a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 양방향 연결(트리)
            adj.get(x).add(y);
            adj.get(y).add(x);
        }

        dfs(a, 0); // a 노드, depth 0

        System.out.print(result);
    }

    public static void dfs(int node, int depth){
        visited[node] = true;

        if(node == b){ // b 노드를 발견하면 탐색을 중단하고 depth return
            result = depth;
            return;
        }

        for(int next : adj.get(node)){
            if(!visited[next]){
                dfs(next, depth + 1);
            }
        }

    }
}