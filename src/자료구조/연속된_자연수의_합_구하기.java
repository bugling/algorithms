package 자료구조;

import java.util.Scanner;

public class 연속된_자연수의_합_구하기 {

	public static void main(String[] args) {
		 
		/**
		 * ���� �ڵ�
		 * 
		 * count = 1;	// �ڱ��ڽ� �ϳ��� �̷���� ����� ���� �̸� ����
		 * start_index = 1;
		 * end_index = 1;
		 * sum = 1;
		 * 
		 * if(sum == N) count ����, sum �� ����
		 * else if(sum > N) sum �� ����, start_index ����
		 * else if(sum < N) end_index ����, sum �� ����
		 * 
		 * **/
	
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int count = 1;
		int start_index = 1, end_index = 1, sum = 1;
		
		
		while(N != end_index) {
			
			// 15 = 1 + 2 + 3 + 4 + 5
			if(N == sum) {
				count++;
				end_index++;
				sum = sum + end_index;
			}
			// 15 > 1 + 2 + 3
			else if(N > sum) {		
				end_index++;
				sum = sum + end_index;
			}
			// 15 < 1 + 2 + 3 + 4 + 5 + 6
			else {
				sum = sum - start_index;
				start_index++;
			}
		}
		
		System.out.println(count);
		
	}

}
