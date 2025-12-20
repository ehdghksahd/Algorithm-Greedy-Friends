// PG / 퍼즐 게임 챌린지 / Lv. 2
package somyoung.week7;

import java.util.*;

class PG_퍼즐게임챌린지 {
    public static int solution(int[] diffs, int[] times, long limit) {
        // 1 ≤ diffs[i] ≤ 100,000
        int max = 1;
        int min = 100000;
        int n = diffs.length;

        while(max <= min){
            int mid = (max + min) / 2; // 이진 탐색 이용, 숙련도

            long time = 0;
            for(int i=0; i<n; i++){
                if(diffs[i] <= mid){ // diff ≤ mid -> 퍼즐을 틀리지 않고 time_cur만큼의 시간을 사용
                    time += (long)times[i];
                } else { // diff > mid -> (diff - mid) * (time_cur + time_prev) + time_cur
                    time += (long)(diffs[i] - mid) * (times[i] + times[i-1]) + times[i];
                }
            }

            // 제한 시간을 초과 -> 숙련도 상승 -> max = mid + 1
            if(time > limit){
                max = mid + 1;
            } else { // 해당 숙련도에서 해결 -> 최솟값을 찾기 위해 숙련도 하락 -> min = mid - 1
                min = mid - 1;
            }
        }

        return max;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 5, 3}, new int[]{2, 4, 7}, 30));
        System.out.println(solution(new int[]{1, 4, 4, 2}, new int[]{6, 3, 8, 2}, 59));
        System.out.println(solution(new int[]{1, 328, 467, 209, 54}, new int[]{2, 7, 1, 4, 3}, 1723));
        System.out.println(solution(new int[]{1, 99999, 100000, 99995}, new int[]{9999, 9001, 9999, 9001}, 3456789012L));
    }
}
