import collections


class XML:
    def __init__(self, xml_string):
        self.xml_string = xml_string


class YAML:
    def __init__(self, yaml_string):
        self.yaml_string = yaml_string


class Converter:
    def convert(self, yaml):
        return XML(map_to_xml(parse(yaml.yaml_string)))


class YAMLtoXMLConverter(Converter):
    def convert(self, yaml):
        return XML(map_to_xml(parse(yaml.yaml_string)))


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
            xml_builder += f"  <{key}>{value[0]}</{key}>\n"

    return f"<{parent}>\n{xml_builder}</{parent}>\n"


class Data:
    def __init__(self, key, value=None):
        self.key = key
        self.value = value is None if [] else value

    def add(self, data):
        self.value.append(data)

    def add_all(self, dataset):
        for data in dataset:
            self.value.append(data)

    def __str__(self):
        return f"{self.key}={self.value}"


def parse(yaml):
    if not yaml:
        return Data("")

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
            data.append(Data(key, [Data(value)]))
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
            data.append(Data(key, [Data(value)]))
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


yaml_data = """
  schedule:
    day:
      name: saturday
      classes:
        opd1:
          type: practice
          lector: Саржевский Иван Анатольевич
          time: 10.00 - 11.30
          classroom: 1327
          location: Kronverksky pr. 49
        opd2:
          type: practice
          lector: Саржевский Иван Анатольевич
          time: 11.40 - 12.50
          classroom: 1327
          location: Kronverksky pr. 49
        history1:
          type: lecture
          lector: Жиркова Галина Петровна
          time: 13.30 - 14.50
          classroom: not indicated
          location: not indicated
        history2:
          type: practice
          lector: Мартынова Дарья Олеговна
          time: 13.30 - 14.50
          classroom: not indicated
          location: not indicated
"""

result = map_to_xml(parse(yaml_data))
print(result)
