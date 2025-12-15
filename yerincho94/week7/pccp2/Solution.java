package week7.pccp2;

/**
 * 퍼즐이 여러 개 있고, 각 퍼즐마다:
 * - 난이도(diff)
 * - 소요 시간(time_cur)
 * - 이전 퍼즐 시간(time_prev)
 *
 * 숙련도(level)에 따라:
 * - diff ≤ level: 한 번에 풀림 (time_cur 소요)
 * - diff > level: 틀림 (diff - level)번 틀린 후 성공
 *   → 틀릴 때마다: time_cur + time_prev 소요
 *   → 마지막 성공: time_cur 소요
 *
 * 제한 시간(limit) 내에 모든 퍼즐을 풀 수 있는 최소 숙련도는?
 *
 * 이분탐색 -> 정렬된 배열에서 특정 값을 빠르게 찾기
 */
// https://school.programmers.co.kr/learn/courses/30/lessons/340212
// [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지 (이분탐색)
public class Solution {

    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1; // // 탐색 범위 시작 (최소 숙련도)
        int right = 100000; // 탐색 범위 끝 (최대 숙련도)
        int answer = right;

        while (left <= right) { // 1, 100000
            int mid = (left + right) / 2; // 중간값 계산

            if (canSolve(mid, diffs, times, limit)) {
                // 성공 → 더 작은 값 찾기
                answer = mid;
                right = mid - 1;
            } else {
                // 실패 → 더 큰 값 필요
                left = mid + 1;
            }
        }

        return answer;
    }

    // 주어진 숙련도(level)로 제한 시간 내에 풀 수 있는지 확인
    public boolean canSolve(int level, int[] diffs, int[] times, long limit) {
        long totalTime = 0; // 누적용

        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i]; // 난이도
            int timeCur = times[i]; // 현재 퍼즐 시간
            int timePrev = (i == 0) ? 0 : times[i - 1]; // 이전 퍼즐시간

            if (diff <= level) {
                // 한 번에 성공
                totalTime += timeCur;
            } else {
                // 틀린 후 성공
                int mistakes = diff - level;
                totalTime += (long)(timeCur + timePrev) * mistakes + timeCur;
            }

            // 시간 초과하면 바로 false (조기종료)
            if (totalTime > limit) return false;
        }

        return totalTime <= limit;
    }
}
