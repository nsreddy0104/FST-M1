package activities;

import java.util.Arrays;

public class Activity4 {
	public static void main(String[] args) {
		
		int[] arr = {3,5,7,9,4,1};
		ascendingSort(arr);
		System.out.println("Ascending order is :");
		System.out.println(Arrays.toString(arr));
	}
	
	static void ascendingSort(int arr[]) {
		int size=arr.length, i;
		
		for(i=1;i<size;i++) {
			int key = arr[i];
			int j=i-1;
			while(j>=0 && key<arr[j]) {
				arr[j+1]=arr[j];
				--j;
			}
			arr[j+1] =key;
		}
	}
}
