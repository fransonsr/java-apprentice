package fransonsr.paramters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class ParameterTest {

    static class MyClass {
        private String value = "default";

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    @Test
    public void testParmeters() throws Exception {

        int primitiveInt = 3;
        int unmodifiableInt = 5;

        MyClass objectReference = new MyClass();
        MyClass unmodifiableReference = new MyClass();

        changEm(primitiveInt, unmodifiableInt, objectReference, unmodifiableReference);

        assertThat(primitiveInt, is(equalTo(3)));
        assertThat(unmodifiableInt, is(equalTo(5)));
        assertThat(objectReference.getValue(), is("default"));
        assertThat(unmodifiableReference.getValue(), is("modified"));
    }


    /**
     * Demonstrates that all method parameters in Java are 'pass-by-value'. None are 'pass-by-reference'.
     * In the case of the objects, it is the object reference that is passed, and it is passed by value.
     * @param primitiveInt
     * @param unmodifiableInt
     * @param objectReference
     * @param unmodifiableReference
     */
    private void changEm(int primitiveInt, final int unmodifiableInt, MyClass objectReference, final MyClass unmodifiableReference) {

        primitiveInt = 7;
//        unmodifiableInt = 11; // compilation error
        objectReference = new MyClass();
//        unmodifiableReference = new MyClass();    // compilation error

        objectReference.setValue("some other value");
        unmodifiableReference.setValue("modified");
    }
}
