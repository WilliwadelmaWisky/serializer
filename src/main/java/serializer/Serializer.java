package serializer;

import serializer.format.Formatter;
import serializer.meta.Metadata;
import serializer.meta.ObjectMetadata;

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
     * @param formatter
     * @return
     */
    public String serialize(Object obj, Formatter formatter) {
        Class<?> objClass = obj.getClass();
        Metadata metadata = new ObjectMetadata(objClass.getName(), obj);
        return formatter.convert(metadata);
    }

    /**
     * @param objType
     * @param s
     * @param formatter
     * @param <T>
     * @return
     */
    public <T> T deserialize(Class<T> objType, String s, Formatter formatter) {
        Metadata metadata = formatter.convert(s);
        return metadata.construct(objType);
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
        Object value = field.get(owner);

        if (value.getClass().isArray()) {
            serializeArray(value, stringBuilder);
            return;
        }

        serializeValue(value, stringBuilder);
    }

    /**
     * @param array
     * @param stringBuilder
     */
    public static void serializeArray(Object array, StringBuilder stringBuilder) throws IllegalAccessException {
        stringBuilder.append("[");

        int count = 0;
        for (int i = 0; i < Array.getLength(array); i++) {
            if (count > 0) {
                stringBuilder.append(",");
            }

            Object element = Array.get(array, i);
            serializeValue(element, stringBuilder);
            count++;
        }

        stringBuilder.append("]");
    }

    public static void serializeValue(Object value, StringBuilder stringBuilder) throws IllegalAccessException {
        if (PrimitiveUtil.isPrimitiveType(value)) {
            serializePrimitiveValue(value, stringBuilder);
            return;
        }

        serializeObject(value, stringBuilder);
    }

    /**
     * @param value
     * @param stringBuilder
     */
    public static void serializePrimitiveValue(Object value, StringBuilder stringBuilder) {
        stringBuilder.append(PrimitiveUtil.convertToString(value));
    }
}
