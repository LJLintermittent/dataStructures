package recursion;
/**                                                               �ݹ�����
 * 
 * �ݹ���������Ƿ����Լ������Լ���ÿ�ε���ʱ���벻ͬ�ı������ݹ������ڱ���߽�����ӵ����⣬ͬʱ�����ô����ø����.��
 * @author �����  2020.3.12
 *
 */
/*                                                                �ݹ����
����֪��java�����������Է�Ϊ�����֣��ѿռ䡢ջ�ռ�ʹ����볣���ռ䣬��ִ�����߷�����ʱ�򣬴�main������ʼ�������Ȼ���ջ�ռ��п���һ�����������main��������Ϊmain�����е���printtest(4)��������������ջ�ռ��п���һ�������������printtest(4)������������Ĵ��룬��Ϊ���ǵ�printtest�п��Է�Ϊif������System��ӡ�����֣�����ÿ��printtest�����������ж�if����ô����Ȼ�ĸ���if�е�n�Ƿ����2��printtest(4)�ֻ����printtest(3)��printtest(3)�ֻ����printtest(2)������ջ�ռ��д��ϵ��¾�������ͼ��ʾ���ĸ��ռ䣬���պ�ɫ��ͷ�ķ����𼶵���
������Ϊֹ��printtest(2)��ȥ�ж�if��ʱ���Ѿ������������ˣ����Ծ�ִ��System�Ĵ�ӡ���֣����n=2����ôprinttest(2)�е������ֶ��Ѿ�ִ������ˣ���ջ
�ص�printtest(3)������ִ��������ʣ���System�Ĵ�ӡ���֣����n=3��ִ�����printtest(3)��������ִ����ϣ���ջ
�ص�printtest(4)������ִ��������ʣ���System�Ĵ�ӡ���֣����n=4��ִ�����printtest(3)��������ִ����ϣ���ջ
���ص�main��������ômain������ֻ�е���printttest(4)����ִ������ˣ�main����Ҳִ����ϣ�����Ȼ�����������ִ������ˡ��������н������ͼ����̨������ʾ  */

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

/*                                                              �׳�����ĵݹ�ʾ��
 *    public static void main(String args[]) {
		//��һ����־λ�������벻Ϊ������ʱ�������������
		boolean flag = true;
		//����һ������
		System.out.println("������һ��������");
		Scanner s = new Scanner(System.in);
		while(flag) {
			String number = s.nextLine();
			//�ж��Ƿ�������
			if(isInteger(number)) {
				//�ݹ���÷�������������Ľ׳�
				int result = factorial(Integer.parseInt(number)); 
				System.out.println(number+"�Ľ׳��ǣ�"+result);
				//��Դ�ر�
				s.close();
				flag = false;
			}else {
				System.out.println("��������������������������룺");
			}
		}
	}
	
	                                                                                                           �ݹ���÷�����׳�
	public static int factorial(int n) {
		if(n == 1) {
			return 1;
		}else {
			return factorial(n-1)*n;	
		}
	}
	
	//дһ�������������ж���������Ƿ�������
	public static boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	}

*/
/**
 * �ݹ����ڽ��ʲô�������⣺
1��������ѧ���⣺��8�ʺ����⣬��ŵ�����׳����⣬�Թ����⣬�����������
2�������㷨��Ҳ���õ��ݹ飬������ţ��鲢���򣬶��ֲ��ң������㷨��
3������ջ����������Ϊ�õݹ������ô�������
ʹ�õݹ�Ĺ���
1��ִ��һ���������ʹ���һ���µ��ܱ����Ķ����ռ䣨ջ�ռ䣩
2�������ľֲ������Ƕ����ģ������໥Ӱ�죨�������������ӡ�����е�ÿ�η�������ı���n��
3�����������ʹ�õ����������͵ı������ͻṲ����������͵�����
4���ݹ������ݹ�����������ƽ�������������޵ݹ飬����StackOverflowError����
5����һ������ִ����ϣ���������return���ͻ᷵�أ�����˭���þͽ�������ظ�˭��ͬʱ������ִ����ϻ��߷���ʱ���÷���Ҳ��ִ�����

 * 
 *
 */

//                                                                    �Թ���������
//��ʼλ����  int[1][1]
//�յ�λ����  int[6][5]
public class RecursionTest {
	
	public static void main(String args[]) {
		
		//����һ����ά����ȥģ���Թ���ͼ,��1��ʾǽ
		int [][] map = new int[8][7];
		//��ʼ��ǽ
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
		
		//��ӡһ�µ�ͼ
		System.out.println("��ʼ����ͼ��");
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j <7; j++) {
				System.out.printf("%d\t",map[i][j]);
			}
			System.out.println();
		}
		
		//���õݹ麯��ȥѰ·
		Pathfinding(map, 1, 1);
		
		//Ѱ·��֮���ٴ�ӡһ�µ�ͼ
		System.out.println("Ѱ·֮��ĵ�ͼ��");
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j <7; j++) {
				System.out.printf("%d\t",map[i][j]);
			}
			System.out.println();
		}
		
	}
	
	/**
	 * @param
	 * 		map:��ͼ
	 * 		i:��ǰ������
	 * 		j:��ǰ������
	 * @return
	 * 		����ҵ�����true���Ҳ�������false
	 * Լ��:����ʾ��ͼ�Ķ�ά����map��ֵΪ0��ʾû�߹�
	 * 		Ϊ1��ʾǽ������
	 * 		Ϊ2��ʾ�߹��ˣ���������ͨ·
	 * 		Ϊ3��ʾ�߹��ˣ����߲�ͨ
	 * �涨һ�����ԣ������¡��ҡ��ϡ����˳�����Ѱ·
	 */
	public static boolean Pathfinding(int[][] map, int i, int j) {
		if(map[6][5] == 2) {
			//����յ�λ��int[6][5]�Ѿ�Ϊ2��˵���Ѿ������յ�
			return true;
		}else {
			//�����û���յ�Ļ�,���������㻹û�߹��Ļ�
			if(map[i][j] == 0) {
				//�Ƚ�����Ϊ2�����ұ�ʾΪͨ·
				map[i][j] = 2;
				//���Ű��ղ����¡��ҡ��ϡ��������һ����Ѱ·
				if(Pathfinding(map, i+1, j)) {
					return true;
				}else if(Pathfinding(map, i, j+1)) {
					return true;
				}else if(Pathfinding(map, i-1, j)) {
					return true;	
				}else if(Pathfinding(map, i, j-1)) {
					return true;
				}else {
					//����ĸ������߹��˶����У���˵������㲻Ϊͨ·����Ϊ3
					map[i][j] = 3;
					return false;
				}
			}else {
				//���������Ѿ��߹���
				return false;
			}
		}
	}
}

