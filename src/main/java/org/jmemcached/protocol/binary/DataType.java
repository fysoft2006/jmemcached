package org.jmemcached.protocol.binary;

public enum DataType implements HasCode {
    RAW_BYTES(0x00);

    private final int code;

    private DataType(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    private static final EnumCodeCache<DataType> VALUE_CACHE = new EnumCodeCache<DataType>(values());

    public static DataType fromCode(int code) {
        return VALUE_CACHE.getValueByCode(code);
    }
}
