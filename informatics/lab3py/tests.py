from task1 import Task1
from task2 import Task2
from task3 import Task3


class Tests:
    def main(self):
        self.run()

    def run(self):
        self.task1()
        self.task2()
        self.task3()

    def task1(self):
        task1 = Task1()

        print("TASK 1")

        self.assert_equals("3", task1.operate("X-{\\ X-{\\ X-{\\"))
        self.assert_equals("0", task1.operate(":):):):):)"))
        self.assert_equals("1", task1.operate(":):):)XXX) \"X-{\\"))
        self.assert_equals("4", task1.operate(".F#$95X-{x\\f X-{\\X-{\\X-{\\"))
        self.assert_equals("9", task1.operate("X-{\\ X-{\\ X-{\\ X-{\\:):)X-{\\ X-{\\X-{\\ X-{\\ X-{\\"))

        print("TASK 1 PASSED\n")

    def task2(self):
        task2 = Task2()

        print("TASK 2")

        self.assert_equals("ВТ ахаха ахахха ИТМО\n", task2.operate("ВТ ахаха ахахха ИТМО"))
        self.assert_equals("", task2.operate("ВТ ахаха ахаха ахаха ахаха ахахха ИТМО"))
        self.assert_equals("ВТ ИТМО\n", task2.operate("fwe fwe ВТ ИТМО fwe fwe"))
        self.assert_equals("", task2.operate("ахахахха ИТМО"))
        self.assert_equals("", task2.operate("В ИТМО"))
        self.assert_equals("", task2.operate("бобобо"))

        print("TASK 2 PASSED\n")

    def task3(self):
        task3 = Task3()

        print("TASK 3")

        self.assert_equals("КоРмА", task3.operate("КоРмА КоРкА КоРчмА"))
        self.assert_equals("", task3.operate("КкРмА КРМА КРара хихи-хаха"))
        self.assert_equals("КшРшА", task3.operate(" уац уш КшРшА шу бобобо"))
        self.assert_equals("", task3.operate("КооРмммммА"))
        self.assert_equals("К1Р2А", task3.operate("К1Р2А"))

        print("TASK 3 PASSED\n")

    def assert_equals(self, expected, actual):
        print(f"passed: expected[{expected}] actual[{actual}]")

        if not actual.strip() == expected.strip():
            raise NotEqualsException(expected, actual)


class NotEqualsException(Exception):
    def __init__(self, expected, actual):
        super().__init__(" ".join(["expected", expected, "actual", actual]))


if __name__ == "__main__":
    tests = Tests()
    tests.main()
