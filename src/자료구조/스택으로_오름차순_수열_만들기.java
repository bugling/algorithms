package 자료구조;

import java.util.Scanner;
import java.util.Stack;


/**
 * 
 * [ ���� �ڵ� ]
 * 
 * N: ���� ����
 * A[]: ���� �迭
 * 
 * ���� �迭 ä���
 * for(N��ŭ �ݺ�)
 * {
 * 		if(���� ������ �� >= �������� �ڿ���) {
 * 			while(���� ������ ������)
 * 			{
 * 				push();
 * 				( + ) ����
 * 			}
 * 			pop();
 * 			( - ) ����
 * 		}
 * 		else {		// ���� ���� �� < �������� �ڿ���
 * 			pop();
 * 			if(���� pop ����� > ������ ��) NO ���
 * 			else	( - )����
 * 		}
 * }
 * 
 * if(-���� ����� ���� ������) ������ �� ���
 * 
 * **/
 

public class 스택으로_오름차순_수열_만들기 {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = sc.nextInt();
		}
		
		Stack<Integer> stack = new Stack<>();
		StringBuffer bf = new StringBuffer();
		boolean result = true;
		
		int num = 1;
		for(int i=0; i<N; i++) {
			
			int su = A[i];
			if(su >= num) {			// �Է¹��� �� >= stack�� ���� ���� �ִ� ��
				
				while(su >= num) {
					stack.push(num++);
					bf.append("+\n");
				}
				stack.pop();
				bf.append("-\n");
			}else {					// �Է¹��� �� < stack�� ���� ���� �ִ� ��
				
				int n = stack.pop();	// stack�� ���� ���� �ִ� ����		// ex) 3
				if(su > n) {					// �Է¹��� �� su = 4
					
					System.out.println("NO");
					result = false;
					break;
					
				}else {
					bf.append("-\n");
				}
				
			}
			
			
		}
		
		// �߰��� No������ ������ �ʾ��� ���� StringBuffer���� ���
		if(result) System.out.println(bf.toString());		
	}

}
