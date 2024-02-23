import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class TestSerializer {

    /**
     *
     */
    @Test
    public void testSerialize() {
        int[] testArray = new int[5];
        System.out.println(testArray.getClass().isArray());

        TestClass t = new TestClass();
        String s = Serializer.serialize(t);
        assertEquals("{str:string_value,bool:true}", s);
    }

    /**
     *
     */
    @Test
    public void testDeserialize() {
        TestClass t = Serializer.deserialize(TestClass.class, "{value='Handle'}");
        assertNotNull(t);
        assertEquals("string_value", t.str);
    }


    public static class TestClass {
        public String str;
        private final boolean bool = true;
        private SubClass subClass;


        //@Ignored
        private final int[] intArray;
        private HashMap<String, Integer> map;


        public TestClass() {
            str = "string_value";
            subClass = new SubClass();
            intArray = new int[] { 1, 2, 3 };
            map = new HashMap<>();
        }
    }

    public static class SubClass {
        private final boolean bool = false;
        public final int i = 10;

        public SubClass() { }
    }
}
