import java.lang.reflect.Array;
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
        StringBuilder stringBuilder = new StringBuilder();

        try {
            serializeObject(obj, stringBuilder);
        } catch (IllegalAccessException illegalAccessException) {
            throw new RuntimeException(illegalAccessException);
        }

        return stringBuilder.toString();
    }


    /**
     * @param obj
     * @param stringBuilder
     * @throws IllegalAccessException
     */
    public static void serializeObject(Object obj, StringBuilder stringBuilder) throws IllegalAccessException {
        stringBuilder.append("{");

        int count = 0;
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (Util.isFieldIgnored(field))
                continue;

            if (count > 0) {
                stringBuilder.append(",");
            }

            serializeField(field, obj, stringBuilder);
            count++;
        }

        stringBuilder.append("}");
    }

    /**
     * @param field
     * @param owner
     * @param stringBuilder
     * @throws IllegalAccessException
     */
    public static void serializeField(Field field, Object owner, StringBuilder stringBuilder) throws IllegalAccessException {
        field.setAccessible(true);
        stringBuilder.append(field.getName());
        stringBuilder.append(":");
        serializeValue(field.get(owner), stringBuilder);
    }

    /**
     * @param value
     * @param stringBuilder
     * @throws IllegalAccessException
     */
    public static void serializeValue(Object value, StringBuilder stringBuilder) throws IllegalAccessException {
        if (value.getClass().isArray()) {
            serializeArray(value, stringBuilder);
            return;
        }

        if (PrimitiveUtil.isPrimitiveType(value)) {
            stringBuilder.append(PrimitiveUtil.convertToString(value));
            return;
        }

        serializeObject(value, stringBuilder);
    }

    /**
     * @param array
     * @param stringBuilder
     */
    public static void serializeArray(Object array, StringBuilder stringBuilder) {
        stringBuilder.append("[");

        int count = 0;
        for (int i = 0; i < Array.getLength(array); i++) {
            if (count > 0) {
                stringBuilder.append(",");
            }

            Object element = Array.get(array, i);
            stringBuilder.append(element.toString());
            count++;
        }

        stringBuilder.append("]");
    }
}
