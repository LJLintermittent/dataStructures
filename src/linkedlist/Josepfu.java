package linkedlist;
//                                                          Լɪ������
//    n ����Χ��һȦ��Լ�����Ϊk���˴� 1 ��ʼ����������  m  ���Ǹ��˳��У�������һλ�ִ�1��ʼ����������m���Ǹ����ֳ��У�ֱ�������˳���

/**
 * ����һ�������������˼·��
 * 1.�ȴ�����һ���ڵ㣬��firstָ��ýڵ㣬�γɻ���
 * 2.��������û����һ���ڵ㣬�ͰѸýڵ���뵽���еĻ��������м���
 * 
 *������������
 *1.����һ������ָ��Curboyָ��first�ڵ�
 *2.ͨ��whileѭ���������û�������  Curboy.next == first;
 *
 */
/**
 * ��Ҫ����һ������ָ�루������helper ������Ӧ��ָ����������������ڵ�   tips��С������ǰ������first��helper�ƶ�k-1��
 * ��С������ʱ����first��helperָ��ͬʱ�ƶ�m-1�Σ�
 * ��ʱ�Ϳ��Խ�firstָ���С����Ȧ
 * first == first.next��
 * helper.next == first��
 * ԭ��firstָ��Ľڵ�û���κ����ã��ͻᱻ����
 */

/**
 * Tips:
 *
 * @author �����
 * @version 2020��3��3��
 */
public class Josepfu {
 
    public static void main(String[] args) {
        // ����һ�ѿ����������������ͱ����Ƿ�ok
        CircleSingleLinkedList circleSingleLinked = new CircleSingleLinkedList();
        circleSingleLinked.addBoys(20);
        circleSingleLinked.showBoy();
        circleSingleLinked.countBoy(1,20,25);
 
    }
 
}
 
// ����һ�����εĵ�������
class CircleSingleLinkedList {
 
    // ����һ��first�ڵ�,��ǰû�б��
    private Boy first = null;
 
    // ���С���ڵ㣬������һ�����ε�����
    public void addBoys(int nums) {
 
        if (nums < 1) {
            System.out.println("nums  ֵ���ԣ���");
            return;
        }
        //����ָ�룬����������������
        Boy curBoy = null;
 
 
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
 
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                boy.setNext(first);
                curBoy.setNext(boy);
                curBoy = boy;
            }
        }
 
    }
 
    // ������ǰ�Ļ�������
    public void showBoy() {
        // �ж������Ƿ�Ϊ��
        if (first == null) {
            System.out.println("û���κ�С��~~");
            return;
        }
        // ��Ϊfirst���ܶ������������Ȼʹ��һ������ָ����ɱ���
        Boy curBoy = first;
        while (true) {
            System.out.printf("boy's number  %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {// ˵���Ѿ��������
                break;
            }
            curBoy = curBoy.getNext(); // curBoy����
        }
    }
 
    // �����û������룬�����С����Ȧ��˳��
 
    /**
     * @param startNo  ��ʾ�ӵڼ���С����ʼ����
     * @param countNum ��ʾ������
     * @param nums     ��ʾ����ж���С����Ȧ��
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // �ȶ����ݽ���У��
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("������������ ����������");
            return;
        }
        // ���󴴽�һ������ָ��(����) helper , ����Ӧ��ָ����������������ڵ�
        Boy  helper =first;
 
        while (true){
            if(helper.getNext()==first){
                break;
            }
 
            helper=helper.getNext();
        }
 
        //С������ǰ������ first ��  helper �ƶ� k - 1��
        for(int j = 0; j < startNo - 1; j++) {
            first=first.getNext();
            helper=helper.getNext();
        }
 
 
        while (true){
            if(helper==first){
                System.out.printf("end boy  %d \n",first.getNo());
                break;
            }
 
         for(int j = 0; j < countNum-1; j++) {
 
                 first=first.getNext();
                 helper=helper.getNext();
          }
            //��ʱfirstָ��Ľڵ㣬����Ҫ��Ȧ��С���ڵ�
            System.out.printf("boy  %d  out  \n",first.getNo());
 
            first=first.getNext();
            helper.setNext(first);
        }
 
 
    }
 
}
 
 
// ����һ��Boy�࣬��ʾһ���ڵ�
class Boy {
    private int no;// ���
    private Boy next; // ָ����һ���ڵ�,Ĭ��null
 
    public Boy(int no) {
        this.no = no;
    }
 
    public int getNo() {
        return no;
    }
 
    public void setNo(int no) {
        this.no = no;
    }
 
    public Boy getNext() {
        return next;
    }
 
    public void setNext(Boy next) {
        this.next = next;
    }
 
}
 