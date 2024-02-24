public class TestClassOne {
    public String str;
    private final float f;
    private final boolean bool = true;
    private static final int max_value = 10;

    private TestClassTwo subClassOne;

    @Ignored
    private final TestClassTwo subClassTwo = new TestClassTwo(10);

    private final int[] intArray = new int[] { 5, 10, 15, 16 };


    public TestClassOne() {
        str = "string_value";
        f = 1.009f;
        subClassOne = new TestClassTwo(20);
    }
}
