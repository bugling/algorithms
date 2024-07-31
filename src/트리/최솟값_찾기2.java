package 트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 문제 ]
 * N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때, a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수를 찾는 것은 어려운 일이 아니다.
 * 하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다. 이 문제를 해결해 보자.
 * 여기서 a번째라는 것은 입력되는 순서로 a번째라는 이야기이다.
 * 예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서 최솟값을 찾아야 한다.
 * 각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.
 * 
 * [ 입력 ]
 * 첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다.
 * 다음 M개의 줄에는 a, b의 쌍이 주어진다.
 * 
 * ( 예제 입력 )
10 4
75
30
100
38
50
51
52
20
81
5
1 10
3 5
6 9
8 10
 * 
 * [ 출력 ]
 * M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 출력한다.
 * 
 * ( 예제 출력 )
 * 5
 * 38
 * 20
 * 5
 * 
 * [ 설명 ]
 * 1. 1차원 배열로 트리의 값을 최솟값 기준으로 초기화합니다.
 *    트리 배열 크기가 N=10이므로 2^k >= N(10)을 만족하는 k의 값은 4이고, 배열 2^4 * 2 = 32가 된다.
 *    시작 인덱스는 2^4 = start_index = 16이 된다.
 * 
 * 2. 질의값 연산 함수를 수행하고, 결괏값을 춝력한다.
 * 
 * [ 슈도 코딩 ]
 * tree: 세그먼트 트리 배열
 * N: 수의 개수
 * M: 최솟값을 구하는 횟수
 * treeSize 구하기 -> Math.pow(2, 트리의 높이 + 1)
 * leftNodeStartIndex 구하기 -> treeSize / 2 - 1		// 리프 노드 시작 인덱스
 * 트리 초기화하기(모든 값을 Max값으로 초기화)
 * tree 배열의 리프 노드 영역에 데이터 입력받기
 * setTree(트리의 크기)									// 초기 트리를 생성하는 함수
 * 
 * for(M만큼 반복하기) {
 * 		getMin(tree에서 시작 인덱스, tree에서 종료 인덱스)		// 최솟값을 구하는 함수 호출 및 출력하기
 * }
 * 
 * // 범위의 최솟값을 구하는 함수
 * getMin(시작 인덱스, 종료 인덱스) {
 * 		Min: 법ㅁ위의 최솟값을 나타내는 변수, MAX_VALUE로 초기화
 * 		while(시작 인덱스와 종료 인덱스가 교차될 때까지) {
 * 			if(시작 인덱스 % 2 == 1) {
 * 				Min과 현재 인덱스의 트리값을 비교해 작은 값을 Min 변수에 저장
 * 				시작 인덱스 증가
 * 			}
 * 
 * 			if(종료 인덱스 % 2 == 0) {
 * 				Min과 현재 인덱스의 트리값을 비교해 작은 값을 Min 변수에 저장
 * 				종료 인덱스 감소
 * 			}
 * 
 * 			시작 인덱스 = 시작 인덱스 / 2
 * 			종료 인덱스 = 종료 인덱스 / 2
 * 		}
 * 		Min값 리턴하기
 * }
 * 
 * // 초기 트리 생성 함수 구현하기
 * setTree(트리가 마지막 인덱스) {
 * 		while(인덱스가 루트가 아닐 때까지 반복하기) {
 * 			트리의 인덱스 / 2 부분(부모 노드)의 값과 현재의 값을 비교해 현재의 값이 더 작을 때
 * 			해당 값을 트리의 인덱스 / 2 부분(부모 노드)에 저장하기
 * 			index 1개 감소
 * 		}	
 * }
 * 
 * **/
public class 최솟값_찾기2 {

	static long[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());		// 노드의 수  10
		int M = Integer.parseInt(st.nextToken());		// 질의의 수  4
		int treeHeight = 0;
		int length = N;		// 10
		while(length != 0) {
			length /= 2;	// 5 2 1 0
			treeHeight++;	// 4
		}
		int treeSize 			= (int) Math.pow(2, treeHeight+1);		// 2^(treeHeight+1)		2^5 = 32
		int leftNodeStartIndex 	= (treeSize / 2) - 1;	// 15
		
