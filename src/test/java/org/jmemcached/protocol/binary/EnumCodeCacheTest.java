package org.jmemcached.protocol.binary;


import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class EnumCodeCacheTest {
    @Test
    public void
    shouldReturnValueForValidCode() throws Exception {
        for (TestEnum value : TestEnum.values()) {
            TestEnum result = TestEnum.fromCode(value.getCode());

            assertThat(result, Matchers.equalTo(value));
        }
    }


    @Test(expected = RuntimeException.class)
    public void
    shouldThrowRuntimeExceptionForTooLargeCode() throws Exception {
        TestEnum.fromCode(0x4);
    }

    @Test(expected = RuntimeException.class)
    public void
    shouldThrowRuntimeExceptionForNegativeCode() throws Exception {
        TestEnum.fromCode(-1);
    }

    @Test(expected = RuntimeException.class)
    public void
    shouldThrowRuntimeExceptionForUnknownCode() throws Exception {
        TestEnum.fromCode(0x2);
    }

    enum TestEnum implements HasCode {
        V1(0x0),
        V2(0x1),
        V3(0x3);

        private final int code;

        TestEnum(int code) {
            this.code = code;
        }

        @Override
        public int getCode() {
            return code;
        }

        private static final EnumCodeCache<TestEnum> VALUE_CACHE = new EnumCodeCache<TestEnum>(values());

        public static TestEnum fromCode(int code) {
            return VALUE_CACHE.getValueByCode(code);
        }
    }
}
