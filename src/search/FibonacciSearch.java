package search;



import java.util.Arrays;
 
/*
 * 斐波那契数查找 
 * 同插值查找，改变mid值
 * 对黄金分割法的一点点理解：
 * 它和插值寻找都是改变mid值，而斐波那契数列f[k] = f[k-1]+ f[k-2]
 * 			mid = low + f[k-1]-1;
 * 因此我们可以在查找的时候利用这两个特性，如果要查找的值value < temp[mid],也就是小于发[k-1]索引代表的值，则肯定返回high = mid -1 同时返回k-1
 */
 
public class FibonacciSearch {
 
	public static int maxSize = 20;
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,8,10,89,1000,1234};
		int result = fibSearch(array,1234);
		if(result!= -1) {
			System.out.printf("索引为：%d",result);
		}else {
			System.out.printf("没有该数据");
 
		}
	}
	
	//斐波那契数列
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for(int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}
	
 
	
	/*
	 * value是查找的值，如果由则返回数组的下标
	 */
	public static int fibSearch(int[]array,int value) {
		int low = 0;
		int high = array.length -1;
		int k = 0;//斐波那契数列的下标
		int mid = 0; //存放mid值
		int f[] = fib(); //获取斐波那契数列
		while(high > f[k] - 1) {
			k++;
			
			//找到合适的k值
		}
		System.out.printf("找到的K值为:%d \n",k);
		//因为f[k]的值可能大于array的长度，因此需要使用Arrays类，构建一个新的数组，并指向array[]
		int[] temp = Arrays.copyOf(array, f[k]);
		//这句代码的含义是将array中前f[k]长度的数据复制到temp中，array长度不够则后面都填0
		//为了不使其为0，需要将0改变成array[high],即array数组中最后一个元素的值
		for(int i = high + 1; i < temp.length; i++) {
			temp[i] = array[high];
		}
		
		
		//使用while循环，找到key值
		while(low <= high) {
			mid = low + f[k-1]-1;
			if(value < temp[mid]) {
				high = mid -1;
				k--;
				//因为value的值小于temp[mid]，因此需要找[low,mid]的范围，而这个范围对应f[k] = f[k-1] + f[k-2];
				//因此需要k--,去找前半部分f[k-1]的内容  
				//在此基础上下一轮循环中 mid 的值为f[k-1] -1 + low
			}else if(value > temp[mid]) {
				low = mid + 1;
				k-=2;
				//此时k-=2同上后面的元素对应f[k-2]部分
			}else {
				
				//需要考虑返回哪个下标
				if(mid <= high) {
					return mid;
				
				}else {
					return high;
				}
			}
		}
 
		return -1;
	}
 
}