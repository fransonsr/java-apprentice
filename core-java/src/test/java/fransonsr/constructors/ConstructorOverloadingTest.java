package fransonsr.constructors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class ConstructorOverloadingTest {

    // also a poor example of encapsulation!
    static class MyPoorEncapsulationClass {

        String value1;
        String value2;
        String value3;
        String value4;

        public MyPoorEncapsulationClass(String value1, String value2, String value3, String value4) {
            this.value1 = value1;
            this.value2 = value2;
            this.value3 = value3;
            this.value4 = value4;
        }

        public MyPoorEncapsulationClass(String value1, String value2, String value3) {
            this(value1, value2, value3, "<default 4>");
        }

        public MyPoorEncapsulationClass(String value1, String value2) {
            this(value1, value2, "<default 3>");
        }

        public MyPoorEncapsulationClass(String value1) {
            this(value1, "<default 2>");
        }

    }

    @Test
    public void testConstructor_MyPoorEncapsulationClass() throws Exception {
        MyPoorEncapsulationClass myClass = new MyPoorEncapsulationClass("value1");

        assertThat(myClass.value1, is(equalTo("value1")));
        assertThat(myClass.value2, is(equalTo("<default 2>")));
        assertThat(myClass.value3, is(equalTo("<default 3>")));
        assertThat(myClass.value4, is(equalTo("<default 4>")));
    }

    @Test
    public void testConstructor_MyGoodEncapsulationClass() throws Exception {
        MyGoodEncapsulationClass myClass = new MyGoodEncapsulationClass("value1");

        assertThat(myClass.toArray(), is(equalTo((new String[] {"value1", "<default 2>", "<default 3>", "<default 4>"}))));

    }

}
