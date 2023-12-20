import re

from task import Task


class Task2(Task):
    def run(self):
        self.write(self.operate(self.read()))

    def operate(self, val):
        pattern = re.compile(r'ВТ( [а-яА-Я\w–]+){0,4} ИТМО')
        matches = pattern.finditer(val)

        result = "\n".join(match.group() for match in matches)
        return result
