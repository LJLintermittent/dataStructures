package sort;
/**
 * ��λ�ȡ�������������λ��
 * int maxLength = (max + "").length();
 * @author ����� 2020.3.19
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

		// System.out.println("����ǰ��������");
		// System.out.println(Arrays.toString(arr));

		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("����ǰ��ʱ����=" + date1Str);

		radixSort(arr);
		// System.out.println("������������");
		// System.out.println(Arrays.toString(arr));

		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("������ʱ����=" + date2Str);

	}

	public static void radixSort(int[] array) {
		// �Ը�λʮλ��λ���д���
		// �����λ�����ʾʮ��Ͱ,��Ҫ��ֹ���
		// �ռ任ʱ���㷨
		// Ϊ�˼�¼ÿ��Ͱ��ʵ�ʴ���˶������ݣ�bucket[][0]Ϊ����
		// ����һά�����¼ÿ��Ͱ�ڵ���������
		int num[] = new int[10];
		int[][] bucket = new int[10][array.length];
		// �õ��������������λ��
		int max = array[0];
		for (int i = 1; i < array.length - 1; i++) {
			if (max < array[i])
				max = array[i];
		}
		// ��ȡ���λ��������ǿ
		int maxLength = (max + "").length();
		// ��λ/1/10��λ/100��%
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