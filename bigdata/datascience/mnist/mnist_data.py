import tensorflow as tf
import pandas as pd


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
