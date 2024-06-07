package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * [ 슈도 코딩 ]
 * N: 질의 요청 개수
 * 
 * 우선순위 큐 선언
 * - 절댓값 기준으로 정렬되도록 설정하기
 * - 단, 절댓값이 같으면 음수 우선 출력하기
 * 
 * for(N만큼 반복)
 * {
 * 		요청이 0일 때: 큐가 비어 있으면 0, 비어 있지 않으면 큐의 front값 출력하기(poll());
 * 		요청이 1일 때: 새로운 데이터를 우선순위 큐에 더하기(add()); 
 * }
 * 
 * **/
public class 절댓값_힙_구하기 {
 
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> MyQueue = new PriorityQueue<>((o1, o2) ->  {
			
			int first_abs = Math.abs(o1);		// 각 수의 절댓값 구하기
			int second_abs = Math.abs(o2);
			
			if(first_abs == second_abs) 		// 절댓값이 같다면
				return o1 > o2 ? 1 : -1;		// 첫 번째 값이 크면 1, 아니면 -1 리턴		// 음수 우선 정렬
			else								// 절댓값이 다르면
				return first_abs - second_abs;	// 첫번째 값 - 두번쨰 값					// 절댓값을 기준으로 정렬하기
		});
		
		for(int i=0; i<N; i++) {
			int request = Integer.parseInt(br.readLine());
			if(request == 0) {
				if(MyQueue.isEmpty()) 
					System.out.println("0");
				else 
					System.out.println(MyQueue.poll());
			}else {
				MyQueue.add(request);
			}
		}
	}

}
