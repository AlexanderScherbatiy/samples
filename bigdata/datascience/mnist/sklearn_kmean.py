from mnist_model import ClassificationModel
import numpy as np
from sklearn.cluster import KMeans
from sklearn import decomposition


class SKLearnKMean(ClassificationModel):

    def __init__(self):
        self.N = 10
        self.pca = decomposition.PCA(n_components=390)
        self.kmeans = KMeans(n_clusters=self.N)
        self.class_mapping = np.zeros(self.N)

    def train(self, data, labels):
        self.pca.fit(data)
        self.kmeans.fit(self.pca.transform(data))

        class_map = np.zeros((self.N, self.N))
        for (cls, label) in zip(self.kmeans.labels_, labels):
            class_map[cls][label] += 1

        for i in range(self.N):
            max_index = class_map[i].argmax()
            self.class_mapping[i] = max_index

    def score(self, data, labels):
        predict = self.predict(data)
        score = 0.0
        for i in range(labels.size):
            if labels[i] == predict[i]:
                score += 1.0

        return score / labels.size

    def predict(self, data):
        predicted_labels = self.kmeans.predict(self.pca.transform(data))
        prediction = np.zeros(predicted_labels.size)

        for i in range(predicted_labels.size):
            predicted_label = predicted_labels[i]
            prediction[i] = self.class_mapping[predicted_label]
            pass
        return prediction
