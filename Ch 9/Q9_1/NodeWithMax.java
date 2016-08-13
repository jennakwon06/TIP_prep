package Q9_1;

class NodeWithMax {
    public int value;
    public int max;

    public NodeWithMax(int v, int max){
        this.value = v;
        this.max = max;
    }

    public String toString() {
        return "(" + value + "," + max + ")";
    }
}
