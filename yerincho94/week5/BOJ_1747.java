package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제: N 이상 중에서 “소수”이면서 “팰린드롬”인 수들 중 가장 작은 수
 * - 소수: 1과 자기 자신으로만 나누어떨어지는 1보다 큰 자연수
 * - 팰린드롬: 앞에서 읽으나 뒤에서 읽으나 같은 수 (예: 1, 121, 12321)
 *
 * N의 최대값은 1,000,000이고,
 * 이 문제의 정답은 1,003,001 이하에 항상 존재하는 것이 알려져 있음.
 * => while(true)지만, 실제로는 1,003,001 이전에 반드시 종료됨.
 */
// 소수&팰린드롬 (실버1)
public class BOJ_1747 {
    // 숫자가 팰린드롬인지 확인하는 함수
    public static boolean isPalindrome(int num) {
        String str = String.valueOf(num); // 숫자를 문자열로 변환
        int left = 0; // 왼쪽 포인터
        int right = str.length() - 1; // 오른쪽 포인터

        // 양 끝에서 중앙으로 이동하며 문자 비교
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // 다르면 팰린드롬이 아님
            }
            left++;
            right--;
        }
        return true; // 모두 같으면 팰린드롬
    }

    // 숫자가 소수인지 확인하는 함수
    public static boolean isPrime(int num) {
        // 2보다 작은 수는 소수가 아님
        if (num < 2) return false;

        // 2는 유일한 짝수 소수
        if (num == 2) return true;

        // 2를 제외한 짝수는 모두 소수가 아님
        if (num % 2 == 0) return false;

        // 3부터 √num까지 홀수로만 나누어떨어지는지 확인
        // √num까지만 확인하는 이유:
        // num = a * b 일 때, a와 b 중 하나는 반드시 √num 이하
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false; // 나누어떨어지면 소수가 아님
            }
        }
        return true; // 모든 검사를 통과하면 소수
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 입력값 N

        // N부터 시작해서 조건을 만족하는 첫 번째 수를 찾음
        // 조건: 소수이면서 팰린드롬
        while (true) {
            // 팰린드롬이면서 소수인지 확인
            // 순서 주의: 팰린드롬 검사가 더 빠르므로 먼저 수행
            if (isPalindrome(N) && isPrime(N)) {
                System.out.println(N);
                break; // 조건을 만족하는 수를 찾았으므로 종료
            }
            N++; // 다음 수로 이동
        }
    }
}
