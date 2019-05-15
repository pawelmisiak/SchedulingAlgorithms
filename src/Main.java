import java.util.Arrays;


public class Main {

    public static void main(String[] args) {


        int[] cylinders = new int[1000];

        fillArray(cylinders);

        System.out.println("FCFS: " + fcfs(2150, cylinders));
        System.out.println("SSTF: " + sstf(2150, cylinders));
        System.out.println("SCAN to right: " + lookAndScan(2150, cylinders, false ,false, true));
        System.out.println("SCAN to left: " + lookAndScan(2150, cylinders, true ,false, true));
        System.out.println("C-SCAN to right: " + lookAndScan(2150, cylinders, false ,true, true));
        System.out.println("C-SCAN to left: " + lookAndScan(2150, cylinders, true ,true, true));
        System.out.println("LOOK to right: " + lookAndScan(2150, cylinders, false ,false, false));
        System.out.println("LOOK to left: " + lookAndScan(2150, cylinders, true ,false, false)); // going left
        System.out.println("C-LOOK to right: " + lookAndScan(2150, cylinders, false,true, false));
        System.out.println("C-LOOK to left: " + lookAndScan(2150, cylinders, true,true, false)); //going left



        // Ready Algorithms //
        fcfs(Integer.parseInt(args[0]), cylinders);
//        sstf(Integer.parseInt(args[0]), test);
    }



    private static int lookAndScan(int head, int [] arr,boolean goLeft, boolean isC, boolean isScan){

        int sum = 0;
        int tempArr[] = Arrays.copyOf(arr,arr.length); //make a copy of an array (by value)
        Arrays.sort(tempArr); // sort if to make algorithm easier
        int indexOfClosest = getclosestCylinder(head,tempArr); // get the first closest item to determine direction
        int leftArr[] = {}; // Left part of array from head
        int rightArr[] = {}; // Right part of array from head

        //this will fill in arrays

        rightArr = Arrays.copyOfRange(tempArr, indexOfClosest+1, tempArr.length);
        leftArr = Arrays.copyOfRange(tempArr, 0 , indexOfClosest +1);


        // left array has to be reversed to reuse fcfs algorithm
        // also decide here if we will reverse the arr for LOOK or not for CLOOK

        if(!isC){
            for(int i = 0; i < leftArr.length / 2; i++)
            {
                int temp = leftArr[i];
                leftArr[i] = leftArr[leftArr.length - i - 1];
                leftArr[leftArr.length - i - 1] = temp;
            }
        }

        if (isScan){
            if(goLeft){ // Going left
                sum += getDistance(head, 0);
                if (isC){
                    sum += getDistance(4999, rightArr[0]);
                }else{
                    sum += getDistance(0, rightArr[rightArr.length-1]);
                }
            }else{ // Going right
                sum += getDistance(head, 4999);
                if (isC){
                    sum += getDistance(0, leftArr[leftArr.length-1]);
                }else{
                    sum += getDistance(4999, leftArr[leftArr.length-1]);
                }
            }

        }else {

        if(goLeft){ // Going left
            sum += getDistance(head, leftArr[leftArr.length-1]);
            sum += getDistance(leftArr[leftArr.length-1], rightArr[rightArr.length-1]);
        }else{ // Going right
            sum += getDistance(head, rightArr[rightArr.length-1]);
            sum += getDistance(rightArr[rightArr.length-1], leftArr[leftArr.length-1]);
        }
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
        return Math.abs(a-b);
    }

}
