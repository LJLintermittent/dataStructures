package sort;
// ********************************************************************堆排序 ************************************************************************************
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	public static void main(String[] args) {
		// 将数组进行升序排序
	//	int arr[] = { 4, 6, 8, 5, 9 };
	//	System.out.println("排序前=" + Arrays.toString(arr));
	//	heapSort(arr);
	//	System.out.println("排序后=" + Arrays.toString(arr));

		// 创建有8000000个随机数的数组
		int[] arr1 = new int[8];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
		}

		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间是=" + date1Str);

		heapSort(arr1);

		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序后的时间是=" + date2Str);
	}

	// 编写一个堆排序的方法
	public static void heapSort(int arr[]) {
		int temp = 0;

		// 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
		// 1)找出堆中最大数，放在顶端
		// 同时，能把每个分支比较大的数，都放在每个分支的顶端
		// 这样为以后的比较做了很好的保证
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		/*
		 * 2)将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端; 　　 
		 * 3)重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素。
		 *   反复执行调整+交换步骤，直到整个序列有序。
		 */
		for (int j = arr.length - 1; j > 0; j--) {
			// 交换
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}

	}

	// 将一个数组(二叉树), 调整成一个大顶堆
	/**
	 * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
	 * 
	 * @param arr
	 *            待调整的数组
	 * @param i
	 *            表示非叶子结点在数组中索引
	 * @param length
	 *            表示对多少个元素继续调整， length 是在逐渐的减少
	 */
	public static void adjustHeap(int arr[], int i, int length) {

		int temp = arr[i];// 先取出当前元素的值，保存在临时变量
		// k = i * 2 + 1 是 i结点的左子结点
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			// 说明左子结点的值小于右子结点的值
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				k++; // k 指向右子结点
			}
			if (arr[k] > temp) { // 如果子结点大于父结点
				arr[i] = arr[k]; // 把较大的值赋给当前结点
				i = k; // i 指向 k,继续循环比较
			} else {
				break;
			}
		}
		// 当for循环结束后，我们已经将以i为父结点的树的最大值，放在了最顶(局部)
		arr[i] = temp;// 将temp值放到调整后的位置
	}

}

