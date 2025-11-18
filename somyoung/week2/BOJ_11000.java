// [BOJ] 11000: 강의실 배정
package somyoung.week2;

import java.util.*;
import java.io.*;

public class BOJ_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> input = new ArrayList<>();
            input.add(Integer.parseInt(st.nextToken()));
            input.add(Integer.parseInt(st.nextToken()));
            list.add(input);
        }

        // 시작 시간 기준 정렬
        list.sort((o1, o2) -> o1.get(0) - o2.get(0));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(list.get(0).get(1)); // 첫 강의 종료 시간 삽입


        for(int i=1; i<n; i++){
            int start = list.get(i).get(0);
            int end = list.get(i).get(1);

            // 우선순위 큐 = 종료시간 오름차순 정렬
            if(!pq.isEmpty() && pq.peek() <= start){
                // 종료 시간이 현재 강의 시작시간보다 먼저 끝나면 poll()
                pq.poll();
            }
            pq.offer(end);
        }

        System.out.print(pq.size());
    }
}
