from abc import ABC, abstractmethod

class Task(ABC):
    @abstractmethod
    def run(self):
        pass

    @abstractmethod
    def operate(self, val):
        pass

    def read(self):
        try:
            input_data = ""
            while True:
                line = input().strip()
                if line.lower() in ["end", "к", "e"]:
                    break
                input_data += line + " "

            return input_data
        except Exception as ex:
            print(ex)
            return "READ_ERROR"

    def write(self, text):
        try:
            print(f"answer: {text}")
        except Exception as ex:
            print(ex)
