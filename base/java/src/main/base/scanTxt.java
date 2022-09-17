package main.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class scanTxt {
    public static void main(String[] args) {
        //读取文件
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new File("D://read.txt"));
//            while (scanner.hasNext()) {
//                System.out.println(scanner.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if (scanner != null) {
//                scanner.close();
//            }
//        }
        int[] arr = new int[]{4,9,5,1};

//        int[] maoPaoSort = maoPaoSort(arr);
//        for (int i : maoPaoSort){
//            System.out.println(i);
//        }

//        int[] selectSort = selectSort(arr);
//        for (int i : selectSort){
//            System.out.println(i);
//        }
        int[] insertSort = insertSort(arr);
        for (int i : insertSort){
            System.out.println(i);
        }
    }


    /**
     * 冒泡排序
     * @param arr
     * @return
     */
    public static int[] maoPaoSort(int[] arr){

        for (int i = 1; i < arr.length; i++) {

            boolean flag = true ;

            for (int j = 0; j < arr.length - i; j++) {

                if (arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;

                    flag = false;
                 }

            }

            if (flag){
                break;
            }


        }



        return arr;
    }

    /**
     * 选择排序
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr){

        for (int i = 0; i < arr.length; i++) {

            int minIndex = i;

            for (int j = i + 1 ; j < arr.length; j++) {
                if( arr[j] < arr[i] ){
                    minIndex = j;
                }
            }

            if (minIndex != i){

                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;

            }

        }




        return arr;
    }

    /**
     * 插入排序
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr){

        for (int i = 1; i < arr.length; i++) {

            int previousIndex = i -1 ;
            int current = arr[i];

            while (previousIndex >= 0 && current < arr[previousIndex]){

                arr[previousIndex + 1] = arr[previousIndex];

                previousIndex -= 1;

            }

            arr[previousIndex + 1] = current;


        }



        return arr;
    }

}
