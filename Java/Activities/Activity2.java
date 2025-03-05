package activities;

import javax.naming.spi.DirStateFactory.Result;

public class Activity2 {

	public static void main(String[] args) {
		
		Activity2 actObj = new Activity2();

		int[] arr = {10,22,34,10,21,10};
		
		int searchNum=10;
		int fixedSum=30;
		
		System.out.println("Does the saum equal to 30?: "+actObj.result(arr,searchNum,fixedSum));
	}
	
	public boolean result(int[] nums, int searchNum, int fixedSum) {
		int temp=0;
		for(int num :nums) {
			if(num==searchNum) {
				temp+=searchNum;
				
			}
			if(temp>fixedSum) {
				break;
			}
		}
		return temp==fixedSum;
	}

}
