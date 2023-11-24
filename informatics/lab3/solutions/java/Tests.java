public class Tests {
    public static void main(String[] args) {
        new Tests().run();
    }

    private void run() {
        task1();
        task2();
        task3();
    }

    void task1() {
        Task task1 = new Task1();

        System.out.println("TASK 1");

        assertEquals("3", task1.operate("X-{\\ X-{\\ X-{\\"));
        assertEquals("0", task1.operate(":):):):):)"));
        assertEquals("1", task1.operate(":):):)XXX) \"X-{\\"));
        assertEquals("3", task1.operate(".F#$95X-{x\\f X-{\\X-{\\"));
        assertEquals("9", task1.operate("X-{\\ X-{\\ X-{\\ X-{\\:):)X-{\\ X-{\\X-{\\ X-{\\ X-{\\"));

        System.out.println("TASK 1 PASSED\n");
    }

    void task2() {
        Task task2 = new Task2();

        System.out.println("TASK 2");

        assertEquals("ВТ ахаха ахахха ИТМО\n", task2.operate("ВТ ахаха ахахха ИТМО"));
        assertEquals("ВТ ИТМО\n", task2.operate("fwe fwe ВТ ИТМО fwe fwe"));
        assertEquals("", task2.operate("ахахахха ИТМО"));
        assertEquals("", task2.operate("В ИТМО"));
        assertEquals("", task2.operate("бобобо"));

        System.out.println("TASK 2 PASSED\n");
    }

    void task3() {
        Task task3 = new Task3();

        System.out.println("TASK 3");

        assertEquals("КоРмА", task3.operate("КоРмА КоРкА КоРчмА"));
        assertEquals("", task3.operate("КкРмА КРМА КРара хихи-хаха"));
        assertEquals("КшРшА", task3.operate(" уац уш КшРшА шу бобобо"));
        assertEquals("", task3.operate("КооРмммммА"));
        assertEquals("К1Р2А", task3.operate("К1Р2А"));


        System.out.println("TASK 3 PASSED\n");
    }

    public void assertEquals(String expected, String actual) {
        System.out.printf("passed: expected[%s] actual[%s]%n", expected.replaceAll("\n", " "), actual.replaceAll("\n", " "));

        if (!actual.trim().equals(expected.trim())) throw new NotEqualsException(expected, actual);
    }

    public static class NotEqualsException extends RuntimeException {
        public NotEqualsException(String expected, String actual) {
            super(String.join(" ", "expected", expected, "actual", actual));
        }
    }
}
