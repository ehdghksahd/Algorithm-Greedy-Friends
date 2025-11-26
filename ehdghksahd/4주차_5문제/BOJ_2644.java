import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/2644

public class Main{
    static List<List<Integer>> tree  = new ArrayList<>();
    static boolean visited[]; // 방문 여부
    static int A; // 목표 1
    static int B; // 목표 2
    static int cnt; // 촌수

    public static void dfs(int node, int depth) {
        visited[node] = true;
        if(node == B) {
            cnt = depth;
            return;
        }
        for(int n : tree.get(node)) {
            if(!visited[n])  {
                dfs(n, depth + 1);
            }
        }
    }
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 총 노드 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) tree.add(new ArrayList<>());

        int M = Integer.parseInt(br.readLine()); // 연결 상태 수

        for(int i = 1; i <= M; i++ ) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree.get(p).add(c);
            tree.get(c).add(p);
        }

        visited = new boolean[N + 1];
        cnt = 0;

        dfs(A, 0);

        if(cnt == 0) System.out.println(-1);
        else
            System.out.println(cnt);

    }
}
