import java.lang.reflect.Array;
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
        StringBuilder stringBuilder = new StringBuilder("{");

        try {
            int count = 0;
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Ignored.class))
                    continue;

                if (count > 0) {
                    stringBuilder.append(',');
                }

                field.setAccessible(true);

                stringBuilder.append(field.getName());
                stringBuilder.append(':');
                Object fieldValue = field.get(obj);

                System.out.println(fieldValue.getClass().getName() + ": " + fieldValue.getClass().isArray());
                stringBuilder.append(fieldValue.toString());
                count++;
            }
        } catch (Exception ignored) { }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

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
