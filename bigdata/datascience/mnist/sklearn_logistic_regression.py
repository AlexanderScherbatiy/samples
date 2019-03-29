from sklearn.linear_model import LogisticRegression
from mnist_model import ClassificationModel


class SKLearnLogisticRegression(ClassificationModel):

    def __init__(self):
        self.logistic_regr = LogisticRegression(solver='lbfgs')

    def train(self, data, labels):
        self.logistic_regr.fit(data, labels)

    def score(self, data, labels):
        return self.logistic_regr.score(data, labels)

    def predict(self, data):
        return self.logistic_regr.predict(data)
