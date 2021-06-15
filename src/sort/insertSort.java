package sort;

import java.text.SimpleDateFormat;
import java.util.Date;


public class insertSort {

   public static void main(String[] args) {
     //  int arr[] ={101, 34, 119, 1, -1, 89};
		int[] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 8000000); // ����һ��[0, 8000000) ��
		}
	   
	   
	   Date data1 = new Date();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
       String date1Str = simpleDateFormat.format(data1);
       System.out.println("����ǰ��ʱ����=" + date1Str);

       insertSort(arr);
       
       Date data2 = new Date();
       String date2Str = simpleDateFormat.format(data2);
       System.out.println("������ʱ����=" + date2Str);

   }

   //��������
   public static void insertSort(int[] arr) {
       int insertVal = 0;
       int insertIndex = 0;
       //ʹ��forѭ�����Ѵ����
       for(int i = 1; i < arr.length; i++) {
           insertVal=arr[i]; //���������
           insertIndex=i-1;  //�����������ǰһ��index

           while (insertIndex>=0 && insertVal<arr[insertIndex]){
               //��������һλ
               arr[insertIndex + 1] =arr[insertIndex];
               insertIndex--;
           }

            //���������������һλ��С�ڴ������������Ҫ��λ
           if(insertIndex+1!=i){
               arr[insertIndex+1]=insertVal;
           }
       }
   }
}