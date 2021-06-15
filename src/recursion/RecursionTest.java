package recursion;
/**                                                               递归问题
 * 
 * 递归简单来讲就是方法自己调用自己，每次调用时传入不同的变量，递归有助于编程者解决复杂的问题，同时可以让代码变得更简洁.。
 * @author 李佳乐  2020.3.12
 *
 */
/*                                                                递归机制
我们知道java虚拟机里面可以分为三部分，堆空间、栈空间和代码与常量空间，在执行上诉方法的时候，从main方法开始，最首先会在栈空间中开辟一个区域来存放main方法，因为main方法中调用printtest(4)方法，所以又在栈空间中开辟一个区域用来存放printtest(4)方法。看上面的代码，因为我们的printtest中可以分为if条件和System打印两部分，所以每个printtest方法都会先判断if，那么很自然的根据if中的n是否大于2，printtest(4)又会调用printtest(3)，printtest(3)又会调用printtest(2)，所以栈空间中从上到下就是如下图所示的四个空间，按照黑色箭头的方向逐级调用
到这里为止，printtest(2)再去判断if的时候已经不满足条件了，所以就执行System的打印部分，输出n=2，那么printtest(2)中的两部分都已经执行完毕了，出栈
回到printtest(3)，继续执行它里面剩余的System的打印部分，输出n=3，执行完后printtest(3)整个方法执行完毕，出栈
回到printtest(4)，继续执行它里面剩余的System的打印部分，输出n=4，执行完后printtest(3)整个方法执行完毕，出栈
最后回到main方法，那么main方法里只有调用printttest(4)，它执行完毕了，main方法也执行完毕，很自然的整个程序就执行完毕了。最终运行结果如下图控制台部分所示  */

/*            
public static void main(String args[]) {
		printtest(4);
	}
	
	public static void printtest(int n) {
		if(n>2) {
				printtest(n-1);	
		}
		System.out.println("n="+n);
	}

*/

/*                                                              阶乘问题的递归示例
 *    public static void main(String args[]) {
		//给一个标志位：在输入不为整数的时候可以重新输入
		boolean flag = true;
		//输入一个整数
		System.out.println("请输入一个整数：");
		Scanner s = new Scanner(System.in);
		while(flag) {
			String number = s.nextLine();
			//判断是否是整数
			if(isInteger(number)) {
				//递归调用方法计算这个数的阶乘
				int result = factorial(Integer.parseInt(number)); 
				System.out.println(number+"的阶乘是："+result);
				//资源关闭
				s.close();
				flag = false;
			}else {
				System.out.println("输入的数不是整数！请重新输入：");
			}
		}
	}
	
	                                                                                                           递归调用方法求阶乘
	public static int factorial(int n) {
		if(n == 1) {
			return 1;
		}else {
			return factorial(n-1)*n;	
		}
	}
	
	//写一个方法，用于判断输入的数是否是整数
	public static boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	}

*/
/**
 * 递归用于解决什么样的问题：
1）各种数学问题：如8皇后问题，汉诺塔，阶乘问题，迷宫问题，球和篮子问题
2）各种算法中也会用到递归，比如快排，归并排序，二分查找，分治算法等
3）将用栈解决的问题改为用递归解决能让代码更简洁
使用递归的规则：
1）执行一个方法，就创建一个新的受保护的独立空间（栈空间）
2）方法的局部变量是独立的，不会相互影响（比如我们上面打印问题中的每次方法传入的变量n）
3）如果方法中使用的是引用类型的变量，就会共享该引用类型的数据
4）递归必须向递归结束的条件逼近，否则就是无限递归，出现StackOverflowError错误
5）当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕

 * 
 *
 */

//                                                                    迷宫回溯问题
//起始位置是  int[1][1]
//终点位置是  int[6][5]
public class RecursionTest {
	
	public static void main(String args[]) {
		
		//先用一个二维数组去模拟迷宫地图,用1表示墙
		int [][] map = new int[8][7];
		//开始画墙
		for(int i = 0; i<7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		for(int i=0; i<8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		map[3][1] = 1;
		map[3][2] = 1;
		
		//打印一下地图
		System.out.println("初始化地图：");
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j <7; j++) {
				System.out.printf("%d\t",map[i][j]);
			}
			System.out.println();
		}
		
		//调用递归函数去寻路
		Pathfinding(map, 1, 1);
		
		//寻路完之后，再打印一下地图
		System.out.println("寻路之后的地图：");
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j <7; j++) {
				System.out.printf("%d\t",map[i][j]);
			}
			System.out.println();
		}
		
	}
	
	/**
	 * @param
	 * 		map:地图
	 * 		i:当前所在行
	 * 		j:当前所在列
	 * @return
	 * 		如果找到返回true，找不到返回false
	 * 约定:当表示地图的二维数组map中值为0表示没走过
	 * 		为1表示墙不能走
	 * 		为2表示走过了，且能走是通路
	 * 		为3表示走过了，但走不通
	 * 规定一个策略：按照下、右、上、左的顺序进行寻路
	 */
	public static boolean Pathfinding(int[][] map, int i, int j) {
		if(map[6][5] == 2) {
			//如果终点位置int[6][5]已经为2，说明已经到了终点
			return true;
		}else {
			//如果还没到终点的话,且如果这个点还没走过的话
			if(map[i][j] == 0) {
				//先将它设为2，暂且表示为通路
				map[i][j] = 2;
				//接着按照策略下、右、上、左进行下一步的寻路
				if(Pathfinding(map, i+1, j)) {
					return true;
				}else if(Pathfinding(map, i, j+1)) {
					return true;
				}else if(Pathfinding(map, i-1, j)) {
					return true;	
				}else if(Pathfinding(map, i, j-1)) {
					return true;
				}else {
					//如果四个方向都走过了都不行，那说明这个点不为通路，置为3
					map[i][j] = 3;
					return false;
				}
			}else {
				//如果这个点已经走过了
				return false;
			}
		}
	}
}

