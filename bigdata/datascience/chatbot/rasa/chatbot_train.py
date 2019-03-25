from rasa_nlu.training_data import load_data
from rasa_nlu.model import Trainer
from rasa_nlu import config

import logging
from rasa_core.agent import Agent
from rasa_core.policies.memoization import MemoizationPolicy


def train(data, config_file, model_dir):
    training_data = load_data(data)
    trainer = Trainer(config.load(config_file))
    trainer.train(training_data)
    trainer.persist(model_dir, fixed_model_name='chat')


# python3 -m spacy download en
# You can now load the model via spacy.load('en')
train('data/nlu_train.md', 'nlu_config.yml', 'models/nlu')


def train_dialog(dialog_training_data_file, domain_file, path_to_model='models/dialogue'):
    logging.basicConfig(level='INFO')
    agent = Agent(domain_file,
                  policies=[MemoizationPolicy(max_history=1)])
    training_data = agent.load_data(dialog_training_data_file)
    agent.train(training_data)

    agent.persist(path_to_model)


# Train
# --------
train_dialog('data/stories.md', 'domain.yml')
