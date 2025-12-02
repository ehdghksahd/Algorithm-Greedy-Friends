package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 규칙은 앞 수*a + b이다. 그리고, a와 b는 정수
 */
// IQ Test(골드3)
public class BOJ_1111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수열의 길이

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 경우 1: 수열의 길이가 1인 경우
        // 다음에 올 수를 특정할 수 없으므로 여러 가능성 존재
        if (N == 1) {
            System.out.println("A");
            return;
        }

        // 경우 2: 수열의 길이가 2인 경우
        if (N == 2) {
            // 두 수가 같으면 다음 수도 같은 수 (a=1, b=0 또는 a=0, b=arr[0] 등)
            if (arr[0] == arr[1]) {
                System.out.println(arr[0]);
            } else {
                // 두 수가 다르면 무수히 많은 규칙 가능
                // 예: 1, 2 → 다음이 3일 수도(a=1,b=1), 4일 수도(a=2,b=0) 있음
                System.out.println("A");
            }
            return;
        }

        // 경우 3: 수열의 길이가 3 이상인 경우
        // 규칙: X_n = a * X_(n-1) + b
        // 첫 세 개의 수로 a, b를 구함
        int a, b;

        // X_2 = a * X_1 + b ... (1)
        // X_3 = a * X_2 + b ... (2)
        // (2) - (1): X_3 - X_2 = a * (X_2 - X_1)

        // 케이스 3-1: 첫 두 수가 같은 경우
        if (arr[0] == arr[1]) {
            // 모든 수가 같아야 함
            boolean allSame = true;
            for (int i = 2; i < N; i++) {
                if (arr[i] != arr[0]) {
                    allSame = false;
                    break;
                }
            }

            if (allSame) {
                System.out.println(arr[0]); // 다음 수도 같은 수
            } else {
                System.out.println("B"); // 규칙에 맞지 않음
            }
            return;
        }

        // 케이스 3-2: 첫 두 수가 다른 경우
        // a = (X_3 - X_2) / (X_2 - X_1)
        // 정수 나눗셈이 가능한지 확인
        if ((arr[2] - arr[1]) % (arr[1] - arr[0]) != 0) {
            System.out.println("B"); // a가 정수가 아니면 규칙 없음
            return;
        }

        a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
        b = arr[1] - a * arr[0]; // b = X_2 - a * X_1

        // 구한 a, b로 모든 수열이 규칙을 만족하는지 확인
        boolean valid = true;
        for (int i = 1; i < N; i++) {
            // X_i = a * X_(i-1) + b 인지 확인
            if (arr[i] != a * arr[i - 1] + b) {
                valid = false;
                break;
            }
        }

        if (valid) {
            // 다음 수 계산: X_N = a * X_(N-1) + b
            int next = a * arr[N - 1] + b;
            System.out.println(next);
        } else {
            System.out.println("B"); // 규칙에 맞지 않음
        }
    }
}
