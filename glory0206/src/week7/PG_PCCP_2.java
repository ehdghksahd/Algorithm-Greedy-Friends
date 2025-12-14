package week7;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int maxDiffs = 0;

        for (int diff : diffs) { // 가장 높은 난이도 찾기
            maxDiffs = Math.max(maxDiffs, diff);
        }

        int left = 1;
        int right = maxDiffs; // 모두 한 번에 풀 수 있는 레벨
        int clearLevel = 0;

        while(left<=right){
            int mid = left + (right - left) / 2; //
            if(clear(mid, diffs, times, limit)){ // mid레벨로 제한시간을 넘지 않고 풀이 가능하다면
                clearLevel = mid;
                right = mid-1; // 레벨을 낮춰본다
            } else{
                left = mid+1; // 레벨을 높여본다
            }
        }

        return clearLevel;
    }

    private boolean clear(int level, int[] diffs, int[] times, long limit){
        long count = 0; // 시간 제한을 long으로 받아서 걸리는 시간도 long으로 표기

        if(diffs[0] > level){ // 첫 난이도가 현 레벨보다 높다면 갈 수 있는 이전의 단계가 없으므로 불가능한 레벨
            return false;
        }

        count += times[0]; // 첫 문제는 바로 해결

        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] <= level) { // 레벨이 난이도보다 높을 경우 바로 해결 가능
                count += times[i];
            } else {
                int fail = diffs[i] - level;
                count += (long) fail * (times[i] + times[i - 1]) + times[i]; // 틀린 횟수 * (이전+현재 풀이시간) + 현재 풀이시간
            }

            if (count > limit){ // 문제 풀이가 남아있는데, 제한 시간을 넘었을 경우 불가능
                return false;
            }
        }
        return count <= limit;
    }
}