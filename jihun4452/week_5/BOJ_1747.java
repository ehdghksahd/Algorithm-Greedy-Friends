import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 1. N보다 크거나 같은 수부터 시작한다.
		 * 2. 해당 숫자가 팰린드롬인지 검사한다.
		 * 3. 해당 숫자가 소수인지 검사한다.
		 * 4. 두 조건을 모두 맞는다면 종료해야함.
		 */

		int n = Integer.parseInt(br.readLine());
		int num = n;

		while (true) {
			if (isPalindrome(num) && isPrime(num)) {
				System.out.println(num);
				break;
			}
			num++;
		}
	}

	private static boolean isPalindrome(int x) {
		String s = String.valueOf(x);
		int left = 0, right = s.length() - 1;

		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) return false;
			left++;
			right--;
		}
		return true;
	}

	private static boolean isPrime(int x) {
		if (x < 2) return false;
		if (x == 2) return true;
		if (x % 2 == 0) return false;

		for (int i = 3; i * i <= x; i += 2) {
			if (x % i == 0) return false;
		}
		return true;
	}
}
