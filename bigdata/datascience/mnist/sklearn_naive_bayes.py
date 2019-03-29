from mnist_model import ClassificationModel
from sklearn.naive_bayes import GaussianNB, BernoulliNB, MultinomialNB


class SKLearnGaussianNB(ClassificationModel):

    def __init__(self):
        self.gaussian_nb = GaussianNB()

    def train(self, data, labels):
        self.gaussian_nb.fit(data, labels)

    def score(self, data, labels):
        return self.gaussian_nb.score(data, labels)

    def predict(self, data):
        return self.gaussian_nb.predict(data)


class SKLearnBernoulliNB(ClassificationModel):

    def __init__(self, alpha=1.0):
        self.bernoulli_nb = BernoulliNB(alpha=alpha)

    def train(self, data, labels):
        self.bernoulli_nb.fit(data, labels)

    def score(self, data, labels):
        return self.bernoulli_nb.score(data, labels)

    def predict(self, data):
        return self.bernoulli_nb.predict(data)


class SKLearnMultinomialNB(ClassificationModel):

    def __init__(self, alpha=1.0):
        self.multinomial_nb = MultinomialNB(alpha=alpha)

    def train(self, data, labels):
        self.multinomial_nb.fit(data, labels)

    def score(self, data, labels):
        return self.multinomial_nb.score(data, labels)

    def predict(self, data):
        return self.multinomial_nb.predict(data)
