class Solution {
	public int solution(int[] diffs, int[] times, long limit) {
		int left = 1;
		int right = 100000;

		while (left < right) {
			int mid = (left + right) / 2;

			if (canSolve(diffs, times, limit, mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}

	private boolean canSolve(int[] diffs, int[] times, long limit, int level) {
		long total = 0;

		for (int i = 0; i < diffs.length; i++) {
			if (diffs[i] <= level) {
				total += times[i];
			} else {
				int wrong = diffs[i] - level;
				int timePrev = (i > 0) ? times[i - 1] : 0;
				total += (long) wrong * (times[i] + timePrev) + times[i];
			}

			if (total > limit) {
				return false;
			}
		}

		return true;
	}
}

//이 문제는 풀리지가 않아서 AI의 도움을 받았습니다..