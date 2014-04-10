package fransonsr.instance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class InstanceOfTest {

    static interface Named {
        String getName();
    }

    static class A implements Named {
        public String getName() {
            return "A";
        }
    }

    static class B extends A implements Named {
        public String getName() {
            return "B";
        }
    }

    @Test
    public void testInstanceOf() throws Exception {
        assertThat(methodCall(new B()), is("B"));
        assertThat(methodCall(new A()), is("A"));
    }

    private String methodCall(Named named) {
        if(named instanceof A) {
            A a = (A) named;
            return a.getName();
        }

        else if(named instanceof B) {
            B b = (B) named;
            return b.getName();
        }

        return "unknown Named";
    }
}
