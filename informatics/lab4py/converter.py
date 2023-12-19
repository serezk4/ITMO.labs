import collections
import time

def map_to_xml(dataset):
    return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + helper(dataset.value, dataset.key).replace("=[]|[]", "")


def helper(dataset, parent):
    xml_builder = ""
    for data in dataset:
        key = data.key
        value = data.value

        if key == "" and not value:
            continue
        if len(value) > 1:
            xml_builder += helper(value, key)
        else:
            xml_builder += f"{'  ' * (data.indentation + 1)}<{key}>{value[0]}</{key}>\n"

    return f"{'  '*data.indentation}<{parent}>\n{xml_builder}{'  '*data.indentation}</{parent}>\n"


class Data:
    def __init__(self, indentation, key, value=None):
        self.key = key
        self.value = value is None if [] else value
        self.indentation = indentation

    def add(self, data):
        self.value.append(data)

    def add_all(self, dataset):
        for data in dataset:
            self.value.append(data)

    def __str__(self):
        return f"{self.key}"


def parse(yaml):
    if not yaml:
        return Data(0, "")

    yaml = "body\n" + yaml + "\n "

    indentations = collections.deque()
    data = collections.deque()
    indentations.append(float("-inf"))

    cached_key = ""
    cached_indentation = -1
    cached_value = ""
    next_line = False
    block = False

    tokenizer = yaml.split("\n")
    for token in tokenizer:
        if "#" in token:
            token = token[:token.index("#")]

        if (not token.strip()) and block:
            token = f"{cached_key}:{cached_value}"
            cached_key = ""
            cached_value = ""
            block = False

        if block:
            cached_value += f"{token.strip()} {'\n' if next_line else ''}"
            continue

        if not token.strip():
            continue

        indentation = cached_indentation if cached_indentation != -1 else get_indentation_level(token)
        split = token.split(":", 1)

        if len(split) == 0:
            raise ValueError(token)

        key = split[0].strip()
        value = split[1].strip() if len(split) == 2 else ''

        if value.startswith(">"):
            block = True
            next_line = not bool(value.startswith(">-"))
            cached_key = key
            cached_indentation = indentation
            cached_value += f"{value.replace('>-', '').strip()} {'\n' if next_line else ''}"
            continue

        if indentation >= indentations[-1]:
            data.append(Data(indentation, key, [Data(indentation+2, value)]))
            indentations.append(indentation)
            continue

        if indentation < indentations[-1]:
            buffer = []
            buffer_level = indentations[-1]
            while buffer_level > indentation:
                buffer.append(data.pop())
                indentations.pop()

                if indentations[-1] != buffer_level:
                    buffer.reverse()
                    data[-1].add_all(buffer)
                    buffer.clear()
                    buffer_level = indentations[-1]
                    continue

            if buffer:
                buffer.reverse()
                data[-1].add_all(buffer)
            indentations.append(indentation)
            data.append(Data(indentation, key, [Data(indentation+1, value)]))
            continue

    buffer = []
    buffer_level = indentations.pop()

    while len(indentations) > 1:


        buffer.append(data.pop())
        if indentations[-1] != buffer_level:
            buffer.reverse()
            data[-1].add_all(buffer)
            buffer.clear()
            buffer_level = indentations.pop()
            continue
        indentations.pop()

    if buffer:
        buffer.reverse()
        data[-1].add_all(buffer)

    return data[0]


def get_indentation_level(line):
    count = 0
    while count < len(line) and line[count] == " ":
        count += 1
    return count // 2


yaml_data = open('data.yaml', encoding='utf-8', mode='r').read()
xml_out = open(f"{time.time()}_out.xml", encoding='utf-8', mode='w+')
xml_out.write(map_to_xml(parse(yaml_data)))
