package fransonsr.dataTypes;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringSizeTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringSizeTest.class);

    static class SerializableList extends ArrayList<String> implements Serializable {
        private static final long serialVersionUID = -794199470998679802L;
    }

    @Test
    public void testStringSize_serialized() throws Exception {
        String test = "";

        byte[] bytes = SerializationUtils.serialize(test);

        LOG.info("Empty string size: " + bytes.length);
    }
}
