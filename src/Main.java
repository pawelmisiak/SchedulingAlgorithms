import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        int upperLimit = 4999;
        int lowerLimit = 0;
        int[] cylinders = new int[1000];
        int test[] = {2069,1212,2296,2800,544,1618,356,1523,4965,3681};

        fillArray(cylinders);

        System.out.println("SSTF: " + sstf(2150, test));
        System.out.println("FCFS: " + fcfs(2150, test));
        System.out.println("LOOK: " + look(2150, test));



        // Ready Algorithms //
//        fcfs(Integer.parseInt(args[0]), cylinders);
//        sstf(Integer.parseInt(args[0]), test);
    }

    private static int look(int head, int [] arr){

        int sum = 0;
        int tempArr[] = arr; //make a copy of an array
        Arrays.sort(tempArr); // sort if to make algorithm easier
        int indexOfClosest = getclosestCylinder(head,tempArr); // get the first closest item to determine direction
        int leftArr[] = {}; // Left part of array from head
        int rightArr[] = {}; // Right part of array from head
        boolean goLeft = true; // To make decision which way to go

        sum += getDistance(head, tempArr[indexOfClosest]); // get the distance to the first object from head

        //this will fill in arrays
        if(tempArr[indexOfClosest] <= head){
            leftArr = Arrays.copyOfRange(tempArr, 0, indexOfClosest);
            rightArr = Arrays.copyOfRange(tempArr, indexOfClosest + 1, tempArr.length);
        } else if (tempArr[indexOfClosest] > head){
            goLeft = false;
            rightArr = Arrays.copyOfRange(tempArr, indexOfClosest, tempArr.length);
            leftArr = Arrays.copyOfRange(tempArr, 0 , indexOfClosest -1);
        }

        // left array has to be reversed to reuse fcfs algorithm
        for(int i = 0; i < leftArr.length / 2; i++)
        {
            int temp = leftArr[i];
            leftArr[i] = leftArr[leftArr.length - i - 1];
            leftArr[leftArr.length - i - 1] = temp;
        }

        //this is where magic happens
        if(goLeft){
            sum += fcfs(indexOfClosest,leftArr);
            sum += fcfs(leftArr[leftArr.length-1],rightArr);
        }else{
            sum += fcfs(indexOfClosest,rightArr);
            sum += fcfs(leftArr[rightArr.length-1],leftArr);
        }

        return sum;
    }

    private static int sstf(int head, int [] arr){
        int tempArr[] = arr;
        int sum = 0;
        int currentCylinder = head;

        for(int i = 0; i < tempArr.length; i ++){
            int closest = getclosestCylinder(currentCylinder, tempArr);
            System.out.println("closest: " + tempArr[closest]);
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
        System.out.println(sum);
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
        return Math.abs(a-b);
    }

}
