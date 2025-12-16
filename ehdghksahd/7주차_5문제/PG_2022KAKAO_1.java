import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/118667
// 두 큐 합 같게 만들기

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1 = 0;
        long sum2 = 0;
        long total = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for(int i = 0; i < queue1.length; i++) {
            sum1 += (long)queue1[i];
            q1.offer(queue1[i]);

            sum2 += (long)queue2[i];
            q2.offer(queue2[i]);
        }

        total = (sum1+sum2) / 2;
        int limits = queue1.length * 3;

        while(answer <= limits) {
            if(sum1 == sum2) return answer;

            if(sum1 > sum2) {
                int tmp = q1.poll();
                q2.offer(tmp);
                answer++;
                sum2 += tmp;
                sum1 -= tmp;
            }
            else {
                int tmp = q2.poll();
                q1.offer(tmp);
                answer++;
                sum1 += tmp;
                sum2 -= tmp;
            }

        }

        return -1;
    }
}