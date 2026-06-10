import java.io.*;
import java.util.*;

public class Main {
    /*
    소수 조건:
    - 첫번째자리숫자 2,3,5,7
    - 두번째이하자리숫자 1,3,7,9
    - 따라서 앞자리에서 검증 안되면 최대 79999999 까지 검사 필요.
    */
    public static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n자리수
        N = Integer.parseInt(br.readLine());

        // 1자리인경우 바로 답 출력
        if (N==1) {
            System.out.println("2\n3\n5\n7\n");
            return;
        }

        StringBuilder sb = new StringBuilder();

        dfs(2, sb);
        dfs(3, sb);
        dfs(5, sb);
        dfs(7, sb);

        System.out.println(sb);
    }

    // 깊이우선탐색(끝까지 찍고 다음으로)
    public static void dfs(int nowNum, StringBuilder sb) {
        // 들어온 수가 최대자리수와 같으면 밑으로 내려가지 않고 끝냄
        int nowDigit = (int)(Math.log10(nowNum)+1);
        if (nowDigit==N) return;

        // 자리수 더하고 1의자리에 1,3,5,7,9 추가후 테스트
        for (int i=1; i<=9; i+=2) {
            int nextNum = (nowNum*10) + i;
            // 다음 수가 소수인지 판단 후
            if (isPrime(nextNum)) {
                if ((nowDigit+1)==N) {
                    // 최종 자리수에 맞으면 출력에 더하기
                    sb.append(nextNum).append("\n");
                } else {
                    // 최종 자리수와 안맞으면 다음으로 넘김
                    dfs(nextNum, sb);
                }
            }
        }
    }

    // 소수검증 함수
    // (2자리이상)소수 검증법 : 3부터 나눠서 나머지가 0이면 탈락 -> ... -> 해당수의 1/3지점까지 나눠보면 됨.
    // (7*11) = 77 -> 3~25까지 나누어보다 7에서 걸려서 탈락.
    public static boolean isPrime(int num) {
        for (int i=3; i<=(num/3); i++) {
            if (num%i==0) return false;
        }
        return true;
    }
}
