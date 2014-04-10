package fransonsr.vitualMethods;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class VirtualMethodTest {

    static class A {

        public String getName() {
            return "A";
        }
    }

    static class B extends A {

        @Override
        public String getName() {
            return "B";
        }
    }

    @Test
    public void testVirtualMethods() throws Exception {
        B b = new B();

        /*
         * All methods in Java are 'virtual' in that the JVM determines the method invocation at runtime
         * not compile time. Methods marked 'final' are prohibited by the compiler from being overridden,
         * but the are invoked the same during runtime.
         */

        assertThat(b.getName(), is("B"));
        assertThat(((A)b).getName(), is("B"));
    }

}
