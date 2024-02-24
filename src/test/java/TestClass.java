/**
 *
 */
public class TestClass {

    public final String str = "str";

    private final int[] intArray = new int[] { 5, 10, 15, 16 };

    private InnerClass innerClass = new InnerClass();

    private static final int ignoredStatic = 10;

    @Ignored
    private final InnerClass ignoredInnerClass = new InnerClass();


    /**
     *
     */
    public TestClass() { }


    /**
     *
     */
    public static class InnerClass {
        private final boolean bool = false;

        private final int i = 10;


        /**
         *
         */
        public InnerClass() { }
    }
}
