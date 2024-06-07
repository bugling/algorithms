package 자료구조;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 카드_게임 {

	/**
	 * 1~N까지의 카드가 오름차순으로 쌓여있다.
	 * 가장 위의 카드를 버리고 그 위에 위치한 카드를 가장 아래로 이동한다.
	 * 마지막에 남는 카드는 무엇일까?
	 * 
	 * [ 슈도 코딩 ]
	 * N: 카드의 개수
	 * myQueue: 카드 저장 자료구조
	 * 
	 * for(카드의 개수만큼 반복)
	 * {
	 * 		큐에 카드 저장하기
	 * }
	 * 
	 * while(카드가 1장 남을 때까지)
	 * {
	 * 		맨 위의 카드를 버림: poll();
	 * 		맨 위의 카드를 가장 아래의 카드 밑으로 이동: poll() -> add();
	 * }
	 * 
	 * 마지막에 남은 카드 출력
	 * 
	 * **/
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Queue<Integer> myQueue = new LinkedList<>();
		int N = sc.nextInt();		// 카드의 개수 저장
		
		// 큐에 카드 저장하기
		for(int i=1; i<=N; i++) {
			myQueue.add(i);
		}
		
		while(myQueue.size() > 1) {
			myQueue.poll();
			myQueue.add(myQueue.poll());
		}
		
		System.out.println(myQueue.poll());
	}

}
