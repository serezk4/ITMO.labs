from pyparsing import Suppress, Word, alphanums, Forward, Group, QuotedString, Optional, ZeroOrMore, delimitedList, nums

LBRACE, RBRACE, LBRACK, RBRACK, COLON, COMMA = map(Suppress, '{}[],:')
TRUE = Suppress("true")
FALSE = Suppress("false")
NULL = Suppress("null")

key = Word(alphanums + "_")
value = Forward()
dict_ = Group(LBRACE + Optional(delimitedList(Group(key + COLON + value + Optional(COMMA)))) + RBRACE)
list_ = Group(LBRACK + Optional(delimitedList(value)) + RBRACK)
string = QuotedString('"')
number = Word("-0123456789", nums)
value << (dict_ | list_ | string | number | TRUE | FALSE | NULL)

yaml_text = '''
name: John Doe
occupation: Developer
age: 30
languages:
  - Python
  - JavaScript
'''

parsed_data = value.parseString(yaml_text).asList()
print(parsed_data)
