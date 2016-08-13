package Q9_1;

import java.util.Stack;

/**
 * Stack with nodes that store max for elements below it
 */
public class StackWithMax extends Stack<NodeWithMax> {

    public void push(int value) {
        int newMax = Math.max(value, max());
        super.push(new NodeWithMax(value, newMax));
    }


    public int max() {
        if (this.isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            return peek().max;
        }
    }
}
