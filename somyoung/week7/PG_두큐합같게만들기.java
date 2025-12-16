// PG / 두 큐 합 같게 만들기 / Lv. 2
package somyoung.week7;

import java.util.*;

class PG_두큐합같게만들기 {
    public static int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long q1sum = 0;
        long q2sum = 0;

        for (int num : queue1) {
            q1.offer(num);
            q1sum += num;
        }

        for (int num : queue2) {
            q2.offer(num);
            q2sum += num;
        }

        if((q1sum + q2sum) % 2 != 0) return -1; // 모든 원소의 합이 홀수면 return

        int max = queue1.length * 4; // 작업 횟수 max값 안전하게 *4
        int cnt = 0; // 작업 횟수

        while(cnt <= max){
            if(q1sum == q2sum) return cnt;

            if(q1sum > q2sum){
                if(q1.isEmpty()) break;
                int peek = q1.poll();
                q2.offer(peek);
                q1sum -= peek;
                q2sum += peek;
            } else { // q1sum < q2sum
                if(q2.isEmpty()) break;
                int peek = q2.poll();
                q1.offer(peek);
                q2sum -= peek;
                q1sum += peek;
            }

            cnt++;
        }

        // max번 까지 실행했는데도 두 큐의 합을 맞추지 못하면 -1 return
        return -1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1}));
        System.out.println(solution(new int[]{1, 2, 1, 2}, new int[]{1, 10, 1, 2}));
        System.out.println(solution(new int[]{1, 1}, new int[]{1, 5}));
    }
}
