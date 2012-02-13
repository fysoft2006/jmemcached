package org.jmemcached.protocol.binary;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public final class EnumCodeCache<T extends Enum<T> & HasCode> {
	private final Class<T> type;
	private final Int2ObjectMap<T> code2Value;

	public EnumCodeCache(Class<T> type, T[] values) {
		this.type = type;
		this.code2Value = createCode2ValueMap(values);
	}

	public T getValueByCode(int code) {
		final T value = code2Value.get(code);
		Preconditions.checkArgument(value != null, "Unsupported code: %s for type: %s", code, type);
		return value;
	}

	private static <T extends HasCode> Int2ObjectMap<T> createCode2ValueMap(T[] values) {
		Int2ObjectMap<T> code2Value = new Int2ObjectOpenHashMap<T>(values.length);
		for (T value : values) {
			code2Value.put(value.getCode(), value);
		}
		return code2Value;
	}
}
