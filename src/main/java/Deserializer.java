import java.lang.reflect.Constructor;

/**
 *
 */
public class Deserializer {

    /**
     * @param c
     * @param s
     * @param <T>
     * @return
     */
    public static <T> T deserialize(Class<T> c, String s) {
        try {
            Constructor<T> ctor = c.getDeclaredConstructor();
            T newObject = ctor.newInstance();
            return newObject;

        } catch (Exception ignored) { }

        return null;
    }
}
