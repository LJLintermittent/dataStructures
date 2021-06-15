package stack;
/**
 *                                                                        栈的应用场景：
 * 
1）子程序的调用；
2）处理递归调用；
3）表达式的转换（中缀表达式转后缀表达式）与求值；
4）二叉树的遍历；
5）图形深度优先搜索法
 * @author 李佳乐  
 * 2020年3月12日
 *
 */
//栈的特点：
/* 栈是先入后出的有序列表
栈是限制线性表中元素只能在线性表的同一端进行的一种特殊线性表。允许插入和删除，进行变化的一端称为栈顶，另一端固定的称为栈底
根据栈的先入后出的定义可知，最先放入栈中的元素，最后出来，放在最底下，而删除刚好相反，最后放入的元素，在栈顶，最先被删除 
*/

//                                                                         用数组构造栈
/**
 * 可以用数组和链表去实现栈，这里我们先用数组实现栈的去分析：
先定义一个实现栈的类，因为对于出栈和入栈移动的都是栈顶标志位，所以需要定义一个变量top用来表示栈顶，初始化为-1。此外还需要的成员变量有一个数组stack，和一个确定数组的大小，也就是确定了栈的大小的变量MaxSize
构造方法：
传入一个数值，用来确定栈的大小，同时构造出该栈
入栈方法：
先判断栈是否满，top == MaxSize-1
不满时，先让top++，往上移动一位，然后将传入的数值，入栈stack[top]=入栈的值
出栈方法：
判断栈是否为空，top == -1；
不为空时，需要出栈的数据就是当前栈顶的数据stack[top]，然后直接top–即可
显示栈顶元素：
因为top始终指向栈顶元素，所以直接显示stack[top]即可
遍历方法：
直接用一个for循环，从栈顶开始往下遍历输出即可

*
*/

/**
 * 栈类
 * @author 
 * @param
 * 		MaxSize:栈的容量大小
 * 		stack:栈数组
 * 		top:用来指向栈顶位置
 * @method
 * 		Stack(int size):构造方法，构造出一个栈
 * 		is_Full():判断栈满
 * 		is_Empty():判断栈空
 * 		push(int data):入栈
 * 		pop():出栈
 * 		show():遍历显示栈
 */




//用数组实现的栈实现一个综合计算器（只能计算个位整数加减乘除且不带小括号）
public class Calculator {

	public static void main(String[] args) {
		
		int num1,num2;//定义两个变量用来接收数栈中出栈需要被计算的两个数
		int oper;//用来接收符号栈中出栈的运算符
		int rs = 0;//用来存放运算的结果
		int index = 0;//用来扫描算是字符串的索引
		char ch = ' ';//每次扫描算式得到的char都保存在该变量中
		
		//先构造一个数栈和一个符号栈
		CalculatorStack numstack = new CalculatorStack(10);//数栈
		CalculatorStack operstack = new CalculatorStack(10);//符号栈
		//接着给一个算是字符串
		String exam = "4+3*6-5";
		//开始扫描字符串
		while(true) {
			//依次得到exam中的每一个字符
			ch = exam.substring(index, index+1).charAt(0);
			//判断扫描到的是数字还是运算符
			if(operstack.is_Oper(ch)) {
				//如果是运算符,继续判断符号栈中是否为空
				if(operstack.is_Empty()) {
					//如果为空，直接入栈
					operstack.push(ch);
				}else {
					//如果不为空，则比较扫描到的运算符与符号栈栈顶的运算符的优先级
					if(operstack.priority(ch) < operstack.priority(operstack.peek())) {
						//如果扫描到的运算符比符号栈栈顶的运算符的优先级小或相等
						//那么数栈出栈两个数，符号栈中出栈两个数
						num1 = numstack.pop();
						num2 = numstack.pop();
						oper = operstack.pop();
						//接着计算
						rs = numstack.cal(num1, num2, oper);
						//将结果放入数栈
						numstack.push(rs);
						//再将刚才扫描到的运算符，入符号栈
						operstack.push(ch);
					}else {
						//如果扫描到的运算符比符号栈栈顶的运算符的优先级大,直接入栈
						operstack.push(ch);
					}
				}
			}else {
				//如果是数字,直接入数栈
				numstack.push(ch-48);//注意这里有个ASCII表的char和int的数值问题
			}
			index++;//索引往下移动
			//判断是否扫描完
			if(index >= exam.length()) {
				break;//跳出while循环
			}
		}
		
		//扫描完之后，开始度数栈和符号栈中的残留数据进行运算
		while(true){
			if(operstack.is_Empty()) {
				//如果符号栈为空，说明数栈中只剩一个数字，即为最终结果
				rs = numstack.pop();
				break;
			}
			//否则
			//进行运算:取数栈中的两个数和符号栈中的运算符进行运算，将结果放回数栈，反复进行直到数栈中剩一个最终结果
			num1 = numstack.pop();
			num2 = numstack.pop();
			oper = operstack.pop();
			rs = numstack.cal(num1, num2, oper);
			numstack.push(rs);
		}
		System.out.println("计算结果为："+ rs);
		}
	}


