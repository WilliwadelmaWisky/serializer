package serializer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 *
 */
public class Deserializer {

    /**
     * @param objClass
     * @param s
     * @param formatter
     * @param <T>
     * @return
     */
    public static <T> T deserialize(Class<T> objClass, String s, Formatter formatter) {
        Metadata metadata = formatter.convert(s);
        return createObject(objClass, metadata);
    }


    public static <T> T deserializeObject(Class<T> objClass, StringBuilder stringBuilder) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> ctor = objClass.getDeclaredConstructor();
        T obj = ctor.newInstance();

        for (Field field : obj.getClass().getDeclaredFields()) {
            if (Util.isFieldIgnored(field))
                continue;

            deserializeField(field, obj, stringBuilder);
        }

        return obj;
    }

    public static void deserializeField(Field field, Object obj, StringBuilder stringBuilder) {

    }


    public static void deserializeArray(Class<?> arrayClass, StringBuilder s) {

    }

    public static Object deserializeValue(Class<?> valueType, StringBuilder stringBuilder) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (PrimitiveUtil.isPrimitiveType(valueType)) {
            return deserializePrimitiveValue(valueType, stringBuilder);
        }

        return deserializeObject(valueType, stringBuilder);
    }

    public static Object deserializePrimitiveValue(Class<?> valueType, StringBuilder stringBuilder) {
        if (valueType.equals(Integer.class)) {
            return Integer.parseInt(stringBuilder.toString());
        }

        if (valueType.equals(Long.class)) {
            return Long.parseLong(stringBuilder.toString());
        }

        return stringBuilder.toString();
    }


    public static <T> T createObject(Class<T> objClass, Metadata metadata) {
        try {
            Constructor<T> ctor = objClass.getDeclaredConstructor();
            T obj = ctor.newInstance();

            for (Field field : objClass.getDeclaredFields()) {
                if (Util.isFieldIgnored(field))
                    continue;

                Metadata fieldData = metadata.getField(field.getName());
                if (field.getType().isArray()) {
                    continue;
                }

                if (PrimitiveUtil.isPrimitiveType(field.getType())) {
                    continue;
                }

                Object value = createObject(field.getType(), fieldData);
                field.set(obj, value);
            }

        } catch (Exception ignored) { }

        return null;
    }
}
