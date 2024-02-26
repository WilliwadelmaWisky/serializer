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
     * @param <T>
     * @return
     */
    public static <T> T deserialize(Class<T> objClass, String s) {
        try {
            StringBuilder stringBuilder = new StringBuilder(s);
            return deserializeObject(objClass, stringBuilder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T deserializeObject(Class<T> objClass, StringBuilder stringBuilder) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> ctor = objClass.getDeclaredConstructor();
        T obj = ctor.newInstance();

        return obj;
    }

    public static void deserializeField(Field field, Object obj, String s) {

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
}
