package org.jmemcached.protocol.binary;

import com.google.common.base.Preconditions;

public enum Operation {
    GET(0x00),;
    private final int code;

    private Operation(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    private static final OperationCache OPERATION_CACHE = new OperationCache();

    public static Operation fromCode(int code) {
        Operation operation = OPERATION_CACHE.getOperationByCode(code);
        Preconditions.checkArgument(operation != null, "Unsupported operation code: %s", code);
        return operation;
    }


    static class OperationCache {
        private final Operation[] code2Operation;

        OperationCache() {
            Operation[] operations = values();
            int maxCode = calcSize(operations);
            code2Operation = new Operation[maxCode];
            for (Operation operation : operations) {
                code2Operation[operation.code] = operation;
            }
        }

        Operation getOperationByCode(int code) {
            return code2Operation[code];
        }

        private int calcSize(Operation[] operations) {
            int maxCode = 0;
            for (Operation operation : operations) {
                int code = operation.code;
                if (code > maxCode) {
                    maxCode = code;
                }
            }
            return maxCode + 1;
        }
    }
}
