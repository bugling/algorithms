package 탐욕;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 백준 1931
 *
 * [ 문제 ]
 * 한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다.
 * 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
 * 단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
 * 회의의 시작시간과 끝나는 시간이 같을 수도 있다.
 * 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
 *
 * [ 입력 ]
 * 첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다.
 * 둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.
 * 시작 시간과 끝나는 시간은 2^31-1보다 작거나 같은 자연수 또는 0이다.
 *
 * [ 출력 ]
 * 첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
 *
 * [ 분석 ]
 * 문제에서는 1개의 회의실에 회의가 겹치지 않게 최대한 많은 회의를 배정해야 한다.(최소 1개는 가능함)
 * 이때는 그리디 알고리즘을 적용해야 하는데, 현재 회의의 종료 시간이 빠를수록 다음 회의와 겹치지 않게 시작하는 데 유리하다.
 * 그렇기 때문에 종료 시간이 빠른 순서대로 정렬해 겹치지 않는 회의실을 적절하게 선택하면 된다.
 *
 * 1. 회의 정보와 관련된 데이터를 저장한 후 종료 시간 순으로 정렬한다.
 *    단, 종료 시간이 같을 때는 시작 시간을 기준으로 다시 한 번 정렬한다.
 * 2. 순차적으로 탐색하다가 시간이 겹치지 않는 회의가 나오면 선택한다.
 * ※ 종료 시간이 같을 때는 시작 시간이 빠른 순으로 정렬하는 기준이 포함돼야 한다.
 *    일반적으로 시작 시간이 빨리 시작해야 빨리 끝날 확률이 높기 때문.
 *
 * [ 수도 코드 ]
 * N: 회의 개수
 * A: 회의 정보 저장
 * A 배열 정렬 수행하기(종료 시간 기준으로 정렬, 종료 시간이 같으면 시작 시간 기준 정렬)
 * for(회의실의 개수만큼 반복하기) {
 *     if(앞 회의의 종료 시간보다 시작 시간이 빠른 회의가 나온 경우) {
 *         현재 회의의 종료 시간으로 종료 시간 업데이트하기
 *         진행할 수 있는 회의 수 1 증가
 *     }
 * }
 * 총 진행 가능 회의 수 출력하기
 *
 * */
public class 회의실_배정하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] A = new int[N][2];
        for (int i = 0; i < N; i++) {
            A[i][0] = sc.nextInt();
            A[i][1] = sc.nextInt();
        }
        Arrays.sort(A, new Comparator<int []>() {
            @Override
            public int compare(int[] S, int[] E) {
                if (S[1] == E[1]) {     // 종료 시간이 같을 때
                    return S[0] - E[0];
                }
                return S[1] - E[1];
            }
        });
        int count = 0;
        int end = -1;
        for (int i = 0; i < N; i++) {
            if(A[i][0] >= end) {    // 종료 시점과 같거나 큰(겹치지 않는) 시작 시간이 나오면
                end = A[i][1];      // 겹치지 않는 회의의 종료 시간을 전체 종료 시간으로 지정
                count++;            // 겹치지 않고 진행할 수 있는 회의 개수
            }
        }
        System.out.println("count = " + count);
    }
}
