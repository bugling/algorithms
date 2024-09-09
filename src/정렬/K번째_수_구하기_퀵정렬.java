package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 문제 ]
 * 백준 11004번
 * 수 N개 A[1], A[2], ..., A[N]이 주어진다. A를 오름차순 정렬했을 때, 앞에서부터 K번째 있는 수를 구하는 프로그램을 작성하시오.
 *
 * [ 입력 ]
 * 첫째 줄에 N(1 ≤ N ≤ 5,000,000)과 K (1 ≤ K ≤ N)이 주어진다.
 * 둘째에는 A[1], A[2], ..., A[N]이 주어진다. (-10^9 ≤ A[i] ≤ 10^9)
 * (예제 입력)
 5 2
 4 1 2 3 5
 *
 * [ 출력 ]
 * A를 정렬했을 때, 앞에서부터 K번째 있는 수를 출력한다.
 * (예제 출력)
 * 2
 *
 * [ 설명 ]
 * N의 최대 범위가 5,000,000이므로 O(nlogn)의 시간 복잡도로 정렬을 수행하면 된다.
 * 앞에서 배운 퀵 정렬을 구현해 주어진 수를 오름ㅊ순 정렬하고, K번째 수를 출력해 보겠다.
 * 단, 이 문제는 시간 복잡도가 민감하므로 퀵 정렬 알고리즘에서 K번째 수를 좀 더 빨리 구하기 위한 아이디어를 먼저 고미해 보겠다.
 * 퀵 정렬 알고리즘을 구현하려면 먼저 pivot을 지정해야 한다.
 * 이때 어떤 값을 pivot으로 정하면 K번째 수를 더 빨리 구할 수 있을지 생각해 보자.
 *
 * ( pivot을 정하는 방법 )
 * pivot == K: K번째 수를 찾는 것이므로 알고리즘을 종료한다.
 * pivot > K: pivot의 왼쪽 부분에 K가 있으므로 (S ~ pivot-1)만 정렬을 수행한다.
 * pivot < K: pivot의 오른쪽 부분에 K가 있으므로 오른쪽(pivot+1 ~ E)만 정렬을 수행한다.
 *
 * 1.중간 위치를 pivot으로 설정한 다음 맨 앞에 있는 값과 swap한다.
 *   pivot을 맨 앞으로 옮기는 이유는 i, j 이동을 편하게 하기 위함이다.
 *   이어서 i와 j를 pivot을 제외한 그룹에서 왼쪽, 오른쪽 끝으로 정한다.
 *
 *  (배열)
 *  p       j
 *  2 1 4 3 5
 *    i
 *
 * 2. 우선 j를 이동한다. j가 pivot보다 크면 j-- 연산을 반복한다.
 *    그 결과 j는 1에 위치하게 된다.
 *    j를 이동한 후에는 i가 pivot보다 작으면서 i보다 j가 크면 i++연산을 반복한다. ( A[i]<pivot && i<j)
 *    현재의 경우 i와 j의 위치가 같으므로 i는 이동하지 않는다.
 *
 * (배열)
 *  p j
 *  2 1 4 3 5
 *    i
 *
 * 3. pivot을 두 집합을 나눠 주는 위치, 즉 i와 j가 만난 위치와 swap한다.
 *
 *  (배열)
 *  2 1 4 3 5
 *  ( 2, 1 swap실행 ) -> 1 2 4 3 5
 *
 * 4. K는 2이므로 이제 더이상 정렬하지 않고 A[2]를 출력한다.
 *
 * (배열)
 * 1 2 4 3 5 -> K[2] == 2 -> 2출력
 *
 * [ 슈도 코드 ]
 * N: 숫자의 개수    K: K번째 수
 * A: 숫자 데이터 저장 배열
 * 
 * for(N만큼 반복하기) {
 *     A배열 저장하기
 * }
 * 
 * 퀵 소트 실행하기
 * K번째 데이터 출력하기
 * 
 * [별도의 함수 구현 부분]
 * 퀵 소트 함수(시작, 종료, K)
 * {
 *     피벗 구하기 함수(시작, 종료)
 *     if(피벗 == K) 종료
 *     else if(K < 피벗) 퀵 소트 수행하기(시작, 피벗-1, K)
 *     else 퀵 소트 수행하기(피벗+1, 종료, K)
 * }
 *
 * 피벗 구하기 함수(시작, 종료)
 * {
 *      데이터가 2개인 경우는 바로 비교하여 정렬
 *
 *      M: 중앙값
 *      중앙값을 시작 위치와 swap
 *      pivot을 시작 위치 값 A[S]로 저장
 *      while(i <= j)
 *      {
 *          피벗보다 큰 수가 나올 때까지 i++
 *          피벗보다 작은 수가 나올 때까지 j--
 *      }
 *      
 *      피벗 데이터를 나눠진 두 그룹의 경계 index에 저장하기
 *      경계 index 리턴
 * }
 * */
