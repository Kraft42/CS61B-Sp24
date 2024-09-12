import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** This test check what will happen if the Deque61B is empty. */
    public void EmptyDequeTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.toList().isEmpty()).isTrue();
    }

    @Test
    public void testIsEmpty(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public  void testIsNotEmpty(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(2);
        lld1.addLast(4);

        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test public void testSizeOne(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);

        assertThat(lld1.size()).isEqualTo(1);
    }

    @Test public void testSizeTwo(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addLast(2);

        assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void testIsEmptyAndSize(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue();
        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void testNormalGetRecursive(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(1);// [1]
        lld1.addLast(4);//  [1,4]
        lld1.addFirst(2);// [2,1,4]
        lld1.addFirst(1);// [1,2,1,4]

        assertThat(lld1.getRecursive(0)).isEqualTo(1);
        assertThat(lld1.getRecursive(1)).isEqualTo(2);
        assertThat(lld1.getRecursive(2)).isEqualTo(1);
        assertThat(lld1.getRecursive(3)).isEqualTo(4);
    }

    @Test
    public void testGetRecursiveOutOfBound(){
        Deque61B<String>  lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("Saber");
        lld1.addLast("Archer");
        lld1.addLast("Lancer");

        assertThat(lld1.getRecursive(114)).isEqualTo(null);
    }

    @Test
    public void testNegativeIndextRecursive(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();

        lld1.addLast(34);
        lld1.addFirst(23);
        lld1.addLast(12);

        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
    }

    @Test
    public void testGetFromEmptyRecursive(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.getRecursive(0)).isEqualTo(null);
    }

    @Test
    public void testNormalGet(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(1);// [1]
        lld1.addLast(4);//  [1,4]
        lld1.addFirst(2);// [2,1,4]
        lld1.addFirst(1);// [1,2,1,4]

        assertThat(lld1.get(0)).isEqualTo(1);
        assertThat(lld1.get(1)).isEqualTo(2);
        assertThat(lld1.get(2)).isEqualTo(1);
        assertThat(lld1.get(3)).isEqualTo(4);
    }

    @Test
    public void testGetOutOfBound(){
        Deque61B<String>  lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("Saber");
        lld1.addLast("Archer");
        lld1.addLast("Lancer");

        assertThat(lld1.get(114)).isEqualTo(null);
    }

    @Test
    public void testNegativeIndext(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();

        lld1.addLast(34);
        lld1.addFirst(23);
        lld1.addLast(12);

        assertThat(lld1.get(-1)).isEqualTo(null);
    }

    @Test
    public void testGetFromEmpty(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.get(0)).isEqualTo(null);
    }

    @Test
    public void removeFirstAndremoveLastTest(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();
        lld1.addLast(2);
        lld1.addFirst(3);
        lld1.addLast(6);
        lld1.addFirst(0);
        lld1.addFirst(5);// [5,0,3,2,6]

        assertThat(lld1.removeFirst()).isEqualTo(5);
        assertThat(lld1.toList()).containsExactly(0,3,2,6).inOrder();
        assertThat(lld1.size()).isEqualTo(4);

        assertThat(lld1.removeLast()).isEqualTo(6);
        assertThat(lld1.toList()).containsExactly(0,3,2).inOrder();
        assertThat(lld1.size()).isEqualTo(3);
    }

    @Test
    public void testRemoveEmpty(){
        Deque61B<Integer>  lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.removeFirst()).isEqualTo(null);
        assertThat(lld1.removeLast()).isEqualTo(null);
    }
}