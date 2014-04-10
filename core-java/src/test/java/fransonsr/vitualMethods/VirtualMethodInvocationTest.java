package fransonsr.vitualMethods;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class VirtualMethodInvocationTest {

    static interface Named {
        String getName();
    }

    static class A implements Named {
        public String getName() {
            return "A";
        }
    }

    static class B implements Named {
        public String getName() {
            return "B";
        }
    }

    static class NameResolver {

        public String getName(Named named) {
            return named.getName();
        }
    }

    @Test
    public void testVirtualMethodInvocation() throws Exception {
        A a = new A();
        B b = new B();

        NameResolver nameResolver = new NameResolver();

        /*
         * Demonstrate 'virtual method invocation'; meaning polymorphism.
         */

        assertThat(nameResolver.getName(a), is("A"));
        assertThat(nameResolver.getName(b), is("B"));
    }
}


