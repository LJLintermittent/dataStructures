package sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
 
/*
 * �ֶ���֮
 * �鲢����
 */
public class MergeSort {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	int array[] = {8,4,5,7,1,3,6,2};
		//���Թ鲢�����ִ���ٶ�
		// ����Ҫ��80000�������������
		int[] arr = new int[80000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 800000000); // ����һ��[0, 8000000) ��
		}
//       System.out.println("����ǰ��������");
//        System.out.println(Arrays.toString(arr));
        
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("����ǰ��ʱ����=" + date1Str);
		
		
		int temp[] = new int[arr.length];
		mergeSort(arr,0,arr.length-1,temp);
		
//		System.out.println("������������");
//		System.out.println(Arrays.toString(arr));
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("������ʱ����=" + date2Str);
	//	System.out.printf("������Ϊ��%s", Arrays.toString(array));
	}
	
	//��+ �Ϸ���
	public static void mergeSort(int[] array, int left,int right,int[] temp) {
		if(left < right) {
			int mid = (left + right)/2;
			mergeSort(array,left,mid,temp);
			mergeSort(array,mid + 1,right,temp);
			merge(array,left,mid,right,temp);
		
		}
	}
	
	
	public static void merge(int[] array,int left,int mid,int right,int[] temp) {
		int i = left;  //��ʼ��i������������еĳ�ʼ����
		int j = mid + 1; //��ʼ��j,�ұ����������еĳ�ʼ����
		int t = 0; 
		//���������ߣ����򣩵����鰴�չ�����䵽temp����
		while(i <= mid && j <= right) {
			if(array[i] <= array[j]) {
				temp[t++] = array[i++];
				
			}else {
				temp[t++] = array[j++];
 
			}
		}
		
		//������ʣ�����ݵ�һ��ȫ����䵽temp
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