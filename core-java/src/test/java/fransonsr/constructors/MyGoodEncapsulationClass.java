package fransonsr.constructors;

public class MyGoodEncapsulationClass {

    private final String value1;
    private final String value2;
    private final String value3;
    private final String value4;

    public MyGoodEncapsulationClass(String value1, String value2, String value3, String value4) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    public MyGoodEncapsulationClass(String value1, String value2, String value3) {
        this(value1, value2, value3, "<default 4>");
    }

    public MyGoodEncapsulationClass(String value1, String value2) {
        this(value1, value2, "<default 3>");
    }

    public MyGoodEncapsulationClass(String value1) {
        this(value1, "<default 2>");
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    public String getValue3() {
        return value3;
    }

    public String getValue4() {
        return value4;
    }

    public String[] toArray() {
        return new String[] {value1, value2, value3, value4};
    }
}