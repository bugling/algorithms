package 그래프;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 2251 (골드V)
 * 
 * [ 문제 ]
 * 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다.
 * 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다.
 * 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데,
 * 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다.
 * 이 과정에서 손실되는 물은 없다고 가정한다.
 * 이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다.
 * 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.
 * 
 * 
 * [ 입력 ]
 * 첫째 줄에 세 정수 A, B, C가 주어진다.
 * ( 예제 입력 )
 * 8 9 10
 * 
 * [ 출력 ]
 * 첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
 * ( 예제 출력 )
 * 1 2 8 9 10
 * 
 * [ 설명 ]
 * 1. 처음에는 물통 A, B는 비어있고, C는 꽉 차 있으므로 최초 출발 노드를 (0, 0, 3번째 물통의 용량: 0, 0, 10)으로 초기화한다.
 * 2. BFS를 수행한다.
 * 		1) 노드에서 갈 수 있는 6개의 경우(A->B, A->C, B->A, B->C, C->A, C->B)에 관해 다음 노드로 정해 큐에 추가한다.
 * 		   A, B, C 무게가 동일한 노드에 방문한 이력이 있을 때는 큐에 추가하지 않는다.
 *		2) 보내는 물통의 모든 용량을 받는 물통에 저장하고, 보내는 물통에에는 0을 저장한다. 단, 받는 물통이 넘칠 때는 초과하는 값만큼 보내는 물통에 남긴다.
 *		3) 큐에 추가하는 시점에 1번째 물통(A)의 무게가 0일 때가 있으며, 3번째 물통(C)의 값을 정답 배열에 추가한다.
 * 3. 정답 리스트를 오름차순 출력한다.
 * 
 * 
 * [ 슈도 코딩 ]
 * Sender, Receiver: 6가지 경우를 탐색하기 위한 선언 배열
 * answer: 정답 배열
 * now: A, B, C의 값을 저장하는 배열
 * 
 * now 배열 저장하기
 * visited, answer 초기화 작업하기
 * 
 * BFS 수행하기
 * 
 * for(answer 배열 탐색하기) {
 * 		answer 배열에서 값이 true인 index를 정답으로 출력하기
 * }
 * 
 * BFS {
 * 		큐 자료구조에 출발 노드 더하기 -> A와 B가 0인 상태이므로 0, 0 노드에에서 시작하기
 * 		visited 배열에 현재 노드 방문 기록하기
 * 		answer 배열에 현재 C의 값 체크하기
 * 		while(큐가 빌 때 까지) {
 * 			큐에서 노드 데이터를 가져오기(poll 연산)
 * 			데이터를 이용해 A, B, C의 값 초기화하기
 * 			
 * 			for(6가지 케이스 반복하기) {			// A->B, A->C, B->A, B->C, C->A, C->B
 * 				받는 물통에 보내려는 물통의 값을 더하기
 * 				보내려는 물통 값을 0으로 업데이트하기
 * 
 * 				if(받는 물통이 넘칠 때) {
 * 					넘치는 만큼 보내는 물통에 다시 넣어 주고, 받는 물통은 이 물통의 최댓값으로 지정
 * 				}
 * 				현재 노드의 연결 노드 중 방문하지 않는 노드로
 * 				큐에 데이터 삽입(add 연산)하고 visited 배열에 방문 기록하기,
 * 				if(1번째 물통이 비어 있을 때) 3번째 물통의 물의 먕을 answer 배열에 기록하기
 * 			} 
 * 		}
 * }
 * 
 * // A, B 클래스 선언 -> A와 B의 값만 지니고 있으면 C는 유추할 수 있으므로 두 변수만 사용 
 * class AB {
 * 		A, B 물통 무게를 변수로 가짐
 * }
 * **/
public class 물의_양_구하기 {

	static int[] Sender 	= { 0, 0, 1, 1, 2, 2 };
	static int[] Receiver 	= { 1, 2, 0, 2, 0, 1 };
	static boolean visited[][];		// A, B의 무게만 있으면 C의 무게가 고정되므로 2개만 체크
	static boolean answer[];
	static int now[];
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		now = new int[3];
		now[0] 	= scan.nextInt();
		now[1] 	= scan.nextInt();
		now[2] 	= scan.nextInt();
		visited = new boolean[201][201];
		answer 	= new boolean[201];
		BFS();
	}
	
	private static void BFS() {
		Queue<AB> queue = new LinkedList<>();
		queue.add( new AB( 0, 0 ) );
		visited[0][0] 	= true;
		answer[now[2]] 	= true;
		
		while(!queue.isEmpty()) {
			AB p = queue.poll();
			int A = p.A;
			int B = p.B;
			int C = now[2] - A - B;
			
			for(int k=0; k<6; k++) {			// A->B, A->C, B->A, B->C, C->A, C->B
				int[] next = { A, B, C };
				next[Receiver[k]] += next[Sender[k]];
			}
		}
		
	}
	
}

class AB {
	int A;
	int B;
	public AB(int A, int B) {
		this.A = A;
		this.B = B;
	}
}
