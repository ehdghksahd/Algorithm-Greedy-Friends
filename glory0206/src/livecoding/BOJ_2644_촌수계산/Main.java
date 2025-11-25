package livecoding.BOJ_2644_촌수계산;

import java.util.*;
import java.io.*;

public class Main{
    static ArrayList<Integer>[] graph;
    static int n;

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for(int i=0; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        // 연관 관계
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            graph[parent].add(child);
            graph[child].add(parent);
        }
        int result = bfs(p1, p2);
        System.out.println(result);
    }

    static int bfs(int p1, int p2){
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        queue.add(new int[]{p1, 0}); // 첫 노드
        visited[p1] = true;

        while(!queue.isEmpty()){
            int[] current =  queue.poll();
            int child = current[0];
            int depth = current[1];

            if(child == p2){
                return depth;
            } else{
                for(int c: graph[child]){
                    if(!visited[c]){
                        visited[c] = true;
                        queue.add(new int[]{c, depth+1});
                    }
                }
            }
        }
        return -1;
    }
}
