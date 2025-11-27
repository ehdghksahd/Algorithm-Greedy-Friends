package week4.BOJ_15900_나무_탈출;

import java.util.*;
import java.io.*;

public class Main{
    static int count = 0;
    static ArrayList<Integer>[] tree;

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            tree[i] = new ArrayList<Integer>();
        }

        for (int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            tree[f].add(s);
            tree[s].add(f);
        }

        dfs(1, 0, 0);

        System.out.println(count % 2 != 0 ? "Yes" : "No");
    }

    static void dfs(int current, int parent, int depth) {
        boolean isLeafNode = true;

        for (int next : tree[current]) {
            if (next != parent) {// 부모 노드가 아니면 자식 노드
                isLeafNode = false;
                dfs(next, current, depth + 1);
            }
        }

        // 자식 노드가 하나도 없다면 LeafNode
        if (isLeafNode) {
            count += depth;
        }
    }
}
