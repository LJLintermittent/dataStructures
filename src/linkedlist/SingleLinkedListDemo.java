package linkedlist;

/**
 * ʹ�ô�headͷ�ĵ�������ʵ��--ˮ�Ӣ�����а����--��ɶ��������ɾ�Ĳ����
 * 
 */
// 1.�ȴ���һ��head ͷ��㣬�����Ǳ�ʾ�������ͷ
// 2.����ÿ���һ���ڵ㣬��ֱ�Ӽ��뵽��������
// 3.ͨ��һ��������������������������������
//�ӵ�������ɾ��һ���ڵ��˼·
//temp.next = temp.next.next;
// ��ɾ���Ľڵ㽫��������������ָ�򣬻ᱻ�������ջ��ƻ���

/**
 *                                                         �����⣺�������е���Ч�ڵ����
 *   public static int getLengthSelf(HeroNode head) {
        HeroNode temp = head;
        int count = 0;
        if (temp.getNext() == null) {
            return 0;
        }
        while (true) {
 
            if (temp == null) {
                break;
            }
            if (temp.getNext() != null) {
                count++;
            }
            temp = temp.getNext();
        }
 
        System.out.println("length: " + count);
        return count;
 
    }
 * 
 */

/**
 *                                                      ���ҵ������еĵ�����k����� �����������⡿
    public static HeroNode findLastIndexNode(HeroNode head ,int lastIndex){
 
        if(head==null){
            return null;
        }  
 
        int length = getLengthSelf(head);
 
        if(length==0 || length-lastIndex<0){
         return  null;
        }
        HeroNode cur = head.getNext(); //3 // 3 - 1 = 2
        for(int i = 0; i < length-lastIndex; i++) {
            cur=cur.getNext();
        }
 
        System.out.println(cur.getId());
        return cur;
    } 
 */

/**
 *                                                              ������ķ�ת  ��Ѷ������
 *               ˼·��
 *               �ȶ���һ���ڵ㣺reversehead = new HeroNode��
 *               ��ͷ��β����ԭ��������ÿ����һ���ڵ㣬�ͽ���ȡ�����������µ�����reversedHead ����ǰ��
 *               ԭ��������head.next = reversedHead.next��
 *  //��������ת
    public static void reversetList(HeroNode head) {
 
        if(head.getNext()==null || head.getNext().getNext()==null){
                    return;
        }
 
        //�൱��һ����ʱ������
        HeroNode  reversetHead=new HeroNode(0," ","");
        HeroNode  cur =head.getNext();
        //��ʱ�Ľڵ�  �洢cur��״̬
        HeroNode  next =null;
 
        while (cur!=null){
            next=cur.getNext();
            //�൱�ڰ� cur.next�ڵ� �ĳ���reversetHead��ͷ������һ���ڵ㣩����һ���ڵ�
            //����Ϊ����cur�ڵ㻹��Ŀ�������Ԫ��
            cur.setNext(reversetHead.getNext());
            //��һ�д���ĸ�ֵ
            reversetHead.setNext(cur);
            cur = next;//��cur����
 
        }
        //��ת��ɺ� ��ֵ��Ŀ��ڵ�
      //  head=reversetHead;  Ϊʲô��������
        head.setNext(reversetHead.getNext());
    }
 */

/**                                                      ��ͷ��β��ӡ����  �ٶ�������
 * ����ջ�ṹ���������ڵ�ѹ��ջ�У�Ȼ������ջ���Ƚ�������ص㣬ʵ�������ӡ��Ч��
 *  //��ת��ӡ����   ����ջ���Ƚ�����ص�  java�Դ���api  Stack
    public static void reversetPrint(HeroNode head) {
        if(head.getNext()==null ){
            return;
        }
 
        Stack stack=new Stack<HeroNode>();
        HeroNode  cur=head.getNext();
        while (cur!=null){
            stack.push(cur);
            cur=cur.getNext();
        }
 
        while (stack.size()>0){
            System.out.println("stack pop :"+ stack.pop());
        }
 
    }
 */

