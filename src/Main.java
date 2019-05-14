import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello World! " + args[0]);

        int upperLimit = 4999;
        int lowerLimit = 0;
        int[] cylinders = new int[1000];
        int test[] = {2069,1212,2296,2800,544,1618,356,1523,4965,3681};

        fillArray(cylinders);

        System.out.println("the result is " + sstf(2150, test));
//        System.out.println("the result is " + fcfs(2150, test));

        // Ready Algorithms //
//        fcfs(Integer.parseInt(args[0]), cylinders);
//        sstf(Integer.parseInt(args[0]), test);
    }

    private static int sstf(int head, int [] arr){
        int sum = 0;
        int currentCylinder = head;

        for(int i = 0; i < arr.length; i ++){
            int closest = getclosestCylinder(currentCylinder, arr);
            System.out.println("closest: " + arr[closest]);
            sum += getDistance(currentCylinder,arr[closest]);
            currentCylinder = arr[closest];
            arr[closest] = 0;
        }
        System.out.println(sum);
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
            if(arr[i] != 0){
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
