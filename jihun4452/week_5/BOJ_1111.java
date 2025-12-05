import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 다음 수는 항상 앞 수 곱하기 a 더하기 b 규칙으로 결정됨
		 * a와 b는 정수,
		 * 가능한 규칙이 하나면 그 규칙으로 다음 수를 구하면 됨
		 * 규칙이 여러 개면 A를 출력해야 함
		 * 규칙을 못 찾으면 B를 출력해야 함
		 * N이 1이나 2면 규칙이 여러 가지이므로 A를 출력해야 함
		 *
		 * 이건 너무 오래걸려서 도움을 받았습니다.. 시도는 한시간10분정도.,,
		 */

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

		if (N == 1 || N == 2) {
			System.out.println("A");
			return;
		}

		Integer a = null, b = null;

		// 앞의 세 항으로 a와 b를 계산
		int x1 = arr[0], x2 = arr[1], x3 = arr[2];
		int d1 = x2 - x1;
		int d2 = x3 - x2;

		if (d1 != 0 && d2 % d1 == 0) {
			a = d2 / d1;
			b = x2 - x1 * a;
		}

		// a와 b를 못 찾으면 B를 출력하고 종료
		if (a == null || b == null) {
			System.out.println("B");
			return;
		}

		// 계산한게 맞는지 확인
		for (int i = 1; i < N; i++) {
			if (arr[i] != arr[i - 1] * a + b) {
				System.out.println("B");
				return;
			}
		}

		System.out.println(arr[N - 1] * a + b);
	}
}
