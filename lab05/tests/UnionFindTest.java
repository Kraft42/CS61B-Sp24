import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.fail;

public class UnionFindTest {

    /**
     * Checks that the initial state of the disjoint sets are correct (this will pass with the skeleton
     * code, but ensure it still passes after all parts are implemented).
     */
    @Test
    public void initialStateTest() {
        UnionFind uf = new UnionFind(4);
        assertThat(uf.connected(0, 1)).isFalse();
        assertThat(uf.connected(0, 2)).isFalse();
        assertThat(uf.connected(0, 3)).isFalse();
        assertThat(uf.connected(1, 2)).isFalse();
        assertThat(uf.connected(1, 3)).isFalse();
        assertThat(uf.connected(2, 3)).isFalse();
    }

    /**
     * Checks that invalid inputs are handled correctly.
     */
    @Test
    public void illegalFindTest() {
        UnionFind uf = new UnionFind(4);
        try {
            uf.find(10);
            fail("Cannot find an out of range vertex!");
        } catch (IllegalArgumentException e) {
            return;
        }
        try {
            uf.union(1, 10);
            fail("Cannot union with an out of range vertex!");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Checks that union is done correctly (including the tie-breaking scheme).
     */
    @Test
    public void basicUnionTest() {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        assertThat(uf.find(0)).isEqualTo(1);
        uf.union(2, 3);
        assertThat(uf.find(2)).isEqualTo(3);
        uf.union(0, 2);
        assertThat(uf.find(1)).isEqualTo(3);

        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(4, 8);
        uf.union(4, 6);

        assertThat(uf.find(5)).isEqualTo(9);
        assertThat(uf.find(7)).isEqualTo(9);
        assertThat(uf.find(8)).isEqualTo(9);

        uf.union(9, 2);
        assertThat(uf.find(3)).isEqualTo(9);
    }

    /**
     * Unions the same item with itself. Calls on find and checks that the outputs are correct.
     */
    @Test
    public void sameUnionTest() {
        UnionFind uf = new UnionFind(4);
        uf.union(1, 1);
        for (int i = 0; i < 4; i += 1) {
            assertThat(uf.find(i)).isEqualTo(i);
        }
    }

    /**
     * Write your own tests below here to verify for correctness. The given tests are not comprehensive.
     * Specifically, you may want to write a test for path compression and to check for the correctness
     * of all methods in your implementation.
     */
    @Test
    public void testCreate(){
        UnionFind u1 = new UnionFind(5);

        for(int i = 0;i < 5;i++) {
            assertThat(u1.sizeOf(i)).isEqualTo(1);
            assertThat(u1.parent(i)).isEqualTo(-1);
        }
    }

    @Test
    public void testSzieOf(){
        UnionFind u1 = new UnionFind(8);
        u1.union(0,1);
        u1.union(0,2);
        u1.union(2,7);
        u1.union(3,4);
        u1.union(5,6);

        assertThat(u1.sizeOf(2)).isEqualTo(4);
        assertThat(u1.sizeOf(3)).isEqualTo(2);
        assertThat(u1.sizeOf(5)).isEqualTo(2);
    }

    @Test
    public void testParent(){
        UnionFind u1 = new UnionFind(8);
        u1.union(0,1);
        u1.union(0,2);
        u1.union(2,7);
        u1.union(3,4);

        assertThat(u1.parent(0)).isEqualTo(1);
        assertThat(u1.parent(2)).isEqualTo(1);
        assertThat(u1.parent(5)).isEqualTo(-1);
    }

    @Test
    public void testConneccted(){
        UnionFind u1 = new UnionFind(8);
        u1.union(0,1);
        u1.union(0,2);
        u1.union(2,7);
        u1.union(3,4);

        assertThat(u1.connected(1,1)).isTrue();
        assertThat(u1.connected(1,7)).isTrue();
        assertThat(u1.connected(2,3)).isFalse();
    }

    @Test
    public void testPath_Compressed(){
        UnionFind u1 = new UnionFind(8);
        u1.union(0,1);
        u1.union(0,2);
        u1.union(2,7);
        u1.union(3,4);
        u1.union(5,6);
        u1.union(3,5);
        u1.union(2,5);

        u1.find(0);
        assertThat(u1.parent(0)).isEqualTo(6);

        u1.find(3);
        assertThat(u1.parent(3)).isEqualTo(6);

        UnionFind u2 = new UnionFind(8);
        u2.union(1,0);
        u2.union(3,2);
        u2.union(5,4);
        u2.union(7,6);
        u2.union(7,5);
        u2.union(3,1);
        u2.union(4,0);

        u2.find(7);
        assertThat(u2.parent(7)).isEqualTo(0);
        assertThat(u2.parent(6)).isEqualTo(0);
    }
}


