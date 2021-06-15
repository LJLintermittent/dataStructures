package sort;

import java.text.SimpleDateFormat;
import java.util.Date;


public class insertSort {

   public static void main(String[] args) {
     //  int arr[] ={101, 34, 119, 1, -1, 89};
		int[] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}
	   
	   
	   Date data1 = new Date();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
       String date1Str = simpleDateFormat.format(data1);
       System.out.println("排序前的时间是=" + date1Str);

       insertSort(arr);
       
       Date data2 = new Date();
       String date2Str = simpleDateFormat.format(data2);
       System.out.println("排序后的时间是=" + date2Str);

   }

   //插入排序
   public static void insertSort(int[] arr) {
       int insertVal = 0;
       int insertIndex = 0;
       //使用for循环来把代码简化
       for(int i = 1; i < arr.length; i++) {
           insertVal=arr[i]; //待插入的数
           insertIndex=i-1;  //待插入的数的前一个index

           while (insertIndex>=0 && insertVal<arr[insertIndex]){
               //往后面移一位
               arr[insertIndex + 1] =arr[insertIndex];
               insertIndex--;
           }

            //如果有序数组的最大一位是小于待插入的数则不需要移位
           if(insertIndex+1!=i){
               arr[insertIndex+1]=insertVal;
           }
       }
   }
}