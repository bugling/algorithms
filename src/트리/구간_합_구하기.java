package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [ 문제 ]
 * 어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다.
 * 만약에 1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17을 출력하면 되는 것이다.
 * 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.
 *
 * [ 입력 ]
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다.
 * M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다.
 * 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다.
 * 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데,
 * a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.
 * 입력으로 주어지는 모든 수는 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.
 * 
 * ( 예제 입력 )
5 2 2
1
2
3
4
5
1 3 6		// A[3] -> 6
2 2 5		// [2]~[5] 합
1 5 2		// A[5] -> 2
2 3 5		// [3]~[5] 합
 * 
 * [ 출력 ]
 * 첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다.
 * 단, 정답은 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.
 * 
 * 17
 * 12
 * 
 * [ 설명 ]
 * 합 배열은 자료구조의 특성상 데이터 변경에 시간이 오래 걸리는 단접이 있다.
 * 따라서 이 문제는 데이터 변경에도 시간이 비교적 적게 걸리는 세그먼트 트리 자료구조를 이용해 해결한다.
 * 
 * 1. 1차원 배열을 이용해 트리의 값을 초기화한다.
 *    트리 배려의 크기가 N=5이므로 2^k>=N을 만족하는 k의 값은 3이고, 배열의 크기가 2^3=start_index = 8이 된다.
 * 2. 질의값 연산 함수와 데이터 업데이트 함수를 수행하고, 질의와 관련된 결괏값을 출력한다.
 * 
 * [ 슈도 코딩 ]
 * tree: 세그먼트 트리 배열
 * N: 수의 개수, M: 변경이 일어나는 개수, K: 구한 합을 구하는 개수
 * treeSize 구하기 -> Math.pow(2, 트리의 높이+1)
 * leftNodeStartIndex 구하기 -> treeSize / 2 - 1		// 리프 노드 시작 인덱스
 * tree 배열의 리프 노드 영역에 데이터 입력하기
 * setTree: 트리의 크기
 * for(M+K만큼 반복하기) {
 * 		a: 질의 유형, s: 시작 인덱스, e: 변경값 또는 종료 인덱스
 * 		a가 1일 때 -> changeVal(tree에서 시작 인덱스, e(변경값))			// 데이터 병경 함수
 * 		a가 2일 때 -> getSum(tree에서 시작 인덱스, tree에서 종료 인덱스)		// 구간 합 함수 호출 및 출력
 * }
 * 
 * // 구간 합을 구하는 함수 구현하기
 * getSum(시작 인덱스, 종료 인덱스) {
 * 		while(시작 인덱스와 종료 인덱스가 교차될 때까지) {
 * 			if(시작 인덱스 % 2 == 1) 해당 노드의 값을 구간 합에 추가하거나 시작 인덱스 증가
 * 			if(종료 인덱스 % 2 == 0) 해당 노드의 값을 구간 합에 추가하거나 종료 인덱스 감소
 * 
 * 			시작 인덱스 = 시작 인덱스 / 2
 * 			종료 인덱스 = 종료 인덱스 / 2
 * 		}
 * 
 * 		구간 합 결과 리턴하기
 * }
 * 
 * // 값 변경 함수 구현하기
 * changeVal(시작 인덱스, 변경값) {
 * 		diff: 현재 노드의 값과 변경된 값의 차이
 * 		while(시작 인덱스가 0보다 크다) {
 * 			시작 인덱스의 트리값에 diff값을 더하기
 * 			시작 인덱스 = 시작 인덱스 / 2
 * 		}
 * }
 * 
 * // 초기 트리 생성 함수 구현하기
 * setTree(트리의 마지막 인덱스) {
 * 		while(인덱스가 루트가 아닐 때까지 반복하기) {
 * 			트리의 인덱스 / 2 부분(부모 노드)에 현재 index의 트리값 더하기
 * 			index 1개 감소하기
 * 		}
 * } 
 * 
 * 
 * **/
public class 구간_합_구하기 {

	static long[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int treeHeight = 0;
		int length = N;
		
		while(length != 0) {
			length /= 2;
			treeHeight++;
		}
		
		int treeSize = (int) Math.pow(2, treeHeight+1);		// 16
		int leftNodeStartIndex = treeSize / 2 - 1;			// 7
		tree = new long[treeSize + 1];
		
		// 데이터를 리프 노드에 입력받기		8 ~ 15 (7+8)
		for(int i=leftNodeStartIndex+1; i<=leftNodeStartIndex+N; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}
		
		setTree(treeSize - 1);
		
		for(int i=0; i<M+K; i++) {		// M + K: 변경 횟수 + 구간합 구하는 횟수
			st 		= new StringTokenizer(br.readLine());
			long a 	= Long.parseLong(st.nextToken());
			int s 	= Integer.parseInt(st.nextToken());
			long e  = Long.parseLong(st.nextToken());
			
			if(a == 1) {
				changVal(leftNodeStartIndex + s, e);
			}else if(a == 2) {
				s = s + leftNodeStartIndex;
				e = e + leftNodeStartIndex;
				System.out.println( getSum(s, (int)e) );
			}
		}
	}

	private static long getSum(int s, int e) {
		long partSum = 0;
		while(s <= e) {
			if(s % 2 == 1) {
				partSum = partSum + tree[s];
				s++;
			}
			if(e % 2 == 0) {
				partSum = partSum + tree[e];
				e--;
			}
			s = s / 2;
			e = e / 2;
		}
		
		return partSum;
	}

	private static void changVal(int index, long val) {
		long diff = val - tree[index];
		while(index > 0) {
			tree[index] = tree[index] + diff;
			index = index / 2;
		}
	}

	// 초기 트리를 구성하는 함수
	private static void setTree(int i) {
		while(i != 1) {
			tree[i / 2] += tree[i];			// 자식노드의 합을 부모노드의 값으로 셋팅
			i--;							// 뒤에서 앞 방향으로 셋팅 진행
		}
	}

}
