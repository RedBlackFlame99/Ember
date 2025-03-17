package devv.rbfz.ember.enums;

public enum ByteUnit {
    BYTES(1, "B"),
    KILOBYTES(1024, "KB"),
    MEGABYTES(1024 * 1024, "MB"),
    GIGABYTES(1024 * 1024 * 1024, "GB");

    private final long bytes;
    private final String suffix;

    ByteUnit(long bytes, String suffix) {
        this.bytes = bytes;
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public static long convert(long value, ByteUnit targetUnit) {
        return value / targetUnit.bytes;
    }
}
