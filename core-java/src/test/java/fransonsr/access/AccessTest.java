package fransonsr.access;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import fransonsr.access.other.MyClass;

public class AccessTest {

    static class MySubClass extends MyClass {
        @Override
        protected String getProtectedValue() {
            return super.getProtectedValue();
        }

        @Override
        public String getPublicValue() {
            return super.getPublicValue();
        }
    }

    @Test
    public void testAccess() throws Exception {
        MySubClass mySubClass = new MySubClass();

//        assertThat(mySubClass.getPrivateValue(), is("privateValue"));
//        assertThat(mySubClass.getDefaultValue(), is("defaultValue"));
        assertThat(mySubClass.getProtectedValue(), is("protectedValue"));
        assertThat(mySubClass.getPublicValue(), is("publicValue"));
    }
}
