import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private int[][] grid;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        // -1 represent blocked,0 represent open,1 represent full.
        if(N <= 0)
            throw new IllegalArgumentException("N must be bigger than 0.");

        grid = new int[N][N];
        for(int r = 0;r < N;r++){
            for(int c = 0;c < N;c++){
                grid[r][c] = -1;
            }
        }

        uf = new WeightedQuickUnionUF(N*N);
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if(row<0 || row>=grid.length || col<0 || col>=grid.length)
            throw new IndexOutOfBoundsException();

        if(grid[row][col] == -1) {
            grid[row][col] = 0;
            fill_full(row,col);
            if(grid[row][col] == 1)
                make_full(row,col);
        }

        if(row == 0){
            grid[row][col] = 1;
            make_full(row, col);
        }

    }

    // When a site is open,check if it can be filled full.
    public void fill_full(int row,int col){
        assert grid[row][col] == 0;

        if(grid[Math.max(row-1,0)][col] == 1)
            grid[row][col] = 1;
        else if(grid[row][Math.max(col-1,0)] == 1)
            grid[row][col] = 1;
        else if(grid[Math.min(row+1,grid.length-1)][col] == 1)
            grid[row][col] = 1;
        else if(grid[row][Math.min(col+1,grid.length-1)] == 1)
            grid[row][col] = 1;
    }

    // When a site is full,check if it can make near sites full.
    public void make_full(int row,int col){
        assert grid[row][col] == 1;

        if(grid[Math.max(row-1,0)][col] == 0){
            grid[Math.max(row-1,0)][col] = 1;
            make_full(Math.max(row-1,0),col);
        }
        if(grid[row][Math.max(col-1,0)] == 0){
            grid[row][Math.max(col-1,0)] = 1;
            make_full(row,Math.max(col-1,0));
        }
        if(grid[Math.min(row+1,grid.length-1)][col] == 0) {
            grid[Math.min(row + 1, grid.length - 1)][col] = 1;
            make_full(Math.min(row+1,grid.length-1),col);
        }
        if(grid[row][Math.min(col+1,grid.length-1)] == 0) {
            grid[row][Math.min(col + 1, grid.length - 1)] = 1;
            make_full(row,Math.min(col+1, grid.length-1));
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if(row<0 || row>=grid.length || col<0 || col>=grid.length)
            throw new IndexOutOfBoundsException();

        return grid[row][col] != -1;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if(row<0 || row>=grid.length || col<0 || col>=grid.length)
            throw new IndexOutOfBoundsException();

        return grid[row][col] == 1;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        int cnt = 0;
        for(int r = 0;r < grid.length;r++){
            for(int c = 0;c < grid.length;c++){
                if(grid[r][c] != -1)
                    cnt++;
            }
        }
        return cnt;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        for(int i = 0;i < grid.length;i++){
            if(grid[grid.length-1][i] == 1)
                return true;
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
