import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {
    @Test
    public void testIterator(){
        LinkedListDeque61B<Integer> a1 = new LinkedListDeque61B<>();
        a1.addLast(1);
        a1.addLast(2);
        a1.addLast(3);
        a1.addLast(4);
        a1.addLast(5);
        a1.addLast(6);

        assertThat(a1).containsExactly(1,2,3,4,5,6).inOrder();
    }

    @Test
    public void testEquals(){
        LinkedListDeque61B<Integer> a1 = new LinkedListDeque61B<>();
        a1.addLast(1);
        a1.addLast(2);
        a1.addLast(3);
        a1.addLast(4);
        a1.addLast(5);
        a1.addLast(6);

        LinkedListDeque61B<Integer> a2 = new LinkedListDeque61B<>();
        a2.addLast(1);
        a2.addLast(2);
        a2.addLast(3);
        a2.addLast(4);
        a2.addLast(5);
        a2.addLast(6);

        assertThat(a1).isEqualTo(a2);
    }

    @Test
    public void testToString(){
        LinkedListDeque61B<String> s = new LinkedListDeque61B<>();
        s.addLast("first");
        s.addLast("middle");
        s.addLast("last");

        assertThat(s.toString()).isEqualTo("[first, middle, last]");
    }
}
