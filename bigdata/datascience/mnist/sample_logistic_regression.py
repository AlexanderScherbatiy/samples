import tensorflow as tf
import matplotlib.pyplot as plt
from sklearn.linear_model import LogisticRegression
import pandas as pd


def logistic_regression_sample(x_train, y_train, x_test, y_test, show_plot=False):
    if show_plot:
        x_train_img = x_train.reshape(x_train.shape[0], 28, 28)
        plt.figure(figsize=(20, 4))
        for index, (image, label) in enumerate(zip(x_train_img[0:5], y_train[0:5])):
            plt.subplot(1, 5, index + 1)
            plt.imshow(image, cmap=plt.cm.gray)
            plt.title('Training: %i\n' % label, fontsize=20)

    logistic_regr = LogisticRegression(solver='lbfgs')
    logistic_regr.fit(x_train, y_train)

    score = logistic_regr.score(x_test, y_test)
    print('score:', score)

    predictions = logistic_regr.predict(x_test)

    index = 0
    misclassified_indexes = []
    for label, predict in zip(y_test, predictions):
        if label != predict:
            misclassified_indexes.append(index)
        index += 1

    if show_plot:
        x_test_img = x_test.reshape(x_test.shape[0], 28, 28)
        plt.figure(figsize=(20, 4))
        for plotIndex, badIndex in enumerate(misclassified_indexes[0:5]):
            plt.subplot(1, 5, plotIndex + 1)
            plt.imshow(x_test_img[badIndex], cmap=plt.cm.gray)
            plt.title('Predicted: {} ({})'.format(predictions[badIndex], y_test[badIndex]), fontsize=15)
        plt.show()

    return logistic_regr


def load_data_from_file(data_file):
    mnist_df = pd.read_csv(data_file)
    mnist = mnist_df.values
    N = mnist.shape[0]

    x_data = mnist[:, 1:]
    y_data = mnist[:, 0].reshape(N, 1)

    split_index = int(N * 0.8)

    x_train = x_data[0:split_index, :]
    y_train = y_data[0:split_index, :]

    x_test = x_data[split_index:, :]
    y_test = y_data[split_index:, :]

    return x_train, y_train, x_test, y_test


def load_data_from_keras():
    mnist = tf.keras.datasets.mnist
    (x_train_img, y_train), (x_test_img, y_test) = mnist.load_data()

    dimension = x_train_img.shape[1] * x_train_img.shape[2]

    x_train = x_train_img.reshape(x_train_img.shape[0], dimension)
    x_test = x_test_img.reshape(x_test_img.shape[0], dimension)

    return x_train, y_train, x_test, y_test


x_train, y_train, x_test, y_test = load_data_from_keras()
# x_train, y_train, x_test, y_test = load_data_from_file("mnist.csv")

logistic_regr = logistic_regression_sample(x_train, y_train, x_test, y_test, show_plot=True)
