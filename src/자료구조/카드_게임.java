package 자료구조;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 카드_게임 {

	 
	/**
	 * 
	 * [ ���� ]
	 * 
	 * 1~N������ ī�尡 ������������ �׿��ִ�.
	 * ���� ���� ī��� ������, �� ���� ī��� ���� �Ʒ��� �ű��.
	 * �� ������ ������ 1�� ���� ������ �ݺ��Ѵ�.
	 * �������� ���� ī���� ���ڴ� �����ΰ�?
	 * 
	 * 
	 * [ �����ڵ� ]
	 * 
	 * N: ī���� ����
	 * myQueue: ī�� ���� �ڷᱸ��
	 * 
	 * for(ī���� ������ŭ �ݺ�)
	 * {
	 * 		ť�� ī�� �����ϱ�
	 * }
	 * 
	 * while(ī�尡 1�� ���� ������)
	 * {
	 * 		�� ���� ī�� ����: poll();
	 * 		�� ���� ī�� ���� �Ʒ��� ī�� ������ �̵�: poll() -> add();
	 * }
	 * 
	 * ���������� ���� ī�� ���
	 * 
	 * **/
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		Queue<Integer> myQueue = new LinkedList<>();
		int N = sc.nextInt();
		
		for(int i=1; i<=N; i++) {
			myQueue.add(i);
		}
		
		while(myQueue.size() > 1) {
			myQueue.poll();
			int temp = myQueue.poll();
			myQueue.add(temp);
		}
		
		System.out.println(myQueue.poll());	
	}

}
