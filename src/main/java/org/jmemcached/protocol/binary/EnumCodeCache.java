package org.jmemcached.protocol.binary;

import com.google.common.base.Preconditions;

class EnumCodeCache<T extends Enum<T> & HasCode> {
    private final Object[] code2Value;

    public EnumCodeCache(T[] values) {
        int size = calcSize(values);
        code2Value = new Object[size];
        for (T value : values) {
            code2Value[value.getCode()] = value;
        }
    }

    public T getValueByCode(int code) {
        Preconditions.checkArgument(code >= 0 && code < code2Value.length, "Unsupported code: %s", code);
        @SuppressWarnings("unchecked")
        final T value = (T) code2Value[code];
        Preconditions.checkArgument(value != null, "Unsupported code: %s", code);
        return value;
    }

    private int calcSize(T[] values) {
        int maxCode = 0;
        for (T value : values) {
            int code = value.getCode();
            if (code > maxCode) {
                maxCode = code;
            }
        }
        return maxCode + 1;
    }
}
