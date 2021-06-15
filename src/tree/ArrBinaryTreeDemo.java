package tree;
//***************************************************************˳��洢������****************************************************************************
public class ArrBinaryTreeDemo {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		// ����һ�� ArrBinaryTree
		ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
		System.out.println("ǰ�����");
		arrBinaryTree.preOrder(); // 1,2,4,5,3,6,7
		System.out.println("");
		System.out.println("�������");
		arrBinaryTree.infixOrder(); // 4,2,5,1,6,3,7
		System.out.println("");
		System.out.println("�������");
		arrBinaryTree.postOrder(); // 4,5,2,6,7,3,1
	}

}

// ��дһ��ArrayBinaryTree, ʵ��˳��洢����������

class ArrBinaryTree {
	private int[] arr;// �洢���ݽ�������

	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}

	// ����preOrder
	public void preOrder() {
		this.preOrder(0);
	}

	// ����infixOrder
	public void infixOrder() {
		this.infixOrder(0);
	}

	// ����postOrder
	public void postOrder() {
		this.postOrder(0);
	}

	// ��дһ�����������˳��洢��������ǰ�����
	/**
	 * @param index:������±�
	 *            
	 */
	public void preOrder(int index) {
		// �������Ϊ�գ����� arr.length = 0
		if (arr == null || arr.length == 0) {
			System.out.println("����Ϊ�գ����ܰ��ն�������ǰ�����");
		}
		// �����ǰ���Ԫ��
		System.out.print(arr[index]);
		// ����ݹ����
		if ((index * 2 + 1) < arr.length) {
			preOrder(2 * index + 1);
		}
		// ���ҵݹ����
		if ((index * 2 + 2) < arr.length) {
			preOrder(2 * index + 2);
		}
	}

	// ��дһ�����������˳��洢���������������
	public void infixOrder(int index) {
		// �������Ϊ�գ����� arr.length = 0
		if (arr == null || arr.length == 0) {
			System.out.println("����Ϊ�գ����ܰ��ն��������������");
		}
		// ����ݹ����
		if ((index * 2 + 1) < arr.length) {
			infixOrder(2 * index + 1);
		}
		// �����ǰ���Ԫ��
		System.out.print(arr[index]);
		// ���ҵݹ����
		if ((index * 2 + 2) < arr.length) {
			infixOrder(2 * index + 2);
		}
	}

	// ��дһ�����������˳��洢�������ĺ������
	public void postOrder(int index) {
		// �������Ϊ�գ����� arr.length = 0
		if (arr == null || arr.length == 0) {
			System.out.println("����Ϊ�գ����ܰ��ն������ĺ������");
		}
		// ����ݹ����
		if ((index * 2 + 1) < arr.length) {
			postOrder(2 * index + 1);
		}
		// ���ҵݹ����
		if ((index * 2 + 2) < arr.length) {
			postOrder(2 * index + 2);
		}
		// �����ǰ���Ԫ��
		System.out.print(arr[index]);
	}
}

