import re

from task import Task


class Task1(Task):
    def run(self):
        self.write(self.operate(self.read()))

    def operate(self, val):
        pattern = re.compile(r'X-\{\.{0}')
        count = len(pattern.findall(val))
        return str(count)
