package week7.liveCoding;

/**
 * 두 개의 큐가 있고, 각 큐의 원소 합을 같게 만들기
 *
 * 연산:
 * 1. queue1에서 pop → queue2에 insert
 * 2. queue2에서 pop → queue1에 insert
 * 최소 연산 횟수는? (불가능하면 -1)
 *
 * 전체 합 = queue1 합 + queue2 합
 * 각 큐의 목표 합 = 전체 합 / 2
 * ∴ 홀수면 불가능하겠네?
 */
// https://school.programmers.co.kr/learn/courses/30/lessons/118667
// 두 큐 합 같게 만들기
public class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int q1Length = queue1.length;

        // 일단 큐 하나에 합치기
        int[] qArr = new int[q1Length * 2];
        // 오버플로우 발생 가능성이 있으므로 long타입으로
        long q1Sum = 0; // queue1의 합
        long q2Sum = 0; // queue2의 합

        for (int i = 0; i < q1Length; i++) {
            qArr[i] = queue1[i]; // [3,2,7,2]
            q1Sum += queue1[i]; // 14
        }

        for (int i = 0; i < queue2.length; i++) {
            qArr[q1Length + i] = queue2[i]; // [3,2,7,2,4,6,5,1]
            q2Sum += queue2[i]; // 16
        }

        // 전체합
        long total = q1Sum + q2Sum;
        // 홀수면 계산 불가능
        if(total % 2 != 0) return -1;
        // 각 큐의 목표합!!
        long target = total / 2; // 10
        int cnt = 0; // 최소 횟수 누적용
        int max = q1Length * 3;
        int index1 = 0, index2 = q1Length;

        while (cnt <= max) {
            if (q1Sum == target) return cnt; // 목표달성했음으로 작업횟수 리턴
            if (q1Sum < target) { // q2에서 가져오기
                if (index2 >= q1Length * 2) break; // 범위체크!!!

                q1Sum += qArr[index2]; // 20
                q2Sum -= qArr[index2]; // 12
                index2++;
            } else { // q1에서 -> q2로 보내기
                if (index1 >= q1Length * 2) break; // 범위체크!!!

                q1Sum -= qArr[index1]; // 11
                q2Sum += qArr[index1]; // 16
                index1++;
            }
            cnt++;
        }
        // 불가능일때
        return -1;
    }
}
