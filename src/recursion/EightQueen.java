package recursion;

/**
 * 八皇后问题：在8×8的国际象棋上摆放八个皇后，使其不能相互攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上。问：有多少种摆法？
 * 具体思路：
1）先把第一个皇后放在第一行的第一列

2）接着开始放后面的皇后，每个皇后都放在上一皇后所在行的下一行，并且从下一行的第一列开始放，判断是否和底下的任一皇后有冲突，如果有的话，就再往右移动一列

3）如果放到某一行发现为死路，放不下去了，那么就开始进行回溯，回退到上一行，判断该行是否已经在最后一列了，如果是的话，继续回退到上一行，不是的话，往右移动一列，判断是否和底下的任一皇后有冲突，如果有的话，就再往右移动一列，如果没有的话就回到（2）步

4）当第一个皇后(也就是第一行的皇后)放在第一列的情况下，八个皇后都摆放好后的第一种情况完成之后，又开始进行回溯

5）回溯的思路：从第八行（也就是第八个皇后）开始，判断他是否在最后一列，如果不是的话，将它所在列往右移动一列，然后继续判断是否和底下的任意一个皇后有冲突，如果没有，又是一种解。

6）如果移到最后一列了都不行，或本身就在最后一列的话，那就回溯到倒数第二行，将倒数第二行的皇后往右移动一列。然后将下一行的皇后从第一列开始重新摆一遍

7)后面依次往下回一行溯，重复上面的（5）（6）步骤
*
*/


/**
 * 八皇后问题
 * 
 * 		number:确定皇后的数量
 *		array:存放皇后的位置
 *		total_solution:记录一共有多少种解法
 *		total_frequency:记录一共递归了多少次
 *		total_check:记录一共调用了多少次检查位置的方法
 *
 */
public class EightQueen {
	
	//确定皇后的数量
	int number = 8;
	//用一个一维来存放皇后的位置
	//如果array[] = {0,4,7,5,2,6,1,3};即表示第一个皇后在第一列，第二个皇后在第五列，第三个皇后在第八列...
	int[] array = new int[number];
	//用变量来记录一共有多少种解法，记录计算机一共递归了多少次，记录一共调用了多少次检查位置的方法
	static int total_solution,total_frequency,total_check;
	
	public static void main(String args[]) {
		EightQueen eq = new EightQueen();
		eq.place(0);//从第一行，也就是从第一个皇后开始摆放位置
		System.out.printf("一共有%d种解法\n",total_solution);
		System.out.printf("一共调用了%d次递归\n",total_frequency);
		System.out.printf("一共进行了%d次判断是否位置冲突\n",total_check);
	}
	
	/**
	 * 检查皇后的位置是否合理
	 * @param
	 * 		n:表示检查第几个皇后的位置是否合理
	 */
	private boolean checkLocation(int n) {
		total_check++;//每次一进来检查的次数就+1
		for(int i = 0; i<n; i++) {
			//不用判断行，因为我们已经规定了每一行放一个，所以判断列和斜线即可
			//这里判断是否在统一斜线用了java自带的函数Math.abs
			//Math.abs(n-i) == Math.abs(array[n] - array[i])可以理解为斜线斜率为1，即y2 - y1 = x2 - x1
			if(array[n] == array[i] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 递归调用方法去寻找摆放的位置
	 * @param
	 * 		n:表示放置第n个皇后
	 * !!注意：这里的递归是放在了for循环里面，所以这里比较难理解的是for循环里嵌入递归
	 */
	private void place(int n) {
		total_frequency++;//每次一进来递归次数就+1
		if(n == number) {
			//如果n到8了，说明八个皇后已经放好，因为数组下标从0开始，最大到7
			//既然八个皇后都放好了我们就打印一下结果
			print();
			total_solution++;//解法+1
		}else {
			//否则的话，依次放入皇后,并判断位置是否冲突
			for(int i = 0 ;i < number; i++) {
				//先把当前皇后放到对应行的第一列
				array[n] = i;
				//判断位置是否冲突
				if(checkLocation(n)) {
					//如果不冲突，递归继续放下一行
					place(n+1);
				}
				//如果冲突，那么就继续执行for循环，array[n] = i;i自动加一，改行的位置就会自动的往右移一列
			}
		}
	}
	
	/**
	 * 写一个方法，用来打印array数组，方便显示
	 */
	private void print() {
		for(int i = 0; i<array.length; i++) {
			System.out.printf(array[i]+" ");
		}
		System.out.println();
	}
}



