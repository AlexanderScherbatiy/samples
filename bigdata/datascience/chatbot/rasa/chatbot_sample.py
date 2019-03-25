from rasa_nlu.model import Metadata, Interpreter

interpreter = Interpreter.load('./models/nlu/default/chat')


def ask_question(text):
    print(interpreter.parse(text))


ask_question("Hey")
ask_question("How many days in March")
ask_question("Goodbye")
