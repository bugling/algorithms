package 자료구조;

import java.util.Scanner;

/**
 * [ 슈도코딩 ]
 * N값 입력받기
 * 길이 N의 숫자를 입력받아 String형 변수 sNum에 저장하기		** 길이를 알 수 없으므로 일단 String에 담고 하나씩 char[]에 옮긴다.
 * sNum을 다시 char[]형 변수 cNum에 변환하여 저장하기
 * int형 변수 sum선언하기
 * 
 * for(cNum 길이만큼 반복하기)
 * {
 * 		배열의 각 자릿값을 정수형으로 변환하며 sum에 더하여 누적하기
 * }
 * 
 * **/

public class 숫자의_합_구하기 { 

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 입력값을 String형 변수 sNum에 저장한 후 char[]형 변수로 변환하기
		String sNum = sc.next();
		
		char[] cNum = sNum.toCharArray();
		
		int sum = 0;
		
		for(int i=0; i<cNum.length; i++) {
			sum += cNum[i] - '0';		// cNum[i]를 정수형으로 변환하여 sum에 더하여 누적하기
		}
		
		System.out.println(sum);
	}

}
