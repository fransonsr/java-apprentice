package fransonsr.composition;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.junit.Test;


public class InheritanceTest {

    /**
     * Interface for classes that write a message.
     */
    public interface MessageWriter {

        public abstract void write(String message) throws IOException;
    }

    /**
     * Base class for message writers.
     */
    public abstract class AbstractMessageWriter implements MessageWriter {

        protected void writeMessage(String output, OutputStream out) throws IOException {
            out.write(output.getBytes(Charset.forName("UTF-8")));
        }
    }

    /**
     * A MessageWriter that IS A AbstractWriter that writes JSON.
     */
    public class MyJSONMessageWriter extends AbstractMessageWriter {

        @Override
        public void write(String message) throws IOException {
            String output = String.format("{\"message\":\"%s\"}", message);
            writeMessage(output, System.out);
        }
    }

    /**
     * A MessageWriter that IS A AbstractMessageWriter that writes XML.
     */
    public class MyXMLMessageWriter extends AbstractMessageWriter {
        @Override
        public void write(String message) throws IOException {
            StringBuilder buff = new StringBuilder()
            .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
            .append("<message>").append(message).append("</message>");

            writeMessage(buff.toString(), System.out);
        }
    }

    @Test
    public void testWrite_JSON() throws Exception {
        MessageWriter writer = new MyJSONMessageWriter();
        writer.write("This is a test.");
    }

    @Test
    public void testWrite_XML() throws Exception {
        MessageWriter writer = new MyXMLMessageWriter();
        writer.write("This is a test.");
    }
}
