from dicttoxml import dicttoxml
from xml.dom.minidom import parseString


def parse_xml(data, result_file):
    xml = dicttoxml(data, attr_type=False)
    xml_string = parseString(xml).toprettyxml()

    result_file.write(xml_string)