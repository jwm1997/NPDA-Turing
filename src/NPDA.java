import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NPDA {
    static int count = 0;//str的位置指针

    public boolean checkNPDA(String filename, String str) throws IOException {
        if (str == null) return false;
        //预处理 获取格里巴赫范式list 创建下推自动机的栈 str转为char数组
        List<CFG> list = new LinkedList<>();//存格里巴赫文法
        ToGNF changeToGreibach = new ToGNF();
        list = changeToGreibach.readRule(list, filename);
        System.out.println("Greibach：");
        //changeToGreibach.showRules(list);
        changeToGreibach.writeToConsole(list);
        System.out.println("-----------------------------------------------");
        System.out.println("输入字符串：" + str);
        Stack<Character> stk = new Stack<>();//下推自动机的栈
        Stack<Character> stk_copy;
        char[] input = str.toCharArray();

        //judge
        if (input.length == 0) {//如果输入为空，产生式中只有有S->$下推自动机才接受
            for (CFG node : list) {
                if (node.getFirst().equals("S") && node.getSecond().equals("$")) return true;
            }
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            count = 0;
            stk.clear();//回退栈
            //System.out.println(stk);
            CFG node = list.get(i);
            char left = node.getFirst().charAt(0);
            char[] right = node.getSecond().toCharArray();
            if (left == 'S' && right[0] == input[count]) {//从S开头的产生式开始，输入字符和产生式右部第一个字符相同
                count++;
                for (int j = right.length - 1; j > 0; j--) stk.add(right[j]);//把产生式右部剩下的字符入栈
                stk_copy = (Stack<Character>) stk.clone();//复制到克隆栈，至此处理完第一个字符，产生式第一条利用完成
                //递归判断是否能推出
                if (judge(list, stk_copy, input)) return true;//递归判断后面的所有字符是否匹配
                else count--;//回退字符
            }
        }
        //System.out.println("未找到~");
        return false;
    }

    public boolean judge(List<CFG> list, Stack<Character> stk, char[] input) {
        if (stk.isEmpty()) {
            if (count == input.length) return true;//栈空且字符指针移到末尾
            else return false;//栈空但是还有字符
        } else {
            if (count == input.length) return false;//没有字符了但是栈不为空
        }

        Stack<Character> stk_copy;
        for (int i = 0; i < list.size(); i++) {
            //还原栈
            stk_copy = (Stack<Character>) stk.clone();//回退栈，恢复栈没有匹配产生式的状态
            CFG node = list.get(i);
            char left = node.getFirst().charAt(0);
            char[] right = node.getSecond().toCharArray();
            if (left == stk_copy.peek() && right[0] == input[count]) {//栈顶符号和某条产生式左部变元符合，同时右部第一个终结符与输入字符匹配
                stk_copy.pop();//出栈
                count++;//输入字符指针后移
                for (int j = right.length - 1; j > 0; j--) stk_copy.add(right[j]);//把匹配的产生式右部剩余的字符入栈
                if (judge(list, stk_copy, input)) return true;//递归调用判断后面的所有字符是否匹配
                else count--;//，该条产生式不符合，回退该输入字符，指针恢复，下面尝试另一条栈顶符号为左部变元的产生式
            }
        }
        return false;//没有一条产生式可以使得匹配进行下去，失败
    }

    public static void main(String[] args) throws IOException {
        NPDA chkNPDA = new NPDA();
        if (chkNPDA.checkNPDA("./Data/outputTest.txt", "aaaaacaaabcccccd")) {
            System.out.println("是否接受：YES");
        } else {
            System.out.println("是否接受：NO");
        }
    }
}

