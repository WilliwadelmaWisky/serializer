import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 *
 */
public class Util {

    /**
     * @param field
     * @return
     */
    public static boolean isFieldIgnored(Field field) {
        return field.isAnnotationPresent(Ignored.class) || Modifier.isStatic(field.getModifiers());
    }
}
