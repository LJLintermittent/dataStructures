package stack;
/**
 *                                                                        ջ��Ӧ�ó�����
 * 
1���ӳ���ĵ��ã�
2������ݹ���ã�
3�����ʽ��ת������׺���ʽת��׺���ʽ������ֵ��
4���������ı�����
5��ͼ���������������
 * @author �����  
 * 2020��3��12��
 *
 */
//ջ���ص㣺
/* ջ���������������б�
ջ���������Ա���Ԫ��ֻ�������Ա��ͬһ�˽��е�һ���������Ա���������ɾ�������б仯��һ�˳�Ϊջ������һ�˹̶��ĳ�Ϊջ��
����ջ���������Ķ����֪�����ȷ���ջ�е�Ԫ�أ�����������������£���ɾ���պ��෴���������Ԫ�أ���ջ�������ȱ�ɾ�� 
*/

//                                                                         �����鹹��ջ
/**
 * ���������������ȥʵ��ջ������������������ʵ��ջ��ȥ������
�ȶ���һ��ʵ��ջ���࣬��Ϊ���ڳ�ջ����ջ�ƶ��Ķ���ջ����־λ��������Ҫ����һ������top������ʾջ������ʼ��Ϊ-1�����⻹��Ҫ�ĳ�Ա������һ������stack����һ��ȷ������Ĵ�С��Ҳ����ȷ����ջ�Ĵ�С�ı���MaxSize
���췽����
����һ����ֵ������ȷ��ջ�Ĵ�С��ͬʱ�������ջ
��ջ������
���ж�ջ�Ƿ�����top == MaxSize-1
����ʱ������top++�������ƶ�һλ��Ȼ�󽫴������ֵ����ջstack[top]=��ջ��ֵ
��ջ������
�ж�ջ�Ƿ�Ϊ�գ�top == -1��
��Ϊ��ʱ����Ҫ��ջ�����ݾ��ǵ�ǰջ��������stack[top]��Ȼ��ֱ��top�C����
��ʾջ��Ԫ�أ�
��Ϊtopʼ��ָ��ջ��Ԫ�أ�����ֱ����ʾstack[top]����
����������
ֱ����һ��forѭ������ջ����ʼ���±����������

*
*/

/**
 * ջ��
 * @author 
 * @param
 * 		MaxSize:ջ��������С
 * 		stack:ջ����
 * 		top:����ָ��ջ��λ��
 * @method
 * 		Stack(int size):���췽���������һ��ջ
 * 		is_Full():�ж�ջ��
 * 		is_Empty():�ж�ջ��
 * 		push(int data):��ջ
 * 		pop():��ջ
 * 		show():������ʾջ
 */




//������ʵ�ֵ�ջʵ��һ���ۺϼ�������ֻ�ܼ����λ�����Ӽ��˳��Ҳ���С���ţ�
public class Calculator {

	public static void main(String[] args) {
		
		int num1,num2;//����������������������ջ�г�ջ��Ҫ�������������
		int oper;//�������շ���ջ�г�ջ�������
		int rs = 0;//�����������Ľ��
		int index = 0;//����ɨ�������ַ���������
		char ch = ' ';//ÿ��ɨ����ʽ�õ���char�������ڸñ�����
		
		//�ȹ���һ����ջ��һ������ջ
		CalculatorStack numstack = new CalculatorStack(10);//��ջ
		CalculatorStack operstack = new CalculatorStack(10);//����ջ
		//���Ÿ�һ�������ַ���
		String exam = "4+3*6-5";
		//��ʼɨ���ַ���
		while(true) {
			//���εõ�exam�е�ÿһ���ַ�
			ch = exam.substring(index, index+1).charAt(0);
			//�ж�ɨ�赽�������ֻ��������
			if(operstack.is_Oper(ch)) {
				//����������,�����жϷ���ջ���Ƿ�Ϊ��
				if(operstack.is_Empty()) {
					//���Ϊ�գ�ֱ����ջ
					operstack.push(ch);
				}else {
					//�����Ϊ�գ���Ƚ�ɨ�赽������������ջջ��������������ȼ�
					if(operstack.priority(ch) < operstack.priority(operstack.peek())) {
						//���ɨ�赽��������ȷ���ջջ��������������ȼ�С�����
						//��ô��ջ��ջ������������ջ�г�ջ������
						num1 = numstack.pop();
						num2 = numstack.pop();
						oper = operstack.pop();
						//���ż���
						rs = numstack.cal(num1, num2, oper);
						//�����������ջ
						numstack.push(rs);
						//�ٽ��ղ�ɨ�赽��������������ջ
						operstack.push(ch);
					}else {
						//���ɨ�赽��������ȷ���ջջ��������������ȼ���,ֱ����ջ
						operstack.push(ch);
					}
				}
			}else {
				//���������,ֱ������ջ
				numstack.push(ch-48);//ע�������и�ASCII���char��int����ֵ����
			}
			index++;//���������ƶ�
			//�ж��Ƿ�ɨ����
			if(index >= exam.length()) {
				break;//����whileѭ��
			}
		}
		
		//ɨ����֮�󣬿�ʼ����ջ�ͷ���ջ�еĲ������ݽ�������
		while(true){
			if(operstack.is_Empty()) {
				//�������ջΪ�գ�˵����ջ��ֻʣһ�����֣���Ϊ���ս��
				rs = numstack.pop();
				break;
			}
			//����
			//��������:ȡ��ջ�е��������ͷ���ջ�е�������������㣬������Ż���ջ����������ֱ����ջ��ʣһ�����ս��
			num1 = numstack.pop();
			num2 = numstack.pop();
			oper = operstack.pop();
			rs = numstack.cal(num1, num2, oper);
			numstack.push(rs);
		}
		System.out.println("������Ϊ��"+ rs);
		}
	}


