package org.jmemcached.protocol.binary;

public enum Magic {
    REQUEST(0x80),
    RESPONSE(0x81);

    private final int code;

    private Magic(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Magic fromCode(int code) {
        if (code == REQUEST.code) {
            return REQUEST;
        } else if (code == RESPONSE.code) {
            return RESPONSE;
        }
        throw new IllegalArgumentException("Unsupported magic code:" + code);
    }
}
