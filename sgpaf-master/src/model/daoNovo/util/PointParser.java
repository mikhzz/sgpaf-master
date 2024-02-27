package model.daoNovo.util;

import java.util.Arrays;

public class PointParser {
	/**
     * Read a long from a byte buffer in big endian format.
     * @param buf must be 8 bytes
     */
    public static long readLongFromBytesBigEndian(byte[] buf) {
            return    (long) (buf[0] & 0xff) << 56
                    | (long) (buf[1] & 0xff) << 48
                    | (long) (buf[2] & 0xff) << 40
                    | (long) (buf[3] & 0xff) << 32
                    | (long) (buf[4] & 0xff) << 24
                    | (long) (buf[5] & 0xff) << 16
                    | (long) (buf[6] & 0xff) <<  8
                    | (long) (buf[7] & 0xff);
    }

    /**
     * Read a long from a byte buffer in little endian format.
     * @param buf must be 8 bytes
     */
    public static long readLongFromBytesLittleEndian(byte[] buf) {
            return    (long) (buf[7] & 0xff) << 56
                    | (long) (buf[6] & 0xff) << 48
                    | (long) (buf[5] & 0xff) << 40
                    | (long) (buf[4] & 0xff) << 32
                    | (long) (buf[3] & 0xff) << 24
                    | (long) (buf[2] & 0xff) << 16
                    | (long) (buf[1] & 0xff) <<  8
                    | (long) (buf[0] & 0xff);
    }

    /**
     * Read a long from a byte buffer in big or little endian format.
     * @param bigEndian true for big endian, false for little endian
     * @param buf must be 8 bytes
     */
    public static double readDoubleFromBytes(byte[] buf, boolean bigEndian) {
        long longVal =  bigEndian ? readLongFromBytesBigEndian(buf)
                : readLongFromBytesLittleEndian(buf);
        return Double.longBitsToDouble(longVal);
    }

    /**
     * Read a long from a byte buffer in big or little endian format.
     * @param bigEndian true for big endian, false for little endian
     * @param buf must be 8 bytes length after offset
     */
    public static double readDoubleFromBytes(byte[] buf, int offset, boolean bigEndian) {
        byte[] bufOf8Bytes = Arrays.copyOfRange(buf, offset, offset + 8);
        return readDoubleFromBytes(bufOf8Bytes, bigEndian);
    }

    /**
     * Read a coordinate from a byte array in WKB format.
     * @param wkbBytes must be 25 bytes long
     */
    public static Coordinate readCoordinateFromWkbBytes(byte[] wkbBytes) {
        // Points are stored in MySQL marked as big endian, but in reality is little endian. Not good
        boolean isBigEndian = false; // readIsWkbBigEndianByteOrder(wkbBytes[0]);
        double x = readDoubleFromBytes(wkbBytes, 9, isBigEndian);
        double y = readDoubleFromBytes(wkbBytes, 17, isBigEndian);
        Coordinate coordinate = new Coordinate();
        coordinate.setX(x);
        coordinate.setY(y);
        return coordinate;
    }

    public static boolean readIsWkbBigEndianByteOrder(byte b) {
        final byte BIG_ENDIAN = 0;
        @SuppressWarnings("unused")
		final byte LITTLE_ENDIAN = 1;
        return b == BIG_ENDIAN;
    }
}
