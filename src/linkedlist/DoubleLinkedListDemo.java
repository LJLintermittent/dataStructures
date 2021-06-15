package linkedlist;
 
                                                                    //˫���������ɾ�Ĳ�
/**
 * �����������ȱ�����: �����������ҵķ���ֻ����һ�����򣬶�˫���� �������ǰ���������ҡ�
 * ��������������ɾ������Ҫ�������ڵ� ����˫�� �������������ɾ�������Ե�����ɾ�� ʱ�ڵ㣬�����ҵ�temp,temp�Ǵ�ɾ���ڵ��ǰһ ���ڵ�
 * 
 */

/**
 * Tips:
 *
 * @author �����
 * @version 2020��3��3��
 */
public class DoubleLinkedListDemo {
 
 
    public static void main(String [] args){
 
        // ����
        System.out.println("DoubleLinkedListDemo Test");
        // �ȴ����ڵ�
        HeroNode2 hero1 = new HeroNode2(1, "1", "11");
        HeroNode2 hero2 = new HeroNode2(2, "2", "22");
        HeroNode2 hero3 = new HeroNode2(3, "3", "33");
        HeroNode2 hero4 = new HeroNode2(4, "4", "44");
        // ����һ��˫������
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
 
        doubleLinkedList.list();
        doubleLinkedList.update(new HeroNode2(3,"33","333"));
        doubleLinkedList.list();
    }
}
 
// ����һ��˫���������
class DoubleLinkedList {
 
    // �ȳ�ʼ��һ��ͷ�ڵ�, ͷ�ڵ㲻Ҫ��, ����ž��������
    private HeroNode2 head = new HeroNode2(0, "", "");
 
    public HeroNode2 getHead() {
        return head;
    }
 
    public  void add(HeroNode2 node){
 
       HeroNode2 temp= head;
        while (true){
            //null ʱ  ָ������������
            if(temp.next==null){
                temp.next=node;
                node.pre=temp;
                node.next=null;
                break;
            }
            temp=temp.next;
        }
 
    }
    public  void dele(HeroNode2 node){
 
        HeroNode2 temp= head.next;
 
        if(temp==null){
            return;
        }
 
        while (true){
 
            if(temp==null){
                break;
            }
 
            if(temp.no==node.no){
                temp.pre.next=temp.next;
 
                //ע��������������һ���ڵ�  ����ᱨ��ָ��
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
            }
        }
 
    }
 
    public void update(HeroNode2 node){
 
        HeroNode2 temp= head.next;
 
        if(temp==null){
            return;
        }
 
        while (true){
            if(temp==null){
                break;
            }
 
            if(temp.no==node.no){
              //  node.next=temp.next;
             //   node.pre=temp.pre;
                temp.name = node.name;
                temp.nickname = node.nickname;
                break;
            }
            temp=temp.next;
        }
 
    }
 
    public  void list(){
 
        if(head.next==null){
            return;
        }
 
        HeroNode2 temp=head;
        while (temp!=null){
 
            if(temp.next==null){
                 break;
            }
 
            System.out.println(temp.next.toString());
            temp=temp.next;
        }
 
    }
 
}
 
// ����HeroNode2 �� ÿ��HeroNode �������һ���ڵ�
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // ָ����һ���ڵ�, Ĭ��Ϊnull
    public HeroNode2 pre; // ָ��ǰһ���ڵ�, Ĭ��Ϊnull
    // ������
 
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
 
    // Ϊ����ʾ��������toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
 
}
 