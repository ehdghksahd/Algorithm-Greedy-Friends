package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
/*
    백준에 제출했는데 "시간초과"가 떠서 GPT에게 힌트를 요청했다.
    -> Q_min.remove(max);
    -> Q_max.remove(min);
    이 remove(Object)는 PriorityQueue 내부에서 해당 값을 찾아서 삭제하는 과정이 필요하다고 한다.
    문제는 :
    입력이 최대 100만(10⁶) 번 이상일 수 있기 때문에
    연산 하나당 O(N) → 전체 O(N²) 수준이 되어버림...

    # 개선 아이디어:
    - remove(Object)를 없애고, 값의 "유효 상태"를 따로 관리한다.
    - HashMap<Integer, Integer> count를 사용해서
      각 숫자가 현재 몇 번 유효하게 남아 있는지를 기록한다.
    - 삽입 시: count[n]++
    - 삭제 시: poll()로 큐에서 꺼낸 뒤 count[n]--
    - 이미 count가 0이 된 값은 clean()에서 큐에서 제거해 동기화한다.

    이렇게 하면 모든 연산이 O(log N) 안에서 끝나고,
    전체 시간복잡도는 O(N log N)으로 줄어든다.

    clean()에서 remove()안쓰고 poll()쓰는 이유:
    - clean()의 목적은 큐의 맨 위(peek()) 값이 유효하지 않으면 버리는 목적임.
    → “특정 값을 찾아 삭제(remove)”가 아니라
      “맨 위를 하나씩 꺼내는(poll)” 게 맞음.
*/
// 이중 우선순위 큐
public class BOJ_7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 모든 테스트케이스
        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine()); // 각 테스트케이스별 갯수

            // 초기화
            PriorityQueue<Integer> Q_min = new PriorityQueue<>(); // 최소값(오름차순)
            PriorityQueue<Integer> Q_max = new PriorityQueue<>(Collections.reverseOrder()); // 최대값(내림차순)
            HashMap<Integer, Integer> count = new HashMap<>(); // 값별 '남은개수' 기록용

            for (int i = 0; i < k; i++) {
                String[] inputs = br.readLine().split(" ");
                String keyword = inputs[0]; // D 또는 I
                int n = Integer.parseInt(inputs[1]); // 정수

                if(keyword.equals("I")){
                    Q_max.offer(n);
                    Q_min.offer(n);
                    count.put(n, count.getOrDefault(n, 0) + 1); // 'I'인 개수 기록
                } else if (keyword.equals("D")) {
                    if(count.isEmpty()) continue; // 남아있는 개수가 없으면 스킵

                    if(n == 1) clean(Q_max, count); // 최대값 큐 정리
                    else clean(Q_min, count);       // 최소값 큐 정리

                    if(n == 1 && !Q_max.isEmpty()){ // 최대값 삭제
                        int max = Q_max.poll();
                        count.put(max, count.get(max) - 1);
                        if(count.get(max) == 0) count.remove(max); // 남아있는 개수가 0이면 제거

                    } else if (n == -1 && !Q_min.isEmpty()) { // 최소값 삭제
                        int min = Q_min.poll();
                        count.put(min, count.get(min) - 1);
                        if(count.get(min) == 0) count.remove(min);
                    }
                }
            }
            clean(Q_max, count);
            clean(Q_min, count);

            if(Q_max.isEmpty() || Q_min.isEmpty()){
                sb.append("EMPTY\n");
            } else {
                sb.append(Q_max.peek()).append(" ").append(Q_min.peek()).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 불필요한 값 정리 (이미 삭제된 값 제거)
    // peek()은 큐 맨 앞(위)에 있는 값을 확인하므로, 그 값이 유효한지 검사해야함.
    // 안하면 Q_max와 Q_min 동기화가 깨질 수 있음.
    private static void clean(PriorityQueue<Integer> Q, HashMap<Integer, Integer> count) {
        while (!Q.isEmpty() && (!count.containsKey(Q.peek()) || count.get(Q.peek()) <= 0)) {
            Q.poll();
        }
    }
}

