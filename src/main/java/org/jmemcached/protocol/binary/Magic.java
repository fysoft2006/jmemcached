package org.jmemcached.protocol.binary;

public enum Magic implements HasCode {
    REQUEST((byte)0x80),
    RESPONSE((byte)0x81);

    private final byte code;

    private Magic(byte code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    private static final EnumCodeCache<Magic> VALUE_CACHE = new EnumCodeCache<Magic>(Magic.class, values());

    public static Magic fromCode(int code) {
        return VALUE_CACHE.getValueByCode(code);
    }
}
