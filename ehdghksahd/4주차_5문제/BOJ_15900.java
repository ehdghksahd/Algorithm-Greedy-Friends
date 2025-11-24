import java.util.*;
import java.io.*;


// https://www.acmicpc.net/problem/15900

// 리프 노드 -> 부모가 하나인 노드 -> 간선이 하나인 노드 -> 배열 크기가 1인 노드...?
// 리프 노드 개수 : 초기 게임말 개수
// 게임말 이동 횟수 홀수 -> 무조건 승리? 흠.....

public class Main{
    static List<List<Integer>> tree;
    static boolean visited[];
    static int cnt;
    public static void dfs(int node, int depth) {
        visited[node] = true;
        if(node != 1 && tree.get(node).size() == 1) { // 리프노드 판단
            cnt += depth;
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

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        visited = new boolean[N + 1];
        cnt = 0;
        for(int i = 0; i <= N; i++) tree.add(new ArrayList<>());

        for(int i = 1; i < N; i++) { // 그래프 그리기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            tree.get(first).add(second);
            tree.get(second).add(first);
        }
        dfs(1, 0);

        if(cnt % 2 == 0) System.out.println("No");
        else System.out.println("Yes");
    }
}
