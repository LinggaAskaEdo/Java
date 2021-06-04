package testonline;

import java.util.ArrayList;

public class GFG
{
    private static final int ROW = 3;
    private static final int COLUMN = 4;

    public static boolean isInBounds(int i, int j)
    {
        return i >= 0 && i < ROW && j >= 0 && j < COLUMN;
    }

    // check if the postion is blocked
    public static boolean isBlocked(int[][] matrix, int i, int j)
    {
        if (!isInBounds(i, j))
            return true;

        return matrix[i][j] == -1;
    }

    // DFS code to traverse spirally
    private static void spirallyDFSTravserse(int[][] matrix, int i, int j, int dir, ArrayList<Integer> res)
    {
        if (isBlocked(matrix, i, j))
            return;

        boolean allBlocked = true;

        for (int k = -1; k <= 1; k += 2)
        {
            allBlocked = allBlocked && isBlocked(matrix, k + i, j) && isBlocked(matrix, i, j + k);
        }

        res.add(matrix[i][j]);
        matrix[i][j] = -1;

        if (allBlocked)
        {
            return;
        }

        // dir: 0 - right, 1 - down, 2 - left, 3 - up
        int nextI = i;
        int nextJ = j;
        int nextDir = dir;

        if (dir == 0)
        {
            if (!isBlocked(matrix, i, j + 1))
            {
                nextJ++;
            }
            else
            {
                nextDir = 1;
                nextI++;
            }
        }
        else if (dir == 1)
        {
            if (!isBlocked(matrix, i + 1, j))
            {
                nextI++;
            }
            else {
                nextDir = 2;
                nextJ--;
            }
        }
        else if (dir == 2)
        {
            if (!isBlocked(matrix, i, j - 1))
            {
                nextJ--;
            }
            else
            {
                nextDir = 3;
                nextI--;
            }
        }
        else if (dir == 3)
        {
            if (!isBlocked(matrix, i - 1, j))
            {
                nextI--;
            }
            else
            {
                nextDir = 0;
                nextJ++;
            }
        }

        spirallyDFSTravserse(matrix, nextI, nextJ, nextDir, res);
    }

    // to traverse spirally
    private static ArrayList<Integer> spirallyTraverse(int[][] matrix)
    {
        ArrayList<Integer> res = new ArrayList<>();
        spirallyDFSTravserse(matrix, 0, 0, 0, res);

        return res;
    }

    // Driver code
    public static void main (String[] args)
    {
        int[][] a = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };

        // Function Call
        ArrayList<Integer> res = spirallyTraverse(a);

        for (Integer re : res)
            System.out.print(re + " ");

        System.out.println();
    }
}