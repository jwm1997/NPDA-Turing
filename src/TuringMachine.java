public class TuringMachine {
    //计算x的y次方
    public void computeYPowerOfX(int x, int y) {
        //初始化图灵带为y0x010
        Turing s = new Turing();
        if (y != 0) {
            s.setData('1');
            //tail为尾节点
        }
        Turing tail = s;
        for (int i = 0; i < y - 1; i++) {//构造双向链表，y个1
            Turing p = new Turing();
            p.setData('1');
            p.pre = tail;
            tail.next = p;
            tail = p;
        }

        Turing p = new Turing();
        p.setData('0');
        p.pre = tail;
        tail.next = p;
        tail = p;

        for (int i = 0; i < x; i++) {//双向链表，x个1
            p = new Turing();
            p.setData('1');
            p.pre = tail;
            tail.next = p;
            tail = p;
        }

        p = new Turing();
        p.setData('0');
        p.pre = tail;
        tail.next = p;
        tail = p;

        p = new Turing();
        p.setData('1');
        p.pre = tail;
        tail.next = p;
        tail = p;

        p = new Turing();
        p.setData('0');
        p.pre = tail;
        tail.next = p;
        tail = p;
        //初始化结束
        //移动指针q，从y开始移动
        Turing q = s;
        //状态转移
        int state = 0;
        while (state != 18) {
            switch (state) {
                case 0:
                    if (q.data == '0') {
                        state = 17;
                        q = q.pre;
                    } else if (q.data == '1') {
                        state = 1;
                        q.setData('a');
                        q = q.next;

                    } else state = 18;
                    break;
                case 1:
                    if (q.data == '0') {
                        state = 2;
                        q = q.next;
                    } else if (q.data == '1') {
                        state = 1;
                        q = q.next;
                    } else state = 18;
                    break;
                case 2:
                    if (q.data == '0') {
                        state = 10;
                        q = q.pre;
                    } else if (q.data == '1') {
                        state = 3;
                        q.setData('b');
                        q = q.next;
                    } else state = 18;
                    break;
                case 3:
                    if (q.data == '0') {
                        state = 4;
                        q = q.next;
                    } else if (q.data == '1') {
                        state = 3;
                        q = q.next;
                    } else state = 18;
                    break;
                case 4:
                    if (q.data == '0') {
                        state = 9;
                        q = q.pre;
                    } else if (q.data == '1') {
                        state = 5;
                        q.setData('c');
                        q = q.next;
                    } else state = 18;
                    break;
                case 5:
                    if (q.data == '0') {
                        state = 6;
                        q = q.next;
                    } else if (q.data == '1') {
                        state = 5;
                        q = q.next;
                    } else state = 18;
                    break;
                //??
                case 6:
                    if (q == null) {
                        p = new Turing();
                        p.setData('1');
                        p.pre = tail;
                        p.next = null;
                        tail.next = p;
                        tail = p;
                        q = p;
                        state = 7;
                        q = q.next;
                    } else if (q.data == '1') {
                        state = 6;
                        q = q.next;
                    } else if (q.data == '0') {
                        state = 7;
                        q.setData('1');
                        q = q.next;
                    } else state = 18;
                    break;
                case 7:
                    if (q == null) {
                        p = new Turing();
                        p.setData('0');
                        p.pre = tail;
                        p.next = null;
                        tail.next = p;
                        tail = p;
                        state = 8;
                        q = tail.pre;
                    } else state = 18;
                    break;
                case 8:
                    if (q.data == '0') {
                        state = 8;
                        q = q.pre;
                    } else if (q.data == '1') {
                        state = 8;
                        q = q.pre;
                    } else if (q.data == 'c') {
                        state = 4;
                        q = q.next;
                    } else state = 18;
                    break;
                case 9:
                    if (q.data == '0') {
                        state = 9;
                        q = q.pre;
                    } else if (q.data == '1') {
                        state = 9;
                        q = q.pre;
                    } else if (q.data == 'b') {
                        state = 2;
                        q = q.next;
                    } else if (q.data == 'c') {
                        state = 9;
                        q.setData('1');
                        q = q.pre;
                    } else state = 18;
                    break;
                case 10:
                    if (q.data == '0') {
                        state = 20;
                        q = q.next;
                    } else if (q.data == 'b') {
                        state = 19;
                        q.setData('1');
                        q = q.pre;
                    } else state = 18;
                    break;
                case 11:
                    if (q.data == '0') {
                        state = 12;
                        q = q.next;
                    } else if (q.data == '1') {
                        state = 11;
                        q = q.next;
                    } else state = 18;
                    break;
                case 12:
                    if (q.data == '0') {
                        state = 13;
                        q.setData('1');
                        q = q.next;
                    } else if (q.data == '1') {
                        state = 12;
                        q.setData('d');
                        q = q.next;
                    } else state = 18;
                    break;
                //???
                case 13:
                    if (q.data == '0') {
                        state = 14;
                        q = q.pre;
                        tail = tail.pre;
                        tail.next = null;
                    } else if (q.data == '1') {
                        state = 13;
                        q = q.next;
                    } else state = 18;
                    break;
                case 14:
                    if (q.data == '1') {
                        state = 15;
                        q.setData('0');
                        q = q.pre;
                    } else state = 18;
                    break;
                case 15:
                    if (q.data == '0') {
                        state = 16;
                        q = q.pre;
                    } else if (q.data == '1') {
                        state = 15;
                        q = q.pre;
                    } else if (q.data == 'd') {
                        state = 13;
                        q.setData('1');
                        q = q.next;
                    } else state = 18;
                    break;
                case 16:
                    if (q.data == '0') {
                        state = 16;
                        q = q.pre;
                    } else if (q.data == '1') {
                        state = 16;
                        q = q.pre;
                    } else if (q.data == 'a') {
                        state = 0;
                        //q.setData('1');
                        q = q.next;
                    } else state = 18;
                    break;
                case 17:
                    if (q == null) {
                        state = 18;
                        q = s;
                    } else if (q.data == 'a') {
                        state = 17;
                        q.setData('1');
                        q = q.pre;
                    } else state = 18;
                    break;
                case 19:
                    if (q.data == '0') {
                        state = 11;
                        q = q.next;
                    } else if (q.data == 'b') {
                        state = 19;
                        q.setData('1');
                        q = q.pre;
                    } else state = 18;
                    break;
                case 20:
                    if (q.data == '0') {
                        state = 20;
                        q = q.next;
                    } else if (q.data == '1') {
                        state = 18;
                        q.setData('0');
                        q = q.next;
                    } else state = 18;
                    break;
                default:
                    break;
            }
            Turing temp = s;
            while (temp != null) {
                System.out.print(temp.data);
                temp = temp.next;
            }
            System.out.println(" ");
        }
        int ans = 0;
        Turing count = tail.pre;
        while (count.data != '0') {
            ans++;
            count = count.pre;
        }
        System.out.println("x = " + x +"\n" + "y = " + y +"\n" +  "ans = " + ans);
    }

    public static void main(String[] args) {
        TuringMachine tm = new TuringMachine();
        tm.computeYPowerOfX(2, 3);
    }
}

