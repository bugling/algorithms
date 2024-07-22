package 그래프;

import java.util.Scanner;

/**
 * 백준 1717
 * 
 * [ 문제 ]
 * 초기에 n+1개의 집합 
 * {0}, {1}, {2}, ... , {n}이 있다. 여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.
 * 집합을 표현하는 프로그램을 작성하시오.
 * 
 * [ 입력 ]
 * 첫째 줄에 n, m이 주어진다. 
 * m은 입력으로 주어지는 연산의 개수이다.
 * 다음 m개의 줄에는 각각의 연산이 주어진다.
 * 합집합은 0 a b의 형태로 입력이 주어진다. 이는 a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합친다는 의미이다.
 * 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b의 형태로 입력이 주어진다.
 * 이는 a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산이다.
 * 
 * ( 예제 입력 )
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
 * 
 * [ 출력 ]
 * 1로 시작하는 입력에 대해서 a와 b가 같은 집합에 포함되어 있으면 "YES" 또는 "yes"를, 그렇지 않다면 "NO" 또는 "no"를 한 줄에 하나씩 출력한다.
 * 
 * ( 예제 출력 )
 * NO
 * NO
 * YES
 * 
 * [ 슈도 코딩 ]
 * 
 * N: 원소 개수
 * M: 질의 개수
 * parent: 대표 노드 저장 배열
 * 
 * for(N만큼 반복하기)
 * {
 * 		대표 노드를 자기 자신으로 초기화하기
 * }
 * 
 * for(M만큼 반복하기)
 * {
 * 		if(0이면) 집합 합치기 -> union연산
 * 		else와 같은 집합 원소인지 확인하고 결괏값 출력하기
 * }
 * 
 * union(a, b) {
 * 		a와 b의 대표 노드 찾기
 * 		두 원소의 대표 노드끼리 연결하기
 * }
 * 
 * find(a) {
 * 		a가 대표 노드면 리턴
 * 		아니면 a의 대표 노드값을 find(parent[a]) 값으로 저장 -> 재귀 함수 형태
 * }
 * 
 * // 두 원소가 같은 집합인지 확인
 * checkSame(a, b) {
 * 		a와 b의 대표 노드 찾기
 * 			두 대표 노드가 같으면 true
 * 			아니면 false return
 * }
 * 
 * **/
public class 집합_표현하기 {

	public static int[] parent;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		parent = new int[N+1];
		
		// 대표노드 초기화
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}
		
		// 질의 계산 부분
		for(int i=0; i<M; i++) {
			int question = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if(question == 0) {
				union(a, b);
			}else {
				
				boolean result = checkSame(a, b);
				if(result) {
					System.out.println("Yes");
				}else {
					System.out.println("No");
				}
			}
		}
		
	}
	
	// 대표 노드끼리 연결하기
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b) {
			parent[b] = a;
		}
	}
	
	// 대표노드 찾기
	public static int find(int n) {
		if(n == parent[n]) {
			return n;
			
		}else {
								// value를 index로 바꿔서 또 찾아보기
			return parent[n] = find(parent[n]);		// 결룩 재귀 호출을 여러번 반복하다가 n == parent[n] 조건에 걸리게 되어 "return n;"이 parent[n]에 들어가게 됨.  
		}
	}
	
	// 두 원소가 같은 집합인지 확인하기
	public static boolean checkSame(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) {
			return true;
		}else {
			return false;
		}
	}
	
}
