import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, S, count;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 1. 크기가 양수인 부분수열 중에서
		 * 2. 원소들의 합이 S가 되는 경우의 수를 찾는다.
		 * 3. N이 최대 20이므로 모든 조합을 DFS로 탐색해도 가능.
		 * 4. 공집합은 없어야함
		 */

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0, 0);

		System.out.println(count);
	}

	private static void dfs(int idx, int sum, int selected) {
		// 끝까지 탐색했을 때
		if (idx == N) {
			// 선택한 원소가 하나 이상이면서 합이 S면 카운트
			if (selected > 0 && sum == S) {
				count++;
			}
			return;
		}

		dfs(idx + 1, sum + arr[idx], selected + 1);
		dfs(idx + 1, sum, selected);
	}
}
