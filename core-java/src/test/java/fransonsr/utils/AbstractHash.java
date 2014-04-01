/**
 *
 */
package fransonsr.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Abstract base-class for creating a hash value for plain-text data.
 * The {@link #toString()} method is overridden to return a hex-string
 * representation of the hash value.
 * @author fransonsr
 *
 */
public abstract class AbstractHash {

    private final byte[] hash;

    protected AbstractHash(final char[] data) {
        CharBuffer charBuffer = CharBuffer.wrap(data);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = byteBuffer.array();

        hash = create(bytes);

        // clear the intermediate byteBuffer
        for(int i = 0; i < bytes.length; i++) {
            bytes[i] = '*';
        }
    }

    protected AbstractHash(final byte[] data) {
        hash = create(data);
    }

    /**
     * Return a copy of the hash value.
     * @return
     */
    public byte[] getHash() {
        return Arrays.copyOf(hash, hash.length);
    }

    /**
     * Hook method for concrete implementations to return the
     * intended hash algorithm.
     * @return
     */
    protected abstract String getAlgorithm();

    private byte[] create(byte[] data) {
        byte[] hashBytes = new byte[0];

        try {
            hashBytes = MessageDigest.getInstance(getAlgorithm()).digest(data);
        } catch (NoSuchAlgorithmException e1) {
        }

        return hashBytes;
    }

    @Override
    public String toString() {
        StringBuffer hashBuffer = new StringBuffer();
        for(byte b : hash) {
            hashBuffer.append(Integer.toHexString(0xFF & b));
        }

        return hashBuffer.toString();
    }
}
