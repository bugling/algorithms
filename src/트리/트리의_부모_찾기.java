package 트리;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 11725
 * 
 * [ 문제 ]
 * 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
 * 
 * [ 입력 ]
 * 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다.
 * 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.
 * 
 * ( 예제 입력 )
7
1 6
6 3
3 5
4 1
2 4
4 7
 *  
 * [ 출력 ]
 * 첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
 * 
 * ( 예제 출력 )
 * 4
 * 6
 * 1
 * 3
 * 1
 * 4
 * 
 * [ 설명 ]
 * 1. 인접 리스트로 트리 데이터를 구현한다.
 * 2. DFS 탐색을 수행한다. 수행할 때 부모 노드의 값을 정답 배열에 저장한다.
 * 3. 정답 배열의 2번 인덱스부터 값을 차례대로 출력한다.  
 *  
 * [ 슈도 코딩 ]
 * 
 * N: 노드 개수
 * tree: 트리 데이터 저장 인접 리스트
 * visited: 방문 기록 저장 배열
 * answer: 부모 노드 저장 정답 배열
 * for(N의 개수만큼 반복하기) {
 * 		tree 인접 리스트의 각 ArrayList 초기화화기
 * }
 * for(N - 1의 개수만큼 반복하기) {
 * 		tree 인접 리스트에 트리 데이터 저장하기
 * }
 * 
 * 1번 노드부터 DFS 실행하기
 * for(2 ~ N 반복하기) {
 * 		answer 배열 출력하기
 * }
 * 
 * DFS {
 * 		visited 배열에 현재 노드 방문 기록하기
 * 		if(현재 노드의 연결 노드 중 방문하지 않은 노드) {
 * 			부모 노드 저장하기
 * 			DFS 실행하기(재귀 함수 형태)
 * 		}
 * }
 * 
 * **/
public class 트리의_부모_찾기 {

	static int N;
	static boolean[] visited;
	static ArrayList<Integer> tree[];
	static int answer[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new boolean[N + 1];
		tree = new ArrayList[N + 1];
		answer = new int[N + 1];
		
		for(int i=0; i<tree.length; i++) {
			tree[i] = new ArrayList<>();
		}
		
		// 트리의 에지 개수 만큼 반복함	-> 에지 정보를 초기화하는 과정	-> 노드개수-1 만큼 반복
		for(int i=1; i<N; i++) {		
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			tree[n1].add(n2);			// 양방향이기 때문에 각 노드의 인접 리스트에 요소 추가
			tree[n2].add(n1);
		}
		
		DFS(1);		// 최 상단 부모부터 깊이 우선 탐색 시작
		
		// '1'은 부모를 가지지 않기 때문에 '2'부터 부모 노드를 출력!
		for(int i=2; i<=N; i++) {
			System.out.println(answer[i]);
		}
	}
	
	// DFS 순서
	// 부모 들어옴 -> 부모 방문 체크 -> 연결된 애들 하나씩 꺼냄 -> 부모는 이미 방문 체크 되어 있어서 if문에 걸러짐
	// -> 0번째 자식에 도착 -> 자기가 부모가 되어 DFS에 다시 들어감 -> 0번째 자식 드디어 방문 체크
	// -> 다시 자식 꺼냄 -> 만약 자식 없으면 로직 종료 -> (다시 이전에 실행 중인 for문 으로 돌아옴) 1번째 자식 시작 -> N번 째까지 반복 -> N번쨰 자식이 방문 체크하면 작은 트리 탐색 종료
	// -> 이런 식으로 모든 트리 내부 탐색 가능
	static void DFS(int number) {
		visited[number] = true;			// 부모 요소의 방문 체크
		for(int i : tree[number]) {		// 자식들을 꺼냄. 만약 자식이 없다면 해당 요소의 로직은 종료됨.
			if(visited[i] == false) {	// visited == true 인 경우: 부모 노드를 방문했을 경우
				answer[i] = number;		// 정답 배열 [i]에 부모 노드(number)을 넣는다. 
				DFS(i);					// 자식이 부모가 되어 다시 자식을 탐색
			}
		}
	}

}
