package testonline;

public class TestUmma
{
    private static final int ROW = 3;
    private static final int COLUMN = 4;

    public static void main (String[] args)
    {
        int[][] arr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };

        // Function Call
        print(arr, 0, 0, ROW, COLUMN);
    }

    private static void print(int[][] arr, int i, int j, int r, int c)
    {
        System.out.print("\ni:" + i + "\n");

        // If i or j lies outside the matrix
        if (i >= r || j >= c)
        {
            return;
        }

        // Print First Row
        for (int p = i; p < c; p++)
        {
            System.out.print("a" + arr[i][p] + " ");
        }

        // Print Last Column
        for (int p = i + 1; p < r; p++)
        {
            System.out.print("b" + arr[p][c - 1] + " ");
        }

        // Print Last Row, if Last and
        // First Row are not same
        if ((r - 1) != i)
        {
            for (int p = c - 2; p >= j; p--)
            {
                System.out.print("c" + arr[r - 1][p] + " ");
            }
        }

        // Print First Column, if Last and
        // First Column are not same
        if ((c - 1) != j)
        {
            for (int p = r - 2; p > i; p--)
            {
                System.out.print("d" + arr[p][j] + " ");
            }
        }

        print(arr, i + 1, j + 1, r - 1, c - 1);
    }
}