/**
 * Tips:
 *
 * @author �����
 * @version 2020��3��3��
 */
public class SingleLinkedListDemo {
 
 
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "Song", "JISHIYU");
        HeroNode hero2 = new HeroNode(2, "LU", "YUQILING");
        HeroNode hero3 = new HeroNode(3, "WU", "ZHIDUOXING");
        HeroNode hero4 = new HeroNode(4, "LINBG", "BAOZITOU");
 
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.list();
        singleLinkedList.update(new HeroNode(7,"XXXXX","XXXXXXXX"));
        singleLinkedList.list();
        singleLinkedList.delete(2);
        singleLinkedList.list();
 
    }
}
 
class SingleLinkedList {
    private HeroNode head = new HeroNode(0, " ", " ");
 
    //����ͷ�ڵ�
    public HeroNode getHead() {
        return head;
    }
 
    public void add(HeroNode node) {
 
        HeroNode temp = head;
 
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
 
            temp = temp.getNext();
        }
 
        temp.setNext(node);
    }
 
    public void delete(int id){
        HeroNode temp= head;
        boolean flag=false;
        if(temp.getNext()==null){
            System.out.println("linkedList  is  null ");
        }
 
        while (true){
            if(temp.getNext()==null){
                System.out.println("this node  not exist--delete");
                break;
            }
 
            if(temp.getNext().getId()==id){
                flag=true;
                break;
            }
            temp=temp.getNext();
 
        }
 
        if(flag){
            temp.setNext(temp.getNext().getNext());
        }
 
    }
 
    public void update(HeroNode  node){
           HeroNode temp= head;
           if(temp.getNext()==null){
               System.out.println("linkedList  is  null ");
           }
 
           while (true){
               if(temp==null){
                   System.out.println("this id  not exist");
                   break;
               }
 
               if(temp.getId()==node.getId()){
                   temp.setName(node.getName());
                   temp.setNikeName(node.getNikeName());
                   break;
               }
               temp=temp.getNext();
           }
 
    }
 
    public void list() {
        //�ж������Ƿ�Ϊ��
        if (head.getNext() == null) {
            System.out.println("����Ϊ��");
            return;
        }
 
        HeroNode temp = head.getNext();
 
        while (true) {
            if (temp == null) {
                System.out.println("��������");
                break;
            }
 
            System.out.println(temp.toString());
            temp = temp.getNext();
 
        }
 
    }
 /**
  * ����Ĳ���Ԫ��˳��(id����)
  * ��Ҫ����Ԫ�صı��˳�����
  * ˼·��
  * 1.�����ҵ�����ӵĽڵ��λ�ã���ͨ������������ָ�룩��ͨ���������㶨
  * 2.�µĽڵ�.next = temp.next
  * 3.temp.next = �µĽڵ�
  * @param node
  */
       
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                flag = true;
                break;
            }
 
            if (temp.getNext().getId() < node.getId()) {
                flag = true;
                break;
            }
            if(temp.getNext().getId() == node.getId()){
                break;
            }
            temp = temp.getNext();
        }
 
        if (flag) {
            //���뵽������, temp�ĺ���
            node.setNext(temp.getNext());
            temp.setNext(node);
        }
    }
    
}
 
class HeroNode {
 
    private int id;
    private String name;
    private String nikeName;
    private HeroNode next;
 
    public HeroNode(int id, String name, String nikeName) {
        this.id = id;
        this.name = name;
        this.nikeName = nikeName;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getNikeName() {
        return nikeName;
    }
 
    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }
 
    public HeroNode getNext() {
        return next;
    }
 
    public void setNext(HeroNode next) {
        this.next = next;
    }
 
 
    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nikeName='" + nikeName + '\'' +
                '}';
    }
}
 
