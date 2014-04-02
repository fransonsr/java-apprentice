package fransonsr.composition;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.junit.Test;

public class CompositionTest {

    /**
     * Composition: MyClass HAS A MessageWriter.
     * @author FransonSR
     *
     */
    public class MyClass {

        private MessageWriter messageWriter;

        public MyClass(MessageWriter messageWriter) {
            this.messageWriter = messageWriter;
        }

        public void write(String message) throws IOException {
            messageWriter.writeMessage(message, System.out);
        }
    }

    /**
     * Strategy pattern to define different ways to write out a message.
     * @author FransonSR
     *
     */
    interface MessageWriter {
        void writeMessage(String message, OutputStream out) throws IOException;
    }


    /**
     * A JSON MessageWriter.
     * @author FransonSR
     *
     */
    public class JSONMessageWriter implements MessageWriter {
        @Override
        public void writeMessage(String message, OutputStream out) throws IOException {
            String json = String.format("{\"message\":\"%s\"}", message);
            out.write(json.getBytes(Charset.forName("UTF-8")));
        }
    }

    /**
     * A XML MessageWriter.
     * @author FransonSR
     *
     */
    public class XMLMessageWriter implements MessageWriter {
        @Override
        public void writeMessage(String message, OutputStream out) throws IOException {
            StringBuilder buff = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<message>").append(message).append("</message>");

            out.write(buff.toString().getBytes(Charset.forName("UTF-8")));
        }
    }

    @Test
    public void testWrite_JSON() throws Exception {
        MyClass message = new MyClass(new JSONMessageWriter());
        message.write("This is a test.");
    }

    @Test
    public void testWrite_XML() throws Exception {
        MyClass message = new MyClass(new XMLMessageWriter());
        message.write("This is a test.");
    }
}
