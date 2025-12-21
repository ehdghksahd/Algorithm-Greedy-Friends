import java.util.*;

class Solution {
	public int solution(int[] queue1, int[] queue2) {
		int n = queue1.length;

		long[] arr = new long[2 * n];
		long sum1 = 0, total = 0;

		for (int i = 0; i < n; i++) {
			arr[i] = queue1[i];
			arr[i + n] = queue2[i];
			sum1 += queue1[i];
			total += queue1[i] + queue2[i];
		}

		if (total % 2 != 0) {
			return -1;
		}

		long target = total / 2;

		int left = 0;
		int right = n;
		int count = 0;
		int maxValue = 4 * n;

		while (count <= maxValue) {
			if (sum1 == target) {
				return count;
			}

			if (sum1 > target) {
				sum1 -= arr[left];
				left++;
			} else {
				if (right >= 2 * n) {
					return -1;
				}
				sum1 += arr[right];
				right++;
			}
			count++;
		}

		return -1;
	}
}