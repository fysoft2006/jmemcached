package org.jmemcached.protocol.binary;

/**
 * Represents request header.
 * @see <a href="http://code.google.com/p/memcached/wiki/BinaryProtocolRevamped#Request_header">Structure of header</a>
 *
      Byte/     0       |       1       |       2       |       3       |
         /              |               |               |               |
        |0 1 2 3 4 5 6 7|0 1 2 3 4 5 6 7|0 1 2 3 4 5 6 7|0 1 2 3 4 5 6 7|
        +---------------+---------------+---------------+---------------+
       0| Magic         | Opcode        | Key length                    |
        +---------------+---------------+---------------+---------------+
       4| Extras length | Data type     | vbucket id                    |
        +---------------+---------------+---------------+---------------+
       8| Total body length                                             |
        +---------------+---------------+---------------+---------------+
      12| Opaque                                                        |
        +---------------+---------------+---------------+---------------+
      16| CAS                                                           |
        |                                                               |
        +---------------+---------------+---------------+---------------+
        Total 24 bytes
 */
@SuppressWarnings("MagicNumber")
public final class RequestHeader {
    private static final int MAGIC_OFFSET = 0;
    private static final int OPCODE_OFFSET = 1;
    private static final int KEY_LENGTH_OFFSET = 2;
    private static final int EXTRA_LENGTH_OFFSET = 4;
    private static final int DATA_TYPE_OFFSET = 5;
    private static final int VBUCKET_OFFSET = 6;
    private static final int TOTAL_BODY_LENGTH_OFFSET = 8;
    private static final int OPAQUE_OFFSET = 12;
    private static final int CAS_OFFSET = 16;

    public static final int HEADER_LENGTH = 24;

    private final byte[] data;

    public RequestHeader(byte[] data) {
        assert data.length >= HEADER_LENGTH;
        this.data = data;
    }

    public Magic getMagic() {
        return Magic.fromCode(getMagicCode());
    }

    public Operation getOperation() {
        return Operation.fromCode(getOpcode());
    }

    public int getKeyLength() {
        return decodeInt(data, KEY_LENGTH_OFFSET);
    }

    public short getExtraLength() {
        return decodeShort(data, EXTRA_LENGTH_OFFSET);
    }

    public DataType getDataType() {
        return DataType.fromCode(getDataTypeCode());
    }

    public int getVbucket() {
        return decodeInt(data, VBUCKET_OFFSET);
    }

    public int getTotalBodyLength() {
        return decodeInt(data, TOTAL_BODY_LENGTH_OFFSET);
    }

    public int getOpaque() {
        return decodeInt(data, OPAQUE_OFFSET);
    }

    public long getCas() {
        return decodeLong(data, CAS_OFFSET);
    }

    private short getMagicCode() {
        return decodeShort(data, MAGIC_OFFSET);
    }

    private short getOpcode() {
        return decodeShort(data, OPCODE_OFFSET);
    }

    private short getDataTypeCode() {
        return decodeShort(data, DATA_TYPE_OFFSET);
    }

    private static short decodeShort(byte[] data, int i) {
        return (short) ((data[i] & 0xff) << 8 | (data[i + 1] & 0xff));
    }

    private static int decodeInt(byte[] data, int i) {
        return (data[i] & 0xff) << 24
                | (data[i + 1] & 0xff) << 16
                | (data[i + 2] & 0xff) << 8
                | (data[i + 3] & 0xff);
    }

    private static long decodeLong(byte[] data, int i) {
        return (data[i] & 0xffL) << 56
                | (data[i + 1] & 0xffL) << 48
                | (data[i + 2] & 0xffL) << 40
                | (data[i + 3] & 0xffL) << 32
                | (data[i + 4] & 0xffL) << 24
                | (data[i + 5] & 0xffL) << 16
                | (data[i + 6] & 0xffL) << 8
                | (data[i + 7] & 0xffL);
    }
}
