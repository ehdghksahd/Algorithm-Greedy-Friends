// https://school.programmers.co.kr/learn/courses/30/lessons/340212
// 퍼즐 게임 챌린지

class Solution {
    public boolean chk(int[] diffs, int[] times, long limit ,int level) {
        long time = 0;
        int prev = 0;

        for(int i = 0; i < diffs.length; i++) {
            int cur = times[i];
            long solvedTime = 0;

            if(diffs[i] <= level) solvedTime = cur; // 레벨 값 이하 일때
            else { // 아닐 때

                int t = diffs[i] - level;
                solvedTime =(long) (t * (cur + prev) + cur);
            }
            time += solvedTime;

            if(time > limit) return false;

            prev = cur; // 현재 푼 시간을 저장 (이전 퍼즐을 다시 풀 때는 이전 퍼즐의 난이도에 상관없이 틀리지 않습니다.)
        }

        return true;
    }
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int start = 1;
        int end = 100000;

        while(end >= start) { // 이분 탐색 시작
            int level = (start + end) / 2;

            if(chk(diffs, times, limit, level)) { // limit 넘지 않으면 끝값 = 중간 값 -1 // 최소 값 찾기 위해
                answer = level;
                end = level - 1;
            }
            else start = level + 1; // 넘으면 시작값 = 중간값 + 1
        }


        return answer;
    }
}