public class K번째_수_구하기_퀵정렬 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());       // 숫자의 개수
        int K = Integer.parseInt(st.nextToken());       // K번째 수
        st = new StringTokenizer(in.readLine());

        int[] A = new int[N];                           // 숫자 데이터 저장 배열
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(A, 0, N-1, K-1);
        System.out.println( A[K-1] );
    }

    /**
     * @param A (숫자 데이터 저장 배열)
     * @param S (시작 지점)
     * @param E (종료 지점)
     * @param K (찾는 인덱스)
     * **/
    public static void quickSort(int[] A, int S, int E, int K) {
        if(S < E) {

            // 피벗이 최종적으로 위치하게 된 인덱스
            // 배열의 중앙에 있던 값을 기준으로 작은 수는 왼편, 큰 수는 오른편에 배치시키고 pivot의 인덱스를 반환함
            int pivot_idx = partition(A, S, E);

            if(pivot_idx == K)
                return;
            else if(K < pivot_idx)
                quickSort(A, S, pivot_idx-1, K);
            else
                quickSort(A, pivot_idx+1, E, K);
        }
    }

    // 피벗 구하기 함수
    public static int partition(int[] A, int S, int E) {

        // 데이터가 2개인 경우는 바로 비교하여 정렬
        if(S + 1 == E) {    // " 시작인덱스+1 == 종료인덱스 "라는 건 요소가 2개 뿐이라는 뜻
            if(A[S] > A[E])
                swap(A, S, E); // 시작 지점 요소가 더 크면 종료 지점 요소와 교환

            return E;   // 종료 인덱스를 리턴
        }

        // 중앙 지점 인덱스를 M에 할당
        int M = (S + E) / 2;

        // 시작 요소 <--> 중앙 요소      // pivot을 맨 앞으로 옮기는 이유는 i, j 이동을 편하게 하기 위함
        swap(A, S, M);

        int pivot_value = A[S];   // 시작 지점 요소를 피벗으로 설정

        int i = S+1;    // 시작 인덱스 + 1       **A[S]는 피벗
        int j = E;      // 종료 인덱스

        while (i <= j){

            // 피벗보다 큰 수가 나오는 동안 j--
            while(j>=S+1 && pivot_value<A[j]) j--;

            // 피벗보다 작은 수가 나오는 동안 i++
            while(i<=E && pivot_value>A[i]) i++;

            // 두 while문이 종료된 시점 상태
            // j: 피벗보다 작거나 같은 수를 만남
            // i: 피벗보다 크나 같은 수를 만남

            // 두 인덱스가 교차하지 않았다면...
            // i와 j번째 요소를 교환하고 -> i++, j-- 수행
            if(i<=j)
                swap(A, i++, j--);  // 교차하는 이유: 피벗을 기준으로 작은 수는 왼쪽에, 큰 수는 오른쪽에 위치시키는 작업

            // while문 조건이 (i<=j, 왼쪽과 오른쪽 인덱스가 크로스될때 까지)
            //  true라면 위의 로직을 반복한다.
        }

        // i보다 왼쪽에 있는 j

        A[S] = A[j];    // 피벗과 j번째 요소를 스왑
        A[j] = pivot_value;
        return j;       // 피벗이 최종적으로 위치하게 된 인덱스를 반환
    }

    // A 배열의 i번째 요소와 j번째 요소를 바꾼다.
    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

