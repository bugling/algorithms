package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * [ 슈도 코딩 ]
 * 
 * suNo: 숫자 개수
 * quizNo: 질의 개수
 * 
 * for(숫자 개수만큼 반복하기)
 * {
 * 		합 배열 생성하기( S[i] = S[i-1] + A[i] )
 * }
 * 
 * for(질의 개수만큼 반복하기){
 * 		질의 범위 받기(i ~ j)
 * 		구간 합 출력하기( S[j] - S[i - 1] )
 * }
 * 
 * 
 * **/
public class 구간_합_구하기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bufferedReader = 
				new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stringTokenizer =
				new StringTokenizer(bufferedReader.readLine());
		
		int suNo = Integer.parseInt(stringTokenizer.nextToken());
		int quizNo = Integer.parseInt(stringTokenizer.nextToken());
		
		long[] S = new long[suNo + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());		// 배열을 입력받음
		
		for(int i=1; i<=suNo; i++) {
			S[i] =S[i-1] + Integer.parseInt(stringTokenizer.nextToken());
			System.out.print("  S[" + i + "] = " + S[i]);						//   S[1] = 5  S[2] = 9  S[3] = 12  S[4] = 14  S[5] = 15
		}
		System.out.println();
		
		for(int q=0; q<quizNo; q++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int i = Integer.parseInt(stringTokenizer.nextToken());
			int j = Integer.parseInt(stringTokenizer.nextToken());
			
			int result = (int) (S[j] - S[i-1]);		// 1~j까지의 구간 합 - i의 직전 구간 합
			
			System.out.println(result);
		}
		
		// 입력 / 답
		// 1 3 -> 12	// 2 3 -> 7 	 // 5 5 -> 1
	}

}
