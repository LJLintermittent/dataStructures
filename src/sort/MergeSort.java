package sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
 
/*
 * 分而治之
 * 归并排序
 */
public class MergeSort {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	int array[] = {8,4,5,7,1,3,6,2};
		//测试归并排序的执行速度
		// 创建要给80000个的随机的数组
		int[] arr = new int[80000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 800000000); // 生成一个[0, 8000000) 数
		}
//       System.out.println("排序前的数组是");
//        System.out.println(Arrays.toString(arr));
        
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);
		
		
		int temp[] = new int[arr.length];
		mergeSort(arr,0,arr.length-1,temp);
		
//		System.out.println("排序后的数组是");
//		System.out.println(Arrays.toString(arr));
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序后的时间是=" + date2Str);
	//	System.out.printf("输出结果为：%s", Arrays.toString(array));
	}
	
	//分+ 合方法
	public static void mergeSort(int[] array, int left,int right,int[] temp) {
		if(left < right) {
			int mid = (left + right)/2;
			mergeSort(array,left,mid,temp);
			mergeSort(array,mid + 1,right,temp);
			merge(array,left,mid,right,temp);
		
		}
	}
	
	
	public static void merge(int[] array,int left,int mid,int right,int[] temp) {
		int i = left;  //初始化i，左边有序序列的初始索引
		int j = mid + 1; //初始化j,右边有序列序列的初始索引
		int t = 0; 
		//把左右两边（有序）的数组按照规则填充到temp数组
		while(i <= mid && j <= right) {
			if(array[i] <= array[j]) {
				temp[t++] = array[i++];
				
			}else {
				temp[t++] = array[j++];
 
			}
		}
		
		//将把有剩余数据的一边全部填充到temp
		while(i <= mid) {
			temp[t++] = array[i++];
		}
		while(j <= right) {
			temp[t++] = array[j++];
 
		}
		
		t = 0;
		int tempLeft = left;
		while(tempLeft <= right) {
			array[tempLeft++] = temp[t++];
		}
	}
	
	
 
}