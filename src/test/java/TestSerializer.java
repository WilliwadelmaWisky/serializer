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
        TestClassOne t = new TestClassOne();
        String s = Serializer.serialize(t);
        assertEquals("{str:string_value,f:1.009,bool:true,subClassOne:{bool:false,i:20},intArray:[5,10,15,16]}", s);
    }
}
