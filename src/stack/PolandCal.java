package stack;



import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器(结合中缀表达式转后缀表达�?),这里以（3+4）�?5-6为例
 * @author 李佳�?  2020 3.12
 * 
 */
/**
 * 中缀表达式转后缀表达式：
1）初始化两个栈，�?个用来存放运算符（S1），�?个用来存放中间结果（数栈S2），接着从左至右�?始扫描表达式
2）遇到操作数，直接入S2栈�?�遇到运算符，如果S1为空或�?�栈顶运算符为左括号，则直接入栈，否则，将其与S1栈顶的运算符优先级作比较
 �??2.1）如果当前扫描到的运算符优先级大于栈顶的运算符，直接入栈
 �??2.2）如果当前扫描到的运算符优先级小于等于栈顶的运算符，将S1中的运算符出栈，然后入S2栈中。接�?再将当前扫描到的运算符与下一个S1栈顶的运算符进行优先级的比较，重复上诉步骤，直到当前的运算符入了S1�?
 �??2.2）如果扫描到的是右括号，那么将S1中的运算符依次弹出入S2栈，直到弹出到一个左括号为止�?
3）当中缀表达式扫描完之后，将S1中的运算符依次出栈后入S1�?
4）将S1栈中的元素，顺序出栈后的倒序即为后缀表达�?
*/

public class PolandCal {
    public static void main(String[] args) {
        //先人为的给一个中�?表达�?
        String middle_formula = "10+((2+3)*4)-5";//答案应该是：25
        /**
         * 我们先将表达式进行分割，并将每一个字符放入ArrayList中，然后再遍历ArrayList，用栈进行计�?
         */
        List<String> splitlist = SplitArrayList(middle_formula);//分割中缀表达�?
        System.out.println("分割好的中缀表达�?" + splitlist);
        List<String> back_formula = changeformula(splitlist);//中缀表达式转后缀表达�?
        System.out.println("转化的后�?表达�?" + back_formula);
        int result = against_cal(back_formula);
        System.out.println("计算机计算的结果为：" + result);
    }

    /**
     * 中缀表达式转后缀表达�?
     */
    public static List<String> changeformula(List<String> splitlist) {
        //创建�?个Stack和一个ArrayList
        Stack<String> s = new Stack();
        List<String> ls = new ArrayList<>();
        //�?始进行转�?
        for (String item : splitlist) {//遍历传进来的分解好的中缀表达�?
            if (item.matches("\\d+")) {
                //如果匹配的是多位数，直接加入ArrayList
                ls.add(item);
            } else if (item.equals("(")) {
                //如果是左括号，直接入�?
                s.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，那么将栈中运算符出栈放到ArrayList中，直到出现左括号）为止
                while (!s.peek().equals("(")) {
                    ls.add(s.pop());//出栈符号加入ArrayList�?
                }
                s.pop();//将左括号（丢�?
            } else if (s.size() == 0 || s.peek().equals("(")) {
                //如果是运算符，且stack为空或�?�栈顶元素为左括�?,直接入栈
                s.push(item);
            } else if (priority(item) > priority(s.peek())) {
                //如果是运算符，且stack不为为空,且该运算符的优先级大于栈顶运算符的优先级，直接入�?
                s.push(item);
            } else if (priority(item) <= priority(s.peek())) {
                //如果是运算符，且stack不为为空,且该运算符的优先级小于等于栈顶运算符的优先级，则将栈中优先级大于该运算符的依次出栈加入到ArrayList�?
                while (s.size() > 0 && priority(item) <= priority(s.peek())) {
                    ls.add(s.pop());
                }
                s.push(item);//�?后记得这个运算符还要入栈
            }
        }
        //遍历完了之后，千万要记得将栈中剩余的运算符出栈到ArrayList�?
        while(s.size() != 0){
            ls.add(s.pop());
        }
        return ls;
    }

    /**
     * 用于分割字符串，将每�?个字符放入ArrayList�?
     */
    public static List<String> SplitArrayList(String middle_formula) {
        String str = "";//用于拼接多位�?
        char c = ' ';//用于存放分割的字�?
        List<String> list = new ArrayList<>();

        for (int i = 0; i < middle_formula.length(); i++) {
            c = middle_formula.charAt(i);//获取middle_formula中的第i个字�?
            if (c < 48 || c > 57 || i + 1 >= middle_formula.length()) {//如果获得的字符不为数�?,直接加入list�?;或�?�如果为数字且为�?后一个数字的话也直接加入list
                //多位数加入list
                if (!str.equals("")) {
                    list.add(str);
                }
                list.add(c + "");
                str = "";//每次不是多位数加入的时�?�，�?要对str做一个清空，以便下一个多位数继续从头�?始拼�?
            } else if (i + 1 < middle_formula.length() && (middle_formula.charAt(i + 1) <= 48 || middle_formula.charAt(i + 1) >= 57)) {
                str = str + c;//多位数的数字拼接
            }
        }

        return list;//�?后将分割好的List返回回去
    }

    /**
     * 用栈实现的�?�波兰的计算方法
     */
    public static int against_cal(List<String> splitlist) {
        Stack<String> stack = new Stack<>();
        //�?始遍历分割好的list
        for (String c : splitlist) {
            if (c.matches("\\d+")) {//用正则表达式匹配多位�?
                //如果是数字，直接入栈
                stack.push(c);
            } else {
                //如果是运算符,则出栈两个数字后进行计算，再将结果入�?
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int rs = 0;
                switch (c) {
                    case "+":
                        rs = num1 + num2;
                        break;
                    case "-":
                        rs = num2 - num1;//注意减数和被减数的顺�?
                        break;
                    case "*":
                        rs = num1 * num2;
                        break;
                    case "/":
                        rs = num2 / num1;//注意除数和被除数的顺�?
                        break;
                    default:
                        throw new RuntimeException("运算符有�?");
                }
                //结果入栈
                stack.push(rs + "");
            }
        }
        //取最终结�?
        return Integer.parseInt(stack.pop());
    }

    /**
     * 用于比较运算符优先级的方�?
     */
    public static int priority(String item) {
        int result;
        switch (item) {
            case "+":
                result = 1;
                break;
            case "-":
                result = 1;
                break;
            case "*":
                result = 2;
                break;
            case "/":
                result = 2;
                break;
            default:
                throw new RuntimeException("不存在该运算�?");
        }
        return result;
    }

}











