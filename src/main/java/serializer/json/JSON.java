package serializer.json;

import serializer.Serializer;

/**
 *
 */
public class JSON {

    /**
     * @param obj
     * @return
     */
    public static String stringify(Object obj) {
        Serializer.serialize(obj);
        return null;
    }

    /**
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T parse(String json) {
        return null;
    }
}
