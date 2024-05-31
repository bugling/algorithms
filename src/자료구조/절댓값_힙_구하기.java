package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 절댓값_힙_구하기 {
 
	
	/**
	 * 
	 * [ ���� ]
	 * ����� �Է� ������ �ڷᱸ���� �����
	 * �Է°� �� 0�� ������ ����� �� �� ���밪�� ���� ���� ���� ���
	 * ���밪�� ���� ���� 2�� �̻� ������ ���� ��(����)�� ���
	 * ���� �ڷᱸ���� �ƹ� �͵� ���µ� 0�� ������ 0�� ��� 
	 * 
	 * 
	 * [���� �ڵ�]
	 * 
	 * N: ���� ��û ����
	 * 
	 * �켱���� ť ����
	 * - ���밪 �������� ���ĵǵ��� �����ϱ�
	 * - ��, ���밪�� ������ ���� �켱 �����ϱ�
	 * 
	 * for(N��ŭ �ݺ�)
	 * {
	 * 		��û�� 0�� ��: ť�� ��� ������ 0, ��� ���� ������ ť�� front�� ����ϱ�(poll())
	 * 		��û�� 1�� ��: ���ο� �����͸� �켱���� ť�� ���ϱ�(add())
	 * }
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * 
	 * **/
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> myQueue = new PriorityQueue<>((o1, o2) -> {
			
			int first_abs 	= Math.abs(o1);
			int second_abs	= Math.abs(o2);
			
			// ���밪�� ���ٸ� 
			if(first_abs == second_abs) {
				
				// o1�� ũ�ٸ� 1�� �����ϰ�, �ƴ϶�� -1�� �����Ѵ�.
				return o1 > o2 ? 1 : -1;
			}
			
			// ���밪�� ���� ������ �켱		
				// first�� ũ��: ��� ����
				// second�� ũ��: ���� ����
			return first_abs - second_abs;		
		});
		
		
		for(int i=0; i<N; i++) {
			int request = Integer.parseInt(br.readLine());
			if(request == 0) {
				if(myQueue.isEmpty()) {
					System.out.println("0");
				}else {
					System.out.println(myQueue.poll());
				}
			}else {
				myQueue.add(request);
			}
		}
	}

}
