package fastscanner;


import java.util.Objects;


public class FastScannerTest {

    private static class Test {
        public static boolean assertLogging = true;

        public static void setAssertLogging(boolean flag) {
            Test.assertLogging = flag;
        }

        private static void logAssertMessage(boolean assertResult, String messagePassed, String messageFailed) {
            if (assertLogging) {
                if (assertResult) {
                    System.out.println(messagePassed);
                } else {
                    System.out.println(messageFailed);
                }
            }
        }

        public static boolean assertEquality(Object obj1, Object obj2) {
            boolean result = Objects.equals(obj1, obj2);
            if (assertLogging) {
                String assertAnswer = String.format("Assertion equality failed: %s, %s", String.valueOf(obj1), String.valueOf(obj2));
                if (result) {
                    assertAnswer = "Assertion passed";
                }
                System.out.println(assertAnswer);
            }
            return result;
        }

        ;
    }

    public static void main(String[] args) {
        Test.assertEquality(new FastScanner("123").next(), "123");
        Test.assertEquality(new FastScanner("123").nextInt(), 123);
        Test.assertEquality(new FastScanner("    123    ").nextInt(), 123);
    }
}