		// 트리 초기화하기
		tree = new long[treeSize + 1];		// new long[33]		**tree[0]은 사용하지 않음 1~32까지만 사용
		for(int i=0; i<tree.length; i++) {	// tree.length: 33		// 0~32까지 반복
			tree[i] = Integer.MAX_VALUE;
		}
		
		// 데이터 입력 받기
				// leftNodeStartIndex를 0으로 보는 게 좋을 듯
				// "1~N까지 셋팅한다"로 이해하자.
		for(int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex+N; i++) {
			
			tree[i] = Long.parseLong(br.readLine());
		}
		
		
		// "treeSize - 1 (31)"을 인자로 보내는 이유: tree[0]은 사용하지 않기 때문
		setTree(treeSize - 1);		// setTree(31)		** 트리 노드 개수는 32개!
		
		// 트리 구조
		// [2147483647, 5, 20, 5, 30, 20, 5, 2147483647, 30, 38, 50, 20, 5, 2147483647, 2147483647, 2147483647, 75, 30, 100, 38, 50, 51, 52, 20, 81, 5, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647]
		/**
		                             5[1] 
		                           /      \
		                          /        \ 
		                         /          \ 
		                        /            \ 
		                       /              \ 
		                      /                \ 
		                     /                  \ 
		                    /                    \ 
		                   /                      \ 
		                  /                        \ 
		                 /                          \ 
		                /                            \ 
		               /                              \ 
		              20[2]                           5[3] 
		             /  \                             /  \
			        /    \                           /    \
		           /      \	                        /      \
		          /        \                       /        \
		         /          \					  /          \
		        /            \                   /            \
		       /              \				    /              \
		      /                \		       /                \   
		     30[4]            20[5]            5[6]             ■[7] 
		    /   \            /   \           /   \            /   \
		   /     \          /     \         /     \          /     \
		 30[8]    38[9]    50[10]  20[11]  5[12]   ■[13]     ■[14]  ■[15]
		 / \      / \     / \     / \     / \     / \       / \    / \
		75 30   100 38   50 51   52 20   81 5    ■   ■     ■   ■  ■   ■
		[16][17][18][19][20][21] [22][23][24][25][26][27][28] [29][30][31] 

		 * **/
		
		for(int i=0;i <M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			s = s + leftNodeStartIndex;		// s: 리프 노드 상에서의 인덱스		// s+leftNodeStartIndex: 전체 트리에서의 인덱스
			e = e + leftNodeStartIndex;
			
			System.out.println(getMine(s, e));
		}
		
		br.close();
		
	}

	// 범위 내에서 최소값을 찾는 메서드
	private static long getMine(int s, int e) {
		long Min = Long.MAX_VALUE;
		
		// "s < e"가 아니라 "s <= e"인 이유: 
			// 길이가 1인 배열도 체크하기 위해서임. 사이즈가 1인 애가 getMin을 탔는데 long Min = Long.MAX_VALUE; 만 수행되고 에러남ㅋㅋ
			// s++, e--가 수행되어서 범위를 벚어나도 어짜피 s나 e 중에 하나는 부모노드까지 올라가기 때문에 최소값을 구하게 됨. 
		while(s <= e) {			 
			if(s % 2 == 1) {		// 부모 노드를 볼 필요 없는 노드(범위 안에 부모노드가 포함되지 않음)
				Min = Math.min(Min, tree[s]);
				s++;	// 앞으로 이동
			}
			s = s / 2;			// 부모 레벨로 이동			// setTree에서 이미 부모노드에 자식노드의 최소값이 셋팅되어 있기 때문에 부모 노드 레벨로 바로 이동
			if(e % 2 == 0) {		// 부모 노드를 볼 필요 없는 노드(범위 안에 부모노드가 포함되지 않음)
				Min = Math.min(Min, tree[e]);
				e--;	// 뒤로 이동
			}
			e = e / 2;
		}
		return Min;
	}

	// 트리를 셋팅하는 메서드	// 최초 i: 31
	private static void setTree(int i) {		// i: 트리 최후반에서 앞으로 전진하는 인덱스
		while(i != 1) {
			if(tree[i / 2] > tree[i]) {			// 부모노드와 자신을 비교해서 작은 값을 부모 노드에 셋팅
				tree[i/2] = tree[i];
			}
			i--;								// 트리의 뒤에서 앞으로 이동
		}
	}

}
