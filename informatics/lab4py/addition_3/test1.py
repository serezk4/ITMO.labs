import xml.etree.ElementTree as ET
from lark import Lark, Transformer

yaml_grammar = r"""
    %import common.WS
    %ignore WS

    start: document

    document: pair*

    pair: key ":" value
        | key ":"

    key: /[a-zA-Z_]\w*/

    value: scalar
        | list
        | map

    scalar: /[^\n:]+/
        | /("[^"]*")+/  // Multiline scalar

    list: "[" [value ("," value)*] "]"

    map: "{" [pair ("," pair)*] "}"
"""

class YAMLTransformer(Transformer):
    def document(self, items):
        return dict(items)

    def pair(self, items):
        if len(items) == 2:
            key, value = items
            return key, value
        elif len(items) == 1:
            return items[0], None

    def key(self, items):
        return items[0].value

    def value(self, items):
        return items[0]

    def scalar(self, items):
        return items[0]

    def list(self, items):
        return items

    def map(self, items):
        return dict(items)

yaml_parser = Lark(yaml_grammar, parser='lalr', transformer=YAMLTransformer())

def parse_yaml(yaml_text):
    return yaml_parser.parse(yaml_text)

def convert_to_xml(data):
    root = ET.Element("root")
    convert_dict_to_xml(data, root)
    return ET.tostring(root, encoding="utf-8").decode("utf-8")

def convert_dict_to_xml(data, parent):
    for key, value in data.items():
        if isinstance(value, dict):
            child = ET.SubElement(parent, key)
            convert_dict_to_xml(value, child)
        elif isinstance(value, list):
            for item in value:
                child = ET.SubElement(parent, key[0])
                convert_dict_to_xml(item, child)
        else:
            child = ET.SubElement(parent, key[0])
            child.text = str(value)

# Example usage
yaml_text = """
name: "John Doe"
age: 30
address:
  street: "123 Main St"
  city: "Anytown"
"""

parsed_yaml = parse_yaml(yaml_text)
xml_output = convert_to_xml(parsed_yaml)
print(xml_output)