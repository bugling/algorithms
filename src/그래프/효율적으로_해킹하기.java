package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 백준 1325
 * 
 * [ 문제 ]
 * 해커 김지민은 잘 알려진 어느 회사를 해킹하려고 한다.
 * 이 회사는 N개의 컴퓨터로 이루어져 있다.
 * 김지민은 귀찮기 때문에, 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려고 한다.
 * 이 회사의 컴퓨터는 신뢰하는 관계와, 신뢰하지 않는 관계로 이루어져 있는데,
 * A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다는 소리다.
 * 이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때,
 * 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성하시오.
 * 
 * [ 입력 ]
 * 첫째 줄에, N과 M이 들어온다.
 * N은 10,000보다 작거나 같은 자연수, M은 100,000보다 작거나 같은 자연수이다.
 * 둘째 줄부터 M개의 줄에 신뢰하는 관계가 A B와 같은 형식으로 들어오며, "A가 B를 신뢰한다"를 의미한다.
 * 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.
 * 
 * (예제 입력)
5 4
3 1
3 2
4 3
5 3
 * 
 * [ 출력 ]
 * 첫째 줄에, 김지민이 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력한다.
 * 
 * (예제 출력)
 * 1 2
 * 
 * [ 그래프 ]
 * 1
 * 2
 * 3 -> 1, 2
 * 4 -> 3
 * 5 -> 3
 * 
 * [ 설명 ]
 * 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터는 신뢰를 가장 많이 받는 컴퓨터.
 * 그래프의 노드와 에지를 기준으로 이해하면 A라는 노드에서 탐색 알고리즘으로 방문하는 노드가 B, C라고 하면 B, C는 A에게 신뢰받는 노드가 된다.
 * 
 * 1. 인접 리스트로 컴퓨터와 신뢰 관계 데이터의 그래프를 표현한다.
 * 2. 모든 노드로 각각 BFS탐색 알고리즘을 적용해 탐색을 수행한다.
 *    탐색을 수행하면서 탐색되는 노드들의 신뢰도를 증가시켜 준다.
 * 3. 탐색 종료 후 신뢰도 배열을 탐색해 신뢰도의 최댓값을 Max값으로 지정하고,
 *    신뢰도 배열을 다시 탐색하면서 Max값을 지니고 있는 노드를 오름차순 출력한다.
 *    
 * 
 * [ 슈도 코딩 ]
 * N: 노드 개수 	M: 에지 개수
 * answer: 정답 배열
 * visited: 방문 유무 저장 배열
 * 
 * for(N의 개수만큼 반복하기)
 * {
 * 		A 인접 리스트의 각 ArrayList 초기화하기
 * }
 * 
 * for(M의 개수만큼 반복하기)
 * {
 * 		A 인접 리스트에 그래프 데이터 저장하기
 * }
 * 
 * for(i -> N의 개수만큼 반복하기)
 * {
 * 		visited 배열 초기화하기
 * 		BFS(i) 실행하기			// 모든 노드로 BFS 실행하기
 * }
 * 
 * for(N의 개수만큼 반복하기)
 * {
 * 		answer 배열에서 가장 큰 수 찾기 -> maxVal
 * }
 * 
 * for(N의 개수만큼 반복하기)
 * {
 * 		answer 배열에서 maxVal와 같은 값을 가진 index를 정답으로 출력하기
 * }
 * 
 * // BFS 구현하기
 * BFS {
 * 		큐 자료구조에 출발 노드 더하기(add 연산)
 * 		visited 배열에 현재 노드 방문 기록하기
 * 		whlie(큐가 빌 때 까지)
 * 		{
 * 			큐에서 노드 데이터를 가져오기(poll 연산)
 * 			현재 노드의 연결 노드 중 방문하지 않은 노드로
 * 			큐에 데이터 삽입(add 연산)하고 visited 배열에 방문 기록하기,
 * 			신규 노드 인덱스의 정답 배열 값을 증가시키기
 * 		}
 * }
 *    
 * **/
public class 효율적으로_해킹하기 {

	static int N, M;
	static int[] answer;
	static ArrayList<Integer> A[];		// ArrayList<Integer>를 받는 배열
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new ArrayList[N + 1];
		answer = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			A[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			A[S].add(E);
		}
		
		for(int i=1; i<=N; i++) {
			visited = new boolean[N+1];
			BFS(i);
		}
		
		int maxVal = 0;
		for(int i=1; i<=N; i++) {
			maxVal= Math.max(maxVal, answer[i]);		// 두 인자 중 큰 수를 maxVal에 담는다.
		}
		
		for(int i=1; i<=N; i++) {
			if(answer[i] == maxVal)
				System.out.print(i + "  ");
		}
	}
	
	
	private static void BFS(int index) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(index);
		visited[index] = true;
		
		while(!queue.isEmpty()) {
			int now_Node = queue.poll();
			for(int i : A[now_Node]) {
				if(visited[i] == false) {
					visited[i] = true;
					answer[i]++;			// 신규 노드 인덱스의 정답 배열 값을 증가시킴
					queue.add(i);
				}
			}
		}
		
	}

}
