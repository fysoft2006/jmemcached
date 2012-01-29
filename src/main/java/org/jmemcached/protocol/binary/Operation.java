package org.jmemcached.protocol.binary;

public enum Operation implements HasCode {
    GET(0x00),
    SET(0x01),
    ADD(0x02),
    REPLACE(0x03),
    DELETE(0x04),
    INCREMENT(0x05),
    DECREMENT(0x06),
    QUIT(0x07),
    ;


    private final int code;

    private Operation(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    private static final EnumCodeCache<Operation> VALUE_CACHE = new EnumCodeCache<Operation>(values());

    public static Operation fromCode(int code) {
        return VALUE_CACHE.getValueByCode(code);
    }
}
