package 자료구조;

import java.util.Scanner;

public class 평균_구하기 { 

	public static void main(String[] args) {
		
//		Scanner sc = new Scanner(System.in);
//		
//		int cnt = sc.nextInt();
//		
//		int[] arr = new int[cnt];
//		
//		for(int i=0; i<cnt; i++) {
//			int score = sc.nextInt();
//			arr[i] = score;
//		}
//		
//		long maxScore = arr[0];
//		long sum = 0;
//		for(int i=0; i<cnt; i++) {
//			int j = i+1;
//			
//			if(j < cnt && maxScore <= arr[j]) {
//				maxScore = arr[j];
//			}
//			
//			sum += arr[i];
//		}
		
//		System.out.println(sum * 100.0 / maxScore / 3);
		
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long sum = 0;
		long max = 0;
		
		for(int i=0; i<N; i++) {
			int temp = sc.nextInt();
			if(temp > max) max = temp;
			sum += temp;
		}
		
		System.out.println(sum*100.0/max/N);
		
		
		
	}

}
