import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        int upperLimit = 4999;
        int lowerLimit = 0;
        int[] cylinders = new int[1000];
        int test[] = {2069,1212,2296,2800,544,1618,356,1523,4965,3681};

        fillArray(cylinders);

        System.out.println("FCFS: " + fcfs(2150, test));
        System.out.println("SSTF: " + sstf(2150, test));
        System.out.println("LOOK: " + look(2150, test, false ,false, false));
        System.out.println("CLOOK: " + look(2150, test, false,true, false));



        // Ready Algorithms //
//        fcfs(Integer.parseInt(args[0]), cylinders);
//        sstf(Integer.parseInt(args[0]), test);
    }



    private static int look(int head, int [] arr,boolean goLeft, boolean isC, boolean isScan){

        int sum = 0;
        int tempArr[] = Arrays.copyOf(arr,arr.length); //make a copy of an array (by value)
        Arrays.sort(tempArr); // sort if to make algorithm easier
        int indexOfClosest = getclosestCylinder(head,tempArr); // get the first closest item to determine direction
        int leftArr[] = {}; // Left part of array from head
        int rightArr[] = {}; // Right part of array from head


        sum += getDistance(head, tempArr[indexOfClosest]); // get the distance to the first object from head
        //this will fill in arrays
        if(goLeft){
            leftArr = Arrays.copyOfRange(tempArr, 0, indexOfClosest);
            rightArr = Arrays.copyOfRange(tempArr, indexOfClosest + 1, tempArr.length);
        } else if (!goLeft){
            rightArr = Arrays.copyOfRange(tempArr, indexOfClosest, tempArr.length);
            leftArr = Arrays.copyOfRange(tempArr, 0 , indexOfClosest -1);
        }

        // left array has to be reversed to reuse fcfs algorithm
        // also decide here if we will reverse the arr for LOOK or not for CLOOK
        if (!isC){
            for(int i = 0; i < leftArr.length / 2; i++)
            {
                int temp = leftArr[i];
                leftArr[i] = leftArr[leftArr.length - i - 1];
                leftArr[leftArr.length - i - 1] = temp;
            }
        }

        //this is where magic happens
        if(goLeft){
            sum += fcfs(indexOfClosest,leftArr);
            sum += fcfs(leftArr[leftArr.length-1],rightArr);
        }else{
//            sum += fcfs(indexOfClosest,rightArr);
            System.out.println("I am looking for those values ");
            sum += fcfs(tempArr[indexOfClosest],rightArr);
//            sum += fcfs(leftArr[leftArr.length-1],leftArr);
            sum += fcfs(rightArr[rightArr.length-1],leftArr);
//            sum += getDistance(head,rightArr[0]);

//            sum += getDistance(rightArr[rightArr.length-1], leftArr[0]);
        }


        return sum;
    }

    private static int sstf(int head, int [] arr){
        int tempArr[] = Arrays.copyOf(arr,arr.length);
        int sum = 0;
        int currentCylinder = head;

        for(int i = 0; i < tempArr.length; i ++){
            int closest = getclosestCylinder(currentCylinder, tempArr);
            sum += getDistance(currentCylinder,tempArr[closest]);
            currentCylinder = tempArr[closest];
            tempArr[closest] = 9999;
        }

        return sum;
    }

    private static int fcfs(int head, int [] arr){
        int sum = Math.abs(head - arr[0]);

        for(int i = 0; i < arr.length -1; i ++){
            sum += getDistance(arr[i], arr[i+1]);
        }
        return sum;
    }

    private static void fillArray(int [] arr){
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random()*4999);
        }
    }

    private static int getclosestCylinder(int beggining, int [] arr){
        int closest = 5000;
        int cylinderLocation = 0;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 9999){
                int currentDistance = getDistance(beggining, arr[i]);
                if(currentDistance < closest){
                    closest = currentDistance;
                    cylinderLocation = i;
                }
            }
        }
        return cylinderLocation;
    }

    private static int getDistance(int a, int b){
        System.out.println("val a " + a);
        System.out.println("val b " + b);
        System.out.println(Math.abs(a-b));
        return Math.abs(a-b);
    }

}
