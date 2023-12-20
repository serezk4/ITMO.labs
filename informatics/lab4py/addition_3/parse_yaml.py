from lark import Lark, Transformer, Tree
from xml.etree.ElementTree import Element, SubElement, tostring

yaml_grammar = r"""
start: document

document: block_entry+

block_entry: block_mapping | block_sequence | block_scalar

block_mapping: key ":" value

block_sequence: "-" value

block_scalar: SCALAR

value: block_mapping | block_sequence | block_scalar | flow_collection

flow_collection: flow_mapping | flow_sequence

flow_mapping: "{" (key_value ("," key_value)*)? "}"

flow_sequence: "[" (value ("," value)*)? "]"

key_value: key ":" value

key: SCALAR

SCALAR: /[a-zA-Z0-9_\-]+/

%import common.WS
%ignore WS
"""


class YamlTransformer(Transformer):
    def start(self, items):
        return items

    def document(self, items):
        return items[0]

    def block_sequence(self, items):
        return {"sequence": items}

    def block_mapping(self, items):
        key, value = items
        return {key: value}

    def block_scalar(self, items):
        return items[0][1:-1]  # Removing quotes

    def empty_scalar(self, items):
        return None

    def key(self, items):
        return items[0]


# Пример использования
yaml_parser = Lark(yaml_grammar, parser="lalr", transformer=YamlTransformer())

yaml_string = """
name: John Doe
age: 30
is_student: false
grades:
  subject: Math
    grade: 90
  subject: English
    grade: 85
"""

parsed_data = yaml_parser.parse(yaml_string)
print(parsed_data)
