import re

from task import Task


class Task3(Task):
    def run(self):
        self.write(self.operate(self.read()))

    def operate(self, val):
        pattern = re.compile(r'[Кк][^кКрРаА][Рр][^кКрРаА][Аа]')
        matches = pattern.finditer(val)

        result = "\n".join(match.group() for match in matches)
        return result
