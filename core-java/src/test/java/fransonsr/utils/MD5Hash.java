/**
 *
 */
package fransonsr.utils;

/**
 * Creates a MD5 hash of data. The {@link #toString()} method
 * is overridden to return a hex-string representation of the
 * value.
 * @author fransonsr
 *
 */
public class MD5Hash extends AbstractHash {

    public MD5Hash(final char[] data) {
        super(data);
    }

    public MD5Hash(final byte[] data) {
        super(data);
    }

    @Override
    protected String getAlgorithm() {
        return "MD5";
    }
}
