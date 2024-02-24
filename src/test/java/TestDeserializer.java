import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 */
public class TestDeserializer {

    /**
     *
     */
    @Test
    public void testDeserialize() {
        TestClassOne t = Deserializer.deserialize(TestClassOne.class, "{value='Handle'}");
        assertNotNull(t);
        assertEquals("string_value", t.str);
    }
}
