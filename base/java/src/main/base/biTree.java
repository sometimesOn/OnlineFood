package main.base;


public class biTree {


    public static void main(String[] args) {


        print99();


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


    public static void print99(){

        for (int i = 1; i <= 9; i++) {

            for (int j = 1; j < 9; j++) {

                System.out.print( i + "*" + j + "=" + i*j+"\t");

            }

            System.out.println();


        }

    }


}
