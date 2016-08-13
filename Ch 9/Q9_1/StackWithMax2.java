package Q9_1;

import java.util.Iterator;
import java.util.Stack;

/**
 * Stack that internally stores gap
 */
public class StackWithMax2 extends Stack<Integer> {

    private static int max;

    public StackWithMax2() {
        max = Integer.MIN_VALUE;
    }

    public Integer pop() {
        if (super.isEmpty()) {return null;}
        int val = super.pop();

        int trueVal = val - max;

        max = Math.min(max, max + val);
        System.out.println("inside pop");
        System.out.println(max + val);
        return val + max;
    }

    public void push(int val) {
        max = Math.max(val, max);
        super.push(val + max);
    }


    public int max() {
        if (this.isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            return max;
        }
    }

    @Override
    public String toString() {
        Iterator<Integer> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            Integer e = it.next() + max;
            sb.append(e);
//            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}
