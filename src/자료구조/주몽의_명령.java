package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 설명 ]
 * 
 * N: 재료의 개수
 * M: 값옷이 되는 번호
 * A: 재료가 들어갈 배열
 * 
 * N개의 수(재료)를 더해 M의 값을 가진 갑옷을 만드는 로직.
 * 
 * [ 슈도 코딩 ]
 *
 * N: 재료의 개수
 * M: 값옷이 되는 번호
 * 
 * for(N만큼 반복)
 * {
 * 		A에 재료 배열형태로 저장하기
 * }
 * 
 * 재료 배열 정렬하기
 * 
 * while(i < j)
 * {
 * 		if(재료의 합 < M) 		작은 번호 재료를 한 칸 위로 변경하기
 * 		else if(재료 합 > M) 	큰 번호 재료를 한 칸 아래로 변경하기
 * 		else 				경우의 수 증가, 양쪽 index 각각 변경하기
 * }
 * 
 * count 출력하기
 * 
 * **/
public class 주몽의_명령 { 

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int M = Integer.parseInt(bf.readLine());
		
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());		// 재료 저장
		}
		
		Arrays.sort(A);			// 재료들 오름차순으로 정렬
		int count = 0;
		int i = 0;
		int j = N - 1;
		
		while(i < j) {		// 투 포인터 이동 원칙에 따라 포인터를 이동하며 처리
			if(A[i] + A[j] < M) {		// 두 수의 합이 M보다 작다면
				i++;					// i값을 증가
			}else if(A[i] + A[j] > M){	// 두 수의 합이 M보다 크다면
				j--;					// j값 감소
			}else {
				count++;
				i++;
				j--;
			}
		}
		
		System.out.println(count);
		bf.close();
	}

}
