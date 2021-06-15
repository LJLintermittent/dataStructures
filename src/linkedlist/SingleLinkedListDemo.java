package linkedlist;

/**
 * 使用带head头的单向链表实现--水浒英雄排行榜管理--完成对人物的增删改查操作
 * 
 */
// 1.先创建一个head 头结点，作用是表示单链表的头
// 2.后面每添加一个节点，就直接加入到链表的最后
// 3.通过一个辅助变量遍历，帮助遍历整个链表
//从单链表中删除一个节点的思路
//temp.next = temp.next.next;
// 被删除的节点将不会有其他引用指向，会被垃圾回收机制回收

/**
 *                                                         面试题：求单链表中的有效节点个数
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
 *                                                      查找单链表中的倒数第k个结点 【新浪面试题】
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
 *                                                              单链表的反转  腾讯面试题
 *               思路：
 *               先定义一个节点：reversehead = new HeroNode；
 *               从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reversedHead 的最前端
 *               原来的链表：head.next = reversedHead.next；
 *  //将单链表反转
    public static void reversetList(HeroNode head) {
 
        if(head.getNext()==null || head.getNext().getNext()==null){
                    return;
        }
 
        //相当于一个临时的链表
        HeroNode  reversetHead=new HeroNode(0," ","");
        HeroNode  cur =head.getNext();
        //临时的节点  存储cur的状态
        HeroNode  next =null;
 
        while (cur!=null){
            next=cur.getNext();
            //相当于把 cur.next节点 改成了reversetHead（头结点的下一个节点）的下一个节点
            //是因为现在cur节点还是目标链表的元素
            cur.setNext(reversetHead.getNext());
            //上一行代码的赋值
            reversetHead.setNext(cur);
            cur = next;//让cur后移
 
        }
        //反转完成后 赋值给目标节点
      //  head=reversetHead;  为什么这样不行
        head.setNext(reversetHead.getNext());
    }
 */

/**                                                      从头到尾打印链表  百度面试题
 * 利用栈结构，将各个节点压入栈中，然后利用栈的先进后出的特点，实现逆序打印的效果
 *  //反转打印链表   利用栈的先进后出特点  java自带的api  Stack
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
 * @author 李佳乐
 * @version 2020年3月3日
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
 
    //返回头节点
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
        //判断链表是否为空
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
 
        HeroNode temp = head.getNext();
 
        while (true) {
            if (temp == null) {
                System.out.println("遍历结束");
                break;
            }
 
            System.out.println(temp.toString());
            temp = temp.getNext();
 
        }
 
    }
 /**
  * 有序的插入元素顺序(id排序)
  * 需要按照元素的编号顺序添加
  * 思路：
  * 1.首先找到新添加的节点的位置，是通过辅助变量（指针），通过遍历来搞定
  * 2.新的节点.next = temp.next
  * 3.temp.next = 新的节点
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
            //插入到链表中, temp的后面
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
 
