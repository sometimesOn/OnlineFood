package main.base;

public class BITNode {

    private BITNode left;
    private int data;
    private BITNode right;


    public BITNode() {
    }

    public BITNode(BITNode left, int data, BITNode right) {
        this.left = left;
        this.data = data;
        this.right = right;
    }

    public BITNode getLeft() {
        return left;
    }

    public void setLeft(BITNode left) {
        this.left = left;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BITNode getRight() {
        return right;
    }

    public void setRight(BITNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BITNode{" +
                "left=" + left +
                ", data=" + data +
                ", right=" + right +
                '}';
    }


}
