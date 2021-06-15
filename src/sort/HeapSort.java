package sort;
// ********************************************************************������ ************************************************************************************
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	public static void main(String[] args) {
		// �����������������
	//	int arr[] = { 4, 6, 8, 5, 9 };
	//	System.out.println("����ǰ=" + Arrays.toString(arr));
	//	heapSort(arr);
	//	System.out.println("�����=" + Arrays.toString(arr));

		// ������8000000�������������
		int[] arr1 = new int[8];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = (int) (Math.random() * 8000000); // ����һ��[0, 8000000) ��
		}

		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("����ǰ��ʱ����=" + date1Str);

		heapSort(arr1);

		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("������ʱ����=" + date2Str);
	}

	// ��дһ��������ķ���
	public static void heapSort(int arr[]) {
		int temp = 0;

		// ���������й�����һ���ѣ���������������ѡ��󶥶ѻ�С����
		// 1)�ҳ���������������ڶ���
		// ͬʱ���ܰ�ÿ����֧�Ƚϴ������������ÿ����֧�Ķ���
		// ����Ϊ�Ժ�ıȽ����˺ܺõı�֤
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		/*
		 * 2)���Ѷ�Ԫ����ĩβԪ�ؽ����������Ԫ��"��"������ĩ��; ���� 
		 * 3)���µ����ṹ��ʹ������Ѷ��壬Ȼ����������Ѷ�Ԫ���뵱ǰĩβԪ�ء�
		 *   ����ִ�е���+�������裬ֱ��������������
		 */
		for (int j = arr.length - 1; j > 0; j--) {
			// ����
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}

	}

	// ��һ������(������), ������һ���󶥶�
	/**
	 * ���ܣ� ��� �� �� i ��Ӧ�ķ�Ҷ�ӽ����������ɴ󶥶�
	 * 
	 * @param arr
	 *            ������������
	 * @param i
	 *            ��ʾ��Ҷ�ӽ��������������
	 * @param length
	 *            ��ʾ�Զ��ٸ�Ԫ�ؼ��������� length �����𽥵ļ���
	 */
	public static void adjustHeap(int arr[], int i, int length) {

		int temp = arr[i];// ��ȡ����ǰԪ�ص�ֵ����������ʱ����
		// k = i * 2 + 1 �� i�������ӽ��
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			// ˵�����ӽ���ֵС�����ӽ���ֵ
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				k++; // k ָ�����ӽ��
			}
			if (arr[k] > temp) { // ����ӽ����ڸ����
				arr[i] = arr[k]; // �ѽϴ��ֵ������ǰ���
				i = k; // i ָ�� k,����ѭ���Ƚ�
			} else {
				break;
			}
		}
		// ��forѭ�������������Ѿ�����iΪ�������������ֵ���������(�ֲ�)
		arr[i] = temp;// ��tempֵ�ŵ��������λ��
	}

}

