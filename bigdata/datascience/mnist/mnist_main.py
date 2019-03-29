import matplotlib.pyplot as plt
from mnist_data import load_data_from_keras
from sample_logistic_regression import SKLearnLogisticRegression

x_train, y_train, x_test, y_test = load_data_from_keras()
# x_train, y_train, x_test, y_test = load_data_from_file("mnist.csv")

model = SKLearnLogisticRegression()
show_plot = True

if show_plot:
    x_train_img = x_train.reshape(x_train.shape[0], 28, 28)
    plt.figure(figsize=(20, 4))
    for index, (image, label) in enumerate(zip(x_train_img[0:5], y_train[0:5])):
        plt.subplot(1, 5, index + 1)
        plt.imshow(image, cmap=plt.cm.gray)
        plt.title('Training: %i\n' % label, fontsize=20)

model.train(x_train, y_train)

score = model.score(x_test, y_test)

print('score:', score)

predictions = model.predict(x_test)

if show_plot:

    index = 0
    misclassified_indexes = []
    for label, predict in zip(y_test, predictions):
        if label != predict:
            misclassified_indexes.append(index)
        index += 1

    x_test_img = x_test.reshape(x_test.shape[0], 28, 28)
    plt.figure(figsize=(20, 4))
    for plotIndex, badIndex in enumerate(misclassified_indexes[0:5]):
        plt.subplot(1, 5, plotIndex + 1)
        plt.imshow(x_test_img[badIndex], cmap=plt.cm.gray)
        plt.title('Predicted: {} ({})'.format(predictions[badIndex], y_test[badIndex]), fontsize=15)
    plt.show()

if show_plot:
    plt.show()
