package fransonsr.dataTypes;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vladium.utils.ObjectProfiler;

public class StringSizeTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringSizeTest.class);

    static class SerializableList extends ArrayList<String> implements Serializable {
        private static final long serialVersionUID = -794199470998679802L;
    }

    @Test
    public void testStringSize_serialized() throws Exception {
        String test = "";

        byte[] bytes = SerializationUtils.serialize(test);

        LOG.info("Empty string size (serialized): " + bytes.length);
    }

    @Test
    public void testStringSize_sizeOf() throws Exception {
        String test = "";

        int sizeof = ObjectProfiler.sizeof(test);

        LOG.info("Empty string size (sizeof): " + sizeof);
    }

    @Test
    public void testStringSize_estimated() throws Exception {
        int size = 8;       // object overhead; assuming 64-bit JVM, no condensed pointers
        size += 8;          // array reference; assuming 64-bit JVM, no condensed pointers
        size += 12;         // array overhead; object overhead, assuming 64-bit JVM, no condensed pointers + length
        size +=4;           // offset
        size +=4;           // count
        size +=4;           // hash

        LOG.info("Size of empty string: " + size);
    }

    @Test
    public void testStringMemorySize() throws Exception {
        int linkedListEntrySize = 8;    // object overhead
        linkedListEntrySize += 8;       // element reference
        linkedListEntrySize += 8;       // next reference
        linkedListEntrySize += 8;       // previous reference

        int sizeEmptyStringSize = 40;   // empty string size
        sizeEmptyStringSize += linkedListEntrySize; // linked list entry size

        // assuming each string entry of 1000 characters; each unique
        int sizeCharactersPerEntry = 1000 * 2;

        int size = sizeEmptyStringSize + sizeCharactersPerEntry;

        long freeMemory = Runtime.getRuntime().freeMemory();

        // assuming a simple flat heap !!!

        long entries = freeMemory / size;

        long bytes = entries * sizeCharactersPerEntry;

        long overhead = freeMemory - bytes;  // free memory minus characters in bytes

        float overheadPercent = ((float)overhead/(float)freeMemory);

        NumberFormat nf = NumberFormat.getIntegerInstance();
        NumberFormat pf = NumberFormat.getPercentInstance();
        pf.setMinimumFractionDigits(2);

        StringBuilder buff = new StringBuilder();

        buff.append("Estimate:\n")
            .append("\tfreeMemory: ").append(nf.format(freeMemory)).append(" (bytes)\n")
            .append("\tentries: ").append(nf.format(entries)).append("\n")
            .append("\tbytes per entry: ").append(nf.format(size)).append(" (bytes)\n")
            .append("\tcharacters: ").append(nf.format(bytes/2)).append("\n")
            .append("\tcharacters: ").append(nf.format(bytes)).append(" (bytes)\n")
            .append("\toverhead: ").append(nf.format(overhead)).append(" (bytes)\n")
            .append("\toverhead: ").append(pf.format(overheadPercent));

        LOG.info(buff.toString());
    }
}
