import sys
import time

sys.path.append('..')

from addition_3.parse_xml import parse_xml
from addition_3.parse_yaml import parse_yaml

def parse(input_file_name, output_file_name):
    with open(input_file_name, encoding='utf-8', mode='r') as input_file:
        input_file_data = parse_yaml(input_file)

    print(input_file_data)

    with open(output_file_name, encoding='utf-8', mode='w+') as output_file:
        parse_xml(input_file_data, output_file)


if __name__ == "__main__":
    input_file_name = "../data.yaml"
    output_file_name = f"{time.time()}_out.xml"

    parse(input_file_name, output_file_name)

