 package 정렬;

public class 계수정렬 {		// CountSort

	public static void main(String[] args) {
		
		// 5이하의 수들을 오름차순으로 나열하라
		
		int temp = 0;
		int count[] = new int[5];
		
		int array[] = {1, 3, 2, 4, 3, 2, 5, 3, 1, 2,
						3, 4, 4, 3, 5, 1, 2, 3, 5, 2,
						3, 1, 4, 3, 5, 1, 2, 1, 1, 1};
		
		
		for(int i=0; i<5; i++) {
			count[i] = 0;
		}
		
		
		for(int i=0; i<array.length; i++) {
			count[array[i] - 1]++;
		}
		
//		for(int i=0; i<count.length; i++) {
//			System.out.println(  count[i] ) ;
//		}
		
		for(int i=0; i<5; i++) {
			if(count[i] != 0) {
				for(int j=0; j<count[i]; j++) {
					System.out.printf("%d", i + 1);
				}
			}
		}
	}

}
