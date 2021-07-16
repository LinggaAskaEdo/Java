package testonline;

public class TestHciFajrIllahi
{
    public static void main(String[] args)
    {
        printer(1);
        printer(5);

        int [][] arr = new int[][] {{7, 3, 4}, {8, 6, 9},{1, 2, 5}};
        int [][] arr1 = {{9, 3, 5, 4, 8}, {5, 1, 4, 2, 7},{6, 5, 7, 9, 1}};
        System.out.println("The case#1 return is " + lowestCost(arr) + "\n");
        System.out.println("The case#2 return is " + lowestCost(arr1) + "\n");
    }

    public static void printer(int initValue) {
        int n = 9, mid = initValue + 4, limit = initValue +1;

        for(int i = initValue; i < n+1+initValue; i++) {

            if(i < mid) {
                for(int j = i ; j < limit; j++) {
                    System.out.print(j);
                }
                limit+=2;
            }
            else if(i >= mid) {
                int limit2 = limit;
                for(int j = i; j < limit2; j+=1) {
                    System.out.print(j);
                }
                //limit2-=1;
            }
            System.out.println();
        }
    }

    public static int lowestCost(int[][] arr) {

        int pi = (arr.length -1) / 2,//1
                pj = (arr[0].length -1) / 2,//1
                sumLeft = 0,
                sumUp = 0,
                sumRight = 0,
                sumDown = 0;

        while(pj >= 0) {
            sumLeft += arr[pi][pj];
            sumRight += arr[pi][arr[0].length - 1 - pj];
            pj--;
        }
        int min1 = Math.min(sumLeft, sumRight);
        // System.out.println("pj is "+ pj);
        pj = (arr[0].length -1) / 2;//1
        while(pi >= 0) {
            sumUp += arr[pi][pj];
            sumDown += arr[arr.length - 1 - pi][pj];
            pi--;
        }
        int min2 = Math.min(sumUp, sumDown);



        System.out.println("\n" + "sumLeft is " + sumLeft);
        System.out.println("sumUp is " + sumUp);
        System.out.println("sumRight is " + sumRight);
        System.out.println("sumDown is " + sumDown);
        System.out.println("min1 is " + min1 + " min2 is " + min2);

        return Math.min(min1, min2);
    }
}
