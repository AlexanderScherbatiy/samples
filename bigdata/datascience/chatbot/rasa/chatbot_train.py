from rasa_nlu.training_data import load_data
from rasa_nlu.model import Trainer
from rasa_nlu import config


def train(data, config_file, model_dir):
    training_data = load_data(data)
    trainer = Trainer(config.load(config_file))
    trainer.train(training_data)
    trainer.persist(model_dir, fixed_model_name='chat')


# python3 -m spacy download en
# You can now load the model via spacy.load('en')
train('data/nlu_train.md', 'nlu_config.yml', 'models/nlu')
