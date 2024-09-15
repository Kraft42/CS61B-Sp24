import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     public void addFirstAndaddLasttest(){
         ArryDeque61B<Integer> a1 = new ArryDeque61B<>();
         assertThat(a1.size()).isEqualTo(8);
         a1.addFirst(2);
         a1.addFirst(3);
         a1.addLast(9);
         a1.addFirst(7);
         a1.addLast(6);
         a1.addLast(5);
         a1.addLast(11);
         a1.addLast(4);

        assertThat(a1.toList()).containsExactly(11,4,7,3,2,9,6,5).inOrder();
     }

     @Test
    public void testresize(){
         ArryDeque61B<Integer> a1 = new ArryDeque61B<>();
         assertThat(a1.size()).isEqualTo(8);
         a1.addFirst(2);
         a1.addFirst(3);
         a1.addLast(9);
         a1.addFirst(7);
         a1.addLast(6);
         a1.addLast(5);
         a1.addLast(11);
         a1.addLast(4);
         a1.addLast(15);
         a1.addFirst(17);

         assertThat(a1.size()).isEqualTo(16);
         assertThat(a1.toList()).containsExactly(11,4,15,17,7,3,2,9,6,5).inOrder();
     }
}
