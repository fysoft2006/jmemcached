package org.jmemcached.protocol.binary;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class OperationTest {
    @Test public void
    shouldReturnOperationForValidCode() throws Exception {
        Operation operation = Operation.fromCode(Operation.GET.getCode());

        assertThat(operation, Matchers.equalTo(Operation.GET));
    }


    @Test(expected = RuntimeException.class) public void
    shouldThrowRuntimeExceptionForTooLargeCode() throws Exception {
        Operation.fromCode(Integer.MAX_VALUE);
    }

    @Test(expected = RuntimeException.class) public void
    shouldThrowRuntimeExceptionForNegativeCode() throws Exception {
        Operation.fromCode(-1);
    }
}
