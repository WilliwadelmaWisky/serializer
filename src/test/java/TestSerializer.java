import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class TestSerializer {

    @Test
    public void testSerialize() {
        TestClass t = new TestClass();
        String s = Serializer.serialize(t);
        assertEquals("{value='Handle'}", s);
    }

    @Test
    public void testDeserialize() {
        TestClass t = Serializer.deserialize(TestClass.class, "{value='Handle'}");
        assertNotNull(t);
        assertEquals("Handle", t.value);
    }


    public static class TestClass {
        public String value = "Handle";


        public TestClass() {
            value = "Handle";
        }
    }
}
