package 탐욕;

import java.util.Scanner;

/**
 * 백준 1541		// https://www.acmicpc.net/problem/1541
 * 
 * [ 문제 ]
 * 세준이는 양수와 +, -, 그리고 괄호를 가지고 식을 만들었다. 그리고 나서 세준이는 괄호를 모두 지웠다.
 * 그리고 나서 세준이는 괄호를 적절히 쳐서 이 식의 값을 최소로 만들려고 한다.
 * 괄호를 적절히 쳐서 이 식의 값을 최소로 만드는 프로그램을 작성하시오.
 * 
 * [ 입력 ]
 * 첫째 줄에 식이 주어진다. 식은 ‘0’~‘9’, ‘+’, 그리고 ‘-’만으로 이루어져 있고, 가장 처음과 마지막 문자는 숫자이다.
 * 그리고 연속해서 두 개 이상의 연산자가 나타나지 않고, 5자리보다 많이 연속되는 숫자는 없다.
 * 수는 0으로 시작할 수 있다. 입력으로 주어지는 식의 길이는 50보다 작거나 같다.
 * 
 * 1) 55-50+40
 * 2) 10+20+30+40
 * 
 * [ 출력 ]
 * 1) -35
 * 2) 100
 * 
 * [ 설명 ]
 * 가장 작은 최솟값을 만들기 위해서는 가능한 한 큰 수를 뺴야 한다.
 * 수식 이 더하기와 빼기 연산만으로 이뤄져 있기 때문에 더하기에 해당하는 부분에 괄호를 쳐서 먼저 모두 계산한 후 빼기를 실행하면 문제가 해결된다.
 * 1. 가장 먼저 더하기 연산을 실행한다.
 * 2. 가장 앞에 있는 값에서 더하기 연산으로 나온 결괏값들을 모두 뺍니다.
 * 
 * [ 슈도 코딩 ]
 * answer: 정답 변수
 * 들어온 데이터를 "-" 기로를 기준으로 split() 수행하기
 * for(나눠진 데이터 개수만큼 반복하기) {
 * 		결괏값 = mySum() 함수 실행하기
 * 		if(가장 앞 데이터일 때) answer에 결괏값 더하기
 * 		else answer에 결괏값 빼기
 * }
 * 
 * anser 출력하기
 * 
 * mySum() {
 * 		현재 들어온 String값을 "+" 기호 기준으로 split() 수행하기
 * 		for(나눠진 데이터 개수만큼 반복하기){
 * 			String 값을 Integer형으로 변환해 리턴값에 더하기
 * 		}
 * 
 * 		전체 합 리턴하기
 * }
 * 
 * 
 * **/
public class 최솟값을_만드는_괄호_배치_찾기 {

	static int answer = 0;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String example = sc.nextLine();
		String[] str = example.split("-");
		
		for(int i=0; i<str.length; i++) {
			int temp = mySum(str[i]);
			
			if(i == 0) 						// 처음값은 빼기 전 기준이 되는 값이므로 anser에 더하기
				answer = answer + temp;
			else							// 나머지는 사정없이 뺄셈 ㄱㄱ
				answer = answer - temp;
			
		}
		
		System.out.println(answer);
		
	}
	
	static int mySum(String a) {
		
		int sum = 0;
		
		String[] temp = a.split("\\+");			// [+] 사용도 가능		// "+"를 인자로 넣을 시 패턴 오류 발생!!
		for(int i=0; i<temp.length; i++) {
			sum += Integer.parseInt(temp[i]);
		}
		
		return sum;
	}

}
