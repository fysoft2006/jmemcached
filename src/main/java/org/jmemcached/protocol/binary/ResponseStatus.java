package org.jmemcached.protocol.binary;

public enum ResponseStatus implements HasCode {
    NO_ERROR(0x0000),
    KEY_NOT_FOUND(0x0001),
    KEY_EXISTS(0x0002),
    VALUE_TOO_LARGE(0x0003),
    INVALID_ARGUMENTS(0x0004),
    ITEM_NOT_STORED(0x0005),
    INC_DEC_NON_NUMERIC_VALUE(0x0006),
    INVALID_VBUCKET(0x0007),
    AUTHENTICATION_ERROR(0x0008),
    AUTHENTICATION_CONTINUE(0x0009),
    UNKNOWN_COMMAND(0x0081),
    OUT_OF_MEMORY(0x0082),
    NOT_SUPPORTED(0x0083),
    INTERNAL_ERROR(0x0084),
    BUSY(0x0085),
    TEMPORARY_FAILURE(0x0086);
    
    private final int code;

    private ResponseStatus(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    private static final EnumCodeCache<ResponseStatus> VALUE_CACHE = new EnumCodeCache<ResponseStatus>(values());

    public static ResponseStatus fromCode(int code) {
        return VALUE_CACHE.getValueByCode(code);
    }
}
