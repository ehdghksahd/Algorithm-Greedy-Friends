package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분수열의 합 (실버2)
public class BOJ_1182 {
    static int N, S;
    static int[] arr;
    static int count = 0; // 합이 S가 되는 경우의 수

    // 부분수열을 만들면서 합을 계산
    public static void solve(int index, int sum) {
        // 기저 조건: 모든 원소를 다 확인했을 때
        if (index == N) {
            // 부분수열의 합이 S와 같다면 카운트 증가
            if (sum == S) {
                count++;
            }
            return;
        }

        // 현재 원소(arr[index])를 부분수열에 포함
        solve(index + 1, sum + arr[index]);

        // 현재 원소를 부분수열에 포함하지 않음
        solve(index + 1, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정수의 개수
        S = Integer.parseInt(st.nextToken()); // 목표 합

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 0번 인덱스부터 시작, 초기 합은 0
        solve(0, 0);

        // 주의: 크기가 양수인 부분수열만 세어야 함
        // sum = 0으로 시작했기 때문에 아무것도 선택하지 않은 경우(공집합)도 포함됨
        // S가 0인 경우, 공집합도 합이 0이므로 1을 빼줘야 함
        if (S == 0) {
            count--; // 공집합 제거
        }

        System.out.println(count);
    }
}
