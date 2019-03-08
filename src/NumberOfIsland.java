public class NumberOfIsland {

    static int getNumberOfIslands(int[][] binaryMatrix) {
        // your code goes here
        boolean[][] visited = new boolean[binaryMatrix.length][binaryMatrix[0].length];
        int counter = 0;
        for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < binaryMatrix[i].length; j++) {
                if (binaryMatrix[i][j] == 1 && visited[i][j] == false) {
                    counter++;
                    dfs(binaryMatrix, visited, i, j);
                }
            }
        }
        return counter;
    }
    static void dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return;
        }
        if (matrix[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs(matrix, visited, i - 1, j);
        dfs(matrix, visited, i + 1, j);
        dfs(matrix, visited, i, j - 1);
        dfs(matrix, visited, i, j + 1);

    }

    public static void main(String[] args) {

    }


}
