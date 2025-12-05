// [BOJ] 1747: 소수&팰린드롬
package somyoung.week5;

import java.io.*;
import java.util.*;

public class BOJ_1747 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        while(true){
            // n부터 1씩 증가시키면서 검사, 팰린드롬/소수를 모두 만족할 경우 루프 탈출
            if(isPalindrome(n) && isPrime(n)) break;
            n++;
        }

        System.out.print(n);
    }

    static boolean isPalindrome(int num){
        String natural = String.valueOf(num);
        String reverse = new StringBuilder(natural).reverse().toString();

        if(natural.equals(reverse)) return true;
        else return false;
    }

    static boolean isPrime(int num){
        if(num == 1){
            return false;
        }

        for(int i=2; i<=Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }

        return true;
    }
}

