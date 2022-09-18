package main.base;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class biTree {


    public static void main(String[] args) {


        print99();

//        class TreeNode{
//
//            public int data;
//            public TreeNode left;
//            public TreeNode right;
//
//        }
//
//        TreeNode treeNode = new TreeNode();

//        Map<Integer, Integer> map = new HashMap<>();



        int[] arr = new int[]{1,2,3,4,5,6,7};

        BITNode bitNode = insertArrayIntoTree(arr, 0, arr.length - 1);

        pre(bitNode);

        System.out.println();

        mid(bitNode);

        System.out.println();

        post(bitNode);

        System.out.println();

        preOrder(bitNode);


    }


    public static BITNode insertArrayIntoTree(int[] arr,int start,int end){

        BITNode root = null;

        if(end >= start){
            root = new BITNode();
            int mid = (start + end + 1) / 2;
            //
            root.setData(arr[mid]);
            //
            root.setLeft(insertArrayIntoTree(arr,start,mid-1));
            //
            root.setRight(insertArrayIntoTree(arr,mid+1,end));

        }


        return root;
    }

    public static void pre(BITNode bitNode){

        if(bitNode == null){
            return;
        }

        if (bitNode != null){

            System.out.print(bitNode.getData() + "\t");
            pre(bitNode.getLeft());
            pre(bitNode.getRight());

        }

    }

    public static void mid(BITNode bitNode){


        if(bitNode == null){
            return;
        }

        if(bitNode != null){

            pre(bitNode.getLeft());
            System.out.print(bitNode.getData() + "\t");
            pre(bitNode.getRight());

        }

    }

    public static void post(BITNode bitNode){


        if(bitNode == null){
            return;
        }

        if(bitNode != null){

            pre(bitNode.getLeft());
            pre(bitNode.getRight());
            System.out.print(bitNode.getData() + "\t");

        }

    }


    public static void print99(){

        for (int i = 1; i <= 9; i++) {

            for (int j = 1; j < 9; j++) {

                System.out.print( i + "*" + j + "=" + i*j+"\t");

            }

            System.out.println();


        }

    }

    /**
     * 非递归
     * @param root
     */
    public static void preOrder(BITNode root) {
        Stack<BITNode> stack = new Stack<>();
        BITNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                System.out.print(node.getData()+ "->");
                stack.push(node);
                node = node.getLeft();
            } else {
                BITNode tem = stack.pop();
                node = tem.getRight();
            }
        }
    }




}
