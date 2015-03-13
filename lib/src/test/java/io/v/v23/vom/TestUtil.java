package io.v.v23.vom;

import junit.framework.TestCase;

import io.v.v23.vdl.VdlType;
import io.v.v23.vdl.VdlValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.Arrays;

public class TestUtil {
    static String bytesToHexString(byte[] dat) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : dat) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    static byte[] hexStringToBytes(String hex) {
        if (hex.length() % 2 != 0) {
            throw new RuntimeException("Hex strings must be multiples of 2 in length");
        }
        int outLen = hex.length() / 2;
        byte[] dat = new byte[outLen];
        for (int i = 0; i < outLen; i++) {
            dat[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return dat;
    }

    static Object decode(byte[] bytes, Type targetType) throws Exception {
        BinaryDecoder decoder = new BinaryDecoder(new ByteArrayInputStream(bytes));
        return decoder.decodeValue(targetType);
    }

    static Object decode(byte[] bytes) throws Exception {
        BinaryDecoder decoder = new BinaryDecoder(new ByteArrayInputStream(bytes));
        return decoder.decodeValue();
    }

    static String encode(VdlType type, Object value) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryEncoder encoder = new BinaryEncoder(out);
        encoder.encodeValue(type, value);
        return TestUtil.bytesToHexString(out.toByteArray());
    }

    static String encode(VdlValue value) throws Exception {
        return encode(value.vdlType(), value);
    }

    static String encode(Type type, Object value) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryEncoder encoder = new BinaryEncoder(out);
        encoder.encodeValue(type, value);
        return TestUtil.bytesToHexString(out.toByteArray());
    }

    static void assertEqual(Object expected, Object actual) {
        if (expected.getClass().isArray()) {
            Class<?> component = expected.getClass().getComponentType();
            if (component == Boolean.TYPE) {
                TestCase.assertTrue(Arrays.equals((boolean[]) expected, (boolean[]) actual));
            } else if (component == Byte.TYPE) {
                TestCase.assertTrue(Arrays.equals((byte[]) expected, (byte[]) actual));
            } else if (component == Short.TYPE) {
                TestCase.assertTrue(Arrays.equals((short[]) expected, (short[]) actual));
            } else if (component == Integer.TYPE) {
                TestCase.assertTrue(Arrays.equals((int[]) expected, (int[]) actual));
            } else if (component == Long.TYPE) {
                TestCase.assertTrue(Arrays.equals((long[]) expected, (long[]) actual));
            } else if (component == Float.TYPE) {
                TestCase.assertTrue(Arrays.equals((float[]) expected, (float[]) actual));
            } else if (component == Double.TYPE) {
                TestCase.assertTrue(Arrays.equals((double[]) expected, (double[]) actual));
            } else {
                TestCase.assertTrue(Arrays.equals((Object[]) expected, (Object[]) actual));
            }
        } else {
            TestCase.assertEquals(expected, actual);
        }
    }
}
