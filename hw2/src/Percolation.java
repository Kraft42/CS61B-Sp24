import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private int virtual_top,vritual_bottom;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        // -1 represent blocked,0 represent open,1 represent full.
        if(N <= 0)
            throw new IllegalArgumentException("N must be bigger than 0.");

        grid = new boolean[N][N];
        for(int r = 0;r < N;r++){
            for(int c = 0;c < N;c++){
                grid[r][c] = false;
            }
        }

        uf = new WeightedQuickUnionUF(N*N+2);
        virtual_top = N*N;
        vritual_bottom = N*N+1;
    }

    private int tr_h(int row, int col){
        return row * grid.length + col;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if(row<0 || row>=grid.length || col<0 || col>=grid.length)
            throw new IndexOutOfBoundsException();

        grid[row][col] = true;
        if(row == 0)
            uf.union(tr_h(row, col),virtual_top);
        if(row == grid.length-1)
            uf.union(tr_h(row,col),vritual_bottom);

        if(grid[Math.max(row - 1, 0)][col]){
            uf.union(tr_h(row,col), tr_h(Math.max(row-1,0),col));
        }
        if(grid[row][Math.max(col - 1, 0)]){
            uf.union(tr_h(row,col), tr_h(row,Math.max(col-1,0)));
        }
        if(grid[Math.min(row + 1, grid.length - 1)][col]){
            uf.union(tr_h(row,col), tr_h(Math.min(row+1,grid.length-1),col));
        }
        if(grid[row][Math.min(col + 1, grid.length - 1)]){
            uf.union(tr_h(row,col), tr_h(row,Math.min(col+1,grid.length-1)));
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if(row<0 || row>=grid.length || col<0 || col>=grid.length)
            throw new IndexOutOfBoundsException();

        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if(row<0 || row>=grid.length || col<0 || col>=grid.length)
            throw new IndexOutOfBoundsException();

        return uf.connected(tr_h(row,col),virtual_top);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return grid.length*grid.length - uf.count();
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return uf.connected(virtual_top,vritual_bottom);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
