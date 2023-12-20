import time


def parser(yaml_text, xml_file):
    global i
    indent = 0
    previous_indent = -2
    closing_tags = []
    while i < len(yaml_text):
        while yaml_text[i] == ' ':
            i += 1
            indent += 1
        while indent <= previous_indent:
            xml_file.write(previous_indent // 2 * '\t' + closing_tags[-1] + '\n')
            closing_tags.pop(-1)
            previous_indent -= 2
        if yaml_text[i:i + 2] == ': ':
            xml_file.write(tagContentParser(yaml_text, indent) + closing_tags[-1])
            closing_tags.pop(-1)
        elif yaml_text[i:i + 2] == ':\n':
            i += 1
            indent += 2
            previous_indent += 2
        elif yaml_text[i] == '\n':
            xml_file.write('\n')
            i += 1
            indent = 0
        else:
            xml_file.write(indent // 2 * '\t' + tagParser(yaml_text, closing_tags))
    while len(closing_tags) != 0:
        xml_file.write(previous_indent // 2 * '\t' + closing_tags[-1] + '\n')
        closing_tags.pop(-1)
        previous_indent -= 2


def tagParser(yaml_text, closing_tags):
    global i
    new_tag = '<'
    if yaml_text[i] in ['"', "'"]:
        new_tag = '<' + stringParser(yaml_text) + '>'
    else:
        while yaml_text[i:i + 2] not in [': ', ':\n']:
            new_tag += yaml_text[i]
            i += 1
        new_tag += '>'
    closing_tags.append('</' + new_tag[1:])
    return new_tag


def tagContentParser(yaml_text, indent):
    global i
    tag_content = ''
    i += 2
    if yaml_text[i] in ['"', "'"]:
        tag_content = stringParser(yaml_text)
    elif yaml_text[i:i + 2] in ['|\n', '>\n']:
        tag_content = multilineStringParser(yaml_text, indent)
    else:
        while yaml_text[i] != '\n':
            tag_content += yaml_text[i]
            i += 1
    return tag_content


def stringParser(yaml_text):
    global i
    new_string = ''
    opening_quote = yaml_text[i]
    i += 1
    while yaml_text[i] != opening_quote:
        new_string += yaml_text[i]
        i += 1
    return new_string


def multilineStringParser(yaml_text, indent):
    global i
    new_string = ''
    i += 2
    while True:
        line_indent = 0
        while yaml_text[i] == ' ':
            i += 1
            line_indent += 1
        if line_indent <= indent:
            break
        else:
            while yaml_text[i] != '\n':
                new_string += yaml_text[i]
            new_string += ' '
            i += 1
    return new_string


with open('../data.yaml', 'r', encoding='utf-8') as YML, open(f'3_{time.time()}_out.xml', 'w', encoding='utf-8') as XML:
    i = 0
    s = YML.read()
    parser(s, XML)

def generate():
    print()
