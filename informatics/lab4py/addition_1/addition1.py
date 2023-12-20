import time

import yaml
import xmltodict


def generate():
    with open('../data.yaml', 'r', encoding='utf-8') as YML, open(f'1_{time.time()}_out.xml', 'w+',
                                                                  encoding='utf-8') as XML:
        y = yaml.load(YML, Loader=yaml.SafeLoader)
        x = xmltodict.unparse(y, output=XML, full_document=True)


if __name__ == '__main__':
    generate()
