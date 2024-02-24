import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public abstract class PrimitiveUtil {

    private static final Set<Class<?>> PRIMITIVE_TYPE_SET = new HashSet<>(List.of(
            Boolean.class,
            Character.class, String.class,
            Short.class, Integer.class, Long.class,
            Float.class, Double.class
    ));


    /**
     * @param obj
     * @return
     */
    public static boolean isPrimitiveType(Object obj) {
        return PRIMITIVE_TYPE_SET.contains(obj.getClass());
    }


    public static String convertToString(Object obj) {
        return obj.toString();
    }

    public static Object parse(String s) {
        return null;
    }
}
