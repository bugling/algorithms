package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11505
 * 
 * [ 문제 ]
 * 어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 곱을 구하려 한다.
 * 만약에 1, 2, 3, 4, 5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 곱을 구하라고 한다면 240을 출력하면 되는 것이다.
 * 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 곱을 구하라고 한다면 48이 될 것이다.
 * 
 * [ 입력 ]
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다.
 * M은 수의 변경이 일어나는 횟수이고, K는 구간의 곱을 구하는 횟수이다.
 * 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다.
 * 그리고 N+2번째 줄부터 N+M+K+1 번째 줄까지 세 개의 정수 a,b,c가 주어지는데, a가 1인 경우 b번째 수를 c로 바꾸고 a가 2인 경우에는 b부터 c까지의 곱을 구하여 출력하면 된다.
 * 입력으로 주어지는 모든 수는 0보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.
 * 
 * ( 예제 입력 )
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5
 * 
 * [ 출력 ]
 * 첫째 줄부터 K줄에 걸쳐 구한 구간의 곱을 1,000,000,007로 나눈 나머지를 출력한다.
 * 
 * ( 예제 출력 )
 * 240
 * 48
 * 
 * [ 설명 ]
 * 1. 1차원 배열로 트리의 값을 초기화한다. 트리 배열의 크기가 N=5이므로 2^k>=N을 만족하는 k의 값은 3이고,
 *    배열의 크기는 2^3*2=16이 된다. 시작 인덱스는 2^3=start_inde=8이 된다. 
 *    곱셈이기 때문에 초깃값을 1로 저장해 주고, 부모 노드를 양쪽 자식 노드의 곱으로 표현한다.
 *    이때 MOD연산을 지속적으로 수행해 값의 범위가 1,000,000,007이 넘지 않도록 구현한다.
 *    
 * 2. 질의값 연산 함수와 데이터 업데이트 함수를 수행하고 결괏값을 출력한다.
 *    이때 값을 업데이트하거나 구간 곱을 구하는 각 곱셈마다 모두 MOD연산을 수행한다. 
 *    
 * ** 곱셈과 관련된 % 연산의 성질
 *    : 두 값을 곱셈한 후 % 연산한 결과는 각각 % 연산한 값을 곱해 %로 나눈 것과 동일함
 * (A * B) % C = (A % C) * (B % C) % C
 * 
 * 
 * [ 슈도 코딩 ]
 * tree: 세그먼트 트리 배열
 * N: 수의 개수
 * M: 변경이 일어나느 개수
 * K: 구한 합을 구하는 개수
 * MOD: 1000000007
 * treeSize 구하기 -> Math.pow(2, 트리의 높이+1)
 * leftNodeStartIndex구하기 -> treeSize / 2 - 1
 * tree 초기화하기
 * tree 배열의 리프 노드 영역에 데이터 입력받기
 * setTree(트리의 크기)
 * for(M+K만큼 반복) {
 * 		a: 질의 유형, s: 시작 인덱스, e: 변경값 또는 종료 인덱스
 * 		a=1 -> chageVal(tree에서 시작 인덱스, e(변경값))			// 데이터 변경 함수
 * 		a=2 -> getMul(tree에서 시작 인덱스, tree에서 종료 인덱스) 		// 구간 곱 호출 및 출력하기
 * }
 * 
 * // 구간 곱을 구하는 함수 구현하기
 * getMul(시작 인덱스, 종료 인덱스) {
 * 		while(시작 인덱스와 종료 인덱스가 교차될 때까지) {
 * 			if(시작 인덱스 % 2 == 1) 해당 노드의 값을 구간 곱에 곱하기 % MOD
 * 			if(종료 인덱스 % 2 == 0) 해당 노드의 값을 구간 곱에 곱하기 % MOD
 * 
 * 			시작 인덱스 = 시작 인덱스 / 2
 * 			종료 인덱스 = 종료 인덱스 / 2
 * 		}
 * 		구간 곱 결과 리턴하기
 * }
 * 
 * 
 * // 값 변경 함수 구현하기
 * changeVal(시작 인덱스, 변경값) {
 * 		현재 index에 변경값 저장하기
 * 		while(시작 인덱스가 1보다 크다) {
 * 			시작 인덱스 = 시작 인덱스 / 2
 * 			
 * 			// 현재 노드의 양쪽 자식 노드를 찾아 곱하는 로직
 * 			시작 인덱스의 트리값 = (시작 인덱스*2)의 트리값 % MOD * (시작 인덱스*2+1)의 트리값 % MOD
 * 		}
 * }
 * 
 * // 초기 트리 생성 함수 구현하기
 * setTree(트리의 마지막 인덱스) {
 * 		while(인덱스가 루트가 아닐 때까지 반복하기) {
 * 			트리의 인덱스 / 2 부분(부모 노드)에 현재 index의 트리값 곱하기 % MOD
 * 			index 1개 감소
 * 		}
 * }
 * 
 * **/
public class 구간_곱_구하기 {

	static long[] tree;
	static int MOD;
	
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
		
		int treeSize = (int) Math.pow(2, treeHeight+1);
		int leftNodeStartIndex = treeSize / 2 + 1;
		MOD = 1000000007;			// 1000000007은 큰 소수임(Prime Number) -> num % 1000000007 = num (num !=1 && num != 1000000007) , 오버플로우 방지
		tree = new long[treeSize + 1];
		for(int i=0; i<tree.length; i++) {
			tree[i] = 1;
		}
		
		for(int i=leftNodeStartIndex+1; i<=leftNodeStartIndex+N; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}
		
		setTree(treeSize-1);
		
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			long a 	= Long.parseLong(st.nextToken());
			int s 	= Integer.parseInt(st.nextToken());
			long e	= Long.parseLong(st.nextToken());
		
			if(a == 1) {
				changeVal(leftNodeStartIndex+s, e);
			}else if(a == 2) {
				s = s + leftNodeStartIndex;
				e = e + leftNodeStartIndex;
				
				System.out.println(getMul(s, (int) e));
			}else {
				return;
			}
		}
		
		br.close();
	}

	private static long getMul(int s, int e) {
		long partMul = 1;
		while(s <= e) {
			if(s % 2 == 1) {
				partMul = partMul * tree[s] % MOD;
				s++;
			}
			if(e % 2 == 0) {
				partMul = partMul * tree[e] % MOD;
				e--;
			}
			s = s / 2;
			e = e / 2;
		}
		
		return partMul;
	}

	private static void changeVal(int index, long val) {
		tree[index] = val;
		while(index > 1) {		// 현재 노드의 양쪽 자식 노드를 찾아 곱하는 로직
			index = index / 2;	// 2로 나누면 무조건 부모 인덱스를 찾데 되어 있음
								// 부모*2, 부모*2+1은 무조건 자식 인덱스를 찾게 되어 있음 ->  index가 홀수, 짝수 관계 없이 올바른 위치의 노드를 찾음!
			tree[index] = tree[index*2] % MOD * tree[index*2+1] % MOD;
		}
	}

	private static void setTree(int i) {
		while(i != 1) {
			tree[i / 2] = tree[i / 2] * tree[i] % MOD;
			i--;
		}
	}

}
