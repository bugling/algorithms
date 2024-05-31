package 정렬;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PhyscExamSort {  

	static class PhyscData {
		String name;		// 이름
		int height;			// 키
		double vision;		// 시력
		
		// 생성자
		PhyscData(String name, int height, double vision) {
			this.name = name;
			this.height = height;
			this.vision = vision;
		}
		
		// 신체컥사 데이터를 문자열로 반환한다.
		public String toString() {
			return name + " " + height + " " + vision;
		}
		
		static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();
		
		private static class HeightOrderComparator implements Comparator<PhyscData>{

			@Override
			public int compare(PhyscData d1, PhyscData d2) {
				return (d1.height > d2.height) ? 1:				// d1이 더 크면 1을 리턴
					(d1.height < d2.height) ? -1 : 0;			// 앞의 질의가 false이면 d2가 더 큰지 계산
																// d2가 더 크면 -1		// 두 값이 같으면 0을 리턴
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		PhyscData[] x = {
				new PhyscData("이나령", 162, 0.3),
				new PhyscData("전서현", 173, 0.7),
				new PhyscData("이수민", 162, 2.0),
				new PhyscData("홍준기", 171, 1.5),
				new PhyscData("유지훈", 168, 0.4),
				new PhyscData("이호연", 174, 1.2),
				new PhyscData("김한결", 169, 0.8),
		};
		
		Arrays.sort(x,						// 배열 x를
				PhyscData.HEIGHT_ORDER		// HEIGHT_ORDER을 사용하여 정렬
		);
		
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키	시럭");
		System.out.println("-----------------------");
		for(int i=0; i<x.length; i++)
			System.out.printf("%-8s%3d%8.1f\n", x[i].name, x[i].height, x[i].vision);
		
		
		/**
		 * ■ 신체검사 리스트 ■
 		이름     키	시럭
		-----------------------
		이나령     162     0.3
		이수민     162     2.0
		유지훈     168     0.4
		김한결     169     0.8
		홍준기     171     1.5
		전서현     173     0.7
		이호연     174     1.2
		 * **/
		
	}

}
