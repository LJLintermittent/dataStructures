package sort;
/**
 * 如何获取数组中最大数的位数
 * int maxLength = (max + "").length();
 * @author 李佳乐 2020.3.19
 *
 */

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int array[] = {53,3,542,748,14,214};
		int[] arr = new int[8000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}

		// System.out.println("排序前的数组是");
		// System.out.println(Arrays.toString(arr));

		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);

		radixSort(arr);
		// System.out.println("排序后的数组是");
		// System.out.println(Arrays.toString(arr));

		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序后的时间是=" + date2Str);

	}

	public static void radixSort(int[] array) {
		// 对个位十位百位进行处理
		// 定义二位数组表示十个桶,需要防止溢出
		// 空间换时间算法
		// 为了记录每个桶中实际存放了多少数据，bucket[][0]为数字
		// 定义一维数组记录每个桶内的数字数量
		int num[] = new int[10];
		int[][] bucket = new int[10][array.length];
		// 得到数组中最大数的位置
		int max = array[0];
		for (int i = 1; i < array.length - 1; i++) {
			if (max < array[i])
				max = array[i];
		}
		// 获取最大位数操作很强
		int maxLength = (max + "").length();
		// 各位/1/10百位/100都%
		for (int count = 0, n = 1; count < maxLength; count++, n *= 10) {
			for (int i = 0; i < array.length; i++) {
				int data = array[i] / n % 10;
				bucket[data][num[data]] = array[i];
				num[data] = num[data] + 1;
			}
			int index = 0;

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < array.length; j++) {
					if (bucket[i][j] != 0) {
						// System.out.print(bucket[i][j] + " ");
						array[index++] = bucket[i][j];
					}
					bucket[i][j] = 0;

				}

			}
			for (int i = 0; i < 10; i++) {
				num[i] = 0;
			}
			// System.out.println();
			// System.out.print(Arrays.toString(array));
			// System.out.println();

		}

	}

}