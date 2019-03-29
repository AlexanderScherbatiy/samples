import matplotlib.pyplot as plt
from sklearn.linear_model import LogisticRegression
from mnist_data import load_data_from_keras


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


x_train, y_train, x_test, y_test = load_data_from_keras()
# x_train, y_train, x_test, y_test = load_data_from_file("mnist.csv")

logistic_regr = logistic_regression_sample(x_train, y_train, x_test, y_test, show_plot=True)
