package 탐욕;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 백준 1744
 *
 * [ 문제 ]
 * 길이가 N인 수열이 주어졌을 때, 그 수열의 합을 구하려고 한다.
 * 하지만, 그냥 그 수열의 합을 모두 더해서 구하는 것이 아니라, 수열의 두 수를 묶으려고 한다.
 * 어떤 수를 묶으려고 할 때, 위치에 상관없이 묶을 수 있다. 하지만, 같은 위치에 있는 수(자기 자신)를 묶는 것은 불가능하다.
 * 그리고 어떤 수를 묶게 되면, 수열의 합을 구할 때 묶은 수는 서로 곱한 후에 더한다.
 * 예를 들면, 어떤 수열이 {0, 1, 2, 4, 3, 5}일 때, 그냥 이 수열의 합을 구하면 0+1+2+4+3+5 = 15이다.
 * 하지만, 2와 3을 묶고, 4와 5를 묶게 되면, 0+1+(2*3)+(4*5) = 27이 되어 최대가 된다.
 * 수열의 모든 수는 단 한번만 묶거나, 아니면 묶지 않아야한다.
 * 수열이 주어졌을 때, 수열의 각 수를 적절히 묶었을 때, 그 합이 최대가 되게 하는 프로그램을 작성하시오.
 *
 * [ 입력 ]
 * 첫째 줄에 수열의 크기 N이 주어진다. N은 50보다 작은 자연수이다.
 * 둘째 줄부터 N개의 줄에 수열의 각 수가 주어진다. 수열의 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
 *
 * [ 출력 ]
 * 수를 합이 최대가 나오게 묶었을 때 합을 출력한다. 정답은 항상 2^31보다 작다.
 *
 * [ 문제 분석 ]
 * N의 최대 범위가 10,000이므로 시간 복잡도와 관련된 제약은 적은 문제이다.
 * 가능한 한 큰 수끼리 묶어야 결괏값이 커진다는 것을 생각할 수 있다.
 * 또한 절댓값이 큰 음수끼리 곱하면 더 큰 양수를 만들 수 있다는 사실도 고려해야 한다.
 *
 * 1. 수의 집합을 '1보다 큰 수', '1', '0', '음수' 이렇게 4가지 유형으로 나눠 저장한다.
 * 2. 1보다 큰 수의 집합을 정렬해 최댓값부터 차례대로 곱한 후에 더한다.
 *    원소의 개수가 홀수일 경우, 마지막 남은 수는 그대로 더한다.
 * 3. 음수의 집합을 정렬해 최솟값(절댓값이 큰 수)부터 차례대로 곱한 후에 더한다.
 *    원소의 개수가 홀수일 때 수열에 0이 있다면 1개 남는 음수를 0과 곱해 0을 만들고, 수열에 0이 없다면 그대로 더한다.
 * 4. 2~3과정에 구한 값을 더하고, 그 값에 숫자 1의 개수를 더한다.
 *
 * [ 슈도 코드 ]
 * N: 카드 묶음 개수
 * plusPq: 양수 우선순위 큐
 * misulPq: 음수 우선순위 큐
 * one: 1의 개수 카운트
 * zero: 0의 개수 카운트
 * for(N만큼 반복하기) {
 *     데이터를 4개의 그룹에 분리 저장하기
 * }
 * while(양수 우선순위 큐 크기가 2보다 작을 때까지) {
 *     수 2개를 큐에서 뽑음(remove 연산)
 *     2개의 수를 곱한 값을 결괏값에 더함
 * }
 * 양수 우선순위 큐에 데이터가 남아 있으면
 * 이 데이터를 결괏값에 더함
 *
 * while(음수 우선순위 큐 크기가 2보다 작을 때까지) {
 *     수 2개를 큐에서 뽑음(remove 연산)
 *     2개의 수를 곱한 값을 결괏값에 더함
 * }
 * 음수 우선순위 큐에 데이터가 남아 있고, 데이터 0이 1개도 없으면
 * 이 데이터를 결괏값에 더함
 * 
 * 숫자 1의 개수를 결괏값에 더함
 * 결괏값 출력
 * */
public class 수를_묶어서_최댓값_만들기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();       // 카드 묶음의 수 저장하기
        PriorityQueue<Integer> plusPq = new PriorityQueue<>(Collections.reverseOrder());    // 양수는 내림차순 정렬하기
        PriorityQueue<Integer> minusPq = new PriorityQueue<>(); // 음수는 오름차순 정렬 -> 절댓값이 큰 음수끼리 곱하도록 하여 최대한 큰 양수를 만듦
        int oneCnt = 0;
        int zeroCnt = 0;
        for (int i = 0; i < N; i++) {
            int data = sc.nextInt();
            if (data > 1) {
                plusPq.add(data);
            } else if (data == 1) {
                oneCnt++;
            } else if (data == 0) {
                zeroCnt++;
            } else {
                minusPq.add(data);
            }
        }
        int sum = 0;
        // 양수 처리하기
        while (plusPq.size() > 1) {
            int first = plusPq.remove();
            int second = plusPq.remove();
            sum = sum + (first * second);
        }
        if (!plusPq.isEmpty()) {
            sum = sum + plusPq.remove();
        }
        // 음수 처리하기
        while (minusPq.size() > 1) {
            int first = minusPq.remove();
            int second = minusPq.remove();
            sum = sum + (first * second);
        }

        if (!minusPq.isEmpty()) {
            if (zeroCnt == 0) {
                sum = sum + minusPq.remove();
            }
//            else {
//                sum = sum + (minusPq.remove() * 0);     // 0이 존재하면 음수와 곱하여 0을 만들어 가산하게 됨.   -> 생략 가능
//            }
        }
        // 1 처리하기
        sum = sum + oneCnt;
        System.out.println("sum = " + sum);
    }
}
