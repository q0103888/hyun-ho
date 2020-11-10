package lhh;

import java.util.Scanner;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           test1();
	}
	
	/*
	 * 사용자로 부터 먗개의 정수를 입력할지 그 크기를 입럭받는ㄷ
	 * 그 크기의 배열변수를 생성한다
	 * 
	 * 배열의 크기만큼 다음을 반복한다
	 * 사용자로부터 점수를 입력받아 배열에 순서대로 저장한다
	 * 
	 * 배열의 첫번째 원소부터 마지막 원소까지 출력한다
	 * 그 점수들의 평균을 출력한다
	 * 입력된 정수 중 최대 점수를 출력한다
	 */
	    public static void test1() {
		Scanner input = new Scanner(System.in);
		System.out.println("몇 개의 정수를 입력하시겠습니다까?");
		
		int count = input.nextInt();
		
		int[] scores = new int[count];
		
		int sum = 0;
		
		for(int idx = 0; idx < count; idx++) {
			System.out.println(idx+1+"번째 정수를 입력하세요");
			int score = input.nextInt();
			
			scores[idx] = score;
			sum += score; //sum = sum + score
		}
		
		int max = scores[0];
		
		System.out.println("입력된 정수는 다음과 같습니다...");
		for (int idx = 0; idx < count; idx++) {
			System.out.println(scores[idx]);
			if(scores[idx] > max) {
				max = scores[idx];
			}
		}
		System.out.println("평균 : " + (double)sum/count);
		System.out.println("최대값은 : " + max);
	}

}
