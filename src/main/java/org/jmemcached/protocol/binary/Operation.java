package org.jmemcached.protocol.binary;

public enum Operation implements HasCode {
    GET((byte)0x00),
    SET((byte)0x01),
    ADD((byte)0x02),
    REPLACE((byte)0x03),
    DELETE((byte)0x04),
    INCREMENT((byte)0x05),
    DECREMENT((byte)0x06),
    QUIT((byte)0x07),
    ;


    private final byte code;

    private Operation(byte code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    private static final EnumCodeCache<Operation> VALUE_CACHE = new EnumCodeCache<Operation>(Operation.class, values());

    public static Operation fromCode(int code) {
        return VALUE_CACHE.getValueByCode(code);
    }
}
