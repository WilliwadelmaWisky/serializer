import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
public class TestSerializer {

    /**
     *
     */
    @Test
    public void testSerialize() {
        TestClass t = new TestClass();
        String s = Serializer.serialize(t);
        assertEquals("{str:str,intArray:[5,10,15,16],innerClass:{bool:false,i:10}}", s);
    }

    /**
     *
     */
    @Test
    public void testSerializeObject() throws IllegalAccessException {
        TestClass t = new TestClass();
        StringBuilder stringBuilder = new StringBuilder();
        Serializer.serializeObject(t, stringBuilder);
        assertEquals("{str:str,intArray:[5,10,15,16],innerClass:{bool:false,i:10}}", stringBuilder.toString());
    }

    /**
     *
     */
    @Test
    public void testSerializeField() throws NoSuchFieldException, IllegalAccessException {
        TestClass testClass = new TestClass();
        StringBuilder stringBuilder = new StringBuilder();
        Serializer.serializeField(testClass.getClass().getDeclaredField("str"), testClass, stringBuilder);
        assertEquals("str:str", stringBuilder.toString());

        stringBuilder.setLength(0);
        Serializer.serializeField(testClass.getClass().getDeclaredField("intArray"), testClass, stringBuilder);
        assertEquals("intArray:[5,10,15,16]", stringBuilder.toString());

        stringBuilder.setLength(0);
        Serializer.serializeField(testClass.getClass().getDeclaredField("innerClass"), testClass, stringBuilder);
        assertEquals("innerClass:{bool:false,i:10}", stringBuilder.toString());
    }

    /**
     *
     */
    @Test
    public void testSerializeValue() throws IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        int value1 = 100;
        Serializer.serializeValue(value1, stringBuilder);
        assertEquals("100", stringBuilder.toString());

        stringBuilder.setLength(0);
        boolean[] value2 = new boolean[] { true, false };
        Serializer.serializeValue(value2, stringBuilder);
        assertEquals("[true,false]", stringBuilder.toString());
    }

    /**
     *
     */
    @Test
    public void testSerializeArray() {
        StringBuilder stringBuilder = new StringBuilder();
        float[] array1 = new float[] { 1.0f, 2.3f, 1.2f };
        Serializer.serializeArray(array1, stringBuilder);
        assertEquals("[1.0,2.3,1.2]", stringBuilder.toString());

        stringBuilder.setLength(0);
        char[] array2 = new char[] { 'm', '1', '_' };
        Serializer.serializeArray(array2, stringBuilder);
        assertEquals("[m,1,_]", stringBuilder.toString());
    }
}
