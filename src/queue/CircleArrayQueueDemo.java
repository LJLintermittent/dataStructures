package queue;

import java.util.Scanner;



public class CircleArrayQueueDemo {
	public static void main(String[] args) {
		// 测试
		        // 创建一个队列
		        CircleArray queue = new CircleArray(4);
		        // 接收用户输入
		        char key = ' ';
		        Scanner scanner = new Scanner(System.in);
		        boolean loop = true;
		        // 输出一个菜单
		        while (loop) {
		            System.out.println("s(show): 显示队列");
		            System.out.println("e(exit): 退出程序");
		            System.out.println("a(add): 添加数据到队列");
		            System.out.println("g(get): 从队列取出数据");
		            System.out.println("h(head): 查看队列的头数据");
		            key = scanner.next().charAt(0);// 接收一个字符
		            switch (key) {
		                case 's':
		                    queue.showQueue();
		                    break;
		                case 'a':
		                    System.out.println("请输入一个数字");
		                    int value = scanner.nextInt();
		                    queue.addQueue(value);
		                    break;
		                case 'g':
		                    try {
		                        int res = queue.getQueue();
		                        System.out.printf("取出的数据是%d\n", res);
		                    } catch (Exception e) {
		                        System.out.println(e.getMessage());
		                    }
		                    break;
		                case 'h':
		                    try {
		                        int res = queue.headQueue();
		                        System.out.printf("队列的头数据是%d\n", res);
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
		        System.out.println("程序退出");
		    }
		}

		// 使用数组模拟一个环形队列,定义一个CircleArray类
		class CircleArray {
		    private int maxSize;// 表示数组的最大容量
		    // front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
		    //front 的初始值 = 0
		    private int front;// 表示队列头
		    //  rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定
		    //  rear 的初始值 = 0
		    private int rear;// 表示队列尾
		    private int[] arr;// 该数组用于存放数据,模拟队列

		    // 创建队列的构造器
		    public CircleArray(int arrMaxSize) {
		        maxSize = arrMaxSize;
		        arr = new int[maxSize];
		        // front和rear的默认值就是0
		    }

		    // 判断队列是否已满
		    public boolean isFull() {
		        // 如:rear=1,front=0,maxSize=3
		        return (rear + 1) % maxSize == front;
		    }

		    // 判断队列是否为空
		    public boolean isEmpty() {
		        return front == rear;
		    }

		    // 添加数据到队列
		    public void addQueue(int n) {
		        // 判断队列是否已满
		        if (isFull()) {
		            System.out.println("队列已满,不能添加数据");
		            return;
		        }
		        arr[rear] = n;
		        rear = (rear + 1) % maxSize;// 让near后移
		    }

		    // 获取队列的数据,出队列
		    public int getQueue() {
		        // 判断队列是否为空
		        if (isEmpty()) {
		            // 通过抛出异常
		            throw new RuntimeException("队列为空,不能取出数据");
		        }
		        int value = arr[front];
		        front = (front + 1) % maxSize;
		        return value;
		    }

		    // 显示队列的所有数据
		    public void showQueue() {
		        // 判断队列是否为空
		        if (isEmpty()) {
		            System.out.println("队列中没有数据");
		            return;
		        }
		        for (int i = front; i < front + size(); i++) {
		            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		        }
		    }

		    // 显示队列的头数据,注意不是取出数据
		    public int headQueue() {
		        // 判断队列是否为空
		        if (isEmpty()) {
		            throw new RuntimeException("队列为空,没有数据");
		        }
		        return arr[front];
		    }

		    // 获取数组中有效元素的数量
		    public int size() {
		        return (rear + maxSize - front) % maxSize;
		    }
		}
	
