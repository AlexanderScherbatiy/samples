from rasa_nlu.model import Metadata, Interpreter
from rasa_core.agent import Agent
from rasa_core.interpreter import RasaNLUInterpreter

interpreter = Interpreter.load('./models/nlu/default/chat')


def parse_question(question):
    print('question:', question)
    print('parse:', interpreter.parse(question))


parse_question("Hey")
parse_question("How many days in March")
parse_question("Goodbye")


def ask_question(question):
    print('question:', question)
    print('answer:', agent.handle_message(question))


rasaNLU = RasaNLUInterpreter("models/nlu/default/chat")
agent = Agent.load("models/dialogue", interpreter=rasaNLU)

ask_question('Hi')
ask_question('How many days in January')
ask_question('Bye')
