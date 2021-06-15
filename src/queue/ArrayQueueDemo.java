package queue;

import java.util.Scanner;

/**
 * ������ģ�����
 * ��������:����ֻ��ʹ��һ��
 * ԭ��:û��ȡģ,û�����ɻ��ζ���
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        // ����
        // ����һ������
        ArrayQueue queue = new ArrayQueue(3);
        // �����û�����
        char  key = ' ';
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

//ʹ������ģ��һ������,����һ��ArrayQueue��
class ArrayQueue {
    private int maxSize;// ��ʾ������������
    private int front;// ��ʾ����ͷ
    private int rear;// ��ʾ����β
    private int[] arr;// ���������ڴ������,ģ�����

    // �������еĹ�����
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    // �ж϶����Ƿ�����
    public boolean isFull() {
        return rear == maxSize - 1;
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
        rear++;// ��near����
        arr[rear] = n;
    }

    // ��ȡ���е�����,������
    public int getQueue() {
        // �ж϶����Ƿ�Ϊ��
        if (isEmpty()) {
            // ͨ���׳��쳣
            throw new RuntimeException("����Ϊ��,����ȡ������");
        }
        front++;
        return arr[front];
    }

    // ��ʾ���е���������
    public void showQueue() {
        // �ж϶����Ƿ�Ϊ��
        if (isEmpty()) {
            System.out.println("������û������");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // ��ʾ���е�ͷ����,ע�ⲻ��ȡ������
    public int headQueue() {
        // �ж϶����Ƿ�Ϊ��
        if (isEmpty()) {
            throw new RuntimeException("����Ϊ��,û������");
        }
        return arr[front + 1];
    }
}