/**
 * ջ��
 * @author 
 * @param
 * 		MaxSize:ջ��������С
 * 		stack:ջ����
 * 		top:����ָ��ջ��λ��
 * @method
 * 		Stack(int size):���췽���������һ��ջ
 * 		is_Full():�ж�ջ��
 * 		is_Empty():�ж�ջ��
 * 		push(int data):��ջ
 * 		pop():��ջ
 * 		show():������ʾջ
 */
class CalculatorStack{
	
	private int MaxSize;//ջ��������С
	private int[] stack;//ջ����
	private int top = -1;//����ָ��ջ��λ��
	
	//���췽��
	CalculatorStack(int size){
		MaxSize = size;
		stack = new int[MaxSize];
	}
	//�ж�ջ��
	public boolean is_Full() {
		return top == MaxSize-1;
	}
	//�ж�ջ��
	public boolean is_Empty() {
		return top == -1;
	}
	//��ջ
	public void push(int data) {
		if(is_Full()) {
			System.out.println("ջ���������ٷ���������");
			return;
		}
		top++;
		stack[top] = data;
	}
	//��ջ
	public int pop() {
		int val = 0;
		if(is_Empty()) {
			System.out.println("ջ�գ����ܳ�ջ");
			return 0;
		}
		System.out.printf("��ջ��Ԫ����:stack[%d]:%d\n", top,stack[top]);
		val = stack[top];
		top--;
		return val;
	}
	//��ʾջ��Ԫ��
	public int peek() {
		if(is_Empty()) {
			System.out.println("ջ�գ�û��Ԫ��");
			return 0;
		}
		System.out.printf("ջ��Ԫ����:stack[%d]:%d\n", top,stack[top]);
		return stack[top];
	}
	//����
	public void show() {
		if(is_Empty()) {
			System.out.println("ջ�գ�û��Ԫ��");
			return;
		}
		for(int i=top; i>=0; i--) {
			System.out.printf("stack[%d]:%d\n", i,stack[i]);
		}
	}
	
	//��������1����Ϊ����������ȼ��ǳ���Ա�Լ����ģ���������һ������ȡȷ������������ȼ�
	public int priority(int i) {//����ע��һ�£�char��int�ڵײ��ǿ����໥ͨ�ñȽϵ�
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
	
	//��������2����һ������ȥ�ж�ɨ�赽�������ֻ��������
	public boolean is_Oper(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	//��������3������Ҫһ�����㷽��
	public int cal(int num1, int num2, int oper) {
		int result = 0;
		switch(oper) {
		case '+':
			result = num1+num2;
			break;
		case '-':
			result = num2-num1;//����Ҫע��һ�¼����ͱ�����ɶ��λ��
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
 * ������ʵ�ֵ�ջʵ��һ���ۺϼ�����
 * ������3+2*6-2
 * @author 
 * @param
 * 		index:����ɨ�������ַ���������
 * 		ch:ÿ��ɨ����ʽ�õ���char�������ڸñ�����
 * 		num1,num2:����������������������ջ�г�ջ��Ҫ�������������
 *		oper:�������շ���ջ�г�ջ�������
 *		rs:�����������Ľ��
 *		numstack:��ջ
 *		operstack:����ջ
 */


// �����鹹��ջ
/*class Stack{
	
	private int MaxSize;//ջ��������С
	private int[] stack;//ջ����
	private int top = -1;//����ָ��ջ��λ��
	
	//���췽��
	Stack(int size){
		MaxSize = size;
		stack = new int[MaxSize];
	}
	//�ж�ջ��
	public boolean is_Full() {
		return top == MaxSize-1;
	}
	//�ж�ջ��
	public boolean is_Empty() {
		return top == -1;
	}
	//��ջ
	public void push(int data) {
		if(is_Full()) {
			System.out.println("ջ���������ٷ���������");
			return;
		}
		top++;
		stack[top] = data;
	}
	//��ջ
	public void pop() {
		if(is_Empty()) {
			System.out.println("ջ�գ����ܳ�ջ");
			return;
		}
		System.out.printf("��ջ��Ԫ����:stack[%d]:%d\n", top,stack[top]);
		top--;
	}
	//��ʾջ��Ԫ��
	public void peek() {
		if(is_Empty()) {
			System.out.println("ջ�գ�û��Ԫ��");
			return;
		}
		System.out.printf("ջ��Ԫ����:stack[%d]:%d\n", top,stack[top]);
	}
	//����
	public void show() {
		if(is_Empty()) {
			System.out.println("ջ�գ�û��Ԫ��");
			return;
		}
		for(int i=top; i>=0; i--) {
			System.out.printf("stack[%d]:%d\n", i,stack[i]);
		}
	}
}
*/


























