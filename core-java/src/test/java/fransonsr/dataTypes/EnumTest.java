package fransonsr.dataTypes;

import org.junit.Test;

public class EnumTest {

    /**
     * Enumeration to constrain a value to a set.
     * @author FransonSR
     *
     */
    static enum MyEnum {
        ANIMAL,
        VEGETABLE,
        MINERAL;
    }

    @Test
    public void testValues() throws Exception {
        for(MyEnum e : MyEnum.values()) {
            System.out.println(e.name());
        }
    }
}
