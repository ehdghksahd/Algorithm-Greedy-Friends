package week4;

import java.io.*;
import java.util.*;

// 주어진 두 사람의 촌수를 계산하는 프로그램
// 촌수 계산 할 수 없을 때는 -1

// 촌수계산 (실버2)
public class BOJ_2644 {
    static int n; // 전체 사람 수
    static ArrayList<Integer>[] familyGraph;  // 가족관계 그래프
    static boolean[] visited; // 방문 체크(꼭)

    static int bfs(int start, int degree2) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0}); // {현재 사람, 현재 촌수} -> 처음에 자기 자신은 0
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentPerson = current[0]; // 현재 사람 번호
            int currentDegree = current[1]; // 현재까지의 촌수

            // 목표 사람 발견시 현재촌수가 답임
            if(currentPerson == degree2) return currentDegree;

            // 현재 사람과 연결된 모든 사람들 확인
            for (int next : familyGraph[currentPerson]) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, currentDegree + 1}); // 부모/자식 관계 1촌 차이라서 -> 촌수 + 1
                }
            }
        }
        return -1; // 촌수 계산 할 수 없을 때는
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 전체 사람 수 9

        StringTokenizer st = new StringTokenizer(br.readLine());
        int degree1 = Integer.parseInt(st.nextToken()); // 사람1 7
        int degree2 = Integer.parseInt(st.nextToken()); // 사람2 3

        int m = Integer.parseInt(br.readLine()); // 부모 자식 간의 관계 개수

        // 가족 관계 그래프 초기화
        familyGraph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            familyGraph[i] = new ArrayList<>();
        }

        // m개의 관계 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()); // 시작
            int child = Integer.parseInt(st.nextToken());

            // 양방향으로 연결
            familyGraph[parent].add(child);
            familyGraph[child].add(parent);
        }

        // 방문 배열 초기화
        visited = new boolean[n + 1];

        // 가까운 촌수부터 계산 bfs()
        int result = bfs(degree1, degree2);
        System.out.println(result);
    }
}
