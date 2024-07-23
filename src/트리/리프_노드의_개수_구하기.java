package 트리;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * 백준 1068
 * 
 * [ 문제 ]
 * 트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.
 * 트리가 주어졌을 때, 노드 하나를 지울 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오.
 * 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.
 *
 * [ 입력 ]
 * 첫째 줄에 트리의 노드의 개수 N이 주어진다.
 * N은 50보다 작거나 같은 자연수이다.
 * 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다.
 * 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다.
 *
 * ( 예제 입력 )
5
-1 0 0 1 1
2
 *
 * [ 출력 ]
 * 첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.
 *
 * ( 예제 출력 )
 * 2
 * 
 * [ 설명 ]
 * 이 문제의 핵심은 '리프 노드를 어떻게 제거하는가?'이다.
 * 리프 노드를 탐색하는 탐색 알고리즘을 수행할 때나 제거하는 노드가 나왔을 때 탐색을 종료하는 아이디어를 적용하면 실제 리프 노드를 제거하는 효과를 낼 수 있다.
 * 이러한 아이디어를 접목해 문제에 접근해 보겠다.
 * 
 * 1. 인접 리스트로 트리 데이터를 구현한다.
 * 2. DFS또는 BFS 탐색을 수행하면서 노드의 개수를 센다.
 *    단 제거 대상 노드를 만났을 때는 그 아래 자식 노드들과 관련된 탐색은 중지한다.
 *    이는 제거한 노드의 범위에서 리프 노드를 제거하는 효과가 있다. 
 * 
 * [ 슈도 코딩 ]
 * N: 노드 개수
 * tree: 트리 데이터 저장 인접 리스트
 * visited: 방문 기록 저장 배열
 * answer: 리프 노드 개수 저장 변수
 * deleteNode: 삭제 노드
 * 
 * for(N의 개수만큼 반복하기) {
 * 		tree 인접 리스트의 각 ArrayList 초기화하기
 * }
 * 
 * for(N의 개수만큼 반복하기) {
 * 		if(루트 노드가 아닌 경우) 
 * 			tree인접 리스트에 트리 데이터 저장하기
 * 		else 
 * 			루트 노드값 저장하기
 * }
 * 
 * 삭제 노드값 저장하기
 * if(삭제 노드값이 0) 모두 삭제되므로 0을 출력하고 프로세스 끝냄
 * else DFS(root)		// 루트 노드부터 DFS 실행하기
 * 		answer 출력하기
 * 
 * DFS {
 * 		방문 배열에 현재 노드 방문 기록하기
 * 		for(연결 노드 탐색하기) {
 * 			if(현재 노드의 연결 노드 중 방문하지 않은 노드이고 삭제 노드가 아닐 때)
 * 				자식 노드 개수 증가
 * 			
 * 			DFS 실행하기(재귀 함수 형태)
 * 		}
 * 
 * 		만약 자식 노드 개수가 0이면 answer 변수 증가
 * }
 * 
 * **/
public class 리프_노드의_개수_구하기 {
	
	static ArrayList<Integer>[] tree;
	static boolean[] visited;
	static int answer = 0;
	static int deleteNode = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		tree = new ArrayList[N];
		visited = new boolean[N];
		int root = 0;
		
		for(int i=0; i<N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<N; i++) {
			int p = sc.nextInt();
			if(p != -1) {		// 부모가 존재한다면
				tree[i].add(p);
				tree[p].add(i);
			}else {				// 부모가 존재하지 않는다면
				root = i;		// 루트로 지정
			}
		}
		
		deleteNode = sc.nextInt();
		
		if(deleteNode == root)
			System.out.println(0);
		else {
			DFS(root);
			System.out.println(answer);
		}
	}

	private static void DFS(int number) {
		visited[number] = true;
		int cNode = 0;
		for(int i : tree[number]) {
			
			// 방문하지 않은 노드			-> 인접 리스트에서 부모노드는 방문하지 않음 
			// 	&& 삭제 노드가 아닌 건	-> 삭제된 건을 탐색하지 않으므로써 그 자식노드들까지 탐색하지 않게 되고, 삭제된 건 이하의 노드들은 삭제처리 된 것과 동일한 효과를 보임
			if(visited[i] == false && i != deleteNode) {		
				cNode++;
				DFS(i);
			} 
		}
		
		// for문을 돌지 않고 그대로 해당 if문으로 오는 오는 경우만 조건에 들어가게 됨.
		if(cNode == 0) {	// 리프 노드인 경우
			answer++;
		}
	}

}
