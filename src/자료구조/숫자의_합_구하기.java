package 자료구조;

import java.util.Scanner;
 
public class 숫자의_합_구하기 { 

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		//int N = sc.nextInt();
		
		String sNum = sc.next();
		
		// sc���� �Է¹��� ���ڸ� char������ �迭�� ����
		char[] cNum = sNum.toCharArray();
		
		int sum = 0;
		for(int i=0; i<cNum.length; i++) {
			sum += cNum[i] - '0';
		}
		
		System.out.println(sum);
		
		
	}

}
