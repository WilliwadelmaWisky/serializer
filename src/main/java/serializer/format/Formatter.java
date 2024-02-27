package serializer.format;

import serializer.meta.Metadata;

/**
 *
 */
public interface Formatter {

    /**
     * @param metadata
     * @return
     */
    String convert(Metadata metadata);

    /**
     * @param s
     * @return
     */
    Metadata convert(String s);
}
