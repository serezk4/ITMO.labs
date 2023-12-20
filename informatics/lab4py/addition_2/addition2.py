import re
import time

def generate():
    with open('../data.yaml', 'r', encoding='utf-8') as YML, open(f'2_{time.time()}_out.xml', 'w+', encoding='utf-8') as XML:
        closing_tags = []
        closing_tag_indent = 0
        while True:
            s = YML.readline()
            if not s:
                break
            indent = 0 if not re.search(r'^\s+', s) else re.search(r'^\s+', s).end()
            while len(closing_tags) != 0 and indent // 2 <= (0 if not re.search(r'^\t+', closing_tags[-1]) else len(re.search(r'^\t+', closing_tags[-1])[0])):
                XML.write(closing_tags[-1])
                closing_tags.pop(-1)
                if len(closing_tags) == 0 or not re.search(r'^\t+', closing_tags[-1]):
                    closing_tag_indent = 0
                else:
                    closing_tag_indent = re.search(r'^\t+', (closing_tags[-1])).end()
            if re.search(r':\n', s):
                XML.write(indent // 2 * '\t' + f'<{s[indent:-2]}>\n')
                closing_tags.append(indent // 2 * '\t' + f'</{s[indent:-2]}>\n')
                if not re.search(r'^\t+', closing_tags[-1]):
                    closing_tag_indent = 0
                else:
                    closing_tag_indent = re.search(r'^\t+', (closing_tags[-1])).end()
            elif re.search(r':\s', s):
                XML.write(indent // 2 * '\t' + '<' + s[indent:re.search(r':\s', s).start()] + '>' + s[re.search(r':\s', s).end():-1] + '</' + s[indent:re.search(r':\s', s).start()] + '>\n')
        for j in closing_tags[::-1]:
            XML.write(j)
            closing_tags.pop(-1)

if __name__ == '__main__':
    generate()