/**
 * 栈类
 * @author 
 * @param
 * 		MaxSize:栈的容量大小
 * 		stack:栈数组
 * 		top:用来指向栈顶位置
 * @method
 * 		Stack(int size):构造方法，构造出一个栈
 * 		is_Full():判断栈满
 * 		is_Empty():判断栈空
 * 		push(int data):入栈
 * 		pop():出栈
 * 		show():遍历显示栈
 */
class CalculatorStack{
	
	private int MaxSize;//栈的容量大小
	private int[] stack;//栈数组
	private int top = -1;//用来指向栈顶位置
	
	//构造方法
	CalculatorStack(int size){
		MaxSize = size;
		stack = new int[MaxSize];
	}
	//判断栈满
	public boolean is_Full() {
		return top == MaxSize-1;
	}
	//判断栈空
	public boolean is_Empty() {
		return top == -1;
	}
	//入栈
	public void push(int data) {
		if(is_Full()) {
			System.out.println("栈满，不能再放入数据了");
			return;
		}
		top++;
		stack[top] = data;
	}
	//出栈
	public int pop() {
		int val = 0;
		if(is_Empty()) {
			System.out.println("栈空，不能出栈");
			return 0;
		}
		System.out.printf("出栈的元素是:stack[%d]:%d\n", top,stack[top]);
		val = stack[top];
		top--;
		return val;
	}
	//显示栈顶元素
	public int peek() {
		if(is_Empty()) {
			System.out.println("栈空，没有元素");
			return 0;
		}
		System.out.printf("栈顶元素是:stack[%d]:%d\n", top,stack[top]);
		return stack[top];
	}
	//遍历
	public void show() {
		if(is_Empty()) {
			System.out.println("栈空，没有元素");
			return;
		}
		for(int i=top; i>=0; i--) {
			System.out.printf("stack[%d]:%d\n", i,stack[i]);
		}
	}
	
	//新增方法1：因为运算符的优先级是程序员自己定的，所以增加一个方法取确定运算符的优先级
	public int priority(int i) {//这里注意一下，char和int在底层是可以相互通用比较的
		switch(i) {
		case '*':
			return 1;
		case '/':
			return 1;
		case '+':
			return 0;
		case '-':
			return 0;
		}
		return-1;
	}
	
	//新增方法2：加一个方法去判断扫描到的是数字还是运算符
	public boolean is_Oper(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	//新增方法3：还需要一个计算方法
	public int cal(int num1, int num2, int oper) {
		int result = 0;
		switch(oper) {
		case '+':
			result = num1+num2;
			break;
		case '-':
			result = num2-num1;//这里要注意一下减数和被减数啥的位置
			break;
		case '*':
			result = num1*num2;
			break;
		case '/':
			result = num2/num1;
			break;
		default:
			break;
		}
		return result;
	}
	
}

/**
 * 用数组实现的栈实现一个综合计算器
 * 这里以3+2*6-2
 * @author 
 * @param
 * 		index:用来扫描算是字符串的索引
 * 		ch:每次扫描算式得到的char都保存在该变量中
 * 		num1,num2:定义两个变量用来接收数栈中出栈需要被计算的两个数
 *		oper:用来接收符号栈中出栈的运算符
 *		rs:用来存放运算的结果
 *		numstack:数栈
 *		operstack:符号栈
 */


// 用数组构造栈
/*class Stack{
	
	private int MaxSize;//栈的容量大小
	private int[] stack;//栈数组
	private int top = -1;//用来指向栈顶位置
	
	//构造方法
	Stack(int size){
		MaxSize = size;
		stack = new int[MaxSize];
	}
	//判断栈满
	public boolean is_Full() {
		return top == MaxSize-1;
	}
	//判断栈空
	public boolean is_Empty() {
		return top == -1;
	}
	//入栈
	public void push(int data) {
		if(is_Full()) {
			System.out.println("栈满，不能再放入数据了");
			return;
		}
		top++;
		stack[top] = data;
	}
	//出栈
	public void pop() {
		if(is_Empty()) {
			System.out.println("栈空，不能出栈");
			return;
		}
		System.out.printf("出栈的元素是:stack[%d]:%d\n", top,stack[top]);
		top--;
	}
	//显示栈顶元素
	public void peek() {
		if(is_Empty()) {
			System.out.println("栈空，没有元素");
			return;
		}
		System.out.printf("栈顶元素是:stack[%d]:%d\n", top,stack[top]);
	}
	//遍历
	public void show() {
		if(is_Empty()) {
			System.out.println("栈空，没有元素");
			return;
		}
		for(int i=top; i>=0; i--) {
			System.out.printf("stack[%d]:%d\n", i,stack[i]);
		}
	}
}
*/


























