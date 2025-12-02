import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1747

public class Main{
    // 최대 탐색 범위 설정
    private static final int MAX = 10000000;
    private static boolean[] isPrime;

    private static void sieveOfEratosthenes() { // 에라토스테네스의 체
        isPrime = new boolean[MAX + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(MAX); i++) {
            if (isPrime[i]) {
                // i의 배수들을 모두 소수가 아니라고 표시
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    private static boolean palindrome(int N) { // 팰린드롬
        String str = Integer.toString(N);
        int left = 0;
        int right = str.length() - 1;

        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sieveOfEratosthenes(); // 미리 소수 계산

        int N = Integer.parseInt(br.readLine());

        for (int i = N; i <= MAX; i++) {
            if (isPrime[i]) {
                if (palindrome(i)) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}