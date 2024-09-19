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
         ArrayDeque61B<Integer> a1 = new ArrayDeque61B<>();
         assertThat(a1.size()).isEqualTo(0);
         a1.addFirst(2);
         a1.addFirst(3);
         a1.addLast(9);
         a1.addFirst(7);
         a1.addLast(6);
         a1.addLast(5);
         a1.addLast(11);
         a1.addLast(4);

        assertThat(a1.toList()).containsExactly(7,3,2,9,6,5,11,4).inOrder();
     }

     @Test
     public void testresizeup(){
         ArrayDeque61B<Integer> a1 = new ArrayDeque61B<>();
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

         assertThat(a1.size()).isEqualTo(10);
         assertThat(a1.toList()).containsExactly(17,7,3,2,9,6,5,11,4,15).inOrder();
     }

     @Test
     public void testisEmpty(){
         ArrayDeque61B<Integer> a1 = new ArrayDeque61B<>();
         assertThat(a1.isEmpty()).isTrue();
         a1.addFirst(1);
         assertThat(a1.isEmpty()).isFalse();
     }

     @Test
     public void testget(){
         ArrayDeque61B<Integer> a1 = new ArrayDeque61B<>();
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

         assertThat(a1.get(1)).isEqualTo(7);
         assertThat(a1.get(-1)).isEqualTo(null);
         assertThat(a1.get(10)).isEqualTo(null);
     }

     @Test
     public void RemoveFirstAndRemoveLasttest(){
         ArrayDeque61B<String> a1 = new ArrayDeque61B<>();
         a1.addFirst("a");
         a1.addFirst("c");
         a1.addLast("f");
         a1.addFirst("t");
         a1.addLast("h");
         a1.addLast("q");
         a1.addLast("s");
         a1.addLast("b");
         a1.addLast("l");
         a1.addFirst("k");

         assertThat(a1.toList()).containsExactly("k","t","c","a","f","h","q","s","b","l").inOrder();
         assertThat(a1.removeFirst()).isEqualTo("k");
         assertThat(a1.removeLast()).isEqualTo("l");
         assertThat(a1.toList()).containsExactly("t","c","a","f","h","q","s","b").inOrder();
         assertThat(a1.size()).isEqualTo(8);

         ArrayDeque61B<String> a2 = new ArrayDeque61B<>();
         assertThat(a2.removeFirst()).isEqualTo(null);
     }

     @Test
     public void testresizedown(){
         ArrayDeque61B<String> a1 = new ArrayDeque61B<>();
         for(int i = 0;i<20;i++){
             a1.addLast("a");
         }

         assertThat(a1.getMax_size()).isEqualTo(32);
         for(int i = 0;i < 18;i++){
             a1.removeFirst();
         }
         assertThat(a1.getMax_size()).isEqualTo(8);
         assertThat(a1.size()).isEqualTo(2);
     }
}
