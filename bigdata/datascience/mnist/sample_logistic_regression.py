import tensorflow as tf
import matplotlib.pyplot as plt
from sklearn.linear_model import LogisticRegression

mnist = tf.keras.datasets.mnist

(x_train_img, y_train), (x_test_img, y_test) = mnist.load_data()

dimension = x_train_img.shape[1] * x_train_img.shape[2]

x_train = x_train_img.reshape(x_train_img.shape[0], dimension)
x_test = x_test_img.reshape(x_test_img.shape[0], dimension)

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

plt.figure(figsize=(20, 4))
for plotIndex, badIndex in enumerate(misclassified_indexes[0:5]):
    plt.subplot(1, 5, plotIndex + 1)
    plt.imshow(x_test_img[badIndex], cmap=plt.cm.gray)
    plt.title('Predicted: {} ({})'.format(predictions[badIndex], y_test[badIndex]), fontsize=15)

plt.show()
