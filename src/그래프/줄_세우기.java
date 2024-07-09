package 그래프;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * [ 문제 ]
 * N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면 간단하겠지만,
 * 마땅한 방법이 없어서 두 학생의 키를 비교하는 방법을 사용하기로 하였다.
 * 그나마도 모든 학생들을 다 비교해 본 것이 아니고, 일부 학생들의 키만을 비교해 보았다.
 * 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.
 * 
 * [ 입력 ]
 * 첫째 줄에 N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000)이 주어진다. M은 키를 비교한 회수이다.
 * 다음 M개의 줄에는 키를 비교한 두 학생의 번호 A, B가 주어진다.
 * 이는 학생 A가 학생 B의 앞에 서야 한다는 의미이다. 학생들의 번호는 1번부터 N번이다.
 * 
 * ( 예제 입력 )
3 2
1 3
2 3
 * 
 * [ 출력 ]
 * 첫째 줄에 학생들을 앞에서부터 줄을 세운 결과를 출력한다.
 * 답이 여러 가지인 경우에는 아무거나 출력한다.
 * 
 * ( 예제 출력 )
 * 1 2 3
 * 
 * [ 위상 정렬 수행 과정 ]
 * 1. 진입 차수가 0인 노드를 큐에 저장한다.
 * 2. 큐에서 데이터를 poll해 해당 노드를 탐색 결과에 추가하고, 해당 노드가 가리키는 노드의 진입 차수를 1씩 감소한다.
 * 3. 감소했을 때 진입 차수가 0이 되는 노드를 큐에 offer한다.
 * 4. 큐가 빌 때까지 1~3을 반복한다.
 * 
 * 
 * [ 슈도 코딩 ]
 * N: 학생 수 / M: 비교 횟수
 * A: 데이터 저장 인접 리스트
 * 학생 수만큼 인접 리스트 초기화하기
 * 진입 차수 배열 초기화하기
 * 
 * for(비교 횟수만큼 반복하기)
 * {
 * 		인접 리스트 데이터 저장하기
 * 		진입 차수 배열 초기 데이터 저장하기
 * }
 * 
 * // 위상 정렬 수행하기
 * 큐 생성하기
 * for(학생 수) {
 * 		진입 차수 배열의 값이 0인 학생(노드)을 큐에 삽입하기
 * }
 * 
 * while(큐가 빌 때까지) {
 * 		현재 노드 = 큐에서 데이터 poll
 * 		현재 노드값 출력하기
 * 		for(현재 노드에서 갈 수 있는 노드의 개수) {
 * 			타깃 노드 집입 차수 배열 --
 * 			if(타깃 노드의 진입 차수가 0이면) {
 * 				큐에 타깃 노드 추가하기
 * 			}
 * 		}
 * }
 * 
 * **/
public class 줄_세우기 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();		// 학생 수
		int M = sc.nextInt();		// 비교 횟수
		ArrayList<ArrayList<Integer>> A = new ArrayList<>();
		

		for(int i=0; i<=N; i++) {
			A.add(new ArrayList<>());
		}
		
		int[] indegree = new int[N + 1];		// 진입 차수 배열
		
		for(int i=0; i<M; i++) {
			int S = sc.nextInt();
			int E = sc.nextInt();
			
			A.get(S).add(E);
			indegree[E]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1; i<=N; i++) {			// queue 최초 셋팅: 진입차수가 없는 요소를 미리 queue에 넣는다.
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			System.out.print(now + " ");
			
			for(int next : A.get(now)) {	// 연결된 요소에 접근한다.
				indegree[next]--;			// 진입 차수를 -(minus) 해준다
				if(indegree[next] == 0 ) {	// 진입차수가 0인 건만 넣기 때문에 중복해서 들어가지 않는다.		// while문을 들어오기 전에 queue에 들어간 요소가 next로 나오는 경우는 indegree가 음수로 업데이트되기 때문.
					queue.offer(next);
				}
			} 
		}
	}

}
