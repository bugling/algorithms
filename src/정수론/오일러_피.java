package 정수론;

/**
 * n: 소수인수 표현
 * result: 결괏값
 * 
 * for(2~n)
 * {
 * 		if(현재 값이 소인수라면)
 * 		{
 * 			결괏값 = 결괏값 - 결괏값 / 현재값
 * 			n에서 현재 소인수 내역을 제거하기(2^7 * 11 * 13 -> 현재 소인수가 2일 때 11*13으로 변경)
 * 		}
 * }
 * 
 * if(n > 1)		// n이 마지막 소인수일 때
 * {
 * 		결괏값 = 결괏값 - 결괏값 / n
 * }
 * 
 * 결괏값 출력하기
 * 
 * **/
public class 오일러_피 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
