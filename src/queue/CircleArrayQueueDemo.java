package queue;

import java.util.Scanner;



public class CircleArrayQueueDemo {
	public static void main(String[] args) {
		// ����
		        // ����һ������
		        CircleArray queue = new CircleArray(4);
		        // �����û�����
		        char key = ' ';
		        Scanner scanner = new Scanner(System.in);
		        boolean loop = true;
		        // ���һ���˵�
		        while (loop) {
		            System.out.println("s(show): ��ʾ����");
		            System.out.println("e(exit): �˳�����");
		            System.out.println("a(add): ������ݵ�����");
		            System.out.println("g(get): �Ӷ���ȡ������");
		            System.out.println("h(head): �鿴���е�ͷ����");
		            key = scanner.next().charAt(0);// ����һ���ַ�
		            switch (key) {
		                case 's':
		                    queue.showQueue();
		                    break;
		                case 'a':
		                    System.out.println("������һ������");
		                    int value = scanner.nextInt();
		                    queue.addQueue(value);
		                    break;
		                case 'g':
		                    try {
		                        int res = queue.getQueue();
		                        System.out.printf("ȡ����������%d\n", res);
		                    } catch (Exception e) {
		                        System.out.println(e.getMessage());
		                    }
		                    break;
		                case 'h':
		                    try {
		                        int res = queue.headQueue();
		                        System.out.printf("���е�ͷ������%d\n", res);
		                    } catch (Exception e) {
		                        System.out.println(e.getMessage());
		                    }
		                    break;
		                case 'e':
		                    scanner.close();
		                    loop = false;
		                    break;
		                default:
		                    break;
		            }
		        }
		        System.out.println("�����˳�");
		    }
		}

		// ʹ������ģ��һ�����ζ���,����һ��CircleArray��
		class CircleArray {
		    private int maxSize;// ��ʾ������������
		    // front �����ĺ�����һ�������� front ��ָ����еĵ�һ��Ԫ��, Ҳ����˵ arr[front] ���Ƕ��еĵ�һ��Ԫ��
		    //front �ĳ�ʼֵ = 0
		    private int front;// ��ʾ����ͷ
		    //  rear �����ĺ�����һ��������rear ָ����е����һ��Ԫ�صĺ�һ��λ��. ��Ϊϣ���ճ�һ���ռ���ΪԼ��
		    //  rear �ĳ�ʼֵ = 0
		    private int rear;// ��ʾ����β
		    private int[] arr;// ���������ڴ������,ģ�����

		    // �������еĹ�����
		    public CircleArray(int arrMaxSize) {
		        maxSize = arrMaxSize;
		        arr = new int[maxSize];
		        // front��rear��Ĭ��ֵ����0
		    }

		    // �ж϶����Ƿ�����
		    public boolean isFull() {
		        // ��:rear=1,front=0,maxSize=3
		        return (rear + 1) % maxSize == front;
		    }

		    // �ж϶����Ƿ�Ϊ��
		    public boolean isEmpty() {
		        return front == rear;
		    }

		    // ������ݵ�����
		    public void addQueue(int n) {
		        // �ж϶����Ƿ�����
		        if (isFull()) {
		            System.out.println("��������,�����������");
		            return;
		        }
		        arr[rear] = n;
		        rear = (rear + 1) % maxSize;// ��near����
		    }

		    // ��ȡ���е�����,������
		    public int getQueue() {
		        // �ж϶����Ƿ�Ϊ��
		        if (isEmpty()) {
		            // ͨ���׳��쳣
		            throw new RuntimeException("����Ϊ��,����ȡ������");
		        }
		        int value = arr[front];
		        front = (front + 1) % maxSize;
		        return value;
		    }

		    // ��ʾ���е���������
		    public void showQueue() {
		        // �ж϶����Ƿ�Ϊ��
		        if (isEmpty()) {
		            System.out.println("������û������");
		            return;
		        }
		        for (int i = front; i < front + size(); i++) {
		            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		        }
		    }

		    // ��ʾ���е�ͷ����,ע�ⲻ��ȡ������
		    public int headQueue() {
		        // �ж϶����Ƿ�Ϊ��
		        if (isEmpty()) {
		            throw new RuntimeException("����Ϊ��,û������");
		        }
		        return arr[front];
		    }

		    // ��ȡ��������ЧԪ�ص�����
		    public int size() {
		        return (rear + maxSize - front) % maxSize;
		    }
		}
	
