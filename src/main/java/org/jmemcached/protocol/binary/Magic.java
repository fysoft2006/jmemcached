package org.jmemcached.protocol.binary;

public enum Magic implements HasCode {
    REQUEST(0x80),
    RESPONSE(0x81);

    private final int code;

    private Magic(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    private static final EnumCodeCache<Magic> VALUE_CACHE = new EnumCodeCache<Magic>(values());

    public static Magic fromCode(int code) {
        return VALUE_CACHE.getValueByCode(code);
    }
}
