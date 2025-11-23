package week4.BOJ_1967_트리의_지름;

import java.util.*;
import java.io.*;

public class Main{
    static class Pair{
        int child;
        int w;

        Pair(int child, int w){
            this.child = child;
            this.w = w;
        }
    }

    static ArrayList<Pair>[] graph;
    static boolean[] visited;
    static int maxDist;
    static int maxNode;

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[p].add(new Pair(c, w));
            graph[c].add(new Pair(p, w));
        }

        visited = new boolean[n + 1];
        maxNode = 1;
        maxDist = 0;
        dfs(1, 0);

        visited = new boolean[n + 1];
        maxDist = 0;
        dfs(maxNode, 0);

        System.out.println(maxDist);
    }
    static void dfs(int current, int dist){
        visited[current] = true;
        if(dist > maxDist){
            maxDist = dist;
            maxNode = current;
        }

        for(Pair next : graph[current]){
            if(!visited[next.child]){
                dfs(next.child, dist+next.w);
            }
        }
    }
}
