import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 *
 */
public class Serializer {

    /**
     * @param obj
     * @return
     */
    public static String serialize(Object obj) {
        try {
            Field field = obj.getClass().getDeclaredField("value");
            return "{" + field.getName() + "='" + field.get(obj).toString() + "'}";

        } catch (Exception ignored) { }

        return null;
    }

    /**
     * @param c
     * @param s
     * @param <T>
     * @return
     */
    public static <T> T deserialize(Class<T> c, String s) {
        try {
            for (Constructor<?> cto : c.getDeclaredConstructors())
                System.out.println(cto.getName());

            Constructor<T> ctor = c.getDeclaredConstructor();
            T newObject = ctor.newInstance();
            return newObject;

        } catch (Exception ignored) { }

        return null;
    }
}
